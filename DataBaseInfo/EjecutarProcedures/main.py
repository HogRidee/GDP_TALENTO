import os
import subprocess

# Configuración de la base de datos
DB_USER = "admin"
DB_PASS = "pv30g2sp1cDmaeu5404"
DB_NAME = "gamedevspucpRecursosHumanos"
DB_HOST = "gdpdatabase.celfjee0bnfs.us-east-1.rds.amazonaws.com"
BASE_DIR = r"D:\PROG3\GDP_TALENTO\GDP_TALENTO\SQL\ProcedimientosAlmacenados"

# Ruta completa del cliente MySQL (modificar si es necesario)
MYSQL_EXECUTABLE = r"C:\Program Files\MySQL\MySQL Workbench 8.0 CE"

# Procesar subcarpetas
for folder in ["Core", "Eventos", "Miembros", "Talento"]:
    folder_path = os.path.join(BASE_DIR, folder)
    print(f"Procesando carpeta: {folder_path}")

    for root, _, files in os.walk(folder_path):
        for file in files:
            if file.endswith(".sql"):
                file_path = os.path.join(root, file)
                print(f"Ejecutando script: {file_path}")

                if os.path.getsize(file_path) > 0:
                    try:
                        # Ejecutar el archivo SQL
                        command = [
                            MYSQL_EXECUTABLE,
                            f"-h{DB_HOST}",
                            f"-u{DB_USER}",
                            f"-p{DB_PASS}",
                            DB_NAME,
                            "-e", f"source \"{file_path}\""
                        ]
                        result = subprocess.run(command, text=True, capture_output=True)

                        if result.returncode == 0:
                            print(f"✓ Ejecución exitosa: {file_path}")
                        else:
                            print(f"✗ Error al ejecutar {file_path}:\n{result.stderr}")
                    except Exception as e:
                        print(f"⚠ Error inesperado con {file_path}:\n{e}")
                else:
                    print(f"⚠ Archivo vacío: {file_path}")
