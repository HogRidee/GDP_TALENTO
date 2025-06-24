/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.gdptalento.eventos.dao;
import pe.edu.pucp.gdptalento.eventos.model.Evento;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.core.model.Usuario;
import pe.edu.pucp.gdptalento.miembros.model.Staff;


/**
 *
 * @author USER
 */
public interface EventoDAO {
    int insertar(Evento evento);
    int modificar(Evento evento);
    int eliminar(Evento evento);
    ArrayList<Evento> listarTodos();
    int modificarParticipantes(int idEvento, ArrayList<Staff> participantes);
    
    ArrayList<Staff> listarParticipantesPorEvento(int idEvento);
    int modificarEncargados(int idEvento, ArrayList<Usuario> encargados);
}

