/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.edu.pucp.gdptalento.servelts;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Image;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pucp.edu.pe.gdptalento.config.DBManager;

/**
 *
 * @author USER
 */
public class ReportePostulantes extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //Referenciamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/pe/edu/pucp/gdptalento/reportes/ReportePostulantes.jasper"));
            //Establecemos los parametros que necesita el reporte
            HashMap parametros = new HashMap();
            //Referenciamos la imagen del logo y los subreportes
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/gdptalento/reportes/LOGO_GDP.png");
            URL subReporteEntrevistador = getClass().getResource("/pe/edu/pucp/gdptalento/reportes/EntrevistaXPostulante.jasper");
            //Generamos los objetos necesarios en el reporte
            String rutaSubreporteEntrevistador = URLDecoder.decode(subReporteEntrevistador.getPath(), "UTF-8");
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            //Colocamos los parámetros
            parametros.put("Logo", logo);
            parametros.put("rutaSubReporteEntrevistaXPostulante",rutaSubreporteEntrevistador);
            //Establecemos la conexión
            Connection con = DBManager.getInstance().getConnection();
            //Poblamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            //Mostramos por pantalla
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
