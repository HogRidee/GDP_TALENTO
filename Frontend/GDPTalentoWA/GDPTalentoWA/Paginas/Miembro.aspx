<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Miembro.aspx.cs" Inherits="GDPTalentoWA.Paginas.Miembro" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container mt-3">
        <div class="row align-items-center">
            <!-- Título a la izquierda -->
            <div class="col">
                <h2 class="fw-bold mb-0">Gestión de Miembros</h2>
                <p class="text-muted mb-0">Administra la información de los miembros de Game Devs PUCP.</p>
            </div>
            <!-- Botón a la derecha -->
            <div class="col-auto ms-auto">
                <asp:LinkButton ID="btnRegistrarMiembro" CssClass="btn btn-primary text-white" runat="server"
                    OnClick="btnRegistrarMiembro_Click">
                    <i class="fa-solid fa-plus pe-2"></i> Registrar Nuevo Miembro
                </asp:LinkButton>
            </div>
        </div>
    </div>

    <div class="container mt-4">
        <!-- Caja blanca -->
        <div class="bg-white p-4 rounded-3 shadow-sm border">

            <!-- Encabezado -->
            <h5 class="fw-bold mb-1">Miembros</h5>
            <p class="text-muted mb-3">Lista de miembros registrados en el sistema.</p>

            <!-- Filtros y buscador -->
            <div class="row g-2 mb-3 align-items-center">
                <div class="col-md-8">
                    <div class="input-group">
                        <asp:LinkButton
                            ID="lbBuscarMiembro"
                            runat="server"
                            CssClass="input-group-text bg-white border-end-0"
                            OnClick="lbBuscarMiembro_Click"
                            ToolTip="Buscar">
                            <i class="fa-solid fa-magnifying-glass text-muted"></i>
                        </asp:LinkButton>
                        <asp:TextBox
                            ID="txtBuscarMiembro"
                            runat="server"
                            CssClass="form-control border-start-0"
                            placeholder="Buscar por nombre, código o área..." />
                    </div>
                </div>

                <div class="col-auto">
                    <asp:DropDownList ID="ddlEstados" runat="server" CssClass="form-select">
                        <asp:ListItem Selected="True" Text="Todos los estados" Value=""></asp:ListItem>
                        <asp:ListItem Text="Activo" Value="1"></asp:ListItem>
                        <asp:ListItem Text="Inactivo" Value="2"></asp:ListItem>
                    </asp:DropDownList>
                </div>

                <div class="col-auto">
                    <asp:DropDownList ID="ddlAreas" runat="server" CssClass="form-select">
                        <asp:ListItem Selected="True" Text="Todas las áreas" Value=""></asp:ListItem>
                        <asp:ListItem Text="Desarrollo" Value="Desarrollo"></asp:ListItem>
                        <asp:ListItem Text="Diseño" Value="Diseño"></asp:ListItem>
                        <asp:ListItem Text="Marketing" Value="Marketing"></asp:ListItem>
                    </asp:DropDownList>
                </div>

                <div class="col-auto ms-2">
                    <asp:LinkButton
                        ID="btnFiltrarMiembro"
                        runat="server"
                        CssClass="btn btn-outline-secondary"
                        OnClick="btnFiltrarMiembro_Click">
                        <i class="fa-solid fa-filter"></i>
                    </asp:LinkButton>
                </div>
            </div>

            <!-- Tabla -->
            <div class="table-responsive">
                <asp:GridView ID="dgvMiembros" runat="server" AutoGenerateColumns="false"
                    OnPageIndexChanging="dgvMiembros_PageIndexChanging"
                    PageSize="5" CssClass="table table-hover table-striped">
                    <Columns>
                        <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                        <asp:BoundField DataField="codigoPUCP" HeaderText="Código" />
                        <asp:BoundField DataField="area" HeaderText="Área" />
                        <asp:BoundField DataField="estado" HeaderText="Estado" />
                        <asp:BoundField DataField="fechaIngreso" HeaderText="Fecha Ingreso" DataFormatString="{0:dd/MM/yyyy}" HtmlEncode="false" />
                        <asp:TemplateField HeaderText="Acciones">
                            <ItemTemplate>
                                <asp:DropDownList ID="ddlAcciones" runat="server">
                                    <asp:ListItem Value="">Seleccione...</asp:ListItem>
                                    <asp:ListItem Value="Editar">Editar</asp:ListItem>
                                    <asp:ListItem Value="Eliminar">Eliminar</asp:ListItem>
                                </asp:DropDownList>
                                <asp:Button ID="btnEjecutar" runat="server" Text="Ir" CommandName="Accion" CommandArgument='<%# Eval("id") %>' OnClick="btnEjecutar_Click" />
                            </ItemTemplate>
                        </asp:TemplateField>

                    </Columns>
                </asp:GridView>

            </div>

        </div>
    </div>
</asp:Content>

