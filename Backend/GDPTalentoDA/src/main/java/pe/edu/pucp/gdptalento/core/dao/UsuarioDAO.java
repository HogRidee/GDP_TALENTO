/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.dao;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.core.model.Usuario;

/**
 *
 * @author USER
 */
public interface UsuarioDAO {
    int insertar(Usuario usuario);
    int modificar(Usuario usuario, int id);
    int eliminar(int id_usuario);
    ArrayList<Usuario> listarTodas();
    Usuario obtenerPorId(String nombreRol);
}
