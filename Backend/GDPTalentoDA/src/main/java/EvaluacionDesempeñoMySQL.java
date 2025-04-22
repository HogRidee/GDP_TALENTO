package pe.edu.pucp.gdptalento.talento.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import pe.edu.pucp.gdptalento.talento.dao.EvaluacionDesempeñoDAO;
import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;
import pe.edu.pucp.gdptalento.talento.model.Staff;
import pe.edu.pucp.gdptalento.miembros.model.Usuario;
import pucp.edu.pe.gdptalento.config.DBManager;

public class EvaluacionDesempeñoMySQL implements EvaluacionDesempeñoDAO{
    private Statement st;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;

    @Override
    public int insertarEvaluacion(EvaluacionDesempeño evaluacion){
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Staff staff = evaluacion.getMiembroEvaluado();
            Usuario usuario = evaluacion.getEvaluador();
            //Ejecuciones SQL
            String sql = "INSERT INTO EvaluacionDesempeno(id_evaluador, id_miembro, puntaje, comentarios, fecha) VALUES(?,?,?,?,?)"
            pst = con.prepareStatement(sql);
            pst.setInt(1,staff.getId());
            pst.setInt(2,usuario.getId());
            pst.setInt(3,evaluacion.getPuntaje());
            pst.setString(4,evaluacion.getComentarios());
            pst.setString(5,sdf.format(evaluacion.getFecha()));
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
    public int modificarEvaluacion(EvaluacionDesempeño evaluacion) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Staff staff = evaluacion.getMiembroEvaluado();
            Usuario usuario = evaluacion.getEvaluador();
            //Ejecuciones SQL
            String sql = "UPDATE EvaluacionDesempeno SET id_evaluador = ? id_miembro = ? puntaje = ? comentarios = ? fecha = ? WHERE id_evaluacion_desempeno = ?";
            pst = con.createStatement();
            //st = con.createStatement();
            pst.setInt(1,staff.getId());
            pst.setInt(2,usuario.getId());
            pst.setInt(3,evaluacion.getPuntaje());
            pst.setString(4,evaluacion.getComentarios());
            pst.setString(5,sdf.format(evaluacion.getFecha()));
            pst.setId(6,evaluacion.getId())
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
    public int eliminarEvaluacion(int id) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //Ejecuciones SQL
            String sql = "DELETE FROM EvalacionDesempeno WHERE id_evaluacion_desempeno = "+id;
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
    public ArrayList<EvaluacionDesempeño> listarEvaluaciones() {
        ArrayList<EvaluacionDesempeño> evaluacionesCompletas = new ArrayList<EvaluacionDesempeño>();
        try{
            con = DBManager.getInstance().getConnection();
            //Ejecuciones SQL
            String sql = "SELECT e.id_evaluacion_desempeno, e.id_evaluador, e.id_miembro, e.puntaje, e.comentarios, e.fecha FROM EvaluacionDesempeno e";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                EvalacionDesempeño evaluacion = new EvaluacionDesempeño();
                
                evaluacionesCompletas.add(evaluacion);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{rs.close();} catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return evaluacionesCompletas;
    }

    @Override
    public EvaluacionDesempeño obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}