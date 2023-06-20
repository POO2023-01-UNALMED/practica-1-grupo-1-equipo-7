# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo
from src.gestor_aplicacion.administracion.categoria_habitacion import *
from src.gestor_aplicacion.servicios.servicio import Servicio


class Habitacion(Servicio):

    # Atributos y constructor
    def __init__(self, numero, categoria, ocupada, paciente, dias):
        super().__init__(paciente)
        self.numero = numero
        self.categoria = categoria
        self.ocupada = ocupada
        self.dias = dias

    # Metodos

    # Se crea el metodo estatico, el cual se encarga de filtrar. Recibe un string y
    # retorna un ArrayList de las habitaciones vacias de la categoria que se selecciono
    @classmethod
    def buscar_habitacion_disponible(cls, categoria):
        from src.gestor_aplicacion.administracion.hospital import Hospital
        habitaciones_disponibles = []
        for habitacion in Hospital.get_habitaciones():
            if not habitacion.ocupada and habitacion.categoria.name == categoria:
                habitaciones_disponibles.append(habitacion)
        if habitaciones_disponibles:
            return habitaciones_disponibles
        return None

    # Este metodo se encarga de cambiar la categoria a una inferior. Recibe un string y
    # retorna un ArrayList de las habitaciones vacias de la categoria que se selecciono
    @classmethod
    def buscar_otra_categoria(cls, categoria):
        if categoria == Categoria_habitacion.UCC.name:
            return Categoria_habitacion.UCI.name
        elif categoria == Categoria_habitacion.UCI.name:
            return Categoria_habitacion.OBSERVACION.name
        elif categoria == Categoria_habitacion.OBSERVACION.name:
            return Categoria_habitacion.DOBLE.name
        elif categoria == Categoria_habitacion.DOBLE.name:
            return Categoria_habitacion.INDIVIDUAL.name
        elif categoria == Categoria_habitacion.INDIVIDUAL.name:
            return Categoria_habitacion.CAMILLA.name
        else:
            return None

    @property
    def paciente(self):
        return self._paciente

    @paciente.setter
    def paciente(self, paciente):
        self._paciente = paciente
    @property
    def numero(self):
        return self._numero

    @numero.setter
    def numero(self, numero):
        self._numero = numero

    @property
    def categoria(self):
        return self._categoria

    @categoria.setter
    def categoria(self, categoria):
        self._categoria = categoria

    @property
    def ocupada(self):
        return self._ocupada

    @ocupada.setter
    def ocupada(self, ocupada):
        self._ocupada = ocupada

    @property
    def dias(self):
        return self._dias

    @dias.setter
    def dias(self, dias):
        self._dias = dias

    def descripcion_servicio(self):
        return f"{self._id_servicio} - Habitacion {self.numero} ocupada {self.dias} dias"

    def confirmar_pago(self, paciente, id_servicio):
        if paciente.habitacion_asignada.id_servicio == id_servicio:
            paciente.habitacion_asignada.estado_pago = True

