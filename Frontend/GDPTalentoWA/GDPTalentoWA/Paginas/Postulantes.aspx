<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Postulantes.aspx.cs" Inherits="GDPTalentoWA.Paginas.Postulantes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
    Postulantes
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container mt-3">
        <!--Cabecera-->
        <div class="row align-items-center">
            <div class="col">
                <h2 class="fw-bold mb-0">Gestión de Postulantes</h2>
                <p class="text-muted">Gestiona todos los aspectos de GDP Talento desde un solo lugar.</p>
            </div>
            <div class="col-auto ms-auto">
                <asp:LinkButton ID="btnRegistrarPostulante" CssClass="btn btn-primary text-white" runat="server"
                    OnClick="btnRegistrarPostulante_Click">
                    <i class="fa-solid fa-plus pe-2"></i> Registrar nuevo postulante
                </asp:LinkButton>
            </div>
        </div>
        <!--Contenido-->
        <div class="bg-white p-4 rounded-3 shadow-sm border" id="postulantContent">
            <!--Titulito y descripción-->
            <div>
                <h4 class="fw-bold mb-0">Postulantes</h4>
                <p class="text-muted">Lista de postulante a la convocatoria actual.</p>
            </div>
            <!--Contenedor de datos-->
            <div>
                <!--Selector de parámetros de búsqueda-->
                <div class="row gx-2 gy-2 mb-3 align-items-center flex-nowrap overflow-auto">
                    <div class="col-auto">
                        <div class="input-group flex-nowrap">
                            <asp:LinkButton
                                ID="lbBuscarPostulante"
                                runat="server"
                                CssClass="input-group-text bg-white border-end-0"
                                OnClick="lbBuscarPostulante_Click"
                                ToolTip="Buscar">
                                <i class="fa-solid fa-magnifying-glass text-muted"></i>
                            </asp:LinkButton>
                            <asp:TextBox
                                ID="txtBuscarPostulante"
                                runat="server"
                                CssClass="form-control border-start-0"
                                placeholder="Buscar por nombre..." 
                                Style="min-width: 735px;"/>
                        </div>
                    </div>

                    <div class="col-auto">
                        <asp:DropDownList ID="ddlEstados" runat="server" CssClass="form-select">
                            <asp:ListItem Selected="True" Text="Todos los estados" Value=""></asp:ListItem>
                            <asp:ListItem Text="Pendiente" Value="1"></asp:ListItem>
                            <asp:ListItem Text="Aprobado" Value="2"></asp:ListItem>
                            <asp:ListItem Text="Rechazado" Value="3"></asp:ListItem>
                        </asp:DropDownList>
                    </div>

                    <div class="col-auto">
                        <asp:DropDownList ID="ddlAreas" runat="server" CssClass="form-select">
                            <asp:ListItem Selected="True" Text="Todas las áreas" Value=""></asp:ListItem>
                            <asp:ListItem Text="DESARROLLO" Value="Desarrollo"></asp:ListItem>
                            <asp:ListItem Text="DISEÑO" Value="Diseño"></asp:ListItem>
                            <asp:ListItem Text="MARKETING" Value="Marketing"></asp:ListItem>
                        </asp:DropDownList>
                    </div>

                    <div class="col-auto">
                        <asp:LinkButton
                            ID="btnFiltrarPostulante"
                            runat="server"
                            CssClass="btn btn-outline-secondary"
                            OnClick="btnFiltrarPostulante_Click">
                            <i class="fa-solid fa-filter"></i>
                        </asp:LinkButton>
                    </div>
                </div>
                <!--Información de postulantes-->
                <!--Aquí la info será mostrada de 5 en 5, por ejemplo, conectando a la base datos-->                
                <!--                        
                -->
                <div class="table-responsive">
                    <asp:GridView ID="dgvPostulantes" runat="server" AutoGenerateColumns="false"
                        OnPageIndexChanging="dgvPostulantes_PageIndexChanging"
                        PageSize="5" CssClass="table table-hover table-striped table-bordered text-center align-middle">
                        <Columns>
                            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                            <asp:BoundField DataField="especialidad" HeaderText="Especialidad" />
                            <asp:BoundField DataField="estadoProceso" HeaderText="Estado" />
                            
                            <asp:TemplateField HeaderText="Acciones" ItemStyle-HorizontalAlign="Center" ItemStyle-Width="180px">
                                <ItemTemplate>
                                    <asp:DropDownList ID="ddlAcciones" runat="server" CssClass="form-select form-select-sm w-auto"
                                        AutoPostBack="true" OnSelectedIndexChanged="ddlAcciones_SelectedIndexChanged"
                                        data-id='<%# Eval("id") %>'>
                                        <asp:ListItem Text="Acción" Value="" />
                                        <asp:ListItem Text="Ver Detalles" Value="VerDetalles" />
                                        <asp:ListItem Text="Editar" Value="EditarInformacion" />
                                        <asp:ListItem Text="Eliminar" Value="EliminarPostulante" />
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
