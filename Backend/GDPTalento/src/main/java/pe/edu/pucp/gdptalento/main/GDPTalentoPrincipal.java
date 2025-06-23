package pe.edu.pucp.gdptalento.main;

import java.util.ArrayList;
import java.util.Date;
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
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pe.edu.pucp.gdptalento.miembros.mysql.StaffMySQL;

public class GDPTalentoPrincipal {
    public static void main(String[] args){
        /*
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
        */
        StaffBO staffBO = new StaffBO();
        AsistenciaBO asistenciaBO = new AsistenciaBO();
//        Staff nuevoStaff = new Staff();
//
//        // ⚠️ Asegúrate de que estos datos sean válidos
//        nuevoStaff.setNombre("María López");
//        nuevoStaff.setCorreo("maria.lopez@pucp.edu.pe");
//        nuevoStaff.setCodigoPUCP(20201234);
//        nuevoStaff.setFacultad("Ingeniería");
//        nuevoStaff.setEspecialidad("Informática");
//        nuevoStaff.setStatus(EstadoPUCP.MATRICULADO);
//        nuevoStaff.setTelefono("987654321");
//        nuevoStaff.setArea(Area.RECURSOS_HUMANOS);
//        nuevoStaff.setFechaIngreso(new Date()); // Fecha actual
//        nuevoStaff.setEstado(EstadoMiembro.ACTIVO);
//        nuevoStaff.setFechaSalida(new Date()); // Aún no tiene fecha de salida
//        nuevoStaff.setDesempenio(4.5); // Ejemplo de desempeño
//
//        // Ejecutar inserción
//        try {
//            int idGenerado = staffBO.insertar(nuevoStaff);
//            System.out.println("✅ Staff insertado correctamente. ID generado: " + idGenerado);
//        } catch (Exception ex) {
//            System.out.println("❌ Error al insertar staff: " + ex.getMessage());
//            ex.printStackTrace();
//        }

         // 1. Obtener staff existente
        ArrayList<Staff> listaStaff = staffBO.listarTodas(); // O staffBO.listarTodos()

        Staff staffExistente = null;
        int idBuscado = 5;

        for (Staff s : listaStaff) {
            if (s.getId() == idBuscado) {
                staffExistente = s;
                break;
            }
        }

        if (staffExistente != null) {
            // Modificas solo los campos necesarios
            staffExistente.setCorreo("body.lopez@pucp.edu.pe");
            staffExistente.setTelefono("987654321");
            staffExistente.setArea(Area.RECURSOS_HUMANOS);
            staffExistente.setEstado(EstadoMiembro.ACTIVO);
            staffExistente.setFechaSalida(null);
            staffExistente.setDesempenio(4.5);

            // Ejecutar modificación
            try {
                int resultado = staffBO.modificar(staffExistente);
                if (resultado > 0) {
                    System.out.println("✅ Staff modificado correctamente.");
                } else {
                    System.out.println("⚠️ No se realizó ninguna modificación.");
                }
            } catch (Exception ex) {
                System.out.println("❌ Error al modificar staff: " + ex.getMessage());
                ex.printStackTrace();
            }

        } else {
            System.out.println("⚠️ No se encontró un staff con ID = " + idBuscado);
        }
        
//        ArrayList<Asistencia> listaAsistencia = asistenciaBO.listarTodas();
//        for (Asistencia a : listaAsistencia) {
//                System.out.println("Asistente: " + a.getParticipante().getNombre());
//                System.out.println("Evento: " + a.getEvento().getTipoEvento());
//                System.out.println("Estado: " + a.getAsistencia());
//        }
        ArrayList<Asistencia> listaAsistencia = asistenciaBO.listarTodasPorID(1);
           for (Asistencia a : listaAsistencia) {
                System.out.println("Asistente: " + a.getParticipante().getNombre());
                System.out.println("Evento: " + a.getEvento().getTipoEvento());
                System.out.println("Estado: " + a.getAsistencia());
                System.out.println("Fecha: " + a.getEvento().getFecha());
                
        }
    }
}
