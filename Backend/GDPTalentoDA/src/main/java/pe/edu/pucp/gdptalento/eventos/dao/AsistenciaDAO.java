/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.gdptalento.eventos.dao;
import java.util.ArrayList;
import  pe.edu.pucp.gdptalento.eventos.model.Asistencia;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pe.edu.pucp.gdptalento.eventos.model.Evento;

/**
 *
 * @author USER
 */
public interface AsistenciaDAO {
    
    int insertar(Asistencia asistencia);
    int modificar(Asistencia asistencia);
    ArrayList<Asistencia> listarTodas (Staff participante);  // Listar asistencias por staff
    ArrayList<Asistencia> listarTodas (Evento evento);       // Listar asistencias por evento
}
