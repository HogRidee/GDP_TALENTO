package pe.edu.pucp.gdptalento.talento.model;

import pe.edu.pucp.gdptalento.core.model.Usuario;
import java.util.ArrayList;
import java.util.Date;

public class Tarea {

    private int id;
    private Date fechaCreacion;
    private Usuario creador;

    
    private String descripcion;
    private ArrayList<Usuario> encargados;
    private Date fechaLimite;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
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

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
