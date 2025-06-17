DROP PROCEDURE IF EXISTS LISTAR_EVENTO;

DELIMITER $$

CREATE PROCEDURE LISTAR_EVENTO()
BEGIN
	SELECT e.id_evento, e.fecha, e.tipoEvento, e.estadoEvento
	FROM Evento e
    WHERE e.estadoEvento = 'APROBADO';
END$$

DELIMITER ;

