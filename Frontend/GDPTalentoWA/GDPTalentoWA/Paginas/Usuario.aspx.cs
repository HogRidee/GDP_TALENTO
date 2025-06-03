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
            lblCargo.Text = $"{usuario.rol?.nombre} - {usuario.area}";
            lblEstado.Text = usuario.estado.ToString().ToUpper() == "ACTIVO"
                ? "<span class='badge bg-success mb-3'>Activo</span>"
                : "<span class='badge bg-secondary mb-3'>Inactivo</span>";

            lblCorreo.Text = usuario.correo;
            lblTelefono.Text = usuario.telefono;
            lblDireccion.Text = "Dirección no registrada"; // Aún no forma parte del modelo
            lblCodigo.Text = usuario.codigoPUCP.ToString();
            if (usuario.fechaIngreso != null)
                lblFechaIngreso.Text = usuario.fechaIngreso.GetType().ToString();
            else
                lblFechaIngreso.Text = "No disponible";

            lblCarrera.Text = $"{usuario.especialidad} - {usuario.facultad}";
            lblEvaluacion.Text = usuario.desempenio.ToString("0.0");

            // Datos ficticios mientras no se implemente
            lblAsistencia.Text = "90%";
            lblPendientes.Text = "2";
            lblCompletadas.Text = "15";
        }

        protected void btnEditar_Click(object sender, EventArgs e)
        {
            Response.Redirect("modificarUsuario.aspx");
        }
    }
}