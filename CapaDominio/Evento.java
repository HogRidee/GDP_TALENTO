import java.time.LocalDate;
import java.util.ArrayList;

public class Evento {
    private int id;
    private LocalDate fecha;
    private ArrayList<EstadoAsistencia> asistencias;
    private ArrayList<Usuario> encargados;
    private ArrayList<Staff> participantes;

    // Constructor
    public Evento(int id, LocalDate fecha, ArrayList<EstadoAsistencia> asistencias, ArrayList<Usuario> encargados, ArrayList<Staff> participantes) {
        this.id = id;
        this.fecha = fecha;
        this.asistencias = asistencias != null ? asistencias : new ArrayList<>();
        this.encargados = encargados != null ? encargados : new ArrayList<>();
        this.participantes = participantes != null ? participantes : new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
