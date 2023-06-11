import sys

from src.gestor_aplicacion.administracion.hospital import Hospital
import tkinter as tk

from src.ui_main.temp.facturacion_consola import facturacion_consola
from src.ui_main.ventana_principal import ventana_principal


def ventana_inicial(hospital):

    ventana = tk.Tk()
    ventana.title("Sistema de gestion hospitalaria")
    ventana.geometry("400x300")
    # ventana.protocol("WM_DELETE_WINDOW", hospital.serializar())

    boton = tk.Button(ventana, text="Ingresar a la aplicacion",
                      command=lambda: [ventana.destroy(), ventana_principal(hospital)])
    boton.pack()

    ventana.mainloop()


# Menu por consola temporal para ensayar las funcionalidades
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
            # hospital.serializar()
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
            facturacion_consola(hospital)
        elif opcion == 6:
            return
        elif opcion == 7:
            # hospital.serializar()
            sys.exit(0)


if __name__ == '__main__':
    hospital = Hospital()

    # Descomenten este y comenten el otro para ver la interfaz grafica
    ventana_inicial(hospital)

    # Descomenten este y comenten el otro para ver la interfaz por consola
    #menu_inicial(hospital)
