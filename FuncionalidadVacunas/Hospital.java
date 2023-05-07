package FuncionalidadVacunas;

import java.util.ArrayList;
import java.util.Arrays;

public class Hospital {
    static private ArrayList<Paciente> listaPacientes= new ArrayList<Paciente>();
    static private ArrayList<Vacuna> listaVacunas=new ArrayList<Vacuna>();

    static{
        //Se cargan los pacientes
        //EN LA HISTORIA CLINICA DEBE LLEVAR UN CONSTRUCTOR DONDE HAYA UN THIS Y SE PONGA A SI MISMO
        listaPacientes.add(new Paciente(111, "Juan", "Subsidiado", new HistoriaClinica()));
        listaPacientes.add(new Paciente(222, "Jose", "Contributivo", new HistoriaClinica()));
        listaPacientes.add(new Paciente(333, "Felipe", "Particular", new HistoriaClinica()));
        listaPacientes.add(new Paciente(444, "Maria","Subsidiado", new HistoriaClinica()));
        listaPacientes.add(new Paciente(555, "Pedro", "Particular", new HistoriaClinica()));

        //Se cargan las vacunas
        listaVacunas.add(new Vacuna("No obligatoria","Covid-19",new ArrayList<String>(Arrays.asList("Subsidiado","Contributivo")),10000));
        listaVacunas.add(new Vacuna("Obligatoria","Hepatitis B",new ArrayList<String>(Arrays.asList("Subsidiado","Particular","Contributivo")),5000));
        listaVacunas.add(new Vacuna("Obligatoria","Rotavirus",new ArrayList<String>(Arrays.asList("Subsidiado","Particular")),3000));
        listaVacunas.add(new Vacuna("No obligatoria","Varicela",new ArrayList<String>(Arrays.asList("Particular")),2000));
        listaVacunas.add(new Vacuna("Obligatoria","Neumococo",new ArrayList<String>(Arrays.asList("Particular","Contributivo")),4000));
    }


    static public Paciente buscarPaciente(int numeroCedula){
        Paciente pacienteAsignado=null;
        for(int i=1; i<=listaPacientes.size();i++){
            if (listaPacientes.get(i-1).getCedula()==numeroCedula){
                pacienteAsignado=listaPacientes.get(i-1);
            }
        }
        return pacienteAsignado;
    }

    static public ArrayList<Vacuna> buscarTipoVacuna(String tipo){
        ArrayList<Vacuna> vacunasDisponibles = new ArrayList<Vacuna>();
        for (int i=1; i<=listaVacunas.size(); i++){
            if (listaVacunas.get(i-1).getTipo()==tipo){
                vacunasDisponibles.add(listaVacunas.get(i-1));
            }
        }
        return vacunasDisponibles;
    }
}
