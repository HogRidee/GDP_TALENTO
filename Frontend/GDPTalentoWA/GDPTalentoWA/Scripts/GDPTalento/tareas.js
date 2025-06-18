let modalDetallesTarea = null;
let modalEditarTarea = null;
function showModalDetallesTarea(idTarea) {
    modalDetallesTarea = new bootstrap.Modal(document.getElementById('modalDetallesTarea'));


    modalDetallesTarea.show();
}

function showModalEditarTarea(idTarea) {
    modalEditarTarea = new bootstrap.Modal(document.getElementById('modalEditarTarea'));

    modalEditarTarea.show();
}
