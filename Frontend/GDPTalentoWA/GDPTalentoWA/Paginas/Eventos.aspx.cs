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

        protected void btnGuardarAsistencia_Click(object sender, EventArgs e)
        {
            var eventoSeleccionado = (evento)Session["eventoSeleccionado"];
            AsistenciaWSClient boAsistencia = new AsistenciaWSClient();
            boAsistencia.eliminarAsistenciasPorEvento(eventoSeleccionado.id);
            foreach (GridViewRow row in gvAsistencia.Rows)
            {
                int idParticipante = Convert.ToInt32(gvAsistencia.DataKeys[row.RowIndex].Value);
                CheckBox chkAsistio = (CheckBox)row.FindControl("chkAsistio");

                if (chkAsistio != null)
                {
                    var asistenciasExistentes = boAsistencia.listarAsistenciasPorStaff(idParticipante) ?? new asistencia[0];

                    var yaRegistrado = asistenciasExistentes.FirstOrDefault(
                    a => a.evento != null && a.evento.id == eventoSeleccionado.id);

                    var asistenciaObj = new ServicioWeb.asistencia();
                    asistenciaObj.participante = new ServicioWeb.staff() { id = idParticipante };
                    asistenciaObj.evento = new ServicioWeb.evento() { id = eventoSeleccionado.id };
                    asistenciaObj.estadoAsistencia = chkAsistio.Checked ? estadoAsistencia.ASISTIO : estadoAsistencia.FALTO;

                    if (yaRegistrado != null)
                    {
                        boAsistencia.modificarAsistencia(asistenciaObj);
                    }
                    else
                    {
                        boAsistencia.insertarAsistencia(asistenciaObj);
                    }
                }
            }

            ScriptManager.RegisterStartupScript(this, GetType(), "alerta", "alert('Asistencia registrada correctamente.');", true);
        }

        protected void dgvEventos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                // Recuperas el objeto evento que corresponde a esta fila
                var evento = (evento)e.Row.DataItem;

                // Recuperas el botón
                Button btnAsistencia = (Button)e.Row.FindControl("btnAsistencia");

                // Habilitas solo si es la fecha actual
                if (btnAsistencia != null)
                {
                    btnAsistencia.Enabled = (evento.fecha.Date == DateTime.Today);
                }
            }
            

        }

        protected void dgvEventos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Asistencia")
            {
                int idEvento = Convert.ToInt32(e.CommandArgument);
                EventoWSClient boEvento = new EventoWSClient();
                AsistenciaWSClient boAsistencia = new AsistenciaWSClient();
                StaffWSClient bostaff = new StaffWSClient();

                var eventoSeleccionado = boEvento.listarEventos().FirstOrDefault(s => s.id == idEvento);

                if (eventoSeleccionado != null)
                {
                    Session["eventoSeleccionado"] = eventoSeleccionado;

                    // Obtener todos los staff
                    var listaConDatos = new List<staff>(bostaff.listarStaff() ?? new staff[0]);

                    // Obtener asistencias desde la BD (y evitar null)
                    var todasAsistencias = boAsistencia.listarAsistencias() ?? new asistencia[0];

                    // Participantes del evento
                    List<staff> listaAsistentes = new List<staff>();
                    var idsAgregados = new HashSet<int>();

                    foreach (staff x in eventoSeleccionado.participantes ?? new staff[0])
                    {
                        var staffMatch = listaConDatos.FirstOrDefault(y => y.id == x.id);
                        if (staffMatch != null && idsAgregados.Add(staffMatch.id))
                        {
                            listaAsistentes.Add(staffMatch);
                        }
                    }

                    // Crear lista a mostrar con estado de asistencia
                    var listaParaMostrar = listaAsistentes.Select(p =>
                    {
                        var asistenciaBD = todasAsistencias
                            .FirstOrDefault(a => a.evento?.id == eventoSeleccionado.id && a.participante?.id == p.id);

                        bool asistio = (asistenciaBD != null && asistenciaBD.estadoAsistencia == estadoAsistencia.ASISTIO);

                        return new
                        {
                            id = p.id,
                            codigoPUCP = p.codigoPUCP,
                            nombre = p.nombre,
                            asistio = asistio
                        };
                    }).ToList();

                    Session["Asistentes"] = listaParaMostrar;
                    gvAsistencia.DataSource = listaParaMostrar;
                    gvAsistencia.DataBind();

                    ScriptManager.RegisterStartupScript(this, GetType(), "abrirModal", "abrirModalAsistencia();", true);
                }
            }
        }
    }
}