using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.HtmlControls;

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
            
            if (!IsPostBack)
            {
                boUsuario = new UsuarioWSClient();
                LlenarAreas();
                LlenarStatus();
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
            txtNombreCompleto.Text = usuario.nombre;
            //lblCargo.Text = $"{usuario.rol?.nombre} - {usuario.area}";
            chkActivo.Checked = usuario.estado.ToString().ToUpper() == "ACTIVO" ? true : false;
            txtCorreo.Text = usuario.correo;
            txtTelefono.Text = usuario.telefono;
            txtCodigo.Text = usuario.codigoPUCP.ToString();

            //Parte Derecha
            ddlArea.SelectedValue = usuario.area.ToString();
            ddlArea.DataBind();
            ddlStatus.SelectedValue = usuario.status.ToString();
            ddlStatus.DataBind();
            txtFacultad.Text = usuario.facultad;
            txtCarrera.Text = usuario.especialidad;
            //if (usuario.fechaIngreso != null)
            //    lblFechaIngreso.Text = usuario.fechaIngreso.GetType().ToString();
            //else
            //   lblFechaIngreso.Text = "No disponible";

            //lblCarrera.Text = $"{usuario.especialidad} - {usuario.facultad}";
        }

        private void LlenarAreas()
        {
            ddlArea.DataSource = Enum.GetNames(typeof(area));
            ddlArea.DataBind();
        }

        private void LlenarStatus()
        {
            ddlStatus.DataSource = Enum.GetNames(typeof(estadoPUCP));
            ddlStatus.DataBind();
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boUsuario = new UsuarioWSClient();  
            usuario = new usuario();
            usuario.id = 1;
            usuario.nombre = txtNombreCompleto.Text;
            usuario.codigoPUCP = Int32.Parse(txtCodigo.Text);
            usuario.correo = txtCorreo.Text;
            usuario.telefono = txtTelefono.Text;
            usuario.status = (estadoPUCP)Enum.Parse(typeof(estadoPUCP), ddlStatus.SelectedValue);
            usuario.statusSpecified = true;
            usuario.area = (area)Enum.Parse(typeof(area), ddlArea.SelectedValue);
            usuario.areaSpecified = true;
            /*
            try
            {
                DateTime fecha = DateTime.Parse(dtpFechaIngreso.Value);

                usuario.fechaIngreso = new ServicioWeb.localDate
                {
                    year = fecha.Year,
                    month = fecha.Month,
                    day = fecha.Day
                };

            }
            catch (Exception ex)
            { //lanzarMensajedeError("Debe seleccionar una fecha de ingreso");
              return; 
            }
            */
            usuario.estado = chkActivo.Checked ? estadoMiembro.ACTIVO : estadoMiembro.INACTIVO;
            usuario.estadoSpecified = true;
            usuario.facultad = txtFacultad.Text;
            usuario.especialidad = txtCarrera.Text;
            
            try
            {
                boUsuario.modificarUsuarioBasico(usuario);
                Response.Redirect("Usuario.aspx");
            }
            catch (Exception ex)
            { //lanzarMensajedeError(ex.Message);
              return; 
            }     
        }
        public void lanzarMensajedeError(String mensaje)
        {
            lblMensajeError.Text = mensaje;
            string script = "mostrarModalError();";
            ScriptManager.RegisterStartupScript(this, GetType(), "modalError", script, true);
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Usuario.aspx");
        }

    }
}