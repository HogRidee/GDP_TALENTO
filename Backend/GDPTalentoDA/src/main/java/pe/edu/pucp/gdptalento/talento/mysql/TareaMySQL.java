package pe.edu.pucp.gdptalento.talento.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        
        parametrosEntrada.put(2, Timestamp.valueOf(tarea.getFechaCreacion()));
        parametrosEntrada.put(3, tarea.getCreador().getId());
        parametrosEntrada.put(4, Timestamp.valueOf(tarea.getFechaLimite()));
        parametrosEntrada.put(5, tarea.getEstado().name());
        // Llamada al procedimiento
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_TAREA", parametrosEntrada, parametrosSalida);

        // Asignar el ID generado
        tarea.setId((int) parametrosSalida.get(1));
        //Insertar encargados en Tarea_Encargado
        if (tarea.getEncargados() != null) {
            for (Usuario encargado : tarea.getEncargados()) {
                Map<Integer, Object> parametrosEntrada_encargados = new HashMap<>();
                parametrosEntrada_encargados.put(1, tarea.getId());
                parametrosEntrada_encargados.put(2, encargado.getId());
                DBManager.getInstance().ejecutarProcedimiento("INSERTAR_TAREA_ENCARGADO", parametrosEntrada_encargados, null);
                System.out.println("Se ha registrado el los encargados de tarea correctamente");
            }
        }
        System.out.println("Se ha registrado la tarea correctamente");
        return tarea.getId();
    }


    @Override
    public int modificarTarea(Tarea tarea) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, tarea.getId());
        parametrosEntrada.put(2, Timestamp.valueOf(tarea.getFechaCreacion()));
        parametrosEntrada.put(2, tarea.getCreador().getId());
        parametrosEntrada.put(4, Timestamp.valueOf(tarea.getFechaLimite()));
        parametrosEntrada.put(5, tarea.getEstado().name());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("INSERTAR_TAREA_ENCARGADO", parametrosEntrada, null);
        System.out.println("Se ha modificado la tarea correctamente - paso 1");
        
        Map<Integer, Object> parametrosEntrada_tarea_encargado_eliminar = new HashMap<>();
        parametrosEntrada_tarea_encargado_eliminar.put(1,tarea.getId());
        resultado*=DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_TAREA_ENCARGADO_FOR_UPDATE", parametrosEntrada_tarea_encargado_eliminar, null);
        System.out.println("Se ha modificado la tarea correctamente - paso 2");
        if (tarea.getEncargados() != null) {
            for (Usuario encargado : tarea.getEncargados()) {
                Map<Integer, Object> parametrosEntrada_encargados = new HashMap<>();
                parametrosEntrada_encargados.put(1, tarea.getId());
                parametrosEntrada_encargados.put(2, encargado.getId());
                resultado*=DBManager.getInstance().ejecutarProcedimiento("INSERTAR_TAREA_ENCARGADO", parametrosEntrada_encargados, null);
                System.out.println("Se ha registrado el los encargados de tarea correctamente en modificar");
            }
        }
        System.out.println("Se ha registrado la tarea correctamente TOTAL");
        return resultado;
    }


    @Override
    public int eliminarTarea(int id) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_TAREA", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del staff con id: "+ id);
        return resultado;
    }

    @Override
    public ArrayList<Tarea> listarTareas() {
        ArrayList<Tarea> tareasCompletas = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_TAREA", null);
        System.out.println("Lectura de Tareas...");
        try {
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
                Map<Integer, Object> parametrosEntrada = new HashMap<>();
                parametrosEntrada.put(1, tarea.getId());
                ResultSet rs2 = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_TAREA_ENCARGADO", parametrosEntrada);
                while (rs2.next()) {
                    int idEncargado = rs2.getInt("id_usuario");
                    Usuario encargado = new Usuario(); //deberia ser Obtener por ID
                    encargado.setId(rs2.getInt("id_usuario"));
                    encargados.add(encargado);
                }
                rs2.close();
                tarea.setEncargados(encargados);
                tareasCompletas.add(tarea);
            }

        } catch (Exception ex) {
            System.out.println("Error al listar tareas: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return tareasCompletas;
    }


    @Override
    public Tarea obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}