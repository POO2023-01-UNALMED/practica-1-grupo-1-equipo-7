from tkinter import messagebox


class ErrorAplicacion(Exception):
    def __init__(self, mensaje):
        self._mensaje = f"Manejo de errores de la Aplicación\n{mensaje}"
        super().__init__(self._mensaje)

    def enviar_mensaje(self):
        messagebox.showerror("Error", self._mensaje)


class IngresoErroneo(ErrorAplicacion):
    def __init__(self, mensaje):
        self._mensaje = mensaje
        super().__init__(f"Ingreso erroneo: {self._mensaje}")


class ErrorAdministrativo(ErrorAplicacion):
    def __init__(self, mensaje):
        self._mensaje = mensaje
        super().__init__(f"Error administrativo: {self._mensaje}")


class DatosFalsos(IngresoErroneo):
    def __init__(self):
        super().__init__("No se encuentra el dato que ingreso")


class TipoIncorrecto(IngresoErroneo):
    def __init__(self,mensaje=""):
        super().__init__(f"Ingrese dato de forma correcta {mensaje}")


class CampoVacio(IngresoErroneo):
    def __init__(self):
        super().__init__("Uno o más campos están vacíos")


class DatoDuplicado(IngresoErroneo):
    def __init__(self):
        super().__init__("El dato ingresado ya existe")


class SinDoctores(ErrorAdministrativo):
    def __init__(self):
        super().__init__("No hay doctores")


class SinAgenda(ErrorAdministrativo):
    def __init__(self):
        super().__init__("No hay citas disponibles")


class SinCitaPrevia(ErrorAdministrativo):
    def __init__(self):
        super().__init__("Debe de agendar cita con su doctor primero")


class SinMedicamentos(ErrorAdministrativo):
    def __init__(self):
        super().__init__("Su medicamento no esta disponible")


class SinVacunas(ErrorAdministrativo):
    def __init__(self):
        super().__init__("No tiene vacunas disponibles")


class SinServicioSeleccionado(ErrorAdministrativo):
    def __init__(self):
        super().__init__("Seleccione al menos un servicio")


class EstaHospitalizado(ErrorAdministrativo):
    def __init__(self):
        super().__init__("Ya esta ocupando una habitacion")
