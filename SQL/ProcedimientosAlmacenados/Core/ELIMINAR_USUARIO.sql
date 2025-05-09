DROP PROCEDURE IF EXISTS ELIMINAR_USUARIO;

DELIMITER $$

CREATE PROCEDURE ELIMINAR_USUARIO(
    IN p_id_usuario INT
)
BEGIN
    -- Eliminar de la tabla Usuario
    DELETE FROM Usuario WHERE id_usuario = p_id_usuario;

    -- Eliminar de la tabla Staff
    DELETE FROM Staff WHERE id_staff = p_id_usuario;

    -- Eliminar de la tabla MiembroPUCP
    DELETE FROM MiembroPUCP WHERE id_miembro_pucp = p_id_usuario;
END$$

DELIMITER ;
