<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Usuario.aspx.cs" Inherits="GDPTalentoWA.Paginas.Usuario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container bg-white rounded shadow-sm p-4">
        <div class="row d-flex flex-column flex-md-row align-items-start">
            <!-- SECCIÓN IZQUIERDA - PERFIL -->
            <div class="col-md-5 d-flex flex-column align-items-center align-items-md-start">
                <div class="text-center mb-3">
                    <div class="rounded-circle bg-light border d-flex align-items-center justify-content-center" style="width: 120px; height: 120px;">
                        <i class="fa-regular fa-user fa-3x text-muted"></i>
                    </div>
                </div>
                <h4 class="fw-bold text-center text-md-start">Ana García</h4>
                <p class="text-muted mb-2 text-center text-md-start">Programador Senior - Desarrollo</p>
                <span class="badge bg-success mb-3">Activo</span>

                <ul class="list-unstyled mb-3">
                    <li><i class="fa-regular fa-envelope pe-2"></i>ana.garcia@pucp.edu.pe</li>
                    <li><i class="fa-regular fa-phone pe-2"></i>987654321</li>
                    <li><i class="fa-regular fa-location-dot pe-2"></i>Av. Universitaria 1801, San Miguel</li>
                    <li><i class="fa-regular fa-id-card pe-2"></i>Código: 20180123</li>
                    <li><i class="fa-regular fa-calendar pe-2"></i>Ingreso: 10/01/2023</li>
                    <li><i class="fa-regular fa-graduation-cap pe-2"></i>Ingeniería Informática - 9no ciclo</li>
                </ul>

                <a href="EditarMiembro.aspx" class="btn btn-outline-primary mt-2">
                    <i class="fa-regular fa-pen-to-square me-1"></i> Editar información
                </a>
            </div>

            <!-- LÍNEA SEPARADORA -->
            <div class="d-none d-md-flex justify-content-center px-0" style="width: 1px;">
                <div class="vr bg-secondary opacity-50" style="height: 350px;"></div>
            </div>

            <div class="d-md-none my-4">
                <hr />
            </div>

            <!-- SECCIÓN DERECHA - RESUMEN -->
            <div class="col-md-6 d-flex flex-column justify-content-start" style="margin-top: 120px;">
                <h5 class="fw-semibold mb-4">Resumen</h5>

                <div class="mb-3 d-flex align-items-center">
                    <i class="fa-regular fa-star text-warning me-2"></i>
                    <strong>Evaluación:</strong>
                    <span class="text-warning ms-2">
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star-half-stroke"></i>4.5
                    </span>
                </div>

                <div class="mb-3 d-flex align-items-center">
                    <i class="fa-regular fa-calendar text-primary me-2"></i>
                    <strong>Asistencia:</strong> <span class="ms-2">90%</span>
                </div>

                <div class="mb-3 d-flex align-items-center">
                    <i class="fa-regular fa-clock text-secondary me-2"></i>
                    <strong>Tareas pendientes:</strong> <span class="ms-2">2</span>
                </div>

                <div class="mb-3 d-flex align-items-center">
                    <i class="fa-regular fa-circle-check text-success me-2"></i>
                    <strong>Tareas completadas:</strong> <span class="ms-2">15</span>
                </div>
            </div>
</asp:Content>
