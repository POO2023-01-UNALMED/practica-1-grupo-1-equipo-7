from tkinter import messagebox

from src.gestor_aplicacion.servicios.cita import Cita
from src.manejo_errores.error_aplicacion import DatosFalsos, CampoVacio, TipoIncorrecto
from src.ui_main.gestion.field_frame import FieldFrame
import tkinter as tk


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Agregar cita", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def agregar_cita(hospital, frame):
    def ver_citas_doctor(doctor):
        imprimir_titulo(frame)

        info_doctor = tk.Label(frame, text=f"Citas en la agenda de {doctor.nombre} - CC: {doctor.cedula}", bg="white", font=("Helvetica", 12))
        info_doctor.pack(pady=10)

        agenda_text = tk.Text(frame, bg="white", font=("Helvetica", 14))
        agenda_text.pack(fill=tk.BOTH, expand=True)

        for cita in doctor.agenda:
            fecha = cita.fecha

            texto_agenda = f"Fecha: {fecha}\n\n"

            agenda_text.insert(tk.END, texto_agenda)

        agenda_text.config(padx=30)
        agenda_text.config(highlightthickness=5, highlightbackground="#4D5BE4")
        agenda_text.config(state="disabled")

        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    def agregar_fecha_cita(doctor):
        def confirmar_cita():
            respuesta = tk.messagebox.askyesno("Confirmar cita", "¿Estas seguro de agregar esta cita?")
            if respuesta:
                fecha = str(fp2.getValue(1))

                cita = Cita(None, doctor, fecha)
                doctor.agenda.append(cita)
                messagebox.showinfo("Cita agregada", "La cita se ha agregado exitosamente")
                ver_citas_doctor(doctor)
            else:
                messagebox.showinfo("Cita cancelada", "La cita no ha sido agregada")
                # Se importa aca para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)

        imprimir_titulo(frame)

        info_doctor = tk.Label(frame, text=f"{doctor.nombre} - CC: {doctor.cedula}", bg="white",font=("Helvetica", 12))
        info_doctor.pack(pady=10)

        criterios = ["Fecha de la cita (ejm: 25 de Junio, 10:00 am)"]
        fp2 = FieldFrame(frame, "", criterios, "", None, None)
        fp2.pack()

        boton_guardar = tk.Button(frame, text="Guardar", command=lambda: confirmar_cita())
        boton_guardar.pack(pady=5)

        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()


    def busqueda_doctor():
        cedula = fp1.getValue(1)

        if len(cedula) != 0:
            try:
                doctor = hospital.buscar_doctor(int(cedula))
                if doctor is not None:
                    agregar_fecha_cita(doctor)
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

    titulo_ingreso_cedula = tk.Label(frame, text="Ingrese la cedula del doctor al que se le agregara la cita:", bg="white",font=("Helvetica", 10, "bold"))
    titulo_ingreso_cedula.pack()

    criterios = ["Cedula"]
    fp1 = FieldFrame(frame, "", criterios, "", None, None)
    fp1.pack()

    boton_buscar_doctor = tk.Button(frame, text="Buscar", command=busqueda_doctor)
    boton_buscar_doctor.pack(pady=5)

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
    boton_regresar.pack()