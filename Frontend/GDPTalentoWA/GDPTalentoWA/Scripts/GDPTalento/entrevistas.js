let modalEliminarEntrevista;
let modalCambiarFecha;
let modalCompletarEntrevista;

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