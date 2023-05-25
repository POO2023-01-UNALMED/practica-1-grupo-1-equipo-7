/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package uiMain.gestion.gestionHospital;

import gestorAplicacion.administracion.Hospital;
import gestorAplicacion.administracion.Medicamento;

import java.util.ArrayList;

public class VerMedicamentos {
    public static void verMedicamentos(Hospital hospital) {
        ArrayList<Medicamento> listaMedicamentos = hospital.getListaMedicamentos();
        System.out.println("Este es el inventario de los medicamentos del hospital: ");
        for (int i = 0; i < listaMedicamentos.size(); i++) {
            System.out.println(i + 1 + "." + listaMedicamentos.get(i) + "Cantidad:" + listaMedicamentos.get(i).getCantidad());
        }
    }
}
