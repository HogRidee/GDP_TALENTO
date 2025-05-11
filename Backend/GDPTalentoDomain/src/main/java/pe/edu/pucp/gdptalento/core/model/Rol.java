package pe.edu.pucp.gdptalento.core.model;

import java.util.ArrayList;

public class Rol {

    private int id;
    private NombreRol nombre;
    private ArrayList<Permiso> permisos;
    private ArrayList<Usuario> usuarios;

    // Constructor
    public Rol() {
        permisos = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public NombreRol getNombre() {
        return nombre;
    }

    public void setNombre(NombreRol nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(ArrayList<Permiso> permisos) {
        this.permisos = permisos;
    }
}
