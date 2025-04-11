package pe.edu.pucp.gdptalento.core.model;

import pe.edu.pucp.gdptalento.miembros.model.Staff;

public class Usuario extends Staff {

    // Atributos
    private String hashContrasena;
    private Rol rol;

    // Constructor por defecto
    public Usuario() {
        rol = new Rol();
    }

    // Getters y setters
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setHashContrasena(String contrasena) {
        this.hashContrasena = contrasena;
    }

    public String getHashContrasena() {
        return hashContrasena;
    }
}
