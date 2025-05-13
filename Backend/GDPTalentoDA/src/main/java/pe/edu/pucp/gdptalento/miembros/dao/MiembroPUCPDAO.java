/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.miembros.dao;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.model.MiembroPUCP;

/**
 *
 * @author USER
 */
public interface MiembroPUCPDAO {
    int insertar(MiembroPUCP miembro);
    int modificar(MiembroPUCP miembro);
    int eliminar(int idMiembro);
    ArrayList<MiembroPUCP> listarTodos();
}
