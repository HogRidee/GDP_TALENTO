DROP PROCEDURE IF EXISTS MODIFICAR_TAREA;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_TAREA(
    IN p_id_tarea INT,
    IN p_fecha_creacion DATE,
    IN p_id_creador INT,
    IN p_fecha_limite DATE,
    IN p_estado VARCHAR(25)
)
BEGIN
    UPDATE Tarea
    SET fecha_creacion = p_fecha_creacion,
        id_creador = p_id_creador,
        fecha_limite = p_fecha_limite,
        estado = p_estado
    WHERE id_tarea = p_id_tarea;
END$$

DELIMITER ;
