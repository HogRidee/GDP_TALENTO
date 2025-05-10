DROP PROCEDURE IF EXISTS ELIMINAR_ENTREVISTA;

DELIMITER $$

CREATE PROCEDURE ELIMINAR_ENTREVISTA (
    IN id_entrevista_eliminar INT
)
BEGIN
    DELETE FROM Entrevista
    WHERE id_entrevista = id_entrevista_eliminar;

    --Falta la tabla Eentrevista-evaluador??
END$$

DELIMITER ;
