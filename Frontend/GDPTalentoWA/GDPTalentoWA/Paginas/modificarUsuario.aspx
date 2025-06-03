<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="modificarUsuario.aspx.cs" Inherits="GDPTalentoWA.Paginas.modificarUsuario" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
    Editar Información
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Editar información</h2>
                <p class="text-muted mb-0">Actualiza los datos del miembro</p>
            </div>
            <div class="card-body">
                <div class="row">
                    <!-- Primera mitad: Izquierda -->
                    <div class="col-md-6 border-end border-bottom border-md-bottom-0 pe-md-4 mb-4 mb-md-0">
                        <div class="mb-3 row">
                            <asp:Label ID="lblNombre" CssClass="col-sm-4 col-form-label" runat="server" Text="Nombre completo:" />
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtNombreCompleto" runat="server" CssClass="form-control" />
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblCodigo" CssClass="col-sm-4 col-form-label" runat="server" Text="Código:" />
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtCodigo" runat="server" CssClass="form-control" />
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblCorreo" CssClass="col-sm-4 col-form-label" runat="server" Text="Correo electrónico:" />
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" />
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblTelefono" CssClass="col-sm-4 col-form-label" runat="server" Text="Teléfono:" />
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" />
                            </div>
                        </div>

                    </div>

                    <!-- Segunda mitad: Derecha -->
                    <div class="col-md-6 ps-md-4">
                        <div class="mb-3 row">
                            <asp:Label ID="lblArea" CssClass="col-sm-4 col-form-label" runat="server" Text="Área:" />
                            <div class="col-sm-8">
                                <asp:DropDownList ID="ddlArea" runat="server" CssClass="form-select">
                                </asp:DropDownList>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblStatus" CssClass="col-sm-4 col-form-label" runat="server" Text="Status:" />
                            <div class="col-sm-8">
                                <asp:DropDownList ID="ddlStatus" runat="server" CssClass="form-select">
                                </asp:DropDownList>
                            </div>
                        </div>
                        <!---
                        <div class="mb-3 row">
                            <asp:Label ID="lblFechaIngreso" CssClass="col-sm-4 col-form-label" runat="server" Text="Fecha de ingreso:" />
                            <div class="col-sm-8">
                                <input id="dtpFechaIngreso" class="form-control" type="date" runat="server" />
                            </div>
                        </div>
                        ---->
                        <div class="mb-3 row">
                            <asp:Label ID="lblFacultad" CssClass="col-sm-4 col-form-label" runat="server" Text="Facultad:" />
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtFacultad" runat="server" CssClass="form-control" />
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblCarrera" CssClass="col-sm-4 col-form-label" runat="server" Text="Carrera:" />
                            <div class="col-sm-8">
                                <asp:TextBox ID="txtCarrera" runat="server" CssClass="form-control" />
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <asp:Label ID="lblActivo" CssClass="col-sm-4 col-form-label" runat="server" Text="Estado:" />
                            <div class="col-sm-8 d-flex align-items-center">
                                <div class="form-check form-switch">
                                    <input type="checkbox" class="form-check-input" id="chkActivo" runat="server" />
                                    <label class="form-check-label" for="chkActivo">Miembro activo</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-footer clearfix">
                <asp:LinkButton ID="btnRegresar" runat="server" Text="<i class='fa-solid fa-rotate-left'></i> Regresar" CssClass="float-start btn btn-secondary" OnClick="btnRegresar_Click" />
                <asp:LinkButton ID="btnGuardar" CssClass="float-end btn btn-primary" runat="server" Text="<i class='fa-solid fa-floppy-disk pe-2'></i> Guardar" OnClick="btnGuardar_Click" />
            </div>
        </div>
    </div>

    <!-- Modal de Error -->
    <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="errorModalLabel">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>Mensaje de Error
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:Label ID="lblMensajeError" runat="server" CssClass="form-text text-danger" />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
