import tkinter as tk
from tkinter import ttk, messagebox
from src.gestor_aplicacion.servicios.formula import Formula
from src.manejo_errores.error_aplicacion import *
from src.ui_main.gestion.field_frame import FieldFrame


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Formular Medicamentos", bg="white", font=("Helvetica", 10, "bold"))
    titulo.pack()


def formula_medica(hospital, frame):
    def doctor_formula(paciente):
        def habilitar_doctor(event):
            global valor_defecto1
            indice_seleccionado = combo_enfermedades.current()
            objeto_seleccionado = paciente.historia_clinica.enfermedades[indice_seleccionado]
            valor_defecto1 = objeto_seleccionado.nombre + objeto_seleccionado.tipologia  # Acceder al nombre del objeto seleccionado
            eleccion = combo_enfermedades.get()
            lista_doc_enf = paciente.historia_clinica.buscar_cita(objeto_seleccionado.especialidad, hospital)
            combo_elegir_doctor.set("")
            if len(lista_doc_enf) != 0:
                if eleccion:
                    combo_elegir_doctor['state'] = 'readonly'
                    combo_elegir_doctor['values'] = lista_doc_enf
                else:
                    combo_elegir_doctor['state'] = 'disabled'
                indice_enfermedad = combo_enfermedades.current()
                enf_objeto = paciente.historia_clinica.enfermedades[indice_enfermedad]
                if len(paciente.historia_clinica.buscar_cita(enf_objeto.especialidad, hospital)) != 0:
                    formula_paciente = Formula(paciente)
                    for widget in frame.winfo_children():
                        if isinstance(widget, tk.Button) and widget.cget("text") == "Seleccionar medicamentos":
                            widget.destroy()

                        # Crear y empaquetar el nuevo botón
                    boton_seleccionar = tk.Button(frame, text="Seleccionar medicamentos",
                                                  command=lambda: seleccion_medicamentos(enf_objeto,
                                                                                         formula_paciente,
                                                                                         combo_elegir_doctor.get()))
                    boton_seleccionar.pack()
                else:
                    try:
                        raise SinCitaPrevia()
                    except SinCitaPrevia as e:
                        e.enviar_mensaje()
                        # Se importa aca para evitar una referencia circular
                        from src.ui_main.ventana_principal import implementacion_default
                        implementacion_default(frame)
            else:
                try:
                    raise SinCitaPrevia()
                except SinCitaPrevia as e:
                    e.enviar_mensaje()
                    combo_elegir_doctor['state'] = 'disabled'
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)


        imprimir_titulo(frame)  # Imprime el titulo de "Formular medicamentos"
        # Label que muestra la informacion del paciente en la parte superior
        info_paciente = tk.Label(frame, text=f"{paciente.nombre} - CC: {paciente.cedula}", bg="white", font=("Helvetica", 10, "bold"))
        info_paciente.pack()

        # Frame que va a contener todos los widgets de la funcionalidad
        frame_datos = tk.Frame(frame)
        frame_datos.pack()

        # Label con instruccion de seleccionar enfermedad a tratar y combobox de enfermedades
        enfermedad_tratar = tk.Label(frame_datos, text="Seleccione la enfermedad a tratar: ", bg="white", font=("Helvetica", 10, "bold"))
        enfermedad_tratar.grid(row=0, column=0, padx=10, pady=10, sticky="w")
        valor_defecto1 = tk.StringVar
        combo_enfermedades = ttk.Combobox(frame_datos, values=paciente.historia_clinica.enfermedades,
                                          textvariable=valor_defecto1, state="readonly")
        combo_enfermedades.bind("<<ComboboxSelected>>", habilitar_doctor)
        combo_enfermedades.grid(row=0, column=1, padx=10, pady=10, sticky="w")

        # Label con instruccion de seleccionar doctor que formulara y combobox de doctores
        elegir_doctor = tk.Label(frame_datos, text="Seleccione el doctor que hará la formula:", bg="white", font=("Helvetica", 10, "bold"))
        elegir_doctor.grid(row=1, column=0, padx=10, pady=10, sticky="w")
        valor_defecto2 = tk.StringVar
        combo_elegir_doctor = ttk.Combobox(frame_datos, textvariable=valor_defecto2, state="disabled",width=30)
        combo_elegir_doctor.grid(row=1, column=1, padx=10, pady=10, sticky="w")

        def seleccion_medicamentos(enfermedad, formula, doctor):
            if doctor != "":
                # Crear el nuevo frame para la selección de medicamentos
                imprimir_titulo(frame)
                frame_seleccion = tk.Frame(frame, bg="white")
                frame_seleccion.pack(fill="both", expand=True)
                medicamentos_disponibles = paciente.med_enfermedad(enfermedad, hospital)
                if len(medicamentos_disponibles) != 0:
                    # Título de la selección de medicamentos
                    titulo_seleccion = tk.Label(frame_seleccion, text="Seleccione los medicamentos", bg="white", font=("Helvetica", 10, "bold"))
                    titulo_seleccion.pack()

                    # Listbox para la selección de medicamentos
                    listbox_medicamentos = tk.Listbox(frame_seleccion, selectmode=tk.MULTIPLE, bg="white")
                    listbox_medicamentos.pack(fill="both", expand=True)

                    # Agregar los medicamentos disponibles al listbox
                    for med in medicamentos_disponibles:
                        listbox_medicamentos.insert(tk.END, med)

                    # Botón para finalizar la selección
                    boton_finalizar = tk.Button(frame_seleccion, text="Finalizar selección",
                                                command=lambda: obtener_seleccion(listbox_medicamentos.curselection(),
                                                                                  medicamentos_disponibles, formula, doctor))
                    boton_finalizar.pack()
                else:
                    try:
                        raise SinMedicamentos()
                    except SinMedicamentos as e:
                        SinMedicamentos().enviar_mensaje()
                        # Se importa aca para evitar una referencia circular
                        from src.ui_main.ventana_principal import implementacion_default
                        implementacion_default(frame)
            else:
                try:
                    raise ValueError
                except ValueError:
                    IngresoErroneo("No elegiste doctor").enviar_mensaje()
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)

        def obtener_seleccion(seleccion, disponibles, formula, doctor):
            imprimir_titulo(frame)
            # Obtener los índices seleccionados en el Listbox
            indices_seleccionados = seleccion

            # Obtener los medicamentos seleccionados utilizando los índices
            medicamentos_seleccionados = [disponibles[indice] for indice in indices_seleccionados]
            if len(medicamentos_seleccionados) != 0:
                frame_meds_seleccionados = tk.Frame(frame)
                frame_meds_seleccionados.pack(fill="both", expand=True)
                label_meds_seleccionados = tk.Label(frame_meds_seleccionados, text="Estos son sus medicamentos", bg="white", font=("Helvetica", 10, "bold"))
                label_meds_seleccionados.pack()
                formula.lista_meds = medicamentos_seleccionados
                for med in medicamentos_seleccionados:
                    string_info = med.mostrar_info()
                    label_med = tk.Label(frame_meds_seleccionados, text=string_info, bg="white")
                    label_med.pack()
                boton_generar_formula = tk.Button(frame_meds_seleccionados, text="Generar Formula",
                                                  command=lambda: generar_formula(formula, doctor))
                boton_generar_formula.pack()
            else:
                try:
                    raise ValueError
                except ValueError:
                    IngresoErroneo("No elegiste medicamentos").enviar_mensaje()
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)

            def generar_formula(formula_paciente, doctor):
                respuesta = tk.messagebox.askyesno("Confirmar Formula", "¿Estas seguro de generar esta formula?")
                if respuesta:
                    for med in formula_paciente.lista_meds:
                        med.eliminar_cantidad()
                    for doc in hospital.lista_doctores:
                        if doc.nombre == doctor.split(" ")[0]:
                            formula_paciente.doctor = doc
                            paciente.historia_clinica.lista_formulas.append(formula_paciente)
                            messagebox.showinfo("Formula Generada", "La formula se ha generado exitosamente")
                            mostrar_historial(paciente)
                else:
                    messagebox.showinfo("Formula cancelada", "La formula ha sido cancelada")
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)

    def mostrar_historial(paciente):
        imprimir_titulo(frame)
        frame_formulas = tk.Frame(frame)
        frame_formulas.pack(fill="both", expand=True)
        label = tk.Label(frame_formulas, text="Historial de formulas", bg="white", font=("Helvetica", 10, "bold"))
        label.pack(fill="both")
        for formula in paciente.historia_clinica.lista_formulas:
            formula_label = tk.Label(frame_formulas, text=formula.descripcion_servicio())
            formula_label.pack(fill="both", expand=True)
        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame_formulas, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    def buscar_paciente():
        cedula = fp.getValue(1)
        if len(cedula) != 0:
            try:
                paciente = hospital.buscar_paciente(int(cedula))
                doctor_formula(paciente)
            except DatosFalsos as e:
                e.enviar_mensaje()
            except ValueError:
                TipoIncorrecto().enviar_mensaje()
        else:
            try:
                raise CampoVacio()
            except CampoVacio as e:
                e.enviar_mensaje()

    imprimir_titulo(frame)

    # Pide la cedula del paciente
    label_ingreso_cedula = tk.Label(frame, text="Ingrese la cédula del paciente:", bg="white", font=("Helvetica", 10, "bold"))
    label_ingreso_cedula.pack()

    criterios = ["Cédula"]
    fp = FieldFrame(frame, "", criterios, "", None, None)
    fp.pack()

    boton_buscar_paciente = tk.Button(frame, text="Buscar", command=buscar_paciente)
    boton_buscar_paciente.pack()
