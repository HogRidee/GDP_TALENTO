DROP PROCEDURE IF EXISTS INSERTAR_POSTULANTE;
DELIMITER $$
CREATE PROCEDURE INSERTAR_POSTULANTE(
    IN _nombre VARCHAR(100),
    IN _correo VARCHAR(100),
    IN _codigoPUCP INT,
    IN _facultad VARCHAR(100),
    IN _especialidad VARCHAR(100),
    IN _status VARCHAR(10),
    IN _telefono VARCHAR(15),
	IN _estado_proceso VARCHAR(10)
)
BEGIN
DECLARE postulanteid INT;

	CALL INSERTAR_MIEMBROPUCP(_nombre, _correo, _codigoPUCP, _facultad, _especialidad, _status, _telefono);
	SELECT @@last_insert_id AS postulanteid;
	INSERT INTO Postulante(id_postulante, estado_proceso) VALUES(postulanteid, _estado_proceso);
END$$
DELIMITER ;




