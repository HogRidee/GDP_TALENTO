package pe.edu.pucp.gdptalento.core.business;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.core.dao.UsuarioDAO;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.core.mysql.UsuarioMySQL;

public class UsuarioBO {
    private final UsuarioDAO daoUsuario;
    
    public UsuarioBO(){
        daoUsuario = new UsuarioMySQL();
    }
    
    public int insertar(Usuario usuario){
        return daoUsuario.insertar(usuario);
    }
    
    public int modificar(Usuario usuario){
        return daoUsuario.modificar(usuario);
    }
    public int modificarBasico(Usuario usuario){
        return daoUsuario.modificarDatosBasicos(usuario);
    }
    
    public int eliminar(int idUsuario){
        return daoUsuario.eliminar(idUsuario);
    }
    
    public Usuario obtenerPorId(int idUsuario){
        return daoUsuario.obtenerPorId(idUsuario);
    }
    
    public ArrayList<Usuario> listarTodas(){
        return daoUsuario.listarTodas();
    }
    
    public int verificarUsuario(int id, String contrasenha){
        ArrayList<Usuario> arr=new ArrayList<>(daoUsuario.listarTodas());
        for( Usuario u: arr){
            if(u.getId()== id){
                if(u.getHashContrasena().equals(contrasenha)){
                    return id;
                }
            }
        }
        return 0;
    }
    
    public Usuario obtenerPorID(int id){
        return daoUsuario.obtenerPorId(id);
    }
    
}
