/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.business.MiembroPUCPBO;
import pe.edu.pucp.gdptalento.miembros.model.MiembroPUCP;

/**
 *
 * @author USER
 */

@WebService(serviceName = "MiembroPUCPWS", targetNamespace = "http://services.pucp.edu.pe")
public class MiembroPUCPWS {
    private MiembroPUCPBO boMiembroPUCP;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "insertarMiembroPUCP")
    public int insertarMiembroPUCP(@WebParam(name = "miembroPUCP") MiembroPUCP miembroPUCP) {
        boMiembroPUCP = new MiembroPUCPBO();
        return boMiembroPUCP.insertar(miembroPUCP);
    }
    
    @WebMethod(operationName = "modificarMiembroPUCP")
    public int modificarMiembroPUCP(@WebParam(name = "miembroPUCP") MiembroPUCP miembroPUCP) {
        boMiembroPUCP = new MiembroPUCPBO();
        return boMiembroPUCP.modificar(miembroPUCP);
    }
    
    @WebMethod(operationName = "eliminarMiembroPUCP")
    public int eliminarMiembroPUCP(@WebParam(name = "idmiembroPUCP") int idmiembroPUCP) {
        boMiembroPUCP = new MiembroPUCPBO();
        return boMiembroPUCP.eliminar(idmiembroPUCP);
    }
    
    @WebMethod(operationName = "listarMiembroPUCP")
    public ArrayList<MiembroPUCP> listarMiembroPUCP() {
        boMiembroPUCP = new MiembroPUCPBO();
        return boMiembroPUCP.listarTodas();
    }
}
