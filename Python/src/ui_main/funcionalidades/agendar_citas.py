import tkinter as tk
from tkinter import ttk, messagebox


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Agendar citas", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)
def agendar_citas(hospital, frame):
    def mostrar_historial_citas(paciente):
        imprimir_titulo(frame)
        info_historial = tk.Label(frame, text=f"Historial de citas de {paciente.nombre} - CC: {paciente.cedula}",
                                  bg="white",font=("Helvetica", 12))
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

        for cita in paciente.historia_clinica.historial_citas:
            frame_cita = tk.Frame(inner_frame, bg="white")

            label_tipo_cita = tk.Label(frame_cita, text="Tipo de cita: ", bg="white", font=("Helvetica", 10, "bold"))
            label_tipo_cita.grid(row=0, column=0, padx=10, pady=5, sticky="w")
            label_especialidad_doctor = tk.Label(frame_cita, text=cita.doctor.especialidad, bg="white")
            label_especialidad_doctor.grid(row=0, column=1, padx=10, pady=5, sticky="w")

            label_doctor = tk.Label(frame_cita, text="Doctor: ", bg="white", font=("Helvetica", 10, "bold"))
            label_doctor.grid(row=1, column=0, padx=10, pady=5, sticky="w")
            label_nombre_doctor = tk.Label(frame_cita, text=cita.doctor.nombre, bg="white")
            label_nombre_doctor.grid(row=1, column=1, padx=10, pady=5, sticky="w")

            label_fecha = tk.Label(frame_cita, text="Fecha: ", bg="white", font=("Helvetica", 10, "bold"))
            label_fecha.grid(row=2, column=0, padx=10, pady=5, sticky="w")
            label_fecha_cita = tk.Label(frame_cita, text=cita.fecha, bg="white")
            label_fecha_cita.grid(row=2, column=1, padx=10, pady=5, sticky="w")

            frame_cita.pack(padx=10, pady=10)

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


    def agendamiento_de_la_cita(paciente):

        def confirmar_cita():
            eleccion = combo_elegir_cita.get()
            if eleccion:
                respuesta = tk.messagebox.askyesno("Confirmar cita", "¿Estas seguro de agendar esta cita?")
                if respuesta:
                    for doctor in paciente.buscar_doctor_por_eps(combo_tipo_cita.get(), hospital):
                        if doctor.nombre == combo_elegir_doctor.get():
                            cita_agendada = doctor.actualizar_agenda(paciente,combo_elegir_cita.current()+1,doctor.mostrar_agenda_disponible())
                            paciente.actualizar_historial_citas(cita_agendada)
                    messagebox.showinfo("Cita agendada", "La cita se ha agendado exitosamente")
                    mostrar_historial_citas(paciente)
                else:
                    messagebox.showinfo("Cita cancelada", "La cita ha sido cancelada")
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)

            else:
                messagebox.showerror("Informacion incompleta", "Necesita completar todos los espacios para agendar la cita")

        def lista_citas():
            lista_citas = []
            for doctor in paciente.buscar_doctor_por_eps(combo_tipo_cita.get(), hospital):
                if doctor.nombre == combo_elegir_doctor.get():
                    for cita in doctor.mostrar_agenda_disponible():
                        lista_citas.append(cita.fecha)
            return lista_citas

        def habilitar_elegir_cita(event):
            eleccion = combo_elegir_doctor.get()
            combo_elegir_cita.set("")
            if eleccion:
                combo_elegir_cita['state'] = 'readonly'
                combo_elegir_cita['values'] = lista_citas()
            else:
                combo_elegir_cita['state'] = 'disabled'

        def lista_doctores():
            lista_doctores = []
            for doctor in paciente.buscar_doctor_por_eps(combo_tipo_cita.get(), hospital):
                lista_doctores.append(doctor.nombre)
            return lista_doctores

        def habilitar_elegir_doctor(event):
            eleccion = combo_tipo_cita.get()
            combo_elegir_doctor.set("")
            combo_elegir_cita.set("")
            combo_elegir_cita['state'] = 'disabled'
            if eleccion:
                combo_elegir_doctor['state'] = 'readonly'
                combo_elegir_doctor['values'] = lista_doctores()
            else:
                combo_elegir_doctor['state'] = 'disabled'

        imprimir_titulo(frame)

        info_paciente = tk.Label(frame, text=f"{paciente.nombre} - CC: {paciente.cedula}", bg="white", font=("Helvetica", 12))
        info_paciente.pack(pady=10)

        frame1 = tk.Frame(frame, bg="white")
        frame1.pack()

        tipo_cita = tk.Label(frame1, text="Seleccione el tipo de cita:", bg="white", font=("Helvetica", 10, "bold"))
        tipo_cita.grid(row=0,column=0,padx=10,pady=10,sticky="w")
        valor_defecto1 = tk.StringVar
        combo_tipo_cita = ttk.Combobox(frame1, values=["General", "Odontologia", "Oftalmologia"], textvariable=valor_defecto1, state="readonly")
        combo_tipo_cita.bind("<<ComboboxSelected>>", habilitar_elegir_doctor)
        combo_tipo_cita.grid(row=0,column=1,padx=10,pady=10,sticky="w")

        elegir_doctor = tk.Label(frame1, text="Seleccione el doctor de su preferencia:", bg="white", font=("Helvetica", 10, "bold"))
        elegir_doctor.grid(row=1, column=0, padx=10, pady=10, sticky="w")
        valor_defecto2 = tk.StringVar
        combo_elegir_doctor = ttk.Combobox(frame1,textvariable=valor_defecto2,state="disabled")
        combo_elegir_doctor.bind("<<ComboboxSelected>>", habilitar_elegir_cita)
        combo_elegir_doctor.grid(row=1,column=1,padx=10,pady=10,sticky="w")

        elegir_cita = tk.Label(frame1, text="Seleccione una fecha para su cita:", bg="white", font=("Helvetica", 10, "bold"))
        elegir_cita.grid(row=2, column=0, padx=10, pady=10, sticky="w")
        valor_defecto3 = tk.StringVar
        combo_elegir_cita = ttk.Combobox(frame1,textvariable=valor_defecto3,state="disabled")
        combo_elegir_cita.grid(row=2,column=1,padx=10,pady=10,sticky="w")

        boton_aceptar = tk.Button(frame, text="Aceptar", command=confirmar_cita)
        boton_aceptar.pack(pady=5)

        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack(pady=5)




    def buscar_paciente():
        cedula = ingreso_cedula.get()
        paciente = hospital.buscar_paciente(int(cedula))

        # Continua si el paciente esta registrado en el hospital
        if paciente is not None:
            agendamiento_de_la_cita(paciente)
        else:
            respuesta = tk.messagebox.askyesno("Error", "No existe un paciente registrado con esta cedula. "
                                                        "¿Desea intentar de nuevo?")
            if not respuesta:
                # Se importa aca para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)

    imprimir_titulo(frame)

    # Pide la cedula del paciente
    label_ingreso_cedula = tk.Label(frame, text="Ingrese la cédula del paciente:", bg="white", font=("Helvetica", 10, "bold"))
    label_ingreso_cedula.pack()

    ingreso_cedula = tk.Entry(frame)
    ingreso_cedula.pack(pady=5)

    boton_buscar_paciente = tk.Button(frame, text="Buscar", command=buscar_paciente)
    boton_buscar_paciente.pack(pady=10)



    # Funcionalidad para regresar a la ventana principal

    # Se importa aca para evitar una referencia circular
    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",
                               command=lambda: implementacion_default(frame))
    boton_regresar.pack()
