/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.core.business.UsuarioBO;
import pe.edu.pucp.gdptalento.core.model.Usuario;

/**
 *
 * @author USER
 */

@WebService(serviceName = "UsuarioWS", targetNamespace = "http://services.pucp.edu.pe")
public class UsuarioWS {
    private UsuarioBO boUsuario;
    
    @WebMethod(operationName = "insertarUsuario")
    public int insertarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        boUsuario=new UsuarioBO();
        return boUsuario.insertar(usuario);
    }
    @WebMethod(operationName = "modificarUsuario")
    public int modificarUsuario(@WebParam(name = "usuario") Usuario usuario) {
        boUsuario=new UsuarioBO();
        return boUsuario.modificar(usuario);
    }
    
    @WebMethod(operationName = "eliminarUsuario")
    public int eliminarUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        boUsuario=new UsuarioBO();
        return boUsuario.eliminar(idUsuario);
    }
    @WebMethod(operationName = "listarUsuarios")
    public ArrayList<Usuario> listarUsuarios() {
        boUsuario=new UsuarioBO();
        return boUsuario.listarTodas();
    }
    @WebMethod(operationName = "obtenerPorId")
    public Usuario obtenerPorId(@WebParam(name = "id") int id) {
        boUsuario=new UsuarioBO();
        return boUsuario.obtenerPorId(id);
    }
    
    @WebMethod(operationName = "verificar")
    public int verificar(@WebParam(name = "codigo") int codigo, @WebParam(name = "contrasenha") String contrasenha) {
        boUsuario=new UsuarioBO();
        return boUsuario.verificarUsuario(codigo, contrasenha);
    }
    
}
