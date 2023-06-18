import tkinter as tk

def imprimir_titulo(frame_implementacion):
    # Limpia el frame
    for item in frame_implementacion.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame_implementacion, text="Ver doctores del hospital", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def ver_doctores(hospital,frame_implementacion):
    imprimir_titulo(frame_implementacion)

    titulo_ver_doctores = tk.Label(frame_implementacion, text="Estos son todos los doctores presentes en el hospital (Recuerda, si es necesario, desplazarse hacia abajo)",
    bg="white",font=("Helvetica", 10, "bold"))
    titulo_ver_doctores.pack(pady=10)

    doctores_text = tk.Text(frame_implementacion, bg="white", font=("Helvetica", 14))
    doctores_text.pack(fill=tk.BOTH,expand=True)

    # Mostrar todas las vacunas presentes en el hospital
    for doctor in hospital.lista_doctores:
        nombre = doctor.nombre
        cedula = doctor.cedula
        especialidad = doctor.especialidad
        tipo_eps= doctor.tipo_eps
        fechas=[]
        for cita in doctor.agenda:
            fechas.append(cita.fecha)
        agenda = ",".join(fechas)

        texto_doctor = f"Nombre: {nombre}\nCedula: {cedula}\nEspecialidad: {especialidad}\nTipo de eps: {tipo_eps}\nAgenda: {agenda}\n\n"

        # Insertar el texto de la vacuna en el widget Text
        doctores_text.insert(tk.END, texto_doctor)

    doctores_text.config(padx=30)
    doctores_text.config(highlightthickness=5, highlightbackground="#4D5BE4")
    doctores_text.config(state="disabled")

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame_implementacion, text="Regresar",
                               command=lambda: implementacion_default(frame_implementacion),
                               font=("Helvetica", 10, "bold"))
    boton_regresar.pack(pady=20)