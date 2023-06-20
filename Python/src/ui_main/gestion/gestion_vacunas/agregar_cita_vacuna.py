import tkinter as tk
from tkinter import messagebox

from src.gestor_aplicacion.servicios.cita_vacuna import CitaVacuna
from src.manejo_errores.error_aplicacion import DatosFalsos, TipoIncorrecto, CampoVacio
from src.ui_main.gestion.field_frame import FieldFrame


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Agregar cita a vacuna", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)
def agregar_cita_vacuna(hospital, frame):

    def ver_citas_vacuna(vacuna):
        imprimir_titulo(frame)
        info_historial = tk.Label(frame, text=f"Agenda de la vacuna {vacuna.nombre} - Tipo: {vacuna.tipo}",
                                  bg="white", font=("Helvetica", 12))
        info_historial.pack(pady=10)

        agenda_vacuna_text = tk.Text(frame, bg="white", font=("Helvetica", 14))
        agenda_vacuna_text.pack(fill=tk.BOTH, expand=True)

        # Mostrar todas las vacunas que tiene el usuario


        for cita in vacuna.agenda:
            fecha = cita.fecha

            texto_vacuna = f"~~ {fecha}\n\n"

            # Insertar el texto de la vacuna en el widget Text
            agenda_vacuna_text.insert(tk.END, texto_vacuna)

        agenda_vacuna_text.config(padx=30, pady=5)
        agenda_vacuna_text.config(highlightthickness=5, highlightbackground="#4D5BE4")
        agenda_vacuna_text.config(state="disabled")

        # Se importa aca para evitar una referencia circular
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    def agregar_fecha_vacuna(vacuna):
        def confirmar_cita():
            respuesta = tk.messagebox.askyesno("Confirmar cita", "¿Estas seguro de agregar esta cita?")
            if respuesta:
                fecha = str(fp2.getValue(1))

                cita = CitaVacuna(fecha, None, vacuna)
                vacuna.agenda.append(cita)
                messagebox.showinfo("Cita agregada", "La cita se ha agregado exitosamente a esta vacuna")
                ver_citas_vacuna(vacuna)
            else:
                messagebox.showinfo("Cita cancelada", "La cita no ha sido agregada")
                # Se importa aca para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame)

        imprimir_titulo(frame)

        info_doctor = tk.Label(frame, text=f"{vacuna.nombre} - Tipo: {vacuna.tipo}", bg="white",font=("Helvetica", 12))
        info_doctor.pack(pady=10)

        criterios = ["Fecha de la cita (ejm: 25 de Junio, 10:00 am)"]
        fp2 = FieldFrame(frame, "", criterios, "", None, None,25)
        fp2.pack()

        boton_guardar = tk.Button(frame, text="Guardar", command=lambda: confirmar_cita())
        boton_guardar.pack(pady=5)

        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()
    def busqueda_vacuna():
        nombre = fp.getValue(1)

        if len(nombre) != 0:
            try:
                if nombre.isdigit():
                    raise ValueError
                else:
                    vacuna = hospital.buscar_vacuna(nombre)
                    if vacuna is not None:
                        agregar_fecha_vacuna(vacuna)
                    else:
                        raise DatosFalsos
            except ValueError:
                TipoIncorrecto().enviar_mensaje()
            except DatosFalsos as e:
                e.enviar_mensaje()

        else:
            try:
                raise CampoVacio()
            except CampoVacio as e:
                e.enviar_mensaje()

    imprimir_titulo(frame)
    titulo_ingreso_nombre = tk.Label(frame, text="Ingrese el nombre de la vacuna a ver:", bg="white",
                                     font=("Helvetica", 10, "bold"))
    titulo_ingreso_nombre.pack()

    criterios = ["Nombre"]
    fp = FieldFrame(frame, "", criterios, "", None, None)
    fp.pack()

    boton_buscar_vacuna = tk.Button(frame, text="Buscar", command=busqueda_vacuna, font=("Helvetica", 10, "bold"))
    boton_buscar_vacuna.pack(pady=5)

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",
                               command=lambda: implementacion_default(frame),font=("Helvetica", 10, "bold"))
    boton_regresar.pack(pady=20)