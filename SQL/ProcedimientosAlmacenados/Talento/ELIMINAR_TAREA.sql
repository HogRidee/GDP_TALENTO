DROP PROCEDURE IF EXISTS ELIMINAR_TAREA;

DELIMITER $$

CREATE PROCEDURE ELIMINAR_TAREA(
    IN p_id_tarea INT
)
BEGIN
    -- Primero eliminar de la tabla intermedia
    DELETE FROM Tarea_Encargado WHERE id_tarea = p_id_tarea;

    -- Luego eliminar la tarea
    DELETE FROM Tarea WHERE id_tarea = p_id_tarea;
END$$

DELIMITER ;
