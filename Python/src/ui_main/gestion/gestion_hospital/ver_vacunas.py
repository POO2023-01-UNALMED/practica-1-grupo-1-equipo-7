import tkinter as tk

def imprimir_titulo(frame_implementacion):
    # Limpia el frame
    for item in frame_implementacion.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame_implementacion, text="Ver vacunas del hospital", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def ver_vacunas(hospital,frame_implementacion):
    imprimir_titulo(frame_implementacion)

    titulo_ver_vacunas = tk.Label(frame_implementacion, text="Estas son todas las vacunas presentes en el hospital (Recuerda, si es necesario, desplazarse hacia abajo)",
    bg="white",font=("Helvetica", 10, "bold"))
    titulo_ver_vacunas.pack(pady=10)

    vacunas_text = tk.Text(frame_implementacion, bg="white", font=("Helvetica", 14))
    vacunas_text.pack(fill=tk.BOTH,expand=True)

    # Mostrar todas las vacunas presentes en el hospital
    for vacuna in hospital.lista_vacunas:
        nombre = vacuna.nombre
        tipo = vacuna.tipo
        tipo_eps =",".join(vacuna.tipo_eps)
        fechas=[]
        for cita in vacuna.agenda:
            fechas.append(cita.fecha)
        agenda = ",".join(fechas)
        precio = vacuna.precio

        texto_vacuna = f"Nombre: {nombre}\nTipo: {tipo}\nPrecio: {precio}\nEps a la que pertenece: {tipo_eps}\nAgenda: {agenda}\n\n"

        # Insertar el texto de la vacuna en el widget Text
        vacunas_text.insert(tk.END, texto_vacuna)

    vacunas_text.config(padx=30)
    vacunas_text.config(highlightthickness=5, highlightbackground="#4D5BE4")
    vacunas_text.config(state="disabled")

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame_implementacion, text="Regresar",
                               command=lambda: implementacion_default(frame_implementacion),
                               font=("Helvetica", 10, "bold"))
    boton_regresar.pack(pady=20)