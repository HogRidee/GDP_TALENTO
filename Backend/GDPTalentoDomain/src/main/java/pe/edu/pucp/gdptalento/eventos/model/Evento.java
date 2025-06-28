package pe.edu.pucp.gdptalento.eventos.model;

import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "evento")
@XmlAccessorType(XmlAccessType.FIELD)
public class Evento {

    private int id;
    private Date fecha;
    private EstadoEvento estadoEvento;
    private TipoEvento tipoEvento;
    @XmlElementWrapper(name = "encargados")
    @XmlElement(name = "usuario")
    private ArrayList<Usuario> encargados;
    @XmlElementWrapper(name = "participantes")
    @XmlElement(name = "staff")
    private ArrayList<Staff> participantes;

    // Constructor
    public Evento(Date fecha, TipoEvento tipoEvento, ArrayList<Usuario> encargados, ArrayList<Staff> participantes) {
        this.fecha = fecha;
        this.tipoEvento = tipoEvento;
        this.estadoEvento = EstadoEvento.APROBADO;
        this.encargados = encargados != null ? encargados : new ArrayList<>();
        this.participantes = participantes != null ? participantes : new ArrayList<>();
    }

    public Evento() {
        this.estadoEvento = EstadoEvento.APROBADO;
        this.encargados = new ArrayList<>();
        this.participantes = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

     public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EstadoEvento estadoEvento) {
        this.estadoEvento = estadoEvento;
    }
     public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    
    public ArrayList<Usuario> getEncargados() {
        return new ArrayList<>(encargados);
    }

    public void setEncargados(ArrayList<Usuario> encargados) {
        this.encargados = new ArrayList<>(encargados);
    }

    public ArrayList<Staff> getParticipantes() {
        return new ArrayList<>(participantes);
    }

    public void setParticipantes(ArrayList<Staff> participantes) {
        this.participantes = new ArrayList<>(participantes);
    }

}
