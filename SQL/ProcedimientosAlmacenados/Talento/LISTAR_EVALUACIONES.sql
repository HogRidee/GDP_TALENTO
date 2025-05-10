DROP PROCEDURE IF EXISTS LISTAR_EVALUACIONES;

DELIMITER $$

CREATE PROCEDURE LISTAR_EVALUACIONES()
BEGIN
    SELECT 
        ed.id_evaluacion_desempeno,
        u.nombre AS evaluador,
        s.nombre AS miembro_staff,
        ed.puntaje,
        ed.comentarios,
        ed.fecha AS fecha_evaluacion
    FROM 
        EvaluacionDesempeno ed
    LEFT JOIN Usuario u ON ed.id_evaluador = u.id_usuario
    LEFT JOIN Staff s ON ed.id_miembro_staff = s.id_staff
    ORDER BY e.id_evaluacion_desempeno;
END$$

DELIMITER ;