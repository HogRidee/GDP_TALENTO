using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;
namespace GDPTalentoWA.Paginas
{
    public partial class VerDetalles : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

            if (!IsPostBack)
            {
                StaffWSClient boStaff = new StaffWSClient();
                // El ID viene como string en la URL
                string idStr = Request.QueryString["id"];

                if (!string.IsNullOrEmpty(idStr) && int.TryParse(idStr, out int idMiembro))
                {
                    var miembros = boStaff.listarStaff(); // o el método que tengas

                    var miembro = miembros.FirstOrDefault(m => m.id == idMiembro);

                    if (miembro != null)
                    {
                        lblNombre.Text = miembro.nombre;
                        lblCargo.Text = miembro.area.ToString();
                        lblEstado.Text = miembro.estado.ToString();
                        string estado = miembro.estado.ToString().Trim().ToUpper();

                        lblEstado.Text = estado;

                        if (estado == "ACTIVO")
                        {
                            lblEstado.CssClass = "estado-activo";
                        }
                        else if (estado == "INACTIVO")
                        {
                            lblEstado.CssClass = "estado-inactivo";
                        }

                        lblCorreo.Text = miembro.correo;
                        lblTelefono.Text = miembro.telefono;
                        lblCodigo.Text = miembro.codigoPUCP.ToString();
                        lblIngreso.Text = "15/02/2005";
                        lblCarrera.Text = miembro.facultad + " " + miembro.especialidad;
                        lblEvaluacion.Text = miembro.desempenio.ToString();

                    }
                }

            }
        }

    }
}

   