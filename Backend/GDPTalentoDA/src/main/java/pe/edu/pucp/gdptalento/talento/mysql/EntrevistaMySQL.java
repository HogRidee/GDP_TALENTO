package pe.edu.pucp.gdptalento.talento.mysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
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
        parametrosEntrada.put(2, entrevista.getPostulante().getId());
        parametrosEntrada.put(3, new Date(entrevista.getFecha().getTime()));
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
        parametrosEntrada.put(3, new Date(entrevista.getFecha().getTime()));
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
        System.out.println("Se eliminó la entrevista con ID: " + id_entrevista);
        return 1;
    }

    @Override
    public ArrayList<Entrevista> listarEntrevistas() {
        ArrayList<Entrevista> listadoEntrevistas = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ENTREVISTAS", null);
        if (rs == null) {
            System.out.println("ERROR: ResultSet es null. Revisa el procedimiento almacenado LISTAR_ENTREVISTAS.");
            return listadoEntrevistas;
        }
        try {
            while (rs.next()) {
                Entrevista entrevista = new Entrevista();
                entrevista.setId(rs.getInt("id_entrevista"));
                entrevista.setFecha(rs.getDate("fecha_entrevista"));
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

    @Override
    public java.sql.Date obtenerFechaProximaEntrevista() {
        java.sql.Date fecha = null;
        // no params de entrada ni salida
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("OBTENER_PROXIMA_ENTREVISTA", null);
        try {
          if(rs.next()) {
            fecha = rs.getDate("proxima");
          }
        } catch(SQLException ex) {
        } finally {
          DBManager.getInstance().cerrarConexion();
        }
        return fecha;
      }
    }
