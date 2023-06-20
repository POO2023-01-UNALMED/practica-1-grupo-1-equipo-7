# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo

# Clase usada en FormulaMedica, practicamente es el pilar de esta funcionalidad porque son los
# medicamentos que se eligen allí
class Medicamento:
    # Atributos y constructor
    def __init__(self, nombre, enfermedad, descripcion, cantidad, precio):
        self._nombre = nombre
        self._enfermedad = enfermedad
        self._descripcion = descripcion
        self._cantidad = cantidad
        self._precio = precio
    # Metodos

    # Elimina una unidad la cantidad del medicamento
    def eliminar_cantidad(self):
        self._cantidad -= 1

    # Muestra la informacion del medicamento sin la cantidad
    def mostrar_info(self):
        return f"Nombre: {self._nombre} | Trata la enfermedad: {self.enfermedad} | Descripcion: {self._descripcion} | Precio: {self._precio}"

    def __str__(self):
        return f"Nombre: {self._nombre} | Trata la enfermedad: {self.enfermedad} | Descripcion: {self._descripcion} | Precio: {self._precio} | Cantidad: {self._cantidad}"

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
