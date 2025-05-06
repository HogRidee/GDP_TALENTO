package pucp.edu.pe.gdptalento.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static DBManager dbManager;  //Patrón Singleton
    private Properties datos;
    private String hostname;
    private String usuario;
    private String password;
    private String database;
    private String puerto;
    private String url;
    private Connection con;
    private String rutaArchivo = "db.properties";
    private ResultSet rs;
   
    // Luego cambiar public por private (cuando todos los DAOS funcionen con el singleton
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
        tipoBD = datos.getProperty("tipoBD");
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
}
