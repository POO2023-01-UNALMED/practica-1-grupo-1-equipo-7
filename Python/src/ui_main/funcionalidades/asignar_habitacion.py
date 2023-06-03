import tkinter as tk


def asignar_habitacion(frame_implementacion):
    # Implementacion
    label_inicial = tk.Label(frame_implementacion, text="Asignar habitacion")
    label_inicial.pack()
    label_inicial.place(relx=0.5, rely=0.5, anchor=tk.CENTER)

    # Funcionalidad para regresar a la ventana principal

    # Se importa aca para evitar una referencia circular
    from Python.src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame_implementacion, text="Regresar",
                               command=lambda: implementacion_default(frame_implementacion))
    boton_regresar.pack()
    boton_regresar.place(relx=0.5, rely=0.6, anchor=tk.CENTER)
