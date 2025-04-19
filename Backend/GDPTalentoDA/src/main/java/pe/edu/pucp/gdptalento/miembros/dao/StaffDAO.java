package pe.edu.pucp.gdptalento.miembros.dao;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import java.util.ArrayList;

public interface StaffDAO {
    int insertar(Staff staff);
    int modificar(Staff staff);
    int eliminar(Staff staff);
    ArrayList<Staff> listarTodas();
    Staff obtenerPorId(int idStaff);
}
