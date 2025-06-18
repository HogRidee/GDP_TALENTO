using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class Entrevista : System.Web.UI.Page
    {
        private EntrevistaWSClient boEntrevista;
        private BindingList<entrevista> entrevistas;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarEntrevistas();
            }
        }

        private void CargarEntrevistas()
        {
            boEntrevista = new EntrevistaWSClient();
            var listaEntrevistas= boEntrevista.listarEntrevistas();
            if (listaEntrevistas == null)
                entrevistas = new BindingList<entrevista>();
            else
                entrevistas = new BindingList<entrevista>(listaEntrevistas);

            dgvEntrevistas.DataSource = entrevistas;
            dgvEntrevistas.DataBind();

            ViewState["Entrevistas"] = entrevistas;
        }

        protected void lbBuscarEntrevista_Click(object sender, EventArgs e)
        {
            string textoBusqueda = txtBuscarEntrevista.Text.Trim().ToLower();

            boEntrevista = new EntrevistaWSClient();
            var listaEntrevistas = boEntrevista.listarEntrevistas();

            if (listaEntrevistas == null)
            {
                entrevistas = new BindingList<entrevista>();
            }
            else
            {
                // Filtro por postualnte

                
                var filtrados = listaEntrevistas.Where(s =>
                    (s.postulante != null && s.postulante.nombre.ToString().ToLower().Contains(textoBusqueda)) 
                ).ToList();

                entrevistas = new BindingList<entrevista>(filtrados);
            }

            dgvEntrevistas.DataSource = entrevistas;
            dgvEntrevistas.DataBind();
        }

        /*Temas del dgv*/

        protected void dgvEntrevistas_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "postulante.nombre")?.ToString() ?? "";
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "fecha")?.ToString() ?? "";
                //e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "hora")?.ToString() ?? "";
                e.Row.Cells[3].Text = DataBinder.Eval(e.Row.DataItem, "entrevistadores[0].nombre")?.ToString() ?? "";
            }
        }

        protected void dgvEntrevistas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvEntrevistas.PageIndex = e.NewPageIndex;
            dgvEntrevistas.DataBind();
        }

        /*Botones*/

        protected void btnProgramarEntrevista_Click(object sender, EventArgs e)
        {
            Response.Redirect("ProgramarEntrevista.aspx");
        }

        protected void btnFiltrarEntrevista_Click(object sender, EventArgs e)
        {
            /*AQUI FALTA IO*/
            boEntrevista= new EntrevistaWSClient();
            var listaOriginal = boEntrevista.listarEntrevistas();

            if (listaOriginal == null) return;

            string textoBusqueda = txtBuscarEntrevista.Text.Trim().ToLower();
            string estadoSeleccionado = ddlEstados.SelectedValue; // "1" = ACTIVO, "2" = INACTIVO
            //string areaSeleccionada = ddlAreas.SelectedValue;      // Ej: "Marketing"

            var listaFiltrada = listaOriginal.Where(s =>
                (string.IsNullOrEmpty(textoBusqueda) ||
                 s.postulante.nombre.ToLower().Contains(textoBusqueda))
            ).ToList();

            dgvEntrevistas.DataSource = listaFiltrada;
            dgvEntrevistas.DataBind();
        }

        /*Otros*/

        protected void ddlAcciones_SelectedIndexChanged(object sender, EventArgs e)
        {
            DropDownList ddl = (DropDownList)sender;
            GridViewRow row = (GridViewRow)ddl.NamingContainer;

            string accion = ddl.SelectedValue;
            entrevista entrevista;
            entrevistas = (BindingList<entrevista>)Session["entrevistas"];
            if (entrevistas == null)
            {
                boEntrevista = new EntrevistaWSClient();
                entrevistas = new BindingList<entrevista>(boEntrevista.listarEntrevistas());
                Session["entrevistas"] = entrevistas;
            }

            // Obtener ID desde el DataKeys si usas DataKeyNames, o desde atributo personalizado:
            string idStr = ddl.Attributes["data-id"];
            int id = int.TryParse(idStr, out int idParsed) ? idParsed : -1;

            if (id == -1 || string.IsNullOrEmpty(accion)) return;

            switch (accion)
            {
                case "Reprogramar":
                    entrevista = entrevistas.SingleOrDefault(x => x.id == id);
                    Session["entrevistaSeleccionada"] = entrevista;
                    Response.Redirect("ProgramarEntrevista.aspx?accion=reprogramar");
                    break;
                case "Completar":
                    entrevista = entrevistas.SingleOrDefault(x => x.id == id);
                    Session["entrevistaSeleccionada"] = entrevista;
                    entrevista.estado = estadoEntrevista.REALIZADA;
                    /*
                    entrevistas = new BindingList<entrevista>(boEntrevista.listarEntrevistas());
                    dgvEntrevistas.DataSource = entrevistas;
                    dgvEntrevistas.DataBind();
                    */
                    Response.Redirect("Entrevista.aspx");
                    break;
                case "Cancelar":
                    entrevista = entrevistas.SingleOrDefault(x => x.id == id);
                    Session["entrevistaSeleccionada"] = entrevista;
                    entrevista.estado = estadoEntrevista.CANCELADA;
                    /*
                    entrevistas = new BindingList<entrevista>(boEntrevista.listarEntrevistas());
                    dgvEntrevistas.DataSource = entrevistas;
                    dgvEntrevistas.DataBind();
                    */
                    Response.Redirect("Entrevista.aspx");
                    break;
            }
        }
    }
}