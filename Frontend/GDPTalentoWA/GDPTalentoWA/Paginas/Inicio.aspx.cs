using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;

namespace GDPTalentoWA.Paginas
{
    public partial class Inicio : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                using (var client = new InicioWSClient())
                {
                    var datos = client.devolverResumen();
                    litTotalMiembros.Text = datos.Length > 0 ? datos[0]?.ToString() : "0";
                    litPostulantesActivos.Text = datos.Length > 1 ? datos[1]?.ToString() : "0";
                    litEventosProximos.Text = datos.Length > 2 ? datos[2]?.ToString() : "0";
                    litTareasPendientes.Text = datos.Length > 3 ? datos[3]?.ToString() : "0";
                }

            }
        }
    }
}