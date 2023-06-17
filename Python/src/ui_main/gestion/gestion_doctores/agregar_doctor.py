from tkinter import messagebox

from src.gestor_aplicacion.personas.doctor import Doctor
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
        respuesta = tk.messagebox.askyesno("Confirmacion del doctor", "¿Estas seguro de agregar este doctor?")

        if respuesta:
            cedula = int(fp.getValue(1))
            nombre = str(fp.getValue(2))
            tipo_eps = str(fp.getValue(3))
            especialidad = str(fp.getValue(4))

            doctor = Doctor(cedula, nombre, tipo_eps, especialidad)
            hospital.lista_doctores.append(doctor)
            messagebox.showinfo("Doctor agregado", "El doctor se ha agregado exitosamente")
            ver_doctor(cedula, nombre, tipo_eps, especialidad)

    def borrar_campos():
        for entry in fp.valores:
            entry.delete(0,tk.END)


    imprimir_titulo(frame)

    criterios = ["Cédula", "Nombre", "Tipo de eps", "Especialidad"]
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