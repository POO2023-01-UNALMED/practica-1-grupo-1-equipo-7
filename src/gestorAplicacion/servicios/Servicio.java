package gestorAplicacion.servicios;

import gestorAplicacion.personas.Paciente;

import java.io.Serializable;

public abstract class Servicio implements Serializable {

    protected static long generadorID = 1;
    protected final long idServicio;
    protected final Paciente paciente;
    protected final String fecha;
    private boolean estadoPago;

    public Servicio(Paciente paciente, String fecha) {
        this.idServicio = generadorID++;
        this.paciente = paciente;
        this.fecha = fecha;
        this.estadoPago = false;
    }

    public abstract double calcularPrecio();

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public long getIdServicio() {
        return idServicio;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }
}
