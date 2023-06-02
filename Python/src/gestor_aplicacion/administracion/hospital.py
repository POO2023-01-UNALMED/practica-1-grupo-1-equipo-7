class Hospital:

    _habitaciones = []

    def __init__(self):
        self._lista_pacientes = []
        self._lista_doctores = []
        self._lista_medicamentos = []
        self._lista_vacunas = []

    @property
    def lista_pacientes(self):
        return self._lista_pacientes

    @lista_pacientes.setter
    def lista_pacientes(self, lista):
        self._lista_pacientes = lista

    def buscar_paciente(self, cedula):
        for paciente in self._lista_pacientes:
            if paciente.cedula == cedula:
                return paciente
        return None
