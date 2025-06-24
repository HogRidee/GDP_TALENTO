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
    
    private Statement st;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    
    @Override
    public int insertarStaff(Staff staff) {
        Map<Integer, Object> parametrosSalida = new HashMap<>();
        Map<Integer, Object> parametrosEntrada = new HashMap<>();


        // 🔸 Parámetros IN (posición 2 en adelante)
        parametrosEntrada.put(1, staff.getNombre());
        parametrosEntrada.put(2, staff.getCorreo());
        parametrosEntrada.put(3, staff.getCodigoPUCP());
        parametrosEntrada.put(4, staff.getFacultad());
        parametrosEntrada.put(5, staff.getEspecialidad());

        // Enums convertidos a texto con .name(), con verificación null
        parametrosEntrada.put(6, staff.getStatus() != null ? staff.getStatus().name() : null);
        parametrosEntrada.put(7, staff.getTelefono());
        parametrosEntrada.put(8, staff.getArea() != null ? staff.getArea().name() : null);

        // Fechas convertidas a java.sql.Date, verificando null
        parametrosEntrada.put(9, staff.getFechaIngreso() != null ? new java.sql.Date(staff.getFechaIngreso().getTime()) : null);
        parametrosEntrada.put(10, staff.getEstado() != null ? staff.getEstado().name() : null);
        parametrosEntrada.put(11, staff.getFechaSalida() != null ? new java.sql.Date(staff.getFechaSalida().getTime()) : null);

        // Double: desempeño
        parametrosEntrada.put(12, staff.getDesempenio());
        // 🔸 Parámetro OUT en posición 1
        parametrosSalida.put(13, Types.INTEGER);

        // Ejecutar el procedimiento
        DBManager.getInstance().ejecutarProcedimiento("INSERTAR_STAFF_", parametrosEntrada, parametrosSalida);

        // Recuperar el ID generado
        int idGenerado = (int) parametrosSalida.get(13);
        staff.setId(idGenerado);

        System.out.println("✅ Se ha registrado el staff correctamente");
        System.out.println("🆔 Staff insertado correctamente. ID generado: " + idGenerado);

        return idGenerado;
    }

    @Override
    public int modificar(Staff staff) {
        Map<Integer, Object> parametrosEntrada = new HashMap<>();

        // Parámetros según orden del procedimiento almacenado (sin fecha_ingreso)
        parametrosEntrada.put(1, staff.getId()); // ID del Staff
        parametrosEntrada.put(2, staff.getCorreo()); // Correo
        parametrosEntrada.put(3, staff.getFacultad()); // Facultad
        parametrosEntrada.put(4, staff.getEspecialidad()); // Especialidad

        // Enums a String
        parametrosEntrada.put(5, staff.getStatus() != null ? staff.getStatus().name() : null); // Status
        parametrosEntrada.put(6, staff.getTelefono()); // Teléfono
        parametrosEntrada.put(7, staff.getArea() != null ? staff.getArea().name() : null);     // Área
        parametrosEntrada.put(8, staff.getEstado() != null ? staff.getEstado().name() : null); // Estado

        // Fecha de salida (puede ser null)
        parametrosEntrada.put(9, staff.getFechaSalida() != null ? new Date(staff.getFechaSalida().getTime()) : null);

        // Desempeño
        parametrosEntrada.put(10, staff.getDesempenio());
        parametrosEntrada.put(11, staff.getNombre());
        parametrosEntrada.put(12, staff.getCodigoPUCP());

        int resultado = DBManager.getInstance().ejecutarProcedimiento("MODIFICAR_STAFF", parametrosEntrada, null);

        System.out.println("✅ Se ha realizado la modificación del staff");
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
                staff.setFechaIngreso(rs.getDate("fecha_ingreso"));
                staff.setFechaSalida(rs.getDate("fecha_salida"));
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
    
    @Override
    public int calcularVariacionMes(){
        Map<Integer,Object> parametrosSalida = new HashMap<>();
        parametrosSalida.put(1, java.sql.Types.INTEGER);
        DBManager.getInstance()
                 .ejecutarProcedimiento("SP_VARIACION_MIEMBROS", null, parametrosSalida);
        return (int) parametrosSalida.get(1);
    }
    
}
