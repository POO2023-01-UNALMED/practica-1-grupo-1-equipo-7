from tkinter import messagebox

import tkinter as tk

from src.manejo_errores.error_aplicacion import *
from src.ui_main.gestion.field_frame import FieldFrame


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Administrar paciente", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)
def administrar_paciente(hospital, frame):

    def borrar(paciente):
        respuesta = tk.messagebox.askyesno("Confirmar eliminacion", "¿Estas seguro de eliminar este paciente?")
        if respuesta:
            hospital.lista_pacientes.remove(paciente)
            messagebox.showinfo("Paciente eliminado", "El paciente se ha eliminado exitosamente")
            # Se importa aca para evitar una referencia circular
            from src.ui_main.ventana_principal import implementacion_default
            implementacion_default(frame)
        else:
            messagebox.showinfo("Eliminacion cancelada", "El paciente no ha sido eliminado")
            # Se importa aca para evitar una referencia circular
            from src.ui_main.ventana_principal import implementacion_default
            implementacion_default(frame)

    def ver_paciente(paciente):
        imprimir_titulo(frame)

        info_paciente = tk.Label(frame, text=f"Informacion del paciente registrado", bg="white", font=("Helvetica", 12))
        info_paciente.pack(pady=10)

        frame_paciente = tk.Frame(frame, bg="white")
        frame_paciente.pack(padx=10, pady=10)

        label_cedula = tk.Label(frame_paciente, text="Cedula: ", bg="white", font=("Helvetica", 10, "bold"))
        label_cedula.grid(row=0, column=0, padx=10, pady=5, sticky="w")
        label_cedula_doctor = tk.Label(frame_paciente, text=paciente.cedula, bg="white")
        label_cedula_doctor.grid(row=0, column=1, padx=10, pady=5, sticky="w")

        label_paciente = tk.Label(frame_paciente, text="Paciente: ", bg="white", font=("Helvetica", 10, "bold"))
        label_paciente.grid(row=1, column=0, padx=10, pady=5, sticky="w")
        label_nombre_doctor = tk.Label(frame_paciente, text=paciente.nombre, bg="white")
        label_nombre_doctor.grid(row=1, column=1, padx=10, pady=5, sticky="w")

        label_tipo_eps = tk.Label(frame_paciente, text="Tipo de eps: ", bg="white", font=("Helvetica", 10, "bold"))
        label_tipo_eps.grid(row=2, column=0, padx=10, pady=5, sticky="w")
        label_tipo_eps_paciente = tk.Label(frame_paciente, text=paciente.tipo_eps, bg="white")
        label_tipo_eps_paciente.grid(row=2, column=1, padx=10, pady=5, sticky="w")
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()
    def actualizar_paciente(fp, paciente):
        nombre = fp.getValue(2)
        error = False
        if nombre != "":
            try:
                if nombre.isdigit():
                    error = True
                    raise ValueError
                else:
                    nombre = str(nombre)
            except ValueError:
                TipoIncorrecto().enviar_mensaje()
        else:
            try:
                error = True
                raise CampoVacio()
            except CampoVacio as e:
                e.enviar_mensaje()
        if error is not True:
            respuesta = tk.messagebox.askyesno("Confirmacion de cambio", "¿Estas seguro deseas cambiar el nombre?")
            if respuesta:
                paciente.nombre = nombre
                messagebox.showinfo("Cambio hecho", "El nombre se ha cambiado exitosamente")
                ver_paciente(paciente)
            else:
                messagebox.showinfo("Cambio cancelado", "El nombre no se ha cambiado")
                # Se importa aca para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)



    def habilitar_entrys(fp, paciente, boton, boton2):
        boton2.destroy()
        boton.destroy()
        fp.habilitarEntry(2, True)
        guardar_cambios = tk.Button(frame, text="Guardar", command=lambda: actualizar_paciente(fp, paciente))
        guardar_cambios.pack()
    def administracion_paciente(paciente):
        imprimir_titulo(frame)
        info_doctor = tk.Label(frame, text=f"Informacion del paciente", bg="white", font=("Helvetica", 12))
        info_doctor.pack(pady=10)

        criterios = ["Cedula", "Nombre", "Tipo de eps"]

        fp = FieldFrame(frame, "Criterio", criterios, "Valor", [paciente.cedula,
                                                                paciente.nombre, paciente.tipo_eps],
                        [False, False, False, False])
        fp.pack()

        boton_editar = tk.Button(frame, text="Editar", command=lambda: habilitar_entrys(fp, paciente, boton_editar, boton_borrar))
        boton_editar.pack(pady=10)
        boton_borrar = tk.Button(frame, text="Borrar", command=lambda: borrar(paciente))
        boton_borrar.pack(pady=10)


    def buscar_paciente():
        cedula = fp.getValue(1)
        if len(cedula) != 0:
            try:
                paciente = hospital.buscar_paciente(int(cedula))
                administracion_paciente(paciente)
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
