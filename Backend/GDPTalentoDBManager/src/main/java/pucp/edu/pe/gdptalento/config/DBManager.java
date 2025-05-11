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

public class DBManager {
    private static DBManager dbManager;  //Patrón Singleton
    private Properties datos;
    private String hostname;
    private String usuario;
    private String password;
    private String database;
    private String puerto;
    private final String url;
    private Connection con;
    private final String rutaArchivo = "db.properties";
    private ResultSet rs;
   
    // Luego cambiar public por private (cuando todos los DAOS funcionen con el singleton)
    public DBManager() {
        leerArchivoProperties();
        asignarValoresDeProperties();
        this.url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + database;
    }
    
    public static synchronized DBManager getInstance(){
        if(dbManager == null)
            dbManager = new DBManager();
        return dbManager;
    }
    
    public Connection getConnection(){
        try{
            if(con == null || con.isClosed()){
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, usuario, password);
            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
    
    public void cerrarConexionLector() {
        cerrarLector();
        if (con != null) {
            try {
                con.close();  
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión:" + ex.getMessage());
            }
        }
    }
    
    private void leerArchivoProperties(){
        datos = new Properties();
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo);
            datos.load(inputStream);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    private void asignarValoresDeProperties(){
        hostname = datos.getProperty("hostname");
        usuario = datos.getProperty("usuario");
        password = datos.getProperty("password");
        puerto = datos.getProperty("puerto");
        database = datos.getProperty("database");
    }
    
    private void cerrarLector(){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrar el lector:" + ex.getMessage());
            }
        }
    }
    
    //MÉTODOS PARA LLAMAR PROCEDIMIENTOS ALMACENADOS
    public int ejecutarProcedimiento(String nombreProcedimiento, 
            Map<Integer, Object> parametrosEntrada, Map<Integer, Object> parametrosSalida) {
        int resultado = 0;
        try{
            CallableStatement cst = formarLlamadaProcedimiento(nombreProcedimiento, 
                    parametrosEntrada, parametrosSalida);
            registrarParametros(cst, parametrosEntrada, parametrosSalida);
            resultado = cst.executeUpdate();
            obtenerValoresSalida(cst, parametrosSalida);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            cerrarConexionLector();
        }
        return resultado;
    }
    
    public ResultSet ejecutarProcedimientoLectura(String nombreProcedimiento, Map<Integer,Object> parametrosEntrada){
        try{
            CallableStatement cs = formarLlamadaProcedimiento(nombreProcedimiento, parametrosEntrada, null);
            if (parametrosEntrada != null) {
                registrarParametrosEntrada(cs, parametrosEntrada);
            }
            rs = cs.executeQuery();
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
        return rs;
    }
    
    private CallableStatement formarLlamadaProcedimiento(String nombreProcedimiento, 
            Map<Integer, Object> parametrosEntrada, Map<Integer, 
                    Object> parametrosSalida) throws SQLException{
        con = getConnection();
        StringBuilder call = new StringBuilder("{call " + nombreProcedimiento + "(");
        int cantParametrosEntrada = devolverCantidadParametros(parametrosEntrada);
        int cantParametrosSalida = devolverCantidadParametros(parametrosSalida);
        int numParametros =  cantParametrosEntrada + cantParametrosSalida;
        construirCadenaTextoSQL(call, numParametros);
        call.append(")}");
        return con.prepareCall(call.toString());
    }
    
    private int devolverCantidadParametros(Map<Integer, Object> parametros){
        if (parametros != null){
            return parametros.size();
        }
        else{
            return 0;
        }
    }
    
    private void construirCadenaTextoSQL(StringBuilder call, int numParametros){
        for (int i = 0; i < numParametros; i++) {
            call.append("?");
            if (i < numParametros - 1) {
                call.append(",");
            }
        }
    }
    
    private void registrarParametros(CallableStatement cst, 
            Map<Integer, Object> parametrosEntrada, 
            Map<Integer, Object> parametrosSalida) throws SQLException{
        if(parametrosEntrada != null) {
            registrarParametrosEntrada(cst, parametrosEntrada);
        }
        if(parametrosSalida != null){
            registrarParametrosSalida(cst, parametrosSalida);
        }
    }
    
    private void registrarParametrosEntrada(CallableStatement cs, 
            Map<Integer, Object> parametros) throws SQLException {
        for (Map.Entry<Integer, Object> entry : parametros.entrySet()) {
            Integer key = entry.getKey();
            Object value = entry.getValue();
            registrarParametro(cs, key, value);
        }
    }
    
    private void registrarParametro(CallableStatement cs, Integer key, 
            Object value) throws SQLException{
        switch (value) {
            case Integer entero -> cs.setInt(key, entero);
            case String cadena -> cs.setString(key, cadena);
            case Double decimal -> cs.setDouble(key, decimal);
            case Boolean booleano -> cs.setBoolean(key, booleano);
            case java.util.Date fecha -> cs.setDate(key, new java.sql.Date(fecha.getTime()));
            case byte[] archivo -> cs.setBytes(key, archivo);
            default -> {
                // Agregar más tipos según sea necesario
            }
        }
    }
    
    private void registrarParametrosSalida(CallableStatement cst, 
            Map<Integer, Object> params) throws SQLException {
        for (Map.Entry<Integer, Object> entry : params.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();
            cst.registerOutParameter(posicion, sqlType);
        }
    }
    
    private void obtenerValoresSalida(CallableStatement cst, 
            Map<Integer, Object> parametrosSalida) throws SQLException {
        if (parametrosSalida == null) return;
        for (Map.Entry<Integer, Object> entry : parametrosSalida.entrySet()) {
            Integer posicion = entry.getKey();
            int sqlType = (int) entry.getValue();
            Object value = obtenerValorSalida(cst, posicion, sqlType);
            parametrosSalida.put(posicion, value);
        }
    }
    
    private Object obtenerValorSalida(CallableStatement cst, int posicion, 
            int sqlType) throws SQLException {
        return switch (sqlType) {
            case Types.INTEGER -> cst.getInt(posicion);
            case Types.VARCHAR -> cst.getString(posicion);
            case Types.DOUBLE -> cst.getDouble(posicion);
            case Types.BOOLEAN -> cst.getBoolean(posicion);
            case Types.DATE -> cst.getDate(posicion);
            case Types.BLOB -> cst.getBytes(posicion);
            // Agregar más tipos según sea necesario
            default -> throw new SQLException("Tipo SQL no soportado: " + sqlType);
        };
    }

}
