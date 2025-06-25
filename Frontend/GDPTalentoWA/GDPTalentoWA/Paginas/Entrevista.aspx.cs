using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.Services.Description;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class Entrevista : System.Web.UI.Page
    {
        private TareaWSClient boTarea;
        private UsuarioWSClient boUsuario;
        private EntrevistaWSClient boEntrevista;
        private PostulanteWSClient boPostulante;
        private Dictionary<int, string> usuariosCache;
        private Dictionary<int, string> postulantesCache;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                boEntrevista = new EntrevistaWSClient();
                boUsuario = new UsuarioWSClient();
                boPostulante = new PostulanteWSClient();
                usuariosCache = boUsuario.listarUsuarios()?.ToDictionary(u => u.id, u => u.nombre) ?? new Dictionary<int, string>();
                Session["usuariosCache"] = usuariosCache;
                postulantesCache = boPostulante.listarPostulantes()?.ToDictionary(u => u.id, u => u.nombre) ?? new Dictionary<int, string>();
                Session["postulantesCache"] = postulantesCache;
                CargarEntrevistas();
            }
        }

        private void CargarEntrevistas()
        {
            boEntrevista = new EntrevistaWSClient();
            var entrevistas = boEntrevista.listarEntrevistas();
            usuariosCache = (Dictionary<int, string>)Session["usuariosCache"] ?? new Dictionary<int, string>();
            postulantesCache = (Dictionary<int, string>)Session["postulantesCache"] ?? new Dictionary<int, string>();

            if (entrevistas == null || entrevistas.Length == 0)
            {
                dgvEntrevistas.DataSource = null;
                dgvEntrevistas.DataBind();
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('No funca tu pinche listar');", true);
                return;
            }
            
            var entrevistasPlanas = new List<object>();

            foreach (var entrevista in entrevistas)
            {
                if (entrevista.entrevistadores != null && entrevista.entrevistadores.Length > 0)
                {
                    postulantesCache.TryGetValue(entrevista.postulante.id,out string nombrePostulante);
                    foreach (var entrevistador in entrevista.entrevistadores)
                    {
                        usuariosCache.TryGetValue(entrevistador.id, out string nombreEncargado);
                        entrevistasPlanas.Add(new
                        {
                            id = entrevista.id,
                            postulante = nombrePostulante ?? "Sin nombre",
                            estado = entrevista.estado,
                            entrevistador = nombreEncargado ?? "Sin nombre",
                            fecha = entrevista.fecha,
                            feedback = entrevista.feedback
                        });
                    }
                }
            }

            dgvEntrevistas.DataSource = entrevistasPlanas;
            dgvEntrevistas.DataBind();
        }


        protected void dgvEntrevistas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvEntrevistas.PageIndex = e.NewPageIndex;
            CargarEntrevistas();
        }

        protected void ddlAcciones_SelectedIndexChanged(object sender, EventArgs e)
        {
            
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
                CargarEntrevistas();
            }
        }

        protected void btnGuardarCambios_Click(object sender, EventArgs e)
        {
            
        }

        protected void FiltrarTareas_Click(object sender, EventArgs e)
        {
            
        }

        private void CargarTareasFiltradas(string estadoFiltro)
        {
            boEntrevista = new EntrevistaWSClient();
            var entrevistas = boEntrevista.listarEntrevistas();
            usuariosCache = (Dictionary<int, string>)Session["usuariosCache"] ?? new Dictionary<int, string>();
            var entrevistasFiltradas = new List<object>();

            foreach (var entrevista in entrevistas)
            {
                if (entrevista.entrevistadores != null && entrevista.entrevistadores.Length > 0)
                {
                    foreach (var entrevistador in entrevista.entrevistadores)
                    {
                        usuariosCache.TryGetValue(entrevistador.id, out string nombreEncargado);
                        entrevistasFiltradas.Add(new
                        {
                            id = entrevista.id,
                            postulante = entrevista.postulante?.nombre ?? "Sin nombre",
                            estado = entrevista.estado,
                            entrevistador = nombreEncargado ?? "Sin nombre",
                            fecha = entrevista.fecha
                        });
                    }
                }
            }

            dgvEntrevistas.DataSource = entrevistasFiltradas;
            dgvEntrevistas.DataBind();
        }
        /*
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
        */
        protected void btnProgramarEntrevista_Click(object sender, EventArgs e)
        {
            //Response.Redirect("ProgramarEntrevista.aspx");

            try
            {
                lstEntrevistadores.Items.Clear();

                boUsuario = new UsuarioWSClient();
                boPostulante = new PostulanteWSClient(); ;

                var usuarios = boUsuario.listarUsuarios();
                var postulantes = boPostulante.listarPostulantes();

                /*
                foreach (var p in postulantes)
                {
                    var nombre = p.nombre;
                    var id = p.id.ToString();
                    ScriptManager.RegisterStartupScript(this, GetType(),"slcPostulante", "addOptions(slcPostulante,nombre,id);",true);
                }
                */

                foreach (var p in postulantes)
                {
                    lstPostulantes.Items.Add(new ListItem(p.nombre, p.id.ToString()));
                }

                foreach (var u in usuarios)
                {
                    lstEntrevistadores.Items.Add(new ListItem(u.nombre, u.id.ToString()));
                }

                upNuevaEntrevista.Update();
                ScriptManager.RegisterStartupScript(this, GetType(), "mostrarNuevaEntrevista", "showModalNuevaEntrevista();", true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error al preparar el formulario: {ex.Message}');", true);
            }
        }

        protected void btnGuardarNuevaEntrevista_Click(object sender, EventArgs e)
        {
            boEntrevista = new EntrevistaWSClient();

            try
            {
                // Crear nueva entrevista
                entrevista nuevaEntrevista = new entrevista();
                boPostulante = new PostulanteWSClient();
                var postulantes = boPostulante.listarPostulantes();
                boUsuario = new UsuarioWSClient();
                var usuarios = boUsuario.listarUsuarios();

                /*
                foreach (var p in postulantes)
                {
                    if (p.id.ToString().Equals(slcPostulante.Text))
                    {
                        nuevaEntrevista.postulante = p;
                    }
                }
                */

                //Hacer que esto este bonito
                //nuevaEntrevista.postulante = txtPostulante;

                foreach (ListItem item in lstPostulantes.Items)
                {
                    if (item.Selected)
                    {
                        int idEntrevistado = int.Parse(item.Value);
                        foreach (var p in postulantes)
                        {
                            if (p.id.Equals(idEntrevistado))
                            {
                                nuevaEntrevista.postulante = p;
                                break;
                            }
                        }
                    }
                }

                nuevaEntrevista.fecha = DateTime.Parse(txtFecha.Text);
                nuevaEntrevista.fechaSpecified = true;

                //NO HAY HORA NMMS

                nuevaEntrevista.estado = estadoEntrevista.PROGRAMADA;
                nuevaEntrevista.estadoSpecified = true;



                List<usuario> entrevistadores = new List<usuario> { };

                foreach (ListItem item in lstEntrevistadores.Items)
                {
                    if (item.Selected)
                    {
                        int idEncargado = int.Parse(item.Value);
                        entrevistadores.Add(boUsuario.obtenerPorId(idEncargado));
                    }
                }

                nuevaEntrevista.entrevistadores = entrevistadores.ToArray();

                boEntrevista.insertarEntrevista(nuevaEntrevista);

                CargarEntrevistas();
                ScriptManager.RegisterStartupScript(this, GetType(), "cerrarModal", @"
                setTimeout(function () {
                    if (typeof bootstrap !== 'undefined') {
                        var modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('modalNuevaEntrevista'));
                        modal.hide();
                    }
                }, 300);", true);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error al guardar entrevista: {ex.Message}');", true);
            }
        }

        /*
        protected void btnGuardarNuevaTarea_Click(object sender, EventArgs e)
        {
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
        */
    }
}