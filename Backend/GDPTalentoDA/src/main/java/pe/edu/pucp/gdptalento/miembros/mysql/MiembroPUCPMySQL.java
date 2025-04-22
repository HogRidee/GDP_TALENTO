/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.miembros.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.dao.MiembroPUCPDAO;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.model.MiembroPUCP;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author raulm
 */
public class MiembroPUCPMySQL implements MiembroPUCPDAO{
    
    private Statement st;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    
    @Override
    public int insertarMiembroPUCP(MiembroPUCP miembroPUCP) {
        int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "INSERT INTO MiembroPUCP(nombre, correo, codigoPUCP, facultad, especialidad, status, telefono) VALUES(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            //st = con.createStatement();
            pst.setString(1, miembroPUCP.getNombre());
            pst.setString(2, miembroPUCP.getCorreo());
            pst.setInt(3, miembroPUCP.getCodigoPUCP());
            pst.setString(4, miembroPUCP.getFacultad());
            pst.setString(5, miembroPUCP.getEspecialidad());
            pst.setString(6, String.valueOf(miembroPUCP.getStatus()));
            pst.setString(7, String.valueOf(miembroPUCP.getTelefono()));
            resultado=pst.executeUpdate();
            System.out.println("Se ingreso un miembroPUCP");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificarMiembroPUCP(MiembroPUCP miembroPUCP) {
        int resultado=0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL MiembroPUCP
            String sql = "UPDATE MiembroPUCP SET correo = ? facultad = ? especialidad = ? status = ? telefono = ? WHERE id_miembro_pucp = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, miembroPUCP.getId());
            pst.setString(2, miembroPUCP.getCorreo());
            pst.setString(3, miembroPUCP.getFacultad());
            pst.setString(4, miembroPUCP.getEspecialidad());
            pst.setString(5, String.valueOf(miembroPUCP.getStatus()));
            pst.setInt(6, miembroPUCP.getTelefono());
            resultado = pst.executeUpdate();
            System.out.println("Se modifico un miembroPUCP");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminarMiembroPUCP(int id) {
        int resultado=0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            st = con.createStatement();
            String sql = "DELETE FROM Staff WHERE id_staff = " + id;
            resultado=st.executeUpdate(sql);
            System.out.println("Se elimino un staff");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<MiembroPUCP> listarMiembrosPUCP() {
        ArrayList<MiembroPUCP> listadoMiembro = new ArrayList<MiembroPUCP>();
        try{
            /*DBManager db = new DBManager();
            con = db.getConnection();
            String sql = "SELECT * FROM MiembroPUCP";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String estadoString = rs.getString("status");
                EstadoPUCP estado = EstadoPUCP.valueOf(estadoString);
                MiembroPUCP miembro = new MiembroPUCP();//es abstracto, no se puede utilizar p
                miembro.setId(rs.getInt("id_miembro_pucp"));
                miembro.setNombre(rs.getString("nombre"));
                miembro.setCorreo(rs.getString("correo"));
                miembro.setCodigoPUCP(rs.getInt("codigoPUCP"));
                miembro.setFacultad(rs.getString("facultad"));
                miembro.setEspecialidad(rs.getString("especialidad"));
                miembro.setStatus(estado);
                miembro.setTelefono(rs.getInt("telefono"));
                listadoMiembro.add(miembro);
            }*/
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return listadoMiembro;
    }

    @Override
    public MiembroPUCP obtenerPorIdMiembroPUCP(int idMiembroPUCP) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
