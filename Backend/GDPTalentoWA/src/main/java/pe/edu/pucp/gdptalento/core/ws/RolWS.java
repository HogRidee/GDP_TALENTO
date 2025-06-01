/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.core.business.RolBO;
import pe.edu.pucp.gdptalento.core.model.Rol;

/**
 *
 * @author USER
 */

@WebService(serviceName = "RolWS")
public class RolWS {
    private RolBO boRol;

    @WebMethod(operationName = "insertarRol")
    public int insertarRol(@WebParam(name = "rol") Rol rol) {
        boRol = new RolBO();
        return boRol.insertar(rol);
    }
    
    @WebMethod(operationName = "modificarRol")
    public int modificarRol(@WebParam(name = "rol") Rol rol) {
        boRol = new RolBO();
        return boRol.modificar(rol);
    }
    
    @WebMethod(operationName = "eliminarRol")
    public int eliminarRol(@WebParam(name = "idRol") int idRol) {
        boRol = new RolBO();
        return boRol.eliminar(idRol);
    }
    
    @WebMethod(operationName = "listarRoles")
    public ArrayList<Rol> listarRoles() {
        boRol = new RolBO();
        return boRol.listarTodas();
    }
}
