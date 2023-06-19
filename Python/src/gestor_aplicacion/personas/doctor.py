from src.gestor_aplicacion.personas import persona
from src.gestor_aplicacion.personas.persona import Persona
from src.gestor_aplicacion.servicios.cita import Cita
from src.manejo_errores.error_aplicacion import SinAgenda


class Doctor(Persona):
    def __init__(self,  cedula, nombre, tipo_eps, especialidad):
        super().__init__(cedula, nombre, tipo_eps)
        self._especialidad = especialidad
        self._agenda = [
            Cita(None, self, "3 de Abril, 8:00 am"),
            Cita(None, self, "4 de Abril, 3:00 pm"),
            Cita(None, self, "5 de Abril, 10:00 am")
        ]

    def mostrar_agenda_disponible(self):
        agenda_disponible = []
        for cita in self.agenda:
            if cita.paciente is None:
                agenda_disponible.append(cita)
        if len(agenda_disponible) != 0:
            return agenda_disponible
        else:
            raise SinAgenda()

    def actualizar_agenda(self, paciente_seleccionado, numero_cita, agenda_disponible):
        cita_seleccionada = None
        for cita in self.agenda:
            if cita.fecha == agenda_disponible[numero_cita-1].fecha:
                cita.paciente = paciente_seleccionado
                cita_seleccionada = cita
        return cita_seleccionada

    def __str__(self):
        return self._nombre + " Especialidad: " + self._especialidad
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


