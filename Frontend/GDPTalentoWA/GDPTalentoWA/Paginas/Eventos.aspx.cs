using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.ServiceModel.Configuration;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Xml;
using GDPTalentoWA.ServicioWeb;

namespace GDPTalentoWA.Paginas
{
	public partial class Eventos : System.Web.UI.Page
	{
        public EventoWSClient boEvento;
        private BindingList<evento> eventos;
		protected void Page_Load(object sender, EventArgs e)
		{
            if (!IsPostBack)
            {
                cargar_tabla();
            }
        }

        protected string GetColor(string estado)
        {
            switch (estado.ToLower())
            {
                case "aprobado":
                    return "Lime";
                default:
                    return "gray";
            }
        }
        protected void cargar_tabla()
        {
            boEvento = new EventoWSClient();
            var listarEventos = boEvento.listarEventos();
            if (listarEventos == null)
            {
                eventos = new BindingList<evento>();
            }
            else
            {
                eventos = new BindingList<evento>(listarEventos);
            }
            dgvEventos.DataSource = eventos;
            dgvEventos.DataBind();

            Session["eventos"] = eventos;
        }

        protected void btnRegistrarEvento_Click(object sender, EventArgs e)
        {
            //Response.Redirect("VisualizacionEdicionEvento.aspx");
        }

        protected void ddlAcciones_SelectedIndexChanged(object sender, EventArgs e)
        {
        }

        protected void dgvEventos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvEventos.PageIndex = e.NewPageIndex;
            dgvEventos.DataBind();
        }

        protected void lbBuscarEvento_Click(object sender, EventArgs e)
        {
            string buscado = txtBuscarEventos.Text.Trim().ToLower();

            boEvento = new EventoWSClient();
            var listaEvento = boEvento.listarEventos();

            if (listaEvento == null)
            {
                eventos = new BindingList<evento>();
            }
            else
            {
                var filtrados = listaEvento.Where(s => s.estadoEvento.ToString().ToLower().Contains(buscado) || s.tipoEvento.ToString().ToLower().Contains(buscado) || s.id.ToString().Contains(buscado)).ToList();
                eventos = new BindingList<evento>(filtrados);
            }
            dgvEventos.DataSource = eventos;
            dgvEventos.DataBind();
        }

        protected void btnResetFiltros_Click(object sender, EventArgs e)
        {
            boEvento = new EventoWSClient();
            var lista = boEvento.listarEventos();

            if(lista == null)
            {
                eventos = new BindingList<evento>();
            }
            else
            {
                eventos = new BindingList<evento>(lista);
            }

            dgvEventos.DataSource = eventos;
            dgvEventos.DataBind();

        }

        protected void btnFiltrarEvento_Click(object sender, EventArgs e)
        {
            boEvento = new EventoWSClient();
            var listaOriginal = boEvento.listarEventos();
            if (listaOriginal == null) return;

            string textoBuscado = txtBuscarEventos.Text.Trim().ToLower();
            string TipoSeleccionado = ddlTipo.SelectedValue;
            string MesSeleccionado = ddlMES.SelectedValue;
            string EstadoSeleccionado = ddlEstado.SelectedValue;

            var ListaFiltrada = listaOriginal.Where(s => (string.IsNullOrEmpty(textoBuscado) || 
            s.estadoEvento.ToString().ToLower().Contains(textoBuscado) || s.tipoEvento.ToString().ToLower().Contains(textoBuscado)
             || s.id.ToString().ToLower().Contains(textoBuscado)) && 
             (string.IsNullOrEmpty(TipoSeleccionado) || (TipoSeleccionado=="1" && s.tipoEvento==tipoEvento.REUNION) ||
              (TipoSeleccionado=="2" && s.tipoEvento==tipoEvento.INTEGRACION)) && (string.IsNullOrEmpty(EstadoSeleccionado) ||
              (EstadoSeleccionado=="2" && s.estadoEvento==estadoEvento.CANCELADO) || (EstadoSeleccionado=="1" && s.estadoEvento==estadoEvento.APROBADO)) &&
              (string.IsNullOrEmpty(MesSeleccionado) || MesSeleccionado.Equals(s.fecha.Month.ToString()))).ToList();

            eventos = new BindingList<evento>(ListaFiltrada);

            dgvEventos.DataSource=eventos;
            dgvEventos.DataBind();

        }
    }
}