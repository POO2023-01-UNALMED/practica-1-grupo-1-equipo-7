from tkinter import messagebox

from src.manejo_errores.error_aplicacion import DatosFalsos, TipoIncorrecto, CampoVacio
from src.ui_main.gestion.field_frame import FieldFrame
import tkinter as tk

def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Eliminar doctor", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def eliminar_doctor(hospital, frame):

    def eliminacion_doctor(doctor):
        respuesta = tk.messagebox.askyesno("Confirmar eliminacion", "¿Estas seguro de eliminar este doctor?")
        if respuesta:
            hospital.lista_doctores.remove(doctor)
            messagebox.showinfo("Doctor eliminado", "El doctor se ha eliminado exitosamente")
            from src.ui_main.ventana_principal import implementacion_default
            implementacion_default(frame)
        else:
            messagebox.showinfo("Eliminacion cancelada", "El doctor no ha sido eliminado")
            # Se importa aca para evitar una referencia circular
            from src.ui_main.ventana_principal import implementacion_default
            implementacion_default(frame)

    def elementos_doctor(doctor):
        imprimir_titulo(frame)
        info_doctor = tk.Label(frame, text=f"Información del doctor", bg="white", font=("Helvetica", 12))
        info_doctor.pack(pady=10)

        criterios = ["Cedula", "Nombre","Tipo de eps","Especialidad"]

        fp = FieldFrame(frame, "Criterio", criterios, "Valor",[doctor.cedula,
        doctor.nombre,doctor.tipo_eps,doctor.especialidad],[False,False,False,False])
        fp.pack()

        boton_eliminar = tk.Button(frame, text="Eliminar", command=lambda:eliminacion_doctor(doctor))
        boton_eliminar.pack(pady=10)

        from src.ui_main.ventana_principal import implementacion_default
        boton_regresar = tk.Button(frame, text="Regresar",command=lambda: implementacion_default(frame))
        boton_regresar.pack(pady=10)

    def busqueda_doctor():
        cedula = fp.getValue(1)

        if len(cedula) != 0:
            try:
                doctor = hospital.buscar_doctor(int(cedula))
                if doctor is not None:
                    elementos_doctor(doctor)
                else:
                    raise DatosFalsos
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

    titulo_ingreso_cedula = tk.Label(frame, text="Ingrese la cedula del doctor a eliminar:", bg="white",font=("Helvetica", 10, "bold"))
    titulo_ingreso_cedula.pack()

    criterios = ["Cedula"]
    fp = FieldFrame(frame, "", criterios, "", None, None)
    fp.pack()

    boton_buscar_doctor = tk.Button(frame, text="Buscar", command=busqueda_doctor)
    boton_buscar_doctor.pack(pady=5)

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
    boton_regresar.pack()