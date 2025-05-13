DROP PROCEDURE IF EXISTS MODIFICAR_MIEMBROPUCP;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_MIEMBROPUCP (
    IN _id_miembro_pucp INT,
    IN _nombre VARCHAR(100),
    IN _correo VARCHAR(100),
    IN _codigoPUCP INT,
    IN _facultad VARCHAR(100),
    IN _especialidad VARCHAR(100),
    IN _status VARCHAR(20),
    IN _telefono VARCHAR(15)
)
BEGIN
    UPDATE MiembroPUCP 
    SET 
        nombre = _nombre,
        correo = _correo,
        codigoPUCP = _codigoPUCP,
        facultad = _facultad,
        especialidad = _especialidad,
        status = _status,
        telefono = _telefono
    WHERE id_miembro_pucp = _id_miembro_pucp;
END$$

DELIMITER ;
