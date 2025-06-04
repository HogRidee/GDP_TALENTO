<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Postulantes.aspx.cs" Inherits="GDPTalentoWA.Paginas.Postulantes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
    Postulantes
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">

</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <div class="container mt-3">
        <!--Cabecera-->
        <div class="row row-cols-md-2">
            <div class="col col-1">
                <h2 class="fw-bold mb-0">Gestión de Postulantes</h2>
                <p class="text-muted">Gestiona todos los aspectos de GDP Talento desde un solo lugar.</p>
            </div>
            <div class="col">
                <button class="btn btn-primary align-items-center text-white">Registrar nuevo postulante</button>
            </div>
        </div>
        <!--Contenido-->
        <div class="p-5 rounded bg-body border shadow-sm" id="postulantsContent">
            <!--Titulito y descripción-->
            <div>
                <h4 class="fw-bold mb-0">Postulantes</h4>
                <p class="text-muted">Lista de postulante a la convocatoria actual.</p>
            </div>
            <!--Contenedor de datos-->
            <div>
                <!--Selector de parámetros de búsqueda-->
                <div class="row row-cols-md-3 gap-4 mb-4">
                    <div class="col">
                        <input class="flex h-10 rounded border" placeholder="Buscar nombre..." type="search">
                    </div>
                    <div class="col gap-2">
                        <button type="button" role="combobox" aria-autocomplete="none" aria-expanded="false" class="align-items-center border rounded">
                            <span style="pointer-events: none;">Todos los estados</span>
                        </button>
                        <button type="button" role="combobox" aria-autocomplete="none" aria-expanded="false" class="align-items-center border rounded">
                            <span style="pointer-events: none;">Todas las áreas</span>
                        </button>
                    </div>
                </div>
                <!--Información de postulantes-->
                <!--Aquí la info será mostrada de 5 en 5, por ejemplo, conectando a la base datos-->
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
                <!--Información y control de páginas-->
                <div class="row items-center justify-between mt-4">
                    <div class="text-sm text-muted col">
                        Mostrando 5 de 5 postulantes
                    </div>
                    <div class="items-center gap-2 col">
                        <button class="items-center justify-content-center gap-2 rounded bg-body border">Anterior</button>
                        <button class="items-center justify-content-center gap-2 rounded bg-body border">Siguiente</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
