import java.util.ArrayList;

class Rol {

    // Atributos
    private NombreRol nombre;
    private ArrayList<Permiso> permisos;

    // Constructor
    public Rol() {
        permisos = new ArrayList<Permiso>();
    }

    // Getters y Setters
    public void setNombreRol(NombreRol nombre) {
        this.nombre = nombre;
    }

    public NombreRol getNombreRol() {
        return nombre;
    }

    public void agregarPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }

    public ArrayList<Permiso> getPermisos() {
        return permisos;
    }
}
