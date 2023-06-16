import os
import pickle

from src.gestor_aplicacion.administracion.vacuna import Vacuna
from src.gestor_aplicacion.personas.doctor import Doctor
from src.gestor_aplicacion.personas.enfermedad import Enfermedad
from src.gestor_aplicacion.personas.paciente import Paciente
from src.gestor_aplicacion.administracion.medicamento import Medicamento


class Hospital:
    _habitaciones = []

    def __init__(self):
        pac = Paciente(111, "Diego", "Particular")
        self._lista_pacientes = [pac, Paciente(123, "Bolillo", "Subsidiado")]
        enf = Enfermedad("Tos", "II", "General")
        pac.historia_clinica.enfermedades.append(enf)
        self._lista_doctores = [Doctor(222, "Andres", "Particular", "General"),
                                Doctor(333, "Camilo", "Particular", "General"),
                                Doctor(444, "Santiago", "Particular", "Oftalmologia")]
        self._lista_medicamentos = [Medicamento("Quita tos", enf, "ae", 10, 12000)]
        self._lista_vacunas = [Vacuna("Obligatoria", "Covid", ["Particular", "Subsidiado"], 5000),
                               Vacuna("Obligatoria", "Neumococo", ["Subsidiado"], 2000)]
        # self.deserializar()

    def buscar_paciente(self, cedula):
        for paciente in self._lista_pacientes:
            if paciente.cedula == cedula:
                return paciente
        return None

    def meds_disponibles(self):
        disponibles = []
        for med in self._lista_medicamentos:
            if med.cantidad > 0:
                disponibles.append(med)
        return disponibles

    def buscar_tipo_doctor(self, especialidad):
        disponibles = []
        for doc in self._lista_doctores:
            if doc.especialidad == especialidad:
                disponibles.append(doc)
        return disponibles

    def buscar_tipo_vacuna(self, tipo):
        disponibles = []
        for vacuna in self._lista_vacunas:
            if vacuna.tipo == tipo:
                disponibles.append(vacuna)
        return disponibles

    def serializar(self):
        with open(os.path.abspath("../base_datos/temp/registro_doctores.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._lista_doctores, file)
        with open(os.path.abspath("../base_datos/temp/registro_pacientes.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._lista_pacientes, file)
        with open(os.path.abspath("../base_datos/temp/registro_medicamentos.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._lista_medicamentos, file)
        with open(os.path.abspath("../base_datos/temp/registro_vacunas.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._lista_vacunas, file)
        with open(os.path.abspath("../base_datos/temp/registro_habitaciones.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._habitaciones, file)
        with open(os.path.abspath("../base_datos/temp/registro_enfermedades.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(Enfermedad._enfermedades_registradas, file)

    def deserializar(self):
        with open(os.path.abspath("../base_datos/temp/registro_doctores.pickle"), "rb") as file:
            self._lista_doctores = pickle.load(file)
        with open(os.path.abspath("../base_datos/temp/registro_pacientes.pickle"), "rb") as file:
            self._lista_pacientes = pickle.load(file)
        with open(os.path.abspath("../base_datos/temp/registro_medicamentos.pickle"), "rb") as file:
            self._lista_medicamentos = pickle.load(file)
        with open(os.path.abspath("../base_datos/temp/registro_vacunas.pickle"), "rb") as file:
            self._lista_vacunas = pickle.load(file)
        with open(os.path.abspath("../base_datos/temp/registro_habitaciones.pickle"), "rb") as file:
            self._habitaciones = pickle.load(file)
        with open(os.path.abspath("../base_datos/temp/registro_enfermedades.pickle"), "rb") as file:
            Enfermedad._enfermedades_registradas = pickle.load(file)

    @property
    def lista_pacientes(self):
        return self._lista_pacientes

    @lista_pacientes.setter
    def lista_pacientes(self, lista):
        self._lista_pacientes = lista

    @property
    def lista_medicamentos(self):
        return self._lista_medicamentos

    @lista_medicamentos.setter
    def lista_medicamentos(self, value):
        self._lista_medicamentos = value

    @property
    def lista_doctores(self):
        return self._lista_doctores

    @lista_doctores.setter
    def lista_doctores(self, value):
        self._lista_doctores = value
