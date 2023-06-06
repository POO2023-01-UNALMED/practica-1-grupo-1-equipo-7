class Enfermedad:

    _enfermedades_registradas = []

    def __init__(self, especialidad, nombre, tipologia):
        self._especialidad = especialidad
        self._nombre = nombre
        self._tipologia = tipologia
        self._enfermos = 1
        self._enfermedades_registradas.append(self)

    def nuevo_enfermo(self):
        self._enfermos += 1


    @property
    def enfermedades_registradas(self):
        return self._enfermedades_registradas

    @enfermedades_registradas.setter
    def enfermedades_registradas(self, lista):
        self._enfermedades_registradas = lista
