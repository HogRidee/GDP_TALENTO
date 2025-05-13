DROP PROCEDURE IF EXISTS LISTAR_ENTREVISTAS;

DELIMITER $$

CREATE PROCEDURE LISTAR_ENTREVISTAS()
BEGIN
    SELECT 
        e.id_entrevista,
        e.fecha AS fecha_entrevista,
        e.estado,
        e.id_postulante,
        p.nombre AS entrevistador,
        e.puntuacion_final,
        e.feedback
    FROM 
        Entrevista e
    LEFT JOIN Postulante p ON e.id_postulante = p.id_postulante
    ORDER BY e.id_entrevista;
END$$

DELIMITER ;
