DROP PROCEDURE IF EXISTS ELIMINAR_EVALUACION;

DELIMITER $$

CREATE PROCEDURE ELIMINAR_EVALUACION (
    IN id_evaluacion_eliminar INT
)
BEGIN
    DELETE FROM EvaluacionDesempeno
    WHERE id_evaluacion_desempeno = id_evaluacion_eliminar;
END$$

DELIMITER ;
