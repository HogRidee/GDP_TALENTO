<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Inicio.aspx.cs" Inherits="GDPTalentoWA.Paginas.Inicio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
    Inicio
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container mt-3">
        <h2 class="fw-bold mb-0">Bienvenido al panel de Recursos Humanos</h2>
        <p class="text-muted">Gestiona todos los aspectos de GDP Talento desde un solo lugar.</p>
        <!-- CARDS RESUMEN -->
        <div class="row row-cols-1 row-cols-md-4 g-3 mb-4">
            <div class="col">
                <div class="card shadow-sm h-100 position-relative">
                    <div style="position: absolute; top: 14px; right: 16px; color: #6c757d;">
                        <i class="fa-regular fa-user fa-lg"></i>
                    </div>
                    <div class="card-body">
                        <h6 class="text-muted">Total Miembros</h6>
                        <h3 class="fw-bold">45</h3>
                        <small class="text-success">+3 desde el mes pasado</small>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm h-100 position-relative">
                    <div style="position: absolute; top: 14px; right: 16px; color: #6c757d;">
                        <i class="fa-regular fa-user-plus fa-lg"></i>
                    </div>
                    <div class="card-body">
                        <h6 class="text-muted">Postulantes activos</h6>
                        <h3 class="fw-bold">12</h3>
                        <small class="text-success">+5 desde el mes pasado</small>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm h-100 position-relative">
                    <div style="position: absolute; top: 14px; right: 16px; color: #6c757d;">
                        <i class="fa-regular fa-calendar fa-lg"></i>
                    </div>
                    <div class="card-body">
                        <h6 class="text-muted">Eventos Próximos</h6>
                        <h3 class="fw-bold">3</h3>
                        <small class="text-success">Próximo: Workshop de Unity (15/05)</small>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm h-100 position-relative">
                    <div style="position: absolute; top: 14px; right: 16px; color: #6c757d;">
                        <i class="fa-regular fa-square-check fa-lg"></i>
                    </div>
                    <div class="card-body">
                        <h6 class="text-muted">Tareas Pendientes</h6>
                        <h3 class="fw-bold">8</h3>
                        <small class="text-danger">3 con vencimiento esta semana</small>
                    </div>
                </div>
            </div>
        </div>

        <ul class="nav nav-tabs mb-3" id="activityTabs" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="recientes-tab" data-bs-toggle="tab" data-bs-target="#recientes" type="button" role="tab">Actividades Recientes</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="pendientes-tab" data-bs-toggle="tab" data-bs-target="#pendientes" type="button" role="tab">Pendientes</button>
            </li>
        </ul>

        <div class="tab-content" id="activityTabsContent">
            <div class="tab-pane fade show active" id="recientes" role="tabpanel">
                <div class="list-group">
                    <div class="list-group-item d-flex align-items-start">
                        <i class="bi bi-person-fill text-primary fs-4 me-3"></i>
                        <div>
                            <h6 class="mb-1 fw-bold">Nuevo miembro registrado</h6>
                            <small>Juan Pérez fue registrado como miembro del área de Desarrollo</small><br />
                            <small class="text-muted">Hace 2 horas</small>
                        </div>
                    </div>
                    <div class="list-group-item d-flex align-items-start">
                        <i class="bi bi-calendar-event text-info fs-4 me-3"></i>
                        <div>
                            <h6 class="mb-1 fw-bold">Entrevista programada</h6>
                            <small>Entrevista con María García para el puesto de Diseñador UI/UX</small><br />
                            <small class="text-muted">Hace 5 horas</small>
                        </div>
                    </div>
                    <div class="list-group-item d-flex align-items-start">
                        <i class="bi bi-check2-circle text-success fs-4 me-3"></i>
                        <div>
                            <h6 class="mb-1 fw-bold">Evaluación completada</h6>
                            <small>Se completó la evaluación trimestral del área de Marketing</small><br />
                            <small class="text-muted">Hace 1 día</small>
                        </div>
                    </div>
                    <div class="list-group-item d-flex align-items-start">
                        <i class="bi bi-bar-chart-line text-secondary fs-4 me-3"></i>
                        <div>
                            <h6 class="mb-1 fw-bold">Reporte generado</h6>
                            <small>Se generó el reporte OIE del staff actual</small><br />
                            <small class="text-muted">Hace 2 días</small>
                        </div>
                    </div>
                </div>
            </div>

            <div class="tab-pane fade" id="pendientes" role="tabpanel">
                <p class="text-muted">No hay tareas pendientes nuevas.</p>
            </div>
        </div>
    </div>
</asp:Content>
