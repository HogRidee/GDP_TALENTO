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
        s.desempenio,
        s.fecha_ingreso,
        s.fecha_salida
    FROM 
        MiembroPUCP m
    INNER JOIN 
        Staff s ON m.id_miembro_pucp = s.id_staff
    ORDER BY s.fecha_ingreso DESC;
END$$

DELIMITER ;
