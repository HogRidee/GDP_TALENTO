package pe.edu.pucp.gdptalento.talento.mysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import pe.edu.pucp.gdptalento.talento.dao.EvaluacionDesempeñoDAO;
import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pucp.edu.pe.gdptalento.config.DBManager;

public class EvaluacionDesempeñoMySQL implements EvaluacionDesempeñoDAO{
    
    private ResultSet rs;

    @Override
    public int insertarEvaluacion(EvaluacionDesempeño evaluacion){

        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, evaluacion.getEvaluador());
        parametrosEntrada.put(3, evaluacion.getMiembroEvaluado());
        parametrosEntrada.put(4, evaluacion.getPuntaje());
        parametrosEntrada.put(5, evaluacion.getComentarios());
        parametrosEntrada.put(6, new Date(evaluacion.getFecha().getTime()));

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_EVALUACION", 
                parametrosEntrada, parametrosSalida);
        int idEvaluacion = (int) parametrosSalida.get(1);
        evaluacion.setId(idEvaluacion);
        System.out.println("Evaluacion insertada con ID: " + idEvaluacion);
        return idEvaluacion;
    }

    @Override
    public int modificarEvaluacion(EvaluacionDesempeño evaluacion){

        Map<Integer, Object> parametrosEntrada = new HashMap<>();

        parametrosEntrada.put(1, evaluacion.getId());
        parametrosEntrada.put(2, evaluacion.getEvaluador().getId());
        parametrosEntrada.put(3, evaluacion.getMiembroEvaluado().getId());
        parametrosEntrada.put(4, evaluacion.getPuntaje());
        parametrosEntrada.put(5, evaluacion.getComentarios());
        parametrosEntrada.put(6, new Date(evaluacion.getFecha().getTime()));

        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_EVALUACION", 
                parametrosEntrada, null);
        System.out.println("Se modificó una evaluacion con ID: " + evaluacion.getId());
        return evaluacion.getId();
    }

    @Override
    public int eliminarEvaluacion(int id_evaluacion_desempeno){
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id_evaluacion_desempeno);
        DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_EVALUACION", 
                parametrosEntrada, null);
        System.out.println("Se eliminó la evaluación con ID: " + id_evaluacion_desempeno);
        return 1;
    }

    /*Ver que onda con esta cosa*/
    @Override
    public ArrayList<EvaluacionDesempeño> listarEvaluaciones() {
        ArrayList<EvaluacionDesempeño> listadoEvaluaciones = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_EVALUACIONES", null);
        try {
            while (rs.next()) {
                EvaluacionDesempeño evaluacion = new EvaluacionDesempeño();

                evaluacion.setId(rs.getInt("ed.id_evaluacion_desempeno"));
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("evaluador"));
                evaluacion.setEvaluador(usuario);
                Staff miembroEvaluado = new Staff();
                miembroEvaluado.setNombre(rs.getString("miembro_Staff"));
                evaluacion.setMiembroEvaluado(miembroEvaluado);
                evaluacion.setPuntaje(rs.getInt("ed.puntaje"));
                evaluacion.setComentarios(rs.getString("ed.comentarios"));
                evaluacion.setFecha(rs.getDate("fecha_evaluacion"));
                listadoEvaluaciones.add(evaluacion);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al listar evaluaciones: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return listadoEvaluaciones;
    }

    @Override
    public EvaluacionDesempeño obtenerPorId(int idEvaluacion){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
    @Override
    public int insertarEvaluacion(EvaluacionDesempeño evaluacion) {
        int resultado = 0;
        try {
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL de MiembroPUCP
            String sql = "INSERT INTO EvaluacionDesempeno(id_evaluador, id_miembro_staff, puntaje, comentarios, fecha) VALUES(?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            System.out.println(evaluacion.getEvaluador().getId());
            System.out.println(evaluacion.getMiembroEvaluado().getId());
            // Asignación de parámetros
            pst.setInt(1, evaluacion.getEvaluador().getId());     // id_evaluador
            pst.setInt(2, evaluacion.getMiembroEvaluado().getId()); // id_miembro
            pst.setInt(3, evaluacion.getPuntaje());
            pst.setString(4, evaluacion.getComentarios());

            Date fecha_date=java.sql.Date.valueOf(evaluacion.getFecha());
            pst.setDate(5, new java.sql.Date(fecha_date.getTime()));

            resultado = pst.executeUpdate();
            sql="SELECT @@last_insert_id AS id";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            rs.next();
            evaluacion.setId(rs.getInt("id"));
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
            DBManager db = new DBManager();
            con = db.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Staff staff = evaluacion.getMiembroEvaluado();
            Usuario usuario = evaluacion.getEvaluador();
//            System.out.println(usuario.getId());
//            System.out.println(staff.getId());
            //Ejecuciones SQL
            String sql = "UPDATE EvaluacionDesempeno SET id_evaluador = ?, id_miembro_staff = ?, puntaje = ?, comentarios = ?, fecha = ? WHERE id_evaluacion_desempeno = ?";
            pst = con.prepareStatement(sql);
            //st = con.createStatement();
            pst.setInt(1,usuario.getId());
            pst.setInt(2,staff.getId());
            pst.setInt(3,evaluacion.getPuntaje());
            pst.setString(4,evaluacion.getComentarios());
            Date fecha_date=java.sql.Date.valueOf(evaluacion.getFecha());
            pst.setDate(5, new java.sql.Date(fecha_date.getTime()));
            pst.setInt(6,evaluacion.getId());
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
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "DELETE FROM EvaluacionDesempeno WHERE id_evaluacion_desempeno = "+id;
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
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "SELECT e.id_evaluacion_desempeno, e.id_evaluador, e.id_miembro_staff, e.puntaje, e.comentarios, e.fecha FROM EvaluacionDesempeno e";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                EvaluacionDesempeño evaluacion = new EvaluacionDesempeño();
                // Seteamos los campos simples
                evaluacion.setId(rs.getInt("id_evaluacion_desempeno"));
                evaluacion.setPuntaje(rs.getInt("puntaje"));
                evaluacion.setComentarios(rs.getString("comentarios"));
                evaluacion.setFecha(rs.getDate("fecha").toLocalDate());
                // Creamos objetos con solo su ID
                Usuario evaluador = new Usuario();
                evaluador.setId(rs.getInt("id_evaluador"));
                evaluacion.setEvaluador(evaluador);

                Staff miembro = new Staff();
                miembro.setId(rs.getInt("id_miembro_staff"));
                evaluacion.setMiembroEvaluado(miembro);

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
    }*/

}