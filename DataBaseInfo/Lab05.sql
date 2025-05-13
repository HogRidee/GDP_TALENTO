SELECT * FROM Usuario;

SELECT * FROM EvaluacionDesempeno;

select * FROM  Staff;

select * from MiembroPUCP;

select * from Tarea;


-- Paso 1: Desactivar verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 0;

-- Paso 2: Truncar la tabla (elimina todos los datos y reinicia auto_increment)
TRUNCATE TABLE EvaluacionDesempeno;

-- Paso 3: Reactivar verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;