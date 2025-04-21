package pe.edu.pucp.gdptalento.eventos.model;

import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import java.time.LocalDate;
import java.util.ArrayList;

public class Evento {

    private int id;
    private LocalDate fecha;
    private ArrayList<Usuario> encargados;
    private ArrayList<Staff> participantes;

    // Constructor
    public Evento(int id, LocalDate fecha, ArrayList<EstadoAsistencia> asistencias, ArrayList<Usuario> encargados, ArrayList<Staff> participantes) {
        this.id = id;
        this.fecha = fecha;
        this.encargados = encargados != null ? encargados : new ArrayList<>();
        this.participantes = participantes != null ? participantes : new ArrayList<>();
    }

    public Evento() {
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
