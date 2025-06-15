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
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string accion = Request.QueryString["accion"];
                string idStr = Request.QueryString["id"];
                int idPostulante;

                if (accion == "modificar" && int.TryParse(idStr, out idPostulante))
                {
                    lblTitulo.Text = "Editar Postulante";

                    PostulanteWSClient boPostulante = new PostulanteWSClient();
                    var listaPostulantes = boPostulante.listarPostulantes();

                    // Buscar el postulante con el id correspondiente
                    postulante postulante = listaPostulantes?.FirstOrDefault(x => x.id == idPostulante);

                    if (postulante != null)
                    {
                        txtNombreCompleto.Text = postulante.nombre;
                        txtCorreoElectronico.Text = postulante.correo;
                        txtCodigoPUCP.Text = postulante.codigoPUCP.ToString();
                        txtNumeroContacto.Text = postulante.telefono;
                        ddlFacultad.SelectedValue = postulante.facultad;
                        ddlEspecialidad.SelectedValue = postulante.especialidad;
                    }

                }
                else
                {
                    estado = Estado.Nuevo;
                    postulante = new postulante();
                    lblTitulo.Text = "Registrar Nuevo Postulante";
                }
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boPostulante = new PostulanteWSClient();
            postulante = new postulante();
            postulante.nombre = txtNombreCompleto.Text;
            postulante.correo = txtCorreoElectronico.Text;
            postulante.codigoPUCP = int.Parse(txtCodigoPUCP.Text);
            postulante.telefono = txtNumeroContacto.Text;

            postulante.facultad = ddlFacultad.SelectedValue;

            postulante.especialidad = ddlEspecialidad.SelectedValue;

            try
            {
                if (estado == Estado.Nuevo)
                {
                    boPostulante.insertarPostulante(postulante);
                }
                else if (estado == Estado.Modificar)
                {
                    boPostulante.modificarPostulante(postulante);
                }
            }
            catch (Exception ex)
            { }

            Response.Redirect("Miembro.aspx");
        }
    }
}