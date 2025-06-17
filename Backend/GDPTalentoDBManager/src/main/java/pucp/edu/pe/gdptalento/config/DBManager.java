package pucp.edu.pe.gdptalento.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Properties;
/**
 *
 * @author USER
 */
public class DBManager {
    //Patrón Singleton
    private static DBManager dbManager;
    
    private Properties datos;
    private final String hostname;
    private final String usuario;
    private final String password;
    private final String database;
    private final String puerto;
    private final String url;
    private final String tipoBD ;
    private Connection con;
    private String rutaArchivo = "db.properties";
    private ResultSet rs;
    
    private DBManager(){
        //Lectura del archivo properties
        datos = new Properties();
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo);
            datos.load(inputStream);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        //Asignamos valores del archivo leido
        hostname = datos.getProperty("hostname");
        usuario = datos.getProperty("usuario");
        password = datos.getProperty("password");
        puerto = datos.getProperty("puerto");
        database = datos.getProperty("database");
        tipoBD = datos.getProperty("tipoBD");
        
        if(tipoBD.equals("mysql"))
            //Formamos la URL de conexión        
            this.url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true";
        else 
            this.url = "jdbc:sqlserver://" + hostname + 
                    ";encrypt=false;trustServerCertificate=true;databaseName=" + database + 
                    ";integratedSecurity=false;";
    }
    
    public static synchronized DBManager getInstance(){
        if(dbManager == null)
            dbManager = new DBManager();
        return dbManager;
    }
    
    //Nos permite obtener una conexión con la BD
    public Connection getConnection() {
        try {
            System.out.println("Intentando obtener conexión a: " + url);
            System.out.println("Usuario: " + usuario);

            if (con == null || con.isClosed()) {
                if (tipoBD.equals("mysql")) {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } else if (tipoBD.equals("mssql")) {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                }

                con = DriverManager.getConnection(url, usuario, password);
                System.out.println("✅ Se ha establecido la conexión con la BD...");
            }

        } catch (ClassNotFoundException ex) {
            System.err.println("❌ No se encontró el driver de base de datos: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("❌ Error al conectarse a la base de datos:");
            System.err.println("URL: " + url);
            System.err.println("Usuario: " + usuario);
            System.err.println("Mensaje: " + ex.getMessage());
            ex.printStackTrace();
        }

        return con;
    }

    
    public void cerrarConexion() {
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar el lector:" + ex.getMessage());
            }
        }
        if (con != null) {
            try {
                con.close();  
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión:" + ex.getMessage());
            }
        }
    }
    
    //Métodos para llamadas a Procedimientos Almacenados
    public int ejecutarProcedimiento(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) {
        int resultado = 0;
        try{
            CallableStatement cst = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, parametrosSalida);
            if(parametrosEntrada != null)
                registrarParametrosEntrada(cst, parametrosEntrada);
            if(parametrosSalida != null)
                registrarParametrosSalida(cst, parametrosSalida);
        
            resultado = cst.executeUpdate();
        
            if(parametrosSalida != null)
                obtenerValoresSalida(cst, parametrosSalida);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            cerrarConexion();
        }
        return resultado;
    }
    
    public ResultSet ejecutarProcedimientoLectura(String nombreProcedimiento, Map<Integer,Object> parametrosEntrada){
        try{
            CallableStatement cs = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, null);
            if(parametrosEntrada!=null)
                registrarParametrosEntrada(cs,parametrosEntrada);
            rs = cs.executeQuery();
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
        return rs;
    }
    
    public CallableStatement formarLlamadaProcedimiento(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) throws SQLException {
        con = getConnection();
        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
        int cantParametrosEntrada = parametrosEntrada != null ? parametrosEntrada.size() : 0;
        int cantParametrosSalida = parametrosSalida != null ? parametrosSalida.size() : 0;
        int numParams = cantParametrosEntrada + cantParametrosSalida;

        for (int i = 0; i < numParams; i++) {
            call.append("?");
            if (i < numParams - 1) {
                call.append(",");
            }
        }
        call.append(")}");

        System.out.println("⚙️ Llamando al procedimiento: " + call); // 👈 Agrega esta línea
        return con.prepareCall(call.toString());
    }
    
    private void registrarParametrosEntrada(CallableStatement cs, Map<Integer, Object> parametros) throws SQLException {
        for (Map.Entry<Integer, Object> entry : parametros.entrySet()) {
            Integer key = entry.getKey();
            Object value = entry.getValue();

            if (value == null) {
                cs.setObject(key, null); // ✅ Permite enviar null a la BD
                continue;
            }

            switch (value) {
                case Integer entero ->
                    cs.setInt(key, entero);
                case String cadena ->
                    cs.setString(key, cadena);
                case Double decimal ->
                    cs.setDouble(key, decimal);
                case Boolean booleano ->
                    cs.setBoolean(key, booleano);
                case java.util.Date fecha ->
                    cs.setDate(key, new java.sql.Date(fecha.getTime()));
                case byte[] archivo ->
                    cs.setBytes(key, archivo);
                default ->
                    cs.setObject(key, value); // catch-all
            }
        }
    }

    
    private void registrarParametrosSalida(CallableStatement cst, Map<Integer, Object> params) throws SQLException {
        for (Map.Entry<Integer, Object> entry : params.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();
            System.out.println("🟡 Registrando OUT: posición " + posicion + ", tipo " + sqlType); // 👈
            cst.registerOutParameter(posicion, sqlType);
        }
    }


    private void obtenerValoresSalida(CallableStatement cst, Map<Integer, Object> parametrosSalida) throws SQLException {
        for (Map.Entry<Integer, Object> entry : parametrosSalida.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();
            Object value = null;
            switch (sqlType) {
                case Types.INTEGER -> value = cst.getInt(posicion);
                case Types.VARCHAR -> value = cst.getString(posicion);
                case Types.DOUBLE -> value = cst.getDouble(posicion);
                case Types.BOOLEAN -> value = cst.getBoolean(posicion);
                case Types.DATE -> value = cst.getDate(posicion);
                case Types.BLOB -> value = cst.getBytes(posicion);
                // Agregar más tipos según sea necesario
            }
            parametrosSalida.put(posicion, value);
        }
    }
    
    //Para transacciones
    
    public void iniciarTransaccion() throws SQLException{
        con = getConnection();
        con.setAutoCommit(false);
    }
    
    public void confirmarTransaccion() throws SQLException{
        con.commit();
    }
    
    public void cancelarTransaccion(){
        try{
            con.rollback();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            cerrarConexion();
        }
    }
    
    public CallableStatement formarLlamadaProcedimientoTransaccion(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) throws SQLException{
        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
        int cantParametrosEntrada = 0;
        int cantParametrosSalida = 0;
        if(parametrosEntrada!=null) cantParametrosEntrada = parametrosEntrada.size();
        if(parametrosSalida!=null) cantParametrosSalida = parametrosSalida.size();
        int numParams =  cantParametrosEntrada + cantParametrosSalida;
        for (int i = 0; i < numParams; i++) {
            call.append("?");
            if (i < numParams - 1) {
                call.append(",");
            }
        }
        call.append(")}");
        return con.prepareCall(call.toString());
    }
    
    public int ejecutarProcedimientoTransaccion(String nombreProcedimiento, Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) throws SQLException{
        int resultado;
        
        CallableStatement cst = formarLlamadaProcedimientoTransaccion(nombreProcedimiento, parametrosEntrada, parametrosSalida);
        if (parametrosEntrada != null) {
            registrarParametrosEntrada(cst, parametrosEntrada);
        }
        if (parametrosSalida != null) {
            registrarParametrosSalida(cst, parametrosSalida);
        }

        resultado = cst.executeUpdate();

        if (parametrosSalida != null)
            obtenerValoresSalida(cst, parametrosSalida);

        return resultado;
    }
    
    
}
