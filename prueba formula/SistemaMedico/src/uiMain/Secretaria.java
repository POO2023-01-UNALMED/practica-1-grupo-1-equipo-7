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
		// se inicia su lista de medicamentos solicitada vacia
		ArrayList<Medicamento>listMedicamento = new ArrayList<Medicamento>();
		boolean agregarOtro = false; //condicion para el while para ir agregando medicamentos
		//bucle para agregar medicamentos, cada vez q pregunte si desea otro y responda si se repite
		do {
			//aqui se usa el metodo medicamentosDisponibles para mostrar los medicamentos disponibles que hay
			System.out.println("Hola!"+paciente.getNombre()+"\n"+"Los medicamentos disponibles son:");
			ArrayList<Medicamento> disponible = Hospital.medicamentosDisponibles();
			//esto solo se usa para imprimir la lista de la forma 1. Nombre, etc..
			for (int i=0; i<disponible.size();i++) {
				System.out.println(i+1+"."+disponible.get(i)+", Cantidad: "+disponible.get(i).getCantidad());
			}
			//guarda la opcion elegida 
			byte opcMed = sc.nextByte();
			
			if (opcMed <=0 || opcMed > disponible.size()) {
				System.out.println("Opción inválida");
			}
			//si la opcion es valida se agrega a la lista de medicamentos que esta fuera del bucle y se ejecuta eliminarCantidad() 
			//para actualizar la cantidad de medicamentos en inventario
			else {
				Medicamento medicamentoEscogido = disponible.get(opcMed-1);
				medicamentoEscogido.eliminarCantidad();
				listMedicamento.add(medicamentoEscogido);

			}
			//arroja la lista de los medicamentos del paciente
			System.out.println("Esta es tu lista actual de medicamentos:"+listMedicamento);
			//pregunta si desea agregar otro, si dice si se ejecuta otra vez el bucle y si no se acaba 
			System.out.println("¿Desea agregar otro medicamento? (s/n)");
		    char agregar = sc.next().charAt(0);
		    agregarOtro = agregar == 's';
		} while (agregarOtro);
		//se ejecuta el constructor para crear la formula y se ejecuta el metodo agregarFormula de la historia del paciente
		Formula formulaFinal = new Formula(listMedicamento, paciente);
		paciente.getHistoriaClinica().agregarFormula(formulaFinal);
		//esto solo imprime el toString de la formula
		System.out.println(formulaFinal);
		
		
	}
	public static void main(String[] args) {
		
		//menu principal
		byte opcion;
		
		System.out.println("MENU PRINCIPAL");
		System.out.println("1. Formula Medica");
		System.out.println("5. Salir");
		System.out.print("Ingrese una opcion: ");
			
		opcion = sc.nextByte();
			
		switch (opcion) {
		case 1:
			formulaMedica();
			break;
		    
		case 5:
			System.out.println("saliendo");
			break;
		}		
	}
	
}
