using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class VerDetallesPostulante : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                PostulanteWSClient boPostulante = new PostulanteWSClient();

                // El ID viene como string en la URL
                var postulante = (postulante)Session["postulanteSeleccionado"];

                if (postulante != null)
                {
                    lblNombre.Text = postulante.nombre;
                    lblEstado.Text = postulante.status.ToString();
                    string estado = postulante.estadoProceso.ToString().Trim().ToUpper();

                    lblEstado.Text = estado;

                    if (estado == "APROBADO")
                    {
                        lblEstado.CssClass = "estado-activo";
                    }
                    else if (estado == "RECHAZADO")
                    {
                        lblEstado.CssClass = "estado-inactivo";
                    }

                    lblCorreo.Text = postulante.correo;
                    lblTelefono.Text = postulante.telefono;
                    lblCodigo.Text = postulante.codigoPUCP.ToString();
                    lblCarrera.Text = postulante.especialidad;
                    // This is the line that calls your skill loading method
                    LoadSkillsFromListOfStrings(); // Or LoadSkillsFromMiembroObject() if applicable
                    //lblAsistencia.Text = "90%"; // Hardcoded
                    //lblCompletadas.Text = "15"; // Hardcoded
                    //lblPendientes.Text = "2"; // Hardcoded
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