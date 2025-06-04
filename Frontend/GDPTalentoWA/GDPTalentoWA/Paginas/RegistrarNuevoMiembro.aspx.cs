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
                        ddlEstadoAcademico.SelectedValue = miembro.estado.ToString();

                        //dtpFechaIngreso.Value = miembro.fechaIngreso.ToString();
                        //dtpFechaSalida.Value = miembro.fechaSalida.ToString();
                        if (miembro.estado== estadoMiembro.ACTIVO) rbActivo.Checked = true;
                        else rbInactivo.Checked = true;
                    }
                    
                }
                else
                {
                    estado = Estado.Nuevo;
                    miembro = new staff();
                    lblTitulo.Text = "Registrar Nuevo Miembro";
                }
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            boStaff = new StaffWSClient();
            miembro.nombre = txtNombreCompleto.Text;
            miembro.correo = txtCorreoElectronico.Text;
            miembro.codigoPUCP = int.Parse(txtCodigoPUCP.Text);
            miembro.telefono = txtNumeroContacto.Text;
            //miembro.fechaIngreso = DateTime.Parse(dtpFechaIngreso.Value);
            //miembro.fechaSalida = DateTime.Parse(dtpFechaSalida.Value);
            if (rbActivo.Checked) miembro.estado = estadoMiembro.ACTIVO;
            else if (rbInactivo.Checked) miembro.estado = estadoMiembro.INACTIVO;
            
            miembro.facultad= ddlFacultad.SelectedValue;

            miembro.especialidad = ddlEspecialidad.SelectedValue;
            string estadoSeleccionado = ddlEstadoAcademico.SelectedValue;

            if (Enum.TryParse(estadoSeleccionado, out estadoMiembro estadoAcademico))
            {
                miembro.estado = estadoAcademico;
            }

            try
            {
                if (estado == Estado.Nuevo)
                {
                    boStaff.insertarStaff(miembro);
                }
                else if (estado == Estado.Modificar)
                {
                    boStaff.modificarStaff(miembro);
                }
            }
            catch (Exception ex)
            { }

            Response.Redirect("Miembro.aspx");
        }
    }
}