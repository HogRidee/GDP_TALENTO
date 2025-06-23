DROP PROCEDURE IF EXISTS LISTAR_ASISTENCIA_STAFF;

DELIMITER $$

CREATE PROCEDURE LISTAR_ASISTENCIA_STAFF (
    IN p_id_staff INT
)
BEGIN
    SELECT 
        s.id_staff, 
        m.nombre, 
        m.telefono, 
        s.area, 
        a.asistencia, 
        a.id_evento, 
        e.fecha, 
        e.tipoEvento, 
        e.estadoEvento
    FROM 
        Asistencia a
        INNER JOIN Staff s ON a.id_participante = s.id_staff
        INNER JOIN MiembroPUCP m ON s.id_staff = m.id_miembro_pucp
        INNER JOIN Evento e ON a.id_evento = e.id_evento
    WHERE 
        s.id_staff = p_id_staff
        AND e.estadoEvento = 'APROBADO'
    ORDER BY 
        e.fecha ASC;
END$$

DELIMITER ;


