# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo

from src.gestor_aplicacion.servicios.servicio import Servicio


# Clase destinada a crear citas médicas
class Cita(Servicio):

    # Atributos y constructor
    def __init__(self, paciente, doctor, fecha):
        super().__init__(paciente)
        self._doctor = doctor
        self._fecha = fecha

    # Metodos

    # Metodo que imprime una descripcion del servicio
    def descripcion_servicio(self):
        return f"{self._id_servicio} - Cita medica con {self._doctor.nombre} ({self._fecha}))"

    # Métodos que busca el estado de pago de una cita médica y lo cambia
    # Recibe un objeto de la clase Paciente y un entero
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
