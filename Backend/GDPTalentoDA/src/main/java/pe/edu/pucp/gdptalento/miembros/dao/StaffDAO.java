package pe.edu.pucp.gdptalento.miembros.dao;

import pe.edu.pucp.gdptalento.miembros.model.Staff;
import java.util.ArrayList;

public interface StaffDAO {
    int insertarStaff(Staff staff);
    int modificar(Staff staff);
    int eliminar(int id);  
    ArrayList<Staff> listarStaff();
    Staff obtenerPorId(int idStaff);
    int calcularVariacionMes();
}
