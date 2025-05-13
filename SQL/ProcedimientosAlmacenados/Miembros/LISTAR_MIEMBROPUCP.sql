DROP PROCEDURE IF EXISTS LISTAR_MIEMBROPUCP;

DELIMITER $$

CREATE PROCEDURE LISTAR_MIEMBROPUCP()
BEGIN
    SELECT 
        m.id_miembro_pucp, 
        m.nombre, 
        m.correo, 
        m.codigoPUCP, 
        m.facultad, 
        m.especialidad, 
        m.status, 
        m.telefono 
    FROM 
        MiembroPUCP m 
    WHERE 
        m.status = 'MATRICULADO';
END$$

DELIMITER ;
