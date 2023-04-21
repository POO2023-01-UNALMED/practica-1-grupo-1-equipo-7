package uiMain;
import java.util.Scanner;

import gestorAplicacion.administracion.*;
import gestorAplicacion.personas.*;

import java.util.ArrayList;

public class Secretaria {
	static Scanner sc = new Scanner(System.in);

	static public void formulaMedica() {
		//Se pide la cedula del paciente y se arroja el paciente
		System.out.println("Ingrese la cédula del paciente: ");
		int cedula = sc.nextInt();
		Paciente paciente = Hospital.buscarPaciente(cedula);
		// se crea ya la formula con el paciente asociado
		Formula formulaPaciente = new Formula(paciente);
		// se inicia su lista de medicamentos solicitada vacia
		ArrayList<Medicamento>listMedicamento = new ArrayList<Medicamento>();
		boolean agregarOtro = false; //condicion para el while para ir agregando medicamentos
		//bucle para agregar medicamentos, cada vez q pregunte si desea otro y responda si se repite
		do {
			//aqui se usa el metodo medicamentosDisponibles para mostrar los medicamentos disponibles que hay
			System.out.println("Hola! "+paciente.getNombre()+"(¡Recuerda que si tienes una eps su precio es cero!)\n"+"Los medicamentos disponibles para tus enfermedades son:");
			ArrayList<Medicamento> disponible = Hospital.medicamentosDisponibles();
			// disponibleEnf son los medicamentos asociados a las enfermedades del paciente
			ArrayList<Medicamento> disponibleEnf = paciente.medEnfermedad(disponible);
			//esto solo se usa para imprimir la lista de la forma 1. Nombre, etc..
			for (int i=0; i<disponibleEnf.size();i++) {
				System.out.println(i+1+"."+disponibleEnf.get(i)+", Precio: "+disponibleEnf.get(i).getPrecio());
			}
			//guarda la opcion elegida 
			byte opcMed = sc.nextByte();
			
			if (opcMed <=0 || opcMed > disponibleEnf.size()) {
				System.out.println("Opción inválida");
			}
			/* si la opcion es valida se agrega a la lista de medicamentos que esta fuera del bucle y se ejecuta eliminarCantidad()
			para actualizar la cantidad de medicamentos en inventario */
			else {
				Medicamento medicamentoEscogido = disponibleEnf.get(opcMed-1);
				medicamentoEscogido.eliminarCantidad();
				listMedicamento.add(medicamentoEscogido);
			}
			/* Estas dos lineas son para ir actualizando la lista de medicamentos de la formula y ir actualizando el precio,
			 se usa el metodo calcularPrecio de formula, el cual se mete como parametro la lista de medicamentos que vamos llenando */
			formulaPaciente.setListaMedicamentos(listMedicamento);
			formulaPaciente.setPrecio(formulaPaciente.calcularPrecio(listMedicamento));
			System.out.println("Esta es tu lista actual de medicamentos:"+listMedicamento+"\n"+"Valor a pagar: "+formulaPaciente.getPrecio());
			// Esto solo imprime los medicamentos que van
			for (int i=0; i<listMedicamento.size();i++) {
				System.out.println(i+1+"."+listMedicamento.get(i));
			}
			System.out.println("¿Desea agregar otro medicamento? (s/n)");
		    char agregar = sc.next().charAt(0);
		    agregarOtro = agregar == 's';
		} while (agregarOtro);
		//Al saldo del paciente se le resta el valor de la formula
		paciente.getCuenta().restarSaldo(formulaPaciente.getPrecio());
		paciente.getHistoriaClinica().agregarFormula(formulaPaciente);
		//esto solo imprime el toString de la formula
		System.out.println(formulaPaciente);
		System.out.println("Su nuevo saldo es: "+paciente.getCuenta().getSaldo());
		
		
	}
	public static void main(String[] args) {
				
		//menu principal
		byte opcion;
		
		System.out.println("MENU PRINCIPAL");
		System.out.println("1. Formula Medica");
		System.out.println("2. Salir");
		System.out.print("Ingrese una opcion: ");
			
		opcion = sc.nextByte();
			
		switch (opcion) {
		case 1:
			formulaMedica();
			break;
		    
		case 2:
			System.out.println("Adios");
			break;
		}		
	}
	
}
