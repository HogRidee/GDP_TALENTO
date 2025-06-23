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

call LISTAR_TAREA();

Call LISTAR_TAREA_ENCARGADO();

call LISTAR_POSTULANTES();


SELECT 
    m.codigoPUCP,
    m.nombre,
    m.telefono,
    m.correo,
    m.especialidad,
    s.area,
    s.fecha_ingreso,
    s.desempenio
FROM Staff s
JOIN MiembroPUCP m ON s.id_staff = m.id_miembro_pucp
WHERE s.estado = 'ACTIVO' and s.area='MARKETING';


SELECT 
    m.codigoPUCP,
    m.nombre,
    m.telefono,
    m.correo,
    m.especialidad,
    s.area,
    s.fecha_ingreso,
    s.desempenio
FROM Staff s
JOIN MiembroPUCP m ON s.id_staff = m.id_miembro_pucp
WHERE s.estado = 'ACTIVO' and s.area='RECURSOS_HUMANOS';


SELECT 
    m.codigoPUCP,
    m.nombre,
    m.telefono,
    m.correo,
    m.especialidad,
    s.area,
    s.fecha_ingreso,
    s.desempenio
FROM Staff s
JOIN MiembroPUCP m ON s.id_staff = m.id_miembro_pucp
WHERE s.estado = 'ACTIVO' and s.area='PRESIDENCIA';

SELECT 
    m.codigoPUCP,
    m.nombre,
    m.telefono,
    m.correo,
    m.especialidad,
    s.area,
    s.fecha_ingreso,
    s.desempenio
FROM Staff s
JOIN MiembroPUCP m ON s.id_staff = m.id_miembro_pucp
WHERE s.estado = 'ACTIVO' and s.area='GDP_ACADEMY';

SELECT 
    m.codigoPUCP,
    m.nombre,
    m.telefono,
    m.correo,
    m.especialidad,
    s.area,
    s.fecha_ingreso,
    s.desempenio
FROM Staff s
JOIN MiembroPUCP m ON s.id_staff = m.id_miembro_pucp
WHERE s.estado = 'ACTIVO' and s.area='EVENTOS';


SELECT 
    mp.nombre AS "Nombres y apellidos",
    mp.codigoPUCP AS "Código PUCP",
    mp.facultad AS "Facultad",
    mp.especialidad AS "Especialidad",
    mp.status AS "Status 2025-1 (matriculado, no-matriculado, egresado, externo)",
    'Game Devs PUCP' AS "Agrupación"
FROM Staff s
JOIN MiembroPUCP mp ON s.id_staff = mp.id_miembro_pucp
WHERE s.estado = 'ACTIVO';

UPDATE Postulante SET estado_proceso = 'PENDIENTE' WHERE id_postulante = 10;
UPDATE Postulante SET estado_proceso = 'APROBADO' WHERE id_postulante = 11;
UPDATE Postulante SET estado_proceso = 'RECHAZADO' WHERE id_postulante = 12;
