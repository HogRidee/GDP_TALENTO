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
            <small class="text-success">
              <asp:Literal ID="litVarMiembros" runat="server" Text="–" />
            </small>
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
              <small class="text-primary">
                Próxima entrevista: 
                <asp:Literal ID="litProximaEntrevista" runat="server" Text="–" />
              </small>
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
            <small id="smEventosProxDesc" runat="server" class="text-success">
                <asp:Literal ID="litEventosProxDesc" runat="server" />
            </small>
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
            <small class="text-danger">
                <asp:Literal ID="litTareasDesc" runat="server" Text="–" />
            </small>
          </div>
        </div>
      </div>
    </div>
    
    <!-- === ACCIONES RÁPIDAS === -->
    <div class="card shadow-sm mb-4">
      <div class="card-body">
        <h5 class="fw-bold mb-1 text-center">Acciones Rápidas</h5>
        <p class="text-muted mb-4 text-center">Accede rápidamente a las funciones más utilizadas</p>
        <div class="row g-3 justify-content-center w-75 mx-auto">
          <!-- Nuevo Miembro -->
          <div class="col-12 col-md-4">
            <div class="card custom-card h-100 text-center bg-primary bg-opacity-10 custom-rounded">
              <a href="RegistrarNuevoMiembro.aspx" class="stretched-link"></a>
              <div class="card-body d-flex flex-column align-items-center justify-content-center">
                <div class="rounded-circle bg-primary text-white d-flex align-items-center justify-content-center mb-3"
                     style="width:72px; height:72px;">
                  <i class="fa-regular fa-user-plus fa-2x"></i>
                </div>
                <h6 class="card-title mb-1 fw-bold">Nuevo Miembro</h6>
                <p class="text-muted small mb-0">Registrar un nuevo miembro en el sistema</p>
              </div>
            </div>
          </div>
          <!-- Nueva Entrevista -->
          <div class="col-12 col-md-4">
            <div class="card custom-card h-100 text-center bg-success bg-opacity-10 custom-rounded">
              <a href="Entrevista.aspx" class="stretched-link"></a>
              <div class="card-body d-flex flex-column align-items-center justify-content-center">
                <div class="rounded-circle bg-success text-white d-flex align-items-center justify-content-center mb-3"
                     style="width:72px; height:72px;">
                  <i class="fa-regular fa-calendar-check fa-2x"></i>
                </div>
                <h6 class="card-title mb-1 fw-bold">Nueva Entrevista</h6>
                <p class="text-muted small mb-0">Programar una nueva entrevista</p>
              </div>
            </div>
          </div>
          <!-- Nueva Tarea -->
          <div class="col-12 col-md-4">
            <div class="card custom-card h-100 text-center custom-rounded"
                 style="background: linear-gradient(to bottom right, rgba(220,182,255,0.3), rgba(184,92,255,0.3));">
              <a href="Tareas.aspx" class="stretched-link"></a>
                <div class="card-body d-flex flex-column align-items-center justify-content-center">
                <div class="rounded-circle"
                     style="width:72px; height:72px; background: linear-gradient(135deg,#dcb6ff,#b85cff); display:flex; align-items:center; justify-content:center; color:white; margin-bottom:1rem;">
                  <i class="fa-regular fa-list-check fa-2x"></i>
                </div>
                <h6 class="card-title mb-1 fw-bold">Nueva Tarea</h6>
                <p class="text-muted small mb-0">Crear y asignar una nueva tarea</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>


    <!-- === BIENVENIDA === -->
    <div class="rounded-3 p-4 mb-4" style="background-color:#eef5ff;">
      <h4 class="fw-bold mb-2 text-center">¡Bienvenido a GDP Talento!</h4>
      <p class="text-secondary mb-4 text-center w-75 mx-auto lead">
        Desde aquí puedes gestionar todos los aspectos relacionados con el talento humano. 
        Utiliza las acciones rápidas para crear nuevos registros o navega por el menú lateral para 
        acceder a funcionalidades específicas.
      </p>
      <div class="d-flex flex-wrap gap-2 justify-content-center">
        <span class="badge bg-white text-secondary border px-3 py-2 fs-6">Gestión de Miembros</span>
        <span class="badge bg-white text-secondary border px-3 py-2 fs-6">Proceso de Entrevistas</span>
        <span class="badge bg-white text-secondary border px-3 py-2 fs-6">Seguimiento de Tareas</span>
        <span class="badge bg-white text-secondary border px-3 py-2 fs-6">Reportes y Análisis</span>
      </div>
    </div>

  </div>
</asp:Content>

