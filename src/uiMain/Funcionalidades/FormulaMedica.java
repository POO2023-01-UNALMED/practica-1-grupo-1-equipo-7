package uiMain.Funcionalidades;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Medicamento;
import gestorAplicacion.personas.Doctor;
import gestorAplicacion.personas.Enfermedad;
import gestorAplicacion.personas.Paciente;
import gestorAplicacion.servicios.Formula;

import java.util.ArrayList;
import java.util.Scanner;

public class FormulaMedica {
    static Scanner sc = new Scanner(System.in);

    public static void formulaMedica(Hospital hospital) {
        //Se pide la cedula del paciente y se arroja el paciente
        System.out.println("Ingrese la cédula del paciente: ");
        int cedula = sc.nextInt();
        Paciente paciente = hospital.buscarPaciente(cedula);
        // se crea ya la formula con el paciente asociado
        Formula formulaPaciente = new Formula(paciente);
        // se inicia su lista de medicamentos solicitada vacia
        ArrayList<Medicamento> listMedicamento = new ArrayList<Medicamento>();
        // se pregunta al paciente que enfermedad desea tratar y se busca la lista de enfermedades en su historia
        System.out.println("¿Que enfermedad deseas tratar?");
        for (int i = 0; i < paciente.getHistoriaClinica().getEnfermedades().size(); i++) {
            System.out.println(i + 1 + "." + paciente.getHistoriaClinica().getEnfermedades().get(i));
        }
        byte opcEnf = sc.nextByte();
        Enfermedad enfermedadTratar = null;
        if (opcEnf <= 0 || opcEnf > paciente.getHistoriaClinica().getEnfermedades().size()) {
            System.out.println("Opción inválida");
        } else {
            enfermedadTratar = paciente.getHistoriaClinica().getEnfermedades().get(opcEnf - 1); //Aquí seleccionamos que enfermedad eligió el paciente a tratar
        }
        System.out.println("Los doctores que lo han atendido y están disponibles para formular " + enfermedadTratar.getNombre() + " " + enfermedadTratar.getTipologia() + " son: ");

        ArrayList<Doctor> doctoresCita = paciente.getHistoriaClinica().buscarCitaDoc(enfermedadTratar.getEspecialidad(), hospital); /* metodo de la historia del paciente
         para buscar que doctor trata la enfermedad y que halla tenido una cita con el paciente */

        //Imprime la cita de los doctores que tratan su enfermedad y tuvieron una cita con el
        for (int i = 0; i < doctoresCita.size(); i++) {
            System.out.println(i + 1 + "." + doctoresCita.get(i).getNombre());
        }
        byte opcDoc = sc.nextByte();
        Doctor doctorEscogido = null;
        if (opcEnf <= 0 || opcEnf > doctoresCita.size()) {
            System.out.println("Opción inválida");
        } else {
            doctorEscogido = doctoresCita.get(opcDoc - 1);//seleccionamos el doctor
            formulaPaciente.setDoctor(doctorEscogido);
        }
        boolean agregarOtro = false; //condicion para el while para ir agregando medicamentos
        //bucle para agregar medicamentos, cada vez q pregunte si desea otro y responda si se repite
        do {
            System.out.println("Hola doctor, " + doctorEscogido.getNombre() + "\nPor favor selecciona los medicamentos que vas a formularle a: " + paciente.getNombre());
            //Aquí buscamos los medicamentos disponibles y los medicamentos que traten la enfermedad del paciente
            ArrayList<Medicamento> disponibleEnf = paciente.medEnfermedad(enfermedadTratar, hospital);
            //Condicion para que cuando ya no hayan medicamentos disponibles no siga el bucle y lo termine
            if (disponibleEnf.size()==0){
                System.out.println("No hay más medicamentos disponibles");
                break;
            }
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
            }
            /* Estas dos lineas son para ir actualizando la lista de medicamentos de la formula e ir actualizando el precio,
             se usa el metodo calcularPrecio de paciente, el cual se mete como parametro la lista de medicamentos que vamos llenando */
            formulaPaciente.setListaMedicamentos(listMedicamento);
            double precioForm = paciente.calcularPrecio(listMedicamento);
            System.out.println("Esta es tu lista actual de medicamentos:" + listMedicamento + "\n" + "Valor a pagar: " + precioForm);
            // Esto solo imprime los medicamentos que van
            for (int i = 0; i < listMedicamento.size(); i++) {
                System.out.println(i + 1 + "." + listMedicamento.get(i));
            }
            System.out.println("¿Desea agregar otro medicamento? (s/n)");
            char agregar = sc.next().charAt(0);
            agregarOtro = agregar == 's';
        } while (agregarOtro);
        paciente.getHistoriaClinica().agregarFormula(formulaPaciente);
        //esto solo imprime el toString de la formula
        System.out.println(formulaPaciente);
        listMedicamento.clear();
    }
}
