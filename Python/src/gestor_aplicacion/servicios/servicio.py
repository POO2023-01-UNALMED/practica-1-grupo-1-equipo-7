from abc import ABC, abstractmethod


class Servicio(ABC):
    generadorID = 1

    def __init__(self, paciente):
        self._id_servicio = Servicio.generadorID
        Servicio.generadorID += 1
        self._paciente = paciente
        self._estado_pago = False

    @abstractmethod
    def descripcion_servicio(self):
        pass

    @abstractmethod
    def validar_pago(self, paciente, id_servicio):
        pass

    @property
    def id_servicio(self):
        return self._id_servicio

    @property
    def estado_pago(self):
        return self._estado_pago

    @estado_pago.setter
    def estado_pago(self, estado_pago):
        self._estado_pago = estado_pago

    @classmethod
    def obtener_servicios_sin_pagar(cls, paciente):
        historia_clinica_paciente = paciente.historia_clinica
        servicios_sin_pagar = []

        # Obtiene todos los servicios brindados al paciente
        servicios_sin_pagar.extend(historia_clinica_paciente.historial_citas)
        servicios_sin_pagar.extend(historia_clinica_paciente.lista_formulas)
        if paciente.habitacion_asignada is not None:
            servicios_sin_pagar.append(paciente.habitacion_asignada)
        servicios_sin_pagar.extend(historia_clinica_paciente.historial_vacunas)

        # Filtra los servicios pagados
        servicios_sin_pagar = [servicio for servicio in servicios_sin_pagar if not servicio.estado_pago]
        servicios_sin_pagar.sort(key=lambda servicio: servicio.id_servicio)

        return servicios_sin_pagar
