DROP PROCEDURE IF EXISTS MODIFICAR_DATOS_BASICOS_USUARIO;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_DATOS_BASICOS_USUARIO(
    IN p_id_usuario INT,
    IN p_nombre VARCHAR(100),
    IN p_codigoPUCP INT,
    IN p_facultad VARCHAR(100),
    IN p_especialidad VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_telefono VARCHAR(15),
    IN p_area VARCHAR(50),
    IN p_status VARCHAR(20),
    IN p_estado VARCHAR(20)
)
BEGIN
    -- Actualizar datos en MiembroPUCP
    UPDATE MiembroPUCP
    SET nombre = p_nombre,
        codigoPUCP = p_codigoPUCP,
        facultad = p_facultad,
        especialidad = p_especialidad,
        correo = p_correo,
        telefono = p_telefono,
        status = p_status
    WHERE id_miembro_pucp = p_id_usuario;

    -- Actualizar datos en Staff
    UPDATE Staff
    SET area = p_area,
        estado = p_estado
    WHERE id_staff = p_id_usuario;
END$$

DELIMITER ;
