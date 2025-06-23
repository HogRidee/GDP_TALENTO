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

import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

/**
 *
 * @author USER
 */
public class ReporteOIE extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(
                getClass().getResourceAsStream("/pe/edu/pucp/gdptalento/reportes/ReporteOIE.jasper"));

            HashMap parametros = new HashMap();
            Connection con = DBManager.getInstance().getConnection();
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"ReporteOIE.csv\"");

            JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
            JRCsvExporter exporter = new JRCsvExporter(jasperReportsContext);
            exporter.setExporterInput(new SimpleExporterInput(jp));
            exporter.setExporterOutput(new SimpleWriterExporterOutput(response.getOutputStream()));

            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
            configuration.setFieldDelimiter(",");
            exporter.setConfiguration(configuration);

            exporter.exportReport();
            // --------------------------
        } catch (Exception ex) {
            System.out.println("Error al exportar CSV: " + ex.getMessage());
        } finally {
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
