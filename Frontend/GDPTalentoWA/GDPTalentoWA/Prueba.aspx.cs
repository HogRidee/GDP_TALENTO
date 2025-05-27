using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA
{
    public partial class Prueba : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            var client = new ServicioWeb.ServicioWebClient();
            string resultado = client.hello("GDP");
            Response.Write("Respuesta del servicio: " + resultado);
        }

    }
}