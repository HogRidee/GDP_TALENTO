package pe.edu.pucp.gdptalento.core.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.gdptalento.core.dao.UsuarioDAO;
import pe.edu.pucp.gdptalento.core.model.NombreRol;
import pe.edu.pucp.gdptalento.core.model.Rol;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.miembros.dao.StaffDAO;
import pe.edu.pucp.gdptalento.miembros.model.Area;
import pe.edu.pucp.gdptalento.miembros.model.EstadoMiembro;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.mysql.StaffMySQL;
import pucp.edu.pe.gdptalento.config.DBManager;

public class UsuarioMySQL implements UsuarioDAO{
    private Statement st;//no estoy usandolo pq estoy haciendo con preparedStatement
    private PreparedStatement pst;
    
    private Connection con;
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
        int resultado=0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL MiembroPUCP
             String sql = "UPDATE MiembroPUCP SET correo = ?, facultad = ?, especialidad = ?, status = ?, telefono = ? WHERE id_miembro_pucp = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getCorreo());
            pst.setString(2, usuario.getFacultad());
            pst.setString(3, usuario.getEspecialidad());
            pst.setString(4, String.valueOf(usuario.getStatus()));
            pst.setInt(5, usuario.getTelefono());
            pst.setInt(6, usuario.getId());
            resultado = pst.executeUpdate();
            System.out.println("Se modifico un miembroPUCP");
            //Ejecuciones SQL Staff
            sql = "UPDATE Staff SET area = ?, estado = ?, fecha_salida = ?, desempenio = ? WHERE id_staff = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(usuario.getArea()));
            pst.setString(2, String.valueOf(usuario.getEstado()));
            String fecha_fin = String.valueOf(usuario.getFechaSalida());
            Date fecha = java.sql.Date.valueOf(fecha_fin);
            pst.setDate(3, new java.sql.Date(fecha.getTime()));
            pst.setDouble(4, usuario.getDesempenio());
            pst.setInt(5, usuario.getId());
            resultado=pst.executeUpdate();
            System.out.println("Se modifico un staff");
            //Ejecuciones SQL Usuario
            sql = "UPDATE Usuario SET hash_contrasena= ? WHERE id_usuario = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getHashContrasena());
            //select en la tabla Rol y de ahi se obtiene el id_rol;
            pst.setInt(2, usuario.getId());
            resultado=pst.executeUpdate();
            System.out.println("Se modifico un usuario");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Correo: " + usuario.getCorreo());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int id_usuario) {
        int resultado=0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL Usuario
            st = con.createStatement();
            String sql = "DELETE FROM Usuario WHERE id_usuario = " + id_usuario;
            resultado=st.executeUpdate(sql);
            System.out.println("Se elimino un usuario");
            //Ejecuciones SQL Staff
            st = con.createStatement();
            sql = "DELETE FROM Staff WHERE id_staff = " + id_usuario;
            resultado=st.executeUpdate(sql);
            System.out.println("Se elimino un staff");
            con = db.getConnection();
            //Ejecuciones SQL MiembroPUCP
            st = con.createStatement();
            sql = "DELETE FROM MiembroPUCP WHERE id_miembro_pucp = " + id_usuario;
            resultado=st.executeUpdate(sql);
            System.out.println("Se elimino un miembroPUCP");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Usuario> listarTodas() {
        ArrayList<Usuario> listadoUsuario = new ArrayList<Usuario>();
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            String sql = "SELECT m.id_miembro_pucp, m.codigoPUCP, m.nombre, m.correo, m.telefono, m.facultad,"
                    + " m.especialidad, m.status, s.area, s.estado, s.desempenio, u.hash_contrasena " + /*, r.nombre AS nombre_rol */"FROM MiembroPUCP m "
                    + "INNER JOIN Staff s ON m.id_miembro_pucp = s.id_staff "
                    + "INNER JOIN Usuario u ON u.id_usuario = s.id_staff";
                    /*+ "INNER JOIN Rol r ON u.id_rol = r.id_rol";*/
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String estadoString = rs.getString("status");
                EstadoPUCP estado = EstadoPUCP.valueOf(estadoString);
                String areaString = rs.getString("area");
                Area area = Area.valueOf(areaString);
                String miembroString = rs.getString("estado");
                EstadoMiembro miembro = EstadoMiembro.valueOf(miembroString);
               /* String rolnombre = rs.getString("nombre_rol");
                NombreRol nomrol = NombreRol.valueOf(rolnombre);
                Rol rol = new Rol();
                rol.setNombre(nomrol);*/
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_miembro_pucp"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setCodigoPUCP(rs.getInt("codigoPUCP"));
                usuario.setFacultad(rs.getString("facultad"));
                usuario.setEspecialidad(rs.getString("especialidad"));
                usuario.setTelefono(rs.getInt("telefono"));
                usuario.setDesempenio(rs.getDouble("desempenio"));
                usuario.setStatus(estado);
                usuario.setArea(area);
                usuario.setEstado(miembro);
                usuario.setHashContrasena(rs.getString("hash_contrasena"));
                /*usuario.setRol(rol);*/
                listadoUsuario.add(usuario);
            }
            System.out.println("Se hizo la lista aparentemente");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return listadoUsuario;
    }

    @Override
    public Usuario obtenerPorId(String nombreRol) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        Date fecha_date = java.sql.Date.valueOf(usuario.getFechaIngreso());
        parametrosEntrada.put(10, new java.sql.Date(fecha_date.getTime()));
        parametrosEntrada.put(11, String.valueOf(usuario.getEstado()));
        fecha_date = java.sql.Date.valueOf(usuario.getFechaSalida());
        parametrosEntrada.put(12, new java.sql.Date(fecha_date.getTime()));
        parametrosEntrada.put(13, usuario.getDesempenio());
        parametrosEntrada.put(14, usuario.getHashContrasena());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_USUARIO", 
        parametrosEntrada, parametrosSalida);
    }
}
