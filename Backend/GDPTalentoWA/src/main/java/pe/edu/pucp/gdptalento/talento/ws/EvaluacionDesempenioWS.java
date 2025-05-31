/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.talento.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.talento.business.EvaluacionDesempeñoBO;
import pe.edu.pucp.gdptalento.talento.model.EvaluacionDesempeño;

/**
 *
 * @author USER
 */
@WebService(serviceName = "EvaluacionDesempenioWS")
public class EvaluacionDesempenioWS {
    private EvaluacionDesempeñoBO boEvaluacionDesempenio;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "insertarEvaluacionDesempenio")
    public int insertarEvaluacionDesempenio(@WebParam(name = "evaluacionDesempenio") 
            EvaluacionDesempeño evaluacionDesempenio) {
        boEvaluacionDesempenio = new EvaluacionDesempeñoBO();
        return boEvaluacionDesempenio.insertar(evaluacionDesempenio);
    }
    
    @WebMethod(operationName = "modificarEvaluacionDesempenio")
    public int modificarEvaluacionDesempenio(@WebParam(name = "evaluacionDesempenio") 
            EvaluacionDesempeño evaluacionDesempenio) {
        boEvaluacionDesempenio = new EvaluacionDesempeñoBO();
        return boEvaluacionDesempenio.modificar(evaluacionDesempenio);
    }
    
    @WebMethod(operationName = "eliminarEvaluacionDesempenio")
    public int eliminarEvaluacionDesempenio(@WebParam(name = "idEvaluacion") 
            int idEvaluacion) {
        boEvaluacionDesempenio = new EvaluacionDesempeñoBO();
        return boEvaluacionDesempenio.eliminar(idEvaluacion);
    }
    
    @WebMethod(operationName = "listarEvaluacionDesempenio")
    public ArrayList<EvaluacionDesempeño> listarEvaluacionDesempenio() {
        boEvaluacionDesempenio = new EvaluacionDesempeñoBO();
        return boEvaluacionDesempenio.listarTodas();
    }
}
