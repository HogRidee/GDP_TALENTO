DROP PROCEDURE IF EXISTS LISTAR_TAREA;

DELIMITER $$

CREATE PROCEDURE LISTAR_TAREA()
BEGIN
    SELECT 
        id_tarea,
        fecha_creacion,
        id_creador,
        fecha_limite,
        estado
    FROM Tarea;
END$$

DELIMITER ;
