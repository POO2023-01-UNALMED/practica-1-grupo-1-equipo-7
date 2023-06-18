import tkinter as tk

def imprimir_titulo(frame_implementacion):
    # Limpia el frame
    for item in frame_implementacion.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame_implementacion, text="Ver medicamentos del hospital", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def ver_medicamentos(hospital, frame):
    imprimir_titulo(frame)

    titulo_ver_vacunas = tk.Label(frame, text="Estas son todos los medicamentos presentes en el hospital (Recuerda, si es necesario, desplazarse hacia abajo)",
    bg="white",font=("Helvetica", 10, "bold"))
    titulo_ver_vacunas.pack(pady=10)
    meds_text = tk.Text(frame, bg="white", font=("Helvetica", 14))
    meds_text.pack(fill=tk.BOTH,expand=True)

    # Mostrar todos las medicamentos presentes en el hospital
    for medicamento in hospital.lista_medicamentos:
        # Insertar el toString del medicamento en el widget Text
        meds_text.insert(tk.END, str(medicamento) + "\n")

    meds_text.config(padx=30)
    meds_text.config(highlightthickness=5, highlightbackground="blue")
    meds_text.config(state="disabled")

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",
                               command=lambda: implementacion_default(frame),
                               font=("Helvetica", 10, "bold"))
    boton_regresar.pack(pady=20)