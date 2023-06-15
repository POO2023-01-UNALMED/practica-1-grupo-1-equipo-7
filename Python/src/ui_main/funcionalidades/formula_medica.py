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
        def habilitar_doctor(event):
            global valor_defecto1
            indice_seleccionado = combo_enfermedades.current()
            objeto_seleccionado = paciente.historia_clinica.enfermedades[indice_seleccionado]
            valor_defecto1 = objeto_seleccionado.nombre + objeto_seleccionado.tipologia  # Acceder al nombre del objeto seleccionado
            eleccion = combo_enfermedades.get()
            combo_elegir_doctor.set("")
            if eleccion:
                combo_elegir_doctor['state'] = 'readonly'
                combo_elegir_doctor['values'] = paciente.historia_clinica.buscar_cita(objeto_seleccionado.especialidad,
                                                                                      hospital)
            else:
                combo_elegir_doctor['state'] = 'disabled'

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
        combo_enfermedades = ttk.Combobox(frame_datos, values=paciente.historia_clinica.enfermedades,
                                          textvariable=valor_defecto1, state="readonly")
        combo_enfermedades.bind("<<ComboboxSelected>>", habilitar_doctor)
        combo_enfermedades.grid(row=0, column=1, padx=10, pady=10, sticky="w")

        # Label con instruccion de seleccionar doctor que formulara y combobox de doctores
        elegir_doctor = tk.Label(frame_datos, text="Seleccione el doctor que hará la formula:", bg="white")
        elegir_doctor.grid(row=1, column=0, padx=10, pady=10, sticky="w")
        valor_defecto2 = tk.StringVar
        combo_elegir_doctor = ttk.Combobox(frame_datos, textvariable=valor_defecto2, state="disabled")
        combo_elegir_doctor.grid(row=1, column=1, padx=10, pady=10, sticky="w")
        indice_enfermedad = combo_enfermedades.current()
        enf_objeto = paciente.historia_clinica.enfermedades[indice_enfermedad]
        boton_seleccionar = tk.Button(frame, text="Seleccionar medicamentos", command=seleccion_medicamentos)
        boton_seleccionar.pack()

    def seleccion_medicamentos():
        # Crear el nuevo frame para la selección de medicamentos
        imprimir_titulo(frame)
        frame_seleccion = tk.Frame(frame, bg="white")
        frame_seleccion.pack()
        medicamentos_disponibles = hospital.meds_disponibles()

        # Título de la selección de medicamentos
        titulo_seleccion = tk.Label(frame_seleccion, text="Seleccione los medicamentos:", bg="white")
        titulo_seleccion.pack()

        # Listbox para la selección de medicamentos
        listbox_medicamentos = tk.Listbox(frame_seleccion, selectmode=tk.MULTIPLE, width=50)
        listbox_medicamentos.pack()

        # Agregar los medicamentos disponibles al listbox
        for med in medicamentos_disponibles:
            listbox_medicamentos.insert(tk.END, med)

        # Botón para finalizar la selección
        boton_finalizar = tk.Button(frame_seleccion, text="Finalizar selección", command=lambda: obtener_seleccion)
        boton_finalizar.pack()

        def obtener_seleccion():
            # Obtener los índices seleccionados en el Listbox
            indices_seleccionados = listbox_medicamentos.curselection()

            # Obtener los medicamentos seleccionados utilizando los índices
            medicamentos_seleccionados = [medicamentos_disponibles[indice] for indice in indices_seleccionados]


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
