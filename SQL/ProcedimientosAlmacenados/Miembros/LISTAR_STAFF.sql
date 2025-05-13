DROP PROCEDURE IF EXISTS LISTAR_STAFF;

DELIMITER $$

CREATE PROCEDURE LISTAR_STAFF()
BEGIN
    SELECT 
        m.id_miembro_pucp,
        m.codigoPUCP,
        m.nombre,
        m.correo,
        m.telefono,
        m.facultad,
        m.especialidad,
        m.status,
        s.area,
        s.estado,
        s.desempenio
    FROM 
        MiembroPUCP m
    INNER JOIN 
        Staff s ON m.id_miembro_pucp = s.id_staff
    ORDER BY m.id_miembro_pucp;
END$$

DELIMITER ;
