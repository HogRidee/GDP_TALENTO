DROP PROCEDURE IF EXISTS LISTAR_ASISTENCIA;

DELIMITER $$

CREATE PROCEDURE LISTAR_ASISTENCIA()
BEGIN
	SELECT s.id_staff, m.nombre, m.telefono, s.area, a.asistencia, 
	a.id_evento, e.fecha, e.tipoEvento, e.estadoEvento
    FROM Asistencia a, Staff s, MiembroPUCP m, Evento e
    WHERE a.id_participante = s.id_staff
    AND s.id_Staff = m.id_miembro_pucp
    AND a.id_evento = e.id_evento
    AND a.asistencia = 'ASISTIO'
    AND e.estadoEvento = 'APROBADO'
    AND e.tipoEvento = 'REUNION'
    AND s.estado = 'ACTIVO';
END$$

DELIMITER ;


