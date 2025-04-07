import java.time.LocalDate;

class EvaluacionDesempeño {
	private int id;
	private Staff miembroEvaluado;
	private Usuario evaluador;
	private int puntaje;
	private String comentarios;
	private LocalDate fecha;

	public EvaluacionDesempeño() {}

	public EvaluacionDesempeño(int id, Staff miembroEvaluado, Usuario evaluador, int puntaje, String comentarios, LocalDate fecha) {
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

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}
