package pe.edu.pucp.gdptalento.eventos.model;

import java.util.Date;
import pe.edu.pucp.gdptalento.miembros.model.Staff;

public class Asistencia {
    private Staff participante;
    private Evento evento;
    private EstadoAsistencia asistencia;

    public Staff getParticipante() {
        return participante;
    }

    public void setParticipante(Staff participante) {
        this.participante = participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public EstadoAsistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(EstadoAsistencia asistencia) {
        this.asistencia = asistencia;
    }
    
    public Date getEventoFecha() {
        return evento.getFecha();
    }
}
