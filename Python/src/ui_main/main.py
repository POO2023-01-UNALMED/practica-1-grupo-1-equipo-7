import sys

from Python.src.gestor_aplicacion.administracion.hospital import Hospital
from Python.src.ui_main.facturacion import facturacion


def menu_inicial(hospital):
    while True:
        print("\nMENU INICIAL")
        print("1. Servicios para pacientes")
        print("2. Gestionar registros")
        print("3. --Salir--")

        opcion = int(input("Ingrese una opcion: "))

        if opcion == 1:
            menu_funcionalidades(hospital)
        elif opcion == 2:
            pass
        elif opcion == 3:
            hospital.serializar()
            sys.exit(0)


def menu_funcionalidades(hospital):
    while True:
        print("\nMENU FUNCIONALIDADES")
        print("1. Agendar una cita medica")
        print("2. Generar fórmula médica")
        print("3. Asignar habitación a un paciente")
        print("4. Aplicarse una vacuna")
        print("5. Facturacion")
        print("6. --Regresar al menu inicial--")
        print("7. --Salir--")

        opcion = int(input("Ingrese una opcion: "))

        if opcion == 1:
            pass
        elif opcion == 2:
            pass
        elif opcion == 3:
            pass
        elif opcion == 4:
            pass
        elif opcion == 5:
            facturacion(hospital)
        elif opcion == 6:
            return
        elif opcion == 7:
            hospital.serializar()
            sys.exit(0)


if __name__ == '__main__':
    hospital = Hospital()
    menu_inicial(hospital)
