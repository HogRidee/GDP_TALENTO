package pe.edu.pucp.gdptalento.talento.dao;
import pe.edu.pucp.gdptalento.talento.model.Tarea;
import java.util.ArrayList;

public interface TareaDAO {
    int insertarTarea(Tarea tarea);
    int modificarTarea(Tarea tarea);
    int eliminarTarea(int id);
    ArrayList<Tarea> listarTareas();
    Tarea obtenerPorId(int id);
}