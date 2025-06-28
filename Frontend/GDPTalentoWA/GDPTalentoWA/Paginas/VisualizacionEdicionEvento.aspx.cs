using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;
using System.Web.UI.HtmlControls;

namespace GDPTalentoWA.Paginas
{
    public partial class VisualizacionEdicionEvento : System.Web.UI.Page
    {
        private EventoWSClient boEvento;
        private StaffWSClient boStaff;
        private BindingList<staff> staffs;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Cargo el evento desde el servicio y lo pongo en sesión
                if (int.TryParse(Request.QueryString["id"], out int idEvento))
                {
                    if(boEvento == null)
                    {
                        boEvento = new EventoWSClient();
                    }
                    
                    //var lista_eventos = boEvento.listarEventos();


                    evento eventoActual = boEvento.listarEventos()
                                                ?.FirstOrDefault(x => x.id == idEvento);
                    if (eventoActual != null)
                        Session["eventoActual"] = eventoActual;
                }

                dtpFechaEvento.Disabled = true;
                CargarDatosEvento();
                CargarStaff();
            }
        }

        protected void dgvEventos_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType != DataControlRowType.DataRow) return;

            var staffActual = (staff)e.Row.DataItem;
            var chk = (CheckBox)e.Row.FindControl("chkParticipando");
            var chk2 = (CheckBox)e.Row.FindControl("chkEncargado");

            chk.Enabled = ModoEdicion;
            chk2.Enabled = ModoEdicion;

            chk2.Visible = false;

            UsuarioWSClient bouser;
            bouser = new UsuarioWSClient();

            var lista_usuarios = bouser.listarUsuarios();

            foreach (usuario us in lista_usuarios) 
            {
                if(us.id == staffActual.id) chk2.Visible = true;
            }

            var eventoTraido = Session["eventoActual"] as evento;
            if (eventoTraido == null) return;

            // Marco ambos checkboxes leyendo la lista de participantes y encargados
            chk.Checked = eventoTraido.participantes?.Any(p => p.id == staffActual.id) == true;
            chk2.Checked = eventoTraido.encargados?.Any(u => u.id == staffActual.id) == true;
        }

        private void CargarStaff()
        {
            if(boStaff==null)
                boStaff = new StaffWSClient();
            var listaStaff = boStaff.listarStaff();
            if(listaStaff == null)
            {
                staffs = new BindingList<staff>();
            }
            else
            {
                staffs = new BindingList<staff>(listaStaff);
            }

            Session["staffs"] = staffs;
            dgvEventos.DataSource = staffs;
            dgvEventos.DataBind();
        }

        private void CargarDatosEvento()
        {
            var eventoTraido = Session["eventoActual"] as evento;
            if (eventoTraido == null) return;

            ddlEstado.SelectedValue = eventoTraido.estadoEvento.ToString();
            ddlTipo.SelectedValue = eventoTraido.tipoEvento.ToString();
            dtpFechaEvento.Value = eventoTraido.fecha.ToString("yyyy-MM-dd");
        }

        private bool ModoEdicion
        {
            get { return ViewState["ModoEdicion"] != null && (bool)ViewState["ModoEdicion"]; }
            set { ViewState["ModoEdicion"] = value; }
        }

        protected void dgvEventos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {

        }

        protected void btnEditar_Click(object sender, EventArgs e)
        {
            ModoEdicion = true;
            pnlEditar.Visible = false;
            pnlGuardarCancelar.Visible = true;
            ddlEstado.Enabled = true;
            ddlTipo.Enabled = true;
            dtpFechaEvento.Disabled = false;
            CargarStaff();
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            int totalChk = 0, totalChk2 = 0;
            foreach (GridViewRow row in dgvEventos.Rows)
            {
                var chk = (CheckBox)row.FindControl("chkParticipando");
                var chk2 = (CheckBox)row.FindControl("chkEncargado");

                if (chk != null && chk.Checked) totalChk++;
                if (chk2 != null && chk2.Checked) totalChk2++;
            }
            System.Diagnostics.Debug.WriteLine("Checks participantes marcados: " + totalChk);
            System.Diagnostics.Debug.WriteLine("Checks encargados marcados: " + totalChk2);
            // 1) Obtengo participantes y encargados seleccionados
            var lista = ((BindingList<staff>)Session["staffs"]).ToList();

            UsuarioWSClient bouser= new UsuarioWSClient();
            var lista_usuarios = bouser.listarUsuarios();

            StaffWSClient bostaff = new StaffWSClient();
            var lista_staff = bostaff.listarStaff();

            var escogidos = new List<staff>();
            var encargados = new List<usuario>();

            foreach (GridViewRow row in dgvEventos.Rows)
            {
                var chk = (CheckBox)row.FindControl("chkParticipando");
                var chk2 = (CheckBox)row.FindControl("chkEncargado");
                int id = Convert.ToInt32(dgvEventos.DataKeys[row.RowIndex].Value);

                if (chk != null && chk.Checked)
                {
                    //escogidos.Add(lista.Find(s => s.id == id));
                    foreach (staff stf in lista_staff)
                    {
                        if(stf.id == id)
                        {
                            staff s = bostaff.listarStaff().FirstOrDefault(st => st.id == id);
                            if (s != null) escogidos.Add(s);
                        }
                    }
                }
                if (lista_usuarios!=null && chk2 != null && chk2.Checked)
                {
                    foreach (usuario us in lista_usuarios)
                    {
                        if(us.id == lista.Find(s => s.id == id).id)
                        {
                            if(id!= 0)encargados.Add(bouser.obtenerPorId(id));
                        }
                    }
                }
                    
            }

            // 2) Recupero de sesión el evento que cargué en Page_Load
            var eventoTraido = (evento)Session["eventoActual"];
            if (eventoTraido == null) return;

            // 3) Validación de campos obligatorios
            if (string.IsNullOrEmpty(ddlEstado.SelectedValue) ||
                string.IsNullOrEmpty(ddlTipo.SelectedValue) ||
                string.IsNullOrEmpty(dtpFechaEvento.Value))
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "CamposObligatorios",
                    "$('#modalCamposObligatorios').modal('show');", true);
                return;
            }

            // 4) Asigno valores
            eventoTraido.estadoEvento = (estadoEvento)Enum.Parse(typeof(estadoEvento), ddlEstado.SelectedValue);
            eventoTraido.estadoEventoSpecified = true;
            eventoTraido.tipoEvento = (tipoEvento)Enum.Parse(typeof(tipoEvento), ddlTipo.SelectedValue);
            eventoTraido.tipoEventoSpecified = true;
            DateTime.TryParse(dtpFechaEvento.Value, out DateTime fechaIngreso);
            eventoTraido.fecha = fechaIngreso;
            eventoTraido.fechaSpecified = true;

            eventoTraido.participantes = escogidos.Where(s => s != null && s.id != 0).ToArray();

            eventoTraido.encargados = encargados.Where(u => u != null && u.id != 0).ToArray();

            // 5) Llamo AL BACKEND: éste ya hará modificarEvento + participantes + encargados
            if (boEvento==null)
                boEvento = new EventoWSClient();
            

            
            boEvento.modificarEvento(eventoTraido);

            // 6) Actualizo mi sesión única
            Session["eventoActual"] = eventoTraido;

            // 7) Desactivo modo edición y refresco UI
            ModoEdicion = false;
            pnlEditar.Visible = true;
            pnlGuardarCancelar.Visible = false;
            CargarDatosEvento();
            CargarStaff();
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Eventos.aspx");
            ModoEdicion = false;
            pnlEditar.Visible = true;
            pnlGuardarCancelar.Visible = false;
        }
    }
}