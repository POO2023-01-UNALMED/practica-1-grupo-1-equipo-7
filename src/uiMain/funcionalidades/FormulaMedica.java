/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.funcionalidades;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Medicamento;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Formula;
import uiMain.gestion.gestionPacientes.RegistrarPaciente;

import java.util.ArrayList;
import java.util.Scanner;

public class FormulaMedica {
    static Scanner sc = new Scanner(System.in);

    public static void formulaMedica(Hospital hospital) {
        //Se pide la cedula del paciente y se arroja el paciente
        System.out.println("Ingrese la cédula del paciente: ");
        int cedula = sc.nextInt();
        Paciente paciente = hospital.buscarPaciente(cedula);
        if (paciente == null) { /*Si el paciente es null, quiere decir que no lo encontró, por lo que
        preguntamos si desea registrar este paciente */
            while (true) {
                System.out.println("El paciente no esta registrado.\n¿Desea registrarlo?");
                System.out.println("1. Si\n2. No \nSeleccione una opción");
                byte opcion = sc.nextByte();
                switch (opcion) {
                    case 1:
                        RegistrarPaciente.registrarPaciente(hospital);
                        return;

                    case 2:
                        System.out.println("Adios");
                        return;
                    default:
                        System.out.println("Opción Inválida");
                }
            }
        }
        // se crea ya la formula con el paciente asociado
        Formula formulaPaciente = new Formula(paciente);
        // se inicia su lista de medicamentos solicitada vacia
        ArrayList<Medicamento> listMedicamento = new ArrayList<Medicamento>();
        // se pregunta al paciente que enfermedad desea tratar y se busca la lista de enfermedades en su historia
        System.out.println("¿Que enfermedad deseas tratar?");
        Enfermedad enfermedadTratar = null;
        if (paciente.getHistoriaClinica().getEnfermedades().size() == 0){ //Caso en el que el paciente no tenga enfermedades registradas
            System.out.println("No hay enfermedades registradas, por favor diríjase a la sección de registrar enfermedades.");
            return;
        }
        while (true) { //Bucle para el caso en el que elija opciones fuera de rango
            for (int i = 0; i < paciente.getHistoriaClinica().getEnfermedades().size(); i++) {
                System.out.println(i + 1 + "." + paciente.getHistoriaClinica().getEnfermedades().get(i));
            }
            byte opcEnf = sc.nextByte();
            if (opcEnf <= paciente.getHistoriaClinica().getEnfermedades().size() && opcEnf > 0) {
                enfermedadTratar = paciente.getHistoriaClinica().getEnfermedades().get(opcEnf - 1); //Aquí seleccionamos que enfermedad eligió el paciente a tratar
                break;

            } else {
                System.out.println("Opción Inválida");
            }
        }
        ArrayList<Doctor> doctoresCita = paciente.getHistoriaClinica().buscarCitaDoc(enfermedadTratar.getEspecialidad(), hospital); /* metodo de la historia del paciente
         para buscar que doctor trata la enfermedad y que haya tenido una cita con el paciente */
        if (doctoresCita.size() == 0) { //Caso donde no tenga citas con ningún doctor que trate su enfermedad
            System.out.println("Ahora no contamos con doctores para tratar esta enfermedad. Lo sentimos mucho");
            return;
        }
        Doctor doctorEscogido = null;
        while (true) { //Bucle para el caso en el que elija opciones fuera de rango
            System.out.println("Los doctores que lo han atendido y están disponibles para formular " + enfermedadTratar.getNombre() + " " + enfermedadTratar.getTipologia() + " son: ");
            //Imprime la cita de los doctores que tratan su enfermedad y tuvieron una cita con él
            for (int i = 0; i < doctoresCita.size(); i++) {
                System.out.println(i + 1 + "." + doctoresCita.get(i).getNombre());
            }
            byte opcDoc = sc.nextByte();
            if (opcDoc > 0 & opcDoc <= doctoresCita.size()) {
                doctorEscogido = doctoresCita.get(opcDoc - 1); //seleccionamos el doctor
                break;
            } else {
                System.out.println("Opción inválida");
            }
        }
        boolean agregarOtro = false; //condicion para el while para ir agregando medicamentos

        formulaPaciente.setDoctor(doctorEscogido);
        //bucle para agregar medicamentos, cada vez q pregunte si desea otro y responda si se repite
        do {
            System.out.println(paciente.mensajeDoctor(doctorEscogido));
            //Aquí buscamos los medicamentos disponibles y los medicamentos que traten la enfermedad del paciente
            ArrayList<Medicamento> disponibleEnf = paciente.medEnfermedad(enfermedadTratar, hospital);
            //Condicion para que cuando ya no haya medicamentos disponibles no siga el bucle y lo termine
            if (disponibleEnf.size()==0){
                System.out.println("No hay más medicamentos disponibles");
                break;
            }
            while (true) { //Bucle para el caso en el que elija opciones fuera de rango
                //esto solo se usa para imprimir la lista de la forma 1. Nombre, etc..
                for (int i = 0; i < disponibleEnf.size(); i++) {
                    System.out.println(i + 1 + "." + disponibleEnf.get(i) + ", Cantidad: " + disponibleEnf.get(i).getCantidad() + ", Precio: " + disponibleEnf.get(i).getPrecio());
                }
                //guarda la opcion elegida
                byte opcMed = sc.nextByte();
                if (opcMed <= 0 || opcMed > disponibleEnf.size()) {
                    System.out.println("Opción inválida");
                }
                /* si la opcion es valida se agrega a la lista de medicamentos que esta fuera del bucle y se ejecuta eliminarCantidad()
                para actualizar la cantidad de medicamentos en inventario */
                else {
                    Medicamento medicamentoEscogido = disponibleEnf.get(opcMed - 1);
                    medicamentoEscogido.eliminarCantidad();
                    listMedicamento.add(medicamentoEscogido);
                    break;
                }
            }
            /* Estas dos lineas son para ir actualizando la lista de medicamentos de la formula e ir actualizando el precio,
             se usa el metodo calcularPrecio de paciente, el cual se mete como parametro la lista de medicamentos que vamos llenando */
            formulaPaciente.setListaMedicamentos(listMedicamento);
            System.out.println("Esta es tu lista actual de medicamentos:" + listMedicamento);
            // Esto solo imprime los medicamentos que van
            for (int i = 0; i < listMedicamento.size(); i++) {
                System.out.println(i + 1 + "." + listMedicamento.get(i));
            }
            while (true) { //Bucle para el caso en el que se ingresen opciones incorrectas
                System.out.println("¿Desea agregar otro medicamento? (s/n)");
                char agregar = sc.next().charAt(0);
                if (agregar == 's' || agregar == 'n') {
                    agregarOtro = agregar == 's';
                    break;
                } else {
                    System.out.println("Opción inválida");
                }
            }
        } while (agregarOtro);
        if (listMedicamento.size() == 0) { /* En caso de que listaMedicamentos este vacía significa que no formulo medicamentos
         entonces que esa formula vacía creada al inicio de la funcionalidad no se guarde en la historia del paciente */
            return;
        }
        paciente.getHistoriaClinica().agregarFormula(formulaPaciente);
        //esto solo imprime el toString de la formula
        System.out.println(formulaPaciente);
        listMedicamento.clear();
    }
}
