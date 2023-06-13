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

    def mostrar_doctores_por_tipo(paciente):

        def lista_doctores():
            lista_doctores = []
            for doctor in paciente.buscar_doctor_por_eps(combo_tipo_cita.get(), hospital):
                lista_doctores.append(doctor.nombre)
            return lista_doctores

        def habilitar_elegir_doctor(event):
            eleccion = combo_tipo_cita.get()
            combo_elegir_doctor.set("")
            if eleccion:
                combo_elegir_doctor['state'] = 'readonly'
                combo_elegir_doctor['values'] = lista_doctores()
            else:
                combo_elegir_doctor['state'] = 'disabled'

        imprimir_titulo(frame)

        info_paciente = tk.Label(frame, text=f"{paciente.nombre} - CC: {paciente.cedula}", bg="white")
        info_paciente.pack()

        frame1 = tk.Frame(frame)
        frame1.pack()

        tipo_cita = tk.Label(frame1, text="Tipo de cita", bg="white")
        tipo_cita.grid(row=0,column=0,padx=10,pady=10,sticky="w")
        valor_defecto1 = tk.StringVar
        combo_tipo_cita = ttk.Combobox(frame1, values=["General", "Odontologia", "Oftalmologia"], textvariable=valor_defecto1, state="readonly")
        combo_tipo_cita.bind("<<ComboboxSelected>>", habilitar_elegir_doctor)
        combo_tipo_cita.grid(row=0,column=1,padx=10,pady=10,sticky="w")

        elegir_doctor = tk.Label(frame1, text="Doctor", bg="white")
        elegir_doctor.grid(row=1, column=0, padx=10, pady=10, sticky="w")
        valor_defecto2 = tk.StringVar
        combo_elegir_doctor = ttk.Combobox(frame1,textvariable=valor_defecto2,state="disabled")
        combo_elegir_doctor.grid(row=1,column=1,padx=10,pady=10,sticky="w")

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
    boton_regresar.place(relx=0.5, rely=0.6, anchor=tk.CENTER)
