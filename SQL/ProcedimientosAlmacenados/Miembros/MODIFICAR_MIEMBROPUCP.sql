DROP PROCEDURE IF EXISTS MODIFICAR_MIEMBROPUCP;
DELIMITER $$
CREATE PROCEDURE MODIFICAR_MIEMBROPUCP(
	IN _miembroid INT,
	IN _correo VARCHAR(100),
	IN _facultad VARCHAR(100),
	IN _especialidad VARCHAR(100),
	IN _status VARCHAR(10),
	IN _telefono VARCHAR(15)
)
BEGIN
	UPDATE MiembroPUCP SET correo = _correo, facultad = _facultad, especialidad = _especialidad, status = _status, telefono = _telefono WHERE id_miembro_pucp = _miembroid;
END$$
DELIMITER ;