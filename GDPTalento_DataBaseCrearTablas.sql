-- ==============================================================
-- PAQUETE: pe.edu.pucp.gdptalento.core.model
-- Dropear tablas
DROP TABLE IF EXISTS Rol_Permiso;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Permiso;
DROP TABLE IF EXISTS Rol;

CREATE TABLE Permiso (
    id_permiso INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE
)ENGINE=InnoDB;

-- Tabla Rol
CREATE TABLE Rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE
)ENGINE=InnoDB;

-- Relación muchos a muchos entre Rol y Permiso (
CREATE TABLE Rol_Permiso (
    id_rol INT,
    id_permiso INT,
    PRIMARY KEY(id_rol, id_permiso),
    FOREIGN KEY(id_rol) REFERENCES Rol(id_rol),
    FOREIGN KEY(id_permiso) REFERENCES Permiso(id_permiso)
)ENGINE=InnoDB;

-- ==============================================================
-- PAQUETE: pe.edu.pucp.gdptalento.miembros.model
-- Dropear tablas
DROP TABLE IF EXISTS Postulante;
DROP TABLE IF EXISTS Staff;
DROP TABLE IF EXISTS MiembroPUCP;

-- Tabla MiembroPUCP
CREATE TABLE MiembroPUCP (
    id_miembro_pucp INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100),
    codigoPUCP INT UNIQUE,
    facultad VARCHAR(100),
    especialidad VARCHAR(100),
    status ENUM('MATRICULADO', 'NO_MATRICULADO', 'EGRESADO', 'EXTERNO'),
    telefono VARCHAR(15)
)ENGINE=InnoDB;

-- Tabla Staff
CREATE TABLE Staff (
    id_staff INT PRIMARY KEY,
    area ENUM('RECURSOS_HUMANOS', 'PRESIDENCIA', 'MARKETING', 'GDP_ACADEMY', 'EVENTOS'),
    fecha_ingreso DATE,
    estado ENUM('ACTIVO', 'INACTIVO'),
    fecha_salida DATE,
    desempenio DOUBLE,
    FOREIGN KEY(id_staff) REFERENCES MiembroPUCP(id_miembro_pucp)
)ENGINE=InnoDB;

-- Tabla Usuario (Hereda de Staff)
CREATE TABLE Usuario (
    id_usuario INT PRIMARY KEY,
    hash_contrasena VARCHAR(255),
    id_rol INT,
    FOREIGN KEY(id_usuario) REFERENCES Staff(id_staff),
    FOREIGN KEY(id_rol) REFERENCES Rol(id_rol)
)ENGINE=InnoDB;

-- Tabla Postulante
CREATE TABLE Postulante (
    id_postulante INT PRIMARY KEY,
    estado_proceso ENUM('PENDIENTE', 'APROBADO', 'RECHAZADO'),
    FOREIGN KEY(id_postulante) REFERENCES MiembroPUCP(id_miembro_pucp)
)ENGINE=InnoDB;

-- ==============================================================
-- PAQUETE: pe.edu.pucp.gdptalento.talento.model
-- Dropear tablas
DROP TABLE IF EXISTS EvaluacionDesempeno;
DROP TABLE IF EXISTS Entrevista;
DROP TABLE IF EXISTS Tarea;

-- Tabla EvaluacionDesempeno
CREATE TABLE EvaluacionDesempeno (
    id_evaluacion_desempeno INT AUTO_INCREMENT PRIMARY KEY,
    id_evaluador INT,
    id_miembro_staff INT,
    puntaje INT,
    comentarios VARCHAR(5000),
    fecha DATE,
    FOREIGN KEY(id_evaluador) REFERENCES Usuario(id_usuario),
    FOREIGN KEY(id_miembro_staff) REFERENCES Staff(id_staff)
)ENGINE=InnoDB;

-- Tabla Entrevista
CREATE TABLE Entrevista (
    id_entrevista INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    id_postulante INT,
    feedback VARCHAR(5000),
    estado ENUM('PROGRAMADA', 'REALIZADA', 'CANCELADA'),
    puntuacion_final DOUBLE,
    FOREIGN KEY(id_postulante) REFERENCES Postulante(id_postulante)
)ENGINE=InnoDB;

-- Entrevistadores (relación muchos a muchos)
CREATE TABLE Entrevista_Entrevistador (
    id_entrevista INT,
    id_entrevistador INT,
    puntaje_entrevistador DOUBLE,
    PRIMARY KEY(id_entrevista, id_entrevistador),
    FOREIGN KEY(id_entrevista) REFERENCES Entrevista(id_entrevista),
    FOREIGN KEY(id_entrevistador) REFERENCES Usuario(id_usuario)
)ENGINE=InnoDB;

-- Tabla Tarea
CREATE TABLE Tarea (
    id_tarea INT AUTO_INCREMENT PRIMARY KEY,
    fecha_creacion DATE,
    id_creador INT,
    fecha_limite DATE,
    estado ENUM('EN_PROCESO', 'PENDIENTE', 'REALIZADA'),
    FOREIGN KEY(id_creador) REFERENCES Usuario(id_usuario)
)ENGINE=InnoDB;

-- Relación encargados de Tarea (Solo puede ser usuario)
CREATE TABLE Tarea_Encargado (
    id_tarea INT,
    id_usuario INT,
    PRIMARY KEY(id_tarea, id_usuario),
    FOREIGN KEY(id_tarea) REFERENCES Tarea(id_tarea),
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
)ENGINE=InnoDB;

-- ==============================================================
-- PAQUETE: pe.edu.pucp.gdptalento.eventos.model
-- Dropear tablas
DROP TABLE IF EXISTS Asistencia;
DROP TABLE IF EXISTS Evento;
DROP TABLE IF EXISTS Evento_Encargado;
DROP TABLE IF EXISTS Evento_Participante;

-- Tabla Evento
CREATE TABLE Evento (
    id_evento INT AUTO_INCREMENT,
    fecha DATE,
    tipoEvento ENUM('REUNION', 'INTEGRACION'),
    estadoEvento ENUM('APROBADO', 'CANCELADO'),
    PRIMARY KEY(id_evento)
)ENGINE=InnoDB;

-- Encargados del evento (relación muchos a muchos)
CREATE TABLE Evento_Encargado (
    id_evento INT,
    id_usuario INT,
    PRIMARY KEY(id_evento, id_usuario),
    FOREIGN KEY(id_evento) REFERENCES Evento(id_evento),
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
)ENGINE=InnoDB;

-- Participantes del evento (relación muchos a muchos)
CREATE TABLE Evento_Participante (
    id_evento INT,
    id_staff INT,
    PRIMARY KEY(id_evento, id_staff),
    FOREIGN KEY(id_evento) REFERENCES Evento(id_evento),
    FOREIGN KEY(id_staff) REFERENCES Staff(id_staff)
)ENGINE=InnoDB;

-- Tabla Asistencia
CREATE TABLE Asistencia (
    id_evento INT,
    id_participante INT,
    asistencia ENUM('ASISTIO', 'FALTO', 'JUSTIFICADO'),
    PRIMARY KEY(id_evento, id_participante),
    FOREIGN KEY(id_evento) REFERENCES Evento(id_evento),
    FOREIGN KEY(id_participante) REFERENCES Staff(id_staff)
)ENGINE=InnoDB;
