/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.eventos.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;
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
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, Date.valueOf(evento.getFecha()));
        parametrosEntrada.put(2, evento.getTipoEvento());
        parametrosEntrada.put(3, evento.getEstadoEvento());
        int resultado=DBManager.getInstance().ejecutarProcedimiento("INSERTAR_EVENTO", parametrosEntrada, null);
        System.out.println("Se ha realizado el registro del evento");
        return resultado;
    }

    @Override
    public int modificar(Evento evento) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, evento.getId());
        parametrosEntrada.put(2, Date.valueOf(evento.getFecha()));
        parametrosEntrada.put(3, evento.getTipoEvento());
        parametrosEntrada.put(4, evento.getEstadoEvento());
        int resultado=DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_EVENTO", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del evento");
        return resultado;
    }

    @Override
    public int eliminar(Evento evento) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, evento.getId());
        int resultado=DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_EVENTO", parametrosEntrada, null);
        System.out.println("Se ha eliminado el evento");
        return resultado;
    }

    @Override
    public ArrayList<Evento> listarTodos() {
         ArrayList<Evento> eventos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_EVENTO", null);
        System.out.println("Lectura de eventos...");
        try{
            while(rs.next()){
                Evento e = new Evento();
                e.setId(rs.getInt("id_Evento"));
                e.setFecha(rs.getDate("fecha").toLocalDate());
                e.setTipoEvento(TipoEvento.valueOf(rs.getString("tipoEvento")));
                e.setEstadoEvento(EstadoEvento.valueOf(rs.getString("estadoEvento")));
                eventos.add(e);
            }
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return eventos;
    }
   
}
