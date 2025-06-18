<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Inicio.aspx.cs" Inherits="GDPTalentoWA.Paginas.Inicio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
    Inicio
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_contenido" runat="server">
  <div class="container mt-3">
    <h2 class="fw-bold mb-0">Bienvenido al panel de Recursos Humanos</h2>
    <p class="text-muted">Gestiona todos los aspectos de GDP Talento desde un solo lugar.</p>

    <!-- === CARDS DE RESUMEN === -->
    <div class="row row-cols-1 row-cols-md-4 g-3 mb-4">
      <!-- Total Miembros -->
      <div class="col">
        <div class="card shadow-sm h-100 position-relative">
          <div style="position:absolute; top:14px; right:16px; color:#6c757d;">
            <i class="fa-regular fa-user fa-lg"></i>
          </div>
          <div class="card-body">
            <h6 class="text-muted">Total Miembros</h6>
            <h3 class="fw-bold">
              <asp:Literal ID="litTotalMiembros" runat="server" Text="–" />
            </h3>
            <small class="text-success">+3 desde el mes pasado</small>
          </div>
        </div>
      </div>

      <!-- Postulantes Activos -->
      <div class="col">
        <div class="card shadow-sm h-100 position-relative">
          <div style="position:absolute; top:14px; right:16px; color:#6c757d;">
            <i class="fa-regular fa-user-plus fa-lg"></i>
          </div>
          <div class="card-body">
            <h6 class="text-muted">Postulantes Activos</h6>
            <h3 class="fw-bold">
              <asp:Literal ID="litPostulantesActivos" runat="server" Text="–" />
            </h3>
            <small class="text-success">+5 desde el mes pasado</small>
          </div>
        </div>
      </div>

      <!-- Eventos Próximos -->
      <div class="col">
        <div class="card shadow-sm h-100 position-relative">
          <div style="position:absolute; top:14px; right:16px; color:#6c757d;">
            <i class="fa-regular fa-calendar fa-lg"></i>
          </div>
          <div class="card-body">
            <h6 class="text-muted">Eventos Próximos</h6>
            <h3 class="fw-bold">
              <asp:Literal ID="litEventosProximos" runat="server" Text="–" />
            </h3>
            <small class="text-success">Próximo: Workshop de Unity (15/05)</small>
          </div>
        </div>
      </div>

      <!-- Tareas Pendientes -->
      <div class="col">
        <div class="card shadow-sm h-100 position-relative">
          <div style="position:absolute; top:14px; right:16px; color:#6c757d;">
            <i class="fa-regular fa-square-check fa-lg"></i>
          </div>
          <div class="card-body">
            <h6 class="text-muted">Tareas Pendientes</h6>
            <h3 class="fw-bold">
              <asp:Literal ID="litTareasPendientes" runat="server" Text="–" />
            </h3>
            <small class="text-danger">3 con vencimiento esta semana</small>
          </div>
        </div>
      </div>
    </div>

      <ul class="nav nav-pills tab-menu mb-2" role="tablist">
  <li class="nav-item" role="presentation">
    <button class="nav-link active"
            id="recientes-tab"
            data-bs-toggle="pill"
            data-bs-target="#recientes"
            type="button"
            role="tab"
            aria-controls="recientes"
            aria-selected="true">
      Actividades Recientes
    </button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link"
            id="pendientes-tab"
            data-bs-toggle="pill"
            data-bs-target="#pendientes"
            type="button"
            role="tab"
            aria-controls="pendientes"
            aria-selected="false">
      Pendientes
    </button>
  </li>
</ul>
    <!-- === CARD DE ACTIVIDADES === -->
    <div class="card shadow-sm mb-4">
      <div class="card-body">

        <!-- PESTAÑAS -->
        

        <!-- ENCABEZADO -->
        <h5 class="fw-bold mb-1">Actividades Recientes</h5>
        <p class="text-muted mb-4">Últimas acciones realizadas en el sistema</p>

        <!-- LISTA DE ACTIVIDADES -->
        <div class="activity-item d-flex align-items-start mb-3 position-relative">
          <div class="icon-circle-celeste me-3">
             <i class="fa-regular fa-user"></i>
          </div>
          <div class="flex-grow-1">
            <h6 class="mb-1 fw-bold">Nuevo miembro registrado</h6>
            <small class="d-block">Juan Pérez fue registrado como miembro del área de Desarrollo</small>
            <small class="text-muted"><i class="bi bi-clock me-1"></i>Hace 2 horas</small>
          </div>
          <i class="bi bi-arrow-up-right activity-link"></i>
        </div>

        <div class="activity-item d-flex align-items-start mb-3 position-relative">
          <div class="icon-circle-celeste me-3">
            <i class="fa-regular fa-user-check"></i>
          </div>
          <div class="flex-grow-1">
            <h6 class="mb-1 fw-bold">Entrevista programada</h6>
            <small class="d-block">Entrevista con María García para el puesto de Diseñador UI/UX</small>
            <small class="text-muted"><i class="bi bi-clock me-1"></i>Hace 5 horas</small>
          </div>
          <i class="bi bi-arrow-up-right activity-link"></i>
        </div>

        <div class="activity-item d-flex align-items-start mb-3 position-relative">
          <div class="icon-circle-celeste me-3">
            <i class="fa-regular fa-medal"></i>
          </div>
          <div class="flex-grow-1">
            <h6 class="mb-1 fw-bold">Evaluación completada</h6>
            <small class="d-block">Se completó la evaluación trimestral del área de Marketing</small>
            <small class="text-muted"><i class="bi bi-clock me-1"></i>Hace 1 día</small>
          </div>
          <i class="bi bi-arrow-up-right activity-link"></i>
        </div>

        <div class="activity-item d-flex align-items-start mb-0 position-relative">
          <div class="icon-circle-celeste me-3">
            <i class="fa-regular fa-chart-simple"></i>
          </div>
          <div class="flex-grow-1">
            <h6 class="mb-1 fw-bold">Reporte generado</h6>
            <small class="d-block">Se generó el reporte OIE del staff actual</small>
            <small class="text-muted"><i class="bi bi-clock me-1"></i>Hace 2 días</small>
          </div>
          <i class="bi bi-arrow-up-right activity-link"></i>
        </div>

      </div>
    </div>
  </div>
</asp:Content>

