/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.eventos.business.AsistenciaBO;
import pe.edu.pucp.gdptalento.eventos.model.Asistencia;

/**
 *
 * @author USER
 */
import jakarta.ejb.Stateless;
@Stateless
@WebService(serviceName = "AsistenciaWS")
public class AsistenciaWS {

    private AsistenciaBO boAsistencia;
    
    @WebMethod(operationName = "insertarAsistencia")
    public int insertarAsistencia(@WebParam(name = "Asistencia") Asistencia asistencia) {      
        boAsistencia = new AsistenciaBO();
        return boAsistencia.insertar(asistencia);
    }
    
    @WebMethod(operationName = "modificarAsistencia")
    public int modificarAsistencia(@WebParam(name = "Asistencia") Asistencia asistencia) {      
        boAsistencia = new AsistenciaBO();
        return boAsistencia.modificar(asistencia);
    }
    
    @WebMethod(operationName = "modificarAsistencia")
    public ArrayList<Asistencia> listarAsistencias() {      
        boAsistencia = new AsistenciaBO();
        return boAsistencia.listarTodas();
    }
    
}
