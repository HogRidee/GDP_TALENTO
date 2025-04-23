
package pe.edu.pucp.gdptalento.main;

import static java.lang.constant.ConstantDescs.NULL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import pe.edu.pucp.gdptalento.talento.dao.TareaDAO;
import pe.edu.pucp.gdptalento.talento.model.EstadoTarea;
import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;
import pe.edu.pucp.gdptalento.talento.model.Tarea;
import pe.edu.pucp.gdptalento.talento.mysql.TareaMySQL;

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
        //userdao.insertar(user);//agrega por primera vez el id del user
        
        user.setCorreo("correo@hotmail.com");
        user.setHashContrasena("contrasena");
        user.setId(21);//debo forzar el id, debido a que al comentar el insertar y ejecutar el prncipal nunca se le coloca id pero funciona luego de hacer insertar
        userdao.modificar(user);
        
        for(Usuario u : userdao.listarTodas()){
            System.out.println(u.getNombre());
        }
        //userdao.eliminar(20);//coloca el id del usuario, fijar en la tabla
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
        
        /////////////////////////////////////////////ENCARGADO DE TAREA
        Usuario user2 = new Usuario();
        user2.setArea(Area.MARKETING);
        user2.setCodigoPUCP(20112211);
        user2.setCorreo("a20112211@pucp.edu.pe");
        user2.setDesempenio(20);
        user2.setEspecialidad("Informatica");
        user2.setEstado(EstadoMiembro.ACTIVO);
        user2.setFacultad("Ciencias e Ingenieria");
        LocalDate fechax = LocalDate.of(2025, 4, 22);
        LocalDate fechay = LocalDate.of(2030, 4, 22);
        user2.setFechaIngreso(fechax);
        user2.setFechaSalida(fechay);
        
        user2.setHashContrasena("esternocleidomastoideo");
        user2.setNombre("Jose");
        
        //userdao.insertar(user2);
        /////////////////////////////////////////////////TAREA
        TareaDAO tdao = new TareaMySQL();
        Tarea tarea = new Tarea();
        tarea.setCreador(user);
        tarea.setEstado(EstadoTarea.REALIZADA);
        LocalDateTime fecha_tarea_creacion = LocalDateTime.of(2025, 4, 23, 0, 0);
        tarea.setFechaCreacion(fecha_tarea_creacion);
        LocalDateTime fecha_tarea_fin = LocalDateTime.of(2025, 4, 30, 0, 0);
        tarea.setFechaLimite(fecha_tarea_fin);
        ArrayList<Usuario> encargados = new ArrayList<Usuario>();
        encargados.add(user2);
        tarea.setEncargados(encargados);
        
        //tdao.insertarTarea(tarea);
        //tdao.eliminarTarea(2);
        tarea.setEstado(EstadoTarea.EN_PROCESO);
        tarea.setId(3);//fuerzo seteo de id para poder hacer los cambios en la tarea correspondiente
        tdao.modificarTarea(tarea);
        for(Tarea t : tdao.listarTareas()){
            System.out.println(String.valueOf(t.getFechaCreacion()));
            System.out.println(String.valueOf(t.getEstado()));
            System.out.println(t.getCreador().getCorreo());
        }
    }
}
