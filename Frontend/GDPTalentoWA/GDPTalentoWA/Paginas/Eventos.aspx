<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Eventos.aspx.cs" Inherits="GDPTalentoWA.Paginas.Eventos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <div class="containter mt-3">
        <div class="row align-items-center">
            <!-- Título a la izquierda -->
            <div class="col">
                <h2 class="fw-bold mb-0">Gestion de Eventos</h2>
                <p class="text-muted mb-0">Administra los eventos registrados.</p>
            </div>
            <!-- Botón a la derecha -->
            <div class="col-auto ms-auto">
                <asp:LinkButton ID="btnRegistrarEvento" CssClass="btn btn-primary text-white" runat="server" OnClick="btnRegistrarEvento_Click">
                <i class="fa-solid fa-plus pe-2"></i> Registrar Evento
                </asp:LinkButton>
            </div>
        </div>
    </div>
    <div class="container mt-4">

        <div class="bg-white p-4 rounded-3 shadow-sm border">
            <!-- Filtros y buscador -->
            <div class="row gx-2 gy-2 mb-3 align-items-center flex-nowrap overflow-auto">
                <div class="col-auto">
                    <div class="input-group flex-nowrap">
                        <asp:LinkButton
                            ID="lbBuscarEvento"
                            runat="server"
                            CssClass="input-group-text bg-white border-end-0"
                            OnClick="lbBuscarEvento_Click"
                            ToolTip="Buscar">
            <i class="fa-solid fa-magnifying-glass text-muted"></i>
                        </asp:LinkButton>
                        <asp:TextBox
                            ID="txtBuscarEventos"
                            runat="server"
                            CssClass="form-control border-start-0"
                            placeholder="Buscar por id, estado o tipo"
                            Style="min-width: 500px;" />
                    </div>
                </div>
                <div class="col-auto">
                    <asp:DropDownList ID="ddlTipo" runat="server" CssClass="form-select">
                        <asp:ListItem Selected="True" Text="Tipo" Value=""></asp:ListItem>
                        <asp:ListItem Text="Reunion" Value="1"></asp:ListItem>
                        <asp:ListItem Text="Integracion" Value="2"></asp:ListItem>
                    </asp:DropDownList>
                </div>
                <div class="col-auto">
                    <asp:DropDownList ID="ddlEstado" runat="server" CssClass="form-select">
                        <asp:ListItem Selected="True" Text="Estado" Value=""></asp:ListItem>
                        <asp:ListItem Text="Aprobado" Value="1"></asp:ListItem>
                        <asp:ListItem Text="Cancelado" Value="2"></asp:ListItem>
                    </asp:DropDownList>
                </div>

                <div class="col-auto">
                    <asp:DropDownList ID="ddlMES" runat="server" CssClass="form-select" data-size="5" EnableViewState="true">
                        <asp:ListItem Selected="True" Text="Mes" Value=""></asp:ListItem>
                        <asp:ListItem Text="Enero" Value="1"></asp:ListItem>
                        <asp:ListItem Text="Febrero" Value="2"></asp:ListItem>
                        <asp:ListItem Text="Marzo" Value="3"></asp:ListItem>
                        <asp:ListItem Text="Abril" Value="4"></asp:ListItem>
                        <asp:ListItem Text="Mayo" Value="5"></asp:ListItem>
                        <asp:ListItem Text="Junio" Value="6"></asp:ListItem>
                        <asp:ListItem Text="Julio" Value="7"></asp:ListItem>
                        <asp:ListItem Text="Agosto" Value="8"></asp:ListItem>
                        <asp:ListItem Text="Septiembre" Value="9"></asp:ListItem>
                        <asp:ListItem Text="Octubre" Value="10"></asp:ListItem>
                        <asp:ListItem Text="Noviembre" Value="11"></asp:ListItem>
                        <asp:ListItem Text="Diciembre" Value="12"></asp:ListItem>
                    </asp:DropDownList>
                </div>
                <div class="col-auto">
                    <asp:LinkButton
                        ID="btnFiltrarEvento"
                        runat="server"
                        CssClass="btn btn-outline-secondary"
                        OnClick="btnFiltrarEvento_Click">
                    <i class="fa-solid fa-filter"></i>
                    </asp:LinkButton>
                </div>
                <asp:LinkButton ID="btnReset" CssClass="btn btn-primary text-white col-auto" runat="server"
                    OnClick="btnResetFiltros_Click">
                <i class="fas fa-rotate-left me-1"></i> Reset
                </asp:LinkButton>
            </div>
            <!-- Tabla -->
            <div class="table-responsive">
                <asp:GridView ID="dgvEventos" runat="server" AutoGenerateColumns="false"
                    OnPageIndexChanging="dgvEventos_PageIndexChanging"
                    PageSize="20" CssClass="table table-hover table-striped table-bordered text-center align-middle"
                    OnRowDataBound="dgvEventos_RowDataBound" OnRowCommand="dgvEventos_RowCommand">
                    <Columns>
                        <asp:TemplateField HeaderText="ID">
                            <ItemTemplate>
                                <asp:HyperLink ID="lnkID" runat="server" NavigateUrl='<%# "VisualizacionEdicionEvento.aspx?id=" + Eval("id")%>'
                                    Text='<%# Eval("id") %>' />
                            </ItemTemplate>
                        </asp:TemplateField>
                        <asp:TemplateField HeaderText="Estado" ItemStyle-HorizontalAlign="Center">
                            <ItemTemplate>
                                <span runat="server" style='<%# "display:inline-block; width:10px; height:10px; border-radius:50%; background-color:" + GetColor(Eval("estadoEvento").ToString()) + "; margin-right:6px;" %>'></span>
                                <%# Eval("estadoEvento") %>
                            </ItemTemplate>
                        </asp:TemplateField>
                        <asp:BoundField DataField="tipoEvento" HeaderText="Tipo" />

                        <asp:TemplateField HeaderText="Fecha de Evento">
                            <ItemTemplate>
                                <%# Eval("fecha", "{0:dd/MM/yyyy}") %>
                            </ItemTemplate>
                        </asp:TemplateField>
                        <asp:TemplateField HeaderText="Asistencia">
                            <ItemTemplate>
                                <asp:Button ID="btnAsistencia" runat="server" Text="Asistencia"
                                    CssClass="btn btn-primary"
                                    CommandName="Asistencia"
                                    CommandArgument='<%# Eval("id") %>' />
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </div>
        </div>
    </div>
    <div class="modal fade" id="modalAsistencia" tabindex="-1" role="dialog" aria-labelledby="modalAsistenciaLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Asistencia</h5>
                </div>
                <div class="modal-body">
                    <asp:GridView ID="gvAsistencia" runat="server" AutoGenerateColumns="False" DataKeyNames="id"
                        CssClass="table table-hover table-striped table-bordered text-center align-middle">
                        <Columns>
                            <asp:BoundField DataField="id" HeaderText="ID" />
                            <asp:BoundField DataField="codigoPUCP" HeaderText="Código PUCP" />
                            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                            <asp:TemplateField HeaderText="Asistió">
                                <ItemTemplate>
                                    <asp:CheckBox ID="chkAsistio" runat="server" Checked='<%# Eval("estadoAsistencia") %>' />
                                </ItemTemplate>
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>
                </div>
                <div class="modal-footer">
                    <asp:Button ID="btnGuardarAsistencia" runat="server" Text="Guardar" CssClass="btn btn-success" OnClick="btnGuardarAsistencia_Click" />
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        function abrirModalAsistencia() {
            var modal = new bootstrap.Modal(document.getElementById('modalAsistencia'));
            modal.show();
        }
    </script>
</asp:Content>
