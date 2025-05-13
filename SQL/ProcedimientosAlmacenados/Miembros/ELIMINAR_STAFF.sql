DROP PROCEDURE IF EXISTS ELIMINAR_STAFF;

DELIMITER $$

CREATE PROCEDURE ELIMINAR_STAFF (
    IN id_staff_eliminar INT
)
BEGIN
    -- Eliminar relaciones de eventos
    DELETE FROM Evento_Participante WHERE id_staff = id_staff_eliminar;
    DELETE FROM Asistencia WHERE id_participante = id_staff_eliminar;

    -- Eliminar evaluaciones donde fue evaluado
    DELETE FROM EvaluacionDesempeno WHERE id_miembro_staff = id_staff_eliminar;

    -- Eliminar si fue Usuario
    DELETE FROM Tarea_Encargado WHERE id_usuario = id_staff_eliminar;
    DELETE FROM Entrevista_Entrevistador WHERE id_entrevistador = id_staff_eliminar;
    DELETE FROM Tarea WHERE id_creador = id_staff_eliminar;
    DELETE FROM EvaluacionDesempeno WHERE id_evaluador = id_staff_eliminar;
    DELETE FROM Usuario WHERE id_usuario = id_staff_eliminar;

    -- Eliminar de Staff
    DELETE FROM Staff WHERE id_staff = id_staff_eliminar;

    -- Finalmente, eliminar de MiembroPUCP
    DELETE FROM MiembroPUCP WHERE id_miembro_pucp = id_staff_eliminar;
END$$

DELIMITER ;
