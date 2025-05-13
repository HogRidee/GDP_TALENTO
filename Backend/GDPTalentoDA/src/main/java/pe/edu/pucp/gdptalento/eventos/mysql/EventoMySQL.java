/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.eventos.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.eventos.dao.EventoDAO;
import pe.edu.pucp.gdptalento.eventos.model.EstadoEvento;
import pe.edu.pucp.gdptalento.eventos.model.Evento;
import pe.edu.pucp.gdptalento.eventos.model.TipoEvento;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author Dayana
 */
public class EventoMySQL implements EventoDAO{
    private Connection con;
    private Statement st;
    private ResultSet rs;
    

    @Override
    public int insertar(Evento evento) {
        int resultado=0;
        try{
            con= DBManager.getInstance().getConnection();
            String sql= "INSERT INTO Evento (id_evento, fecha, tipoEvento, estadoEvento)"
                    + "VALUES ('" + evento.getId() +
                    "','" + evento.getFecha()+"','"+ evento.getTipoEvento()+"','"+ evento.getEstadoEvento()+ "')";
            System.out.println(sql);
            st = con.createStatement();
            resultado = st.executeUpdate(sql); 
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(Evento evento) {
       int resultado=0;
        try{
            con= DBManager.getInstance().getConnection();
            String sql= "UPDATE Evento SET fecha = '"
                    + evento.getFecha()+ "'  WHERE"+  " id_evento = "+ evento.getId();
            st = con.createStatement();
            resultado = st.executeUpdate(sql); 
            System.out.println("Se ha actualizado la fecha");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(Evento evento) {
        int resultado=0;
        try{
            con= DBManager.getInstance().getConnection();
            String sql= "UPDATE Evento SET estadoEvento = 'CANCELADO'"
                    + "  WHERE id_evento = "+ evento.getId();
            st = con.createStatement();
            resultado = st.executeUpdate(sql); 
            System.out.println("Se ha eliminado el evento");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Evento> listarTodos() {
         ArrayList<Evento> eventos = new ArrayList<>();
        try{
            con= DBManager.getInstance().getConnection();
            
            String sql = "SELECT e.id_evento, e.fecha, e.tipoEvento, e.estadoEvento"
                    + " FROM Evento e"
                    + "WHERE e.estadoEvento = 'APROBADO'"
                    + "AND e.tipoEvento = 'REUNION'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Evento evento = new Evento();
                
                evento.setId(rs.getInt("id_evento"));
                evento.setFecha(rs.getDate("fecha").toLocalDate());
                evento.setEstadoEvento(EstadoEvento.APROBADO);
                evento.setTipoEvento(TipoEvento.REUNION);
                
                eventos.add(evento);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return eventos;
    }
   
}
