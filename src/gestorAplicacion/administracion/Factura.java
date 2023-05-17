package gestorAplicacion.administracion;

import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Servicio;

public class Factura {

    private final long idServicio;
    private final Paciente paciente;

    public Factura(long idServicio, Paciente paciente) {
        this.idServicio = idServicio;
        this.paciente = paciente;
    }

    public void realizarPago() {
        HistoriaClinica historiaClinicaPaciente = this.paciente.getHistoriaClinica();
        
        for (Servicio servicio :
                historiaClinicaPaciente.getHistorialCitas()) {
            if (servicio.getIdServicio() == this.idServicio) {
                servicio.setEstadoPago(true);
                return;
            }
        }

        for (Servicio servicio :
                historiaClinicaPaciente.getListaFormulas()) {
            if (servicio.getIdServicio() == this.idServicio) {
                servicio.setEstadoPago(true);
                return;
            }
        }
    }
}
