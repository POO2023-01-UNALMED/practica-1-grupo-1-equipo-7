class Enfermedad:

    _enfermedades_registradas = []

    @property
    def enfermedades_registradas(self):
        return self._enfermedades_registradas

    @enfermedades_registradas.setter
    def enfermedades_registradas(self, lista):
        self._enfermedades_registradas = lista
