DROP PROCEDURE IF EXISTS MODIFICAR_USUARIO;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_USUARIO(
    IN p_id_usuario INT,
    IN p_correo VARCHAR(100),
    IN p_facultad VARCHAR(100),
    IN p_especialidad VARCHAR(100),
    IN p_status VARCHAR(20),
    IN p_telefono VARCHAR(15),
    IN p_area VARCHAR(50),
    IN p_estado VARCHAR(20),
    IN p_fecha_salida DATE,
    IN p_desempenio DOUBLE,
    IN p_hash_contrasena VARCHAR(255)
)
BEGIN
    -- Actualizar datos en MiembroPUCP
    UPDATE MiembroPUCP
    SET correo = p_correo,
        facultad = p_facultad,
        especialidad = p_especialidad,
        status = p_status,
        telefono = p_telefono
    WHERE id_miembro_pucp = p_id_usuario;

    -- Actualizar datos en Staff
    UPDATE Staff
    SET area = p_area,
        estado = p_estado,
        fecha_salida = p_fecha_salida,
        desempenio = p_desempenio
    WHERE id_staff = p_id_usuario;

    -- Actualizar datos en Usuario
    UPDATE Usuario
    SET hash_contrasena = p_hash_contrasena
    WHERE id_usuario = p_id_usuario;
END$$

DELIMITER ;
