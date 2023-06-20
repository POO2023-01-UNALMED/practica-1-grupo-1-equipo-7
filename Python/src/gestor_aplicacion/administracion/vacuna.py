# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo


from src.gestor_aplicacion.servicios.cita_vacuna import CitaVacuna
from src.manejo_errores.error_aplicacion import SinAgenda

# Clase destinada a los objetos vacuna
class Vacuna:

    # Atributos y constructor
    def __init__(self, tipo, nombre, tipo_eps, precio):
        self._tipo = tipo
        self._nombre = nombre
        self._precio = precio
        self._tipo_eps = tipo_eps
        self._agenda = [
            CitaVacuna("9 de Enero, 8:00 am", None, self),
            CitaVacuna("25 de Junio, 10:00 am", None, self),
            CitaVacuna("31 de Diciembre, 3:00 pm", None, self),
            CitaVacuna("24 de Diciembre, 6:00 am", None, self)
        ]

    # Metodos

    # Método que muestra las citas que no tienen paciente asignado de una vacuna
    # No recibe ningun parametro y devuelve una lista de objetos Cita_Vacuna
    def mostrar_agenda_disponible(self):
        agenda_disponible = []
        for cita in self._agenda:
            if cita.paciente is None:
                agenda_disponible.append(cita)
        if len(agenda_disponible) != 0:
            return agenda_disponible
        else:
            raise SinAgenda()

    # Este método asigna un paciente a una cita de una vacuna
    # recibe un objeto de Paciente, un entero y una lista de Cita_Vacuna
    def actualizar_agenda(self, paciente_asignado, numero_cita, agenda_disponible):
        cita_asignada = None
        for cita in self._agenda:
            if cita.fecha == agenda_disponible[numero_cita - 1].fecha:
                cita.paciente=paciente_asignado
                cita_asignada = cita
        return cita_asignada

    @property
    def tipo(self):
        return self._tipo

    @property
    def tipo_eps(self):
        return self._tipo_eps

    @property
    def nombre(self):
        return self._nombre

    @property
    def precio(self):
        return self._precio

    @property
    def agenda(self):
        return self._agenda
