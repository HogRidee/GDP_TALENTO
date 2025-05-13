DROP PROCEDURE IF EXISTS INSERTAR_MIEMBROPUCP;

DELIMITER $$

CREATE PROCEDURE INSERTAR_MIEMBROPUCP (
    OUT _id_miembro_pucp INT,
    IN _nombre VARCHAR(100),
    IN _correo VARCHAR(100),
    IN _codigoPUCP INT,
    IN _facultad VARCHAR(100),
    IN _especialidad VARCHAR(100),
    IN _status VARCHAR(20),
    IN _telefono VARCHAR(15)
)
BEGIN
    INSERT INTO MiembroPUCP (
        nombre, correo, codigoPUCP, facultad, especialidad, status, telefono
    ) VALUES (
        _nombre, _correo, _codigoPUCP, _facultad, _especialidad, _status, _telefono
    );
    
    SET _id_miembro_pucp = LAST_INSERT_ID();
END$$

DELIMITER ;
