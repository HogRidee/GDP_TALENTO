<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="ReportesMostrar.aspx.cs" Inherits="GDPTalentoWA.Paginas.ReportesMostrar" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
    <h1 class="text-2xl font-bold">Reportes</h1>
    <p class="text-gray-500">Visualiza y exporta reportes sobre la organización.</p>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container bg-white p-4 rounded-3 shadow-sm">
        <div class="btn-group mb-4">
            <asp:Button ID="btnReporteOIE" runat="server" Text="Reporte OIE" CssClass="btn btn-outline-dark active" OnClick="btnReporteOIE_Click" />
            <asp:Button ID="btnReporteArea" runat="server" Text="Reporte por Área" CssClass="btn btn-outline-dark" OnClick="btnReporteArea_Click" />
            <asp:Button ID="btnReporteCandidatos" runat="server" Text="Reporte de Candidatos" CssClass="btn btn-outline-dark" OnClick="btnReporteCandidatos_Click" />
        </div>

        <!-- Panel Reporte OIE -->
        <asp:Panel ID="pnlReporteOIE" runat="server">
            <h2 class="h4 fw-bold">Reporte OIE del Staff</h2>
            <p class="text-muted mb-4">Reporte institucional con información completa de los miembros activos.</p>
            <div class="row g-3">
                <div class="col-md-4">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Total Miembros</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblTotalMiembros" runat="server" Text="0" />
                        </h3>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Miembros Activos</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblMiembrosActivos" runat="server" Text="0" />
                        </h3>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Miembros Inactivos</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblMiembrosInactivos" runat="server" Text="0" />
                        </h3>
                    </div>
                </div>
            </div>

            <div class="d-flex justify-content-end mt-4">
                <asp:Button ID="btnExportarOIE" runat="server" Text="Exportar CSV" CssClass="btn btn-dark" OnClick="btnExportarOIE_Click" />
            </div>
        </asp:Panel>

        <!-- Panel Reporte por Área -->
        <asp:Panel ID="pnlReporteArea" runat="server" Visible="false">
            <h2 class="h4 fw-bold">Reporte por Área</h2>
            <p class="text-muted mb-4">Métricas clave y análisis comparativo por área.</p>
            <asp:DropDownList ID="ddlAreas" runat="server" CssClass="form-select mb-3 w-auto"
                AutoPostBack="true" OnSelectedIndexChanged="ddlAreas_SelectedIndexChanged">
                <asp:ListItem Text="Todas las áreas" Value="0" />
                <asp:ListItem Text="Presidencia" Value="PRESIDENCIA" />
                <asp:ListItem Text="Marketing y Comunicaciones" Value="MARKETING" />
                <asp:ListItem Text="Eventos" Value="EVENTOS" />
                <asp:ListItem Text="GDP Academy" Value="GDP_ACADEMY" />
                <asp:ListItem Text="Recursos Humanos" Value="RECURSOS_HUMANOS" />

            </asp:DropDownList>
            <div class="row g-3">
                <div class="col-md-4">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Miembros</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblMiembros" runat="server" Text="12" /></h3>
                    </div>
                </div>
                <!----
                <div class="col-md-4">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Asistencia Promedio</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblAsistencia" runat="server" Text="85%" /></h3>
                    </div>
                </div>
                    --->
                <div class="col-md-4">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Evaluación Promedio</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblEvaluacion" runat="server" Text="4.2/5" /></h3>
                    </div>
                </div>
            </div>

            <div class="d-flex justify-content-end mt-4">
                <asp:Button ID="btnExportarArea" runat="server" Text="Exportar PDF" CssClass="btn btn-dark" OnClick="btnExportarArea_Click" />
            </div>
        </asp:Panel>

        <!-- Panel Reporte de Candidatos -->
        <asp:Panel ID="pnlReporteCandidatos" runat="server" Visible="false">
            <h2 class="h4 fw-bold">Reporte de Candidatos</h2>
            <p class="text-muted mb-4">Historial completo del proceso de entrevistas y selección.</p>
            <div class="row g-3">
                <div class="col-md-3">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Total Postulantes</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblTotalPostulantes" runat="server" Text="0" />
                        </h3>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Aceptados</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblAceptados" runat="server" Text="0" />
                        </h3>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Pendientes</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblPendientes" runat="server" Text="0" />
                        </h3>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="p-3 bg-light rounded text-center">
                        <p class="text-muted mb-1">Rechazados</p>
                        <h3 class="fw-bold mb-0">
                            <asp:Label ID="lblRechazados" runat="server" Text="0" />
                        </h3>
                    </div>
                </div>
            </div>

            <div class="d-flex justify-content-end mt-4">
                <asp:Button ID="btnExportarCandidatos" runat="server" Text="Exportar PDF" CssClass="btn btn-dark" OnClick="btnExportarCandidatos_Click" />
            </div>
        </asp:Panel>
    </div>
</asp:Content>