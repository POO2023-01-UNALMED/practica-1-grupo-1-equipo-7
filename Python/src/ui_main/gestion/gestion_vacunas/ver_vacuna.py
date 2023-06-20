import tkinter as tk


from src.manejo_errores.error_aplicacion import DatosFalsos, TipoIncorrecto, CampoVacio
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
        vacuna.tipo,cadena_tipo_eps,vacuna.precio],[False,False,False,False],34)

        fp.pack()

        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame_implementacion, text="Regresar",
                                   command=lambda: implementacion_default(frame_implementacion),font=("Helvetica", 10, "bold"))
        boton_regresar.pack(pady=20)


    def busqueda_vacuna():
        nombre = fp.getValue(1)

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
                               command=lambda: implementacion_default(frame_implementacion),font=("Helvetica", 10, "bold"))
    boton_regresar.pack(pady=20)
