import java.util.ArrayList;
import java.time.LocalDateTime;

class Tarea {
	private int id;
	private LocalDateTime fechaCreacion;
	private Usuario director;
	private ArrayList<Usuario> encargados;
	private LocalDateTime fechaLimite;
	private EstadoTarea estado;

	public Tarea() {
		this.encargados = new ArrayList<Usuario>();
	}

	public Tarea(int id, LocalDateTime fechaCreacion, Usuario director, LocalDateTime fechaLimite, EstadoTarea estado) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.director = director;
		this.fechaLimite = fechaLimite;
		this.estado = estado;
		this.encargados = new ArrayList<Usuario>();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario getDirector() {
		return director;
	}
	public void setDirector(Usuario director) {
		this.director = director;
	}

	public ArrayList<Usuario> getEncargados() {
		return encargados;
	}
	public void setEncargados(ArrayList<Usuario> encargados) {
		this.encargados = new ArrayList<Usuario>(encargados);
	}

	public LocalDateTime getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(LocalDateTime fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public EstadoTarea getEstado() {
		return estado;
	}
	public void setEstado(EstadoTarea estado) {
		this.estado = estado;
	}
}
