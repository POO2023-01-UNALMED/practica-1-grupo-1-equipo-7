# Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
# y Santiago Arboleda Acevedo
import os
import pickle

from src.gestor_aplicacion.administracion.categoria_habitacion import Categoria_habitacion
from src.gestor_aplicacion.personas.enfermedad import Enfermedad
from src.gestor_aplicacion.servicios.habitacion import Habitacion
from src.manejo_errores.error_aplicacion import DatosFalsos


# Clase destinada a crear el hospital
class Hospital:
    _habitaciones = [Habitacion(1, Categoria_habitacion.CAMILLA, False, None, 0)]  # atributo de clase

    # demas atributos y constructor
    def __init__(self):
        self._lista_pacientes = []
        self._lista_doctores = []
        self._lista_medicamentos = []
        self._lista_vacunas = []
        self.deserializar()

    # Metodos

    # Metodo que recibe un entero y busca el paciente en la lista de hospital y devuelve un objeto de clase Paciente
    def buscar_paciente(self, cedula):
        for paciente in self._lista_pacientes:
            if paciente.cedula == cedula:
                return paciente
        raise DatosFalsos()

    # Metodo que recibe un entero y busca el doctor en la lista de hospital y devuelve un objeto de clase Doctor

    def buscar_doctor(self, cedula):
        for doctor in self._lista_doctores:
            if doctor.cedula == cedula:
                return doctor
        return None

    # Metodo no recibe nada y busca los medicamentos
    # disponibles en la lista de hospital y devuelve una lista de objetos de Medicamento

    def meds_disponibles(self):
        disponibles = []
        for med in self._lista_medicamentos:
            if med.cantidad > 0:
                disponibles.append(med)
        return disponibles

    # Metodo que recibe un string y busca el doctor en la lista de hospital
    # con ese string y devuelve un objeto de clase Doctor
    def buscar_tipo_doctor(self, especialidad):
        disponibles = []
        for doc in self._lista_doctores:
            if doc.especialidad == especialidad:
                disponibles.append(doc)
        return disponibles

    # Metodo que recibe un string y busca vacunas disponibles por tipo
    # en la lista de hospital y devuelve una lista de objetos Vacuna
    def buscar_tipo_vacuna(self, tipo):
        disponibles = []
        for vacuna in self._lista_vacunas:
            if vacuna.tipo == tipo:
                disponibles.append(vacuna)
        return disponibles

    # Metodo que recibe un string y busca una vacuna por nombre
    # en la lista de hospital y devuelve un un objeto de vacuna
    def buscar_vacuna(self, nombre):
        vacuna_seleccionada = None
        for vacuna in self._lista_vacunas:
            if vacuna.nombre == nombre:
                vacuna_seleccionada = vacuna
        return vacuna_seleccionada

    def serializar(self):
        with open(os.path.abspath("src/base_datos/temp/registro_doctores.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._lista_doctores, file)
        with open(os.path.abspath("src/base_datos/temp/registro_pacientes.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._lista_pacientes, file)
        with open(os.path.abspath("src/base_datos/temp/registro_medicamentos.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._lista_medicamentos, file)
        with open(os.path.abspath("src/base_datos/temp/registro_vacunas.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._lista_vacunas, file)
        with open(os.path.abspath("src/base_datos/temp/registro_habitaciones.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(self._habitaciones, file)
        with open(os.path.abspath("src/base_datos/temp/registro_enfermedades.pickle"), "wb") as file:
            file.truncate()
            pickle.dump(Enfermedad._enfermedades_registradas, file)

    def deserializar(self):
        with open(os.path.abspath("src/base_datos/temp/registro_doctores.pickle"), "rb") as file:
            self._lista_doctores = pickle.load(file)
        with open(os.path.abspath("src/base_datos/temp/registro_pacientes.pickle"), "rb") as file:
            self._lista_pacientes = pickle.load(file)
        with open(os.path.abspath("src/base_datos/temp/registro_medicamentos.pickle"), "rb") as file:
            self._lista_medicamentos = pickle.load(file)
        with open(os.path.abspath("src/base_datos/temp/registro_vacunas.pickle"), "rb") as file:
            self._lista_vacunas = pickle.load(file)
        with open(os.path.abspath("src/base_datos/temp/registro_habitaciones.pickle"), "rb") as file:
            self._habitaciones = pickle.load(file)
        with open(os.path.abspath("src/base_datos/temp/registro_enfermedades.pickle"), "rb") as file:
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

    @property
    def lista_vacunas(self):
        return self._lista_vacunas

    @lista_vacunas.setter
    def lista_vacunas(self, value):
        self._lista_vacunas = value

    @classmethod
    def get_habitaciones(cls):
        return cls._habitaciones

    @classmethod
    def set_habitaciones(cls, habitaciones):
        cls._habitaciones = habitaciones
