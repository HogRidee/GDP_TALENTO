package pe.edu.pucp.gdptalento.miembros.business;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.dao.PostulanteDAO;
import pe.edu.pucp.gdptalento.miembros.model.Postulante;
import pe.edu.pucp.gdptalento.miembros.mysql.PostulanteMySQL;

public class PostulanteBO {
    private final PostulanteDAO daoPostulante;

    public PostulanteBO() {
        daoPostulante = new PostulanteMySQL();
    }

    public int insertar(Postulante postulante) {
        return daoPostulante.insertarPostulante(postulante);
    }

    public int modificar(Postulante postulante) {
        return daoPostulante.modificarPostulante(postulante);
    }

    public int eliminar(int idPostulante) {
        return daoPostulante.eliminarPostulante(idPostulante);
    }

    public ArrayList<Postulante> listarTodas() {
        return daoPostulante.listarPostulantes();
    }
}

