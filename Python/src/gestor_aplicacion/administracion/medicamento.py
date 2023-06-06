class Medicamento:
    def __int__(self, nombre, enfermedad, descripcion, cantidad, precio):
        self._nombre = nombre
        self._enfermedad = enfermedad
        self._descripcion = descripcion
        self._cantidad = cantidad
        self._precio = precio

    def eliminar_cantidad(self):
        self._cantidad -= 1

    @property
    def enfermedad(self):
        return self._enfermedad

    @enfermedad.setter
    def enfermedad(self, value):
        self._enfermedad = value

    @property
    def precio(self):
        return self._precio

    @precio.setter
    def precio(self, value):
        self._precio = value

    @property
    def descripcion(self):
        return self._descripcion

    @descripcion.setter
    def descripcion(self, value):
        self._descripcion = value

    @property
    def nombre(self):
        return self._nombre

    @nombre.setter
    def nombre(self, value):
        self._nombre = value

    @property
    def cantidad(self):
        return self._cantidad

    @cantidad.setter
    def cantidad(self, value):
        self._cantidad = value
