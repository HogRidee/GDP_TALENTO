/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.business.StaffBO;
import pe.edu.pucp.gdptalento.miembros.model.Staff;

/**
 *
 * @author USER
 */

@WebService(serviceName = "StaffWS", targetNamespace = "http://services.pucp.edu.pe")
public class StaffWS {
    private StaffBO boStaff;
    
    @WebMethod(operationName = "insertarStaff")
    public int insertarStaff(@WebParam(name = "staff") Staff staff) {
        boStaff = new StaffBO();
        return boStaff.insertar(staff);
    }
    
    @WebMethod(operationName = "modificarStaff")
    public int modificarStaff(@WebParam(name = "staff") Staff staff) {
        boStaff = new StaffBO();
        return boStaff.modificar(staff);
    }
    
    @WebMethod(operationName = "eliminarStaff")
    public int eliminarStaff(@WebParam(name = "idstaff") int idstaff) {
        boStaff = new StaffBO();
        return boStaff.eliminar(idstaff);
    }
    
    @WebMethod(operationName = "listarStaff")
    public ArrayList<Staff> listarStaff() {
        boStaff = new StaffBO();
        return boStaff.listarTodas();
    }
}
