using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class Usuario : System.Web.UI.Page
    {
        private UsuarioWSClient boUsuario;
        private TareaWSClient boTarea;
        //private UsuarioWSClient boUsuario;
        //private EmpleadoWSClient boEmpleado;
        private usuario usuario;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                
                boUsuario = new UsuarioWSClient();
                boTarea = new TareaWSClient();
                try
                {
                    //Reemplazar luego
                    int codigo = (int)Session["id"];
                    usuario = boUsuario.obtenerPorId(codigo);

                    if (usuario != null)
                    {
                        AsignarValores();
                    }
                    else
                    {
                        lblNombre.Text = "Usuario no encontrado";
                    }
                }
                catch (Exception ex)
                {
                    lblNombre.Text = "Error al cargar datos";
                    // Idealmente, registra este error en un sistema de logs
                }
                
            }
        }

        private void AsignarValores()
        {
            boTarea = new TareaWSClient();
            var todasLasTareas = boTarea.listarTareas();
            if (todasLasTareas == null)
            {
                lblPendientes.Text = "0";
                lblCompletadas.Text = "0";
                return;
            }

            var tareasUsuario = todasLasTareas
                .Where(t => t.encargados != null && t.encargados.Any(e => e.id == usuario.id))
                .ToList();

            // Separar por estado
            int pendientes = tareasUsuario.Count(t =>  t.estado.ToString() == "PENDIENTE");
            int completadas = tareasUsuario.Count(t => t.estado.ToString() == "REALIZADA");
            lblNombre.Text = usuario.nombre;
            lblCargo.Text = $"Rol: {usuario.rol?.nombre} - Area: {usuario.area}";
            lblEstado.Text = usuario.estado.ToString().ToUpper() == "ACTIVO"
                ? "Activo"
    :           "Inactivo";
            if (usuario.estado.ToString().ToUpper() == "ACTIVO")
                lblEstado.CssClass = "estado estado-activo";
            else
                lblEstado.CssClass = "estado estado-inactivo";

            lblCorreo.Text = usuario.correo;
            lblTelefono.Text = usuario.telefono; 
            lblCodigo.Text = usuario.codigoPUCP.ToString();
            
            if (usuario.fechaIngreso != null)
            {
                DateTime fecha = (DateTime)usuario.fechaIngreso;
                lblFechaIngreso.Text = fecha.ToString("yyyy-MM-dd");
            }
            else
            {
                lblFechaIngreso.Text = "No disponible";
            }

            lblCarrera.Text = $"{usuario.facultad} {usuario.especialidad} ";
            lblEvaluacion.Text = usuario.desempenio.ToString("0.0");
            ltlEstrellas.Text = GenerarEstrellasHTML(usuario.desempenio);
            // Datos ficticios mientras no se implemente
            lblAsistencia.Text = 75 + "%";
            lblPendientes.Text = pendientes.ToString();
            lblCompletadas.Text = completadas.ToString();
        }
        private string GenerarEstrellasHTML(double puntuacion)
        {
            int estrellasLlenas = (int)Math.Floor(puntuacion);
            bool mediaEstrella = (puntuacion - estrellasLlenas) >= 0.5;
            int estrellasVacias = 5 - estrellasLlenas - (mediaEstrella ? 1 : 0);

            string html = "";
            for (int i = 0; i < estrellasLlenas; i++)
                html += "<i class='fa-solid fa-star'></i>";

            if (mediaEstrella)
                html += "<i class='fa-solid fa-star-half-stroke'></i>";

            for (int i = 0; i < estrellasVacias; i++)
                html += "<i class='fa-regular fa-star'></i>";

            return html;
        }
        protected void btnEditar_Click(object sender, EventArgs e)
        {
            Response.Redirect("modificarUsuario.aspx");
        }
    }
}