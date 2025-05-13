DROP PROCEDURE IF EXISTS MODIFICAR_ASISTENCIA;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_ASISTENCIA(
	IN _id_evento INT,
	IN _id_participante INT,
	IN _asistencia VARCHAR(20)
)
BEGIN
    UPDATE Asistencia set
	asistencia = _asistencia
	WHERE id_evento = _id_evento and id_participante = _id_participante;
END$$

DELIMITER ;