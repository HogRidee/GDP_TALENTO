/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.miembros.dao;

import java.util.ArrayList;
import pe.edu.pucp.gdptalento.miembros.model.Postulante;

/**
 *
 * @author USER
 */
public interface PostulanteDAO {
    int insertar(Postulante postulante);
    int modificar(Postulante postulante);
    int eliminar(int idPostulante);
    ArrayList<Postulante> listarTodos();
}
