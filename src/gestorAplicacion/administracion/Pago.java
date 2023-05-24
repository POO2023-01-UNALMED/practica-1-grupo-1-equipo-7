/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.administracion;

import gestorAplicacion.servicios.Cita;
import gestorAplicacion.servicios.CitaVacuna;
import gestorAplicacion.servicios.Formula;
import gestorAplicacion.servicios.Habitacion;

//Interfaz destinada a pagos
public interface Pago {
    //Constante
    double IVA = 0.19;

    //Métodos
    double calcularPrecio(Cita cita);
    double calcularPrecio(CitaVacuna citaVacuna);
    double calcularPrecio(Formula formula);
    double calcularPrecio(Habitacion habitacion);

}
