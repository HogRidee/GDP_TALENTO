import java.time.LocalDateTime;

class Reporte {
	private int id;
	private String titulo;
	private String descripcion;
	private LocalDateTime fechaGeneracion;
	private Usuario generadoPor;

	public Reporte() {}

	public Reporte(int id, String titulo, String descripcion, LocalDateTime fechaGeneracion, Usuario generadoPor) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaGeneracion = fechaGeneracion;
		this.generadoPor = generadoPor;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaGeneracion() {
		return fechaGeneracion;
	}
	public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Usuario getGeneradoPor() {
		return generadoPor;
	}
	public void setGeneradoPor(Usuario generadoPor) {
		this.generadoPor = generadoPor;
	}
	
}
