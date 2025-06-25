DROP PROCEDURE IF EXISTS LISTAR_TAREAS_PENDIENTES;
DELIMITER $$
CREATE PROCEDURE LISTAR_TAREAS_PENDIENTES()
BEGIN
  SELECT 
    id_tarea        AS id,
    descripcion     AS descripcion,
    fecha_limite    AS fechaLimite,
    estado          AS estado
  FROM Tarea
  WHERE estado = 'PENDIENTE'
  ORDER BY fecha_limite ASC;
END$$
DELIMITER ;