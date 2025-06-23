/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.business.PostulanteBO;
import pe.edu.pucp.gdptalento.miembros.model.Postulante;


@WebService(serviceName = "PostulanteWS", targetNamespace = "http://services.pucp.edu.pe")
public class PostulanteWS {
    private PostulanteBO boPostulante;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "insertarPostulante")
    public int insertarPostulante(@WebParam(name = "postulante") Postulante postulante) {
        boPostulante = new PostulanteBO();
        return boPostulante.insertar(postulante);
    }
    
    @WebMethod(operationName = "modificarPostulante")
    public int modificarPostulante(@WebParam(name = "postulante") Postulante postulante) {
        boPostulante = new PostulanteBO();
        return boPostulante.modificar(postulante);
    }
    
    @WebMethod(operationName = "eliminarPostulante")
    public int eliminarPostulante(@WebParam(name = "idpostulante") int idpostulante) {
        boPostulante = new PostulanteBO();
        return boPostulante.eliminar(idpostulante);
    }
    
    @WebMethod(operationName = "listarPostulantes")
    public ArrayList<Postulante> listarPostulantes() {
        boPostulante = new PostulanteBO();
        return boPostulante.listarTodas();
    }
}
