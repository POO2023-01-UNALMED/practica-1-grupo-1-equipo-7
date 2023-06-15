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


    def mostrar_historial_vacunas(paciente):
        imprimir_titulo(frame)
        info_historial = tk.Label(frame, text=f"Historial de vacunas de {paciente.nombre} - CC: {paciente.cedula}",
                                  bg="white", font=("Helvetica", 12))
        info_historial.pack(pady=10)

        frame_citas = tk.Frame(frame)
        frame_citas.pack(fill=tk.BOTH, expand=True)

        canvas = tk.Canvas(frame_citas, bg="white")
        canvas.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)

        scrollbar = tk.Scrollbar(frame_citas, orient=tk.VERTICAL, command=canvas.yview)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)

        canvas.configure(yscrollcommand=scrollbar.set)

        inner_frame = tk.Frame(canvas, bg="white")
        inner_frame_id = canvas.create_window((0, 0), window=inner_frame, anchor=tk.NW)

        def configure_canvas(event):
            canvas.configure(scrollregion=canvas.bbox("all"))

        inner_frame.bind("<Configure>", configure_canvas)

        for CitaVacuna in paciente.historia_clinica.historial_vacunas:
            frame_vacuna = tk.Frame(inner_frame, bg="white")

            label_tipo_vacuna = tk.Label(frame_vacuna, text="Vacuna: ", bg="white", font=("Helvetica", 10, "bold"))
            label_tipo_vacuna.grid(row=0, column=0, padx=10, pady=5, sticky="w")
            label_nombre_vacuna = tk.Label(frame_vacuna, text=CitaVacuna.vacuna.nombre, bg="white")
            label_nombre_vacuna.grid(row=0, column=1, padx=10, pady=5, sticky="w")

            frame_vacuna.pack(padx=10, pady=10)

        def resize_canvas(event):
            canvas.itemconfig(inner_frame_id, width=canvas.winfo_width())

        canvas.bind("<Configure>", resize_canvas)

        def on_canvas_scroll(event):
            canvas.yview_scroll(int(-1 * (event.delta / 120)), "units")

        canvas.bind_all("<MouseWheel>", on_canvas_scroll)
        canvas.bind_all("<Button-4>", on_canvas_scroll)
        canvas.bind_all("<Button-5>", on_canvas_scroll)

        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(inner_frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()


    def agendamiento_de_la_vacuna(paciente):

        def confirmar_cita():
            eleccion = combo_elegir_cita.get()
            if eleccion:
                respuesta = tk.messagebox.askyesno("Confirmar cita de vacuna", "¿Estas seguro de agendar esta cita?")
                if respuesta:
                    for vacuna in paciente.buscar_vacuna_por_eps(combo_tipo_vacuna.get(), hospital):
                        if vacuna.nombre == combo_elegir_vacuna.get():
                            cita_agendada = vacuna.actualizar_agenda(paciente,combo_elegir_cita.current()+1,vacuna.mostrar_agenda_disponible())
                            paciente.actualizar_historial_vacunas(cita_agendada)
                    messagebox.showinfo("Vacuna agendada", "La cita se ha agendado exitosamente")
                    mostrar_historial_vacunas(paciente)
                else:
                    messagebox.showinfo("Vacuna cancelada", "La cita ha sido cancelada")
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)

            else:
                messagebox.showerror("Informacion incompleta", "Necesita completar todos los espacios para agendar la vacuna")

        def lista_citas():
            lista_citas = []
            for vacuna in paciente.buscar_vacuna_por_eps(combo_tipo_vacuna.get(), hospital):
                if vacuna.nombre == combo_elegir_vacuna.get():
                    for cita in vacuna.mostrar_agenda_disponible():
                        lista_citas.append(cita.fecha)
            return lista_citas
        def habilitar_elegir_cita(event):
            eleccion = combo_elegir_vacuna.get()
            combo_elegir_cita.set("")
            if eleccion:
                combo_elegir_cita['state'] = 'readonly'
                combo_elegir_cita['values'] = lista_citas()
            else:
                combo_elegir_cita['state'] = 'disabled'
        def lista_vacunas():
            lista_vacunas=[]
            for vacuna in paciente.buscar_vacuna_por_eps(combo_tipo_vacuna.get(), hospital):
                lista_vacunas.append(vacuna.nombre)
            return lista_vacunas


        def habilitar_elegir_vacuna(event):
            eleccion = combo_tipo_vacuna.get()
            combo_elegir_vacuna.set("")
            combo_elegir_cita.set("")
            combo_elegir_cita['state'] = 'disabled'
            if eleccion:
                combo_elegir_vacuna['state'] = 'readonly'
                todas_vacunas=lista_vacunas()
                #Desabilitar las vacunas que ya se puso:
                vacunas_puestas=[]
                for citaVacuna in paciente.historia_clinica.historial_vacunas:
                    vacunas_puestas.append(citaVacuna.vacuna.nombre)
                #Se filtran las vacunas que ya se ha puesto el usuario anteriormente
                opciones_disponibles = list(filter(lambda vacuna: vacuna not in vacunas_puestas,
                                                   todas_vacunas))
                combo_elegir_vacuna['values'] = opciones_disponibles
            else:
                combo_elegir_vacuna['state'] = 'disabled'


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
        combo_tipo_vacuna.bind("<<ComboboxSelected>>", habilitar_elegir_vacuna)
        combo_tipo_vacuna.grid(row=0, column=1, padx=10, pady=10, sticky="w")

        elegir_vacuna = tk.Label(frame1, text="Seleccione la vacuna de su preferencia:", bg="white")
        elegir_vacuna.grid(row=1, column=0, padx=10, pady=10, sticky="w")
        valor_defecto2 = tk.StringVar
        combo_elegir_vacuna = ttk.Combobox(frame1, textvariable=valor_defecto2, state="disabled")
        combo_elegir_vacuna.bind("<<ComboboxSelected>>", habilitar_elegir_cita)
        combo_elegir_vacuna.grid(row=1, column=1, padx=10, pady=10, sticky="w")

        elegir_cita = tk.Label(frame1, text="Seleccione una fecha para su cita en enfermería:", bg="white")
        elegir_cita.grid(row=2, column=0, padx=10, pady=10, sticky="w")
        valor_defecto3 = tk.StringVar
        combo_elegir_cita = ttk.Combobox(frame1, textvariable=valor_defecto3, state="disabled")
        combo_elegir_cita.grid(row=2, column=1, padx=10, pady=10, sticky="w")

        boton_aceptar = tk.Button(frame, text="Aceptar", command=confirmar_cita)
        boton_aceptar.pack()

        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()

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


