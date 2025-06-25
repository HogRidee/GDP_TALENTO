DROP PROCEDURE IF EXISTS SP_VARIACION_MIEMBROS;
DELIMITER $$

CREATE PROCEDURE SP_VARIACION_MIEMBROS(
  OUT diff INT
)
BEGIN
  -- Cuenta cuántos staff tienen fecha_ingreso posterior al último día
  -- del mes anterior (es decir, los ingresos de este mes)
  SELECT COUNT(*) 
    INTO diff
    FROM Staff
   WHERE fecha_ingreso > LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH));
END$$

DELIMITER ;
