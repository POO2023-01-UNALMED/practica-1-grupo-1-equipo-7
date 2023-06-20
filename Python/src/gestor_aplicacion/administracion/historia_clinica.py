# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo
from src.gestor_aplicacion.personas.enfermedad import Enfermedad

# Clase destinada a crear historias clínicas a pacientes

class HistoriaClinica:
    # Atributos y constructor
    def __init__(self, paciente):
        self._PACIENTE = paciente
        self._lista_formulas = []
        self._historial_citas = []
        self._historial_vacunas = []
        self._enfermedades = []
    # Metodos
    # Método que busca los doctores que estén disponibles y también los doctores con los que el paciente
    # asociado a esta historia haya tenido alguna cita. Recibe un string y un objeto de clase hospital.
    # Devuelve una cita
    def buscar_cita(self, especialidad, hospital):
        disponibles = hospital.buscar_tipo_doctor(especialidad)
        doc_cita = []
        for doc in disponibles:
            for cita in self._historial_citas:
                if doc.cedula == cita.doctor.cedula:
                    doc_cita.append(doc)
        return doc_cita
    # Recibe un objeto de la clase Formula y la agrega al atributo
    def agregar_formula(self, formula):
        self._lista_formulas.append(formula)

    # Recibe un objeto de la clase enfermedad y la agrega al atributo

    def agregar_enfermedad(self, enfermedad):
        self._enfermedades.append(enfermedad)
    @property
    def enfermedades(self):
        return self._enfermedades

    @enfermedades.setter
    def enfermedades(self, value):
        self._enfermedades = value

    @property
    def lista_formulas(self):
        return self._lista_formulas

    @property
    def historial_citas(self):
        return self._historial_citas

    @property
    def historial_vacunas(self):
        return self._historial_vacunas
