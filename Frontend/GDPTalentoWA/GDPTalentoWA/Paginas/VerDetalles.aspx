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
                            <h4><asp:Label ID="lblNombre" runat="server" /></h4>
                            <p><asp:Label ID="lblCargo" runat="server" /></p>
                            <asp:Label ID="lblEstado" runat="server"></asp:Label>
                        </div>
                        <hr />
                        <p><i class="fa fa-envelope pe-1"></i> <asp:Label ID="lblCorreo" runat="server" /></p>
                        <p><i class="fa fa-phone pe-1"></i> <asp:Label ID="lblTelefono" runat="server" /></p>
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
                          <span style="display: inline-block; width: 150px; vertical-align: middle;">
                            Evaluación: <asp:Label ID="lblEvaluacion" runat="server" />
                          </span>
                          <span style="display: inline-block; width: 150px; vertical-align: middle;">
                            Asistencia: <asp:Label ID="lblAsistencia" runat="server" />
                          </span>
                        </p>

                        <p>
                          <span style="display: inline-block; width: 150px; vertical-align: middle;">
                            Tareas pendientes: <asp:Label ID="lblPendientes" runat="server" />
                          </span>
                          <span style="display: inline-block; width: 150px; vertical-align: middle;">
                            Tareas completadas: <asp:Label ID="lblCompletadas" runat="server" />
                          </span>
                        </p>

                        <hr />
                        <h6>Habilidades</h6>
                        <div>
                            <asp:Repeater ID="rptHabilidades" runat="server">
                                <ItemTemplate>
                                    <span class="badge badge-skill"><%# Eval("nombre") %></span>
                                </ItemTemplate>
                            </asp:Repeater>
                        </div>
                    </div>
                </div>

                <!-- Detalle derecho -->
                <div class="col-md-8">
                    <div class="card p-3">
                        <h5>Historial de Asistencia</h5>
                        <p class="text-muted">Registro de asistencia a reuniones y eventos</p>
                        <asp:GridView ID="gvAsistencia" runat="server" AutoGenerateColumns="false" CssClass="table">
                            <Columns>
                                <asp:BoundField HeaderText="Evento" DataField="Evento" />
                                <asp:BoundField HeaderText="Fecha" DataField="Fecha" />
                                <asp:TemplateField HeaderText="Asistencia">
                                    <ItemTemplate>
                                        <%# (bool)Eval("Asistio") ?
                                            "<span class='label-badge badge-success'>Asistió</span>" :
                                            "<span class='label-badge badge-danger'>No asistió</span>" %>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                        <p>Porcentaje de asistencia:
                            <asp:Label ID="lblPorcentaje" runat="server" />
                        </p>
                    </div>
                </div>
            </div>
        </div>


</asp:Content>
