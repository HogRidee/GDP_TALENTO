package pe.edu.pucp.gdptalento.core.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.gdptalento.core.dao.UsuarioDAO;
import pe.edu.pucp.gdptalento.core.model.NombreRol;
import pe.edu.pucp.gdptalento.core.model.Rol;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.miembros.model.Area;
import pe.edu.pucp.gdptalento.miembros.model.EstadoMiembro;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pucp.edu.pe.gdptalento.config.DBManager;

public class UsuarioMySQL implements UsuarioDAO{
    private ResultSet rs;
    
    @Override
    public int insertar(Usuario usuario) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        agregarParametrosInsertar(parametrosSalida, parametrosEntrada, usuario);
        usuario.setId((int) parametrosSalida.get(1));
        System.out.println("Se ha realizado el registro del usuario");
        return usuario.getId();
    }

    @Override
    public int modificar(Usuario usuario) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        agregarParametrosModificar(parametrosEntrada, usuario);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_USUARIO", 
                parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del usuario");
        return resultado;
    }
    @Override
    public int modificarDatosBasicos(Usuario usuario) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        agregarParametrosModificarBasico(parametrosEntrada, usuario);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_DATOS_BASICOS_USUARIO", 
                parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del usuario");
        return resultado;
    }
    @Override
    public int eliminar(int id_usuario) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id_usuario);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_USUARIO", 
                parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del usuario");
        return resultado;
    }

    @Override
    public ArrayList<Usuario> listarTodas() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_USUARIOS", null);
        try {
            if (rs == null) {
                System.out.println("ERROR: ResultSet es null, no se puede listar usuarios.");
                return usuarios; // Retorna lista vacía
            }
            while (rs.next()) {
                Usuario usuario = new Usuario();
                leerInformacionMiembroPUCP(usuario);
                leerInformacionGeneral(usuario);
                leerRol(usuario); // FALTA QUE EN EL INSERTAR/MODIFICAR/ELIMINAR SE INTERACTÚE CON LA TABLA ROL
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            if(rs!=null){
                try{
                    rs.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            DBManager.getInstance().cerrarConexion();
        }
        return usuarios;
    }

    @Override
    public Usuario obtenerPorId(int idUsuario) {
        Usuario usuario = null;
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idUsuario);
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("BUSCAR_USUARIO_POR_ID", parametrosEntrada);

        try {
            if (rs.next()) {
                usuario = new Usuario();
                leerInformacionMiembroPUCP(usuario);
                leerInformacionGeneral(usuario);
                usuario.setFechaIngreso(rs.getDate("fecha_ingreso"));
                leerRolPorId(usuario); // Método modificado para usar el ID del rol
            }
        } catch (SQLException ex) {
            System.out.println("ERROR al obtener usuario por ID: " + ex.getMessage());
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBManager.getInstance().cerrarConexion();
        }

        return usuario;
    }
    
    private void agregarParametrosInsertar(Map<Integer,Object> parametrosSalida, 
            Map<Integer,Object> parametrosEntrada, Usuario usuario){
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, usuario.getNombre());
        parametrosEntrada.put(3, usuario.getCorreo());
        parametrosEntrada.put(4, usuario.getCodigoPUCP());
        parametrosEntrada.put(5, usuario.getFacultad());
        parametrosEntrada.put(6, usuario.getEspecialidad());
        parametrosEntrada.put(7, String.valueOf(usuario.getStatus()));
        parametrosEntrada.put(8, String.valueOf(usuario.getTelefono()));
        parametrosEntrada.put(9, String.valueOf(usuario.getArea()));
        parametrosEntrada.put(10, new Date(usuario.getFechaIngreso().getTime()));
        parametrosEntrada.put(11, String.valueOf(usuario.getEstado()));
        parametrosEntrada.put(12, new Date(usuario.getFechaSalida().getTime()));
        parametrosEntrada.put(13, usuario.getDesempenio());
        parametrosEntrada.put(14, usuario.getHashContrasena());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_USUARIO", 
        parametrosEntrada, parametrosSalida);
    }
    
    private void agregarParametrosModificar(Map<Integer, Object> parametrosEntrada, 
            Usuario usuario){
        parametrosEntrada.put(1, usuario.getId());
        parametrosEntrada.put(2, usuario.getCorreo());
        parametrosEntrada.put(3, usuario.getFacultad());
        parametrosEntrada.put(4, usuario.getEspecialidad());
        parametrosEntrada.put(5, String.valueOf(usuario.getStatus()));
        parametrosEntrada.put(6, usuario.getTelefono());
        parametrosEntrada.put(7, String.valueOf(usuario.getArea()));
        parametrosEntrada.put(8, String.valueOf(usuario.getEstado()));
        String fecha_fin = String.valueOf(usuario.getFechaSalida());
        Date fecha = java.sql.Date.valueOf(fecha_fin);
        parametrosEntrada.put(9, new java.sql.Date(fecha.getTime()));
        parametrosEntrada.put(10, usuario.getDesempenio());
        parametrosEntrada.put(11, usuario.getHashContrasena());
    }
    
    private void agregarParametrosModificarBasico(Map<Integer, Object> parametrosEntrada, 
            Usuario usuario){
        parametrosEntrada.put(1, usuario.getId());
        parametrosEntrada.put(2, usuario.getNombre());
        parametrosEntrada.put(3, usuario.getCodigoPUCP());
        parametrosEntrada.put(4, usuario.getFacultad());
        parametrosEntrada.put(5, usuario.getEspecialidad());
        parametrosEntrada.put(6, usuario.getCorreo());
        parametrosEntrada.put(7, usuario.getTelefono());
        parametrosEntrada.put(8, String.valueOf(usuario.getArea()));
        parametrosEntrada.put(9, String.valueOf(usuario.getStatus()));
        parametrosEntrada.put(10, String.valueOf(usuario.getEstado()));
        //parametrosEntrada.put(8, String.valueOf(usuario.getEstado()));
        //String fecha_fin = String.valueOf(usuario.getFechaSalida());
        //Date fecha = java.sql.Date.valueOf(fecha_fin);
        //parametrosEntrada.put(9, new java.sql.Date(fecha.getTime()));
    }
    
    
    private void leerInformacionMiembroPUCP(Usuario usuario) throws SQLException {
        usuario.setId(rs.getInt("id_miembro_pucp"));
        usuario.setCodigoPUCP(rs.getInt("codigoPUCP"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setFacultad(rs.getString("facultad"));
        usuario.setEspecialidad(rs.getString("especialidad"));
    }
    
    private void leerInformacionGeneral(Usuario usuario) throws SQLException{
        EstadoPUCP status = EstadoPUCP.valueOf(rs.getString("status"));
        usuario.setStatus(status);
        Area area = Area.valueOf(rs.getString("area"));
        usuario.setArea(area);
        EstadoMiembro estado = EstadoMiembro.valueOf(rs.getString("estado"));
        usuario.setEstado(estado);
        usuario.setDesempenio(rs.getDouble("desempenio"));
        usuario.setHashContrasena(rs.getString("hash_contrasena"));
    }
    
    private void leerRol(Usuario usuario) throws SQLException{
        String nombreRol = rs.getString("nombre_rol");
        if (nombreRol != null) {
            NombreRol nomrol = NombreRol.valueOf(nombreRol);
            Rol rol = new Rol();
            rol.setNombre(nomrol);
            usuario.setRol(rol);
        }
    }
    
    private void leerRolPorId(Usuario usuario) throws SQLException {
        int idRol = rs.getInt("id_rol");

        // Validamos si hay un valor válido
        if (!rs.wasNull()) {
            Rol rol = new Rol();
            rol.setId(idRol); // Asegúrate de que Rol tenga un campo y método setId(int)
            usuario.setRol(rol);
        }
    }
}
