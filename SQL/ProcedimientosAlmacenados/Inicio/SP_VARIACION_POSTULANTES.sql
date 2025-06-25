DROP PROCEDURE IF EXISTS SP_VARIACION_POSTULANTES;
DELIMITER $$

CREATE PROCEDURE SP_VARIACION_POSTULANTES(
  OUT diff INT
)
BEGIN
  -- Cuenta cuántos postulantes tienen fecha_ingreso posterior al último día
  -- del mes anterior (es decir, los ingresos de este mes)
  SELECT COUNT(*) 
    INTO diff
    FROM Staff
   WHERE fecha_ingreso > LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH));
END$$

DELIMITER ;
