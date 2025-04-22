package pe.edu.pucp.gdptalento.talento.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import pe.edu.pucp.gdptalento.talento.dao.TareaDAO;
import pe.edu.pucp.gdptalento.talento.model.Tarea;
import pe.edu.pucp.gdptalento.talento.model.EstadoTarea;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pucp.edu.pe.gdptalento.config.DBManager;

public class TareaMySQL implements TareaDAO{
    private Statement st;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;

    @Override
    public int insertarTarea(Tarea tarea) {
        int resultado = 0;
        try {
            DBManager db = new DBManager();
            con = db.getConnection();

            // Paso 1: Insertar en Tarea
            String sql = "INSERT INTO Tarea(fecha_creacion, id_creador, fecha_limite, estado) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setTimestamp(1, Timestamp.valueOf(tarea.getFechaCreacion()));
            pst.setInt(2, tarea.getCreador().getId());
            pst.setTimestamp(3, Timestamp.valueOf(tarea.getFechaLimite()));
            pst.setString(4, tarea.getEstado().name());

            resultado = pst.executeUpdate();

            // Obtener el id generado
            int idTareaGenerada = 0;
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                idTareaGenerada = rs.getInt(1);
            }

            //Insertar encargados en Tarea_Encargado
            if (tarea.getEncargados() != null) {
                for (Usuario encargado : tarea.getEncargados()) {
                    String sqlEncargado = "INSERT INTO Tarea_Encargado(id_tarea, id_usuario) VALUES (?, ?)";
                    PreparedStatement pstEncargado = con.prepareStatement(sqlEncargado);
                    pstEncargado.setInt(1, idTareaGenerada);
                    pstEncargado.setInt(2, encargado.getId());
                    pstEncargado.executeUpdate();
                    pstEncargado.close();
                }
            }

        } catch (Exception ex) {
            System.out.println("Error al insertar tarea: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) { System.out.println(ex.getMessage()); }
            try { if (pst != null) pst.close(); } catch (Exception ex) { System.out.println(ex.getMessage()); }
            try { if (con != null) con.close(); } catch (Exception ex) { System.out.println(ex.getMessage()); }
        }
        return resultado;
    }


    @Override
    public int modificarTarea(Tarea tarea) {
        int resultado = 0;
        try {
            DBManager db = new DBManager();
            con = db.getConnection();

            //Actualizar la tarea
            String sql = "UPDATE Tarea SET fecha_creacion = ?, id_creador = ?, fecha_limite = ?, estado = ? WHERE id_tarea = ?";
            pst = con.prepareStatement(sql);
            pst.setTimestamp(1, Timestamp.valueOf(tarea.getFechaCreacion()));
            pst.setInt(2, tarea.getCreador().getId());
            pst.setTimestamp(3, Timestamp.valueOf(tarea.getFechaLimite()));
            pst.setString(4, tarea.getEstado().name());
            pst.setInt(5, tarea.getId());
            resultado = pst.executeUpdate();
            pst.close();

            //Eliminar encargados anteriores
            sql = "DELETE FROM Tarea_Encargado WHERE id_tarea = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, tarea.getId());
            pst.executeUpdate();
            pst.close();

            //Insertar nuevos encargados
            if (tarea.getEncargados() != null) {
                sql = "INSERT INTO Tarea_Encargado(id_tarea, id_usuario) VALUES (?, ?)";
                pst = con.prepareStatement(sql);
                for (Usuario encargado : tarea.getEncargados()) {
                    pst.setInt(1, tarea.getId());
                    pst.setInt(2, encargado.getId());
                    pst.addBatch();
                }
                pst.executeBatch();
                pst.close();
            }

        } catch (Exception ex) {
            System.out.println("Error al modificar tarea: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try { if (con != null) con.close(); } catch (Exception ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return resultado;
    }


    @Override
    public int eliminarTarea(int id) {
        int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
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
        ArrayList<Tarea> tareasCompletas = new ArrayList<>();
        try {
            DBManager db = new DBManager();
            con = db.getConnection();

            // Paso 1: Obtener todas las tareas
            String sql = "SELECT t.id_tarea, t.fecha_creacion, t.id_creador, t.fecha_limite, t.estado FROM Tarea t";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Tarea tarea = new Tarea();
                int idTarea = rs.getInt("id_tarea");

                tarea.setId(idTarea);
                tarea.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
                tarea.setFechaLimite(rs.getTimestamp("fecha_limite").toLocalDateTime());

                // Obtener el creador
                int idCreador = rs.getInt("id_creador");
                Usuario creador = new Usuario(); //deberia ser Obtener por ID
                creador.setId(rs.getInt("id_creador"));
                tarea.setCreador(creador);

                // Estado
                EstadoTarea estado = EstadoTarea.valueOf(rs.getString("estado"));
                tarea.setEstado(estado);

                // Paso 2: Obtener encargados
                ArrayList<Usuario> encargados = new ArrayList<>();
                String sqlEncargados = "SELECT id_usuario FROM Tarea_Encargado WHERE id_tarea = ?";
                PreparedStatement pst2 = con.prepareStatement(sqlEncargados);
                pst2.setInt(1, idTarea);
                ResultSet rs2 = pst2.executeQuery();

                while (rs2.next()) {
                    int idEncargado = rs2.getInt("id_usuario");
                    Usuario encargado = new Usuario(); //deberia ser Obtener por ID
                    encargado.setId(rs2.getInt("id_usuario"));
                    encargados.add(encargado);
                }

                rs2.close();
                pst2.close();

                tarea.setEncargados(encargados);
                tareasCompletas.add(tarea);
            }

        } catch (Exception ex) {
            System.out.println("Error al listar tareas: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) { System.out.println(ex.getMessage()); }
            try { if (pst != null) pst.close(); } catch (Exception ex) { System.out.println(ex.getMessage()); }
            try { if (con != null) con.close(); } catch (Exception ex) { System.out.println(ex.getMessage()); }
        }
        return tareasCompletas;
    }


    @Override
    public Tarea obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}