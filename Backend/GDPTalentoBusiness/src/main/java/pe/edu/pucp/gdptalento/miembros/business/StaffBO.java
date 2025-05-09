package pe.edu.pucp.gdptalento.miembros.business;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.dao.StaffDAO;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pe.edu.pucp.gdptalento.miembros.mysql.StaffMySQL;

public class StaffBO {
    private final StaffDAO daoStaff;

    public StaffBO() {
        daoStaff = new StaffMySQL();
    }

    public int insertar(Staff staff) {
        return daoStaff.insertarStaff(staff);
    }

    public int modificar(Staff staff) {
        return daoStaff.modificar(staff);
    }

    public int eliminar(int idStaff) {
        return daoStaff.eliminar(idStaff);
    }

    public ArrayList<Staff> listarTodas() {
        return daoStaff.listarStaff();
    }
}

