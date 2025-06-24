using GDPTalentoWA.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.EnterpriseServices;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GDPTalentoWA.Paginas
{
    public partial class Reportes : System.Web.UI.Page
    {
        private StaffWSClient boStaff;
        private BindingList<staff> miembros;
        private PostulanteWSClient boPostulante;
        private BindingList<postulante> postulantes;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                cargarDataGeneral();

                MostrarPanel("OIE");
            }
        }

        private void cargarDataGeneral()
        {
            boStaff = new StaffWSClient();
            boPostulante = new PostulanteWSClient();

            var listaStaff = boStaff.listarStaff();
            if (listaStaff == null)
                miembros = new BindingList<staff>();
            else
                miembros = new BindingList<staff>(listaStaff);

            var listaPostulantes = boPostulante.listarPostulantes();
            if (listaPostulantes == null)
                postulantes = new BindingList<postulante>();
            else
                postulantes = new BindingList<postulante>(listaPostulantes);

            //Cargar los datos de OIE
            lblTotalMiembros.Text = miembros.Count.ToString();
            lblMiembrosActivos.Text = miembros.Count(s => s.estado.ToString() == "ACTIVO").ToString();
            lblMiembrosInactivos.Text = (miembros.Count - miembros.Count(s => s.estado.ToString() == "ACTIVO")).ToString();

            //Cargar los datos de postulantes
            lblTotalPostulantes.Text = postulantes.Count.ToString();
            lblAceptados.Text = postulantes.Count(s => s.estadoProceso.ToString() == "APROBADO").ToString();
            lblPendientes.Text = postulantes.Count(s => s.estadoProceso.ToString() == "PENDIENTE").ToString();
            lblRechazados.Text = postulantes.Count(s => s.estadoProceso.ToString() == "RECHAZADO").ToString();
        }

        protected void ddlAreas_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (miembros == null)
            {
                boStaff = new StaffWSClient();
                var listaStaff = boStaff.listarStaff();
                miembros = listaStaff == null ? new BindingList<staff>() : new BindingList<staff>(listaStaff);
            }

            string areaSeleccionada = ddlAreas.SelectedValue;

            var miembrosFiltrados = (areaSeleccionada == "0")
                ? miembros
                : new BindingList<staff>(miembros.Where(m => m.area.ToString() == areaSeleccionada).ToList());

            // Actualizar labels
            lblMiembros.Text = miembrosFiltrados.Count.ToString();
            //lblAsistencia.Text = miembrosFiltrados.Count > 0
                //? $"{miembrosFiltrados.Average(m => m.porcentajeAsistencia):0.##}%"
                //: "0%";
            lblEvaluacion.Text = miembrosFiltrados.Count > 0
                ? $"{miembrosFiltrados.Average(m => m.desempenio):0.0}/5"
                : "0.0/5";
        }


        protected void btnReporteOIE_Click(object sender, EventArgs e)
        {
            pnlReporteOIE.Visible = true;
            pnlReporteArea.Visible = false;
            pnlReporteCandidatos.Visible = false;

            btnReporteOIE.CssClass = "btn btn-dark";
            btnReporteArea.CssClass = "btn btn-outline-dark";
            btnReporteCandidatos.CssClass = "btn btn-outline-dark";
            MostrarPanel("OIE");
        }

        protected void btnReporteArea_Click(object sender, EventArgs e)
        {
            pnlReporteOIE.Visible = false;
            pnlReporteArea.Visible = true;
            pnlReporteCandidatos.Visible = false;

            btnReporteOIE.CssClass = "btn btn-outline-dark";
            btnReporteArea.CssClass = "btn btn-dark";
            btnReporteCandidatos.CssClass = "btn btn-outline-dark";
            MostrarPanel("Area");
        }

        protected void btnReporteCandidatos_Click(object sender, EventArgs e)
        {
            pnlReporteOIE.Visible = false;
            pnlReporteArea.Visible = false;
            pnlReporteCandidatos.Visible = true;

            btnReporteOIE.CssClass = "btn btn-outline-dark";
            btnReporteArea.CssClass = "btn btn-outline-dark";
            btnReporteCandidatos.CssClass = "btn btn-dark";
            MostrarPanel("Candidatos");
        }

        private void MostrarPanel(string reporte)
        {
            pnlReporteOIE.Visible = reporte == "OIE";
            pnlReporteArea.Visible = reporte == "Area";
            pnlReporteCandidatos.Visible = reporte == "Candidatos";
        }
    }
}