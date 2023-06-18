import tkinter as tk

def imprimir_titulo(frame_implementacion):
    # Limpia el frame
    for item in frame_implementacion.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame_implementacion, text="Ver pacientes del hospital", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def ver_pacientes(hospital,frame_implementacion):
    imprimir_titulo(frame_implementacion)

    titulo_ver_pacientes = tk.Label(frame_implementacion, text="Estos son todos los pacientes presentes en el hospital (Recuerda si es necesario, desplazarse hacia abajo)",
    bg="white",font=("Helvetica", 10, "bold"))
    titulo_ver_pacientes.pack(pady=10)

    pacientes_text = tk.Text(frame_implementacion, bg="white", font=("Helvetica", 14))
    pacientes_text.pack(fill=tk.BOTH,expand=True)

    # Mostrar todas las vacunas presentes en el hospital
    for paciente in hospital.lista_pacientes:
        nombre = paciente.nombre
        cedula = paciente.cedula
        tipo_eps=paciente.tipo_eps

        texto_paciente = f"Nombre: {nombre}\nCedula: {cedula}\nTipo eps: {tipo_eps}\n\n"

        # Insertar el texto de la vacuna en el widget Text
        pacientes_text.insert(tk.END, texto_paciente)

        pacientes_text.config(padx=30)
        pacientes_text.config(highlightthickness=5, highlightbackground="blue")