import tkinter as tk
from tkinter import ttk, messagebox

from src.manejo_errores.error_aplicacion import DatosFalsos, TipoIncorrecto, CampoVacio, SinDoctores, SinAgenda
from src.ui_main.gestion.field_frame import FieldFrame

# Se usa para borrar lo que hay en el frame y mostrar el titulo de la funcionalidad
def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Agendar citas", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)
def agendar_citas(hospital, frame):

    # Cuando se confirma la cita, se muestra el historial de todas las citas del paciente
    def mostrar_historial_citas(paciente):
        imprimir_titulo(frame)
        info_historial = tk.Label(frame, text=f"Historial de citas de {paciente.nombre} - CC: {paciente.cedula}",
                                  bg="white",font=("Helvetica", 12))
        info_historial.pack(pady=10)

        historial_citas_text = tk.Text(frame, bg="white", font=("Helvetica", 14))
        historial_citas_text.pack(fill=tk.BOTH, expand=True)

        for cita in paciente.historia_clinica.historial_citas:

            tipo_de_cita = cita.doctor.especialidad
            nombre_doctor = cita.doctor.nombre
            fecha = cita.fecha

            texto_cita = f"Tipo de cita: {tipo_de_cita}\nDoctor: {nombre_doctor}\nFecha: {fecha}\n\n"

            historial_citas_text.insert(tk.END, texto_cita)

        historial_citas_text.config(padx=30)
        historial_citas_text.config(highlightthickness=5, highlightbackground="#4D5BE4")
        historial_citas_text.config(state="disabled")

        # Boton para regresar a la ventana principal

        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    # Aca se ingresan todos los datos necesarios para agendar una cita
    def agendamiento_de_la_cita(paciente):

        # Se pide confirmar la cita y se actualiza la agenda del doctor y el historial del paciente
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
                try:
                    raise CampoVacio()
                except CampoVacio as e:
                    e.enviar_mensaje()

        # Busca las citas en la agenda del doctor seleccionado que estan disponibles
        def lista_citas():
            lista_citas = []
            try:
                for doctor in paciente.buscar_doctor_por_eps(combo_tipo_cita.get(), hospital):
                    if doctor.nombre == combo_elegir_doctor.get():
                        for cita in doctor.mostrar_agenda_disponible():
                            lista_citas.append(cita.fecha)
                return lista_citas

            except SinAgenda as e:
                e.enviar_mensaje()
                combo_elegir_cita['state'] = 'disabled'

        # Se usa como evento para cuando se selecciona algo en el combobox del doctor
        def habilitar_elegir_cita(event):
            eleccion = combo_elegir_doctor.get()
            combo_elegir_cita.set("")
            if eleccion:
                combo_elegir_cita['state'] = 'readonly'
                combo_elegir_cita['values'] = lista_citas()
            else:
                combo_elegir_cita['state'] = 'disabled'

        # Busca los doctores de la especialidad seleccionada y del tipo de eps del paciente
        def lista_doctores():
            lista_doctores = []
            try:
                for doctor in paciente.buscar_doctor_por_eps(combo_tipo_cita.get(), hospital):
                    lista_doctores.append(doctor.nombre)
                return lista_doctores
            except SinDoctores as e:
                e.enviar_mensaje()
                combo_elegir_doctor['state'] = 'disabled'

        # Se usa como evento para cuando se selecciona algo en el combobox del tipo de cita
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

        # se crean los label y combobox necesarios para llenar la informacion para agendar la cita
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

        # Boton para aceptar e ir a la confirmacion de la cita
        boton_aceptar = tk.Button(frame, text="Aceptar", command=confirmar_cita)
        boton_aceptar.pack(pady=5)

        # Boton para regresar a la ventana principal

        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack(pady=5)

    # Aca se verifica que el paciente exista y que no hubieron errores al ingresarlo
    def buscar_paciente():
        cedula = fp.getValue(1)

        if len(cedula) != 0:
            try:
                paciente = hospital.buscar_paciente(int(cedula))

                #Se llama la siguiente funcion
                agendamiento_de_la_cita(paciente)
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

    titulo_ingreso_cedula = tk.Label(frame, text="Ingrese la cédula del paciente:", bg="white",font=("Helvetica", 10, "bold"))
    titulo_ingreso_cedula.pack()

    criterios = ["Cédula"]
    fp = FieldFrame(frame, "", criterios, "", None, None)
    fp.pack()

    # Boton para buscar el paciente
    boton_buscar_paciente = tk.Button(frame, text="Buscar", command=buscar_paciente)
    boton_buscar_paciente.pack(pady=10)

    # Boton para regresar a la ventana principal

    # Se importa aca para evitar una referencia circular
    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",
                               command=lambda: implementacion_default(frame))
    boton_regresar.pack()
