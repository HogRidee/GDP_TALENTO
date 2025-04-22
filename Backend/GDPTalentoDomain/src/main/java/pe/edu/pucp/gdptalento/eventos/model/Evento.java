package pe.edu.pucp.gdptalento.eventos.model;

import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import java.time.LocalDate;
import java.util.ArrayList;

public class Evento {

    private int id;
    private LocalDate fecha;
    private EstadoEvento estadoEvento;
    private TipoEvento tipoEvento;
    private ArrayList<Usuario> encargados;
    private ArrayList<Staff> participantes;

    // Constructor
    public Evento(LocalDate fecha, TipoEvento tipoEvento, ArrayList<Usuario> encargados, ArrayList<Staff> participantes) {
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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
