from src.gestor_aplicacion.servicios.servicio import Servicio


class Cita(Servicio):

    def __init__(self, paciente, doctor, fecha):
        super().__init__(paciente)
        self._doctor = doctor
        self._fecha = fecha

    def descripcion_servicio(self):
        return f"{self._id_servicio} - Cita medica con {self._doctor.nombre} ({self._fecha}))"

    def confirmar_pago(self, paciente, id_servicio):
        for cita in paciente.historia_clinica.historial_citas:
            if cita.id_servicio == id_servicio:
                cita.estado_pago = True
                break

    @property
    def doctor(self):
        return self._doctor

    @doctor.setter
    def doctor(self, value):
        self._doctor = value

    @property
    def fecha(self):
        return self._fecha

    @fecha.setter
    def fecha(self, value):
        self._fecha = value
