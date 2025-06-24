CREATE DEFINER=`admin`@`%` PROCEDURE `MODIFICAR_EVENTO`(
	IN _id_evento INT,
	IN _fecha DATE,
	IN _tipo VARCHAR(20),
	IN _estado VARCHAR(20)
)
BEGIN
    UPDATE Evento SET fecha = _fecha,
	tipoEvento = _tipo,
	estadoEvento = _estado
    WHERE id_evento = _id_evento;
END