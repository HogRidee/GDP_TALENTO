package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.gdptalento.eventos.business.EventoBO;
import pe.edu.pucp.gdptalento.miembros.business.PostulanteBO;
import pe.edu.pucp.gdptalento.miembros.business.StaffBO;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pe.edu.pucp.gdptalento.talento.business.TareaBO;


@WebService(serviceName = "InicioWS", targetNamespace = "http://services.pucp.edu.pe")
public class InicioWS {
    private StaffBO boStaff;
    private PostulanteBO boPostulante;
    private EventoBO boEvento;
    private TareaBO boTarea;
    
    @WebMethod(operationName = "devolverResumen")
    public int[] devolverResumen() {
        int totalMiembros   = boStaff.listarTodas().size();
        int totalPostulantes= boPostulante.listarTodas().size();
        int totalEventos    = boEvento.listarTodas().size();
        int totalTareas     = boTarea.listarTodas().size();
        return new int[]{ totalMiembros, totalPostulantes, totalEventos, totalTareas };
    }
}
