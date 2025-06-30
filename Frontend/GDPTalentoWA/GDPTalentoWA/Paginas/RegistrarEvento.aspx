<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="RegistrarEvento.aspx.cs" Inherits="GDPTalentoWA.Paginas.RegistrarEvento" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <div class="container py-3">
        <a href="Eventos.aspx" class="btn btn-primary text-white">
            <i class="fa-solid fa-arrow-left pe-2"></i>Volver
        </a>
        <div class="card shadow-sm p-4 mt-3">
            <div class="card-body">
                <div class="header-section mb-4">
                    <h1>Registrar Evento</h1>
                    <p>Realiza registros de nuevos eventos de GDP</p>
                </div>
                <div class="row g-3 mb-4">
                    <div class="col-md-6">
                        <asp:Label ID="lblTipo" runat="server" AssociatedControlID="ddlTipo" CssClass="form-label" Text="Tipo"></asp:Label>
                        <span class="text-danger">*</span>
                        <asp:DropDownList ID="ddlTipo" runat="server" CssClass="form-select" Required="true">
                            <asp:ListItem Text="Seleccionar Tipo de Evento" Value="" Selected="True" Disabled="True"></asp:ListItem>
                            <asp:ListItem Text="Reunion" Value="REUNION"></asp:ListItem>
                            <asp:ListItem Text="Integracion" Value="INTEGRACION"></asp:ListItem>
                        </asp:DropDownList>
                    </div>
                    <div class="col-md-6">
                        <asp:Label ID="lblEstado" runat="server" AssociatedControlID="ddlEstado" CssClass="form-label" Text="Estado"></asp:Label>
                        <span class="text-danger">*</span>
                        <asp:DropDownList ID="ddlEstado" runat="server" CssClass="form-select" Required="true">
                            <asp:ListItem Text="Seleccionar Estado del Evento" Value="" Selected="True" Disabled="True"></asp:ListItem>
                            <asp:ListItem Text="Aprobado" Value="APROBADO"></asp:ListItem>
                            <asp:ListItem Text="Cancelado" Value="CANCELADO"></asp:ListItem>
                        </asp:DropDownList>
                    </div>
                    <div class="col-md-6">
                        <asp:Label runat="server" Text="Fecha de Evento: " CssClass="form-label"></asp:Label>
                        <input id="dtpFechaEvento" class="form-control" type="date" runat="server" />
                    </div>
                </div>
                <div class="row g-3 mb-4 table-responsive">
                    <asp:GridView ID="dgvEventos" runat="server" AutoGenerateColumns="false"
                        DataKeyNames="id" CssClass="table table-hover table-striped table-bordered text-center align-middle" OnRowDataBound="dgvRegistrarEventos_RowDataBound">
                        <Columns>
                            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                            <asp:BoundField DataField="codigoPUCP" HeaderText="Código" />
                            <asp:BoundField DataField="area" HeaderText="Área" />
                            <asp:TemplateField HeaderText="Participantes">
                                <ItemTemplate>
                                    <asp:CheckBox ID="chkParticipando" runat="server" Enabled="true" />
                                </ItemTemplate>
                            </asp:TemplateField>
                            <asp:TemplateField HeaderText="Encargados">
                                <ItemTemplate>
                                    <asp:CheckBox ID="chkEncargado" runat="server" Visible="false" Enabled="false" />
                                </ItemTemplate>
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>
                </div>
                <hr style="border: none; margin: 10px 0;">
                <asp:Panel ID="pnlGuardarCancelar" runat="server" CssClass="mt-3">
                    <asp:Button ID="btnGuardar" runat="server" CssClass="btn btn-success me-2" Text="Registrar" OnClick="btnGuardar_Click" />
                    <asp:Button ID="btnCancelar" runat="server" CssClass="btn btn-secondary" Text="Cancelar" OnClick="btnCancelar_Click" CausesValidation="false" UseSubmitBehavior="false" />
                </asp:Panel>
            </div>
        </div>

        <!-- Modal de Validación -->
        <div class="modal fade" id="modalCamposObligatorios" tabindex="-1" role="dialog" aria-labelledby="modalCamposObligatoriosLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header bg-warning">
                        <h5 class="modal-title" id="modalCamposObligatoriosLabel">Campos Obligatorios</h5>
                    </div>
                    <div class="modal-body">
                        Por favor, complete todos los campos: <strong>Fecha</strong>, <strong>Tipo</strong> y <strong>Estado</strong>.
                   
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const guardar = document.getElementById("<%= btnGuardar.ClientID %>");
        const tipo = document.getElementById("<%= ddlTipo.ClientID %>");
        const estado = document.getElementById("<%= ddlEstado.ClientID %>");
        const fecha = document.getElementById("dtpFechaEvento");

        guardar.addEventListener("click", function (e) {
            if (!tipo.value || !estado.value || !fecha.value) {
                e.preventDefault();
                const modal = new bootstrap.Modal(document.getElementById('modalCamposObligatorios'));
                modal.show();
            }
        });
    });
    </script>
</asp:Content>
