from Python.src.gestor_aplicacion.administracion.historia_clinica import HistoriaClinica
from Python.src.gestor_aplicacion.personas.persona import Persona
from Python.src.gestor_aplicacion.servicios.cita import Cita
from Python.src.gestor_aplicacion.servicios.cita_vacuna import CitaVacuna
from Python.src.gestor_aplicacion.servicios.formula import Formula
from Python.src.gestor_aplicacion.servicios.habitacion import Habitacion


class Paciente(Persona):

    def __init__(self, cedula, nombre):
        super().__init__(cedula, nombre)
        self._habitacion_asignada = None
        self._HISTORIA_CLINICA = HistoriaClinica(self)

    def calcular_precio(self, servicio):
        if isinstance(servicio, Formula):
            # Implementacion
            pass
        elif isinstance(servicio, CitaVacuna):
            # Implementacion
            pass
        elif isinstance(servicio, Habitacion):
            # Implementacion
            pass
        elif isinstance(servicio, Cita):
            # Implementacion
            pass

    @property
    def habitacion_asignada(self):
        return self._habitacion_asignada

    @property
    def historia_clinica(self):
        return self._HISTORIA_CLINICA

    @habitacion_asignada.setter
    def habitacion_asignada(self, hab):
        self._habitacion_asignada = hab
