import tkinter as tk
from tkinter import ttk, messagebox
def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="VACUNACIÓN", bg="white")
    titulo.pack()


def vacunacion(hospital, frame):

    def agendamiento_de_la_vacuna(paciente):


        imprimir_titulo(frame)

        info_paciente = tk.Label(frame, text=f"{paciente.nombre} - CC: {paciente.cedula}", bg="white")
        info_paciente.pack()

        frame1 = tk.Frame(frame)
        frame1.pack()

        tipo_vacuna = tk.Label(frame1, text="Seleccione el tipo de vacuna:", bg="white")
        tipo_vacuna.grid(row=0, column=0, padx=10, pady=10, sticky="w")
        valor_defecto1 = tk.StringVar
        combo_tipo_vacuna = ttk.Combobox(frame1, values=["Obligatoria", "No obligatoria"],
                                       textvariable=valor_defecto1, state="readonly")
        #combo_tipo_cita.bind("<<ComboboxSelected>>", habilitar_elegir_doctor)
        combo_tipo_vacuna.grid(row=0, column=1, padx=10, pady=10, sticky="w")

        elegir_vacuna = tk.Label(frame1, text="Seleccione la vacuna de su preferencia:", bg="white")
        elegir_vacuna.grid(row=1, column=0, padx=10, pady=10, sticky="w")
        valor_defecto2 = tk.StringVar
        combo_elegir_vacuna = ttk.Combobox(frame1, textvariable=valor_defecto2, state="disabled")
        #combo_elegir_vacuna.bind("<<ComboboxSelected>>", habilitar_elegir_cita)
        combo_elegir_vacuna.grid(row=1, column=1, padx=10, pady=10, sticky="w")

    def buscar_paciente():
        cedula = ingreso_cedula.get()
        paciente = hospital.buscar_paciente(int(cedula))

        # Continua si el paciente esta registrado en el hospital
        if paciente is not None:
            agendamiento_de_la_vacuna(paciente)
        else:
            respuesta = tk.messagebox.askyesno("Error", "No existe un paciente registrado con esta cedula. "
                                                        "¿Desea intentar de nuevo?")
            if not respuesta:
                # Se importa aca para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)

    imprimir_titulo(frame)

    # Pide la cedula del paciente
    label_ingreso_cedula = tk.Label(frame, text="Ingrese la cédula del paciente:", bg="white")
    label_ingreso_cedula.pack()

    ingreso_cedula = tk.Entry(frame)
    ingreso_cedula.pack()

    boton_buscar_paciente = tk.Button(frame, text="Buscar", command=buscar_paciente)
    boton_buscar_paciente.pack()

    # Funcionalidad para regresar a la ventana principal

    # Se importa aca para evitar una referencia circular
    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",
                               command=lambda: implementacion_default(frame))
    boton_regresar.pack()


