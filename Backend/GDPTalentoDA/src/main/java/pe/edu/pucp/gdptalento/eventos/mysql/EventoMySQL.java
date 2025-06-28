package pe.edu.pucp.gdptalento.eventos.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.eventos.dao.EventoDAO;
import pe.edu.pucp.gdptalento.eventos.model.EstadoEvento;
import pe.edu.pucp.gdptalento.eventos.model.Evento;
import pe.edu.pucp.gdptalento.eventos.model.TipoEvento;
import pe.edu.pucp.gdptalento.miembros.model.Area;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pucp.edu.pe.gdptalento.config.DBManager;

public class EventoMySQL implements EventoDAO{
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    @Override
    public int insertar(Evento evento) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, new Date(evento.getFecha().getTime()));
        parametrosEntrada.put(2, evento.getTipoEvento().toString());
        parametrosEntrada.put(3, evento.getEstadoEvento().toString());
        int resultado=DBManager.getInstance().ejecutarProcedimiento("INSERTAR_EVENTO", parametrosEntrada, null);
        System.out.println("Se ha realizado el registro del evento");
        return resultado;
    }

    @Override
    public int modificar(Evento evento) {
        System.out.println("Participantes recibidos: " + 
        (evento.getParticipantes() == null ? "null" : evento.getParticipantes().size()));
        System.out.println("Encargados recibidos: " + 
        (evento.getEncargados() == null ? "null" : evento.getEncargados().size()));
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, evento.getId());
        parametrosEntrada.put(2, new Date(evento.getFecha().getTime()));
        parametrosEntrada.put(3, evento.getTipoEvento().toString());
        parametrosEntrada.put(4, evento.getEstadoEvento().toString());
        int resultado=DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_EVENTO", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del evento");
        
        if(evento.getParticipantes()!=null){
            modificarParticipantes(evento.getId(), evento.getParticipantes());
        }
        
        if(evento.getEncargados()!=null){
            modificarEncargados(evento.getId(), evento.getEncargados());
        }
        
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
            if(rs == null){
                System.out.println("ERROR: ResultSet nulo al listar eventos");
                return new ArrayList<Evento>(); // o lo que corresponda
            }
            while(rs.next()){
                Evento e = new Evento();
                e.setId(rs.getInt("id_Evento"));
                e.setFecha(rs.getDate("fecha"));
                e.setTipoEvento(TipoEvento.valueOf(rs.getString("tipoEvento")));
                e.setEstadoEvento(EstadoEvento.valueOf(rs.getString("estadoEvento")));

                try {
                    e.setParticipantes(listarParticipantesPorEvento(e.getId()));
                } catch (Exception ex) {
                    System.out.println("Error al listar participantes para evento " + e.getId() + ": " + ex.getMessage());
                    e.setParticipantes(new ArrayList<>());
                }

                try {
                    e.setEncargados(listarEncargadosPorEvento(e.getId()));
                } catch (Exception ex) {
                    System.out.println("Error al listar encargados para evento " + e.getId() + ": " + ex.getMessage());
                    e.setEncargados(new ArrayList<>());
                }

                eventos.add(e);
            }
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return eventos;
    }
    
    @Override
    public int modificarParticipantes(int idEvento, ArrayList<Staff> participantes) {
        int resultado = 0;

        // 1. Primero eliminamos los participantes actuales
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idEvento);
        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_PARTICIPANTES_EVENTO", parametrosEntrada, null);

        // 2. Luego insertamos los nuevos participantes
        try {
            if (participantes != null) {
                for (Staff s : participantes) {
                    Map<Integer, Object> parametrosInsertar = new HashMap<>();
                    parametrosInsertar.put(1, idEvento);
                    parametrosInsertar.put(2, s.getId());
                    DBManager.getInstance().ejecutarProcedimiento("INSERTAR_PARTICIPANTE_EVENTO", parametrosInsertar, null);
                }
                resultado = 1;
            }
        } catch (Exception ex) {
            System.out.println("Error al modificar participantes: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return resultado;
    }
    
    @Override
    public ArrayList<Staff> listarParticipantesPorEvento(int idEvento) {
        ArrayList<Staff> participantes = new ArrayList<>();

        try {
            Map<Integer, Object> parametros = new HashMap<>();
            parametros.put(1, idEvento);
            ResultSet rs_ = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_PARTICIPANTES_EVENTO", parametros);
            
            if(rs_ == null){
                System.out.println("ERROR: ResultSet nulo al listar participantes");
                return new ArrayList<Staff>(); // o lo que corresponda
            }
            while (rs_.next()) {
                Staff s = new Staff();
                s.setId(rs_.getInt("id_staff"));
//                s.setNombre(rs_.getString("nombre"));
//                s.setCodigoPUCP(Integer.parseInt(rs_.getString("codigo_pucp")));
//                s.setArea(Area.valueOf(rs_.getString("area")));
//                s.setCorreo(rs_.getString("correo"));
                participantes.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar participantes: " + ex.getMessage());
        }
        return participantes;
    }
    
    public ArrayList<Usuario> listarEncargadosPorEvento(int idEvento) {
    ArrayList<Usuario> encargados = new ArrayList<>();

    try {
        Map<Integer, Object> parametros = new HashMap<>();
        parametros.put(1, idEvento);
        ResultSet rs_ = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_ENCARGADOS_EVENTO", parametros);
        
        if(rs_ == null){
            System.out.println("ERROR: ResultSet nulo al listar encargados");
            return new ArrayList<Usuario>(); // o lo que corresponda
        }
        while (rs_.next()) {
            Usuario u = new Usuario();
            u.setId(rs_.getInt("id_usuario"));
//            u.setNombre(rs_.getString("nombre"));
//            u.setCodigoPUCP(Integer.parseInt(rs_.getString("codigo_pucp")));
//            u.setArea(Area.valueOf(rs_.getString("area")));
//            u.setCorreo(rs_.getString("correo"));
            encargados.add(u);
        }
    } catch (SQLException ex) {
        System.out.println("Error al listar encargados: " + ex.getMessage());
    }

    return encargados;
}
    
    @Override
    public int modificarEncargados(int idEvento, ArrayList<Usuario> encargados) {
        int resultado = 0;

        // 1. Eliminar encargados actuales
        Map<Integer, Object> parametrosEliminar = new HashMap<>();
        parametrosEliminar.put(1, idEvento);
        DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_ENCARGADOS_EVENTO", parametrosEliminar, null);

        // 2. Insertar nuevos encargados
        try {
            if (encargados != null) {
                for (Usuario u : encargados) {
                    Map<Integer, Object> parametrosInsertar = new HashMap<>();
                    parametrosInsertar.put(1, idEvento);
                    parametrosInsertar.put(2, u.getId());
                    DBManager.getInstance().ejecutarProcedimiento("INSERTAR_ENCARGADO_EVENTO", parametrosInsertar, null);
                }
                resultado = 1;
            }
        } catch (Exception ex) {
            System.out.println("Error al modificar encargados: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }

        return resultado;
    }

    @Override
    public ArrayList<Evento> listarFuturos() {
        ArrayList<Evento> eventos = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_EVENTOS_FUTUROS", null);
        try {
            while(rs.next()){
                Evento e = new Evento();
                e.setId(rs.getInt("id"));
                e.setFecha(rs.getDate("fecha"));
                e.setTipoEvento(TipoEvento.valueOf(rs.getString("tipoEvento")));
                e.setEstadoEvento(EstadoEvento.valueOf(rs.getString("estadoEvento")));
                eventos.add(e);
            }
        } catch(SQLException ex){
            System.out.println("ERROR al listarFuturos: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
        }
        return eventos;
    }

    @Override
    public Evento obtenerProximo() {
        ArrayList<Evento> futuros = listarFuturos();
        return futuros.isEmpty() ? null : futuros.get(0);
    }


}
