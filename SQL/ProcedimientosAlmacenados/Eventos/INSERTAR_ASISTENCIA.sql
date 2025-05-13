DROP PROCEDURE IF EXISTS INSERTAR_ASISTENCIA;

DELIMITER $$

CREATE PROCEDURE INSERTAR_ASISTENCIA(
	IN _id_evento INT,
	IN _id_participante INT,
	IN _asistencia VARCHAR(20)
)
BEGIN
    INSERT INTO Asistencia (id_evento, id_participante, asistencia)
    VALUES(_id_evento, _id_participante, _asistencia);
END$$

DELIMITER ;






