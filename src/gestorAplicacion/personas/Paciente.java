package gestorAplicacion.personas;

import gestorAplicacion.administracion.HistoriaClinica;
import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Medicamento;
import gestorAplicacion.servicios.Servicio;

import java.util.ArrayList;

public class Paciente extends Persona{

    private final HistoriaClinica historiaClinica;
    private String tipoEps;

    public Paciente(int cedula, String nombre) {
        super(cedula,nombre);
        this.historiaClinica = new HistoriaClinica(this);
    }

    public double calcularPrecio(Servicio servicio) {
        return 0;
    }
    public double calcularPrecio(ArrayList<Medicamento> medicamentos){
        double precio = 0;
        for (Medicamento med : medicamentos){
            precio += med.getPrecio();
        }
        return precio;
    }
    public ArrayList<Medicamento> medEnfermedad(Enfermedad enfermedad, Hospital hospital) {
        ArrayList<Medicamento> medicamentos = hospital.medicamentosDisponibles();
        ArrayList<Medicamento> medEnfermedades = new ArrayList<Medicamento>();
        for (Medicamento med : medicamentos){
            if (enfermedad == med.getEnfermedad()){
                medEnfermedades.add(med);
            }

        }
        return medEnfermedades;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public String getTipoEps() {
        return tipoEps;
    }

    public void setTipoEps(String tipoEps) {
        this.tipoEps = tipoEps;
    }


}
