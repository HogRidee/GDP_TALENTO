-- ========================
-- Permisos y Roles
-- ========================
INSERT INTO Rol (nombre) VALUES 
('ADMIN'),
('RECLUTADOR'),
('COORDINADOR');

-- Asignar permisos a roles
INSERT INTO Rol_Permiso (id_rol, id_permiso) VALUES 
(1, 1), (1, 2), (1, 3), (1, 4),     -- ADMIN tiene todos los permisos
(2, 4),                            -- RECLUTADOR puede crear entrevistas
(3, 3);                            -- COORDINADOR puede crear tareas

-- ========================
-- Miembros PUCP
-- ========================
INSERT INTO MiembroPUCP (nombre, correo, codigoPUCP, facultad, especialidad, status, telefono) VALUES 
('Ana Torres', 'ana.torres@pucp.edu.pe', 20204567, 'Ingeniería', 'Informática', 'MATRICULADO', '987654321'),
('Luis Gómez', 'luis.gomez@pucp.edu.pe', 20201234, 'Gestión', 'Marketing', 'MATRICULADO', '998877665'),
('María Pérez', 'maria.perez@pucp.edu.pe', 20207890, 'Derecho', 'Derecho', 'EGRESADO', '912345678'),
('Carlos Ruiz', 'carlos.ruiz@pucp.edu.pe', 20201111, 'Ingeniería', 'Electrónica', 'MATRICULADO', '987123456');

-- ========================
-- Staff
-- ========================
INSERT INTO Staff (id_staff, area, fecha_ingreso, estado, fecha_salida, desempenio) VALUES 
(1, 'MARKETING', '2024-03-01', 'ACTIVO', NULL, 4.5),
(2, 'RECURSOS_HUMANOS', '2023-10-15', 'ACTIVO', NULL, 4.0),
(3, 'EVENTOS', '2024-01-20', 'INACTIVO', '2025-01-15', 3.8);

-- ========================
-- Usuarios
-- ========================
INSERT INTO Usuario (id_usuario, hash_contrasena, id_rol) VALUES 
(1, 'hash12345', 1), -- Ana - Admin
(2, 'hash54321', 2), -- Luis - Reclutador
(3, 'hash98765', 3); -- María - Coordinador

-- ========================
-- Postulantes
-- ========================
INSERT INTO Postulante (id_postulante, estado_proceso) VALUES 
(4, 'PENDIENTE');

-- ========================
-- Evaluación de Desempeño
-- ========================
INSERT INTO EvaluacionDesempeno (id_evaluador, id_miembro_staff, puntaje, comentarios, fecha) VALUES 
(1, 3, 15, 'Buen desempeño general, pero puede mejorar en puntualidad', '2025-03-20');

-- ========================
-- Entrevistas
-- ========================
INSERT INTO Entrevista (fecha, id_postulante, feedback, estado, puntuacion_final) VALUES 
('2025-05-20', 4, 'Buena actitud y disposición', 'REALIZADA', 17.5);

-- Entrevistadores
INSERT INTO Entrevista_Entrevistador (id_entrevista, id_entrevistador, puntaje_entrevistador) VALUES 
(1, 2, 18.0);

-- ========================
-- Tareas
-- ========================
INSERT INTO Tarea (fecha_creacion, id_creador, fecha_limite, estado) VALUES 
('2025-05-10', 3, '2025-06-01', 'PENDIENTE');

-- Encargados de tarea
INSERT INTO Tarea_Encargado (id_tarea, id_usuario) VALUES 
(1, 1),
(1, 2);

-- ========================
-- Eventos
-- ========================
INSERT INTO Evento (fecha, tipoEvento, estadoEvento) VALUES 
('2025-05-25', 'REUNION', 'APROBADO'),
('2025-06-05', 'INTEGRACION', 'CANCELADO');

-- Encargados
INSERT INTO Evento_Encargado (id_evento, id_usuario) VALUES 
(1, 3);

-- Participantes
INSERT INTO Evento_Participante (id_evento, id_staff) VALUES 
(1, 1),
(1, 2);

-- Asistencia
INSERT INTO Asistencia (id_evento, id_participante, asistencia) VALUES 
(1, 1, 'ASISTIO'),
(1, 2, 'FALTO');
