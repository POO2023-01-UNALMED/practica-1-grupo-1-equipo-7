import tkinter as tk
from tkinter import messagebox

from src.ui_main.gestion.field_frame import FieldFrame


def imprimir_titulo(frame_implementacion):
    # Limpia el frame
    for item in frame_implementacion.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame_implementacion, text="Ver una vacuna", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)
def ver_vacuna(hospital,frame_implementacion):

    def elementos_vacuna(vacuna):
        imprimir_titulo(frame_implementacion)
        criterios = ["Nombre de la vacuna", "Tipo de vacuna",
                     "Eps a las que pertenece",
                     "Precio"]
        cadena_tipo_eps=",".join(vacuna.tipo_eps)

        fp = FieldFrame(frame_implementacion, "Criterio", criterios, "Valor",[vacuna.nombre,
        vacuna.tipo,cadena_tipo_eps,vacuna.precio],[False,False,False,False])

        fp.pack()

        print(vacuna.nombre)

        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame_implementacion, text="Regresar",
                                   command=lambda: implementacion_default(frame_implementacion))
        boton_regresar.pack(pady=20)


    def busqueda_vacuna():
        nombre= fp.getValue(1)
        vacuna= hospital.buscar_vacuna(nombre)

        if vacuna is not None:
            elementos_vacuna(vacuna)
        else:
            respuesta = tk.messagebox.askyesno("Error", "No existe una vacuna registrada con ese nombre. "
                                                        "¿Desea intentar de nuevo?")
            if not respuesta:
                # Se importa aca para evitar una referencia circular
                from src.ui_main.ventana_principal import implementacion_default
                implementacion_default(frame_implementacion)

    imprimir_titulo(frame_implementacion)
    titulo_ingreso_nombre = tk.Label(frame_implementacion, text="Ingrese el nombre de la vacuna a ver:", bg="white",
                                     font=("Helvetica", 10, "bold"))
    titulo_ingreso_nombre.pack()

    criterios = ["Nombre"]
    fp = FieldFrame(frame_implementacion, "", criterios, "", None, None)
    fp.pack()

    boton_buscar_vacuna = tk.Button(frame_implementacion, text="Buscar", command=busqueda_vacuna, font=("Helvetica", 10, "bold"))
    boton_buscar_vacuna.pack(pady=5)

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame_implementacion, text="Regresar",
                               command=lambda: implementacion_default(frame_implementacion))
    boton_regresar.pack(pady=20)
