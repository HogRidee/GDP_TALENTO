package pe.edu.pucp.gdptalento.talento.business;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.talento.dao.EvaluacionDesempeñoDAO;
import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;
import pe.edu.pucp.gdptalento.talento.mysql.EvaluacionDesempeñoMySQL;

public class EvaluacionDesempeñoBO {
    private final EvaluacionDesempeñoDAO daoEvaluacion;

    public EvaluacionDesempeñoBO() {
        daoEvaluacion = new EvaluacionDesempeñoMySQL();
    }

    public int insertar(EvaluacionDesempeño evaluacion) {
        return daoEvaluacion.insertarEvaluacion(evaluacion);
    }

    public int modificar(EvaluacionDesempeño evaluacion) {
        return daoEvaluacion.modificarEvaluacion(evaluacion);
    }

    public int eliminar(int idEvaluacion) {
        return daoEvaluacion.eliminarEvaluacion(idEvaluacion);
    }

    public ArrayList<EvaluacionDesempeño> listarTodas() {
        return daoEvaluacion.listarEvaluaciones();
    }
}

