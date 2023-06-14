import tkinter as tk
from tkinter import ttk, messagebox


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Formular Medicamentos", bg="white")
    titulo.pack()


def formula_medica(hospital, frame):
    def doctor_formula(paciente):

        def habilitar_elegir_enfermedad(event):
            eleccion = combo_enfermedades.get()
            combo_enfermedades.set("")
            if eleccion:
                combo_enfermedades['state'] = 'readonly'
                combo_enfermedades['values'] = paciente.historia_clinica.enfermedades
            else:
                combo_enfermedades['state'] = 'disabled'

        imprimir_titulo(frame)  # Imprime el titulo de "Formular medicamentos"
        # Label que muestra la informacion del paciente en la parte superior
        info_paciente = tk.Label(frame, text=f"{paciente.nombre} - CC: {paciente.cedula}", bg="white")
        info_paciente.pack()

        # Frame que va a contener todos los widgets de la funcionalidad
        frame_datos = tk.Frame(frame)
        frame_datos.pack()

        # Label con instruccion de seleccionar enfermedad a tratar y combobox de enfermedades
        enfermedad_tratar = tk.Label(frame_datos, text="Seleccione la enfermedad a tratar: ", bg="white")
        enfermedad_tratar.grid(row=0, column=0, padx=10, pady=10, sticky="w")
        valor_defecto1 = tk.StringVar
        combo_enfermedades = ttk.Combobox(frame_datos, values=paciente.historia_clinica.enfermedades, textvariable=valor_defecto1, state="disabled")
        combo_enfermedades.bind("<<ComboboxSelected>>", habilitar_elegir_enfermedad)
        combo_enfermedades.grid(row=0, column=1, padx=10, pady=10, sticky="w")

        # Label con instruccion de seleccionar doctor que formulara y combobox de doctores
        elegir_doctor = tk.Label(frame_datos, text="Seleccione el doctor que hará la formula:", bg="white")
        elegir_doctor.grid(row=1, column=0, padx=10, pady=10, sticky="w")
        valor_defecto2 = tk.StringVar
        combo_elegir_doctor = ttk.Combobox(frame_datos, textvariable=valor_defecto2, state="disabled")
        combo_elegir_doctor.grid(row=1, column=1, padx=10, pady=10, sticky="w")
        #seleccion_medicamentos(combo_elegir_doctor.get(), combo_enfermedades.get(), paciente)
    def seleccion_medicamentos(doctor, enfermedad, paciente):

        # Imprime todos los medicamentos disponibles en un frame
        frame_medicamentos = tk.Frame(frame, bg="white")
        frame_medicamentos.pack()

        imprimir_titulo(frame)
        label_seleccion_meds = tk.Label(frame_medicamentos, text=f"Hola {doctor}\nSeleccione los medicamentos que desea formular para tratar {enfermedad}", bg="white")
        label_seleccion_meds.pack()
        # Lista de medicamentos disponibles
        medicamentos_disponibles = paciente.med_enfermedad()
        # ListBox para seleccionar varios medicamentos
        listbox_medicamentos = tk.Listbox(frame_medicamentos, width=200, selectmode=tk.MULTIPLE)
        listbox_medicamentos.pack(fill="both", expand=True)
        # Agregar medicamentos al listbox
        for med in medicamentos_disponibles:
            listbox_medicamentos.insert(tk.END, med)
        seleccionados(listbox_medicamentos.curselection(), medicamentos_disponibles, paciente, doctor)
    def seleccionados(indices, disponibles, paciente, doctor):
        imprimir_titulo(frame)
        lab = tk.Label(frame, text="Estos son los medicamentos del paciente")
        lab.pack()
        med_seleccionados = [disponibles[idx] for idx in indices]
        for med in med_seleccionados:
            label_med = tk.Label(frame, text=med)
            label_med.pack()



    def buscar_paciente():
        cedula = ingreso_cedula.get()
        paciente = hospital.buscar_paciente(int(cedula))

        # Continua si el paciente esta registrado en el hospital
        if paciente is not None:
            doctor_formula(paciente)
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

