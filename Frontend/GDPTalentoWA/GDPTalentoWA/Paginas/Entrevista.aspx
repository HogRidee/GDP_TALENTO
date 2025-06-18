<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Entrevista.aspx.cs" Inherits="GDPTalentoWA.Paginas.Entrevista" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container mt-3">
        <!--Cabecera-->
        <div class="row align-items-center">
            <div class="col">
                <h2 class="fw-bold mb-0">Gestión de Entrevistas</h2>
                <p class="text-muted">Administra las entrevistas a postulantes de Game Devs PUCP</p>
            </div>
            <div class="col-auto ms-auto">
                <asp:LinkButton ID="btnProgramarEntrevista" CssClass="btn btn-primary text-white" runat="server"
                    OnClick="btnProgramarEntrevista_Click">
                    <i class="fa-solid fa-plus pe-2"></i> Programar Entrevista
                </asp:LinkButton>
            </div>
        </div>
        <!--Contenido-->
        <div class="p-4 rounded bg-body border shadow-sm" id="entrevistContent">
            <!--Titulito y descripción-->
            <div>
                <h4 class="fw-bold mb-0">Entrevistas</h4>
                <p class="text-muted">Lista de entrevistas programas y completadas.</p>
            </div>
            <!--Contenedor de datos-->
            <div>
                <!--Selector de parámetros de búsqueda-->
                <div class="row g-2 mb-3 align-items-center">
                    <div class="col-md-8">
                        <div class="input-group">
                            <asp:LinkButton
                                ID="lbBuscarEntrevista"
                                runat="server"
                                CssClass="input-group-text bg-white border-end-0"
                                OnClick="lbBuscarEntrevista_Click"
                                ToolTip="Buscar">
                                <i class="fa-solid fa-magnifying-glass text-muted"></i>
                            </asp:LinkButton>
                            <asp:TextBox
                                ID="txtBuscarEntrevista"
                                runat="server"
                                CssClass="form-control border-start-0"
                                placeholder="Buscar por postulante..." />
                        </div>
                    </div>

                    <div class="col-auto">
                        <asp:DropDownList ID="ddlEstados" runat="server" CssClass="form-select">
                            <asp:ListItem Selected="True" Text="Todos los estados" Value=""></asp:ListItem>
                            <asp:ListItem Text="Programada" Value="1"></asp:ListItem>
                            <asp:ListItem Text="Realizada" Value="2"></asp:ListItem>
                            <asp:ListItem Text="Cancelada" Value="3"></asp:ListItem>
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
                            ID="btnFiltrarEntrevista"
                            runat="server"
                            CssClass="btn btn-outline-secondary"
                            OnClick="btnFiltrarEntrevista_Click">
                            <i class="fa-solid fa-filter"></i>
                        </asp:LinkButton>
                    </div>
                </div>
                <!--Información de postulantes-->
                <!--Aquí la info será mostrada de 5 en 5, por ejemplo, conectando a la base datos-->                
                <!--                        
                -->
                <div class="table-responsive">
                    <asp:GridView ID="dgvEntrevistas" runat="server" AutoGenerateColumns="false"
                        OnPageIndexChanging="dgvEntrevistas_PageIndexChanging"
                        PageSize="5" CssClass="table table-hover table-striped">
                        <Columns>
                            <asp:BoundField DataField="postulante.nombre" HeaderText="Postulante" />
                            <asp:BoundField DataField="fecha" HeaderText="Fecha" />
                            <asp:BoundField DataField="estado" HeaderText="Estado" />
                            <asp:BoundField DataField="entrevistadores[0].nombre" HeaderText="Entrevistador" />
                    
                            <asp:TemplateField HeaderText="Acciones" ItemStyle-HorizontalAlign="Center" ItemStyle-Width="180px">
                                <ItemTemplate>
                                    <asp:DropDownList ID="ddlAcciones" runat="server" CssClass="form-select form-select-sm w-auto"
                                        AutoPostBack="true" OnSelectedIndexChanged="ddlAcciones_SelectedIndexChanged"
                                        data-id='<%# Eval("id") %>'>
                                        <asp:ListItem Text="Acción" Value="" />
                                        <asp:ListItem Text="Reprogramar" Value="Reprogramar" />
                                        <asp:ListItem Text="Marcar como completada" Value="Completar" />
                                        <asp:ListItem Text="Cancelar Entrevista" Value="Cancelar" />
                                    </asp:DropDownList>
                                </ItemTemplate>
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
