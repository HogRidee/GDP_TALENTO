DROP PROCEDURE IF EXISTS INSERTAR_ENTREVISTA;

DELIMITER $$

CREATE PROCEDURE INSERTAR_ENTREVISTA(
	OUT p_id_entrevista INT,
    IN p_id_postulante INT,
    IN p_fecha DATE,
    IN p_feedback VARCHAR(100),
    IN p_estado VARCHAR(25),
    IN p_puntuacion_final DOUBLE
)
BEGIN
    INSERT INTO Entrevista(fecha, id_postulante, feedback, estado, puntuacion_final)
    VALUES(p_fecha, p_id_postulante, p_feedback, p_estado, p_puntuacion_final);

    SET p_id_entrevista = LAST_INSERT_ID();
END$$

DELIMITER ;