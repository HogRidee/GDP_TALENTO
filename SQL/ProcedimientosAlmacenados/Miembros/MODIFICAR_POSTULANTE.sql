DROP PROCEDURE IF EXISTS MODIFICAR_POSTULANTE;
DELIMITER $$
CREATE PROCEDURE MODIFICAR_POSTULANTE(
	IN _id INT,
	IN _correo VARCHAR(100),
	IN _facultad VARCHAR(100),
	IN _especialidad VARCHAR(100),
	IN _status VARCHAR(10),
	IN _telefono VARCHAR(15),
	IN _estado_proceso VARCHAR(10)
)
BEGIN
	CALL MODIFICAR_MIEMBROPUCP(_id, _correo, _facultad, _especialidad, _status, _telefono, _miembroid);
	UPDATE Postulante SET estado_proceso = _estado_proceso WHERE id_postulante = _id;
END$$
DELIMITER ;