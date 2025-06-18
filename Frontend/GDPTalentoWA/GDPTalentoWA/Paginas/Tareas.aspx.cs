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
            boUsuario = new UsuarioWSClient();
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
            switch (estado)
            {
                case "PENDIENTE":
                    return "badge bg-warning text-dark"; // Amarillo
                case "EN_PROCESO":
                    return "badge bg-primary"; // Azul
                case "REALIZADA":
                    return "badge bg-success"; // Verde
                default:
                    return "badge bg-secondary"; // Gris
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

            boTarea = new TareaWSClient();
            boUsuario = new UsuarioWSClient();

            tarea tareaSeleccionada = boTarea.listarTareas().FirstOrDefault(t => t.id == idTarea);

            if (tareaSeleccionada != null)
            {
                switch (accion)
                {
                    case "VerDetalles":

                        if (tareaSeleccionada != null)
                        {
                            lblDescripcionDetalle.Text = tareaSeleccionada.descripcion;
                            lblFechaLimiteDetalle.Text = tareaSeleccionada.fechaLimite.ToString("dd/MM/yyyy");
                            lblEstadoDetalle.Text = tareaSeleccionada.estado.ToString();

                            // Buscar nombre del creador
                            var creador = tareaSeleccionada.creador;
                            lblCreadorDetalle.Text = (creador != null)
                                ? boUsuario.obtenerPorId(creador.id)?.nombre ?? "Sin nombre"
                                : "Sin nombre";

                            // Buscar nombres de encargados
                            var nombres = tareaSeleccionada.encargados?
                                .Select(enc => boUsuario.obtenerPorId(enc.id).nombre)
                                .ToList();

                            lblEncargadosDetalle.Text = (nombres != null && nombres.Any())
                                ? string.Join(", ", nombres)
                                : "Sin encargados";
                        }
                        upDetallesTarea.Update();
                        ScriptManager.RegisterStartupScript(upDetallesTarea, upDetallesTarea.GetType(), "abrirDetalles", "setTimeout(function() { showModalDetallesTarea(); }, 300);", true);
                        break;

                    case "EditarTarea":
                        if (tareaSeleccionada != null)
                        {
                            txtDescripcionEditar.Text = tareaSeleccionada.descripcion;
                            txtFechaLimiteEditar.Text = tareaSeleccionada.fechaLimite.ToString("yyyy-MM-dd");
                            ddlEstadoEditar.SelectedValue = tareaSeleccionada.estado.ToString();
                            lblCreadorEditar.Text = boUsuario.obtenerPorId(tareaSeleccionada.creador.id)?.nombre ?? "Sin nombre";

                            // Guarda el ID de la tarea y el ID del encargado en variables de sesión o ViewState si es necesario
                            Session["tareaEditarId"] = tareaSeleccionada.id;
                            Session["encargadoEditarId"] = tareaSeleccionada.encargados?[0]?.id; // solo uno si es por fila

                            upEditarTarea.Update();
                            ScriptManager.RegisterStartupScript(upEditarTarea, upEditarTarea.GetType(), "mostrarEditar", "setTimeout(function() { showModalEditarTarea(); }, 300);", true);
                        }
                        break;

                }
            }

            ddl.SelectedIndex = 0; // Resetea el dropdown
        }
        protected void btnEliminarEncargado_Click(object sender, EventArgs e)
        {
            boTarea = new TareaWSClient(); // <- ¡Agregado aquí!
            int idTarea = (int)Session["tareaEditarId"];
            int idEncargado = (int)Session["encargadoEditarId"];

            var tarea = boTarea.listarTareas().FirstOrDefault(t => t.id == idTarea);
            if (tarea != null)
            {
                tarea.encargados = tarea.encargados
                    .Where(enc => enc.id != idEncargado)
                    .ToArray();

                boTarea.modificarTarea(tarea);
                CargarTareas();
            }
        }


        protected void btnGuardarCambios_Click(object sender, EventArgs e)
        {
            boTarea = new TareaWSClient();
            int idTarea = (int)Session["tareaEditarId"];
            int idEncargado = (int)Session["encargadoEditarId"];

            var tarea = boTarea.listarTareas().FirstOrDefault(t => t.id == idTarea);
            if (tarea != null)
            {
                tarea.descripcion = txtDescripcionEditar.Text;
                tarea.fechaLimite = DateTime.Parse(txtFechaLimiteEditar.Text);
                tarea.estado = (estadoTarea)Enum.Parse(typeof(estadoTarea), ddlEstadoEditar.SelectedValue);

                boTarea.modificarTarea(tarea);

                CargarTareas();

                ScriptManager.RegisterStartupScript(this, GetType(), "cerrarModalEditar", @"
                        setTimeout(function () {
                        if (typeof bootstrap !== 'undefined') {
                            var modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('modalEditarTarea'));
                            modal.hide();
                        }
                    }, 300);", true);
            }
        }

        protected void FiltrarTareas_Click(object sender, EventArgs e)
        {
            string estadoFiltro = ((LinkButton)sender).CommandArgument;

            // Remover clase 'active' de todos
            btnTodas.CssClass = "nav-link";
            btnPendientes.CssClass = "nav-link";
            btnEnProgreso.CssClass = "nav-link";
            btnCompletadas.CssClass = "nav-link";

            // Activar solo el botón actual
            ((LinkButton)sender).CssClass += " active";

            CargarTareasFiltradas(estadoFiltro); // Tu función para filtrar
        }

        private void CargarTareasFiltradas(string estadoFiltro)
        {
            boTarea = new TareaWSClient();
            boUsuario = new UsuarioWSClient();
            var tareas = boTarea.listarTareas();
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
                            tareasFiltradas.Add(new
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

                foreach (var u in usuarios)
                {
                    // No permitir que el creador se agregue a sí mismo como encargado (ya lo es por defecto)
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
            boTarea = new TareaWSClient();
            boUsuario = new UsuarioWSClient();

            try
            {
                // Obtener ID del usuario en sesión
                int idUsuarioSesion = (int)Session["id"];
                var usuarioSesion = boUsuario.obtenerPorId(idUsuarioSesion);

                // Crear nueva tarea
                tarea nuevaTarea = new tarea();

                nuevaTarea.descripcion = txtDescripcionNueva.Text;
                nuevaTarea.fechaCreacion = DateTime.Now;
                nuevaTarea.fechaCreacionSpecified = true; 

                nuevaTarea.fechaLimite = DateTime.Parse(txtFechaLimiteNueva.Text);
                nuevaTarea.fechaLimiteSpecified = true; 
                nuevaTarea.estado = estadoTarea.EN_PROCESO;
                nuevaTarea.estadoSpecified = true;
                nuevaTarea.creador = usuarioSesion;
                

                List<usuario> encargados = new List<usuario>
        {
            usuarioSesion 
        };

                foreach (ListItem item in lstEncargados.Items)
                {
                    if (item.Selected)
                    {
                        int idEncargado = int.Parse(item.Value);
                        var encargado = boUsuario.obtenerPorId(idEncargado);
                        encargados.Add(encargado);
                    }
                }

                nuevaTarea.encargados = encargados.ToArray();

                boTarea.insertarTarea(nuevaTarea);

                CargarTareas();
                ScriptManager.RegisterStartupScript(this, GetType(), "cerrarModal", @"
                    setTimeout(function () {
                        if (typeof bootstrap !== 'undefined') {
                            var modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('modalNuevaTarea'));
                            modal.hide();
                        }
                    }, 300);", true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error al guardar tarea: {ex.Message}');", true);
            }
        }


    }
}
