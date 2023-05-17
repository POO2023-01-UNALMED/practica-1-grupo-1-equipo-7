package uiMain.Funcionalidades;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Vacuna;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.CitaVacuna;


import java.util.ArrayList;
import java.util.Scanner;

public class Vacunacion {

    static Scanner sc = new Scanner(System.in);

    public static void vacunacion(Hospital hospital){

        System.out.println("Ingrese su numero de cedula");

        int numeroCedula;
        numeroCedula= sc.nextInt();

        //Se busca el paciente por el numero de cédula
        Paciente pacienteAsignado= hospital.buscarPaciente(numeroCedula);

        byte tipoVacuna;

        System.out.println("\n Seleccione el tipo de vacuna que requiere");
        System.out.println("1. Obligatoria");
        System.out.println("2. No obligatoria");
        System.out.println("Ingrese una opcion: ");

        tipoVacuna= sc.nextByte();

        while ((tipoVacuna<1) || (tipoVacuna>2)){
            System.out.println("Opción fuera de rango, por favor ingrese otro número: ");
            tipoVacuna = sc.nextByte();
        }

        System.out.println("Vacunas Disponibles");

        //Busca las vacunas disponibles según su Eps y tipo de Vacuna
        ArrayList<Vacuna> vacunasDisponibles = new ArrayList<Vacuna>();

        switch (tipoVacuna){
            case 1:
                vacunasDisponibles= pacienteAsignado.buscarVacunaPorEps("Obligatoria",hospital);
                for (int i=1; i<=vacunasDisponibles.size(); i++){
                    System.out.println(i + "."+vacunasDisponibles.get(i-1).getNombre());
                }
                break;
            case 2:
                vacunasDisponibles= pacienteAsignado.buscarVacunaPorEps("No obligatoria",hospital);
                for (int i=1; i<=vacunasDisponibles.size(); i++){
                    System.out.println(i + "."+vacunasDisponibles.get(i-1).getNombre());
                }
                break;
        }

        System.out.println("\nSeleccione la vacuna que requiere aplicarse: ");

        byte numeroVacuna;
        numeroVacuna = sc.nextByte();


        //Verificar que la vacuna seleccionada no esté en el historial de citas o la oción esté fuera de rango
        boolean verificarVacuna=false;

        do{
            while ((numeroVacuna<1) || (numeroVacuna>vacunasDisponibles.size())){
                System.out.println("Opción fuera de rango, por favor ingrese otro número: ");
                numeroVacuna = sc.nextByte();
            }
            for (int i=1;i<=pacienteAsignado.getHistoriaClinica().getHistorialVacunas().size();i++){
                if (pacienteAsignado.getHistoriaClinica().getHistorialVacunas().get(i-1).getNombre()==vacunasDisponibles.get(numeroVacuna-1).getNombre()){
                    verificarVacuna=true;
                    System.out.println("Usted ya se puso esta vacuna, por favor ingrese otra opción: ");
                    numeroVacuna= sc.nextByte();;
                    break;
                }else {
                    verificarVacuna=false;
                }
            }
        }while ( (verificarVacuna==true) || ((numeroVacuna<1) || (numeroVacuna>vacunasDisponibles.size())));

        ArrayList<CitaVacuna> agendaDisponible = new ArrayList<CitaVacuna>();
        System.out.println("\nCitas disponibles: ");

        Vacuna vacunaAsignada = vacunasDisponibles.get(numeroVacuna-1);
        agendaDisponible = vacunaAsignada.mostrarAgendaDisponible();

        for(int i=1; i<=agendaDisponible.size();i++){
            System.out.println(i + ". "+ agendaDisponible.get(i-1).getFecha());
        }

        //Selecciona la cita
        System.out.println("\nSeleccione la cita de su preferencia: ");

        byte numeroCita;
        numeroCita= sc.nextByte();

        //Cuarto paso, actualiza la agenda de la vacuna

        CitaVacuna citaAsignada = vacunaAsignada.actualizarAgenda(pacienteAsignado, numeroCita,agendaDisponible);

        System.out.println("\nCita asignada correctamente, puede acudir al centro asistencial con la siguiente informacion: ");

        pacienteAsignado.actualizarHistorialVacunas(citaAsignada);

        System.out.println("\nResumen de su cita: ");
        System.out.println("Fecha: " + citaAsignada.getFecha());
        System.out.println("Paciente: " + citaAsignada.getPaciente().getNombre());
        System.out.println("Vacuna: "+ citaAsignada.getVacuna().getNombre());
        System.out.println("Asistente médico: Enfermera ") ;

        //Limpia los arrays de agenda de las vacunas y las vacunas disponibles.
        agendaDisponible.clear();
        vacunasDisponibles.clear();

        System.out.println("\nEste es el historial de vacunas aplicadas del paciente seleccionado: ");
        pacienteAsignado.mostrarHistorialVacunas();
    }
}
