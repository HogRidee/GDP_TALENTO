package pe.edu.pucp.gdptalento.talento.business;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.talento.dao.TareaDAO;
import pe.edu.pucp.gdptalento.talento.model.Tarea;
import pe.edu.pucp.gdptalento.talento.mysql.TareaMySQL;

public class TareaBO {
    private final TareaDAO daoTarea;

    public TareaBO() {
        daoTarea = new TareaMySQL();
    }

    public int insertar(Tarea tarea) {
        return daoTarea.insertarTarea(tarea);
    }

    public int modificar(Tarea tarea) {
        return daoTarea.modificarTarea(tarea);
    }

    public int eliminar(int idTarea) {
        return daoTarea.eliminarTarea(idTarea);
    }

    public ArrayList<Tarea> listarTodas() {
        return daoTarea.listarTareas();
    }
}
