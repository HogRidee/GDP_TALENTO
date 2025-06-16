package pe.edu.pucp.gdptalento.talento.model;

import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import java.util.Date;

public class EvaluacionDesempeño {

    private int id;
    private Usuario evaluador;
    private Staff miembroEvaluado;
    private int puntaje;
    private String comentarios;
    private Date fecha;

    public EvaluacionDesempeño() {
    }

    public EvaluacionDesempeño(int id, Staff miembroEvaluado, Usuario evaluador, int puntaje, String comentarios, Date fecha) {
        this.id = id;
        this.miembroEvaluado = miembroEvaluado;
        this.evaluador = evaluador;
        this.puntaje = puntaje;
        this.comentarios = comentarios;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Staff getMiembroEvaluado() {
        return miembroEvaluado;
    }

    public void setMiembroEvaluado(Staff miembroEvaluado) {
        this.miembroEvaluado = miembroEvaluado;
    }

    public Usuario getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Usuario evaluador) {
        this.evaluador = evaluador;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
