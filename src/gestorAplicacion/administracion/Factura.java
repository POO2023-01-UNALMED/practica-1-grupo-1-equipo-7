package gestorAplicacion.administracion;

import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Servicio;

public class Factura {

    private final long idServicio;
    private final Paciente paciente;
    private final double precio;

    public Factura(long idServicio, Paciente paciente, double precio) {
        this.idServicio = idServicio;
        this.paciente = paciente;
        this.precio = precio;
    }

    public void realizarPago() {
        for (Servicio servicio :
                this.paciente.getHistoriaClinica().getListaEjemploServicios()) {
            if (servicio.getIdServicio() == this.idServicio) {
                servicio.setEstadoPago(true);
            }
        }
    }
}
