DROP PROCEDURE IF EXISTS INSERTAR_POSTULANTE;

DELIMITER $$

CREATE PROCEDURE INSERTAR_POSTULANTE(
    OUT _id_postulante INT,
    IN _nombre VARCHAR(100),
    IN _correo VARCHAR(100),
    IN _codigoPUCP INT,
    IN _facultad VARCHAR(100),
    IN _especialidad VARCHAR(100),
    IN _status VARCHAR(20),
    IN _telefono VARCHAR(15),
    IN _estado_proceso VARCHAR(20)
)
BEGIN
    INSERT INTO MiembroPUCP(nombre, correo, codigoPUCP, facultad, especialidad, status, telefono)
    VALUES (_nombre, _correo, _codigoPUCP, _facultad, _especialidad, _status, _telefono);

    SET _id_postulante = LAST_INSERT_ID();

    INSERT INTO Postulante(id_postulante, estado_proceso)
    VALUES (_id_postulante, _estado_proceso);
END$$

DELIMITER ;
