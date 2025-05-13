DROP PROCEDURE IF EXISTS INSERTAR_STAFF;
DELIMITER $$
CREATE PROCEDURE INSERTAR_STAFF(
    IN _nombre VARCHAR(100),
    IN _correo VARCHAR(100),
    IN _codigoPUCP INT,
    IN _facultad VARCHAR(100),
    IN _especialidad VARCHAR(100),
    IN _status VARCHAR(10),
    IN _telefono VARCHAR(15),
	IN _area VARCHAR(20),
	IN _fecha_ingreso DATE,
	IN _estado VARCHAR(10),
	IN _fecha_salida DATE,
	IN _desempenio DOUBLE
)
BEGIN
DECLARE miembroid INT;

	CALL INSERTAR_MIEMBROPUCP(_nombre, _correo, _codigoPUCP, _facultad, _especialidad, _status, _telefono);
	SELECT @@last_insert_id AS miembroid;
	INSERT INTO Staff(id_staff, area, fecha_ingreso, estado, fecha_salida, desempenio) VALUES (miembroid, _area, _fecha_ingreso, _estado, _fecha_salida, _desempenio);
END$$
DELIMITER ;

