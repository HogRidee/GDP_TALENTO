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
                var miembro = (staff)Session["miembroSeleccionado"];

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
                    lblIngreso.Text = miembro.fechaIngreso.ToString("dd/MM/yyyy");
                    lblCarrera.Text = miembro.especialidad;
                    lblEvaluacion.Text = miembro.desempenio.ToString();
                    // This is the line that calls your skill loading method
                    LoadSkillsFromListOfStrings(); // Or LoadSkillsFromMiembroObject() if applicable
                    lblAsistencia.Text = "90%"; // Hardcoded
                    lblCompletadas.Text = "15"; // Hardcoded
                    lblPendientes.Text = "2"; // Hardcoded
                }
            }
        }

        private void LoadSkillsFromListOfStrings()
        {
            // 1. Create your data source (a list of skill names)
            List<string> skills = new List<string>
            {
                "JavaScript",
                "React",
                "Node.js",
                "MongoDB",
                "HTML5",
                "CSS3",
                "C#"
            };

            // 2. Set the Repeater's DataSource
            rptHabilidades.DataSource = skills;

            // 3. Bind the data to the Repeater
            rptHabilidades.DataBind();
        }

    }
}

   