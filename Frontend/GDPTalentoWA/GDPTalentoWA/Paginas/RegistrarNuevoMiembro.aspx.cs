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

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string accion = Request.QueryString["accion"];
                string idStr = Request.QueryString["id"];
                int idMiembro;

                if (accion == "modificar" && int.TryParse(idStr, out idMiembro))
                {
                    EstadoActual = Estado.Modificar; 
                    ViewState["IdMiembro"] = idMiembro;
                    lblTitulo.Text = "Editar Miembro";

                    StaffWSClient boStaff = new StaffWSClient();
                    var listaStaff = boStaff.listarStaff();

                    // Buscar el miembro con el id correspondiente
                    staff miembro = listaStaff?.FirstOrDefault(x => x.id == idMiembro);

                    if (miembro != null)
                    {
                        txtNombreCompleto.Text = miembro.nombre;
                        txtCorreoElectronico.Text = miembro.correo;
                        txtCodigoPUCP.Text = miembro.codigoPUCP.ToString();
                        txtNumeroContacto.Text = miembro.telefono;
                        ddlFacultad.SelectedValue = miembro.facultad;
                        ddlEspecialidad.SelectedValue = miembro.especialidad;
                        ddlEstadoAcademico.SelectedValue = miembro.status.ToString();

                        dtpFechaIngreso.Value = miembro.fechaIngreso.ToString("yyyy-MM-dd");

                        if (miembro.fechaSalida != null && miembro.fechaSalida > DateTime.MinValue)
                        {
                            dtpFechaSalida.Value = miembro.fechaSalida.ToString("yyyy-MM-dd");
                        }
                        else
                        {
                            dtpFechaSalida.Value = ""; // Oculta el 01/01/0001
                        }


                        if (miembro.estado== estadoMiembro.ACTIVO) rbActivo.Checked = true;
                        else rbInactivo.Checked = true;
                    }
                    
                }
                else
                {
                    EstadoActual = Estado.Nuevo;
                    estado = Estado.Nuevo;
                    miembro = new staff();
                    lblTitulo.Text = "Registrar Nuevo Miembro";
                }
            }
        }
        private Estado EstadoActual
        {
            get => (Estado)(ViewState["Estado"] ?? Estado.Nuevo);
            set => ViewState["Estado"] = value;
        }


        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boStaff = new StaffWSClient();
            miembro = new staff();

            if (EstadoActual == Estado.Modificar && ViewState["IdMiembro"] != null)
            {
                miembro.id = (int)ViewState["IdMiembro"];
            }

            miembro.nombre = txtNombreCompleto.Text;
            miembro.correo = txtCorreoElectronico.Text;
            miembro.codigoPUCP = int.Parse(txtCodigoPUCP.Text);
            miembro.telefono = txtNumeroContacto.Text;
            miembro.fechaIngreso = DateTime.Parse(dtpFechaIngreso.Value);

            if (!string.IsNullOrWhiteSpace(dtpFechaSalida.Value))
            {
                if (DateTime.TryParse(dtpFechaSalida.Value, out DateTime fechaSalida))
                {
                    miembro.fechaSalida = fechaSalida;
                    miembro.fechaSalidaSpecified = true;
                }
            }
            else
            {
                miembro.fechaSalidaSpecified = false;
            }

            miembro.facultad = ddlFacultad.SelectedValue;
            miembro.especialidad = ddlEspecialidad.SelectedValue;

            if (rbActivo.Checked) miembro.estado = estadoMiembro.ACTIVO;
            else if (rbInactivo.Checked) miembro.estado = estadoMiembro.INACTIVO;

            string estadoSeleccionado = ddlEstadoAcademico.SelectedValue;

            if (Enum.TryParse(estadoSeleccionado, out estadoPUCP estadoAcademico))
            {
                miembro.status = estadoAcademico;
            }

            try
            {
                if (EstadoActual == Estado.Nuevo)
                {
                    boStaff.insertarStaff(miembro);
                }
                else if (EstadoActual == Estado.Modificar)
                {
                    boStaff.modificarStaff(miembro);
                }
            }
            catch (Exception ex)
            {
                // para depurar, muestra el error temporalmente
                Response.Write("Error: " + ex.Message);
            }

            Response.Redirect("Miembro.aspx");
        }

    }
}