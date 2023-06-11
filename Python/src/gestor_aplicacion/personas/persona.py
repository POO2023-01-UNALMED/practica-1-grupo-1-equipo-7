class Persona:

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

    @property
    def tipo_eps(self):
        return self._tipo_eps
