from tkinter import messagebox

from src.gestor_aplicacion.administracion.categoria_habitacion import Categoria_habitacion
from src.gestor_aplicacion.servicios.habitacion import Habitacion
from src.manejo_errores.error_aplicacion import DatoDuplicado
from src.ui_main.gestion.field_frame import FieldFrame
import tkinter as tk


def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Contruir Habitación", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def construir_habitacion(hospital, frame):

    def ver_habitacion(numero, categoria):
        imprimir_titulo(frame)

        info_hab = tk.Label(frame, text=f"Informacion de la habitación construida", bg="white", font=("Helvetica", 12))
        info_hab.pack(pady=10)

        frame_hab = tk.Frame(frame, bg="white")
        frame_hab.pack(padx=10, pady=10)

        label_num = tk.Label(frame_hab, text="Numero: ", bg="white", font=("Helvetica", 10, "bold"))
        label_num.grid(row=0, column=0, padx=10, pady=5, sticky="w")
        label_num_hab = tk.Label(frame_hab, text=numero, bg="white")
        label_num_hab.grid(row=0, column=1, padx=10, pady=5, sticky="w")

        label_cate = tk.Label(frame_hab, text="Categoria: ", bg="white", font=("Helvetica", 10, "bold"))
        label_cate.grid(row=1, column=0, padx=10, pady=5, sticky="w")
        label_cat_hab = tk.Label(frame_hab, text=categoria, bg="white")
        label_cat_hab.grid(row=1, column=1, padx=10, pady=5, sticky="w")

        label_ocup = tk.Label(frame_hab, text="Estado: ", bg="white", font=("Helvetica", 10, "bold"))
        label_ocup.grid(row=2, column=0, padx=10, pady=5, sticky="w")
        label_ocup_hab = tk.Label(frame_hab, text="Desocupada", bg="white")
        label_ocup_hab.grid(row=2, column=1, padx=10, pady=5, sticky="w")

        label_paciente = tk.Label(frame_hab, text="Disponibilidad: ", bg="white", font=("Helvetica", 10, "bold"))
        label_paciente.grid(row=3, column=0, padx=10, pady=5, sticky="w")
        label_pac_hab = tk.Label(frame_hab, text="Disponible para pacientes", bg="white")
        label_pac_hab.grid(row=3, column=1, padx=10, pady=5, sticky="w")

        from src.ui_main.ventana_principal import implementacion_default

        boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
        boton_regresar.pack()

    def agregar_a_habitaciones():
        respuesta = tk.messagebox.askyesno("Confirmacion de construcion", "¿Estas seguro de construir esta habitación?")

        if respuesta:
            numero = int(fp.getValue(1))
            categoria = str(fp.getValue(2))

            hay_error = False

            for i, habitacion in enumerate(hospital._habitaciones):
                if habitacion.numero == numero and habitacion.categoria.name ==categoria:
                    try:
                        raise DatoDuplicado
                    except DatoDuplicado as e:
                        hay_error = True
                        e.enviar_mensaje()

            if hay_error == False:
                if categoria == Categoria_habitacion.CAMILLA.name:
                    habitacion_nueva = Habitacion(numero, Categoria_habitacion.CAMILLA, False, None, 0)
                    hospital._habitaciones.append(habitacion_nueva)

                elif categoria == Categoria_habitacion.INDIVIDUAL.name:
                    habitacion_nueva = Habitacion(numero, Categoria_habitacion.INDIVIDUAL, False, None, 0)
                    hospital._habitaciones.append(habitacion_nueva)

                elif categoria == Categoria_habitacion.DOBLE.name:
                    habitacion_nueva = Habitacion(numero, Categoria_habitacion.DOBLE, False, None, 0)
                    hospital._habitaciones.append(habitacion_nueva)

                elif categoria == Categoria_habitacion.OBSERVACION.name:
                    habitacion_nueva = Habitacion(numero, Categoria_habitacion.OBSERVACION, False, None, 0)
                    hospital._habitaciones.append(habitacion_nueva)

                elif categoria == Categoria_habitacion.UCI.name:
                    habitacion_nueva = Habitacion(numero, Categoria_habitacion.UCI, False, None, 0)
                    hospital._habitaciones.append(habitacion_nueva)

                elif categoria == Categoria_habitacion.UCC.name:
                    habitacion_nueva = Habitacion(numero, Categoria_habitacion.UCC, False, None, 0)
                    hospital._habitaciones.append(habitacion_nueva)

                messagebox.showinfo("Habitación Construida", "La habitación se ha construido exitosamente")
                ver_habitacion(numero, categoria)

        else:
            messagebox.showinfo("Habitación no construida", "No se ha construido la habitación")
            # Se importa aca para evitar una referencia circular
            from src.ui_main.ventana_principal import implementacion_default
            implementacion_default(frame)

    def borrar_campos():
        for entry in fp.valores:
            entry.delete(0,tk.END)


    imprimir_titulo(frame)

    criterios = ["Numero", "Tipo de Categoria (CAMILLA, INDIVIDUAL, DOBLE, OBSERVACION, UCI o UCC)"]
    fp = FieldFrame(frame, "Criterio", criterios, "Valor", None, None)
    fp.pack()

    botones_frame = tk.Frame(frame, bg="white")
    botones_frame.pack()

    boton_guardar = tk.Button(botones_frame, text="Guardar", command=agregar_a_habitaciones)
    boton_guardar.grid(row=0, column=0, padx=10, pady=10, sticky="w")

    boton_borrar = tk.Button(botones_frame, text="Borrar", command=borrar_campos)
    boton_borrar.grid(row=0, column=1, padx=10, pady=10, sticky="w")

    # Funcionalidad para regresar a la ventana principal

    # Se importa aca para evitar una referencia circular
    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar",command=lambda: implementacion_default(frame))
    boton_regresar.pack()