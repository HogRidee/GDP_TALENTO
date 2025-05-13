DROP PROCEDURE IF EXISTS LISTAR_POSTULANTE;
DELIMITER $$
CREATE PROCEDURE LISTAR_POSTULANTE(
)
BEGIN
	SELECT m.id_miembro_pucp, m.codigoPUCP, m.nombre, m.correo, m.telefono, m.facultad, m.especialidad, m.status, p.estado_proceso 
	FROM MiembroPUCP m INNER JOIN Postulantes p ON m.id_miembro_pucp = p.id_postulante;
END$$
DELIMITER ;