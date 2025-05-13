DROP PROCEDURE IF EXISTS INSERTAR_TAREA_ENCARGADO;

DELIMITER $$

CREATE PROCEDURE INSERTAR_TAREA_ENCARGADO(
    IN p_id_tarea INT,
    IN p_id_usuario INT
)
BEGIN
    INSERT INTO Tarea_Encargado(id_tarea, id_usuario)
    VALUES(p_id_tarea, p_id_usuario);
END$$

DELIMITER ;
