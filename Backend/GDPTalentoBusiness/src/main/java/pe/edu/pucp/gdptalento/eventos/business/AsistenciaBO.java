package pe.edu.pucp.gdptalento.eventos.business;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.eventos.dao.AsistenciaDAO;
import pe.edu.pucp.gdptalento.eventos.model.Asistencia;
import pe.edu.pucp.gdptalento.eventos.mysql.AsistenciaMySQL;

public class AsistenciaBO {
     private final AsistenciaDAO daoAsistencia;
    
    public AsistenciaBO(){
        daoAsistencia = new AsistenciaMySQL();
    }
    
    public int insertar(Asistencia asistencia){
        return daoAsistencia.insertar(asistencia);
    }
    
    public int modificar(Asistencia asistencia){
        return daoAsistencia.modificar(asistencia);
    }
    
    public ArrayList<Asistencia> listarTodas(){
        return daoAsistencia.listarTodas();
    }
    
    public ArrayList<Asistencia> listarTodasPorID(int id){
        return daoAsistencia.listarTodasPorID(id);
    }
    
    public int eliminarAsistenciasPorEvento(int id){
        return daoAsistencia.eliminarAsistenciasPorEvento(id);
    }
    
}

