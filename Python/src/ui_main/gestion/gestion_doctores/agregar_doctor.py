from tkinter import messagebox

from src.gestor_aplicacion.personas.doctor import Doctor
from src.manejo_errores.error_aplicacion import CampoVacio, TipoIncorrecto, DatoDuplicado
from src.ui_main.gestion.field_frame import FieldFrame
import tkinter as tk


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Agregar doctor", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def agregar_doctor(hospital, frame):

    def ver_doctor(cedula, nombre, tipo_eps, especialidad):
        imprimir_titulo(frame)

        info_doctor = tk.Label(frame, text=f"Informacion del doctor registrado", bg="white", font=("Helvetica", 12))
        info_doctor.pack(pady=10)

        frame_doctor = tk.Frame(frame, bg="white")
        frame_doctor.pack(padx=10, pady=10)

        label_cedula = tk.Label(frame_doctor, text="Cedula: ", bg="white", font=("Helvetica", 10, "bold"))
        label_cedula.grid(row=0, column=0, padx=10, pady=5, sticky="w")
        label_cedula_doctor = tk.Label(frame_doctor, text=cedula, bg="white")
        label_cedula_doctor.grid(row=0, column=1, padx=10, pady=5, sticky="w")

        label_doctor = tk.Label(frame_doctor, text="Doctor: ", bg="white", font=("Helvetica", 10, "bold"))
        label_doctor.grid(row=1, column=0, padx=10, pady=5, sticky="w")
        label_nombre_doctor = tk.Label(frame_doctor, text=nombre, bg="white")
        label_nombre_doctor.grid(row=1, column=1, padx=10, pady=5, sticky="w")

        label_tipo_eps = tk.Label(frame_doctor, text="Tipo de eps: ", bg="white", font=("Helvetica", 10, "bold"))
        label_tipo_eps.grid(row=2, column=0, padx=10, pady=5, sticky="w")
        label_tipo_eps_doctor = tk.Label(frame_doctor, text=tipo_eps, bg="white")
        label_tipo_eps_doctor.grid(row=2, column=1, padx=10, pady=5, sticky="w")

        label_especialidad = tk.Label(frame_doctor, text="Especialidad: ", bg="white", font=("Helvetica", 10, "bold"))
        label_especialidad.grid(row=3, column=0, padx=10, pady=5, sticky="w")
        label_especialidad_doctor = tk.Label(frame_doctor, text=especialidad, bg="white")
        label_especialidad_doctor.grid(row=3, column=1, padx=10, pady=5, sticky="w")

        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    def agregar_a_lista_doctores():
        cedula = fp.getValue(1)
        nombre = fp.getValue(2)
        tipo_eps = fp.getValue(3)
        especialidad = fp.getValue(4)

        # Variable de control para verificar si hay errores
        hay_errores = False

        if len(cedula) != 0:
            try:
                cedula = int(fp.getValue(1))
                if hospital.buscar_doctor(cedula) is not None:
                    hay_errores = True
                    try:
                        raise DatoDuplicado()
                    except DatoDuplicado as e:
                        e.enviar_mensaje()
            except ValueError:
                hay_errores = True
                TipoIncorrecto("en el campo cedula").enviar_mensaje()

        if len(nombre) != 0:
            try:
                if nombre.isdigit():
                    hay_errores = True
                    raise ValueError
                else:
                    nombre = str(fp.getValue(2))
            except ValueError:
                hay_errores = True
                TipoIncorrecto("en el campo nombre").enviar_mensaje()

        if len(tipo_eps) != 0:
            try:
                if tipo_eps != "Subsidiado" and tipo_eps != "Contributivo" and tipo_eps != "Particular":
                    hay_errores = True
                    raise ValueError
                else:
                    tipo_eps = str(fp.getValue(3))
            except ValueError:
                hay_errores = True
                TipoIncorrecto("en el campo tipo de eps").enviar_mensaje()

        if len(especialidad) != 0:
            try:
                if especialidad != "General" and especialidad != "Odontologia" and especialidad != "Oftalmologia":
                    hay_errores = True
                    raise ValueError
                else:
                    especialidad = str(fp.getValue(4))
            except ValueError:
                hay_errores = True
                TipoIncorrecto("en el campo especialidad").enviar_mensaje()

        if not cedula or not nombre or not tipo_eps or not especialidad:
            hay_errores = True
            try:
                raise CampoVacio()
            except CampoVacio as e:
                e.enviar_mensaje()

        if not hay_errores:
            respuesta = tk.messagebox.askyesno("Confirmacion del doctor", "¿Estás seguro de agregar este doctor?")
            if respuesta:
                doctor = Doctor(cedula, nombre, tipo_eps, especialidad)
                hospital.lista_doctores.append(doctor)
                messagebox.showinfo("Doctor agregado", "El doctor se ha agregado exitosamente")
                ver_doctor(cedula, nombre, tipo_eps, especialidad)
            else:
                messagebox.showinfo("Doctor no agregado", "No se ha agregado el doctor")
                # Se importa acá para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)


    def borrar_campos():
        for entry in fp.valores:
            entry.delete(0,tk.END)


    imprimir_titulo(frame)

    criterios = ["Cédula", "Nombre", "Tipo de eps (Subsidado, Contributivo o Particular)", "Especialidad (General, Oftalmologia u Odontologia)"]
    fp = FieldFrame(frame, "Criterio", criterios, "Valor", None, None)
    fp.pack()

    botones_frame = tk.Frame(frame, bg="white")
    botones_frame.pack()

    boton_guardar = tk.Button(botones_frame, text="Guardar", command=agregar_a_lista_doctores)
    boton_guardar.grid(row=0, column=0, padx=10, pady=10, sticky="w")

    boton_borrar = tk.Button(botones_frame, text="Borrar", command=borrar_campos)
    boton_borrar.grid(row=0, column=1, padx=10, pady=10, sticky="w")

    # Funcionalidad para regresar a la ventana principal

    # Se importa aca para evitar una referencia circular
    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",command=lambda: implementacion_default(frame))
    boton_regresar.pack()