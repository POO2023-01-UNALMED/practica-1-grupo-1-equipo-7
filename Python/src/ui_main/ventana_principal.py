import tkinter as tk
from src.ui_main.funcionalidades import agendar_citas, formula_medica, asignar_habitacion, vacunacion, \
    facturacion
from src.ui_main.gestion.gestion_doctores import agregar_doctor,ver_doctor,eliminar_doctor,agregar_cita
from src.ui_main.gestion.gestion_vacunas import registrar_vacuna, ver_vacuna,eliminar_vacuna
from src.ui_main.gestion.gestion_hospital import ver_vacunas


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
        #Gestion hospital
        "ver_vacunas": ver_vacunas.ver_vacunas,
        #Gestion vacunas
        "registrar_vacuna": registrar_vacuna.registrar_vacuna,
        "ver_vacuna": ver_vacuna.ver_vacuna,
        "eliminar_vacuna": eliminar_vacuna.eliminar_vacuna,
        #Gestion Doctores
        "agregar_doctor": agregar_doctor.agregar_doctor,
        "ver_doctor": ver_doctor.ver_doctor,
        "eliminar_doctor": eliminar_doctor.eliminar_doctor,
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
    ventana.title("MedPlus")
    ventana.geometry("1280x720")
    # ventana.protocol("WM_DELETE_WINDOW", hospital.serializar())

    # Menu de opciones (Zona 1)
    barra_menu = tk.Menu(ventana)
    ventana.config(menu=barra_menu)

    opcion_archivo = tk.Menu(barra_menu, tearoff=0)
    barra_menu.add_cascade(label="Archivo", menu=opcion_archivo)
    opcion_archivo.add_command(label="Aplicacion")

    # Se importa aca para evitar una referencia circular
    from src.ui_main.main import ventana_inicial

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
    #Gestión hospital con un submenú
    opcion_gestion_hospital= tk.Menu(opcion_funcionalidades, tearoff=0)
    opcion_funcionalidades.add_cascade(label="Gestión hospital", menu=opcion_gestion_hospital)
    opcion_gestion_hospital.add_command(label="Vacunas del hospital",command=lambda: cambiar_contenido("ver_vacunas", hospital,frame_implementacion))
    #Gestion vacunas con un submenú
    opcion_gestion_vacuna= tk.Menu(opcion_funcionalidades, tearoff=0)
    opcion_funcionalidades.add_cascade(label="Gestión vacunas", menu=opcion_gestion_vacuna)
    opcion_gestion_vacuna.add_command(label="Registrar Vacuna",command=lambda: cambiar_contenido("registrar_vacuna", hospital, frame_implementacion))
    opcion_gestion_vacuna.add_command(label="Ver vacuna",command=lambda: cambiar_contenido("ver_vacuna", hospital, frame_implementacion))
    opcion_gestion_vacuna.add_command(label="Eliminar vacuna",command=lambda: cambiar_contenido("eliminar_vacuna", hospital,frame_implementacion))
    #Gestión doctores con un submenú
    opcion_gestion_doctores = tk.Menu(opcion_funcionalidades, tearoff=0)
    opcion_funcionalidades.add_cascade(label="Gestión doctores", menu=opcion_gestion_doctores)
    opcion_gestion_doctores.add_command(label="Agregar doctor",command=lambda: cambiar_contenido("agregar_doctor", hospital, frame_implementacion))
    opcion_gestion_doctores.add_command(label="Ver doctor",command=lambda: cambiar_contenido("ver_doctor", hospital, frame_implementacion))
    opcion_gestion_doctores.add_command(label="Eliminar doctor",command=lambda: cambiar_contenido("eliminar_doctor", hospital, frame_implementacion))


    opcion_ayuda = tk.Menu(barra_menu, tearoff=0)
    barra_menu.add_cascade(label="Ayuda", menu=opcion_ayuda)
    opcion_ayuda.add_command(label="Acerca de")

    # Implementacion de las funcionalidades (Zona 2)
    frame_implementacion = tk.Frame(ventana)
    frame_implementacion.pack(fill=tk.BOTH, expand=True)
    frame_implementacion.configure(bg="white")

    implementacion_default(frame_implementacion)

    ventana.mainloop()
