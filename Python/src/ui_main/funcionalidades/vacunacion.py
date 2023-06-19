import tkinter as tk
from tkinter import ttk, messagebox

from src.manejo_errores.error_aplicacion import *
from src.ui_main.gestion.field_frame import FieldFrame


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Vacunación", bg="white",font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)


def vacunacion(hospital, frame):


    def mostrar_historial_vacunas(paciente):
        imprimir_titulo(frame)
        info_historial = tk.Label(frame, text=f"Historial de vacunas de {paciente.nombre} - CC: {paciente.cedula}",
                                  bg="white", font=("Helvetica", 12))
        info_historial.pack(pady=10)

        historial_vacunas_text = tk.Text(frame, bg="white", font=("Helvetica", 14))
        historial_vacunas_text.pack(fill=tk.BOTH,expand=True)

        #Mostrar todas las vacunas que tiene el usuario

        vacunas_paciente=[]

        for cita in paciente.historia_clinica.historial_vacunas:
            vacunas_paciente.append(cita.vacuna)

        for vacuna in vacunas_paciente:
            nombre = vacuna.nombre
            tipo = vacuna.tipo
            precio = vacuna.precio

            texto_vacuna = f"Nombre: {nombre}\nTipo: {tipo}\nPrecio: {precio}\n\n"

            # Insertar el texto de la vacuna en el widget Text
            historial_vacunas_text.insert(tk.END, texto_vacuna)

        historial_vacunas_text.config(padx=30)
        historial_vacunas_text.config(highlightthickness=5, highlightbackground="#4D5BE4")
        historial_vacunas_text.config(state="disabled")

        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
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
                try:
                    raise CampoVacio()
                except CampoVacio as e:
                    e.enviar_mensaje()

        def lista_citas():
            lista_citas = []
            try:
                for vacuna in paciente.buscar_vacuna_por_eps(combo_tipo_vacuna.get(), hospital):
                    if vacuna.nombre == combo_elegir_vacuna.get():
                        for cita in vacuna.mostrar_agenda_disponible():
                            lista_citas.append(cita.fecha)
                return lista_citas

            except SinAgenda as e:
                e.enviar_mensaje()
                combo_elegir_cita['state'] = 'disabled'

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
                if len(opciones_disponibles)!=0:
                    combo_elegir_vacuna['values'] = opciones_disponibles
                else:
                    try:
                        raise SinVacunas()
                    except SinVacunas as e:
                        e.enviar_mensaje()
                        combo_elegir_vacuna['state'] = 'disabled'


        imprimir_titulo(frame)

        info_paciente = tk.Label(frame, text=f"{paciente.nombre} - CC: {paciente.cedula}", bg="white",font=("Helvetica", 12))
        info_paciente.pack(pady=20)

        frame1 = tk.Frame(frame,bg="white")
        frame1.pack()

        tipo_vacuna = tk.Label(frame1, text="Seleccione el tipo de vacuna:", bg="white",font=("Helvetica", 10, "bold"))
        tipo_vacuna.grid(row=0, column=0, padx=10, pady=10, sticky="w")
        valor_defecto1 = tk.StringVar
        combo_tipo_vacuna = ttk.Combobox(frame1, values=["Obligatoria", "No obligatoria"],
                                       textvariable=valor_defecto1, state="readonly")
        combo_tipo_vacuna.bind("<<ComboboxSelected>>", habilitar_elegir_vacuna)
        combo_tipo_vacuna.grid(row=0, column=1, padx=10, pady=10, sticky="w")

        elegir_vacuna = tk.Label(frame1, text="Seleccione la vacuna de su preferencia:", bg="white",font=("Helvetica", 10, "bold"))
        elegir_vacuna.grid(row=1, column=0, padx=10, pady=10, sticky="w")
        valor_defecto2 = tk.StringVar
        combo_elegir_vacuna = ttk.Combobox(frame1, textvariable=valor_defecto2, state="disabled")
        combo_elegir_vacuna.bind("<<ComboboxSelected>>", habilitar_elegir_cita)
        combo_elegir_vacuna.grid(row=1, column=1, padx=10, pady=10, sticky="w")

        elegir_cita = tk.Label(frame1, text="Seleccione una fecha para su cita en enfermería:", bg="white",font=("Helvetica", 10, "bold"))
        elegir_cita.grid(row=2, column=0, padx=10, pady=10, sticky="w")
        valor_defecto3 = tk.StringVar
        combo_elegir_cita = ttk.Combobox(frame1, textvariable=valor_defecto3, state="disabled")
        combo_elegir_cita.grid(row=2, column=1, padx=10, pady=10, sticky="w")

        boton_aceptar = tk.Button(frame, text="Aceptar", command=confirmar_cita,font=("Helvetica", 10, "bold"))
        boton_aceptar.pack(pady=5)

        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame),font=("Helvetica", 10, "bold"))
        boton_regresar.pack(pady=5)

    def buscar_paciente():
        cedula = fp.getValue(1)
        if len(cedula) !=0:
            try:
                paciente = hospital.buscar_paciente(int(cedula))
                agendamiento_de_la_vacuna(paciente)
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
    fp = FieldFrame(frame,"", criterios, "", None, None)
    fp.pack()

    boton_buscar_paciente = tk.Button(frame, text="Buscar", command=buscar_paciente,font=("Helvetica", 10, "bold"))
    boton_buscar_paciente.pack(pady=5)


    # Funcionalidad para regresar a la ventana principal

    # Se importa aca para evitar una referencia circular
    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",
                               command=lambda: implementacion_default(frame),font=("Helvetica", 10, "bold"))
    boton_regresar.pack(pady=5)


