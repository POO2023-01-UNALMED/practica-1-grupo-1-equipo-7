from src.gestor_aplicacion.administracion.historia_clinica import HistoriaClinica
from src.gestor_aplicacion.personas.persona import Persona
from src.gestor_aplicacion.servicios.cita import Cita
from src.gestor_aplicacion.servicios.cita_vacuna import CitaVacuna
from src.gestor_aplicacion.servicios.formula import Formula
from src.gestor_aplicacion.servicios.habitacion import Habitacion


class Paciente(Persona):

    def __init__(self, cedula, nombre, tipo_eps):
        super().__init__(cedula, nombre, tipo_eps)
        self._habitacion_asignada = None
        self._HISTORIA_CLINICA = HistoriaClinica(self)

    def calcular_precio(self, servicio):
        if isinstance(servicio, Formula):
            # Implementacion
            pass
        elif isinstance(servicio, CitaVacuna):
            IVA = 0.19
            precio_total = servicio.vacuna.precio

            if servicio.vacuna.tipo == "Obligatoria":
                precio_total += 1000
            elif servicio.vacuna.tipo == "No obligatoria":
                precio_total += 3000

            tipo_eps = servicio.paciente.tipo_eps
            if tipo_eps == "Contributivo":
                precio_total += 2000
            elif tipo_eps == "Subsidiado":
                precio_total += 500
            elif tipo_eps == "Particular":
                precio_total += 10000

            precio_total *= (1 + IVA)
            return precio_total
        elif isinstance(servicio, Habitacion):
            # Implementacion
            pass
        elif isinstance(servicio, Cita):
            # Implementacion
            pass

    def med_enfermedad(self, enfermedad, hospital):
        meds = hospital.meds_disponibles()
        med_enfermedades = []
        for med in meds:
            if med.enfermedad().nombre() == enfermedad.nombre() and med.enfermedad().tipologia() == enfermedad.tipologia():
                med_enfermedades.append(med)
        return med_enfermedades

    def buscar_vacuna_por_eps(self, tipo, hospital):
        vacunas_por_tipo = hospital.buscar_tipo_vacuna(tipo)
        vacunas_disponibles = []

        for vacuna in vacunas_por_tipo:
            for eps in vacuna.tipo_eps:
                if eps == self.tipo_eps:
                    vacunas_disponibles.append(vacuna)
        return vacunas_disponibles

    def actualizar_historial_vacunas(self,cita_asignada):
        self.historia_clinica.historial_vacunas.append(cita_asignada)

    @property
    def habitacion_asignada(self):
        return self._habitacion_asignada

    @property
    def historia_clinica(self):
        return self._HISTORIA_CLINICA

    @habitacion_asignada.setter
    def habitacion_asignada(self, hab):
        self._habitacion_asignada = hab
