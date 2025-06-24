DELIMITER //

CREATE PROCEDURE LISTAR_ENCARGADOS_EVENTO (
    IN _id_evento INT
)
BEGIN
    SELECT s.id_staff, s.nombre, s.codigo_pucp, s.area
    FROM Staff s
    INNER JOIN Evento_Encargado ee ON s.id_staff = ee.id_staff
    WHERE ee.id_evento = _id_evento;
END//
DELIMITER ;

