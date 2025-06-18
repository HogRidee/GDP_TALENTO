<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Tareas.aspx.cs" Inherits="GDPTalentoWA.Paginas.Tareas" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
    Gestión de Tareas
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
    <script src="../Scripts/GDPTalento/tareas.js"></script>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <asp:UpdatePanel ID="upTareas" runat="server" UpdateMode="Conditional">
        <ContentTemplate>
            <div class="container mt-3">
                <div class="row align-items-center">
                    <div class="col">
                        <h2 class="fw-bold mb-0">Gestión de Tareas</h2>
                        <p class="text-muted mb-0">Administra las tareas asignadas a los miembros de Game Devs PUCP.</p>
                    </div>
                    <div class="col-auto ms-auto">
                        <asp:LinkButton ID="btnRegistrarTarea" CssClass="btn btn-primary text-white" runat="server" OnClick="btnRegistrarTarea_Click">
                            <i class="fa-solid fa-plus pe-2"></i> Nueva tarea
                        </asp:LinkButton>
                    </div>
                </div>
            </div>

            <div class="container mt-4">
                <div class="bg-white p-4 rounded-3 shadow-sm border">
                    <ul class="nav nav-pills mb-3">
                        <li class="nav-item">
                            <asp:LinkButton ID="btnTodas" runat="server" CssClass="nav-link active">Todas</asp:LinkButton></li>
                        <li class="nav-item">
                            <asp:LinkButton ID="btnPendientes" runat="server" CssClass="nav-link">Pendientes</asp:LinkButton></li>
                        <li class="nav-item">
                            <asp:LinkButton ID="btnEnProgreso" runat="server" CssClass="nav-link">En progreso</asp:LinkButton></li>
                        <li class="nav-item">
                            <asp:LinkButton ID="btnAtrasadas" runat="server" CssClass="nav-link text-danger">Atrasadas</asp:LinkButton></li>
                        <li class="nav-item">
                            <asp:LinkButton ID="btnCompletadas" runat="server" CssClass="nav-link">Completadas</asp:LinkButton></li>
                    </ul>

                    <h5 class="fw-bold mb-1">Todas las tareas</h5>
                    <p class="text-muted mb-3">Lista completa de tareas en el sistema.</p>

                    <div class="table-responsive">
                        <asp:GridView ID="dgvTareas" runat="server" AutoGenerateColumns="false" CssClass="table table-hover table-striped table-bordered text-center align-middle">
                            <Columns>
                                <asp:BoundField DataField="descripcion" HeaderText="Título" />
                                <asp:BoundField DataField="encargado" HeaderText="Encargado" />
                                <asp:BoundField DataField="fechaLimite" HeaderText="Fecha límite" DataFormatString="{0:dd/MM/yyyy}" />

                                <asp:TemplateField HeaderText="Estado">
                                    <ItemTemplate>
                                        <span class='<%# Eval("estadoCss") %>'><%# Eval("estado") %></span>
                                    </ItemTemplate>
                                </asp:TemplateField>

                                <asp:TemplateField HeaderText="Acciones">
                                    <ItemTemplate>
                                        <asp:DropDownList ID="ddlAcciones" runat="server" CssClass="form-select form-select-sm w-auto"
                                            AutoPostBack="true" OnSelectedIndexChanged="ddlAcciones_SelectedIndexChanged"
                                            data-id='<%# Eval("id") %>'>
                                            <asp:ListItem Text="Acción" Value="" />
                                            <asp:ListItem Text="Ver Detalles" Value="VerDetalles" />
                                            <asp:ListItem Text="Editar" Value="EditarTarea" />
                                        </asp:DropDownList>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                    </div>
                </div>
            </div>
        </ContentTemplate>
    </asp:UpdatePanel>

    <!-- Modales fuera del UpdatePanel -->
    <div class="modal fade" id="modalDetallesTarea" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Detalles de la tarea</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel ID="upDetallesTarea" runat="server" UpdateMode="Conditional">
                        <ContentTemplate>
                            <p><strong>Descripción:</strong> <asp:Label ID="lblDescripcionDetalle" runat="server" /></p>
                            <p><strong>Fecha límite:</strong> <asp:Label ID="lblFechaLimiteDetalle" runat="server" /></p>
                            <p><strong>Estado:</strong> <asp:Label ID="lblEstadoDetalle" runat="server" /></p>
                            <p><strong>Encargado:</strong> <asp:Label ID="lblEncargadosDetalle" runat="server" /></p>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modalEditarTarea" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Editar tarea</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Formulario edición -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-dark">Guardar cambios</button>
                </div>
            </div>
        </div>
    </div>
</asp:Content>