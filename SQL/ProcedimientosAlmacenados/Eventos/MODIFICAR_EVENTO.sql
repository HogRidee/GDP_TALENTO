DROP PROCEDURE IF EXISTS MODIFICAR_EVENTO;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_EVENTO(
	IN _id_evento INT,
	IN _fecha DATE,
	IN _tipo VARCHAR(20),
	IN _estado VARCHAR(20)
)
BEGIN
    UPDATE Evento SET fecha = _fecha,
	tipo = _tipo,
	estado = _estado
    WHERE id_evento = _id_evento;
END$$

DELIMITER ;