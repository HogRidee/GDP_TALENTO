<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="VerDetalles.aspx.cs" Inherits="GDPTalentoWA.Paginas.VerDetalles" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container py-4">
        <a href="Miembro.aspx" class="btn btn-outline-dark btn-sm">
            <i class="fa-solid fa-arrow-left me-1"></i>Volver
        </a>
        <hr style="border: none; margin: 10px 0;">

        <div class="row">
            <!-- Perfil izquierdo -->
            <div class="col-md-4">
                <div class="card card-profile p-3">
                    <div class="text-center">
                        <img src="https://via.placeholder.com/100" class="rounded-circle mb-2" />
                        <h4>
                            <asp:Label ID="lblNombre" runat="server" /></h4>
                        <p>
                            <asp:Label ID="lblCargo" runat="server" />
                        </p>
                        <asp:Label ID="lblEstado" runat="server"></asp:Label>
                    </div>
                    <hr />
                    <p>
                        <i class="fa fa-envelope pe-1"></i> Correo: 
                        <asp:Label ID="lblCorreo" runat="server" />
                    </p>
                    <p>
                        <i class="fa fa-phone pe-1"></i> Teléfono: 
                        <asp:Label ID="lblTelefono" runat="server" />
                    </p>
                    <p>
                        <i class="fa-solid fa-hashtag pe-2"></i>Código: 
                            <asp:Label ID="lblCodigo" runat="server"></asp:Label>
                    </p>
                    <p>
                        <i class="fa-solid fa-calendar-days pe-2"></i>Ingreso: 
                            <asp:Label ID="lblIngreso" runat="server"></asp:Label>
                    </p>

                    <p>
                        <i class="fa-solid fa-building-columns pe-2"></i>Carrera: 
                            <asp:Label ID="lblCarrera" runat="server"></asp:Label>
                    </p>

                    <hr />
                    <h3>Resumen</h3>

                    <p>
                        <span style="display: inline-block; width: 180px; vertical-align: middle;">Evaluación:
                            <asp:Label ID="lblEvaluacion" runat="server" />
                        </span>
                        <span style="display: inline-block; width: 180px; vertical-align: middle;">Asistencia:
                            <asp:Label ID="lblAsistencia" runat="server" />
                        </span>
                    </p>

                    <p>
                        <span style="display: inline-block; width: 180px; vertical-align: middle;">Tareas pendientes:
                            <asp:Label ID="lblPendientes" runat="server" />
                        </span>
                        <span style="display: inline-block; width: 180px; vertical-align: middle;">Tareas completadas:
                            <asp:Label ID="lblCompletadas" runat="server" />
                        </span>
                    </p>

                    <hr />
                    <h3 class="section-title">Habilidades</h3>
                    <div class="skills-container">
                        <asp:Repeater ID="rptHabilidades" runat="server">
                            <ItemTemplate>
                                <%-- IMPORTANTE: Usar Container.DataItem porque el C# está pasando una List<string> --%>
                                <span class="badge-skill"><%# Container.DataItem %></span>
                            </ItemTemplate>
                        </asp:Repeater>
                    </div>
                </div>
            </div>

            <!-- Detalle derecho -->
            <div class="col-md-8">
                <div class="col-md-20">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h4 class="card-title">Historial de Asistencia</h4>
                            <p class="text-muted">Registro de asistencia a reuniones y eventos</p>

                            <div class="table-responsive">
                                <asp:GridView ID="dgvAsistencia" runat="server" AutoGenerateColumns="false"
                                    OnRowDataBound="dgvAsistencia_RowDataBound" AllowPaging="true"
                                    OnPageIndexChanging="dgvAsistencia_PageIndexChanging" PageSize="5"
                                    CssClass="table table-hover table-responsive table-striped">
                                    <Columns>
                                        <asp:BoundField  HeaderText="Evento" />
                                        <asp:BoundField HeaderText="Asistencia" />
                                        <asp:BoundField HeaderText="Fecha" />
                                    </Columns>
                                </asp:GridView>
                            </div>
                            

                            <p>
                                 Porcentaje de asistencia: 
                                <asp:Label ID="lblPorcentaje" runat="server"></asp:Label>
                            </p>
                        </div>
                    </div>
                </div>
            </div>




        </div>
    </div>


</asp:Content>
