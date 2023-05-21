package uiMain.Funcionalidades;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Vacuna;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.CitaVacuna;
import uiMain.Gestion.gestionPacientes.RegistrarPaciente;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Vacunacion {

    static Scanner sc = new Scanner(System.in);

    public static void vacunacion(Hospital hospital){

        System.out.println("Ingrese la cédula del paciente: ");

        int numeroCedula;
        numeroCedula= sc.nextInt();

        //Se busca el paciente por el numero de cédula
        Paciente pacienteAsignado= hospital.buscarPaciente(numeroCedula);

        //Verificación que el paciente se encuentre en la base de datos del hospital

        if (pacienteAsignado == null){
            while(true){
                //Se da la posibilidad de registrar ese paciente
                System.out.println("El paciente no está registrado.\n¿Desea registrarlo?");
                System.out.println("1. Si\n2.No \nSeleccione una opción");
                byte opcion= sc.nextByte();
                switch (opcion){
                    case 1:
                        RegistrarPaciente.registrarPaciente(hospital);
                        return;

                    case 2:
                        System.out.println("Adiós");
                        return;

                    default:
                        System.out.println("Opción Inválida");
                }
            }
        }

        //Se pregunta por el tipo de vacuna que requiere el paciente
        byte tipoVacuna;

        System.out.println("\nSeleccione el tipo de vacuna que requiere");
        System.out.println("1. Obligatoria");
        System.out.println("2. No obligatoria");
        System.out.println("Ingrese una opcion: ");

        tipoVacuna= sc.nextByte();

        //Validación de la opción ingresada
        while ((tipoVacuna<1) || (tipoVacuna>2)){
            System.out.println("Opción fuera de rango, por favor ingrese otro número: ");
            tipoVacuna = sc.nextByte();
        }

        System.out.println("Vacunas Disponibles");

        ArrayList<Vacuna> vacunasDisponibles = new ArrayList<Vacuna>();

        //Busca las vacunas disponibles según su Eps y tipo de Vacuna
        switch (tipoVacuna){
            case 1:
                vacunasDisponibles= pacienteAsignado.buscarVacunaPorEps("Obligatoria",hospital);
                if (vacunasDisponibles.size()==0){
                    System.out.println("No hay vacunas disponibles para usted");
                    return;
                }
                for (int i=1; i<=vacunasDisponibles.size(); i++){
                    System.out.println(i + "."+vacunasDisponibles.get(i-1).getNombre());
                }
                break;
            case 2:
                vacunasDisponibles= pacienteAsignado.buscarVacunaPorEps("No obligatoria",hospital);
                if (vacunasDisponibles.size()==0){
                    System.out.println("No hay vacunas disponibles para usted");
                    return;
                }
                for (int i=1; i<=vacunasDisponibles.size(); i++){
                    System.out.println(i + "."+vacunasDisponibles.get(i-1).getNombre());
                }
                break;
        }

        System.out.println("\nSeleccione la vacuna que requiere aplicarse: ");

        byte numeroVacuna;
        numeroVacuna = sc.nextByte();

        //Si verificar vacuna es false, el paciente no se ha puesto esa vacuna anteriormente.
        boolean verificarVacuna=false;

        do{
            //Se verifica que la opción ingresada no esté fuera de rango
            while ((numeroVacuna<1) || (numeroVacuna>vacunasDisponibles.size())){
                System.out.println("Opción fuera de rango, por favor ingrese otro número: ");
                numeroVacuna = sc.nextByte();
            }
            // Se verifica que la vacuna seleccionada no se la haya puesto antes
            for (int i=1;i<=pacienteAsignado.getHistoriaClinica().getHistorialVacunas().size();i++){
                if (Objects.equals(pacienteAsignado.getHistoriaClinica().getHistorialVacunas().get(i - 1).getNombre(), vacunasDisponibles.get(numeroVacuna - 1).getNombre())){
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

        //Se busca la agenda disponible de la vacuna seleccionada
        Vacuna vacunaAsignada = vacunasDisponibles.get(numeroVacuna-1);
        agendaDisponible = vacunaAsignada.mostrarAgendaDisponible();

        for(int i=1; i<=agendaDisponible.size();i++){
            System.out.println(i + ". "+ agendaDisponible.get(i-1).getFecha());
        }

        //Selecciona la cita
        System.out.println("\nSeleccione la cita de su preferencia: ");

        byte numeroCita;
        numeroCita= sc.nextByte();

        //Se actualiza la agenda de la vacuna

        CitaVacuna citaAsignada = vacunaAsignada.actualizarAgenda(pacienteAsignado, numeroCita,agendaDisponible);

        System.out.println("\nCita asignada correctamente, puede acudir al centro asistencial con la siguiente informacion: ");

        //Se agrega esa vacuna al historial de vacunas del paciente
        pacienteAsignado.actualizarHistorialVacunas(citaAsignada);

        System.out.println("\nResumen de su cita: ");
        System.out.println("Fecha: " + citaAsignada.getFecha());
        System.out.println("Paciente: " + citaAsignada.getPaciente().getNombre());
        System.out.println("Vacuna: "+ citaAsignada.getVacuna().getNombre());
        System.out.println("Asistente médico: Enfermera ") ;

        //Limpia los arrays de agenda de las vacunas y las vacunas disponibles.
        agendaDisponible.clear();
        vacunasDisponibles.clear();

        //Por último se muestra el historial de vacunas del paciente
        System.out.println("\nEste es el historial de vacunas aplicadas del paciente seleccionado: ");
        pacienteAsignado.mostrarHistorialVacunas();
    }
}
