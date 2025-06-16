package pe.edu.pucp.gdptalento.miembros.model;

import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;
import java.util.ArrayList;
import java.util.Date;

public class Staff extends MiembroPUCP {

    // Atributos
    private Area area;
    private Date fechaIngreso;
    private EstadoMiembro estado;
    private Date fechaSalida;
    private double desempenio;
    private ArrayList<EvaluacionDesempeño> evaluaciones;

    // Constructor
    public Staff() {
        evaluaciones = new ArrayList<>();
    }

    // Constructor con parámetros
    public Staff(Area area, Date fechaIngreso, EstadoMiembro estado, Date fechaSalida, double desempenio) {
        this.area = area;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.fechaSalida = fechaSalida;
        this.desempenio = desempenio;
    }

    // Getters y Setters
    public void setArea(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setEstado(EstadoMiembro estado) {
        this.estado = estado;
    }

    public EstadoMiembro getEstado() {
        return estado;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setDesempenio(double desempenio) {
        this.desempenio = desempenio;
    }

    public double getDesempenio() {
        return desempenio;
    }

    public ArrayList<EvaluacionDesempeño> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<EvaluacionDesempeño> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

}
