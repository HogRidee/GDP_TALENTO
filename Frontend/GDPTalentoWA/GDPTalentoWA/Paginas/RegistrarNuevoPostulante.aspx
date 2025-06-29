<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="RegistrarNuevoPostulante.aspx.cs" Inherits="GDPTalentoWA.Paginas.RegistrarNuevoPostulante" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container py-5">
        <!-- Botón Volver -->
        <a href="Postulantes.aspx" class="btn btn-outline-dark btn-sm btn-back-outside">
            <i class="fa-solid fa-arrow-left me-1"></i>Volver
        </a>
        <hr style="border: none; margin: 10px 0;">

        <!-- Título -->
        <div class="card shadow-sm p-4 mt-3">
            <div class="card-body">
                <div class="header-section mb-4">
                    <h1>
                        <asp:Label ID="lblTitulo" runat="server" Text="Registrar nuevo postulante"></asp:Label>
                    </h1>
                    <p>Ingresa la información del nuevo postalante a Game Devs PUCP</p>
                </div>

                <!-- Información Personal -->
                <h4 class="mb-4 text-primary">Información Personal</h4>
                <div class="row g-3 mb-4">
                    <div class="col-md-6">
                        <label for="txtNombreCompleto" class="form-label">Nombre completo <span class="text-danger">*</span></label>
                        <asp:TextBox ID="txtNombreCompleto" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                    </div>

                    <div class="col-md-6">
                        <label for="txtCorreoElectronico" class="form-label">Correo electrónico <span class="text-danger">*</span></label>
                        <asp:TextBox ID="txtCorreoElectronico" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                    </div>

                </div>

                <div class="row g-3 mb-4">
                    <div class="col-md-6">
                        <label for="txtNumeroContacto" class="form-label">Número de contacto <span class="text-danger">*</span></label>
                        <asp:TextBox ID="txtNumeroContacto" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                    </div>

                    <div class="col-md-6">
                        <label for="txtCodigoPUCP" class="form-label">Código PUCP <span class="text-danger">*</span></label>
                        <asp:TextBox ID="txtCodigoPUCP" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                    </div>
                </div>

                <div class="form-section-divider"></div>

                <!-- Información Académica -->
                <h4 class="mb-4 text-primary">Información Académica</h4>
                <div class="row g-3 mb-4">
                    <!-- Facultad -->
                    <div class="col-md-6">
                        <asp:Label ID="lblFacultad" runat="server" AssociatedControlID="ddlFacultad" CssClass="form-label" Text="Facultad"></asp:Label>
                        <span class="text-danger">*</span>
                        <asp:DropDownList ID="ddlFacultad" runat="server" CssClass="form-select" Required="true" AutoPostBack="false">
                            <asp:ListItem Text="Seleccionar facultad" Value="" Selected="True" Disabled="True"></asp:ListItem>
                            <asp:ListItem Text="INGENIERÍA" Value="Ingeniería"></asp:ListItem>
                            <asp:ListItem Text="DERECHO" Value="Derecho"></asp:ListItem>
                            <asp:ListItem Text="GESTIÓN" Value="Gestión"></asp:ListItem>
                        </asp:DropDownList>
                    </div>

                    <!-- Especialidad -->
                    <div class="col-md-6">
                        <asp:Label ID="lblEspecialidad" runat="server" AssociatedControlID="ddlEspecialidad" CssClass="form-label" Text="Especialidad"></asp:Label>
                        <span class="text-danger">*</span>
                        <asp:DropDownList ID="ddlEspecialidad" runat="server" CssClass="form-select" Required="true" AutoPostBack="false">
                            <asp:ListItem Text="Seleccionar especialidad" Value="" Selected="True" Disabled="True"></asp:ListItem>
                            <asp:ListItem Text="DERECHO" Value="Derecho"></asp:ListItem>
                            <asp:ListItem Text="ELECTRÓNICA" Value="Electrónica"></asp:ListItem>
                            <asp:ListItem Text="INFORMÁTICA" Value="Informática"></asp:ListItem>
                            <asp:ListItem Text="MARKETING" Value="Marketing"></asp:ListItem>
                        </asp:DropDownList>
                    </div>
                </div>


                <div class="row g-3 mb-5">
                    <div class="col-md-6">
                        <asp:Label ID="lblEstadoAcademico" runat="server" AssociatedControlID="ddlEstadoAcademico" CssClass="form-label" Text="Estado académico"></asp:Label>
                        <span class="text-danger">*</span>
                        <asp:DropDownList ID="ddlEstadoAcademico" runat="server" CssClass="form-select" Required="true" AutoPostBack="false">
                            <asp:ListItem Text="Seleccionar estado" Value="" Selected="True" Disabled="True"></asp:ListItem>
                            <asp:ListItem Text="MATRICULADO" Value="MATRICULADO"></asp:ListItem>
                            <asp:ListItem Text="NO MATRICULADO" Value="NO_MATRICULADO"></asp:ListItem>
                            <asp:ListItem Text="EGRESADO" Value="EGRESADO"></asp:ListItem>
                            <asp:ListItem Text="EXTERNO" Value="EXTERNO"></asp:ListItem>
                        </asp:DropDownList>
                    </div>
                </div>


                <div class="form-section-divider"></div>

            </div>
        
        </div>
        <hr style="border: none; margin: 10px 0;">
        <div class="card-footer clearfix">
            <asp:LinkButton ID="btnGuardar" CssClass="float-end btn btn-primary" runat="server" Text="<i class='fa-solid fa-floppy-disk pe-2'></i> Guardar" OnClick="btnGuardar_Click" />
        </div>
    </div>
</asp:Content>
