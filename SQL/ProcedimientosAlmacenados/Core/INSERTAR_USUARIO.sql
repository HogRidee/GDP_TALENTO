DROP PROCEDURE IF EXISTS INSERTAR_USUARIO;

DELIMITER $$

CREATE PROCEDURE INSERTAR_USUARIO(
	OUT p_id_usuario INT,
    IN p_nombre VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_codigoPUCP INT,
    IN p_facultad VARCHAR(100),
    IN p_especialidad VARCHAR(100),
    IN p_status VARCHAR(10),
    IN p_telefono VARCHAR(15),
    IN p_area VARCHAR(50),
    IN p_fecha_ingreso DATE,
    IN p_estado VARCHAR(10),
    IN p_fecha_salida DATE,
    IN p_desempenio DOUBLE,
    IN p_hash_contrasena VARCHAR(255)
)
BEGIN
    INSERT INTO MiembroPUCP(nombre, correo, codigoPUCP, facultad, especialidad, status, telefono)
    VALUES(p_nombre, p_correo, p_codigoPUCP, p_facultad, p_especialidad, p_status, p_telefono);

    SET p_id_usuario = LAST_INSERT_ID();

    INSERT INTO Staff(id_staff, area, fecha_ingreso, estado, fecha_salida, desempenio)
    VALUES(p_id_usuario, p_area, p_fecha_ingreso, p_estado, p_fecha_salida, p_desempenio);

    INSERT INTO Usuario(hash_contrasena, id_usuario)
    VALUES(p_hash_contrasena, p_id_usuario);
END$$

DELIMITER ;
