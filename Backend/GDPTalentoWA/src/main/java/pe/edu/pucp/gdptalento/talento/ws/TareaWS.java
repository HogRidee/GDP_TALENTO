/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.talento.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.talento.business.TareaBO;
import pe.edu.pucp.gdptalento.talento.model.Tarea;

/**
 *
 * @author USER
 */
@WebService(serviceName = "TareaWS")
public class TareaWS {
    private TareaBO boTarea;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "insertarTarea")
    public int insertarTarea(@WebParam(name = "tarea") Tarea tarea) {
        boTarea = new TareaBO();
        return boTarea.insertar(tarea);
    }
    
    @WebMethod(operationName = "modificarTarea")
    public int modificarTarea(@WebParam(name = "tarea") Tarea tarea) {
        boTarea = new TareaBO();
        return boTarea.modificar(tarea);
    }
    
    @WebMethod(operationName = "eliminarTarea")
    public int eliminarTarea(@WebParam(name = "idTarea") int idTarea) {
        boTarea = new TareaBO();
        return boTarea.eliminar(idTarea);
    }
            
    @WebMethod(operationName = "listarTareas")
    public ArrayList<Tarea> listarTareas() {
        boTarea = new TareaBO();
        return boTarea.listarTodas();
    }
}
