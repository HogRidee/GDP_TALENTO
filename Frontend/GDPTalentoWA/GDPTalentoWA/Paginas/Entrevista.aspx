<%@ Page Title="" Language="C#" MasterPageFile="~/GDPTalento.Master" AutoEventWireup="true" CodeBehind="Entrevista.aspx.cs" Inherits="GDPTalentoWA.Paginas.Entrevista" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cph_titulo" runat="server">
    Gestion de Entrevistas
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cph_scripts" runat="server">
    <script>
        function showModalNuevaEntrevista() {
            setTimeout(function () {
                if (typeof bootstrap !== 'undefined') {
                    var modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('modalNuevaEntrevista'));
                    modal.show();
                }
            }, 300);
        }

        function hideModalNuevaEntrevista() {
            setTimeout(function () {
                if (typeof bootstrap !== 'undefined') {
                    var modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('modalNuevaEntrevista'));
                    modal.hide();
                }
            }, 300);
        }

        function showModalCompletarEntrevista() {
            setTimeout(function () {
                if (typeof bootstrap !== 'undefined') {
                    var modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('modalCompletarEntrevista'));
                    modal.show();
                }
            }, 300);
        }

        function hideModalCompletarEntrevista() {
            setTimeout(function () {
                if (typeof bootstrap !== 'undefined') {
                    var modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('modalCompletarEntrevista'));
                    modal.hide();
                }
            }, 300);
        }

        function addOptions(target,name,id) {
            if (!target) {
                return false;
            }
            else {

                select = document.getElementById(target);

                var opt = document.createElement('option');
                opt.value = id;
                opt.innerHTML = name;
                select.appendChild(opt);
            }
        }

    </script>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cph_contenido" runat="server">
    <asp:UpdatePanel ID="upEntrevistas" runat="server" UpdateMode="Conditional">
        <ContentTemplate>
            <div class="container mt-3">
                <div class="row align-items-center">
                    <div class="col">
                        <h2 class="fw-bold mb-0">Gestión de Entrevistas</h2>
                        <p class="text-muted mb-0">Administra las entrevistas de postulantes a Game Devs PUCP.</p>
                    </div>
                    <div class="col-auto ms-auto">
                        <asp:LinkButton ID="btnProgramarEntrevista" CssClass="btn btn-primary text-white" runat="server" OnClick="btnProgramarEntrevista_Click">
                            <i class="fa-solid fa-plus pe-2"></i> Nueva entrevista
                        </asp:LinkButton>
                    </div>
                </div>
            </div>
        
            <div class="container mt-4">
                <div class="bg-white p-4 rounded-3 shadow-sm border">

                    <h5 class="fw-bold mb-1">Entrevistas</h5>
                    <p class="text-muted mb-3">Lista de entrevistas programadas y completadas.</p>

                    <div class="table-responsive">
                        <asp:GridView ID="dgvEntrevistas" runat="server" AutoGenerateColumns="false" CssClass="table table-hover table-striped table-bordered text-center align-middle">
                            <Columns>
                                <asp:BoundField DataField="postulante" HeaderText="Postulante" />
                                <asp:BoundField DataField="estado" HeaderText="Estado" />
                                <asp:BoundField DataField="entrevistador" HeaderText="Encargado" />
                                <asp:BoundField DataField="fecha" HeaderText="Fecha" DataFormatString="{0:dd/MM/yyyy}" />

                                <asp:TemplateField HeaderText="Acciones">
                                    <ItemTemplate>
                                        <asp:DropDownList ID="ddlAcciones" runat="server" CssClass="form-select form-select-sm w-auto"
                                            AutoPostBack="true" OnSelectedIndexChanged="ddlAcciones_SelectedIndexChanged"
                                            data-id='<%# Eval("id") %>'>
                                            <asp:ListItem Text="Acción" Value="" />
                                            <asp:ListItem Text="Ver detalles" Value="VerDetalles" />
                                            <asp:ListItem Text="Completar entrevista" Value="Completar" />
                                            <asp:ListItem Text="Cancelar entrevista" Value="Cancelar" />
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

    <!------ Modal para nueva Entrevista --------->
    <div class="modal fade" id="modalNuevaEntrevista" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Registrar nueva entrevista</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel ID="upNuevaEntrevista" runat="server" UpdateMode="Conditional">
                        <ContentTemplate>
                            <div class="mb-4">
                                <label for="lstPostulantes" class="form-label">Postulante</label>
                                <asp:CheckBoxList ID="lstPostulantes" runat="server" CssClass="form-check" >
                                </asp:CheckBoxList>
                            </div>

                            <div class="row">
                                <div class="mb-2">
                                    <label for="txtFecha" class="form-label">Fecha:</label>
                                    <asp:TextBox ID="txtFecha" runat="server" CssClass="form-control" TextMode="Date" />
                                </div>
                                <div class="mb-2">
                                    <label for="txtHora" class="form-label">Hora:</label>
                                    <asp:TextBox ID="txtHora" runat="server" CssClass="form-control" TextMode="Time" />
                                </div>
                            </div>

                            <div class="mb-4">
                                <label for="lstEntrevistadores" class="form-label">Seleccionar entrevistador</label>
                                <asp:CheckBoxList ID="lstEntrevistadores" runat="server" CssClass="form-check">
                                </asp:CheckBoxList>
                            </div>
                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <asp:LinkButton ID="btnGuardarNuevaEntrevista" runat="server" CssClass="btn btn-success" OnClick="btnGuardarNuevaEntrevista_Click">
                    Guardar entrevista
                    </asp:LinkButton>
                </div>
            </div>
        </div>
    </div>

    <!------ Modal para completar entrevista--------->
    <div class="modal fade" id="modalCompletarEntrevista" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Completar tarea</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <asp:UpdatePanel ID="upCompletarEntrevista" runat="server" UpdateMode="Conditional">
                        <ContentTemplate>

                            <div class="mb-3">
                                <label for="txtPuntuacion" class="form-label">Puntuacion</label>
                                <asp:TextBox ID="txtPuntuacion" runat="server" CssClass="form-control"/>
                            </div>

                            <div class="mb-3">
                                <label for="txtFeedback" class="form-label">Descripción</label>
                                <asp:TextBox ID="txtFeedback" runat="server" CssClass="form-control" />
                            </div>

                        </ContentTemplate>
                    </asp:UpdatePanel>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <asp:LinkButton ID="btnCompletar" runat="server" CssClass="btn btn-dark" OnClick="btnCompletar_Click">Completar entrevista</asp:LinkButton>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
