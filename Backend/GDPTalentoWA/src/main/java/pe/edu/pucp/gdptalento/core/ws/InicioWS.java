package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.gdptalento.eventos.business.EventoBO;
import pe.edu.pucp.gdptalento.miembros.business.PostulanteBO;
import pe.edu.pucp.gdptalento.miembros.business.StaffBO;
import pe.edu.pucp.gdptalento.miembros.model.Staff;
import pe.edu.pucp.gdptalento.talento.business.EntrevistaBO;
import pe.edu.pucp.gdptalento.talento.business.TareaBO;


@WebService(serviceName = "InicioWS", targetNamespace = "http://services.pucp.edu.pe")
public class InicioWS {
    private StaffBO boStaff = new StaffBO();
    private PostulanteBO boPostulante = new PostulanteBO();
    private EventoBO boEvento = new EventoBO();
    private TareaBO boTarea = new TareaBO();
    private EntrevistaBO boEntrevista = new EntrevistaBO();
    
    @WebMethod(operationName = "devolverTotales")
    public int[] devolverTotales() {
        int totalMiembros   = boStaff.listarTodas().size();
        int totalPostulantes= boPostulante.listarTodas().size();
        int totalEventos    = boEvento.listarTodas().size();
        int totalTareas     = boTarea.listarTodas().size();
        return new int[]{ totalMiembros, totalPostulantes, totalEventos, totalTareas };
    }
    
    @WebMethod(operationName="devolverVariacionMiembros")
    public int devolverVariacionMiembros() {
        int varMiembros = boStaff.calcularVariacionMes();
        return varMiembros;
    }
    
    @WebMethod(operationName="devolverProximaEntrevista")
    public String devolverProximaEntrevista(){
      Date d = boEntrevista.obtenerFechaProximaEntrevista();
      if(d==null) return "";  
      // Formatear a "dd/MM"
      return new java.text.SimpleDateFormat("dd'/'MM").format(d);
    }
}
