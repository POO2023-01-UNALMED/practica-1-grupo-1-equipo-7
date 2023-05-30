class HistoriaClinica:

    def __init__(self, paciente):
        self._PACIENTE = paciente
        self._lista_formulas = []
        self._historial_citas = []
        self._historial_vacunas = []

    @property
    def lista_formulas(self):
        return self._lista_formulas

    @property
    def historial_citas(self):
        return self._historial_citas

    @property
    def historial_vacunas(self):
        return self._historial_vacunas
