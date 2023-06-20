# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo

# Clase que crea enfermedades, las cuales son usadas para recomendarle al paciente
# distintos servicios que estén relacionados con su enfermedad
class Enfermedad:

    _enfermedades_registradas = [] # Atributo de clase

    # Atributos y constructor
    def __init__(self, nombre, tipologia, especialidad):
        self._especialidad = especialidad
        self._nombre = nombre
        self._tipologia = tipologia
        self._enfermos = 1
        Enfermedad._enfermedades_registradas.append(self)

    # Metodos

    # Aumenta en el atributo enfermos en una unidad
    def nuevo_enfermo(self):
        self._enfermos += 1

    def __str__(self):
        return f"{self._nombre} {self._tipologia}"

    @property
    def tipologia(self):
        return self._tipologia

    @tipologia.setter
    def tipologia(self, value):
        self._tipologia = value

    @property
    def enfermos(self):
        return self._enfermos

    @enfermos.setter
    def enfermos(self, value):
        self._enfermos = value

    @property
    def nombre(self):
        return self._nombre

    @nombre.setter
    def nombre(self, value):
        self._nombre = value

    @property
    def especialidad(self):
        return self._especialidad

    @especialidad.setter
    def especialidad(self, value):
        self._especialidad = value

    @classmethod
    def getEnfermedadesRegistradas(cls):
        return cls._enfermedades_registradas

