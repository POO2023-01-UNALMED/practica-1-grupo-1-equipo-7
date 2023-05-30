from Python.src.gestor_aplicacion.servicios.servicio import Servicio


class Formula(Servicio):

    def __init__(self, paciente):
        super().__init__(paciente)

    def descripcion_servicio(self):
        return f"{self._id_servicio} - Formula prescrita por <doctor>"

    def validar_pago(self, paciente, id_servicio):
        for formula in paciente.historia_clinica.lista_formulas:
            if formula.id_servicio == id_servicio:
                formula.estado_pago = True
                break
