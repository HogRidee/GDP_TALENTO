DROP PROCEDURE IF EXISTS ELIMINAR_EVENTO;

DELIMITER $$

CREATE PROCEDURE ELIMINAR_EVENTO(
	IN _id_evento INT
)
BEGIN
    UPDATE Evento SET
	estado = 'CANCELADO'
    WHERE id_evento = _id_evento;
END$$

DELIMITER ;
