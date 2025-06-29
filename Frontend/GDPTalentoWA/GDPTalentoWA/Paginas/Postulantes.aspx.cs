using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Security.Cryptography;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;

namespace GDPTalentoWA.Paginas
{
    public partial class Postulantes : System.Web.UI.Page
    {
        private PostulanteWSClient boPostulante;
        private BindingList<postulante> postulantes;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarPostulantes();
            }
        }

        private void CargarPostulantes()
        {
            boPostulante = new PostulanteWSClient();
            var listaPostulantes = boPostulante.listarPostulantes();
            if(listaPostulantes == null)
                postulantes = new BindingList<postulante>();
            else
                postulantes = new BindingList<postulante>(listaPostulantes);

            dgvPostulantes.DataSource = postulantes;
            dgvPostulantes.DataBind();

            ViewState["postulantes"] = postulantes;
        }

        protected void lbBuscarPostulante_Click(object sender, EventArgs e)
        {
            string textoBusqueda = txtBuscarPostulante.Text.Trim().ToLower();

            boPostulante = new PostulanteWSClient();
            var listaPostulantes= boPostulante.listarPostulantes();

            if (listaPostulantes == null)
            {
                postulantes = new BindingList<postulante>();
            }
            else
            {
                // Filtro por nombre, código PUCP o área
                var filtrados = listaPostulantes.Where(s =>
                    (s.nombre != null && s.nombre.ToLower().Contains(textoBusqueda)) ||
                    s.codigoPUCP.ToString().Contains(textoBusqueda) ||
                    s.especialidad.ToString().ToLower().Contains(textoBusqueda)
                ).ToList();

                postulantes = new BindingList<postulante>(filtrados);
            }

            dgvPostulantes.DataSource = postulantes;
            dgvPostulantes.DataBind();
        }

        /*Temas del dgv*/

        protected void dgvPostulantes_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "nombre")?.ToString().ToUpper() ?? "";
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "codigoPUCP")?.ToString() ?? "";
                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "especialidad")?.ToString().ToUpper() ?? "";
                e.Row.Cells[3].Text = DataBinder.Eval(e.Row.DataItem, "estadoProceso")?.ToString() ?? "";

            }
        }

        protected void dgvPostulantes_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvPostulantes.PageIndex = e.NewPageIndex;
            dgvPostulantes.DataBind();
        }

        /*Botones*/

        protected void btnRegistrarPostulante_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarNuevoPostulante.aspx");
        }

        protected void btnFiltrarPostulante_Click(object sender, EventArgs e)
        {
            boPostulante = new PostulanteWSClient();
            var listaOriginal = boPostulante.listarPostulantes();

            if (listaOriginal == null) return;

            string textoBusqueda = txtBuscarPostulante.Text.Trim().ToLower();
            string estadoSeleccionado = ddlEstados.SelectedValue;
            string especialidadSeleccionada = ddlEspecialidades.SelectedValue;      // Ej: "Marketing"

            var listaFiltrada = listaOriginal.Where(s =>
                (s.nombre != null && s.nombre.ToLower().Contains(textoBusqueda) ||
                    s.codigoPUCP.ToString().Contains(textoBusqueda) ||
                    s.especialidad.ToString().ToLower().Contains(textoBusqueda)) &&
                (string.IsNullOrEmpty(estadoSeleccionado) ||
                 (estadoSeleccionado == "1" && s.estadoProceso == estadoProceso.PENDIENTE) ||
                 (estadoSeleccionado == "2" && s.estadoProceso == estadoProceso.APROBADO) ||
                 (estadoSeleccionado == "3" && s.estadoProceso == estadoProceso.RECHAZADO)) &&

                (string.IsNullOrEmpty(especialidadSeleccionada) ||
                 s.especialidad.ToString().Equals(especialidadSeleccionada, StringComparison.OrdinalIgnoreCase))
            ).ToList();

            dgvPostulantes.DataSource = listaFiltrada;
            dgvPostulantes.DataBind();
        }

        protected void btnEjecutar_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            GridViewRow row = (GridViewRow)btn.NamingContainer;

            DropDownList ddlAcciones = (DropDownList)row.FindControl("ddlAcciones");

            string accion = ddlAcciones.SelectedValue;
            int idPostulante = Convert.ToInt32(btn.CommandArgument);

            // Recargar la lista para asegurarnos que no sea nula
            boPostulante = new PostulanteWSClient();
            var listaPostulantes = boPostulante.listarPostulantes();
            postulantes = new BindingList<postulante>(listaPostulantes);

            switch (accion)
            {
                case "VerDetalles":
                    Response.Redirect($"VerDetalles.aspx?id={idPostulante}");
                    break;

                case "EditarInformacion":
                    postulante postulanteSeleccionado = postulantes.SingleOrDefault(x => x.id == idPostulante);
                    if (postulanteSeleccionado != null)
                    {
                        Session["postulanteSeleccionado"] = postulanteSeleccionado;
                        Response.Redirect($"RegistrarNuevoPostulante.aspx?accion=modificar&id={idPostulante}");
                    }
                    else
                    {
                        ScriptManager.RegisterStartupScript(this, GetType(), "msg", "alert('Postulante no encontrado.');", true);
                    }
                    break;

                case "EliminarPostulante":
                    boPostulante.eliminarPostulante(idPostulante);
                    postulantes = new BindingList<postulante>(boPostulante.listarPostulantes());
                    dgvPostulantes.DataSource = postulantes;
                    dgvPostulantes.DataBind();
                    break;

            }
        }

        protected void ddlAcciones_SelectedIndexChanged(object sender, EventArgs e)
        {
            boPostulante = new PostulanteWSClient();

            DropDownList ddl = (DropDownList)sender;
            GridViewRow row = (GridViewRow)ddl.NamingContainer;

            string accion = ddl.SelectedValue;
            postulante postulante;
            postulantes = (BindingList<postulante>)Session["postulantes"];
            if (postulantes == null)
            {
                boPostulante = new PostulanteWSClient();
                postulantes = new BindingList<postulante>(boPostulante.listarPostulantes());
                Session["postulantes"] = postulantes;
            }

            // Obtener ID desde el DataKeys si usas DataKeyNames, o desde atributo personalizado:
            string idStr = ddl.Attributes["data-id"];
            int id = int.TryParse(idStr, out int idParsed) ? idParsed : -1;

            if (id == -1 || string.IsNullOrEmpty(accion)) return;

            switch (accion)
            {
                case "VerDetalles":
                    postulante = postulantes.SingleOrDefault(x => x.id == id);
                    Session["postulanteSeleccionado"] = postulante;
                    Response.Redirect("VerDetallesPostulante.aspx");
                    break;
                case "EditarInformacion":
                    postulante = postulantes.SingleOrDefault(x => x.id == id);
                    Session["postulanteSeleccionado"] = postulante;
                    Response.Redirect("RegistrarNuevoPostulante.aspx?accion=modificar");
                    break;
                case "EliminarPostulante":
                    // Aquí llamas a tu servicio de eliminación si corresponde
                    boPostulante.eliminarPostulante(id);
                    postulantes = new BindingList<postulante>(boPostulante.listarPostulantes());
                    dgvPostulantes.DataSource = postulantes;
                    dgvPostulantes.DataBind();
                    Response.Redirect("Postulantes.aspx");
                    break;
            }
        }
    }

    
 }