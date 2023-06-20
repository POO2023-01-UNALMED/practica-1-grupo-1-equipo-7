from tkinter import messagebox

import tkinter as tk

from src.gestor_aplicacion.personas.paciente import Paciente
from src.manejo_errores.error_aplicacion import *
from src.ui_main.gestion.field_frame import FieldFrame


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Agregar paciente", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)


def registrar_paciente(hospital, frame):
    def ver_paciente(cedula, nombre, tipo_eps):
        imprimir_titulo(frame)

        info_paciente = tk.Label(frame, text=f"Informacion del paciente registrado", bg="white", font=("Helvetica", 12))
        info_paciente.pack(pady=10)

        frame_paciente = tk.Frame(frame, bg="white")
        frame_paciente.pack(padx=10, pady=10)

        label_cedula = tk.Label(frame_paciente, text="Cedula: ", bg="white", font=("Helvetica", 10, "bold"))
        label_cedula.grid(row=0, column=0, padx=10, pady=5, sticky="w")
        label_cedula_doctor = tk.Label(frame_paciente, text=cedula, bg="white")
        label_cedula_doctor.grid(row=0, column=1, padx=10, pady=5, sticky="w")

        label_paciente = tk.Label(frame_paciente, text="Paciente: ", bg="white", font=("Helvetica", 10, "bold"))
        label_paciente.grid(row=1, column=0, padx=10, pady=5, sticky="w")
        label_nombre_doctor = tk.Label(frame_paciente, text=nombre, bg="white")
        label_nombre_doctor.grid(row=1, column=1, padx=10, pady=5, sticky="w")

        label_tipo_eps = tk.Label(frame_paciente, text="Tipo de eps: ", bg="white", font=("Helvetica", 10, "bold"))
        label_tipo_eps.grid(row=2, column=0, padx=10, pady=5, sticky="w")
        label_tipo_eps_paciente = tk.Label(frame_paciente, text=tipo_eps, bg="white")
        label_tipo_eps_paciente.grid(row=2, column=1, padx=10, pady=5, sticky="w")

        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    def agregar_a_lista_pacientes():
        cedula = fp.getValue(1)
        nombre = fp.getValue(2)
        tipo_eps = fp.getValue(3)
        error = False
        if cedula != "" and nombre != "" and tipo_eps != "":
            try:
                cedula = int(cedula)
                for pac in hospital.lista_pacientes:
                    if cedula == pac.cedula:
                        error = True
                        raise DatoDuplicado()
            except DatoDuplicado as e:
                e.enviar_mensaje()
            except ValueError:
                TipoIncorrecto("en el campo cedula").enviar_mensaje()
            try:
                if nombre.isdigit():
                    error = True
                    raise ValueError
                else:
                    nombre = str(nombre)
            except ValueError:
                TipoIncorrecto("en el campo nombre").enviar_mensaje()
            try:
                if tipo_eps != "Subsidiado" and tipo_eps != "Contributivo" and tipo_eps != "Particular":
                    error = True
                    raise ValueError
                else:
                    nombre = str(nombre)
            except ValueError:
                TipoIncorrecto("en el campo tipo eps").enviar_mensaje()
        else:
            try:
                error = True
                raise CampoVacio()
            except CampoVacio as e:
                e.enviar_mensaje()

        if error is not True:
            respuesta = tk.messagebox.askyesno("Confirmacion del paciente", "¿Estas seguro de registrar este paciente?")

            if respuesta:

                paciente = Paciente(cedula, nombre, tipo_eps)
                hospital.lista_pacientes.append(paciente)
                messagebox.showinfo("Paciente registrado", "El paciente se ha registrado exitosamente")
                ver_paciente(cedula, nombre, tipo_eps)
            else:
                messagebox.showinfo("Paciente no registrado", "No se ha registrado el paciente")
                # Se importa aca para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)

    def borrar_campos():
        for entry in fp.valores:
            entry.delete(0, tk.END)

    imprimir_titulo(frame)

    criterios = ["Cédula", "Nombre", "Tipo de eps (Subsidado, Contributivo o Particular)"]
    fp = FieldFrame(frame, "Criterio", criterios, "Valor", None, None)
    fp.pack()

    botones_frame = tk.Frame(frame, bg="white")
    botones_frame.pack()

    boton_guardar = tk.Button(botones_frame, text="Guardar", command=agregar_a_lista_pacientes)
    boton_guardar.grid(row=0, column=0, padx=10, pady=10, sticky="w")

    boton_borrar = tk.Button(botones_frame, text="Borrar", command=borrar_campos)
    boton_borrar.grid(row=0, column=1, padx=10, pady=10, sticky="w")
