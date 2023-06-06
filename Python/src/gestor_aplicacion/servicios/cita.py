from src.gestor_aplicacion.servicios.servicio import Servicio


class Cita(Servicio):

    def __init__(self, paciente):
        super().__init__(paciente)

    def descripcion_servicio(self):
        return f"{self._id_servicio} - Cita medica con <doctor> (<fecha>))"

    def validar_pago(self, paciente, id_servicio):
        for cita in paciente.historia_clinica.historial_citas:
            if cita.id_servicio == id_servicio:
                cita.estado_pago = True
                break
