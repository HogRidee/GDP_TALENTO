package pe.edu.pucp.gdptalento.miembros.business;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.dao.MiembroPUCPDAO;
import pe.edu.pucp.gdptalento.miembros.model.MiembroPUCP;
import pe.edu.pucp.gdptalento.miembros.mysql.MiembroPUCPMySQL;

public class MiembroPUCPBO {
    private final MiembroPUCPDAO daoMiembro;

    public MiembroPUCPBO() {
        daoMiembro = new MiembroPUCPMySQL();
    }

    public int insertar(MiembroPUCP miembro) {
        return daoMiembro.insertarMiembroPUCP(miembro);
    }

    public int modificar(MiembroPUCP miembro) {
        return daoMiembro.modificarMiembroPUCP(miembro);
    }

    public int eliminar(int idMiembro) {
        return daoMiembro.eliminarMiembroPUCP(idMiembro);
    }

    public ArrayList<MiembroPUCP> listarTodas() {
        return daoMiembro.listarMiembrosPUCP();
    }
}

