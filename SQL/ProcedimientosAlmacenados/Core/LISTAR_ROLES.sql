DROP PROCEDURE IF EXISTS LISTAR_ROLES;

DELIMITER $$

CREATE PROCEDURE LISTAR_ROLES()
BEGIN
    SELECT 
        r.id_rol,
        r.nombre AS nombre_rol,
        p.id_permiso,
        p.nombre AS nombre_permiso
    FROM 
        Rol r
    LEFT JOIN Rol_Permiso rp ON r.id_rol = rp.id_rol
    LEFT JOIN Permiso p ON rp.id_permiso = p.id_permiso
    ORDER BY r.id_rol;
END$$

DELIMITER ;

