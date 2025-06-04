using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Security.Cryptography;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;


namespace GDPTalentoWA.Paginas
{
    public partial class Miembro : System.Web.UI.Page
    {
        private MiembroPUCPWSClient boMiembro;
        private StaffWSClient boStaff;
        private BindingList<miembroPUCP> miembros;
        private BindingList<staff> staffs;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarStaff();
            }
        }

        private void CargarStaff()
        {
            boStaff = new StaffWSClient();
            var listaStaff = boStaff.listarStaff();
            if (listaStaff == null)
                staffs = new BindingList<staff>();
            else
                staffs = new BindingList<staff>(listaStaff);

            dgvMiembros.DataSource = staffs;
            dgvMiembros.DataBind();

            ViewState["Staffs"] = staffs;
        }


        protected void btnRegistrarMiembro_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarNuevoMiembro.aspx");
        }

        protected void lbBuscarMiembro_Click(object sender, EventArgs e)
        {
            string textoBusqueda = txtBuscarMiembro.Text.Trim().ToLower();

            // Asegúrate de tener la lista actual cargada
            boStaff = new StaffWSClient();
            var listaStaff = boStaff.listarStaff();

            if (listaStaff == null)
            {
                staffs = new BindingList<staff>();
            }
            else
            {
                // Filtro por nombre, código PUCP o área
                var filtrados = listaStaff.Where(s =>
                    (s.nombre != null && s.nombre.ToLower().Contains(textoBusqueda)) ||
                    s.codigoPUCP.ToString().Contains(textoBusqueda) ||
                    s.area.ToString().ToLower().Contains(textoBusqueda)
                ).ToList();

                staffs = new BindingList<staff>(filtrados);
            }

            dgvMiembros.DataSource = staffs;
            dgvMiembros.DataBind();
        }


        protected void dgvMiembros_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                e.Row.Cells[0].Text = DataBinder.Eval(e.Row.DataItem, "nombre")?.ToString() ?? "";
                e.Row.Cells[1].Text = DataBinder.Eval(e.Row.DataItem, "codigoPUCP")?.ToString() ?? "";
                e.Row.Cells[2].Text = DataBinder.Eval(e.Row.DataItem, "area")?.ToString() ?? "";
                e.Row.Cells[3].Text = DataBinder.Eval(e.Row.DataItem, "estado")?.ToString() ?? "";

                e.Row.Cells[4].Text = (string)DataBinder.Eval(e.Row.DataItem, "fechaIngreso");

            }
        }



        protected void dgvMiembros_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvMiembros.PageIndex = e.NewPageIndex;
            dgvMiembros.DataBind();
        }

        protected void btnFiltrarMiembro_Click(object sender, EventArgs e)
        {
            boStaff = new StaffWSClient();
            var listaOriginal = boStaff.listarStaff();

            if (listaOriginal == null) return;

            string textoBusqueda = txtBuscarMiembro.Text.Trim().ToLower();
            string estadoSeleccionado = ddlEstados.SelectedValue; // "1" = ACTIVO, "2" = INACTIVO
            string areaSeleccionada = ddlAreas.SelectedValue;      // Ej: "Marketing"

            var listaFiltrada = listaOriginal.Where(s =>
                (string.IsNullOrEmpty(textoBusqueda) ||
                 s.nombre.ToLower().Contains(textoBusqueda) ||
                 s.codigoPUCP.ToString().Contains(textoBusqueda) ||
                 s.area.ToString().ToLower().Contains(textoBusqueda)) &&

                (string.IsNullOrEmpty(estadoSeleccionado) ||
                 (estadoSeleccionado == "1" && s.estado == estadoMiembro.ACTIVO) ||
                 (estadoSeleccionado == "2" && s.estado == estadoMiembro.INACTIVO)) &&

                (string.IsNullOrEmpty(areaSeleccionada) ||
                 s.area.ToString().Equals(areaSeleccionada, StringComparison.OrdinalIgnoreCase))
            ).ToList();

            dgvMiembros.DataSource = listaFiltrada;
            dgvMiembros.DataBind();
        }

        protected void btnEjecutar_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            GridViewRow row = (GridViewRow)btn.NamingContainer;

            DropDownList ddlAcciones = (DropDownList)row.FindControl("ddlAcciones");

            string accion = ddlAcciones.SelectedValue;
            int idMiembro = Convert.ToInt32(btn.CommandArgument);

            // Recargar la lista para asegurarnos que no sea nula
            boStaff = new StaffWSClient();
            var listaStaff = boStaff.listarStaff();
            staffs = new BindingList<staff>(listaStaff);

            switch (accion)
            {
                case "VerDetalles":
                    Response.Redirect($"VerDetalles.aspx?id={idMiembro}");
                    break;

                case "EditarInformacion":
                    staff miembroSeleccionado = staffs.SingleOrDefault(x => x.id == idMiembro);
                    if (miembroSeleccionado != null)
                    {
                        Session["miembroSeleccionado"] = miembroSeleccionado;
                        Response.Redirect($"RegistrarNuevoMiembro.aspx?accion=modificar&id={idMiembro}");
                    }
                    else
                    {
                        ScriptManager.RegisterStartupScript(this, GetType(), "msg", "alert('Miembro no encontrado.');", true);
                    }
                    break;

                case "EliminarMiembro":
                    boStaff.eliminarStaff(idMiembro);
                    staffs = new BindingList<staff>(boStaff.listarStaff());
                    dgvMiembros.DataSource = staffs;
                    dgvMiembros.DataBind();
                    break;

            }
        }



    }
}