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
import pe.edu.pucp.gdptalento.miembros.dao.PostulanteDAO;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.model.EstadoProceso;
import pe.edu.pucp.gdptalento.miembros.model.Postulante;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author raulm
 */
public class PostulanteMySQL extends MiembroPUCPMySQL implements PostulanteDAO{
    
    private Statement st;//no estoy usandolo pq estoy haciendo con preparedStatement
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    
    @Override
    public int insertarPostulante(Postulante postulante) {
        int resultado = 0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            String sql = "INSERT INTO MiembroPUCP(nombre, correo, codigoPUCP, facultad, especialidad, status, telefono) VALUES(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            //st = con.createStatement();
            pst.setString(1, postulante.getNombre());
            pst.setString(2, postulante.getCorreo());
            pst.setInt(3, postulante.getCodigoPUCP());
            pst.setString(4, postulante.getFacultad());
            pst.setString(5, postulante.getEspecialidad());
            pst.setString(6, String.valueOf(postulante.getStatus()));
            pst.setString(7, String.valueOf(postulante.getTelefono()));
            resultado=pst.executeUpdate();
            //sql=con.createStatement();
            sql="SELECT @@last_insert_id AS id";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            rs.next();
            postulante.setId(rs.getInt("id"));
            sql="INSERT INTO Postulante(id_postulante, estado_proceso) VALUES(?,?)";
            pst=con.prepareStatement(sql);
            pst.setInt(1, postulante.getId());
            pst.setString(2, String.valueOf(postulante.getStatus()));
            resultado=pst.executeUpdate();
            System.out.println("Se ingreso un PostulantePUCP");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificarPostulante(Postulante postulante) {
        int resultado=0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL MiembroPUCP
            String sql = "UPDATE MiembroPUCP SET correo = ? facultad = ? especialidad = ? status = ? telefono = ? WHERE id_miembro_pucp = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, postulante.getCorreo());
            pst.setString(2, postulante.getFacultad());
            pst.setString(3, postulante.getEspecialidad());
            pst.setString(4, String.valueOf(postulante.getStatus()));
            pst.setInt(5, postulante.getTelefono());
            pst.setInt(6, postulante.getId());
            resultado=pst.executeUpdate();
            System.out.println("Se modifico un miembroPUCP");
            //Ejecuciones SQL Staff
            sql = "UPDATE Postulante SET estado_proceso = ? WHERE id_postulante = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(postulante.getEstadoProceso()));
            pst.setInt(2, postulante.getId());
            resultado=pst.executeUpdate();
            System.out.println("Se modifico un postulante");
            
            //Ejecuciones SQL
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminarPostulante(int id) {
        int resultado=0;
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            //Ejecuciones SQL
            st = con.createStatement();
            String sql = "DELETE FROM Postulante WHERE id_postulante = " + id;
            resultado=st.executeUpdate(sql);
            System.out.println("Se elimino un postulante");
            con = db.getConnection();
            //Ejecuciones SQL
            st = con.createStatement();
            sql = "DELETE FROM MiembroPUCP WHERE id_miembro_pucp = " + id;
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
    public ArrayList<Postulante> listarPostulantes() {
        ArrayList<Postulante> listadoPostulantes = new ArrayList<Postulante>();
        try{
            DBManager db = new DBManager();
            con = db.getConnection();
            String sql = "SELECT m.id_miembro_pucp, m.codigoPUCP, m.nombre, m.correo, m.telefono, m.facultad, m.especialidad, m.status, p.estado_proceso FROM MiembroPUCP m INNER JOIN Postulantes p ON m.id_miembro_pucp = p.id_postulante";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String estadoString = rs.getString("status");
                EstadoPUCP estado = EstadoPUCP.valueOf(estadoString);
                String procesoString = rs.getString("estado_proceso");
                EstadoProceso proceso = EstadoProceso.valueOf(procesoString);
                Postulante postulante = new Postulante();
                postulante.setId(rs.getInt("id_miembro_pucp"));
                postulante.setNombre(rs.getString("nombre"));
                postulante.setCorreo(rs.getString("correo"));
                postulante.setCodigoPUCP(rs.getInt("codigoPUCP"));
                postulante.setFacultad(rs.getString("facultad"));
                postulante.setEspecialidad(rs.getString("especialidad"));
                postulante.setStatus(estado);
                postulante.setTelefono(rs.getInt("telefono"));
                postulante.setEstadoProceso(proceso);
                listadoPostulantes.add(postulante);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{con.close();} catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return listadoPostulantes;
    }

    @Override
    public Postulante obtenerPorId(int idPostulante) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
