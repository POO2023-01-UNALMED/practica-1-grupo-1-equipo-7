from tkinter import messagebox, ttk
from src.ui_main.gestion.field_frame import FieldFrame
import tkinter as tk

def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Eliminar cita", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def eliminar_cita(hospital, frame):
    def ver_citas_doctor(doctor):
        imprimir_titulo(frame)

        info_doctor = tk.Label(frame, text=f"Citas en la agenda de {doctor.nombre} - CC: {doctor.cedula}", bg="white",
                               font=("Helvetica", 12))
        info_doctor.pack(pady=10)

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

        for cita in doctor.agenda:
            frame_cita = tk.Frame(inner_frame, bg="white")

            label_fecha = tk.Label(frame_cita, text="Fecha: ", bg="white", font=("Helvetica", 10, "bold"))
            label_fecha.grid(row=0, column=0, padx=10, pady=5, sticky="w")
            label_fecha_cita = tk.Label(frame_cita, text=cita.fecha, bg="white")
            label_fecha_cita.grid(row=0, column=1, padx=10, pady=5, sticky="w")

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

    def busqueda_cita(doctor):
        def lista_citas():
            lista_citas = []
            for cita in doctor.agenda:
                lista_citas.append(cita.fecha)
            return lista_citas

        def eliminacion_cita(doctor,fecha):
            imprimir_titulo(frame)

            for cita in doctor.agenda:
                if cita.fecha == fecha:
                    respuesta = tk.messagebox.askyesno("Confirmar eliminacion", "¿Estas seguro de eliminar esta cita?")
                    if respuesta:
                        doctor.agenda.remove(cita)
                        messagebox.showinfo("Cita eliminada", "La cita se ha eliminado exitosamente")
                        ver_citas_doctor(doctor)
                    else:
                        messagebox.showinfo("Eliminacion cancelada", "La cita no ha sido eliminada")
                        # Se importa aca para evitar una referencia circular
                        from src.ui_main.ventana_principal import implementacion_default
                        implementacion_default(frame)

        imprimir_titulo(frame)

        titulo_ingreso_fecha = tk.Label(frame, text="Ingrese la fecha de la que se eliminara:",bg="white", font=("Helvetica", 10, "bold"))
        titulo_ingreso_fecha.pack()

        valor_defecto = tk.StringVar
        combo_elegir_cita = ttk.Combobox(frame, values= lista_citas(), textvariable=valor_defecto, state="readonly")
        combo_elegir_cita.pack(pady=5)

        fecha = combo_elegir_cita.get()
        print(fecha)

        boton_eliminar_cita = tk.Button(frame, text="Eliminar", command=lambda: eliminacion_cita(doctor,fecha))
        boton_eliminar_cita.pack(pady=5)

        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    def busqueda_doctor():
        cedula= fp1.getValue(1)
        doctor= hospital.buscar_doctor(int(cedula))

        if doctor is not None:
            busqueda_cita(doctor)
        else:
            respuesta = tk.messagebox.askyesno("Error", "No existe un doctor registrado con esa cedula. " "¿Desea intentar de nuevo?")
            if not respuesta:
                # Se importa aca para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)

    imprimir_titulo(frame)

    titulo_ingreso_cedula = tk.Label(frame, text="Ingrese la cedula del doctor al que se le eliminara una cita:", bg="white",font=("Helvetica", 10, "bold"))
    titulo_ingreso_cedula.pack()

    criterios = ["Cedula"]
    fp1 = FieldFrame(frame, "", criterios, "", None, None)
    fp1.pack()

    boton_buscar_doctor = tk.Button(frame, text="Buscar", command=busqueda_doctor)
    boton_buscar_doctor.pack(pady=5)

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
    boton_regresar.pack()