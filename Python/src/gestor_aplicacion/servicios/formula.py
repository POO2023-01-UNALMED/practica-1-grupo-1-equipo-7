from src.gestor_aplicacion.servicios.servicio import Servicio


class Formula(Servicio):

    def __init__(self, paciente, doctor):
        super().__init__(paciente)
        self._lista_meds = []
        self._doctor = doctor

    def descripcion_servicio(self):
        return f"{self._id_servicio} - Formula prescrita por <doctor>"

    def validar_pago(self, paciente, id_servicio):
        for formula in paciente.historia_clinica.lista_formulas:
            if formula.id_servicio == id_servicio:
                formula.estado_pago = True
                break

    @property
    def doctor(self):
        return self._doctor

    @doctor.setter
    def doctor(self, value):
        self._doctor = value

    @property
    def lista_meds(self):
        return self._lista_meds

    @lista_meds.setter
    def lista_meds(self, value):
        self._lista_meds = value
