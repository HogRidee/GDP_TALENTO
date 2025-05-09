
package pe.edu.pucp.gdptalento.main;

import static java.lang.constant.ConstantDescs.NULL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.core.business.RolBO;
import pe.edu.pucp.gdptalento.core.business.UsuarioBO;
import pe.edu.pucp.gdptalento.core.dao.RolDAO;
import pe.edu.pucp.gdptalento.core.dao.UsuarioDAO;
import pe.edu.pucp.gdptalento.core.model.NombreRol;
import pe.edu.pucp.gdptalento.core.model.Permiso;
import pe.edu.pucp.gdptalento.core.model.Rol;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.core.mysql.RolMySQL;
import pe.edu.pucp.gdptalento.core.mysql.UsuarioMySQL;
import pe.edu.pucp.gdptalento.miembros.dao.StaffDAO;
import pe.edu.pucp.gdptalento.miembros.model.Area;
import pe.edu.pucp.gdptalento.miembros.model.EstadoMiembro;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pe.edu.pucp.gdptalento.miembros.mysql.StaffMySQL;
import pe.edu.pucp.gdptalento.talento.dao.EvaluacionDesempeñoDAO;
import pe.edu.pucp.gdptalento.talento.dao.TareaDAO;
import pe.edu.pucp.gdptalento.talento.model.EstadoTarea;
import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;
import pe.edu.pucp.gdptalento.talento.model.Tarea;
import pe.edu.pucp.gdptalento.talento.mysql.EvaluacionDesempeñoMySQL;
import pe.edu.pucp.gdptalento.talento.mysql.TareaMySQL;

public class GDPTalentoPrincipal {
    public static void main(String[] args){
        UsuarioBO boUsuario = new UsuarioBO();
        ArrayList<Usuario> usuarios = boUsuario.listarTodas();
        for (Usuario u: usuarios){
            System.out.println(u.getNombre());
        }
        
        RolBO boRol = new RolBO();
        ArrayList<Rol> roles = boRol.listarTodas();
        for (Rol rol : roles) {
            System.out.println("Rol: " + rol.getNombre() + " (ID: " + rol.getId() + ")");
            System.out.println("Permisos:");
            for (Permiso permiso : rol.getPermisos()) {
                System.out.println("  - " + permiso);
            }
            System.out.println("----------------------");
        }
    }
}
