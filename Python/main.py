from src.gestor_aplicacion.administracion.hospital import Hospital
from src.ui_main.ventana_inicial import ventana_inicial

if __name__ == '__main__':
    medplus = Hospital()

    ventana_inicial(medplus)
