from tkinter import messagebox, ttk

import tkinter as tk

from src.gestor_aplicacion.personas.enfermedad import Enfermedad
from src.manejo_errores.error_aplicacion import *
from src.ui_main.gestion.field_frame import FieldFrame


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Registrar nueva enfermedad a un paciente", bg="white",
                      font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)


def registrar_enfermedad(hospital, frame):
    def nueva_enf(paciente):
        def borrar_campos():
            for entry in fp.valores:
                entry.delete(0, tk.END)

        def crear_enf():
            nombre = fp.getValue(1)
            tipologia = fp.getValue(2)
            especialidad = fp.getValue(3)
            error = False
            if nombre != "" and tipologia != "" and especialidad != "":
                try:
                    if nombre.isdigit():
                        error = True
                        raise ValueError
                    else:
                        nombre = str(nombre)
                except ValueError:
                    TipoIncorrecto("en el campo nombre").enviar_mensaje()
                try:
                    if tipologia.isdigit():
                        error = True
                        raise ValueError
                    else:
                        tipologia = str(tipologia)
                except ValueError:
                    TipoIncorrecto("en el campo tipologia").enviar_mensaje()
                try:
                    if especialidad != "General" and especialidad != "Oftalmologia" and especialidad != "Odontologia":
                        error = True
                        raise ValueError
                    else:
                        especialidad = str(especialidad)
                except ValueError:
                    TipoIncorrecto("en el campo especialidad").enviar_mensaje()
                try:
                    for enf in Enfermedad.getEnfermedadesRegistradas():
                        if nombre == enf.nombre and tipologia == enf.tipologia:
                            error = True
                            raise DatoDuplicado()
                except DatoDuplicado as e:
                    e.enviar_mensaje()
            else:
                try:
                    raise CampoVacio()
                except CampoVacio as e:
                    e.enviar_mensaje()
                    error = True

            if error is not True:
                respuesta = tk.messagebox.askyesno("Confirmacion Enfermedad",
                                                   "¿Estas seguro de agregar esta enfermedad?")
                if respuesta:
                    enf = Enfermedad(nombre, tipologia, especialidad)
                    paciente.historia_clinica.enfermedades.append(enf)
                    messagebox.showinfo("Enfermedad agregada", "La enfermedad se ha agregado exitosamente")
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)

                else:
                    messagebox.showinfo("Enfermedad no agregada", "No se ha agregado la enfermedad")
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)

        imprimir_titulo(frame)
        criterios = ["Nombre", "Tipologia", "Especialidad que la trata(General, Oftalmologia u Odontologia)"]
        fp = FieldFrame(frame, "Criterio", criterios, "Valor", None, None)
        fp.pack()

        botones_frame = tk.Frame(frame, bg="white")
        botones_frame.pack()

        boton_guardar = tk.Button(botones_frame, text="Guardar", command=crear_enf)
        boton_guardar.grid(row=0, column=0, padx=10, pady=10, sticky="w")

        boton_borrar = tk.Button(botones_frame, text="Borrar", command=borrar_campos)
        boton_borrar.grid(row=0, column=1, padx=10, pady=10, sticky="w")

    def agregar_enf(paciente, combo):
            indice_seleccionado = combo.current()
            objeto_seleccionado = Enfermedad.getEnfermedadesRegistradas()[indice_seleccionado]
            error = False
            if objeto_seleccionado in paciente.historia_clinica.enfermedades:
                try:
                    error = True
                    raise DatoDuplicado()
                except DatoDuplicado as e:
                    e.enviar_mensaje()
            if error is not True:
                respuesta = tk.messagebox.askyesno("Confirmar registro", "¿Estas seguro de agregar esta enfermedad?")
                if respuesta:

                    paciente.historia_clinica.agregar_enfermedad(objeto_seleccionado)
                    messagebox.showinfo("Enfermedad agregada", "La enfermedad se ha agregado exitosamente")
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)
                else:
                    messagebox.showinfo("Agregar enfermedad cancelada", "No se ha agregado la enfermedad")
                    # Se importa aca para evitar una referencia circular
                    from src.ui_main.ventana_principal import implementacion_default
                    implementacion_default(frame)

    def registrar_enf(paciente):
        imprimir_titulo(frame)
        label_enfs = tk.Label(frame, text="Enfermedades actuales", bg="white", font=("Helvetica", 12, "bold"))
        label_enfs.pack(pady=10)
        frame_enfermedades = tk.Frame(frame, bg="white")
        frame_enfermedades.pack()
        for enf in paciente.historia_clinica.enfermedades:
            enf_label = tk.Label(frame_enfermedades, text=enf, font=("Helvetica", 10), bg="white")
            enf_label.pack()
        label_opcion = tk.Label(frame, text="Elija que enfermedad registrar", font=("Helvetica", 12, "bold"),
                                bg="white")
        label_opcion.pack()
        combo_enfermedades = ttk.Combobox(frame, values=Enfermedad.getEnfermedadesRegistradas(), state="readonly")
        combo_enfermedades.pack()
        boton_agregar = tk.Button(frame, text="Agregar", command=lambda: agregar_enf(paciente, combo_enfermedades))
        boton_agregar.pack(pady=10)
        boton_registrar = tk.Button(frame, text="Registrar enfermedad nueva", command=lambda: nueva_enf(paciente))
        boton_registrar.pack()

    def buscar_paciente():
        cedula = fp.getValue(1)
        if len(cedula) != 0:
            try:
                paciente = hospital.buscar_paciente(int(cedula))
                registrar_enf(paciente)
            except DatosFalsos as e:
                e.enviar_mensaje()
            except ValueError:
                TipoIncorrecto().enviar_mensaje()
        else:
            try:
                raise CampoVacio()
            except CampoVacio as e:
                e.enviar_mensaje()

    imprimir_titulo(frame)

    # Pide la cedula del paciente
    label_ingreso_cedula = tk.Label(frame, text="Ingrese la cédula del paciente:", bg="white",
                                    font=("Helvetica", 10, "bold"))
    label_ingreso_cedula.pack()

    criterios = ["Cédula"]
    fp = FieldFrame(frame, "", criterios, "", None, None)
    fp.pack()

    boton_buscar_paciente = tk.Button(frame, text="Buscar", command=buscar_paciente)
    boton_buscar_paciente.pack()
