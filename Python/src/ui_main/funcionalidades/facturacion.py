import tkinter as tk
from tkinter import messagebox

from src.gestor_aplicacion.servicios.servicio import Servicio
from src.manejo_errores.error_aplicacion import DatosFalsos, TipoIncorrecto, CampoVacio, SinServicioSeleccionado
from src.ui_main.gestion.field_frame import FieldFrame


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Facturación", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)


def facturacion(hospital, frame):
    def validar_lista_servicios(paciente, lista_servicios, opcion):
        if len(lista_servicios) == 0:
            try:
                raise SinServicioSeleccionado()
            except SinServicioSeleccionado as e:
                e.enviar_mensaje()
        else:
            if opcion == 1:
                calcular_precio(paciente, lista_servicios)
            elif opcion == 2:
                confirmar_pago(paciente, lista_servicios)

    def confirmar_pago(paciente, servicios):
        respuesta = tk.messagebox.askyesno("Confirmar pago", "¿Desea realizar el pago?")
        if respuesta:
            for servicio in servicios:
                servicio.confirmar_pago(paciente, servicio.id_servicio)
            messagebox.showinfo("Pago realizado", "El pago se ha realizado exitosamente")
        else:
            messagebox.showinfo("Pago cancelado", "El pago ha sido cancelado")

        implementacion_default(frame)

    def calcular_precio(paciente, servicios):
        def calcular_precio_servicio(s, ls):
            # Permite editar la variable desde la funcion sin pasarla como argumento
            nonlocal precio_final

            if s in servicios:
                servicios.remove(s)
                ls.config(bg="white")
                precio_final -= paciente.calcular_precio(s)
            else:
                servicios.append(s)
                ls.config(bg="#d2d5f9")
                precio_final += paciente.calcular_precio(s)

            label_precio_final.config(text=f"Precio total: ${precio_final:,}")

        imprimir_titulo(frame)

        precio_final = 0

        label_servicios = tk.Label(frame, text="Confirme los servicios que va a pagar:", bg="white",
                                   font=("Helvetica", 10, "bold"))
        label_servicios.pack(pady=10)

        servicios.sort(key=lambda s: s.id_servicio)

        for servicio in servicios:
            precio_servicio = paciente.calcular_precio(servicio)
            precio_final += precio_servicio
            label_servicio = tk.Label(frame, text=f"{servicio.descripcion_servicio()} - "
                                                  f"Precio: ${precio_servicio:,}", bg="white")
            label_servicio.pack()
            label_servicio.config(bg="#d2d5f9")

            # Permite seleccionar el servicio al hacer click izquierdo (<Button-1>)
            label_servicio.bind(
                "<Button-1>",
                lambda event, s=servicio, ls=label_servicio: calcular_precio_servicio(s, ls))

            # Resalta el label donde se pone el cursor
            label_servicio.bind("<Enter>",
                                lambda event: event.widget.config(highlightthickness=2, highlightbackground="black"))
            label_servicio.bind("<Leave>", lambda event: event.widget.config(highlightthickness=0))

            # Seleccionado por defecto

        label_precio_final = tk.Label(frame, text=f"Precio total: ${precio_final:,}", bg="white")
        label_precio_final.pack(pady=10)

        boton_pagar = tk.Button(frame, text="Realizar pago",
                                command=lambda: validar_lista_servicios(paciente, servicios, 2))
        boton_pagar.pack(pady=10)

        boton_regresar = tk.Button(frame, text="Regresar",
                                   command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    def obtener_servicios_sin_pagar(paciente):
        def seleccionar_servicio(s, ls):
            if s in servicios_seleccionados:
                servicios_seleccionados.remove(s)
                ls.config(bg="white")
            else:
                servicios_seleccionados.append(s)
                ls.config(bg="#d2d5f9")

        lista_servicios_sin_pagar = Servicio.obtener_servicios_sin_pagar(paciente)

        if len(lista_servicios_sin_pagar) == 0:
            tk.messagebox.showinfo("Sin servicios pendientes", "Usted no tiene ningún servicio pendiente de pago")
            implementacion_default(frame)
            return

        imprimir_titulo(frame)

        info_paciente = tk.Label(frame, text=f"{paciente.nombre} - CC: {paciente.cedula}", bg="white",
                                 font=("Helvetica", 12))
        info_paciente.pack(pady=10)

        label_servicios = tk.Label(frame, text="Lista de servicios sin pagar:", bg="white",
                                   font=("Helvetica", 10, "bold"))
        label_servicios.pack(pady=10)

        servicios_seleccionados = []

        # Imprime cada servicio en un label
        for servicio in lista_servicios_sin_pagar:
            label_servicio = tk.Label(frame, text=servicio.descripcion_servicio(), bg="white")
            label_servicio.pack()

            # Permite seleccionar el servicio al hacer click izquierdo (<Button-1>)
            label_servicio.bind(
                "<Button-1>",
                lambda event, s=servicio, ls=label_servicio: seleccionar_servicio(s, ls))

            # Resalta el label donde se pone el cursor
            label_servicio.bind("<Enter>",
                                lambda event: event.widget.config(highlightthickness=2, highlightbackground="black"))
            label_servicio.bind("<Leave>", lambda event: event.widget.config(highlightthickness=0))

        boton_precio = tk.Button(frame, text="Continuar",
                                 command=lambda: validar_lista_servicios(paciente, servicios_seleccionados, 1))
        boton_precio.pack(pady=10)

        boton_regresar = tk.Button(frame, text="Regresar",
                                   command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    def buscar_paciente():
        cedula = ingreso_cedula.getValue(1)

        if len(cedula) != 0:
            try:
                paciente = hospital.buscar_paciente(int(cedula))
                obtener_servicios_sin_pagar(paciente)
            except DatosFalsos as e:
                e.enviar_mensaje()
            except (ValueError, TypeError):
                TipoIncorrecto().enviar_mensaje()
        else:
            try:
                raise CampoVacio()
            except CampoVacio as e:
                e.enviar_mensaje()

    imprimir_titulo(frame)

    # Pide la cedula del paciente
    label_ingreso_cedula = tk.Label(frame, text="Ingrese la cédula del paciente:", bg="white",
                                    font=("Helvetica", 10, "bold"))
    label_ingreso_cedula.pack()

    criterios = ["Cédula:"]
    ingreso_cedula = FieldFrame(frame, "", criterios, "", None, None)
    ingreso_cedula.pack()

    boton_buscar_paciente = tk.Button(frame, text="Buscar", command=buscar_paciente)
    boton_buscar_paciente.pack(pady=10)

    # Funcionalidad para regresar a la ventana principal
    # Se importa aca para evitar una referencia circular
    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",
                               command=lambda: implementacion_default(frame))
    boton_regresar.pack()
