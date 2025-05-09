DROP PROCEDURE IF EXISTS ELIMINAR_ROL;

DELIMITER $$

CREATE PROCEDURE ELIMINAR_ROL (
    IN id_rol_eliminar INT
)
BEGIN
    -- Eliminar los permisos relacionados con el rol
    DELETE FROM Rol_Permiso
    WHERE id_rol = id_rol_eliminar;

    -- Eliminar el rol
    DELETE FROM Rol
    WHERE id_rol = id_rol_eliminar;
END$$

DELIMITER ;
