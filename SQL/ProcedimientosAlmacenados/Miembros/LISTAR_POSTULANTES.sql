DROP PROCEDURE IF EXISTS LISTAR_POSTULANTES;

DELIMITER $$

CREATE PROCEDURE LISTAR_POSTULANTES()
BEGIN
    SELECT 
        m.id_miembro_pucp, 
        m.nombre, 
        m.correo, 
        m.codigoPUCP, 
        m.facultad, 
        m.especialidad, 
        m.status, 
        m.telefono, 
        p.estado_proceso 
    FROM 
        MiembroPUCP m
    INNER JOIN 
        Postulante p ON m.id_miembro_pucp = p.id_postulante
    WHERE 
        m.status = 'MATRICULADO';
END$$

DELIMITER ;
