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
        try{
            while(rs.next()){
                Asistencia a = new Asistencia();
                a.getParticipante().setId(rs.getInt("id_staff"));
                a.getParticipante().setNombre("nombre");
                a.getParticipante().setNombre("telefono");
                a.getParticipante().setArea(Area.valueOf(rs.getString("area")));
                a.setAsistencia(EstadoAsistencia.valueOf(rs.getString("asistencia")));
                a.getEvento().setId(rs.getInt("id_evento"));
                a.getEvento().setFecha(rs.getDate("fecha").toLocalDate());
                a.getEvento().setTipoEvento(TipoEvento.valueOf(rs.getString("tipoEvento")));
                a.getEvento().setEstadoEvento(EstadoEvento.valueOf(rs.getString("estadoEvento")));
                asistencias.add(a);
            }
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return asistencias;
    }
    
}
