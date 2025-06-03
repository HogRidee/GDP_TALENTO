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
        //private UsuarioWSClient boUsuario;
        //private EmpleadoWSClient boEmpleado;
        private usuario usuario;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                
                boUsuario = new UsuarioWSClient();

                try
                {
                    //Reemplazar luego
                    usuario = boUsuario.obtenerPorId(1);

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
                lblFechaIngreso.Text = usuario.fechaIngreso.GetType().ToString();
            else
                lblFechaIngreso.Text = "No disponible";

            lblCarrera.Text = $"{usuario.facultad} {usuario.especialidad} ";
            lblEvaluacion.Text = usuario.desempenio.ToString("0.0");
            ltlEstrellas.Text = GenerarEstrellasHTML(usuario.desempenio);
            // Datos ficticios mientras no se implemente
            lblAsistencia.Text = 50 + "%";
            lblPendientes.Text = "2";
            lblCompletadas.Text = "15";
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