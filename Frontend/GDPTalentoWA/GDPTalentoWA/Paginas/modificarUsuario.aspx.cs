using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class modificarUsuario : System.Web.UI.Page
    {
        //private UsuarioWSClient boUsuario;
        private UsuarioWSClient boUsuario;
        //private EmpleadoWSClient boEmpleado;
        private usuario usuario;
        protected void Page_Load(object sender, EventArgs e)
        {
            /*
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
            */
        }
        private void AsignarValores()
        {
            txtNombreCompleto.Text = usuario.nombre;
            //lblCargo.Text = $"{usuario.rol?.nombre} - {usuario.area}";
            chkActivo.Checked = usuario.estado.ToString().ToUpper() == "ACTIVO" ? true : false;

            txtCarrera.Text = $"{usuario.especialidad} - {usuario.facultad}";
            txtCorreo.Text = usuario.correo;
            txtTelefono.Text = usuario.telefono;
            txtDireccion.Text = "Dirección no registrada"; // Aún no forma parte del modelo
            txtCodigo.Text = usuario.codigoPUCP.ToString();
            //if (usuario.fechaIngreso != null)
            //    lblFechaIngreso.Text = usuario.fechaIngreso.GetType().ToString();
            //else
             //   lblFechaIngreso.Text = "No disponible";

            //lblCarrera.Text = $"{usuario.especialidad} - {usuario.facultad}";
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Usuario.aspx");
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Usuario.aspx");
        }

    }
}