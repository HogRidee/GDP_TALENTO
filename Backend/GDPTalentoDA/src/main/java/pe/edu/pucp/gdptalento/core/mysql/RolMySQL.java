package pe.edu.pucp.gdptalento.core.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    private Statement st;//no estoy usandolo pq estoy haciendo con preparedStatement
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    
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
    public ArrayList<Rol> listarTodas(){
        ArrayList<Rol> listadoRoles = new ArrayList<Rol>();
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            String sql = "SELECT r.nombre AS nombre_r, p.nombre AS nombre_p FROM Rol r "
                    + "INNER JOIN Rol_Permiso rp ON r.id_rol = rp.id_rol "
                    + "INNER JOIN Permiso p ON rp.id_permiso=p.id_permiso";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Rol rol= new Rol();//creamos primer rol
            ArrayList<Permiso> permisos = new ArrayList<Permiso>(); //creamos primer arreglo de permisos para el primer rol
            String nombre = rs.getString("nombre_r");
            permisos.add(Permiso.valueOf(rs.getString("nombre_p")));
            rol.setNombre(NombreRol.valueOf(nombre));
            while(rs.next()){
                if(nombre.equals(rs.getString("nombre_r"))){
                    Permiso permiso = Permiso.valueOf(rs.getString("nombre_p")); 
                    permisos.add(permiso);
                }
                else{
                    rol.setPermisos(permisos);
                    listadoRoles.add(rol);
                    rol = new Rol();//creo nuevo rol porque ya no estoy en el mismo rol
                    permisos = new ArrayList<Permiso>();//creo nueva lista de permisos para el nuevo rol
                    nombre = rs.getString("nombre_r");
                    permisos.add(Permiso.valueOf(rs.getString("p.nombre")));//aniado el permiso de la tabla en el arreglo permisos
                    rol.setNombre(NombreRol.valueOf(nombre));//seteo nombre de rol
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
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
    
}
