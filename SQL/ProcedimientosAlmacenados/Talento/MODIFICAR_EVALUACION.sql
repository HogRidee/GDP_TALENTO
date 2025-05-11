DROP PROCEDURE IF EXISTS MODIFICAR_EVALUACION;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_EVALUACION(
    IN p_id_evaluacion_desempeno INT,
    IN p_id_evaluador INT,
    IN p_id_miembro_staff INT,
    IN p_puntaje INT,
    IN p_comentarios VARCHAR(100),
    IN p_fecha DATE
)
BEGIN
    
    UPDATE EvaluacionDesempeno
    SET id_evaluador = p_id_evaluador,
        id_miembro_staff = p_id_miembro_staff,
        puntaje = p_puntaje,
        comentarios = p_comentarios,
        fecha = p_fecha
    WHERE id_evaluacion_desempeno = p_id_evaluacion_desempeno;

END$$

DELIMITER ;