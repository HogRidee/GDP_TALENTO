package pe.edu.pucp.gdptalento.core.business;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.core.dao.RolDAO;
import pe.edu.pucp.gdptalento.core.model.Rol;
import pe.edu.pucp.gdptalento.core.mysql.RolMySQL;

public class RolBO {
    private final RolDAO daoRol;
    
    public RolBO(){
        daoRol = new RolMySQL();
    }
    
    public int insertar(Rol rol){
        return daoRol.insertar(rol);
    }
    
    public int modificar(Rol rol){
        return daoRol.modificar(rol);
    }
    
    public int eliminar(int idRol){
        return daoRol.eliminar(idRol);
    }
    
    public ArrayList<Rol> listarTodas(){
        return daoRol.listarTodas();
    }
}
