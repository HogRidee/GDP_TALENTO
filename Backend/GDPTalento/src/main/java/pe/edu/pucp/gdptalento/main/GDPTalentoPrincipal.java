
package pe.edu.pucp.gdptalento.main;

import static java.lang.constant.ConstantDescs.NULL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.core.dao.UsuarioDAO;
import pe.edu.pucp.gdptalento.core.model.NombreRol;
import pe.edu.pucp.gdptalento.core.model.Permiso;
import pe.edu.pucp.gdptalento.core.model.Rol;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.core.mysql.UsuarioMySQL;
import pe.edu.pucp.gdptalento.miembros.model.Area;
import pe.edu.pucp.gdptalento.miembros.model.EstadoMiembro;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;

/**
 *
 * @author USER
 */
public class GDPTalentoPrincipal {
    public static void main(String[] args){
        UsuarioDAO userdao = new UsuarioMySQL();
        Usuario user = new Usuario();
        user.setArea(Area.MARKETING);
        user.setCodigoPUCP(20202290);
        user.setCorreo("a20202290@pucp.edu.pe");
        user.setDesempenio(12.5);
        user.setEspecialidad("Industrial");
        user.setEstado(EstadoMiembro.ACTIVO);
        user.setFacultad("Ciencias e Ingenieria");
        LocalDate fecha = LocalDate.of(2025, 4, 22);
        LocalDate fecha_fin = LocalDate.of(2030, 4, 22);
        user.setFechaIngreso(fecha);
        user.setFechaSalida(fecha_fin);
        
        user.setHashContrasena("CarlosSport");
        user.setNombre("Raul");
        Rol rol = new Rol();
        rol.setNombre(NombreRol.MIEMBRO_RRHH);
        ArrayList<Permiso> permisos = new ArrayList<Permiso>();
        permisos.add(Permiso.CREAR_TAREA);
        permisos.add(Permiso.BORRAR_MIEMBRO);
        permisos.add(Permiso.CREAR_ENTREVISTA);
        permisos.add(Permiso.CREAR_MIEMBRO);
        rol.setPermisos(permisos);
        user.setRol(rol);
        user.setStatus(EstadoPUCP.EXTERNO);
        user.setTelefono(951709112);
        //la vaina del id puede funcionar agregando un select con el id generado del insert del user
        //userdao.insertar(user);
        
        //userdao.modificar(user, 1);
        userdao.eliminar(20);
        /*
        Staff staff = new Staff();
        staff.setArea(Area.MARKETING);
        staff.setCodigoPUCP(20181102);
        staff.setCorreo("a20181102@pucp.edu.pe");
        staff.setDesempenio(12);
        staff.setEspecialidad("Bartender");
        staff.setEstado(EstadoMiembro.ACTIVO);
        staff.setEvaluaciones(null);
        staff.setFacultad("Hoteleria y Turismo");
        LocalDate fecha2 = LocalDate.of(2020, 3, 11);
        staff.setFechaIngreso(fecha2);
        staff.setFechaSalida(null);
        staff.setNombre("Jorgito");
        staff.setStatus(EstadoPUCP.NO_MATRICULADO);
        staff.setTelefono(72821921);
        
        EvaluacionDesempeño ev = new EvaluacionDesempeño(); 
        ev.setComentarios("lo intento");
        ev.setEvaluador(user);
        ev.setFecha(fecha);
        ev.setMiembroEvaluado(staff);
        ev.setPuntaje(13);
        */
        
        
    }
}
