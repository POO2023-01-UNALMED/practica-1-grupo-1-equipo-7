from tkinter import messagebox

from src.manejo_errores.error_aplicacion import DatosFalsos, TipoIncorrecto, CampoVacio
from src.ui_main.gestion.field_frame import FieldFrame
import tkinter as tk

def imprimir_titulo(frame):
    # Limpia el frame
    for item in frame.winfo_children():
        item.destroy()

    # Imprime el titulo
    titulo = tk.Label(frame, text="Destruir habitacion", bg="white", font=("Helvetica", 16, "bold"))
    titulo.pack(pady=20)

def destruir_habitacion(hospital, frame):

    def destrucion_habitacion(habitacion):
        respuesta = tk.messagebox.askyesno("Confirmar destrucción", "¿Estas seguro de destruir esta habitacion?")
        if respuesta:
            hospital._habitaciones.remove(habitacion)
            messagebox.showinfo("Habitación Destruida", "La habitación se ha destruido exitosamente")
            from src.ui_main.ventana_principal import implementacion_default
            implementacion_default(frame)
        else:
            messagebox.showinfo("Destrución cancelada", "La habitacion no ha sido eliminado")
            # Se importa aca para evitar una referencia circular
            from src.ui_main.ventana_principal import implementacion_default
            implementacion_default(frame)

    def elementos_habitacion(habitacion):
        imprimir_titulo(frame)
        info_doctor = tk.Label(frame, text=f"Información de la Habitacion", bg="white", font=("Helvetica", 12))
        info_doctor.pack(pady=10)

        criterios = ["Numero", "Tipo de Categoria (CAMILLA, INDIVIDUAL, DOBLE, OBSERVACION, UCI o UCC)","Estado","Paciente Asignado", "Disas Establecido el Paciente"]
        if habitacion.ocupada==False:
            fp = FieldFrame(frame, "Criterio", criterios, "Valor", [habitacion.numero, habitacion.categoria.name,"Desocupada","Libre", habitacion.dias],[False, False, False, False, False])
            fp.pack()
            boton_eliminar = tk.Button(frame, text="Eliminar", command=lambda: destrucion_habitacion(habitacion))
            boton_eliminar.pack(pady=10)
        else:
            fp = FieldFrame(frame, "Criterio", criterios, "Valor",[habitacion.numero, habitacion.categoria.name, "Ocupada", habitacion.paciente.nombre,habitacion.dias], [False, False, False, False, False])
            fp.pack()



        from src.ui_main.ventana_principal import implementacion_default
        boton_regresar = tk.Button(frame, text="Regresar",command=lambda: implementacion_default(frame))
        boton_regresar.pack(pady=10)

    def busqueda_habitacion():
        numero = fp.getValue(1)
        categoria = fp.getValue(2)

        if len(numero) != 0 and categoria != "":
            for i, habitacion in enumerate(hospital._habitaciones):
                if habitacion.numero == int(numero) and habitacion.categoria.name == categoria:
                    if habitacion:
                        elementos_habitacion(habitacion)
                    else:
                        try:
                            raise DatosFalsos
                        except DatosFalsos as e:
                            e.enviar_mensaje()
        else:
            try:
                raise CampoVacio()
            except CampoVacio as e:
                e.enviar_mensaje()

    imprimir_titulo(frame)

    titulo_ingreso_numero = tk.Label(frame, text="Ingrese el numero y categoria de la habitacion a destruir:", bg="white",font=("Helvetica", 10, "bold"))
    titulo_ingreso_numero.pack()


    criterios = ["Numero", "Tipo de Categoria (CAMILLA, INDIVIDUAL, DOBLE, OBSERVACION, UCI o UCC)"]
    fp = FieldFrame(frame, "", criterios, "", None, None)
    fp.pack()

    boton_buscar_doctor = tk.Button(frame, text="Buscar", command=busqueda_habitacion)
    boton_buscar_doctor.pack(pady=5)

    from src.ui_main.ventana_principal import implementacion_default

    boton_regresar = tk.Button(frame, text="Regresar", command=lambda: implementacion_default(frame))
    boton_regresar.pack()