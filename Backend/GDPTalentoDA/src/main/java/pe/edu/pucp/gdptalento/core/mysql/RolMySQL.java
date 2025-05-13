package pe.edu.pucp.gdptalento.core.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.gdptalento.core.dao.RolDAO;
import pe.edu.pucp.gdptalento.core.model.NombreRol;
import pe.edu.pucp.gdptalento.core.model.Permiso;
import static pe.edu.pucp.gdptalento.core.model.Permiso.BORRAR_MIEMBRO;
import static pe.edu.pucp.gdptalento.core.model.Permiso.CREAR_ENTREVISTA;
import static pe.edu.pucp.gdptalento.core.model.Permiso.CREAR_MIEMBRO;
import static pe.edu.pucp.gdptalento.core.model.Permiso.CREAR_TAREA;
import pe.edu.pucp.gdptalento.core.model.Rol;
import pucp.edu.pe.gdptalento.config.DBManager;

public class RolMySQL implements RolDAO{
    
    private ResultSet rs;
    
    @Override
    public int insertar(Rol rol){
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        parametrosEntrada.put(1, String.valueOf(rol.getNombre()));
        StringBuilder permisos = new StringBuilder();
        construirListaPermisosString(rol, permisos);
        parametrosEntrada.put(2, permisos.toString());
        parametrosSalida.put(3, Types.INTEGER);
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ROL", 
                parametrosEntrada, parametrosSalida);
        int idRol = (int) parametrosSalida.get(3);
        rol.setId(idRol);
        System.out.println("Rol insertado con ID: " + idRol);
        return idRol;
    }

    @Override
    public int modificar(Rol rol){
        StringBuilder permisosBuilder = new StringBuilder();
        construirListaPermisosString(rol, permisosBuilder);
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, String.valueOf(rol.getNombre()));
        parametrosEntrada.put(2, rol.getId());
        parametrosEntrada.put(3, permisosBuilder.toString());
        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_ROL", 
                parametrosEntrada, null);
        System.out.println("Se modificó un rol con ID: " + rol.getId());
        return rol.getId();
    }
    
    @Override
    public int eliminar(int id_rol){
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id_rol);
        DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_ROL", 
                parametrosEntrada, null);
        System.out.println("Se eliminó el rol con ID: " + id_rol);
        return 1;
    }
    
    @Override
    public ArrayList<Rol> listarTodas() {
        ArrayList<Rol> listadoRoles = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ROLES", null);
        try {
            Rol rol = null;
            int rolActualId = -1;
            ArrayList<Permiso> permisos = null;
            while (rs.next()) {
                int idRol = rs.getInt("id_rol");
                String nombreRol = rs.getString("nombre_rol");
                String nombrePermiso = rs.getString("nombre_permiso");
                if (rol == null || idRol != rolActualId) { 
                    agregarRol(listadoRoles, rol, permisos);
                    rol = crearRolBasico(idRol, nombreRol);
                    permisos = new ArrayList<>();
                    rolActualId = idRol;
                }
                if (nombrePermiso != null) {
                    permisos.add(Permiso.valueOf(nombrePermiso));
                }
            }
            agregarRol(listadoRoles, rol, permisos); // Rol fuera del bucle
        } catch (SQLException ex) {
            System.out.println("ERROR al listar roles: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return listadoRoles;
    }

    @Override
    public Rol obtenerPorId(String nombreRol){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void construirListaPermisosString(Rol rol, StringBuilder permisos){
        for (Permiso p : rol.getPermisos()) {
            int idPermiso = obtenerIdPermiso(p);
            permisos.append(idPermiso).append(",");
        }
        // Eliminar la última coma
        if (permisos.length() > 0) {
            permisos.setLength(permisos.length() - 1);
        }
    }
    
    // MODIFICAR CADA VEZ QUE SE CREE UN PERMISO
    private int obtenerIdPermiso(Permiso permiso) {
        return switch (permiso) {
            case CREAR_MIEMBRO -> 1;
            case BORRAR_MIEMBRO -> 2;
            case CREAR_TAREA -> 3;
            case CREAR_ENTREVISTA -> 4;
            default -> 0;
        };
    }
    
    private void agregarRol(ArrayList<Rol> listadoRoles, Rol rol, ArrayList<Permiso> permisos){
        if (rol != null) {
            rol.setPermisos(permisos);
            listadoRoles.add(rol);
        }
    }
    
    private Rol crearRolBasico(int idRol, String nombreRol) {
        Rol rol = new Rol();
        rol.setId(idRol);
        rol.setNombre(NombreRol.valueOf(nombreRol));
        return rol;
    }
    
}
