package gestorAplicacion.servicios;

import gestorAplicacion.personas.Paciente;

public class EjemploServicio extends Servicio {

    protected final double precioBase = 10000;

    public EjemploServicio(Paciente paciente, String fecha) {
        super(paciente,fecha);
        this.paciente.getHistoriaClinica().getListaEjemploServicios().add(this);
    }

    @Override
    public double calcularPrecio() {
        return precioBase*0.90;
    }

    @Override
    public String toString() {
        return "ID: " + this.idServicio + " - Ejemplo servicio - " + this.fecha;
    }
}
