from tkinter import messagebox

from src.manejo_errores.error_aplicacion import TipoIncorrecto, DatosFalsos, CampoVacio
from src.ui_main.gestion.field_frame import FieldFrame
import tkinter as tk
def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Eliminar vacuna", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def eliminar_vacuna(hospital,frame):

    def eliminacion_vacuna(vacuna):
        respuesta = tk.messagebox.askyesno("Confirmar eliminacion", "¿Estas seguro de eliminar esta vacuna?")
        if respuesta:
            hospital.lista_vacunas.remove(vacuna)
            messagebox.showinfo("Vacuna eliminada", "La vacuna se ha eliminado exitosamente")
            from src.ui_main.ventana_principal import implementacion_default
            implementacion_default(frame)
        else:
            messagebox.showinfo("Eliminacion cancelada", "La vacuna no ha sido eliminada")
            # Se importa aca para evitar una referencia circular
            from src.ui_main.ventana_principal import implementacion_default
            implementacion_default(frame)

    def elementos_vacuna(vacuna):
        imprimir_titulo(frame)
        info_vacuna = tk.Label(frame, text=f"Información de la vacuna", bg="white", font=("Helvetica", 12))
        info_vacuna.pack(pady=10)
        criterios = ["Nombre de la vacuna", "Tipo de vacuna",
                     "Eps a las que pertenece",
                     "Precio"]
        cadena_tipo_eps = ",".join(vacuna.tipo_eps)

        fp = FieldFrame(frame, "Criterio", criterios, "Valor", [vacuna.nombre,vacuna.tipo,
        cadena_tipo_eps,vacuna.precio],[False, False, False, False], 34)

        fp.pack()

        #Boton de eliminar

        boton_eliminar = tk.Button(frame, text="Eliminar", command=lambda: eliminacion_vacuna(vacuna),font=("Helvetica", 10, "bold"))
        boton_eliminar.pack(pady=10)

        #Boton de regresar
        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar",
                                   command=lambda: implementacion_default(frame),
                                   font=("Helvetica", 10, "bold"))
        boton_regresar.pack(pady=20)



    def busqueda_vacuna():
        nombre= fp.getValue(1)

        if len(nombre) != 0:
            try:
                if nombre.isdigit():
                    raise ValueError
                else:
                    vacuna = hospital.buscar_vacuna(nombre)
                    if vacuna is not None:
                        elementos_vacuna(vacuna)
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

    titulo_ingreso_cedula = tk.Label(frame, text="Ingrese el nombre de la vacuna a eliminar:", bg="white",
                                     font=("Helvetica", 10, "bold"))
    titulo_ingreso_cedula.pack()

    criterios = ["Nombre"]
    fp = FieldFrame(frame, "", criterios, "", None, None)
    fp.pack()

    boton_buscar_doctor = tk.Button(frame, text="Buscar", command=busqueda_vacuna,font=("Helvetica", 10, "bold"))
    boton_buscar_doctor.pack(pady=5)

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame),font=("Helvetica", 10, "bold"))
    boton_regresar.pack()