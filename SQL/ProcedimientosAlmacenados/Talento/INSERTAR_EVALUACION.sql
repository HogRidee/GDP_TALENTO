DROP PROCEDURE IF EXISTS INSERTAR_EVALUACION;

DELIMITER $$

CREATE PROCEDURE INSERTAR_EVALUACION(
	OUT p_id_evaluacion_desempeno INT,
    IN p_id_evaluador INT,
    IN p_id_miembro_staff INT,
    IN p_puntaje INT,
    IN p_comentarios VARCHAR(100),
    IN p_fecha DATE
)
BEGIN
    INSERT INTO Entrevista(id_evaluador, id_miembro_staff, puntaje, comentarios, fecha)
    VALUES(p_id_evaluador, p_id_miembro_staff, p_puntaje, p_comentarios, p_fecha);

    SET p_id_evaluacion_desempeno = LAST_INSERT_ID();
END$$

DELIMITER ;