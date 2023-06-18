from src.gestor_aplicacion.servicios.cita import Cita


class CitaVacuna(Cita):

    def __init__(self, fecha, paciente, vacuna):
        super().__init__(paciente, None, fecha)
        self._vacuna = vacuna

    def descripcion_servicio(self):
        return f"{self._id_servicio} - Vacuna ({self.vacuna.nombre})"

    def validar_pago(self, paciente, id_servicio):
        for cita_vacuna in paciente.historia_clinica.historial_vacunas:
            if cita_vacuna.id_servicio == id_servicio:
                cita_vacuna.estado_pago = True
                break

    @property
    def vacuna(self):
        return self._vacuna

    @vacuna.setter
    def vacuna(self, vacuna):
        self._vacuna = vacuna

