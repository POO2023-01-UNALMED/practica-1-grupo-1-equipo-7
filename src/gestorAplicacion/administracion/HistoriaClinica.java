package gestorAplicacion.administracion;

import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Cita;
import gestorAplicacion.servicios.Formula;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaClinica implements Serializable {
    private final Paciente paciente;
<<<<<<< HEAD

=======
>>>>>>> 3fba1bc8da2596856918a142c92412eddbb53bbe
    private ArrayList<Formula> listaFormulas = new ArrayList<>();
    private ArrayList<Cita> historialCitas = new ArrayList<>();
    private ArrayList<Enfermedad> enfermedades = new ArrayList<>();

    private ArrayList<Vacuna> historialVacunas= new ArrayList<Vacuna>();

    public HistoriaClinica(Paciente paciente) {
        this.paciente = paciente;
    }

<<<<<<< HEAD
    public ArrayList<Servicio> obtenerServiciosSinPagar() {
        ArrayList<Servicio> serviciosSinPagar = new ArrayList<>();

        for (Servicio servicio :
                listaEjemploServicios) {
            if (!servicio.isEstadoPago()) {
                serviciosSinPagar.add(servicio);
            }
        }
        return serviciosSinPagar;
    }
    public ArrayList<Doctor> buscarCitaDoc(String especialidad, Hospital hospital) {
        ArrayList<Doctor> doctoresDisp = hospital.buscarTipoDoctor(especialidad, hospital);
        ArrayList<Doctor> docCita = new ArrayList<>();
        for (Doctor doc : doctoresDisp){
            for (Cita cita : historialCitas){
                if (doc == cita.getDoctor()){
                    docCita.add(doc);
                }
            }
        }
        return docCita;
    }

=======
>>>>>>> 3fba1bc8da2596856918a142c92412eddbb53bbe
    public void agregarFormula(Formula formulaPaciente) {
        listaFormulas.add(formulaPaciente);
    }

<<<<<<< HEAD

=======
>>>>>>> 3fba1bc8da2596856918a142c92412eddbb53bbe
    public Paciente getPaciente() {
        return paciente;
    }

<<<<<<< HEAD

=======
>>>>>>> 3fba1bc8da2596856918a142c92412eddbb53bbe
    public ArrayList<Formula> getListaFormulas() {
        return listaFormulas;
    }

    public void setListaFormulas(ArrayList<Formula> listaFormulas) {
        this.listaFormulas = listaFormulas;
    }

    public ArrayList<Cita> getHistorialCitas() {
        return historialCitas;
    }

    public void setHistorialCitas(ArrayList<Cita> historialCitas) {
        this.historialCitas = historialCitas;
    }

    public ArrayList<Enfermedad> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public ArrayList<Vacuna> getHistorialVacunas() {
        return historialVacunas;
    }

    public void setHistorialVacunas(ArrayList<Vacuna> historialVacunas) {
        this.historialVacunas = historialVacunas;
    }
}
