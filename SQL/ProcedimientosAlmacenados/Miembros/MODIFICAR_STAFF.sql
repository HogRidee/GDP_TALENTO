DROP PROCEDURE IF EXISTS MODIFICAR_STAFF;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_STAFF(
    IN p_id_staff INT,
    IN p_correo VARCHAR(100),
    IN p_facultad VARCHAR(100),
    IN p_especialidad VARCHAR(100),
    IN p_status VARCHAR(10),
    IN p_telefono VARCHAR(20),
    IN p_area VARCHAR(50),
    IN p_estado VARCHAR(10),
    IN p_fecha_salida DATE,
    IN p_desempenio DOUBLE
)
BEGIN
    -- Actualizar MiembroPUCP
    UPDATE MiembroPUCP
    SET correo = p_correo,
        facultad = p_facultad,
        especialidad = p_especialidad,
        status = p_status,
        telefono = p_telefono
    WHERE id_miembro_pucp = p_id_staff;

    -- Actualizar Staff
    UPDATE Staff
    SET area = p_area,
        estado = p_estado,
        fecha_salida = p_fecha_salida,
        desempenio = p_desempenio
    WHERE id_staff = p_id_staff;
END$$

DELIMITER ;
