using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;

namespace GDPTalentoWA.Paginas
{
    public partial class RegistrarEvento : System.Web.UI.Page
    {
        private EventoWSClient boEvento;
        private StaffWSClient boStaff;
        private BindingList<staff> staffs;
        private List<usuario> usuarios;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                boEvento = new EventoWSClient();
                Session["eventoActual"] = new evento(); // Crea un nuevo evento vacío
                usuarios = new UsuarioWSClient().listarUsuarios()?.ToList() ?? new List<usuario>();
                Session["usuarios"] = usuarios;
                CargarStaff();
            }
        }

        protected void dgvRegistrarEventos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType != DataControlRowType.DataRow) return;

            var staffActual = (staff)e.Row.DataItem;
            var chk = (CheckBox)e.Row.FindControl("chkParticipando");
            var chk2 = (CheckBox)e.Row.FindControl("chkEncargado");

            chk.Enabled = true;

            if (usuarios == null && Session["usuarios"] != null)
                usuarios = (List<usuario>)Session["usuarios"];

            bool esEncargado = usuarios.Any(u => u.id == staffActual.id);
            chk2.Visible = esEncargado;
            chk2.Enabled = esEncargado;
        }

        private void CargarStaff()
        {
            
            if(boStaff==null)
                boStaff = new StaffWSClient();
            var listaStaff = boStaff.listarStaff();
            staffs = listaStaff == null ? new BindingList<staff>() : new BindingList<staff>(listaStaff);
            Session["staffs"] = staffs;
            dgvEventos.DataSource = staffs;
            dgvEventos.DataBind();
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(ddlEstado.SelectedValue) ||
                string.IsNullOrEmpty(ddlTipo.SelectedValue) ||
                string.IsNullOrEmpty(dtpFechaEvento.Value))
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "CamposObligatorios",
                    "$('#modalCamposObligatorios').modal('show');", true);
                return;
            }

            var lista = ((BindingList<staff>)Session["staffs"]).ToList();
            UsuarioWSClient bouser = new UsuarioWSClient();
            StaffWSClient bostaff = new StaffWSClient();
            var lista_staff = bostaff.listarStaff();
            var lista_usuarios = bouser.listarUsuarios();

            var escogidos = new List<staff>();
            var encargados = new List<usuario>();

            foreach (GridViewRow row in dgvEventos.Rows)
            {
                var chk = (CheckBox)row.FindControl("chkParticipando");
                var chk2 = (CheckBox)row.FindControl("chkEncargado");
                int id = Convert.ToInt32(dgvEventos.DataKeys[row.RowIndex].Value);

                if (chk != null && chk.Checked)
                {
                    var s = lista_staff.FirstOrDefault(st => st.id == id);
                    if (s != null) escogidos.Add(s);
                }

                if (chk2 != null && chk2.Checked)
                {
                    var usuarioSeleccionado = lista_usuarios.FirstOrDefault(u => u.id == id);
                    if (usuarioSeleccionado != null)
                        encargados.Add(bouser.obtenerPorId(id));
                }
            }

            DateTime.TryParse(dtpFechaEvento.Value, out DateTime fechaIngreso);

            var nuevoEvento = new evento
            {
                tipoEvento = (tipoEvento)Enum.Parse(typeof(tipoEvento), ddlTipo.SelectedValue),
                tipoEventoSpecified = true,
                estadoEvento = (estadoEvento)Enum.Parse(typeof(estadoEvento), ddlEstado.SelectedValue),
                estadoEventoSpecified = true,
                fecha = fechaIngreso,
                fechaSpecified = true,
                participantes = escogidos.ToArray(),
                encargados = encargados.ToArray()
            };

            boEvento = new EventoWSClient();
            boEvento.insertarEvento(nuevoEvento);

            Response.Redirect("Eventos.aspx");
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Eventos.aspx");
        }
    }
}