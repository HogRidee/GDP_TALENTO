DROP PROCEDURE IF EXISTS MODIFICAR_ENTREVISTA;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_ENTREVISTA(
    IN p_id_entrevista INT,
    IN p_id_postulante INT,
    IN p_fecha DATE,
    IN p_feedback VARCHAR(100),
    IN p_estado VARCHAR(25),
    IN p_puntuacion_final DOUBLE
)
BEGIN
    
    UPDATE Entrevista
    SET fecha = p_fecha,
        id_postulante = p_id_postulante,
        feedback = p_feedback,
        estado = p_estado,
        puntuacion_final = p_puntuacion_final
    WHERE id_entrevista = p_id_entrevista;

END$$

DELIMITER ;