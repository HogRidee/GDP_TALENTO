using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;
namespace GDPTalentoWA.Paginas
{
    public partial class VerDetalles : System.Web.UI.Page
    {
        private BindingList<asistencia> asistencias;
        private AsistenciaWSClient boAsistencia;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                StaffWSClient boStaff = new StaffWSClient();
                TareaWSClient boTarea = new TareaWSClient();

                // El ID viene como string en la URL
                var miembro = (staff)Session["miembroSeleccionado"];


                if (miembro != null)
                {
                    Session["idStaffSeleccionado"] = miembro.id;
                    CargarAsistencias();
                    lblNombre.Text = miembro.nombre;
                    string conGuionArea = miembro.area.ToString();
                    string sinGuionArea;
                    if (conGuionArea == "RECURSOS_HUMANOS") { sinGuionArea = "RECURSOS HUMANOS"; }
                    else if (conGuionArea == "GDP_ACADEMY") { sinGuionArea = "GDP ACADEMY"; }
                    else sinGuionArea = conGuionArea;
                    lblCargo.Text = sinGuionArea;
                    lblEstado.Text = miembro.estado.ToString();
                    string estado = miembro.estado.ToString().Trim().ToUpper();

                    lblEstado.Text = estado;

                    if (estado == "ACTIVO")
                    {
                        lblEstado.CssClass = "estado-activo";
                    }
                    else if (estado == "INACTIVO")
                    {
                        lblEstado.CssClass = "estado-inactivo";
                    }

                    lblCorreo.Text = miembro.correo;
                    lblTelefono.Text = miembro.telefono;
                    lblCodigo.Text = miembro.codigoPUCP.ToString();
                    lblIngreso.Text = miembro.fechaIngreso.ToString("dd/MM/yyyy");
                    lblCarrera.Text = miembro.especialidad;
                    lblEvaluacion.Text = miembro.desempenio.ToString();
                    // This is the line that calls your skill loading method
                    var listarAsistencia = boAsistencia.listarAsistenciasPorStaff(miembro.id);

                    if (listarAsistencia != null)
                    {
                        int total = listarAsistencia.Length;
                        int presente = 0, falta = 0;
                        for (int i = 0; i < listarAsistencia.Length; i++)
                        {
                            if (listarAsistencia[i].asistencia1 == estadoAsistencia.ASISTIO)
                            {
                                presente++;
                            }
                            else falta++;
                        }
                        double porcentajeAsistencia = total > 0 ? (presente * 100.0 / total) : 0;
                        lblAsistencia.Text = porcentajeAsistencia.ToString("F2") + "%";
                        lblPorcentaje.Text = porcentajeAsistencia.ToString("F2") + "%";
                    }
                    else
                    {
                        lblAsistencia.Text = "0%";
                        lblPorcentaje.Text = "No hay registros de asistencias";

                    }
                    LoadSkillsFromListOfStrings();
                    lblCompletadas.Text = "15"; // Hardcoded
                    lblPendientes.Text = "2"; // Hardcoded
                }
            }
        }

        private void CargarAsistencias()
        {
            boAsistencia = new AsistenciaWSClient();
            var miembro = (staff)Session["miembroSeleccionado"];
            var listarAsistencia = boAsistencia.listarAsistenciasPorStaff(miembro.id);
            if (listarAsistencia == null)
                asistencias = new BindingList<asistencia>();
            else
                asistencias = new BindingList<asistencia>(listarAsistencia);

            dgvAsistencia.DataSource = asistencias;
            dgvAsistencia.DataBind();

            Session["asistencias"] = asistencias;
        }
        private void LoadSkillsFromListOfStrings()
        {
            // 1. Create your data source (a list of skill names)
            List<string> skills = new List<string>
            {
                "JavaScript",
                "React",
                "Node.js",
                "MongoDB",
                "HTML5",
                "CSS3",
                "C#"
            };

            // 2. Set the Repeater's DataSource
            rptHabilidades.DataSource = skills;

            // 3. Bind the data to the Repeater
            rptHabilidades.DataBind();
        }

        protected void dgvAsistencia_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            dgvAsistencia.PageIndex = e.NewPageIndex;
            dgvAsistencia.DataBind();
        }

        protected void dgvAsistencia_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                string tipoEvento = DataBinder.Eval(e.Row.DataItem, "evento.tipoEvento")?.ToString() ?? "";

                if (tipoEvento == "REUNION") { tipoEvento = "REUNIÓN"; }
                else if (tipoEvento == "INTEGRACION") { tipoEvento = "INTEGRACIÓN"; }
                e.Row.Cells[0].Text = tipoEvento;

                string tipoPresencia = DataBinder.Eval(e.Row.DataItem, "asistencia1")?.ToString() ?? "";

                if (tipoPresencia == "ASISTIO") { tipoPresencia = "PRESENTE"; }
                else if (tipoPresencia == "FALTO") { tipoPresencia = "FALTA"; }
                else if (tipoPresencia == "JUSTIFICADO") { tipoPresencia = "FALTA JUSTIFICADA"; }
                e.Row.Cells[1].Text = tipoPresencia;

                object fechaObj = DataBinder.Eval(e.Row.DataItem, "evento.fecha");

                if (fechaObj != null && DateTime.TryParse(fechaObj.ToString(), out DateTime fecha))
                {
                    if (fecha > new DateTime(1900, 1, 1))
                    {
                        e.Row.Cells[2].Text = fecha.ToString("dd/MM/yyyy");
                    }
                    else
                    {
                        e.Row.Cells[2].Text = "";
                    }
                }
                else
                {
                    e.Row.Cells[2].Text = "";
                }

            }

        }
    }
}

