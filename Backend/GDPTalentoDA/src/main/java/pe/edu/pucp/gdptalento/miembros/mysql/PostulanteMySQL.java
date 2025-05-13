/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.miembros.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.gdptalento.miembros.dao.PostulanteDAO;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.model.EstadoProceso;
import pe.edu.pucp.gdptalento.miembros.model.Postulante;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author Dayana
 */
public class PostulanteMySQL implements PostulanteDAO{
    private Connection con;
    private ResultSet rs;
    
    @Override
    public int insertar(Postulante postulante) {
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosSalida.put(1, Types.INTEGER);
        parametrosEntrada.put(2, postulante.getNombre());
        parametrosEntrada.put(3, postulante.getCorreo());
        parametrosEntrada.put(4, postulante.getCodigoPUCP());
        parametrosEntrada.put(5, postulante.getFacultad());
        parametrosEntrada.put(6, postulante.getEspecialidad());
        parametrosEntrada.put(7, postulante.getStatus());
        parametrosEntrada.put(8, postulante.getTelefono());
        parametrosEntrada.put(9, postulante.getEstadoProceso());
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_POSTULANTE", parametrosEntrada, parametrosSalida);
        System.out.println("Se ha realizado el registro del postulante");
        return postulante.getId();
    }

    @Override
    public int modificar(Postulante postulante) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, postulante.getId());
        parametrosEntrada.put(2, postulante.getEstadoProceso());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_POSTULANTE", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del postulante");
        return resultado;
    }

    @Override
    public int eliminar(int idPostulante) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idPostulante);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_POSTULANTE", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del postulante");
        return resultado;
    }

    @Override
    public ArrayList<Postulante> listarTodos() {
        ArrayList<Postulante> postulantes = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_POSTULANTES", null);
        System.out.println("Lectura de postulantes...");
        try{
            while(rs.next()){
                Postulante postulante = new Postulante();
                postulante.setId(rs.getInt("id_miembro_pucp"));
                postulante.setNombre(rs.getString("nombre"));
                postulante.setCorreo(rs.getString("correo"));
                postulante.setCodigoPUCP(rs.getInt("codigoPUCP"));
                postulante.setFacultad(rs.getString("facultad"));
                postulante.setEspecialidad(rs.getString("especialidad"));
                
                String nombre_status= rs.getString("status");
                if(nombre_status.equals("MATRICULADO")){
                    postulante.setStatus(EstadoPUCP.MATRICULADO);
                }else if(nombre_status.equals("NO_MATRICULADO")){
                     postulante.setStatus(EstadoPUCP.NO_MATRICULADO);
                }else if(nombre_status.equals("EGRESADO")){
                    postulante.setStatus(EstadoPUCP.EGRESADO);
                }else if(nombre_status.equals("EXTERNO")){
                    postulante.setStatus(EstadoPUCP.EXTERNO);
                }
                postulante.setTelefono(rs.getString("telefono"));
                
                String nombre_estado_proceso= rs.getString("estado_proceso");
                if(nombre_estado_proceso.equals("PENDIENTE")){
                    postulante.setEstadoProceso(EstadoProceso.PENDIENTE);
                }else if(nombre_estado_proceso.equals("APROBADO")){
                    postulante.setEstadoProceso(EstadoProceso.APROBADO);
                }else if(nombre_estado_proceso.equals("RECHAZADO")){
                    postulante.setEstadoProceso(EstadoProceso.RECHAZADO);
                }
                
                postulantes.add(postulante);
            }
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return postulantes;
    }
    
}
