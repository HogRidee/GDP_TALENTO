package pe.edu.pucp.gdptalento.talento.model;

import pe.edu.pucp.gdptalento.miembros.model.Postulante;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrevista {

    private int id;
    private Date fecha;
    private ArrayList<Usuario> entrevistadores;
    private Postulante postulante;
    private EstadoEntrevista estado;
    private String feedback;
    private List<Integer> puntuaciones;
    private double puntuacionFinal;

    public Entrevista() {
        entrevistadores = new ArrayList<>();
        puntuaciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Usuario> getEntrevistadores() {
        return entrevistadores;
    }

    public void setEntrevistadores(ArrayList<Usuario> entrevistadores) {
        this.entrevistadores = entrevistadores;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public EstadoEntrevista getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntrevista estado) {
        this.estado = estado;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<Integer> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<Integer> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public double getPuntuacionFinal() {
        return puntuacionFinal;
    }

    public void setPuntuacionFinal(double puntuacionFinal) {
        this.puntuacionFinal = puntuacionFinal;
    }

    // Método para calcular la puntuación final (promedio de las puntuaciones)
    private double calcularPuntuacionFinal() {
        if (puntuaciones == null || puntuaciones.isEmpty()) {
            return 0;
        }

        double suma = 0;
        for (int puntuacion : puntuaciones) {
            suma += puntuacion;
        }

        return suma / puntuaciones.size();
    }

}
