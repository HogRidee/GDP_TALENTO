using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class Tareas : System.Web.UI.Page
    {
        private TareaWSClient boTarea;
        private tarea[] listaTareas;
        private UsuarioWSClient boUsuario;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                boTarea = new TareaWSClient();
                boUsuario = new UsuarioWSClient();
                CargarTareas();
            }
        }

        private void CargarTareas()
        {
            boTarea = new TareaWSClient();
            var tareas = boTarea.listarTareas();

            var tareasPlanas = new List<object>();

            foreach (var tarea in tareas)
            {
                if (tarea.encargados != null && tarea.encargados.Length > 0)
                {
                    foreach (var encargado in tarea.encargados)
                    {
                        tareasPlanas.Add(new
                        {
                            id = tarea.id,
                            descripcion = tarea.descripcion,
                            creador = tarea.creador?.nombre ?? "Sin nombre",
                            encargado = boUsuario.obtenerPorId(encargado.id).nombre,
                            fechaLimite = tarea.fechaLimite,
                            estado = tarea.estado,
                            estadoCss = ObtenerEstadoCss(tarea.estado.ToString())
                        });
                    }
                }
                
            }

            dgvTareas.DataSource = tareasPlanas;
            dgvTareas.DataBind();
        }
        private string ObtenerEstadoCss(string estado)
        {
            switch (estado.ToLower())
            {
                case "PENDIENTE": return "badge bg-warning text-dark";
                case "EN_PROCESO": return "badge bg-primary";
                case "REALIZADA": return "badge bg-success";
                default: return "badge bg-secondary";
            }
        }

        protected void dgvTareas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvTareas.PageIndex = e.NewPageIndex;
            CargarTareas();
        }

        protected void ddlAcciones_SelectedIndexChanged(object sender, EventArgs e)
        {
            DropDownList ddl = (DropDownList)sender;
            GridViewRow row = (GridViewRow)ddl.NamingContainer;
            string idStr = ddl.Attributes["data-id"];
            int idTarea = int.Parse(idStr);
            string accion = ddl.SelectedValue;

            switch (accion)
            {
                case "VerDetalles":
                    ScriptManager.RegisterStartupScript(this, GetType(), "abrirDetalles", $"showModalDetallesTarea({idTarea});", true);
                    break;
                case "EditarTarea":
                    ScriptManager.RegisterStartupScript(this, GetType(), "abrirEditar", $"showModalEditarTarea({idTarea});", true);
                    break;
            }
        }

        protected void btnRegistrarTarea_Click(object sender, EventArgs e)
        {
            // Aquí puedes redirigir a una página para registrar nueva tarea o abrir modal si deseas hacerlo con AJAX
        }
    }
}
