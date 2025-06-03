<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Usuario.aspx.cs" Inherits="GDPTalentoWA.Paginas.Usuario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
    <style>
        .estado-activo {
            display: inline-block;
            background-color: #28a745;
            color: white;
            font-size: 1.1rem;
            padding: 0.5rem 1.2rem;
            border-radius: 1rem;
            font-weight: 600;
            box-shadow: 0 4px 10px rgba(0, 128, 0, 0.3);
            transition: transform 0.2s ease-in-out;
            text-align: center;
        }

            .estado-activo:hover {
                transform: scale(1.05);
            }
    </style>

    <style>
        @keyframes slideInLeft {
            0% {
                opacity: 0;
                transform: translateX(-30px);
            }

            100% {
                opacity: 1;
                transform: translateX(0);
            }
        }

        .animar-entrada {
            opacity: 0;
            animation: slideInLeft 0.5s ease-out forwards;
        }

        /* Retrasos escalonados */
        .delay-1 {
            animation-delay: 0.1s;
        }

        .delay-2 {
            animation-delay: 0.3s;
        }

        .delay-3 {
            animation-delay: 0.5s;
        }

        .delay-4 {
            animation-delay: 0.7s;
        }
    </style>
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

                <h4 class="fw-bold text-center text-md-start">
                    <asp:Label ID="lblNombre" runat="server" Text="Ana García" CssClass="fw-bold text-center text-md-start"></asp:Label>
                </h4>

                <p class="text-muted mb-2 text-center text-md-start">
                    <asp:Label ID="lblCargo" runat="server" Text="Programador Senior - Desarrollo" CssClass="text-muted"></asp:Label>
                </p>

                <asp:Label ID="lblEstado" runat="server" Text="Activo" CssClass="estado-activo mb-3"></asp:Label>


                <ul class="list-unstyled mb-3">
                    <li><i class="fa-regular fa-envelope pe-2"></i>
                        <asp:Label ID="lblCorreo" runat="server" Text="ana.garcia@pucp.edu.pe"></asp:Label></li>
                    <li><i class="fa-regular fa-phone pe-2"></i>
                        <asp:Label ID="lblTelefono" runat="server" Text="987654321"></asp:Label></li>
                    <li><i class="fa-regular fa-location-dot pe-2"></i>
                        <asp:Label ID="lblDireccion" runat="server" Text="Av. Universitaria 1801, San Miguel"></asp:Label></li>
                    <li><i class="fa-regular fa-id-card pe-2"></i>Código:
                        <asp:Label ID="lblCodigo" runat="server" Text="20180123"></asp:Label></li>
                    <li><i class="fa-regular fa-calendar pe-2"></i>Ingreso:
                        <asp:Label ID="lblFechaIngreso" runat="server" Text="10/01/2023"></asp:Label></li>
                    <li><i class="fa-regular fa-graduation-cap pe-2"></i>
                        <asp:Label ID="lblCarrera" runat="server" Text="Ingeniería Informática - 9no ciclo"></asp:Label></li>
                </ul>

                <asp:LinkButton ID="btnEditar" runat="server" CssClass="btn btn-outline-primary mt-2" OnClick="btnEditar_Click" CausesValidation="false" UseSubmitBehavior="false">
                    <i class="fa-regular fa-pen-to-square me-1"></i> Editar información
                </asp:LinkButton>
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

                <div class="mb-3 d-flex align-items-center animar-entrada delay-1">
                    <i class="fa-regular fa-star text-warning me-2"></i>
                    <strong>Evaluación:</strong>
                    <span class="text-warning ms-2">
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star"></i>
                        <i class="fa-solid fa-star-half-stroke"></i>
                        <asp:Label ID="lblEvaluacion" runat="server" CssClass="ms-1" Text="4.5"></asp:Label>
                    </span>
                </div>

                <div class="mb-3 d-flex align-items-center animar-entrada delay-2">
                    <i class="fa-regular fa-calendar text-primary me-2"></i>
                    <strong>Asistencia:</strong>
                    <asp:Label ID="lblAsistencia" runat="server" CssClass="ms-2" Text="90%"></asp:Label>
                </div>

                <div class="mb-3 d-flex align-items-center animar-entrada delay-3">
                    <i class="fa-regular fa-clock text-secondary me-2"></i>
                    <strong>Tareas pendientes:</strong>
                    <asp:Label ID="lblPendientes" runat="server" CssClass="ms-2" Text="2"></asp:Label>
                </div>

                <div class="mb-3 d-flex align-items-center animar-entrada delay-4">
                    <i class="fa-regular fa-circle-check text-success me-2"></i>
                    <strong>Tareas completadas:</strong>
                    <asp:Label ID="lblCompletadas" runat="server" CssClass="ms-2" Text="15"></asp:Label>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
