DROP PROCEDURE IF EXISTS MODIFICAR_ROL;

DELIMITER $$

CREATE PROCEDURE MODIFICAR_ROL (
    IN nuevo_nombre VARCHAR(100),
    IN rol_id INT,
    IN permisos_nuevos TEXT -- Por ejemplo: '1,2,4'
)
BEGIN
    DECLARE permiso_id INT;
    DECLARE coma_pos INT;

    -- Actualizar nombre del rol
    UPDATE Rol
    SET nombre = nuevo_nombre
    WHERE id_rol = rol_id;

    -- Eliminar permisos anteriores
    DELETE FROM Rol_Permiso
    WHERE id_rol = rol_id;

    -- Iterar sobre la cadena de permisos
    WHILE LENGTH(permisos_nuevos) > 0 DO
        SET coma_pos = LOCATE(',', permisos_nuevos);

        IF coma_pos > 0 THEN
            SET permiso_id = CAST(SUBSTRING(permisos_nuevos, 1, coma_pos - 1) AS UNSIGNED);
            SET permisos_nuevos = SUBSTRING(permisos_nuevos, coma_pos + 1);
        ELSE
            SET permiso_id = CAST(permisos_nuevos AS UNSIGNED);
            SET permisos_nuevos = '';
        END IF;

        INSERT INTO Rol_Permiso(id_rol, id_permiso)
        VALUES (rol_id, permiso_id);
    END WHILE;
END$$

DELIMITER ;
