# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo
from src.gestor_aplicacion.servicios.servicio import Servicio

# Clase que crea las formulas que se han generado para un paciente.
class Formula(Servicio):

    # Atributos y constructor
    def __init__(self, paciente):
        super().__init__(paciente)
        self._lista_meds = []
        self._doctor = None

    # Metodos

    # Metodo que imprime una descripcion del servicio
    def descripcion_servicio(self):
        return f"{self._id_servicio} - Formula prescrita por {self._doctor}"

    # Métodos que busca el estado de pago de una cita médica y lo cambia
    # Recibe un objeto de la clase Paciente y un entero
    def confirmar_pago(self, paciente, id_servicio):
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
