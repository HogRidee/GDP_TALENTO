/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.eventos.business.EventoBO;
import pe.edu.pucp.gdptalento.eventos.model.Evento;

/**
 *
 * @author USER
 */

@WebService(serviceName = "EventoWS")
public class EventoWS {
    private EventoBO boEvento;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "insertarEvento")
    public int insertarEvento(@WebParam(name = "evento") Evento evento) {
        boEvento = new EventoBO();
        return boEvento.insertar(evento);
    }
    
    @WebMethod(operationName = "modificarEvento")
    public int modificarEvento(@WebParam(name = "evento") Evento evento) {
        boEvento = new EventoBO();
        return boEvento.modificar(evento);
    }
    
    @WebMethod(operationName = "eliminarEvento")
    public int eliminarEvento(@WebParam(name = "evento") Evento evento) {
        boEvento = new EventoBO();
        return boEvento.eliminar(evento);
    }
    
    @WebMethod(operationName = "listarEventos")
    public ArrayList<Evento> listarEventos() {
        boEvento = new EventoBO();
        return boEvento.listarTodas();
    }
}
