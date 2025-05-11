DROP PROCEDURE IF EXISTS INSERTAR_ROL;
DELIMITER $$

CREATE PROCEDURE INSERTAR_ROL(
    IN nombreRol VARCHAR(100),
    IN listaPermisos TEXT,
    OUT idRol INT
)
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE permisoId INT;
    DECLARE curPermisos CURSOR FOR 
        SELECT CAST(TRIM(SUBSTRING_INDEX(SUBSTRING_INDEX(listaPermisos, ',', numbers.n), ',', -1)) AS UNSIGNED) AS permiso
        FROM 
        (SELECT 1 n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 
         UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) numbers
        WHERE n <= 1 + LENGTH(listaPermisos) - LENGTH(REPLACE(listaPermisos, ',', ''));

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    -- Insertar rol
    INSERT INTO Rol(nombre) VALUES(nombreRol);
    SET idRol = LAST_INSERT_ID();

    -- Insertar permisos
    OPEN curPermisos;
    loop_permisos: LOOP
        FETCH curPermisos INTO permisoId;
        IF done THEN 
            LEAVE loop_permisos;
        END IF;
        INSERT INTO Rol_Permiso(id_rol, id_permiso) VALUES(idRol, permisoId);
    END LOOP;
    CLOSE curPermisos;
END$$

DELIMITER ;
