DROP PROCEDURE IF EXISTS LISTAR_TAREAS_PENDIENTES_SEMANA;
DELIMITER $$
CREATE PROCEDURE LISTAR_TAREAS_PENDIENTES_SEMANA()
BEGIN
  SELECT 
    id_tarea        AS id,
    descripcion     AS descripcion,
    fecha_limite    AS fechaLimite,
    estado          AS estado
  FROM Tarea
  WHERE estado = 'PENDIENTE'
    AND fecha_limite BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY)
  ORDER BY fecha_limite ASC;
END$$
DELIMITER ;