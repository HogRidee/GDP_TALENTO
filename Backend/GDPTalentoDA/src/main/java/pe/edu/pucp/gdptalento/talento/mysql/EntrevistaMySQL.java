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
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.talento.dao.EntrevistaDAO;
import pe.edu.pucp.gdptalento.talento.model.Entrevista;
import pe.edu.pucp.gdptalento.talento.model.EstadoEntrevista;
import pe.edu.pucp.gdptalento.miembros.model.Postulante;
import pucp.edu.pe.gdptalento.config.DBManager;

public class EntrevistaMySQL implements EntrevistaDAO{
    
    private ResultSet rs;

    @Override
    public int insertarEntrevista(Entrevista entrevista){

        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, entrevista.getPostulante());
        Date fecha_date = java.sql.Date.valueOf(entrevista.getFecha());
        parametrosEntrada.put(3, new java.sql.Date(fecha_date.getTime()));
        parametrosEntrada.put(4, entrevista.getFeedback());
        parametrosEntrada.put(5, String.valueOf(entrevista.getEstado()));
        parametrosEntrada.put(6, entrevista.getPuntuacionFinal());

        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ENTREVISTA", 
                parametrosEntrada, parametrosSalida);
        int idEntrevista = (int) parametrosSalida.get(1);
        entrevista.setId(idEntrevista);
        System.out.println("Entrevista insertada con ID: " + idEntrevista);
        return idEntrevista;
    }

    @Override
    public int modificarEntrevista(Entrevista entrevista){

        Map<Integer, Object> parametrosEntrada = new HashMap<>();

        parametrosEntrada.put(1, entrevista.getId());
        parametrosEntrada.put(2, entrevista.getPostulante());
        Date fecha_date = java.sql.Date.valueOf(entrevista.getFecha());
        parametrosEntrada.put(3, new java.sql.Date(fecha_date.getTime()));
        parametrosEntrada.put(4, entrevista.getFeedback());
        parametrosEntrada.put(5, String.valueOf(entrevista.getEstado()));
        parametrosEntrada.put(6, entrevista.getPuntuacionFinal());

        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_ENTREVISTA", 
                parametrosEntrada, null);
        System.out.println("Se modificó una entrevista con ID: " + entrevista.getId());
        return entrevista.getId();
    }

    @Override
    public int eliminarEntrevista(int id_entrevista){
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id_entrevista);
        DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_ENTREVISTA", 
                parametrosEntrada, null);
        System.out.println("Se eliminó el rol con ID: " + id_entrevista);
        return 1;
    }

    @Override
    public ArrayList<Entrevista> listarEntrevistas() {
        ArrayList<Entrevista> listadoEntrevistas = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ENTREVISTAS", null);
        try {
            while (rs.next()) {
                Entrevista entrevista = new Entrevista();
                entrevista.setId(rs.getInt("id_entrevista"));
                java.sql.Date sqlDate = rs.getDate("fecha_entrevista");
                if (sqlDate != null) {
                    entrevista.setFecha(sqlDate.toLocalDate());
                }
                EstadoEntrevista estado = EstadoEntrevista.valueOf(rs.getString("estado"));
                entrevista.setEstado(estado);
                Postulante postulante = new Postulante();
                postulante.setId(rs.getInt("id_postulante"));
                entrevista.setPostulante(postulante);
                entrevista.setPuntuacionFinal(rs.getDouble("puntuacion_final"));
                entrevista.setFeedback(rs.getString("feedback"));

                listadoEntrevistas.add(entrevista);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al listar entrevistas: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return listadoEntrevistas;
    }


    @Override
    public Entrevista obtenerPorId(int idEntrevista){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*@Override
    public int insertarEntrevista(Entrevista entrevista){
        int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();

            String sql = "INSERT INTO Entrevista(fecha, id_postulante, feedback, estado, puntuacion_final) " +
                         "VALUES(?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setDate(1, java.sql.Date.valueOf(entrevista.getFecha()));
            pst.setInt(2, entrevista.getPostulante().getId());
            pst.setString(3, entrevista.getFeedback());
            pst.setString(4, entrevista.getEstado().name());
            pst.setDouble(5, entrevista.getPuntuacionFinal());

            resultado = pst.executeUpdate();

            rs = pst.getGeneratedKeys();
            int idEntrevista = 0;
            if (rs.next()) {
                idEntrevista = rs.getInt(1); // ID de la entrevista recién insertada
            }

            // Insertar en la tabla Entrevista_Entrevistador
            sql = "INSERT INTO Entrevista_Entrevistador(id_entrevista, id_entrevistador, puntaje_entrevistador) VALUES(?, ?, ?)";
            pst = con.prepareStatement(sql);

            List<Usuario> entrevistadores = entrevista.getEntrevistadores();
            List<Integer> puntuaciones = entrevista.getPuntuaciones();

            for (int i = 0; i < entrevistadores.size(); i++) {
                pst.setInt(1, idEntrevista);
                pst.setInt(2, entrevistadores.get(i).getId());
                pst.setDouble(3, puntuaciones.get(i));
                pst.addBatch();
            }
            pst.executeBatch();

        } catch(Exception ex){
            System.out.println("Error al insertar entrevista: " + ex.getMessage());
            ex.printStackTrace();
        } finally{
            try { if (con != null) con.close(); } catch(Exception ex){ System.out.println(ex.getMessage()); }
        }
        return resultado;
    }


    @Override
    public int modificarEntrevista(Entrevista entrevista) {
        int resultado = 0;
        try {
            DBManager db = new DBManager();
            con = db.getConnection();

            //Modificar Entrevista
            String sql = "UPDATE Entrevista SET fecha = ?, id_postulante = ?, feedback = ?, estado = ?, puntuacion_final = ? WHERE id_entrevista = ?";
            pst = con.prepareStatement(sql);
            pst.setDate(1, java.sql.Date.valueOf(entrevista.getFecha()));
            pst.setInt(2, entrevista.getPostulante().getId());
            pst.setString(3, entrevista.getFeedback());
            pst.setString(4, entrevista.getEstado().name());
            pst.setDouble(5, entrevista.getPuntuacionFinal());
            pst.setInt(6, entrevista.getId());
            resultado = pst.executeUpdate();

            //Eliminar registros antiguos de Entrevista_Entrevistador
            sql = "DELETE FROM Entrevista_Entrevistador WHERE id_entrevista = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, entrevista.getId());
            pst.executeUpdate();

            //Insertar nuevos entrevistadores y sus puntajes
            sql = "INSERT INTO Entrevista_Entrevistador(id_entrevista, id_entrevistador, puntaje_entrevistador) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);

            ArrayList<Usuario> entrevistadores = entrevista.getEntrevistadores();
            List<Integer> puntuaciones = entrevista.getPuntuaciones();

            for (int i = 0; i < entrevistadores.size(); i++) {
                pst.setInt(1, entrevista.getId());
                pst.setInt(2, entrevistadores.get(i).getId());
                pst.setDouble(3, puntuaciones.get(i));
                pst.executeUpdate();
            }

        } catch (Exception ex) {
            System.out.println("Error al modificar entrevista: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try { if (con != null) con.close(); } catch (Exception ex) { System.out.println(ex.getMessage()); }
        }
        return resultado;
    }



    @Override
    public int eliminarEntrevista(int id) {
        int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "DELETE FROM Entrevista WHERE id_entrevista = "+id;
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
    public ArrayList<Entrevista> listarEntrevistas() {
        ArrayList<Entrevista> entrevistasCompletas = new ArrayList<Entrevista>();
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "SELECT e.id_entrevista, e.fecha, e.id_postulante, e.feedback, e.estado, e.puntuacion_final FROM Entrevista e";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                EstadoEntrevista estado = EstadoEntrevista.valueOf(rs.getString("estado"));
                Entrevista entrevista = new Entrevista();
                entrevista.setId(rs.getInt("id_entrevista"));
                entrevista.setFecha(rs.getDate("fecha").toLocalDate());
                //Se envía por id por el momento
                Postulante postulante = new Postulante();
                postulante.setId(rs.getInt("id_postulante"));
                entrevista.setPostulante(postulante);
                //Termina el postulante
                entrevista.setEstado(estado);
                entrevista.setFeedback(rs.getString("feedback"));
                entrevista.setPuntuacionFinal(rs.getDouble("puntuacion_final"));
                entrevistasCompletas.add(entrevista);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{rs.close();} catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return entrevistasCompletas;
    }

    @Override
    public Entrevista obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/

}
