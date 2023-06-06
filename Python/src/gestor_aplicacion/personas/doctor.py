from src.gestor_aplicacion.personas import persona

class Doctor(persona):
    def __init__(self,  cedula, nombre, tipo_eps, especialidad, agenda):
        super().__init__(cedula, nombre, tipo_eps)
        self._especialidad = especialidad
        self._agenda = agenda

    @property
    def agenda(self):
        return self._agenda

    @agenda.setter
    def agenda(self, value):
        self._agenda = value

    @property
    def especialidad(self):
        return self._especialidad

    @especialidad.setter
    def especialidad(self, value):
        self._especialidad = value


