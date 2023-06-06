class Medicamento:
    def __int__(self, nombre, enfermedad, descripcion, cantidad, precio):
        self._nombre = nombre
        self._enfermedad = enfermedad
        self._descripcion = descripcion
        self._cantidad = cantidad
        self._precio = precio

    def eliminar_cantidad(self):
        self._cantidad -= 1