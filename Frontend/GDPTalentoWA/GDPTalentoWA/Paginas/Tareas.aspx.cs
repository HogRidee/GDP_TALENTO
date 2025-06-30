using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class Tareas : System.Web.UI.Page
    {
        private TareaWSClient boTarea;
        private UsuarioWSClient boUsuario;
        private Dictionary<int, string> usuariosCache;

        protected void Page_Load(object sender, EventArgs e)
        {
            this.UnobtrusiveValidationMode = UnobtrusiveValidationMode.None;
            if (!IsPostBack)
            {
                boTarea = new TareaWSClient();
                boUsuario = new UsuarioWSClient();
                usuariosCache = boUsuario.listarUsuarios()?.ToDictionary(u => u.id, u => u.nombre) ?? new Dictionary<int, string>();
                Session["usuariosCache"] = usuariosCache;
                CargarTareas();
            }
        }

        private void CargarTareas()
        {
            boTarea = new TareaWSClient();
            var tareas = boTarea.listarTareas();
            usuariosCache = (Dictionary<int, string>)Session["usuariosCache"] ?? new Dictionary<int, string>();

            if (tareas == null || tareas.Length == 0)
            {
                dgvTareas.DataSource = null;
                dgvTareas.DataBind();
                return;
            }

            var tareasPlanas = new List<object>();

            foreach (var tarea in tareas)
            {
                if (tarea.encargados != null && tarea.encargados.Length > 0)
                {
                    foreach (var encargado in tarea.encargados)
                    {
                        usuariosCache.TryGetValue(encargado.id, out string nombreEncargado);
                        tareasPlanas.Add(new
                        {
                            id = tarea.id,
                            descripcion = tarea.descripcion,
                            creador = tarea.creador?.nombre ?? "Sin nombre",
                            encargado = nombreEncargado ?? "Sin nombre",
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
            switch (estado)
            {
                case "PENDIENTE":
                    return "badge bg-warning text-dark";
                case "EN_PROCESO":
                    return "badge bg-primary";
                case "REALIZADA":
                    return "badge bg-success";
                default:
                    return "badge bg-secondary";
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
            int idTarea = int.Parse(ddl.Attributes["data-id"]);
            string accion = ddl.SelectedValue;

            boTarea = new TareaWSClient();
            var tareaSeleccionada = boTarea.listarTareas().FirstOrDefault(t => t.id == idTarea);
            usuariosCache = (Dictionary<int, string>)Session["usuariosCache"] ?? new Dictionary<int, string>();

            if (tareaSeleccionada != null)
            {
                switch (accion)
                {
                    case "VerDetalles":
                        lblDescripcionDetalle.Text = tareaSeleccionada.descripcion;
                        lblFechaLimiteDetalle.Text = tareaSeleccionada.fechaLimite.ToString("dd/MM/yyyy");
                        lblEstadoDetalle.Text = tareaSeleccionada.estado.ToString();
                        lblCreadorDetalle.Text = tareaSeleccionada.creador?.nombre ?? "Sin nombre";

                        var nombres = tareaSeleccionada.encargados?
                            .Select(enc => usuariosCache.TryGetValue(enc.id, out var nombre) ? nombre : "Sin nombre")
                            .ToList();

                        lblEncargadosDetalle.Text = (nombres != null && nombres.Any()) ? string.Join(", ", nombres) : "Sin encargados";

                        upDetallesTarea.Update();
                        ScriptManager.RegisterStartupScript(this, GetType(), "abrirDetalles", "showModalDetallesTarea();", true);
                        break;

                    case "EditarTarea":
                        txtDescripcionEditar.Text = tareaSeleccionada.descripcion;
                        txtFechaLimiteEditar.Text = tareaSeleccionada.fechaLimite.ToString("yyyy-MM-dd");
                        ddlEstadoEditar.SelectedValue = tareaSeleccionada.estado.ToString();
                        lblCreadorEditar.Text = tareaSeleccionada.creador?.nombre ?? "Sin nombre";

                        Session["tareaEditarId"] = tareaSeleccionada.id;
                        Session["encargadoEditarId"] = tareaSeleccionada.encargados?[0]?.id;

                        upEditarTarea.Update();
                        ScriptManager.RegisterStartupScript(this, GetType(), "mostrarEditar", "showModalEditarTarea();", true);
                        break;

                    case "EliminarTarea":
                        Session["tareaEliminarId"] = tareaSeleccionada.id;
                        ScriptManager.RegisterStartupScript(this, GetType(), "mostrarEliminar", "showModalEliminarTarea();", true);
                        break;
                }
            }

            ddl.SelectedIndex = 0;
        }

        protected void btnEliminarEncargado_Click(object sender, EventArgs e)
        {
            boTarea = new TareaWSClient();
            int idTarea = (int)Session["tareaEditarId"];
            int idEncargado = (int)Session["encargadoEditarId"];

            var tarea = boTarea.listarTareas().FirstOrDefault(t => t.id == idTarea);
            if (tarea != null)
            {
                tarea.encargados = tarea.encargados?.Where(enc => enc.id != idEncargado).ToArray();
                boTarea.modificarTarea(tarea);
                CargarTareas();
            }
        }

        protected void btnGuardarCambios_Click(object sender, EventArgs e)
        {
            if (!Page.IsValid) return;
            boTarea = new TareaWSClient();
            int idTarea = (int)Session["tareaEditarId"];

            var tarea = boTarea.listarTareas().FirstOrDefault(t => t.id == idTarea);
            if (tarea != null)
            {
                tarea.descripcion = txtDescripcionEditar.Text;
                tarea.fechaLimite = DateTime.Parse(txtFechaLimiteEditar.Text);
                tarea.estado = (estadoTarea)Enum.Parse(typeof(estadoTarea), ddlEstadoEditar.SelectedValue);

                boTarea.modificarTarea(tarea);
                CargarTareas();
                ScriptManager.RegisterStartupScript(this, GetType(), "cerrarModalEditar", "hideModalEditarTarea();", true);
            }
        }

        protected void FiltrarTareas_Click(object sender, EventArgs e)
        {
            string estadoFiltro = ((LinkButton)sender).CommandArgument;

            btnTodas.CssClass = "nav-link";
            btnPendientes.CssClass = "nav-link";
            btnEnProgreso.CssClass = "nav-link";
            btnCompletadas.CssClass = "nav-link";
            ((LinkButton)sender).CssClass += " active";

            CargarTareasFiltradas(estadoFiltro);
        }

        private void CargarTareasFiltradas(string estadoFiltro)
        {
            boTarea = new TareaWSClient();
            var tareas = boTarea.listarTareas();
            usuariosCache = (Dictionary<int, string>)Session["usuariosCache"] ?? new Dictionary<int, string>();
            var tareasFiltradas = new List<object>();

            if (tareas != null)
            {
                foreach (var tarea in tareas)
                {
                    if ((string.IsNullOrEmpty(estadoFiltro) || tarea.estado.ToString() == estadoFiltro)
                        && tarea.encargados != null && tarea.encargados.Length > 0)
                    {
                        foreach (var encargado in tarea.encargados)
                        {
                            usuariosCache.TryGetValue(encargado.id, out string nombreEncargado);
                            tareasFiltradas.Add(new
                            {
                                id = tarea.id,
                                descripcion = tarea.descripcion,
                                creador = tarea.creador?.nombre ?? "Sin nombre",
                                encargado = nombreEncargado ?? "Sin nombre",
                                fechaLimite = tarea.fechaLimite,
                                estado = tarea.estado,
                                estadoCss = ObtenerEstadoCss(tarea.estado.ToString())
                            });
                        }
                    }
                }
            }

            dgvTareas.DataSource = tareasFiltradas;
            dgvTareas.DataBind();
        }

        protected void btnRegistrarTarea_Click(object sender, EventArgs e)
        {
            try
            {
                boUsuario = new UsuarioWSClient();
                int idUsuarioSesion = (int)Session["id"];
                var usuarioSesion = boUsuario.obtenerPorId(idUsuarioSesion);

                lblEncargadoSesion.Text = usuarioSesion.nombre;
                lblFechaCreacion.Text = DateTime.Now.ToString("dd/MM/yyyy");

                lstEncargados.Items.Clear();
                var usuarios = boUsuario.listarUsuarios();
                usuariosCache = usuarios.ToDictionary(u => u.id, u => u.nombre);
                Session["usuariosCache"] = usuariosCache;

                foreach (var u in usuarios)
                {
                    if (u.id != idUsuarioSesion)
                    {
                        lstEncargados.Items.Add(new ListItem(u.nombre, u.id.ToString()));
                    }
                }

                upNuevaTarea.Update();
                ScriptManager.RegisterStartupScript(this, GetType(), "mostrarNuevaTarea", "showModalNuevaTarea();", true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error al preparar el formulario: {ex.Message}');", true);
            }
        }

        protected void btnGuardarNuevaTarea_Click(object sender, EventArgs e)
        {
            if (!Page.IsValid) return;
            boTarea = new TareaWSClient();
            boUsuario = new UsuarioWSClient();

            try
            {
                int idUsuarioSesion = (int)Session["id"];
                var usuarioSesion = boUsuario.obtenerPorId(idUsuarioSesion);

                var nuevaTarea = new tarea
                {
                    descripcion = txtDescripcionNueva.Text,
                    fechaCreacion = DateTime.Now,
                    fechaCreacionSpecified = true,
                    fechaLimite = DateTime.Parse(txtFechaLimiteNueva.Text),
                    fechaLimiteSpecified = true,
                    estado = estadoTarea.EN_PROCESO,
                    estadoSpecified = true,
                    creador = usuarioSesion
                };

                List<usuario> encargados = new List<usuario> { usuarioSesion };

                foreach (ListItem item in lstEncargados.Items)
                {
                    if (item.Selected)
                    {
                        int idEncargado = int.Parse(item.Value);
                        encargados.Add(boUsuario.obtenerPorId(idEncargado));
                    }
                }

                nuevaTarea.encargados = encargados.ToArray();

                boTarea.insertarTarea(nuevaTarea);
                CargarTareas();
                ScriptManager.RegisterStartupScript(this, GetType(), "cerrarModal", "hideModalNuevaTarea();", true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error al guardar tarea: {ex.Message}');", true);
            }
        }

        protected void btnEliminarTareaFinal_Click(object sender, EventArgs e)
        {
            if (Session["tareaEliminarId"] != null)
            {
                int idTarea = (int)Session["tareaEliminarId"];
                boTarea = new TareaWSClient();

                try
                {
                    boTarea.eliminarTarea(idTarea);
                    CargarTareas();
                    ScriptManager.RegisterStartupScript(this, GetType(), "cerrarModalEliminar", "hideModalEliminarTarea();", true);
                }
                catch (Exception ex)
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "errorEliminar", $"alert('Error al eliminar tarea: {ex.Message}');", true);
                }
            }
        }
    }
}
