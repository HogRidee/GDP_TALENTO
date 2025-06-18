DROP PROCEDURE IF EXISTS BUSCAR_USUARIO_POR_ID;
DELIMITER $$

CREATE PROCEDURE BUSCAR_USUARIO_POR_ID(IN id INT)
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
        s.fecha_ingreso,
        s.desempenio,
        u.hash_contrasena,
        u.id_rol -- <--- aquí devolvemos el id del rol en lugar del nombre
    FROM MiembroPUCP m
    INNER JOIN Staff s ON m.id_miembro_pucp = s.id_staff
    INNER JOIN Usuario u ON u.id_usuario = s.id_staff
    LEFT JOIN Rol r ON u.id_rol = r.id_rol
    WHERE m.id_miembro_pucp = id;
END$$

DELIMITER ;
