from src.gestor_aplicacion.personas import persona

class Doctor(persona):
    def __init__(self,  cedula, nombre, tipo_eps, especialidad, agenda):
        super().__init__(cedula, nombre, tipo_eps)
        self._especialidad = especialidad
        self._agenda = agenda