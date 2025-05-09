package pe.edu.pucp.gdptalento.eventos.business;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.eventos.dao.EventoDAO;
import pe.edu.pucp.gdptalento.eventos.model.Evento;
import pe.edu.pucp.gdptalento.eventos.mysql.EventoMySQL;

public class EventoBO {
    private final EventoDAO daoEvento;

    public EventoBO() {
        daoEvento = new EventoMySQL();
    }

    public int insertar(Evento evento) {
        return daoEvento.insertar(evento);
    }

    public int modificar(Evento evento) {
        return daoEvento.modificar(evento);
    }

    public int eliminar(Evento evento) {
        return daoEvento.eliminar(evento);
    }

    public ArrayList<Evento> listarTodas() {
        return daoEvento.listarTodos();
    }
}

