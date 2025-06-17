UPDATE Staff
SET area = 'MARKETING',
   estado = 'ACTIVO'
WHERE id_staff = 1;

call BUSCAR_USUARIO_POR_ID(1);

call LISTAR_ENTREVISTAS();

call LISTAR_EVALUACIONES();

CALL LISTAR_ASISTENCIA();

SELECT * FROM Staff;

Call listar_Evento();

Call LISTAR_ROLES();