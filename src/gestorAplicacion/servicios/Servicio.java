/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.servicios;

import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.personas.Paciente;

import java.io.Serializable;
import java.util.ArrayList;

//Clase Abstracta destinada a herencia de servicios del hospital
public abstract class Servicio implements Serializable {

    //Atributos
    protected static long generadorID = 1000;
    protected final long idServicio;
    protected Paciente paciente;
    private boolean estadoPago;

    //Constructor
    public Servicio(Paciente paciente) {
        this.idServicio = generadorID++;
        this.paciente = paciente;
        this.estadoPago = false;
    }

    //Métodos

    /* Método abstracto que debe implementarse en las clases hijas para
    validar el pago
     */
    public abstract void validarPago(Paciente paciente, long idServicio);

    /* Metodo abstracto para imprimir la descripcion del servicio.
    Se usa este metodo porque el toString se utiliza en otra funcionalidad.
     */
    public abstract String descripcionServicio();

    //Método estático para obtener servicios sin pagar
    public static ArrayList<Servicio> obtenerServiciosSinPagar(Paciente paciente) {
        HistoriaClinica historiaClinicaPaciente = paciente.getHistoriaClinica();
        ArrayList<Servicio> serviciosSinPagar = new ArrayList<>();

        // Obtiene todos los servicios brindados al paciente
        serviciosSinPagar.addAll(historiaClinicaPaciente.getHistorialCitas());
        serviciosSinPagar.addAll(historiaClinicaPaciente.getListaFormulas());
        if (paciente.getHabitacionAsignada() != null)
            serviciosSinPagar.add(paciente.getHabitacionAsignada());
        serviciosSinPagar.addAll(historiaClinicaPaciente.getHistorialVacunas());

        // Filtra los servicios pagados
        serviciosSinPagar.removeIf(Servicio::isEstadoPago);

        return serviciosSinPagar;
    }

    //Getters y Setters
    public boolean isEstadoPago() {
        return estadoPago;
    }

    public long getIdServicio() {
        return idServicio;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
