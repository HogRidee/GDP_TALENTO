package pe.edu.pucp.gdptalento.miembros.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pe.edu.pucp.gdptalento.miembros.dao.StaffDAO;
import pe.edu.pucp.gdptalento.miembros.model.Area;
import pe.edu.pucp.gdptalento.miembros.model.EstadoMiembro;
import pe.edu.pucp.gdptalento.miembros.model.EstadoPUCP;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author USER
 */
public class StaffMySQL implements StaffDAO {
    
    private Statement st;//no estoy usandolo pq estoy haciendo con preparedStatement
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    
    
    @Override
    public int insertarStaff(Staff staff) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();

        // Salida: id generado
        parametrosSalida.put(1, Types.INTEGER);

        // Entradas
        parametrosEntrada.put(2, staff.getNombre());
        parametrosEntrada.put(3, staff.getCorreo());
        parametrosEntrada.put(4, staff.getCodigoPUCP());
        parametrosEntrada.put(5, staff.getFacultad());
        parametrosEntrada.put(6, staff.getEspecialidad());
        parametrosEntrada.put(7, String.valueOf(staff.getStatus()));
        parametrosEntrada.put(8, String.valueOf(staff.getTelefono()));
        parametrosEntrada.put(9, String.valueOf(staff.getArea())); //
        Date fecha_date=java.sql.Date.valueOf(staff.getFechaIngreso());
        parametrosEntrada.put(10, new java.sql.Date(fecha_date.getTime()));
        parametrosEntrada.put(11, String.valueOf(staff.getEstado()));
        fecha_date=java.sql.Date.valueOf(staff.getFechaSalida());
        parametrosEntrada.put(12, new java.sql.Date(fecha_date.getTime()));
        parametrosEntrada.put(13, staff.getDesempenio());

        // Llamada al procedimiento
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_STAFF", parametrosEntrada, parametrosSalida);

        // Asignar el ID generado
        staff.setId((int) parametrosSalida.get(1));

        System.out.println("Se ha registrado el staff correctamente");
        return staff.getId();
    }

    @Override
    public int modificar(Staff staff) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();

        // Configuración de los parámetros de entrada para el procedimiento almacenado
        parametrosEntrada.put(1, staff.getId()); // ID del Staff
        parametrosEntrada.put(2, staff.getCorreo()); // Correo
        parametrosEntrada.put(3, staff.getFacultad()); // Facultad
        parametrosEntrada.put(4, staff.getEspecialidad()); // Especialidad
        parametrosEntrada.put(5, String.valueOf(staff.getStatus())); // Status
        parametrosEntrada.put(6, staff.getTelefono()); // Teléfono (asumí que es String o int según tu modelo)
        parametrosEntrada.put(7, String.valueOf(staff.getArea())); // Area
        parametrosEntrada.put(8, String.valueOf(staff.getEstado())); // Estado
        String fecha_fin = String.valueOf(staff.getFechaSalida());
        Date fecha = java.sql.Date.valueOf(fecha_fin);
        parametrosEntrada.put(9, new java.sql.Date(fecha.getTime())); // Fecha de salida
        parametrosEntrada.put(10, staff.getDesempenio()); // Desempeño
        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_STAFF", parametrosEntrada, null);
        System.out.println("Se ha realizado la modificación del staff");
        return resultado;
    }


    @Override
    public int eliminar(int id) {
        Map<Integer,Object> parametrosEntrada = new HashMap<>();
        parametrosEntrada.put(1, id);
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ELIMINAR_STAFF", parametrosEntrada, null);
        System.out.println("Se ha realizado la eliminacion del staff con id: "+id);
        return resultado;
    }

    @Override
    public ArrayList<Staff> listarStaff() {
        ArrayList<Staff> listadoStaff = new ArrayList<Staff>();
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("LISTAR_STAFF", null);
        System.out.println("Lectura de Staff...");
        try{
            while(rs.next()){
                String estadoString = rs.getString("status");
                EstadoPUCP estado = EstadoPUCP.valueOf(estadoString);
                String areaString = rs.getString("area");
                Area area = Area.valueOf(areaString);
                String miembroString = rs.getString("estado");
                EstadoMiembro miembro = EstadoMiembro.valueOf(miembroString);
                Staff staff = new Staff();
                staff.setId(rs.getInt("id_miembro_pucp"));
                staff.setNombre(rs.getString("nombre"));
                staff.setCorreo(rs.getString("correo"));
                staff.setCodigoPUCP(rs.getInt("codigoPUCP"));
                staff.setFacultad(rs.getString("facultad"));
                staff.setEspecialidad(rs.getString("especialidad"));
                staff.setTelefono(rs.getString("telefono"));
                staff.setDesempenio(rs.getDouble("desempenio"));
                staff.setStatus(estado);
                staff.setArea(area);
                staff.setEstado(miembro);
                listadoStaff.add(staff);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            DBManager.getInstance().cerrarConexion();
        }
        return listadoStaff;
    }

    @Override
    public Staff obtenerPorId(int idStaff) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
