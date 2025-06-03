/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.talento.business.EntrevistaBO;
import pe.edu.pucp.gdptalento.talento.model.Entrevista;

/**
 *
 * @author USER
 */

@WebService(serviceName = "EntrevistaWS", targetNamespace = "http://services.pucp.edu.pe")
public class EntrevistaWS {
    private EntrevistaBO boEntrevista;

    @WebMethod(operationName = "insertarEntrevista")
    public int insertarEntrevista(@WebParam(name = "entrevista") Entrevista entrevista) {
        boEntrevista = new EntrevistaBO();
        return boEntrevista.insertar(entrevista);
    }
    
    @WebMethod(operationName = "modificarEntrevista")
    public int modificarEntrevista(@WebParam(name = "entrevista") Entrevista entrevista) {
        boEntrevista = new EntrevistaBO();
        return boEntrevista.modificar(entrevista);
    }
    
    @WebMethod(operationName = "eliminarEntrevista")
    public int eliminarEntrevista(@WebParam(name = "idEntrevista") int idEntrevista) {
        boEntrevista = new EntrevistaBO();
        return boEntrevista.eliminar(idEntrevista);
    }
    
    @WebMethod(operationName = "listarEntrevistas")
    public ArrayList<Entrevista> listarEntrevistas() {
        boEntrevista = new EntrevistaBO();
        return boEntrevista.listarTodas();
    }
}
