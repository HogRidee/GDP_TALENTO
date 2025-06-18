using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class ProgramarEntrevista : System.Web.UI.Page
    {
        private Estado estado;
        private entrevista entrevista;
        private EntrevistaWSClient boEntrevista;

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
                    entrevista = new entrevista();
                }
                else if (accion == "Reprogramar")
                {
                    EstadoActual = Estado.Modificar;
                    lblTitulo.Text = "Reprogramar entrevista";

                    entrevista = (entrevista)Session["entrevistaSeleccionada"];

                    if (entrevista != null)
                    {
                        txtPostulante.Text = entrevista.postulante.nombre;
                        txtFecha.Text = entrevista.fecha.ToString(); //Corregir el formato <-----------------------
                        txtEntrevistador.Text = entrevista.entrevistadores[0].nombre;
                    }
                    Session["entrevistaSeleccionada"] = entrevista;
                }
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boEntrevista = new EntrevistaWSClient();

            if (EstadoActual == Estado.Nuevo)
            {
                entrevista = new entrevista();
            }
            else
            {
                // Validación extra para evitar null
                if (Session["entrevistaSeleccionada"] is entrevista m)
                {
                    entrevista = m;
                }
                else
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "msg", "alert('Error: La entrevista no fue encontrada en sesión.');", true);
                    return;
                }
            }

            // Asignar valores
            entrevista.postulante.nombre = txtPostulante.Text;
            entrevista.entrevistadores[0].nombre = txtEntrevistador.Text;

            try
            {
                if (EstadoActual == Estado.Nuevo)
                    boEntrevista.insertarEntrevista(entrevista);
                else
                    boEntrevista.modificarEntrevista(entrevista);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error al guardar: {ex.Message}');", true);
                return;
            }

            // Redirigir
            Response.Redirect("Entrevista.aspx");
        }
    }
}