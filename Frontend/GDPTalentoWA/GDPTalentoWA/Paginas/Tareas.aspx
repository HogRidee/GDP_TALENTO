﻿<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Tareas.aspx.cs" Inherits="GDPTalentoWA.Paginas.Tareas" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
    Gestión de Tareas
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</asp:Content>


<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <asp:UpdatePanel ID="upTareas" runat="server" UpdateMode="Conditional">
        <ContentTemplate>
            <div class="container mt-3">
                <div class="row align-items-center">
                    <div class="col">
                        <h2 class="fw-bold mb-0">Gestión de Tareas</h2>
                        <p class="text-muted mb-0">Administra las tareas asignadas a los miembros de Game Devs PUCP.</p>
                    </div>
                    <div class="col-auto ms-auto">
                        <asp:LinkButton ID="btnRegistrarTarea" CssClass="btn btn-primary text-white" runat="server" OnClick="btnRegistrarTarea_Click">
                            <i class="fa-solid fa-plus pe-2"></i> Nueva tarea
                        </asp:LinkButton>
                    </div>
                </div>
            </div>

            <div class="container mt-4">
                <div class="bg-white p-4 rounded-3 shadow-sm border">
                    <ul class="nav nav-pills mb-3">
                        <li class="nav-item">
                            <asp:LinkButton ID="btnTodas" runat="server" CssClass="nav-link active" OnClick="FiltrarTareas_Click" CommandArgument="">Todas</asp:LinkButton>
                        </li>
                        <li class="nav-item">
                            <asp:LinkButton ID="btnPendientes" runat="server" CssClass="nav-link" OnClick="FiltrarTareas_Click" CommandArgument="PENDIENTE">Pendientes</asp:LinkButton>
                        </li>
                        <li class="nav-item">
                            <asp:LinkButton ID="btnEnProgreso" runat="server" CssClass="nav-link" OnClick="FiltrarTareas_Click" CommandArgument="EN_PROCESO">En progreso</asp:LinkButton>
                        </li>
                        <li class="nav-item">
                            <asp:LinkButton ID="btnCompletadas" runat="server" CssClass="nav-link" OnClick="FiltrarTareas_Click" CommandArgument="REALIZADA">Completadas</asp:LinkButton>
                        </li>
                    </ul>


                    <h5 class="fw-bold mb-1">Todas las tareas</h5>
                    <p class="text-muted mb-3">Lista completa de tareas en el sistema.</p>

                    <div class="table-responsive">
                        <asp:GridView ID="dgvTareas" runat="server" AutoGenerateColumns="false" CssClass="table table-hover table-striped table-bordered text-center align-middle">
                            <Columns>
                                <asp:BoundField DataField="descripcion" HeaderText="Título" />
                                <asp:BoundField DataField="encargado" HeaderText="Encargado" />
                                <asp:BoundField DataField="fechaLimite" HeaderText="Fecha límite" DataFormatString="{0:dd/MM/yyyy}" />

                                <asp:TemplateField HeaderText="Estado">
                                    <ItemTemplate>
                                        <span class='<%# Eval("estadoCss") %>'><%# Eval("estado") %></span>
                                    </ItemTemplate>
                                </asp:TemplateField>

                                <asp:TemplateField HeaderText="Acciones">
                                    <ItemTemplate>
                                        <asp:DropDownList ID="ddlAcciones" runat="server" CssClass="form-select form-select-sm w-auto"
                                            AutoPostBack="true" OnSelectedIndexChanged="ddlAcciones_SelectedIndexChanged"
                                            data-id='<%# Eval("id") %>'>
                                            <asp:ListItem Text="Acción" Value="" />
                                            <asp:ListItem Text="Ver Detalles" Value="VerDetalles" />
                                            <asp:ListItem Text="Editar" Value="EditarTarea" />
                                            <asp:ListItem Text="Eliminar" Value="EliminarTarea" />
                                        </asp:DropDownList>
                                    </ItemTemplate>
                                </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                    </div>
                </div>
            </div>
        </ContentTemplate>
    </asp:UpdatePanel>

    <!-- Modales fuera del UpdatePanel -->
    <!-- Modal Detalles Tarea -->
    <div class="modal fade" id="modalDetallesTarea" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Detalles de la tarea</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel ID="upDetallesTarea" runat="server" UpdateMode="Conditional">
                        <ContentTemplate>
                            <p>
                                <strong>Creado por:</strong>
                                <asp:Label ID="lblCreadorDetalle" runat="server" />
                            </p>
                            <p>
                                <strong>Descripción:</strong>
                                <asp:Label ID="lblDescripcionDetalle" runat="server" />
                            </p>
                            <p>
                                <strong>Fecha límite:</strong>
                                <asp:Label ID="lblFechaLimiteDetalle" runat="server" />
                            </p>
                            <p>
                                <strong>Estado:</strong>
                                <asp:Label ID="lblEstadoDetalle" runat="server" />
                            </p>
                            <p>
                                <strong>Encargado:</strong>
                                <asp:Label ID="lblEncargadosDetalle" runat="server" />
                            </p>

                            <div class="d-flex justify-content-between mt-3">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Regresar</button>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Confirmar Eliminación -->
    <div class="modal fade" id="modalEliminarTarea" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content border border-danger">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title">¿Estás seguro de eliminar esta tarea?</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    Esta acción no se puede deshacer. ¿Deseas continuar?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Volver</button>
                    <asp:LinkButton ID="LinkButton1" runat="server" CssClass="btn btn-danger text-white"
                        OnClick="btnEliminarTareaFinal_Click">Eliminar</asp:LinkButton>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="modalEditarTarea" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Editar tarea</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel ID="upEditarTarea" runat="server" UpdateMode="Conditional">
                        <ContentTemplate>
                            <p>
                                <strong>Creado por:</strong>
                                <asp:Label ID="lblCreadorEditar" runat="server" />
                            </p>

                            <div class="mb-3">
                                <label for="txtDescripcionEditar" class="form-label">Descripción</label>
                                <asp:TextBox ID="txtDescripcionEditar" runat="server" CssClass="form-control" />
                                <asp:RequiredFieldValidator
                                    ID="rfvDescripcionEditar"
                                    runat="server"
                                    ControlToValidate="txtDescripcionEditar"
                                    ErrorMessage="La descripción es obligatoria"
                                    CssClass="text-danger"
                                    Display="Dynamic"
                                    ValidationGroup="EditarTarea" />
                            </div>

                            <div class="mb-3">
                                <label for="txtFechaLimiteEditar" class="form-label">Fecha límite</label>
                                <asp:TextBox ID="txtFechaLimiteEditar" runat="server" CssClass="form-control" TextMode="Date" />
                                <asp:RequiredFieldValidator
                                    ID="rfvFechaLimiteEditar"
                                    runat="server"
                                    ControlToValidate="txtFechaLimiteEditar"
                                    ErrorMessage="La fecha límite es obligatoria"
                                    CssClass="text-danger"
                                    Display="Dynamic"
                                    ValidationGroup="EditarTarea" />
                            </div>

                            <div class="mb-3">
                                <label for="ddlEstadoEditar" class="form-label">Estado</label>
                                <asp:DropDownList ID="ddlEstadoEditar" runat="server" CssClass="form-select">
                                    <asp:ListItem Text="Pendiente" Value="PENDIENTE" />
                                    <asp:ListItem Text="En Proceso" Value="EN_PROCESO" />
                                    <asp:ListItem Text="Realizada" Value="REALIZADA" />
                                </asp:DropDownList>
                            </div>

                            <!-- <asp:Button ID="btnEliminarEncargado" runat="server" CssClass="btn btn-danger" Text="Eliminar encargado de esta tarea" OnClick="btnEliminarEncargado_Click" /> -->
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <asp:LinkButton ID="btnGuardarCambios" runat="server" CssClass="btn btn-dark"
                        OnClick="btnGuardarCambios_Click"
                        ValidationGroup="EditarTarea">
                        Guardar cambios
                    </asp:LinkButton>
                </div>
            </div>
        </div>
    </div>
    <!------ Modal para nueva Tarea --------->
    <div class="modal fade" id="modalNuevaTarea" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Registrar nueva tarea</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel ID="upNuevaTarea" runat="server" UpdateMode="Conditional">
                        <ContentTemplate>
                            <p>
                                <strong>Encargado principal:</strong>
                                <asp:Label ID="lblEncargadoSesion" runat="server" />
                            </p>
                            <p>
                                <strong>Fecha de creación:</strong>
                                <asp:Label ID="lblFechaCreacion" runat="server" />
                            </p>

                            <div class="mb-3">
                                <label for="txtDescripcionNueva" class="form-label">Descripción</label>
                                <asp:TextBox ID="txtDescripcionNueva" runat="server" CssClass="form-control" />
                                <asp:RequiredFieldValidator
                                    ID="rfvDescripcionNueva"
                                    runat="server"
                                    ControlToValidate="txtDescripcionNueva"
                                    ErrorMessage="La descripción es obligatoria"
                                    CssClass="text-danger"
                                    Display="Dynamic"
                                    ValidationGroup="NuevaTarea" />
                            </div>

                            <div class="mb-3">
                                <label for="txtFechaLimiteNueva" class="form-label">Fecha límite</label>
                                <asp:TextBox ID="txtFechaLimiteNueva" runat="server" CssClass="form-control" TextMode="Date" />
                                <asp:RequiredFieldValidator
                                    ID="rfvFechaLimiteNueva"
                                    runat="server"
                                    ControlToValidate="txtFechaLimiteNueva"
                                    ErrorMessage="La fecha límite es obligatoria"
                                    CssClass="text-danger"
                                    Display="Dynamic"
                                    ValidationGroup="NuevaTarea" />
                            </div>

                            <div class="mb-3">
                                <label for="lstEncargados" class="form-label">Seleccionar encargados</label>
                                <asp:CheckBoxList ID="lstEncargados" runat="server" CssClass="form-check">
                                </asp:CheckBoxList>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <asp:LinkButton ID="btnGuardarNuevaTarea" runat="server" CssClass="btn btn-success"
                        OnClick="btnGuardarNuevaTarea_Click"
                        ValidationGroup="NuevaTarea">
                        Guardar tarea
                    </asp:LinkButton>
                </div>
            </div>
        </div>
    </div>
    <script>
        function showModal(id) {
            const modalElement = document.getElementById(id);
            if (modalElement && typeof bootstrap !== "undefined") {
                const modal = bootstrap.Modal.getOrCreateInstance(modalElement);
                modal.show();
            } else {
                console.error("No se pudo mostrar el modal:", id);
            }
        }

        function hideModal(id) {
            const modalElement = document.getElementById(id);
            if (modalElement && typeof bootstrap !== "undefined") {
                const modal = bootstrap.Modal.getOrCreateInstance(modalElement);
                modal.hide();
            } else {
                console.error("No se pudo ocultar el modal:", id);
            }
        }

        // Luego recién las funciones globales:
        window.showModalNuevaTarea = () => showModal('modalNuevaTarea');
        window.hideModalNuevaTarea = () => hideModal('modalNuevaTarea');

        window.showModalDetallesTarea = () => showModal('modalDetallesTarea');
        window.hideModalDetallesTarea = () => hideModal('modalDetallesTarea');

        window.showModalEditarTarea = () => showModal('modalEditarTarea');
        window.hideModalEditarTarea = () => hideModal('modalEditarTarea');

        window.showModalEliminarTarea = () => showModal('modalEliminarTarea');
        window.hideModalEliminarTarea = () => hideModal('modalEliminarTarea');
    </script>


</asp:Content>
