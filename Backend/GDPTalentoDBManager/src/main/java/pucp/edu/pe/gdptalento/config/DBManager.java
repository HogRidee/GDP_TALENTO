
package pucp.edu.pe.gdptalento.config;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author USER
 */
public class DBManager {
   private String hostname = "database-gamedevspucp-r.cl0s4cwya817.us-east-1.rds.amazonaws.com";
   private String usuario = "admin";
   private String password = "pv30g2sp1cDmaeu5404";
   private String database = "gamedevspucpRecursosHumanos";
   private String puerto = "3306";
   private String url;
   private Connection con;
   
    public DBManager() {
        this.url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + database;
        
    }
   
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,usuario,password);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
