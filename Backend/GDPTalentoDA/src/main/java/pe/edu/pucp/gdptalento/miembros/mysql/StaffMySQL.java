package pe.edu.pucp.gdptalento.miembros.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.dao.StaffDAO;
import pe.edu.pucp.gdptalento.miembros.model.Area;
import pe.edu.pucp.gdptalento.miembros.model.EstadoMiembro;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author USER
 */
public class StaffMySQL implements StaffDAO {
    
    private Statement st;//no estoy usandolo pq estoy haciendo con preparedStatement
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    
    
    @Override
    public int insertarStaff(Staff staff) {
        int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "INSERT INTO MiembroPUCP(nombre, correo, codigoPUCP, facultad, especialidad, status, telefono) VALUES(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            //st = con.createStatement();
            pst.setString(1, staff.getNombre());
            pst.setString(2, staff.getCorreo());
            pst.setInt(3, staff.getCodigoPUCP());
            pst.setString(4, staff.getFacultad());
            pst.setString(5, staff.getEspecialidad());
            pst.setString(6, String.valueOf(staff.getStatus()));
            pst.setString(7, String.valueOf(staff.getTelefono()));
            resultado=pst.executeUpdate();
            sql="SELECT @@last_insert_id AS id";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            rs.next();
            staff.setId(rs.getInt("id"));
            sql="INSERT INTO Staff(id_staff, area, fecha_ingreso, estado, fecha_salida, desempenio) VALUES(?,?)";
            pst=con.prepareStatement(sql);
            pst.setInt(1, staff.getId());
            pst.setString(2, String.valueOf(staff.getArea()));
            Date fecha_date=java.sql.Date.valueOf(staff.getFechaIngreso());
            pst.setDate(3, new java.sql.Date(fecha_date.getTime()));
            pst.setString(4, String.valueOf(staff.getEstado()));
            fecha_date=java.sql.Date.valueOf(staff.getFechaSalida());
            pst.setDate(5, new java.sql.Date(fecha_date.getTime()));
            pst.setDouble(6, staff.getDesempenio());
            resultado=pst.executeUpdate();
            System.out.println("Se ingreso un Staff");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Staff staff) {//colocar datos de frente?
       int resultado=0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL MiembroPUCP
            String sql = "UPDATE MiembroPUCP SET correo = ? facultad = ? especialidad = ? status = ? telefono = ? WHERE id_miembro_pucp = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, staff.getId());
            pst.setString(2, staff.getCorreo());
            pst.setString(3, staff.getFacultad());
            pst.setString(4, staff.getEspecialidad());
            pst.setString(5, String.valueOf(staff.getStatus()));
            pst.setInt(6, staff.getTelefono());
            resultado = pst.executeUpdate();
            System.out.println("Se modifico un miembroPUCP");
            //Ejecuciones SQL Staff
            sql = "UPDATE Staff SET area = ? estado = ? fecha_salida = ? desempenio = ? WHERE id_staff = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, staff.getId());
            pst.setString(2, String.valueOf(staff.getArea()));
            pst.setString(3, String.valueOf(staff.getEstado()));
            String fecha_fin = String.valueOf(staff.getFechaSalida());
            Date fecha = java.sql.Date.valueOf(fecha_fin);
            pst.setDate(4, new java.sql.Date(fecha.getTime()));
            pst.setDouble(5, staff.getDesempenio());
            resultado=pst.executeUpdate();
            System.out.println("Se modifico un staff");
            
            //Ejecuciones SQL
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int id) {
        int resultado=0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            st = con.createStatement();
            String sql = "DELETE FROM Staff WHERE id_staff = " + id;
            resultado=st.executeUpdate(sql);
            System.out.println("Se elimino un staff");
            con = db.getConnection();
            //Ejecuciones SQL
            st = con.createStatement();
            sql = "DELETE FROM MiembroPUCP WHERE id_miembro_pucp = " + id;
            resultado=st.executeUpdate(sql);
            System.out.println("Se elimino un miembroPUCP");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Staff> listarStaff() {
        ArrayList<Staff> listadoStaff = new ArrayList<Staff>();
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            String sql = "SELECT m.id_miembro_pucp, m.codigoPUCP, m.nombre, m.correo, m.telefono, m.facultad, m.especialidad, m.status, s.area, s.estado, s.desempenio FROM MiembroPUCP m INNER JOIN Staff s ON m.id_miembro_pucp = s.id_staff";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String estadoString = rs.getString("status");
                EstadoPUCP estado = EstadoPUCP.valueOf(estadoString);
                String areaString = rs.getString("area");
                Area area = Area.valueOf(areaString);
                String miembroString = rs.getString("estado");
                EstadoMiembro miembro = EstadoMiembro.valueOf(miembroString);
                Staff staff = new Staff();
                staff.setId(rs.getInt("id_miembro_pucp"));
                staff.setNombre(rs.getString("nombre"));
                staff.setCorreo(rs.getString("correo"));
                staff.setCodigoPUCP(rs.getInt("codigoPUCP"));
                staff.setFacultad(rs.getString("facultad"));
                staff.setEspecialidad(rs.getString("especialidad"));
                staff.setTelefono(rs.getInt("telefono"));
                staff.setDesempenio(rs.getDouble("desempenio"));
                staff.setStatus(estado);
                staff.setArea(area);
                staff.setEstado(miembro);
                listadoStaff.add(staff);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return listadoStaff;
    }

    @Override
    public Staff obtenerPorId(int idStaff) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
