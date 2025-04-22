package pe.edu.pucp.gdptalento.talento.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import pe.edu.pucp.gdptalento.talento.dao.TareaDAO;
import pe.edu.pucp.gdptalento.talento.model.Tarea;
import pe.edu.pucp.gdptalento.talento.model.EstadoTarea;
import pe.edu.pucp.gdptalento.miembros.model.Usuario;
import pucp.edu.pe.gdptalento.config.DBManager;

public class TareaMySQL implements TareaDAO{
    private Statement st;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;

    @Override
    public int insertarTarea(Tarea tarea){
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Usuario usuario = tarea.getCreador();
            //Ejecuciones SQL
            String sql = "INSERT INTO Tarea(fecha_creacion, id_creador, fecha_limite, estado) VALUES(?,?,?,?)"
            pst = con.prepareStatement(sql);
            pst.setString(1,sdf.format(tarea.getFechaCreacion()));
            pst.setInt(2,usuario.getId());
            pst.setString(3,sdf.format(tarea.getFechaLimite()));
            pst.setString(4,String.valueof(tarea.getEstado()));
            resultado=pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificarTarea(Tarea tarea) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Usuario usuario = evaluacion.getEvaluador();
            //Ejecuciones SQL
            String sql = "UPDATE Tarea SET fecha_creacion = ? id_creador = ? fecha_limite = ? estado = ? WHERE id_tarea = ?";
            pst = con.createStatement();
            //st = con.createStatement();
            pst.setString(1,sdf.format(tarea.getFechaCreacion()));
            pst.setInt(2,usuario.getId());
            pst.setString(3,sdf.format(tarea.getFechaLimite()));
            pst.setString(4,String.valueof(tarea.getEstado()));
            pst.setId(5,tarea.getId())
            resultado=pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminarTarea(int id) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //Ejecuciones SQL
            String sql = "DELETE FROM Tarea WHERE id_tarea = "+id;
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
    public ArrayList<Tarea> listarTareas() {
        ArrayList<Tarea> tareasCompletas = new ArrayList<Tarea>();
        try{
            con = DBManager.getInstance().getConnection();
            //Ejecuciones SQL
            String sql = "SELECT t.id_tarea, t.fecha_creacion, t.id_creador, t.fecha_limite, t.estado FROM Tarea t";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Tarea tarea = new tarea();
                
                tareasCompletas.add(tarea);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{rs.close();} catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return tareasCompletas;
    }

    @Override
    public EvaluacionDesempeño obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}