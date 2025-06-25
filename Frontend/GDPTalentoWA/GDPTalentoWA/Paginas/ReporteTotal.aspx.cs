using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class ReporteTotal : System.Web.UI.Page
    {
        private ReporteWSClient boReporte;
        protected void Page_Load(object sender, EventArgs e)
        {
            boReporte = new ReporteWSClient();
            byte[] reporte = boReporte.generarReporteTotal();

            Response.Clear();
            Response.ContentType = "application/pdf";
            Response.AddHeader("Content-Disposition", "inline;filename=ReporteStaff.pdf");
            Response.BinaryWrite(reporte);
            Response.End();
        }
    }
}