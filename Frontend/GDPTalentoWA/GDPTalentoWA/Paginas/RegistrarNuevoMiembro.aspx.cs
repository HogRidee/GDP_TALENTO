using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;
//using NodaTime;

namespace GDPTalentoWA.Paginas
{
    public partial class RegistrarNuevoMiembro : System.Web.UI.Page
    {
        private Estado estado;
        private staff miembro;
        private StaffWSClient boStaff;

        protected Estado EstadoActual
        {
            get => (Estado)(ViewState["Estado"] ?? Estado.Nuevo);
            set => ViewState["Estado"] = value;
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string accion = Request.QueryString["accion"];

                if (accion == null)
                {
                    EstadoActual = Estado.Nuevo;
                    miembro = new staff();
                    lblTitulo.Text = "Registrar Nuevo Miembro";
                }
                else if (accion == "modificar")
                {
                    EstadoActual = Estado.Modificar;
                    lblTitulo.Text = "Editar Miembro";

                    miembro = (staff)Session["miembroSeleccionado"];

                    if (miembro != null)
                    {
                        txtNombreCompleto.Text = miembro.nombre;
                        txtCorreoElectronico.Text = miembro.correo;
                        txtCodigoPUCP.Text = miembro.codigoPUCP.ToString();
                        txtNumeroContacto.Text = miembro.telefono;
                        ddlFacultad.SelectedValue = miembro.facultad;
                        ddlEspecialidad.SelectedValue = miembro.especialidad;
                        ddlEstadoAcademico.SelectedValue = miembro.status.ToString();
                        ddlAreas.SelectedValue = miembro.area.ToString();

                        dtpFechaIngreso.Value = miembro.fechaIngreso.ToString("yyyy-MM-dd");

                        if (miembro.fechaSalida != null && miembro.fechaSalida > DateTime.MinValue)
                            dtpFechaSalida.Value = miembro.fechaSalida.ToString("yyyy-MM-dd");
                        else
                            dtpFechaSalida.Value = "";

                        if (miembro.estado == estadoMiembro.ACTIVO) rbActivo.Checked = true;
                        else rbInactivo.Checked = true;
                    }
                    Session["miembroSeleccionado"] = miembro;
                }
            }
        }




        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boStaff = new StaffWSClient();

            if (EstadoActual == Estado.Nuevo)
            {
                miembro = new staff();
            }
            else
            {
                // Validación extra para evitar null
                if (Session["miembroSeleccionado"] is staff m)
                {
                    miembro = m;
                }
                else
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "msg", "alert('Error: El miembro no fue encontrado en sesión.');", true);
                    return;
                }
            }

            // Asignar valores
            miembro.nombre = txtNombreCompleto.Text;
            if (txtNombreCompleto.Text=="") {
                lblMensajeError.Text = "Debe ingresar nombre completo";
                string script = "mostrarModalErrorRegistro();";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);

                return;
            }
            miembro.correo = txtCorreoElectronico.Text;
            if (txtCorreoElectronico.Text == "")
            {
                lblMensajeError.Text = "Debe ingresar correo electrónico";
                string script = "mostrarModalErrorRegistro();";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);

                return;
            }

            miembro.telefono = txtNumeroContacto.Text;
            if (txtNumeroContacto.Text == "")
            {
                lblMensajeError.Text = "Debe ingresar número de contacto";
                string script = "mostrarModalErrorRegistro();";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);

                return;
            }

            if (txtCodigoPUCP.Text == "")
            {
                lblMensajeError.Text = "Debe ingresar código PUCP";
                string script = "mostrarModalErrorRegistro();";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);

                return;
            }

            if (int.TryParse(txtCodigoPUCP.Text, out int codigo))
                miembro.codigoPUCP = codigo;
            else
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "msg", "alert('Código PUCP inválido');", true);
                return;
            }

            miembro.facultad = ddlFacultad.SelectedValue;
            if (string.IsNullOrWhiteSpace(ddlFacultad.SelectedValue))
            {
                lblMensajeError.Text = "Debe seleccionar facultad";
                string script = "mostrarModalErrorRegistro();";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);

                return;
            }
            miembro.especialidad = ddlEspecialidad.SelectedValue;
            if (string.IsNullOrWhiteSpace(ddlEspecialidad.SelectedValue))
            {
                lblMensajeError.Text = "Debe seleccionar especialidad";
                string script = "mostrarModalErrorRegistro();";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);

                return;
            }

            if (string.IsNullOrWhiteSpace(ddlEstadoAcademico.SelectedValue))
            {
                lblMensajeError.Text = "Debe seleccionar estado académico";
                string script = "mostrarModalErrorRegistro();";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);

                return;
            }

            if (Enum.TryParse(ddlEstadoAcademico.SelectedValue, out estadoPUCP estadoAcademico))
            {
                miembro.status = estadoAcademico;
                miembro.statusSpecified = true;
            }

            if (string.IsNullOrWhiteSpace(ddlAreas.SelectedValue))
            {
                lblMensajeError.Text = "Debe seleccionar un área.";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);
                return;
            }

            miembro.area = (area)Enum.Parse(typeof(area), ddlAreas.SelectedValue);
            miembro.areaSpecified = true;
            // Fechas

            if (string.IsNullOrWhiteSpace(dtpFechaIngreso.Value))
            {
                lblMensajeError.Text = "Debe seleccionar fecha de ingreso";
                string script = "mostrarModalErrorRegistro();";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);

                return;
            }
            if (DateTime.TryParse(dtpFechaIngreso.Value, out DateTime fechaIngreso))
            {
                miembro.fechaIngreso = fechaIngreso;
                miembro.fechaIngresoSpecified = true;
            }

            if (!string.IsNullOrWhiteSpace(dtpFechaSalida.Value) &&
                DateTime.TryParse(dtpFechaSalida.Value, out DateTime fechaSalida))
            {
                miembro.fechaSalida = fechaSalida;
                miembro.fechaSalidaSpecified = true;
            }

            // Otros campos
            
           

            miembro.desempenio = 4;

            if (!(rbActivo.Checked))
            {
                lblMensajeError.Text = "Debe seleccionar estado (ACTIVO O INACTIVO)";
                string script = "mostrarModalErrorRegistro();";
                ScriptManager.RegisterStartupScript(this, GetType(), "modalError", "mostrarModalErrorRegistro();", true);

                return;
            }
            if (rbActivo.Checked)
                miembro.estado = estadoMiembro.ACTIVO;
            else
                miembro.estado = estadoMiembro.INACTIVO;
            miembro.estadoSpecified = true;

           

            try
            {
                if (EstadoActual == Estado.Nuevo)
                    boStaff.insertarStaff(miembro);
                else
                    boStaff.modificarStaff(miembro);
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error al guardar: {ex.Message}');", true);
                return;
            }

            // Redirigir
            Response.Redirect("Miembro.aspx");
        }

    }
}