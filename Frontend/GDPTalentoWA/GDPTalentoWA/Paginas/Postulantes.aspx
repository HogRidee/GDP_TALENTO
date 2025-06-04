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
        <div class="p-4 rounded bg-body border shadow-sm" id="postulantContent">

            <!--Titulito y descripción-->
            <div>
                <h4 class="fw-bold mb-0">Postulantes</h4>
                <p class="text-muted">Lista de postulante a la convocatoria actual.</p>
            </div>
            <!--Contenedor de datos-->
            <div>
                <!--Selector de parámetros de búsqueda-->
                <div class="row g-2 mb-3 align-items-center">
                    <div class="col-md-8">
                        <div class="input-group">
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
                                placeholder="Buscar por nombre..." />
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
                            <asp:ListItem Text="Desarrollo" Value="Desarrollo"></asp:ListItem>
                            <asp:ListItem Text="Diseño" Value="Diseño"></asp:ListItem>
                            <asp:ListItem Text="Marketing" Value="Marketing"></asp:ListItem>
                        </asp:DropDownList>
                    </div>

                    <div class="col-auto ms-2">
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
                <div class="rounded border" id="postulantsTable">
                    <div class="relative overflow-auto p-3">
                        <table class="text-sm caption-bottom">
                            <thead>
                                <tr class="border-bottom hover:bg-body">
                                    <th class="h-12 px-4 text-lg-start align-middle">Nombre</th>
                                    <th class="h-12 px-4 text-lg-start align-middle">Área</th>
                                    <th class="h-12 px-4 text-lg-start align-middle">Cargo</th>
                                    <th class="h-12 px-4 text-lg-start align-middle">Estado</th>
                                    <th class="h-12 px-4 text-lg-start align-middle">Fecha Postulación</th>
                                    <th class="h-12 px-4 text-lg-start align-middle">Convocatoria</th>
                                    <th class="h-12 px-4 text-lg-start align-middle">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="border-bottom">
                                    <td class="p-4 align-middle">Roberto Gómez</td>
                                    <td class="p-4 align-middle">Desarrollo</td>
                                    <td class="p-4 align-middle">Programador Unity</td>
                                    <td class="p-4 align-middle">
                                        <div class="bg-warning fw-semibold rounded-3 p-1">Pendiente</div>
                                    </td>
                                    <td class="p-4 align-middle">01/05/2025</td>
                                    <td class="p-4 align-middle">2025-1</td>
                                    <td class="p-4 align-middle">
                                        <button class="align-items-center rounded border-0 bg-body fw-bolder" type="button" aria-haspopup="true" aria-expanded="false">...</button>
                                    </td>
                                </tr>
                                <tr class="border-bottom">
                                    <td class="p-4 align-middle">Lucia Martínez</td>
                                    <td class="p-4 align-middle">Diseño</td>
                                    <td class="p-4 align-middle">Artista 2D</td>
                                    <td class="p-4 align-middle">
                                        <div class="bg-primary fw-semibold rounded-3 p-1">Entrevista</div>
                                    </td>
                                    <td class="p-4 align-middle">02/05/2025</td>
                                    <td class="p-4 align-middle">2025-1</td>
                                    <td class="p-4 align-middle">
                                        <button class="align-items-center rounded border-0 bg-body fw-bolder" type="button" aria-haspopup="true" aria-expanded="false">...</button>
                                    </td>
                                </tr>
                                <tr class="border-bottom">
                                    <td class="p-4 align-middle">Javier Ruiz</td>
                                    <td class="p-4 align-middle">Marketing</td>
                                    <td class="p-4 align-middle">Community Manager</td>
                                    <td class="p-4 align-middle">
                                        <div class="bg-danger fw-semibold rounded-3 p-1">Rechazado</div>
                                    </td>
                                    <td class="p-4 align-middle">28/04/2025</td>
                                    <td class="p-4 align-middle">2025-1</td>
                                    <td class="p-4 align-middle">
                                        <button class="align-items-center rounded border-0 bg-body fw-bolder" type="button" aria-haspopup="true" aria-expanded="false">...</button>
                                    </td>
                                </tr>
                                <tr class="border-bottom">
                                    <td class="p-4 align-middle">Valeria Torres</td>
                                    <td class="p-4 align-middle">Desarrollo</td>
                                    <td class="p-4 align-middle">Programador Backend</td>
                                    <td class="p-4 align-middle">
                                        <div class="bg-success fw-semibold rounded-3 p-1">Aceptado</div>
                                    </td>
                                    <td class="p-4 align-middle">25/04/2025</td>
                                    <td class="p-4 align-middle">2025-1</td>
                                    <td class="p-4 align-middle">
                                        <button class="align-items-center rounded border-0 bg-body fw-bolder" type="button" aria-haspopup="true" aria-expanded="false">...</button>
                                    </td>
                                </tr>
                                <tr class="border-bottom">
                                    <td class="p-4 align-middle">Javier Ruiz</td>
                                    <td class="p-4 align-middle">Diseño</td>
                                    <td class="p-4 align-middle">Artista 3D</td>
                                    <td class="p-4 align-middle">
                                        <div class="bg-primary fw-semibold rounded-3 p-1">Entrevista</div>
                                    </td>
                                    <td class="p-4 align-middle">03/05/2025</td>
                                    <td class="p-4 align-middle">2025-1</td>
                                    <td class="p-4 align-middle">
                                        <button class="align-items-center rounded border-0 bg-body fw-bolder" type="button" aria-haspopup="true" aria-expanded="false">...</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                -->
                <div class="table-responsive">
                    <asp:GridView ID="dgvPostulantes" runat="server" AutoGenerateColumns="false"
                        OnPageIndexChanging="dgvPostulantes_PageIndexChanging"
                        PageSize="5" CssClass="table table-hover table-striped">
                        <Columns>
                            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                            <asp:BoundField DataField="especialidad" HeaderText="Especialidad" />
                            <asp:BoundField DataField="estadoProceso" HeaderText="Estado" />
                            <asp:TemplateField HeaderText="Acciones">
                                <ItemTemplate>
                                    <asp:DropDownList ID="ddlAcciones" runat="server">
                                        <asp:ListItem Text="Seleccionar acción" Value="" />
                                        <asp:ListItem Text="Ver Detalles" Value="VerDetalles" />
                                        <asp:ListItem Text="Editar Información" Value="EditarInformacion" />
                                        <asp:ListItem Text="Eliminar Miembro" Value="EliminarMiembro" />
                                    </asp:DropDownList>
                                    <asp:Button ID="btnEjecutar" runat="server" Text="Ir"
                                        CommandName="Accion"
                                        CommandArgument='<%# Eval("id") %>'
                                        OnClick="btnEjecutar_Click" />
                                </ItemTemplate>
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>
                </div>
                <!--Información y control de páginas-->
                <!--

                <div class="row items-center justify-between mt-4">
                    <div class="text-sm text-muted col">
                        Mostrando 5 de 5 postulantes
                    </div>
                    <div class="items-center gap-2 col">
                        <button class="items-center justify-content-center gap-2 rounded bg-body border">Anterior</button>
                        <button class="items-center justify-content-center gap-2 rounded bg-body border">Siguiente</button>
                    </div>
                </div>
                -->

            </div>
        </div>
    </div>
</asp:Content>
