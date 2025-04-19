package pe.edu.pucp.gdptalento.miembros.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.dao.StaffDAO;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author USER
 */
public class StaffMySQL implements StaffDAO {
    private Statement st;
    private Connection con;
    private ResultSet rs;
    @Override
    public int insertar(Staff staff) {
        int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "INSERT INTO sala_especializada()";
            st = con.createStatement();
            resultado=st.executeUpdate(sql);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Staff staff) {
       int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "";
            st = con.createStatement();
            resultado=st.executeUpdate(sql);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(Staff staff) {
        int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "";
            st = con.createStatement();
            resultado=st.executeUpdate(sql);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Staff> listarTodas() {
        ArrayList<Staff> staffCompleto = new ArrayList<Staff>();
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "";
            st = con.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                Staff staff = new Staff();
                //staff.setCodigoPUCP(rs.getInt("id"));
                staffCompleto.add(staff);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{rs.close();} catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return staffCompleto;
    }

    @Override
    public Staff obtenerPorId(int idStaff) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
