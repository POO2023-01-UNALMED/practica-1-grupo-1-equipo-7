class Enfermedad:

    _enfermedades_registradas = []

    def __init__(self, especialidad, nombre, tipologia):
        self._especialidad = especialidad
        self._nombre = nombre
        self._tipologia = tipologia
        self._enfermos = 1
        Enfermedad._enfermedades_registradas.append(self)

    def nuevo_enfermo(self):
        self._enfermos += 1

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

    @property
    def enfermedades_registradas(self):
        return self._enfermedades_registradas

    @enfermedades_registradas.setter
    def enfermedades_registradas(self, lista):
        self._enfermedades_registradas = lista
