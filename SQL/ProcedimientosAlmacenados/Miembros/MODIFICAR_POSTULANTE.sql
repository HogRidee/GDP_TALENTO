DROP PROCEDURE IF EXISTS MODIFICAR_POSTULANTE;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_POSTULANTE(
    IN _id_postulante INT,
    IN _estado_proceso VARCHAR(20)
)
BEGIN
    UPDATE Postulante
    SET estado_proceso = _estado_proceso
    WHERE id_postulante = _id_postulante;
END$$

DELIMITER ;
