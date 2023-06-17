from tkinter import Frame,Label,Entry

class FieldFrame(Frame):
    """
    tituloCriterios: Se coloca el título de la primera columna (Los criterios).
    criterios: Esto es una lista con el nombre de los criterios.
    tituloValores: Se coloca el título de los valores,la segunda columna.
    valores: Es una lista, Se usa para darle un valor por default a un criterio, si no
    quiere que tengan valores por default basta con poner None
    habilitado: es una lista de True o False, si se usa False el campo en esa posicion va a estar deshablitado,
    si se quiere que todos estén hablitados, entonces se usa None. (Recuerde incluir los valores en []
    Ejemplo: [False]).
    """

    def __init__(self, frame,tituloCriterios, criterios, tituloValores, valores, habilitado):
        super().__init__(frame,bg="white")

        self.valores=[]

        #Etiquetas para los títulos de las columnas
        Label(self, text=tituloCriterios,bg="white",font=("Helvetica", 12, "bold")).grid(row=0, column=0)
        Label(self, text=tituloValores,bg="white",font=("Helvetica", 12, "bold")).grid(row=0, column=1)

        # Etiquetas y campos de entrada para cada criterio
        for i, criterio in enumerate(criterios, start=1):
            Label(self, text=criterio,bg="white", font=("Helvetica", 10, "bold")).grid(row=i, column=0, padx=20, pady=5, sticky="w")
            entry = Entry(self)
            entry.grid(row=i, column=1, padx=5, pady=5, sticky="w")
            #
            if habilitado is not None and not habilitado[i - 1]:
                entry.config(state='readonly')  # Campo no editable
            # Se inserta los valores por defecto que queramos
            if valores is not None:
                #el número 0 indica que se inserta desde el inicio del string
                entry.insert(0, valores[i - 1])

            #Se guarda la referencia de ese entry
            self.valores.append(entry)

    def getValue(self, criterio):
        return self.valores[criterio-1].get()
