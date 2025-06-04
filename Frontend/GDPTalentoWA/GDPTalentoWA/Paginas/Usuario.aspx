<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Usuario.aspx.cs" Inherits="GDPTalentoWA.Paginas.Usuario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
    <style>
        .estado {
            display: inline-block;
            font-size: 0.95rem;
            padding: 0.3rem 0.8rem;
            border-radius: 0.8rem;
            font-weight: 500;
            text-align: center;
            margin-top: 0.5rem;
        }

        .estado-activo {
            background-color: #8cda4b;
            color: white;
            box-shadow: 0 2px 6px rgba(40, 167, 69, 0.3);
        }

        .estado-inactivo {
            background-color: #fc5856;
            color: white;
            box-shadow: 0 2px 6px rgba(220, 53, 69, 0.3);
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

        .delay-5 {
            animation-delay: 0.9s;
        }
    </style>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container bg-white rounded shadow-sm p-4">
        <div class="row d-flex flex-column flex-md-row align-items-center">

            <!-- SECCIÓN IZQUIERDA - PERFIL -->
            <div class="col-md-5 d-flex flex-column align-items-center text-center">
                <div class="text-center mb-3">
                    <div class="rounded-circle bg-light border d-flex align-items-center justify-content-center" style="width: 120px; height: 120px;">
                        <i class="fa-regular fa-user fa-3x text-muted"></i>
                    </div>
                </div>

                <h4 class="fw-bold mb-1">
                    <asp:Label ID="lblNombre" runat="server" Text="Ana García" CssClass="fw-bold"></asp:Label>
                </h4>

                <asp:Label ID="lblEstado" runat="server" Text="Activo" CssClass=" "></asp:Label>

                <p class="text-muted mb-1">
                    <asp:Label ID="lblCargo" runat="server" Text="Programador"></asp:Label>
                </p>

                <ul class="list-unstyled mb-3">
                    <li><i class="fa-regular fa-envelope pe-2"></i>
                        <asp:Label ID="lblCorreo" runat="server" Text="ana.garcia@pucp.edu.pe" CssClass="animar-entrada delay-1"></asp:Label></li>
                    <li><i class="fa-regular fa-phone pe-2"></i>
                        <asp:Label ID="lblTelefono" runat="server" Text="987654321" CssClass="animar-entrada delay-2"></asp:Label></li>
                    <li><i class="fa-regular fa-id-card pe-2"></i>Código:
                        <asp:Label ID="lblCodigo" runat="server" Text="20180123" CssClass="animar-entrada delay-3"></asp:Label></li>
                    <li><i class="fa-regular fa-calendar pe-2"></i>Ingreso:
                        <asp:Label ID="lblFechaIngreso" runat="server" Text="10/01/2023" CssClass="animar-entrada delay-4"></asp:Label></li>
                    <li><i class="fa-regular fa-graduation-cap pe-2"></i>
                        <asp:Label ID="lblCarrera" runat="server" Text="Ingeniería Informática - 9no ciclo" CssClass="animar-entrada delay-5"></asp:Label></li>
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
            <div class="col-md-6 d-flex flex-column align-items-center text-center">
                <h5 class="fw-semibold mb-4">Resumen</h5>
                <div class="mb-3 d-flex align-items-center animar-entrada delay-1">
                    <i class="fa-regular fa-star text-warning me-2"></i>
                    <strong>Evaluación:</strong>
                    <span class="text-warning ms-2">
                        <asp:Literal ID="ltlEstrellas" runat="server"></asp:Literal>
                    </span>
                    <asp:Label ID="lblEvaluacion" runat="server" CssClass="ms-1" Text="4.5"></asp:Label>
                </div>

                <div class="mb-3 d-flex align-items-center animar-entrada delay-2">
                    <i class="fa-regular fa-calendar text-primary me-2"></i>
                    <strong>Asistencia:</strong>
                    <asp:Label ID="lblAsistencia" runat="server" CssClass="ms-2 d-none"></asp:Label>
                </div>

                <!-- GRÁFICO CIRCULAR DE ASISTENCIA CON ANIMACIÓN -->
                <div class="d-flex flex-column align-items-center mt-3">
                    <svg width="120" height="120" viewBox="0 0 120 120">
                        <circle cx="60" cy="60" r="50" stroke="#e6e6e6" stroke-width="10" fill="none" />
                        <circle id="asistenciaCircle" cx="60" cy="60" r="50" stroke="#28a745" stroke-width="10"
                            stroke-linecap="round" fill="none"
                            stroke-dasharray="314" stroke-dashoffset="314"
                            transform="rotate(-90 60 60)" />
                        <text id="asistenciaText" x="50%" y="50%" dominant-baseline="middle" text-anchor="middle"
                            font-size="18" font-weight="bold" fill="#28a745">
                            0%
                        </text>
                    </svg>
                </div>

                <!-- JavaScript embebido que usa el valor del control ASP.NET -->
                <script type="text/javascript">
                    document.addEventListener("DOMContentLoaded", function () {
                        // Obtener el valor desde el servidor directamente en el script
                        var asistenciaTexto = "<%= lblAsistencia.Text.Replace("%", "") %>";
                        var asistencia = parseFloat(asistenciaTexto);

                        var circle = document.getElementById("asistenciaCircle");
                        var text = document.getElementById("asistenciaText");

                        var radius = 50;
                        var circumference = 2 * Math.PI * radius;
                        var offset = circumference - (asistencia / 100) * circumference;

                        circle.style.strokeDashoffset = circumference;

                        let current = 0;
                        const steps = 60;
                        const duration = 1000;
                        const increment = asistencia / steps;
                        const interval = duration / steps;

                        const animate = setInterval(function () {
                            current += increment;
                            if (current >= asistencia) {
                                current = asistencia;
                                clearInterval(animate);
                            }

                            let currentOffset = circumference - (current / 100) * circumference;
                            circle.style.strokeDashoffset = currentOffset;
                            text.textContent = Math.round(current) + "%";
                        }, interval);
                    });
                </script>


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
