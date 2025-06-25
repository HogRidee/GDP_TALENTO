using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class ReporteOIE : System.Web.UI.Page
    {
        private ReporteWSClient boReporte;
        protected void Page_Load(object sender, EventArgs e)
        {
            boReporte = new ReporteWSClient();
            byte[] reporteCSV = boReporte.generarReporteOIECSV();

            Response.Clear();
            Response.ContentType = "text/csv";
            Response.AddHeader("Content-Disposition", "attachment;filename=ReporteOIE.csv");
            Response.BinaryWrite(reporteCSV);
            Response.End();
        }
    }
}