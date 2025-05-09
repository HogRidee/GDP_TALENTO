DROP PROCEDURE IF EXISTS LISTAR_USUARIOS;

DELIMITER $$

CREATE PROCEDURE LISTAR_USUARIOS()
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
        u.hash_contrasena,
        r.nombre AS nombre_rol
    FROM MiembroPUCP m
    INNER JOIN Staff s ON m.id_miembro_pucp = s.id_staff
    INNER JOIN Usuario u ON u.id_usuario = s.id_staff
    LEFT JOIN Rol r ON u.id_rol = r.id_rol;
END$$

DELIMITER ;
