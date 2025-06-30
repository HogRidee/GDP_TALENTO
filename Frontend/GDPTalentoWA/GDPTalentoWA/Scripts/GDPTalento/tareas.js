window.addEventListener("load", function () {
    window.showModal = function (id) {
        const modal = bootstrap.Modal.getOrCreateInstance(document.getElementById(id));
        modal.show();
    };

    window.hideModal = function (id) {
        const modal = bootstrap.Modal.getOrCreateInstance(document.getElementById(id));
        modal.hide();
    };

    window.showModalNuevaTarea = () => showModal('modalNuevaTarea');
    window.hideModalNuevaTarea = () => hideModal('modalNuevaTarea');

    window.showModalDetallesTarea = () => showModal('modalDetallesTarea');
    window.hideModalDetallesTarea = () => hideModal('modalDetallesTarea');

    window.showModalEditarTarea = () => showModal('modalEditarTarea');
    window.hideModalEditarTarea = () => hideModal('modalEditarTarea');

    window.showModalEliminarTarea = () => showModal('modalEliminarTarea');
    window.hideModalEliminarTarea = () => hideModal('modalEliminarTarea');
});
