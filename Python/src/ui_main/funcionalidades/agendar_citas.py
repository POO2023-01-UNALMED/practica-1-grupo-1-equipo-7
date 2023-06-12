import tkinter as tk
from tkinter import ttk


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Agendar citas", bg="white")
    titulo.pack()
def agendar_citas(hospital, frame):

    def mostrar_lista_doctores(paciente, especialidad):
        doctores = paciente.buscar_doctor_por_eps(especialidad, hospital)

        if len(doctores) == 0:
            tk.messagebox.showinfo("Sin doctores disponibles", "Usted no tiene ningún servicio pendiente de pago")
            return

    def mostrar_doctores_por_tipo(paciente):
        imprimir_titulo(frame)

        info_paciente = tk.Label(frame, text=f"{paciente.nombre} - CC: {paciente.cedula}", bg="white")
        info_paciente.pack()

        frame1 = tk.Frame(frame)
        frame1.pack()

        tipo_cita = tk.Label(frame1, text="Tipo de cita", bg="white")
        tipo_cita.grid(row=0,column=0,padx=10,pady=10,sticky="w")
        valor_defecto = tk.StringVar(value="Tipo de cita")
        combo_tipo_cita = ttk.Combobox(frame1, values=["General", "Odontologia", "Oftalmologia"], textvariable=valor_defecto, state="readonly")
        combo_tipo_cita.bind("<<ComboboxSelected>>",lambda event: mostrar_lista_doctores(paciente, combo_tipo_cita.get()))
        combo_tipo_cita.grid(row=0,column=1,padx=10,pady=10,sticky="w")

    def buscar_paciente():
        cedula = ingreso_cedula.get()
        paciente = hospital.buscar_paciente(int(cedula))

        # Continua si el paciente esta registrado en el hospital
        if paciente is not None:
            mostrar_doctores_por_tipo(paciente)
        else:
            respuesta = tk.messagebox.askyesno("Error", "No existe un paciente registrado con esta cedula. "
                                                        "¿Desea intentar de nuevo?")
            if not respuesta:
                # Se importa aca para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)

    # Funcionalidad para regresar a la ventana principal

    # Se importa aca para evitar una referencia circular
    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",
                               command=lambda: implementacion_default(frame))
    boton_regresar.pack()
    boton_regresar.place(relx=0.5, rely=0.6, anchor=tk.CENTER)

    imprimir_titulo(frame)

    # Pide la cedula del paciente
    label_ingreso_cedula = tk.Label(frame, text="Ingrese la cédula del paciente:", bg="white")
    label_ingreso_cedula.pack()

    ingreso_cedula = tk.Entry(frame)
    ingreso_cedula.pack()

    boton_buscar_paciente = tk.Button(frame, text="Buscar", command=buscar_paciente)
    boton_buscar_paciente.pack()
