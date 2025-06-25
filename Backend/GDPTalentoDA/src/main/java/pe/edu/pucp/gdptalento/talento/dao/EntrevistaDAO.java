package pe.edu.pucp.gdptalento.talento.dao;
import pe.edu.pucp.gdptalento.talento.model.Entrevista;
import java.util.ArrayList;

public interface EntrevistaDAO {
    int insertarEntrevista(Entrevista entrevista);
    int modificarEntrevista(Entrevista entrevista);
    int eliminarEntrevista(int id);
    ArrayList<Entrevista> listarEntrevistas();
    Entrevista obtenerPorId(int id);
    java.sql.Date obtenerFechaProximaEntrevista();
}