CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_EVENTO`()
BEGIN
	SELECT e.id_evento, e.fecha, e.tipoEvento, e.estadoEvento
	FROM Evento e;
END