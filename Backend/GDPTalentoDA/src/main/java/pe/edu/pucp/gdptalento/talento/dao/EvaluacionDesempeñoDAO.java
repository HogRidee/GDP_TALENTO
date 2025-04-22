package pe.edu.pucp.gdptalento.talento.dao;
import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;
import java.util.ArrayList;

public interface EvaluacionDesempeñoDAO {
    int insertarEvaluacion(EvaluacionDesempeño evaluacion);
    int modificarEvaluacion(EvaluacionDesempeño evaluacion);
    int eliminarEvaluacion(int id);
    ArrayList<EvaluacionDesempeño> listarEvaluaciones();
    EvaluacionDesempeño obtenerPorId(int id);
}