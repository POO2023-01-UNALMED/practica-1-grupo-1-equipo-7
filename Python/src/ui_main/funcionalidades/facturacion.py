import tkinter as tk
from tkinter import messagebox

from Python.src.gestor_aplicacion.servicios.servicio import Servicio


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Facturación", bg="white")
    titulo.pack()


def facturacion(hospital, frame):
    def validar_pago(paciente, servicio):
        respuesta = tk.messagebox.askyesno("Confirmar pago", "¿Desea realizar el pago?")
        if respuesta:
            servicio.validar_pago(paciente, servicio.id_servicio)
            messagebox.showinfo("Pago realizado", "El pago se ha realizado exitosamente")
        else:
            messagebox.showinfo("Pago cancelado", "El pago ha sido cancelado")

        # Se importa aca para evitar una referencia circular
        from Python.src.ui_main.ventana_principal import implementacion_default
        implementacion_default(frame)

    def calcular_precio(paciente, servicio):
        imprimir_titulo(frame)

        info_servicio = tk.Label(frame, text=f"{servicio.descripcion_servicio()}", bg="white")
        info_servicio.pack()

        precio_servicio_seleccionado = paciente.calcular_precio(servicio)

        precio = tk.Label(frame, text=f"Total a pagar: ${precio_servicio_seleccionado}", bg="white")
        precio.pack()

        boton_pagar = tk.Button(frame, text="Realizar pago",
                                command=lambda: validar_pago(paciente, servicio))
        boton_pagar.pack()

    def obtener_servicios_sin_pagar(paciente):
        imprimir_titulo(frame)

        info_paciente = tk.Label(frame, text=f"{paciente.nombre} - CC: {paciente.cedula}", bg="white")
        info_paciente.pack()

        label_servicios = tk.Label(frame, text="Lista de servicios sin pagar:", bg="white")
        label_servicios.pack()

        lista_servicios_sin_pagar = Servicio.obtener_servicios_sin_pagar(paciente)

        if len(lista_servicios_sin_pagar) == 0:
            tk.messagebox.showinfo("Sin servicios pendientes", "Usted no tiene ningún servicio pendiente de pago")
            return

        # Imprime cada servicio en un label
        for servicio in lista_servicios_sin_pagar:
            label_servicio = tk.Label(frame, text=servicio.descripcion_servicio(), bg="white")
            label_servicio.pack()

            # Permite seleccionar el servicio al hacer click izquierdo (<Button-1>)
            label_servicio.bind(
                "<Button-1>",
                lambda event, servicio_seleccionado=servicio: calcular_precio(paciente, servicio_seleccionado))

            # Resalta el label donde se pone el cursor
            label_servicio.bind("<Enter>", lambda event: event.widget.config(bg="yellow"))
            label_servicio.bind("<Leave>", lambda event: event.widget.config(bg="white"))

    def buscar_paciente():
        cedula = ingreso_cedula.get()
        paciente = hospital.buscar_paciente(int(cedula))

        # Continua si el paciente esta registrado en el hospital
        if paciente is not None:
            obtener_servicios_sin_pagar(paciente)
        else:
            respuesta = tk.messagebox.askyesno("Error", "No existe un paciente registrado con esta cedula. "
                                                        "¿Desea intentar de nuevo?")
            if not respuesta:
                # Se importa aca para evitar una referencia circular
                from Python.src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)

    imprimir_titulo(frame)

    # Pide la cedula del paciente
    label_ingreso_cedula = tk.Label(frame, text="Ingrese la cédula del paciente:", bg="white")
    label_ingreso_cedula.pack()

    ingreso_cedula = tk.Entry(frame)
    ingreso_cedula.pack()

    boton_buscar_paciente = tk.Button(frame, text="Buscar", command=buscar_paciente)
    boton_buscar_paciente.pack()
