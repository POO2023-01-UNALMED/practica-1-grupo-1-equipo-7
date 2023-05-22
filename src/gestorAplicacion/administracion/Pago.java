package gestorAplicacion.administracion;

import gestorAplicacion.servicios.Cita;
import gestorAplicacion.servicios.CitaVacuna;
import gestorAplicacion.servicios.Formula;
import gestorAplicacion.servicios.Habitacion;

public interface Pago {

    double IVA = 0.19;

    double calcularPrecio(Cita cita);
    double calcularPrecio(CitaVacuna citaVacuna);
    double calcularPrecio(Formula formula);
    double calcularPrecio(Habitacion habitacion);

}
