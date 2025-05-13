DROP PROCEDURE IF EXISTS INSERTAR_EVENTO;

DELIMITER $$

CREATE PROCEDURE INSERTAR_EVENTO(
	IN _fecha DATE,
	IN _tipo VARCHAR(20),
	IN _estado VARCHAR(20)
)
BEGIN
    INSERT INTO Asistencia (fecha, tipoEvento, estadoEvento)
    VALUES(_fecha, _tipo, _estado);
END$$

DELIMITER ;
