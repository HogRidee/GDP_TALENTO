
package pucp.edu.pe.gdptalento.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    private Connection con;
    private String rutaArchivo = "db.properties";
   
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
        //Formamos la URL de conexión        
        this.url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + database;
    }
    
    public static DBManager getInstance(){
        if(dbManager == null)
            dbManager = new DBManager();
        return dbManager;
    }
    
    //Nos permite obtener una conexión con la BD
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, password);
            System.out.println("Se ha establecido la conexion con la BD...");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
