DROP PROCEDURE IF EXISTS LISTAR_EVENTOS_FUTUROS;
DELIMITER $$
CREATE PROCEDURE LISTAR_EVENTOS_FUTUROS()
BEGIN
  SELECT 
    id_evento    AS id,
    fecha         AS fecha,
    tipo_evento   AS tipoEvento,
    estado_evento AS estadoEvento
  FROM Evento
  WHERE fecha > CURDATE()
  ORDER BY fecha ASC;
END$$
DELIMITER ;
