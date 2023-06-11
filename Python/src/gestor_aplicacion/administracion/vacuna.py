from src.gestor_aplicacion.servicios.cita_vacuna import CitaVacuna


class Vacuna:
    def __init__(self, tipo, nombre, tipo_eps, precio):
        self._tipo = tipo
        self._nombre = nombre
        self._precio = precio
        self._tipo_eps = tipo_eps
        self._agenda = [
            CitaVacuna("3 de Abril, 8:00 am", None, self),
            CitaVacuna("3 de Abril, 11:00 am", None, self),
            CitaVacuna("4 de Abril, 3:00 pm", None, self),
            CitaVacuna("5 de Abril, 10:00 am", None, self)
        ]

    def mostrar_agenda_disponible(self):
        agenda_disponible = []
        for cita in self._agenda:
            if cita.paciente is None:
                agenda_disponible.append(cita)
        return agenda_disponible

    def actualizar_agenda(self, paciente_asignado, numero_cita, agenda_disponible):
        cita_asignada = None
        for cita in self._agenda:
            if cita.fecha == agenda_disponible[numero_cita - 1].fecha:
                cita.paciente(paciente_asignado)
                cita_asignada = cita
        return cita_asignada

    @property
    def tipo(self):
        return self._tipo

    @property
    def tipo_eps(self):
        return self._tipo_eps

    @property
    def nombre(self):
        return self._nombre

    @property
    def precio(self):
        return self._precio

    @property
    def agenda(self):
        return self._agenda
