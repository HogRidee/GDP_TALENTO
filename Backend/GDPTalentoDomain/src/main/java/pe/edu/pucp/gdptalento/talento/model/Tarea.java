package pe.edu.pucp.gdptalento.talento.model;

import pe.edu.pucp.gdptalento.core.model.Usuario;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Tarea {

    private int id;
    private LocalDateTime fechaCreacion;
    private Usuario creador;
    private ArrayList<Usuario> encargados;
    private LocalDateTime fechaLimite;
    private EstadoTarea estado;

    public Tarea() {
        this.encargados = new ArrayList<Usuario>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public ArrayList<Usuario> getEncargados() {
        return encargados;
    }

    public void setEncargados(ArrayList<Usuario> encargados) {
        this.encargados = encargados;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

}
