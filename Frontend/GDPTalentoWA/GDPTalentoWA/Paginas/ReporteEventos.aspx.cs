using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class ReporteEventos : System.Web.UI.Page
    {
        private ReporteWSClient boReporte;
        protected void Page_Load(object sender, EventArgs e)
        {
            boReporte = new ReporteWSClient();
            byte[] reporte = boReporte.generarReporteEventos();

            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", "inline;filename=ReporteEventos.pdf");
            Response.BinaryWrite(reporte);
            Response.End();
        }
    }
}