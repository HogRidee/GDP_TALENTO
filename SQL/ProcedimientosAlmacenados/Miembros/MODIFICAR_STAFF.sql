DROP PROCEDURE IF EXISTS MODIFICAR_STAFF;
DELIMITER $$
CREATE PROCEDURE MODIFICAR_STAFF(
	IN _id INT,
	IN _correo VARCHAR(100),
	IN _facultad VARCHAR(100),
	IN _especialidad VARCHAR(100),
	IN _status VARCHAR(10),
	IN _telefono VARCHAR(15),
	IN _area VARCHAR(20),
	IN _estado VARCHAR(10),
	IN _fecha_salida DATE,
	IN _desempenio DOUBLE
)
BEGIN
	CALL MODIFICAR_MIEMBROPUCP(_id, _correo, _facultad, _especialidad, _status, _telefono, _id);
	UPDATE Staff SET area = _area, estado = _estado, fecha_salida = _fecha_salida, desempenio = _desempenio WHERE id_staff = _id;
END$$
DELIMITER ;