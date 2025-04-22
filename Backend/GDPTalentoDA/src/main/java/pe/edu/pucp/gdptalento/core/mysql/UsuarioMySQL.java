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
import pe.edu.pucp.gdptalento.core.dao.UsuarioDAO;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.miembros.dao.StaffDAO;
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
            //Ejecuciones SQL de staff
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
    public int modificar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Usuario> listarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario obtenerPorId(String nombreRol) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
