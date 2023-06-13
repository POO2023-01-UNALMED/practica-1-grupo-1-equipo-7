import tkinter as tk

from src.gestor_aplicacion.administracion.hospital import Hospital
from src.ui_main.ventana_principal import ventana_principal


def ventana_inicial(hospital):
    ventana = tk.Tk()
    ventana.title("MedPlus")
    ventana.geometry("400x300")
    ventana.columnconfigure(0, weight=1)
    ventana.columnconfigure(1, weight=1)
    ventana.grid_columnconfigure(0, weight=1, minsize=ventana.winfo_width() // 2)
    ventana.grid_columnconfigure(1, weight=1, minsize=ventana.winfo_width() // 2)
    # ventana.protocol("WM_DELETE_WINDOW", hospital.serializar())

    # Frame P1
    p1 = tk.Frame(ventana)
    p1.grid(row=0, column=0, sticky="nsew")
    p1.columnconfigure(0, weight=1)
    p1.rowconfigure(0, weight=1)

    # Frame P2
    p2 = tk.Frame(ventana)
    p2.grid(row=0, column=1, sticky="nsew")
    p2.columnconfigure(0, weight=1)
    p2.rowconfigure(0, weight=1)

    # Frame P3: Saludo de bienvenida
    p3 = tk.Frame(p1)
    p3.pack(expand=True, fill="both")

    label_saludo = tk.Label(p3, text="Bienvenidos a MedPlus")
    label_saludo.pack(fill="both")

    # Frame P4: Fotos e ingresar a la aplicacion
    p4 = tk.Frame(p1)
    p4.pack(expand=True, fill="both")

    # Botón de ingresar a la aplicación
    boton_ingresar = tk.Button(p4, text="Ingresar a la aplicación",
                               command=lambda: [ventana.destroy(), ventana_principal(hospital)])
    boton_ingresar.pack(side="bottom")

    # Hoja de Vida
    # Frame P5: Descripcion
    p5 = tk.Frame(p2)
    p5.pack(expand=True, fill="both")
    label_saludo = tk.Label(p5, text="Descripcion hoja de vida")
    label_saludo.pack(fill="both")

    # Frame P6: Fotos
    p6 = tk.Frame(p2)
    p6.pack(expand=True, fill="both")

    ventana.mainloop()


if __name__ == '__main__':
    medplus = Hospital()
    ventana_inicial(medplus)
