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
            miembro.correo = txtCorreoElectronico.Text;

            if (int.TryParse(txtCodigoPUCP.Text, out int codigo))
                miembro.codigoPUCP = codigo;
            else
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "msg", "alert('Código PUCP inválido');", true);
                return;
            }

            miembro.telefono = txtNumeroContacto.Text;

            // Fechas
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
            miembro.facultad = ddlFacultad.SelectedValue;
            miembro.especialidad = ddlEspecialidad.SelectedValue;
            miembro.area = (area)Enum.Parse(typeof(area), ddlAreas.SelectedValue);
            miembro.areaSpecified = true;
            miembro.desempenio = 4;

            if (rbActivo.Checked)
                miembro.estado = estadoMiembro.ACTIVO;
            else
                miembro.estado = estadoMiembro.INACTIVO;
            miembro.estadoSpecified = true;

            if (Enum.TryParse(ddlEstadoAcademico.SelectedValue, out estadoPUCP estadoAcademico))
            {
                miembro.status = estadoAcademico;
                miembro.statusSpecified = true;
            }

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