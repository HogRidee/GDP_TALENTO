/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.gdptalento.core.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

/**
 *
 * @author USER
 */
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.awt.Image;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pucp.edu.pe.gdptalento.config.DBManager;

import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebService(serviceName = "ReporteWS",
        targetNamespace = "http://services.pucp.edu.pe")
public class ReporteWS {

    @WebMethod(operationName = "generarReportePostulantes")
    public byte[] generarReportePostulantes() {
        byte[] reporte = null;
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
            reporte = JasperExportManager.exportReportToPdf(jp);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return reporte;
    }
    /////////////////////////////Reportes Staff ////////////////////////////
    ///
    @WebMethod(operationName = "generarReporteEventos")
    public byte[] generarReporteEventos() {
        byte[] reporte = null;
        try{
            //Referenciamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/pe/edu/pucp/gdptalento/reportes/ReporteEventos.jasper"));
            //Establecemos los parametros que necesita el reporte
            HashMap parametros = new HashMap();
            //Referenciamos la imagen del logo y los subreportes
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/gdptalento/reportes/LOGO_GDP.png");
            //Generamos los objetos necesarios en el reporte
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            //Colocamos los parámetros
            parametros.put("Logo", logo);
            //Establecemos la conexión
            Connection con = DBManager.getInstance().getConnection();
            //Poblamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            //Mostramos por pantalla
            reporte = JasperExportManager.exportReportToPdf(jp);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return reporte;
    }
    
    @WebMethod(operationName = "generarReporteGDPAcademy")
    public byte[] generarReporteGDPAcademy() {
        byte[] reporte = null;
        try{
            //Referenciamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/pe/edu/pucp/gdptalento/reportes/ReporteGDPAcademy.jasper"));
            //Establecemos los parametros que necesita el reporte
            HashMap parametros = new HashMap();
            //Referenciamos la imagen del logo y los subreportes
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/gdptalento/reportes/LOGO_GDP.png");
            //Generamos los objetos necesarios en el reporte
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            //Colocamos los parámetros
            parametros.put("Logo", logo);
            //Establecemos la conexión
            Connection con = DBManager.getInstance().getConnection();
            //Poblamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            //Mostramos por pantalla
            reporte = JasperExportManager.exportReportToPdf(jp);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return reporte;
    }
    
    @WebMethod(operationName = "generarReporteMarketing")
    public byte[] generarReporteMarketing() {
        byte[] reporte = null;
        try{
            //Referenciamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/pe/edu/pucp/gdptalento/reportes/ReporteMarketing.jasper"));
            //Establecemos los parametros que necesita el reporte
            HashMap parametros = new HashMap();
            //Referenciamos la imagen del logo y los subreportes
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/gdptalento/reportes/LOGO_GDP.png");
            //Generamos los objetos necesarios en el reporte
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            //Colocamos los parámetros
            parametros.put("Logo", logo);
            //Establecemos la conexión
            Connection con = DBManager.getInstance().getConnection();
            //Poblamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            //Mostramos por pantalla
            reporte = JasperExportManager.exportReportToPdf(jp);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return reporte;
    }
    
    @WebMethod(operationName = "generarReportePresidencia")
    public byte[] generarReportePresidencia() {
        byte[] reporte = null;
        try{
            //Referenciamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/pe/edu/pucp/gdptalento/reportes/ReportePresidencia.jasper"));
            //Establecemos los parametros que necesita el reporte
            HashMap parametros = new HashMap();
            //Referenciamos la imagen del logo y los subreportes
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/gdptalento/reportes/LOGO_GDP.png");
            //Generamos los objetos necesarios en el reporte
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            //Colocamos los parámetros
            parametros.put("Logo", logo);
            //Establecemos la conexión
            Connection con = DBManager.getInstance().getConnection();
            //Poblamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            //Mostramos por pantalla
            reporte = JasperExportManager.exportReportToPdf(jp);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return reporte;
    }
     @WebMethod(operationName = "generarReporteRecursosHumanos")
    public byte[] generarReporteRecursosHumanos() {
        byte[] reporte = null;
        try{
            //Referenciamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/pe/edu/pucp/gdptalento/reportes/ReporteRecursosHumanos.jasper"));
            //Establecemos los parametros que necesita el reporte
            HashMap parametros = new HashMap();
            //Referenciamos la imagen del logo y los subreportes
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/gdptalento/reportes/LOGO_GDP.png");
            //Generamos los objetos necesarios en el reporte
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            //Colocamos los parámetros
            parametros.put("Logo", logo);
            //Establecemos la conexión
            Connection con = DBManager.getInstance().getConnection();
            //Poblamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            //Mostramos por pantalla
            reporte = JasperExportManager.exportReportToPdf(jp);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return reporte;
    }
     @WebMethod(operationName = "generarReporteTotal")
    public byte[] generarReporteTotal() {
        byte[] reporte = null;
        try{
            //Referenciamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/pe/edu/pucp/gdptalento/reportes/ReporteArea.jasper"));
            //Establecemos los parametros que necesita el reporte
            HashMap parametros = new HashMap();
            //Referenciamos la imagen del logo y los subreportes
            URL rutaLogo = getClass().getResource("/pe/edu/pucp/gdptalento/reportes/LOGO_GDP.png");
            //Generamos los objetos necesarios en el reporte
            Image logo = (new ImageIcon(rutaLogo)).getImage();
            //Colocamos los parámetros
            parametros.put("Logo", logo);
            //Establecemos la conexión
            Connection con = DBManager.getInstance().getConnection();
            //Poblamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);
            //Mostramos por pantalla
            reporte = JasperExportManager.exportReportToPdf(jp);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            DBManager.getInstance().cerrarConexion();
        }
        return reporte;
    }
    
    /////////////////////////////Reportes OIE ////////////////////////////
    @WebMethod(operationName = "generarReporteOIECSV")
    public byte[] generarReporteOIECSV() {
        byte[] reporte = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // Cargamos el archivo Jasper
            JasperReport jr = (JasperReport) JRLoader.loadObject(
                getClass().getResourceAsStream("/pe/edu/pucp/gdptalento/reportes/ReporteOIE.jasper"));

            // Parámetros (vacío en este caso)
            HashMap parametros = new HashMap<>();

            // Conexión a la base de datos
            Connection con = DBManager.getInstance().getConnection();

            // Llenamos el reporte
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);

            // Exportamos a CSV
            JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
            JRCsvExporter exporter = new JRCsvExporter(jasperReportsContext);
            exporter.setExporterInput(new SimpleExporterInput(jp));
            exporter.setExporterOutput(new SimpleWriterExporterOutput(baos));

            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
            configuration.setFieldDelimiter(",");
            exporter.setConfiguration(configuration);

            exporter.exportReport();
            reporte = baos.toByteArray();
        } catch (Exception ex) {
            System.out.println("Error al exportar CSV: " + ex.getMessage());
        } finally {
            DBManager.getInstance().cerrarConexion();
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reporte;
    }
}
