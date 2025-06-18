using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;

namespace GDPTalentoWA.Paginas
{
    public partial class RegistrarNuevoPostulante : System.Web.UI.Page
    {
        private Estado estado;
        private postulante postulante;
        private PostulanteWSClient boPostulante;

        protected Estado EstadoActual
        {
            get => (Estado)(ViewState["Estado"] ?? Estado.Nuevo);
            set => ViewState["Estado"] = value;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string accion = Request.QueryString["accion"];

                if (accion == null)
                {
                    EstadoActual = Estado.Nuevo;
                    postulante = new postulante();
                    lblTitulo.Text = "Registrar Nuevo Postulante";
                }
                else if (accion == "modificar")
                {
                    EstadoActual = Estado.Modificar;
                    lblTitulo.Text = "Editar Postulante";

                    postulante = (postulante)Session["postulanteSeleccionado"];

                    if (postulante != null)
                    {
                        txtNombreCompleto.Text = postulante.nombre;
                        txtCorreoElectronico.Text = postulante.correo;
                        txtCodigoPUCP.Text = postulante.codigoPUCP.ToString();
                        txtNumeroContacto.Text = postulante.telefono;
                        ddlFacultad.SelectedValue = postulante.facultad;
                        ddlEspecialidad.SelectedValue = postulante.especialidad;
                        ddlEstadoAcademico.SelectedValue = postulante.status.ToString();
                    }
                    Session["postulanteSeleccionado"] = postulante;
                }
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boPostulante= new PostulanteWSClient();

            if (EstadoActual == Estado.Nuevo)
            {
                postulante = new postulante();
            }
            else
            {
                // Validación extra para evitar null
                if (Session["postulanteSeleccionado"] is postulante m)
                {
                    postulante = m;
                }
                else
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "msg", "alert('Error: El postulante no fue encontrado en sesión.');", true);
                    return;
                }
            }

            // Asignar valores
            postulante.nombre = txtNombreCompleto.Text;
            postulante.correo = txtCorreoElectronico.Text;

            if (int.TryParse(txtCodigoPUCP.Text, out int codigo))
                postulante.codigoPUCP = codigo;
            else
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "msg", "alert('Código PUCP inválido');", true);
                return;
            }

            postulante.telefono = txtNumeroContacto.Text;

            // Otros campos
            postulante.facultad = ddlFacultad.SelectedValue;
            postulante.especialidad = ddlEspecialidad.SelectedValue;

            if (Enum.TryParse(ddlEstadoAcademico.SelectedValue, out estadoPUCP estadoAcademico))
            {
                postulante.status = estadoAcademico;
                postulante.statusSpecified = true;
            }

            try
            {
                if (EstadoActual == Estado.Nuevo)
                    boPostulante.insertarPostulante(postulante);
                else
                    boPostulante.modificarPostulante(postulante);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error al guardar: {ex.Message}');", true);
                return;
            }

            // Redirigir
            Response.Redirect("Miembro.aspx");
        }
    }
}