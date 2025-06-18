<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="ProgramarEntrevista.aspx.cs" Inherits="GDPTalentoWA.Paginas.ProgramarEntrevista" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container py-5">
        <!-- Botón Volver -->
        <a href="Entrevista.aspx" class="btn btn-outline-dark btn-sm btn-back-outside">
            <i class="fa-solid fa-arrow-left me-1"></i>Volver
        </a>
        <hr style="border: none; margin: 10px 0;">

        <!-- Título -->
        <div class="card shadow-sm p-4 mt-3">
            <div class="card-body">
                <div class="header-section mb-4">
                    <h1>
                        <asp:Label ID="lblTitulo" runat="server" Text="Programar Entrevista"></asp:Label>
                    </h1>
                    <p>Programar una entrevista para el postulante</p>
                </div>


                <form>
                    <!-- Información General -->
                    <div class="row">
                        <label for="txtPostulante" class="form-label">Postulante<span class="text-danger">*</span></label>
                        <asp:TextBox ID="txtPostulante" runat="server" CssClass="form-control" required="required"></asp:TextBox> <!--Debe de ser un DDL-->
                    </div>

                    <div class="row g-3 mb-4">
                        <div class="col-md-6">
                            <label for="txtFecha" class="form-label">Fecha<span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtFecha" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                        </div>

                         <div class="col-md-6">
                             <label for="txtHora" class="form-label">Hora<span class="text-danger">*</span></label>
                             <asp:TextBox ID="txtHora" runat="server" CssClass="form-control" required="required"></asp:TextBox>
                         </div>
                    </div>

                    <div class="row">
                        <label for="txtEntrevistador" class="form-label">Entrevistador<span class="text-danger">*</span></label> <!--También de ser un DDL-->
                        <asp:TextBox ID="txtEntrevistador" runat="server" CssClass="form-control" required="required"></asp:TextBox> 
                    </div>
                </form>
            </div>   
        </div>
        <hr style="border: none; margin: 10px 0;">
        <div class="card-footer clearfix">
            <asp:LinkButton ID="btnGuardar" CssClass="float-end btn btn-primary" runat="server" Text="<i class='fa-solid fa-floppy-disk pe-2'></i> Guardar" OnClick="btnGuardar_Click" />
        </div>
    </div>
</asp:Content>
