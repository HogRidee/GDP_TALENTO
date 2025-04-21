/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.eventos.dao;
import pe.edu.pucp.gdptalento.eventos.model.Evento;
import java.util.ArrayList;


/**
 *
 * @author USER
 */
public interface EventoDAO {
    int insertar(Evento evento);
    int modificar(Evento evento);
    int eliminar(Evento evento);
    ArrayList<Evento> listarTodos();
}
