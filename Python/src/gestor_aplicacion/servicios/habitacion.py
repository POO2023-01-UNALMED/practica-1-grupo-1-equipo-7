from src.gestor_aplicacion.servicios.servicio import Servicio


class Habitacion(Servicio):

    def __init__(self, paciente):
        super().__init__(paciente)

    def descripcion_servicio(self):
        return f"{self._id_servicio} - Habitacion <numero> ocupada <dias> dias"

    def validar_pago(self, paciente, id_servicio):
        if paciente.habitacion_asignada.id_servicio == id_servicio:
            paciente.habitacion_asignada.estado_pago = True
