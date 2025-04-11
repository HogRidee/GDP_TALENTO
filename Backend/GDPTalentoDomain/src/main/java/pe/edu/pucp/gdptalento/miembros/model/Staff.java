package pe.edu.pucp.gdptalento.miembros.model;

import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;
import java.time.LocalDate;
import java.util.ArrayList;

public class Staff extends MiembroPUCP {

    // Atributos
    private Area area;
    private LocalDate fechaIngreso;
    private EstadoMiembro estado;
    private LocalDate fechaSalida;
    private double desempenio;
    private ArrayList<EvaluacionDesempeño> evaluaciones;

    // Constructor
    public Staff() {
        evaluaciones = new ArrayList<>();
    }

    // Constructor con parámetros
    public Staff(Area area, LocalDate fechaIngreso, EstadoMiembro estado, LocalDate fechaSalida, double desempenio) {
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

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setEstado(EstadoMiembro estado) {
        this.estado = estado;
    }

    public EstadoMiembro getEstado() {
        return estado;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaSalida() {
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
