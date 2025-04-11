package pe.edu.pucp.gdptalento.miembros.model;

public class Postulante extends MiembroPUCP {

    private EstadoProceso estadoProceso;

    // Constructor vacío
    public Postulante() {
        super();  // Llama al constructor vacío de la clase padre
        this.estadoProceso = EstadoProceso.PENDIENTE;
    }

    // Constructor con parámetros
    public Postulante(int id, String nombre, String correo, int codigoPUCP, String facultad, String especialidad, EstadoPUCP status, int telefono,
            EstadoProceso estadoProceso) {
        super(id, nombre, correo, codigoPUCP, facultad, especialidad, status, telefono);
        this.estadoProceso = estadoProceso;
    }

    // Constructor copia
    public Postulante(Postulante otro) {
        super(otro);  // Llama al constructor copia de MiembroPUCP
        this.estadoProceso = otro.estadoProceso;
    }

    public EstadoProceso getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(EstadoProceso estadoProceso) {
        this.estadoProceso = estadoProceso;
    }
}
