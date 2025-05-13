package pe.edu.pucp.gdptalento.miembros.model;

public class MiembroPUCP {
    private int id;
    private String nombre;
    private String correo;
    private int codigoPUCP;
    private String facultad;
    private String especialidad;
    private EstadoPUCP status;
    private int telefono; 

    // Constructor por defecto
    public MiembroPUCP() {
        this.id = 0;
        this.nombre = "";
        this.correo = "";
        this.codigoPUCP = 0;
        this.facultad = "";
        this.especialidad = "";
        this.status = EstadoPUCP.NO_MATRICULADO;
        this.telefono = 0;
    }

    // Constructor con parámetros
    public MiembroPUCP(int id, String nombre, String correo, int codigoPUCP, String facultad, String especialidad, EstadoPUCP status, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.codigoPUCP = codigoPUCP;
        this.facultad = facultad;
        this.especialidad = especialidad;
        this.status = status;
        this.telefono = telefono;
    }

    // Constructor copia
    public MiembroPUCP(MiembroPUCP otro) {
        this.id = otro.id;
        this.nombre = otro.nombre;
        this.correo = otro.correo;
        this.codigoPUCP = otro.codigoPUCP;
        this.facultad = otro.facultad;
        this.especialidad = otro.especialidad;
        this.status = otro.status;
        this.telefono = otro.telefono;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCodigoPUCP() {
        return codigoPUCP;
    }

    public void setCodigoPUCP(int codigoPUCP) {
        this.codigoPUCP = codigoPUCP;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public EstadoPUCP getStatus() {
        return status;
    }

    public void setStatus(EstadoPUCP status) {
        this.status = status;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}
