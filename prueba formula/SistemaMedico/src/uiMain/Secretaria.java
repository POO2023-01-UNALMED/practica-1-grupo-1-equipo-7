package uiMain;
import java.util.Scanner;

import gestorAplicacion.administracion.*;
import gestorAplicacion.personas.*;

import java.util.ArrayList;

public class Secretaria {
	static Scanner sc = new Scanner(System.in);

	static public void formulaMedica() {
		System.out.println("Ingrese la cédula del paciente: ");
		int cedula = sc.nextInt();
		Paciente paciente = Hospital.buscarPaciente(cedula);
		ArrayList<Medicamento>listMedicamento = new ArrayList<Medicamento>();
		boolean agregarOtro = false;
		do {
			System.out.println("Hola!"+paciente.getNombre()+"\n"+"Los medicamentos disponibles son:");
			ArrayList<Medicamento> disponible = Hospital.medicamentosDisponibles();
			for (int i=0; i<disponible.size();i++) {
				System.out.println(i+1+"."+disponible.get(i)+", Cantidad: "+disponible.get(i).getCantidad());
			}
			byte opcMed = sc.nextByte();
			
			if (opcMed <=0 || opcMed > disponible.size()) {
				System.out.println("Opción inválida");
			}
			else {
				Medicamento medicamentoEscogido = disponible.get(opcMed-1);
				medicamentoEscogido.eliminarCantidad();
				listMedicamento.add(medicamentoEscogido);

			}
			System.out.println("Esta es tu lista actual de medicamentos:"+listMedicamento);
			System.out.println("¿Desea agregar otro medicamento? (s/n)");
		    char agregar = sc.next().charAt(0);
		    agregarOtro = agregar == 's';
		} while (agregarOtro);
		Formula formulaFinal = new Formula(listMedicamento, paciente);
		paciente.getHistoriaClinica().agregarFormula(formulaFinal);
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
