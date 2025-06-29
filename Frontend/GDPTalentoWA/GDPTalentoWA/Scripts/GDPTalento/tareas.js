let modalDetallesTarea;
let modalEditarTarea;
let modalEliminarTarea;

function showModalEliminarTarea() {
    modalEliminarTarea = new bootstrap.Modal(document.getElementById('modalConfirmarEliminacion'));
    modalEliminarTarea.show();
}
function showModalDetallesTarea(idTarea) {
    modalDetallesTarea = new bootstrap.Modal(document.getElementById('modalDetallesTarea'));


    modalDetallesTarea.show();
}

function showModalEditarTarea(idTarea) {
    modalEditarTarea = new bootstrap.Modal(document.getElementById('modalEditarTarea'));

    modalEditarTarea.show();
}

<script>
    function showModalNuevaTarea() {
        if (typeof bootstrap !== 'undefined') {
            var modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('modalNuevaTarea'));
    modal.show();
        }
    }

    function hideModalNuevaTarea() {
        if (typeof bootstrap !== 'undefined') {
            var modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('modalNuevaTarea'));
    modal.hide();
        }
    }
</script>
