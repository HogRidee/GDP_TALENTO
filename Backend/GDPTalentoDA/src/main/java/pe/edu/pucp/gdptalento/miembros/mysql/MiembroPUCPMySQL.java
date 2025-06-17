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
import pe.edu.pucp.gdptalento.miembros.dao.MiembroPUCPDAO;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.model.MiembroPUCP;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author Dayana
 */
public class MiembroPUCPMySQL implements MiembroPUCPDAO{
    private Connection con;
    private ResultSet rs;
    
    @Override
    public int insertar(MiembroPUCP miembro) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();

        // Salida: ID generado
        parametrosSalida.put(1, Types.INTEGER);

        // Entradas
        parametrosEntrada.put(2, miembro.getNombre());
        parametrosEntrada.put(3, miembro.getCorreo());
        parametrosEntrada.put(4, miembro.getCodigoPUCP());
        parametrosEntrada.put(5, miembro.getFacultad());
        parametrosEntrada.put(6, miembro.getEspecialidad());
        parametrosEntrada.put(7, miembro.getStatus() != null ? miembro.getStatus().name() : null);
        parametrosEntrada.put(8, miembro.getTelefono());

        // Ejecutar SP
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_MIEMBROPUCP", parametrosEntrada, parametrosSalida);

        // Asignar ID generado
        miembro.setId((int) parametrosSalida.get(1));

        System.out.println("✅ Se ha realizado el registro del miembro PUCP");
        return miembro.getId();
    }


    @Override
    public int modificar(MiembroPUCP miembro) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, miembro.getId());
        parametrosEntrada.put(2, miembro.getNombre());
        parametrosEntrada.put(3, miembro.getCorreo());
        parametrosEntrada.put(4, miembro.getCodigoPUCP());
        parametrosEntrada.put(5, miembro.getFacultad());
        parametrosEntrada.put(6, miembro.getEspecialidad());
        parametrosEntrada.put(7, String.valueOf(miembro.getStatus()));
        parametrosEntrada.put(8, miembro.getTelefono());
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_MIEMBROPUCP", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificacion del miembro PUCP");
        return resultado;
    }

    @Override
    public int eliminar(int idMiembro) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, idMiembro);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_MIEMBROPUCP", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del miembro PUCP");
        return resultado;
    }

    @Override
    public ArrayList<MiembroPUCP> listarTodos() {
        ArrayList<MiembroPUCP> miembros = new ArrayList<>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_MIEMBROPUCP", null);
        System.out.println("Lectura de miembros PUCP...");
        try{
            while(rs.next()){
                MiembroPUCP miembro = new MiembroPUCP();
                miembro.setId(rs.getInt("id_miembro_pucp"));
                miembro.setNombre(rs.getString("nombre"));
                miembro.setCorreo(rs.getString("correo"));
                miembro.setCodigoPUCP(rs.getInt("codigoPUCP"));
                miembro.setFacultad(rs.getString("facultad"));
                miembro.setEspecialidad(rs.getString("especialidad"));
                
                String nombre_status= rs.getString("status");
                if(nombre_status.equals("MATRICULADO")){
                    miembro.setStatus(EstadoPUCP.MATRICULADO);
                }else if(nombre_status.equals("NO_MATRICULADO")){
                     miembro.setStatus(EstadoPUCP.NO_MATRICULADO);
                }else if(nombre_status.equals("EGRESADO")){
                    miembro.setStatus(EstadoPUCP.EGRESADO);
                }else if(nombre_status.equals("EXTERNO")){
                    miembro.setStatus(EstadoPUCP.EXTERNO);
                }
                miembro.setTelefono(rs.getString("telefono"));
                miembros.add(miembro);
            }
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return miembros;
    }
    
}
