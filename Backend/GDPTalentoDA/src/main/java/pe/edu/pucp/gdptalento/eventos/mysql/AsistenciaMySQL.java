/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.eventos.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.gdptalento.eventos.dao.AsistenciaDAO;
import pe.edu.pucp.gdptalento.eventos.model.Asistencia;
import pe.edu.pucp.gdptalento.eventos.model.EstadoAsistencia;
import pe.edu.pucp.gdptalento.eventos.model.EstadoEvento;
import pe.edu.pucp.gdptalento.eventos.model.Evento;
import pe.edu.pucp.gdptalento.eventos.model.TipoEvento;
import pe.edu.pucp.gdptalento.miembros.model.Area;
import pe.edu.pucp.gdptalento.miembros.model.EstadoMiembro;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author Dayana
 */
public class AsistenciaMySQL implements AsistenciaDAO{
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    @Override
    public int insertar(Asistencia asistencia) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, asistencia.getEvento().getId());
        parametrosEntrada.put(2, asistencia.getParticipante().getId());
        parametrosEntrada.put(3, asistencia.getAsistencia());
        int resultado=DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ASISTENCIA", parametrosEntrada, null);
        System.out.println("Se ha insertado una asistencia");
        return resultado;
    }

    @Override
    public int modificar(Asistencia asistencia) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, asistencia.getEvento().getId());
        parametrosEntrada.put(2, asistencia.getParticipante().getId());
        parametrosEntrada.put(3, asistencia.getAsistencia());
        int resultado=DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_ASISTENCIA", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion de la asistencia");
        return resultado;
    }

    @Override
    public ArrayList<Asistencia> listarTodas() {
        ArrayList<Asistencia> asistencias = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ASISTENCIA", null);
        System.out.println("Lectura de asistencias...");
        try {
            while(rs.next()) {
                Asistencia a = new Asistencia();

                Staff participante = new Staff();
                Evento evento = new Evento();

                participante.setId(rs.getInt("id_staff"));
                participante.setNombre(rs.getString("nombre")); 
                participante.setTelefono(rs.getString("telefono"));
                participante.setArea(Area.valueOf(rs.getString("area")));

                evento.setId(rs.getInt("id_evento"));
                evento.setFecha(rs.getDate("fecha"));
                //evento.setTipoEvento(TipoEvento.valueOf(rs.getString("tipo")));
                evento.setTipoEvento(TipoEvento.valueOf(rs.getString("tipoEvento")));

                a.setParticipante(participante);
                a.setEvento(evento);
                a.setAsistencia(EstadoAsistencia.valueOf(rs.getString("asistencia")));

                asistencias.add(a);
            }
        } catch(SQLException ex) {
            System.out.println("ERROR SQL: " + ex.getMessage());
            ex.printStackTrace();
        } catch(Exception ex) {
            System.out.println("ERROR General: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return asistencias;
    }

    @Override
    public ArrayList<Asistencia> listarTodasPorID(int id) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        ArrayList<Asistencia> asistencias = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ASISTENCIA_STAFF", parametrosEntrada);
        System.out.println("Lectura de asistencias...");
        try {
            while(rs.next()) {
                Asistencia a = new Asistencia();

                Staff participante = new Staff();
                Evento evento = new Evento();

                participante.setId(rs.getInt("id_staff"));
                participante.setNombre(rs.getString("nombre")); 
                participante.setTelefono(rs.getString("telefono"));
                participante.setArea(Area.valueOf(rs.getString("area")));

                evento.setId(rs.getInt("id_evento"));
                evento.setFecha(rs.getDate("fecha"));
                //evento.setTipoEvento(TipoEvento.valueOf(rs.getString("tipo")));
                evento.setTipoEvento(TipoEvento.valueOf(rs.getString("tipoEvento")));

                a.setParticipante(participante);
                a.setEvento(evento);
                a.setAsistencia(EstadoAsistencia.valueOf(rs.getString("asistencia")));

                asistencias.add(a);
            }
        } catch(SQLException ex) {
            System.out.println("ERROR SQL: " + ex.getMessage());
            ex.printStackTrace();
        } catch(Exception ex) {
            System.out.println("ERROR General: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return asistencias;
    }
}
