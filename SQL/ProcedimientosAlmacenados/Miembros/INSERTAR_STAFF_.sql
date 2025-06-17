DROP PROCEDURE IF EXISTS INSERTAR_STAFF_;

DELIMITER $$

CREATE PROCEDURE INSERTAR_STAFF_(
    IN p_nombre VARCHAR(100), 
    IN p_correo VARCHAR(100), 
    IN p_codigoPUCP INT, 
    IN p_facultad VARCHAR(100), 
    IN p_especialidad VARCHAR(100), 
    IN p_status VARCHAR(20), 
    IN p_telefono VARCHAR(20), 
    IN p_area VARCHAR(50),
    IN p_fecha_ingreso DATE, 
    IN p_estado VARCHAR(10), 
    IN p_fecha_salida DATE, 
    IN p_desempenio DOUBLE,
    OUT p_id_staff INT
)
BEGIN
    -- Insertar en MiembroPUCP
    INSERT INTO MiembroPUCP(nombre, correo, codigoPUCP, facultad, especialidad, status, telefono)
    VALUES(p_nombre, p_correo, p_codigoPUCP, p_facultad, p_especialidad, p_status, p_telefono);

    -- Obtener ID generado
    SET p_id_staff =  @@last_insert_id;

    -- Insertar en Staff
    INSERT INTO Staff(id_staff, area, fecha_ingreso, estado, fecha_salida, desempenio)
    VALUES(p_id_staff, p_area, p_fecha_ingreso, p_estado, p_fecha_salida, p_desempenio);
END$$

DELIMITER ;
