# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo

# Clase destinada para que hereden doctor y persona
class Persona:

    # Atributos y constructor
    def __init__(self, cedula, nombre, tipo_eps):
        self._cedula = cedula
        self._nombre = nombre
        self._tipo_eps=tipo_eps


    @property
    def cedula(self):
        return self._cedula

    @property
    def nombre(self):
        return self._nombre

    @nombre.setter
    def nombre(self, value):
        self._nombre = value

    @property
    def tipo_eps(self):
        return self._tipo_eps
