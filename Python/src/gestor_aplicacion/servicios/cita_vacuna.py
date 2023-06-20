# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo
from src.gestor_aplicacion.servicios.cita import Cita


# Clase destinada a las citas que son de vacunas
class CitaVacuna(Cita):

    # Atributos y constructor
    def __init__(self, fecha, paciente, vacuna):
        super().__init__(paciente, None, fecha)
        self._vacuna = vacuna

    # Metodos

    # Metodo que imprime una descripcion del servicio
    def descripcion_servicio(self):
        return f"{self._id_servicio} - Vacuna ({self.vacuna.nombre})"

    # Métodos que busca el estado de pago de una cita médica y lo cambia
    # Recibe un objeto de la clase Paciente y un entero
    def confirmar_pago(self, paciente, id_servicio):
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

