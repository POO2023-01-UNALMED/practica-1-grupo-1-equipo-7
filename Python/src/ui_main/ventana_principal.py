import tkinter as tk
from Python.src.ui_main.funcionalidades import agendar_citas, formula_medica, asignar_habitacion, vacunacion, \
    facturacion
from Python.src.ui_main.gestion import gestion_temp


def cambiar_contenido(opcion, hospital, frame_implementacion):
    # Limpia el frame
    for widget in frame_implementacion.winfo_children():
        widget.destroy()

    # Ejecuta la funcionalidad
    opciones = {
        "cita": agendar_citas.agendar_citas,
        "formula": formula_medica.formula_medica,
        "habitacion": asignar_habitacion.asignar_habitacion,
        "vacuna": vacunacion.vacunacion,
        "pago": facturacion.facturacion,
        "gestion1": gestion_temp.gestion1
    }

    if opcion in opciones:
        opciones[opcion](hospital, frame_implementacion)


def implementacion_default(frame_implementacion):
    # Limpia el frame
    for widget in frame_implementacion.winfo_children():
        widget.destroy()

    # Ejecuta la implementacion por defecto
    label_inicial = tk.Label(frame_implementacion, text="Seleccione una opcion para continuar", bg="white")
    label_inicial.pack()
    label_inicial.place(relx=0.5, rely=0.5, anchor=tk.CENTER)


def ventana_principal(hospital):
    ventana = tk.Tk()
    ventana.title("Sistema de gestion hospitalaria")
    ventana.geometry("1280x720")
    # ventana.protocol("WM_DELETE_WINDOW", hospital.serializar())

    # Menu de opciones (Zona 1)
    barra_menu = tk.Menu(ventana)
    ventana.config(menu=barra_menu)

    opcion_archivo = tk.Menu(barra_menu, tearoff=0)
    barra_menu.add_cascade(label="Archivo", menu=opcion_archivo)
    opcion_archivo.add_command(label="Aplicacion")

    # Se importa aca para evitar una referencia circular
    from Python.src.ui_main.main import ventana_inicial

    opcion_archivo.add_command(label="Salir",
                               command=lambda: [ventana.destroy(), ventana_inicial(hospital)])

    opcion_funcionalidades = tk.Menu(barra_menu, tearoff=0)
    barra_menu.add_cascade(label="Procesos y Consultas", menu=opcion_funcionalidades)
    opcion_funcionalidades.add_command(label="Agendar citas",
                                       command=lambda: cambiar_contenido("cita", hospital, frame_implementacion))
    opcion_funcionalidades.add_command(label="Generar formula medica",
                                       command=lambda: cambiar_contenido("formula", hospital, frame_implementacion))
    opcion_funcionalidades.add_command(label="Asignar habitacion",
                                       command=lambda: cambiar_contenido("habitacion", hospital, frame_implementacion))
    opcion_funcionalidades.add_command(label="Aplicarse una vacuna",
                                       command=lambda: cambiar_contenido("vacuna", hospital, frame_implementacion))
    opcion_funcionalidades.add_command(label="Facturacion",
                                       command=lambda: cambiar_contenido("pago", hospital, frame_implementacion))
    opcion_funcionalidades.add_separator()
    opcion_funcionalidades.add_command(label="Uno por uno lo de gestion",
                                       command=lambda: cambiar_contenido("gestion1", hospital, frame_implementacion))

    opcion_ayuda = tk.Menu(barra_menu, tearoff=0)
    barra_menu.add_cascade(label="Ayuda", menu=opcion_ayuda)
    opcion_ayuda.add_command(label="Acerca de")

    # Implementacion de las funcionalidades (Zona 2)
    frame_implementacion = tk.Frame(ventana)
    frame_implementacion.pack(fill=tk.BOTH, expand=True)
    frame_implementacion.configure(bg="white")

    implementacion_default(frame_implementacion)

    ventana.mainloop()
