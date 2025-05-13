DROP PROCEDURE IF EXISTS INSERTAR_TAREA;

DELIMITER $$

CREATE PROCEDURE INSERTAR_TAREA(
    OUT p_id_tarea INT,
    IN p_fecha_creacion DATE,
    IN p_id_creador INT,
	IN p_fecha_limite DATE,
    IN p_estado VARCHAR(25)
)
BEGIN
    INSERT INTO Tarea(fecha_creacion, id_creador, fecha_limite, estado)
    VALUES(p_fecha_creacion, p_id_creador, p_fecha_limite, p_estado);

    SET p_id_tarea = LAST_INSERT_ID();
END$$

DELIMITER ;