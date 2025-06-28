CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_ENCARGADOS_EVENTO`(
    IN _id_evento INT
)
BEGIN
    SELECT s.id_usuario
    FROM Usuario s
    INNER JOIN Evento_Encargado ee ON s.id_usuario = ee.id_usuario
    WHERE ee.id_evento = _id_evento;
END