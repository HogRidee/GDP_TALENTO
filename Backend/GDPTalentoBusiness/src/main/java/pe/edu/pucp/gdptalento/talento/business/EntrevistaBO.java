package pe.edu.pucp.gdptalento.talento.business;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.talento.dao.EntrevistaDAO;
import pe.edu.pucp.gdptalento.talento.model.Entrevista;
import pe.edu.pucp.gdptalento.talento.mysql.EntrevistaMySQL;

public class EntrevistaBO {
    private final EntrevistaDAO daoEntrevista;

    public EntrevistaBO() {
        daoEntrevista = new EntrevistaMySQL();
    }

    public int insertar(Entrevista entrevista) {
        return daoEntrevista.insertarEntrevista(entrevista);
    }

    public int modificar(Entrevista entrevista) {
        return daoEntrevista.modificarEntrevista(entrevista);
    }

    public int eliminar(int idEntrevista) {
        return daoEntrevista.eliminarEntrevista(idEntrevista);
    }

    public ArrayList<Entrevista> listarTodas() {
        return daoEntrevista.listarEntrevistas();
    }
    
    public Date obtenerFechaProximaEntrevista(){
        return daoEntrevista.obtenerFechaProximaEntrevista();
    }
}

