package pe.edu.pucp.gdptalento.main;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.core.business.RolBO;
import pe.edu.pucp.gdptalento.core.business.UsuarioBO;
import pe.edu.pucp.gdptalento.core.model.Permiso;
import pe.edu.pucp.gdptalento.core.model.Rol;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.eventos.business.AsistenciaBO;
import pe.edu.pucp.gdptalento.eventos.model.Asistencia;
import pe.edu.pucp.gdptalento.miembros.business.StaffBO;
import pe.edu.pucp.gdptalento.miembros.model.Area;
import pe.edu.pucp.gdptalento.miembros.model.EstadoMiembro;
import pe.edu.pucp.gdptalento.miembros.model.Staff;

public class GDPTalentoPrincipal {
    public static void main(String[] args){
        UsuarioBO boUsuario = new UsuarioBO();
        ArrayList<Usuario> usuarios = boUsuario.listarTodas();
        Usuario user = new Usuario();
        for (Usuario u: usuarios){
            if (u.getId()==1) user = u;
            System.out.println(u.getNombre());
        }
        System.out.println(user.getNombre());
        user.setEstado(EstadoMiembro.ACTIVO);
        user.setArea(Area.EVENTOS);
        int correcto = boUsuario.modificarBasico(user);
        
        System.out.println("Correcta modificacion: " + correcto);
        
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
        
        StaffBO boStaff = new StaffBO();
        ArrayList<Staff> staff_listado = boStaff.listarTodas();
        for (Staff s: staff_listado){
            System.out.println("Nombre    -   Area");
            System.out.print(s.getNombre() + "    ");
            System.out.println(s.getArea());
        }
        UsuarioBO u = new UsuarioBO();
        System.out.println(u.verificarUsuario(1, "hash12345"));
        
    }
}
