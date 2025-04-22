/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author raulm
 */
public class UsuarioMySQL implements UsuarioDAO{
    
    private Statement st;//no estoy usandolo pq estoy haciendo con preparedStatement
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    
    @Override
    public int insertar(Usuario usuario, int id) {
        int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL de MiembroPUCP
            String sql = "INSERT INTO MiembroPUCP(nombre, correo, codigoPUCP, facultad, especialidad, status, telefono) VALUES(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            //st = con.createStatement();
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getCorreo());
            pst.setInt(3, usuario.getCodigoPUCP());
            pst.setString(4, usuario.getFacultad());
            pst.setString(5, usuario.getEspecialidad());
            pst.setString(6, String.valueOf(usuario.getStatus()));
            pst.setString(7, String.valueOf(usuario.getTelefono()));
            resultado=pst.executeUpdate();
            sql="SELECT @@last_insert_id AS id";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            rs.next();
            usuario.setId(rs.getInt("id"));
            //Ejecuciones SQL de Staff
            sql="INSERT INTO Staff(id_staff, area, fecha_ingreso, estado, fecha_salida, desempenio) VALUES(?,?)";
            pst=con.prepareStatement(sql);
            pst.setInt(1, usuario.getId());
            pst.setString(2, String.valueOf(usuario.getArea()));
            Date fecha_date=java.sql.Date.valueOf(usuario.getFechaIngreso());
            pst.setDate(3, new java.sql.Date(fecha_date.getTime()));
            pst.setString(4, String.valueOf(usuario.getEstado()));
            fecha_date=java.sql.Date.valueOf(usuario.getFechaSalida());
            pst.setDate(5, new java.sql.Date(fecha_date.getTime()));
            pst.setDouble(6, usuario.getDesempenio());
            resultado=pst.executeUpdate();
            //Ejecuciones SQL de Usuario
            sql = "INSERT INTO Usuario(hash_contrasena, id_rol, id_usuario) VALUES(?,?,?) ";
            pst = con.prepareStatement(sql);
            //st = con.createStatement();
            pst.setString(1, usuario.getHashContrasena());
            pst.setInt(2, id);
            pst.setInt(3, rs.getInt("id_staff"));
            resultado=pst.executeUpdate();
            System.out.println("Se ingreso un Usuario");
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Usuario usuario, int id_rol) {
        int resultado=0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL MiembroPUCP
             String sql = "UPDATE MiembroPUCP SET correo = ? facultad = ? especialidad = ? status = ? telefono = ? WHERE id_miembro_pucp = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getCorreo());
            pst.setString(2, usuario.getFacultad());
            pst.setString(3, usuario.getEspecialidad());
            pst.setString(4, String.valueOf(usuario.getStatus()));
            pst.setInt(5, usuario.getTelefono());
            pst.setInt(6, usuario.getId());
            resultado = pst.executeUpdate();
            //Ejecuciones SQL Staff
            sql = "UPDATE Staff SET area = ? estado = ? fecha_salida = ? desempenio = ? WHERE id_staff = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, usuario.getId());
            pst.setString(2, String.valueOf(usuario.getArea()));
            pst.setString(3, String.valueOf(usuario.getEstado()));
            String fecha_fin = String.valueOf(usuario.getFechaSalida());
            Date fecha = java.sql.Date.valueOf(fecha_fin);
            pst.setDate(4, new java.sql.Date(fecha.getTime()));
            pst.setDouble(5, usuario.getDesempenio());
            resultado=pst.executeUpdate();
            //Ejecuciones SQL Usuario
            sql = "UPDATE Usuario SET hash_contrasena= ? id_rol = ? WHERE id_usuario = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getHashContrasena());
            pst.setInt(2, id_rol);
            resultado=pst.executeUpdate();
            System.out.println("Se modifico un usuario");
            
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
            System.out.println("Se elimino un miembroPUCP");
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
                    + " m.especialidad, m.status, s.area, s.estado, s.desempenio, u.hash_contrasena, r.nombre AS nombre_rol FROM MiembroPUCP m "
                    + "INNER JOIN Staff s ON m.id_miembro_pucp = s.id_staff "
                    + "INNER JOIN Usuario u ON u.id_usuario = m.id_miembro_pucp "
                    + "INNER JOIN Rol r ON u.id_rol = r.id_rol";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String estadoString = rs.getString("status");
                EstadoPUCP estado = EstadoPUCP.valueOf(estadoString);
                String areaString = rs.getString("area");
                Area area = Area.valueOf(areaString);
                String miembroString = rs.getString("estado");
                EstadoMiembro miembro = EstadoMiembro.valueOf(miembroString);
                String rolnombre = rs.getString("nombre_rol");
                NombreRol nomrol = NombreRol.valueOf(rolnombre);
                Rol rol = new Rol();
                rol.setNombre(nomrol);
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
                usuario.setHashContrasena("hash_contrasena");
                usuario.setRol(rol);
                listadoUsuario.add(usuario);
            }
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
    
}
