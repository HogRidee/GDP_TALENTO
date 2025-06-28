CREATE DEFINER=`admin`@`%` PROCEDURE `LISTAR_PARTICIPANTES_EVENTO`(
    IN _id_evento INT
)
BEGIN
    SELECT s.id_staff
    FROM Staff s
    INNER JOIN Evento_Participante ep ON s.id_staff = ep.id_staff
    WHERE ep.id_evento = _id_evento;
END