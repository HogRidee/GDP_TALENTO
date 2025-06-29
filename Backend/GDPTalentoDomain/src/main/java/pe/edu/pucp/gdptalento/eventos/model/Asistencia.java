package pe.edu.pucp.gdptalento.eventos.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import pe.edu.pucp.gdptalento.miembros.model.Staff;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "asistencia")
public class Asistencia {
    private Staff participante;
    private Evento evento;
    private EstadoAsistencia asistencia;
    
    @XmlElement(required = true)
    private EstadoAsistencia estadoAsistencia;
    
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
    
    public EstadoAsistencia getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(EstadoAsistencia estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }
    
}
