package FuncionalidadVacunas;

import java.util.ArrayList;

public class Paciente extends Persona {
    private HistoriaClinica historiaClinica;

    public Paciente(int cedula, String nombre, String tipoEps, HistoriaClinica historiaClinica) {
        super(cedula, nombre, tipoEps);
        this.historiaClinica = historiaClinica;
    }

    public ArrayList<Vacuna> buscarVacunaPorEps(String tipo){
        ArrayList<Vacuna> vacunasPorTipo= Hospital.buscarTipoVacuna(tipo);
        ArrayList<Vacuna> vacunasDisponibles= new ArrayList<Vacuna>();

        for (int i=1; i<=vacunasPorTipo.size();i++){
            ArrayList<String> tipoEpsVacuna= vacunasPorTipo.get(i-1).getTipoEps();
            for (int j=1;j<=tipoEpsVacuna.size();j++){
               if (tipoEpsVacuna.get(j-1)==getTipoEps()){
                   vacunasDisponibles.add(vacunasPorTipo.get(i-1));
               }
            }
        }
        return vacunasDisponibles;
    }

    public void actualizarHistorialVacunas(CitaVacuna citaAsignada){
        historiaClinica.getHistorialVacunas().add(citaAsignada.getVacuna());
    }

    public void mostrarHistorialVacunas() {
        for (int i=1; i<=historiaClinica.getHistorialVacunas().size();i++){
            System.out.println(i + ". Vacuna: "+historiaClinica.getHistorialVacunas().get(i-1).getNombre());
        }
    }

    public double calcularPrecio(CitaVacuna citaAsignada){
        final double IVA= 0.19;
        double precioTotal=citaAsignada.getVacuna().getPrecio();

        if(citaAsignada.getVacuna().getTipo()=="Obligatoria"){
            precioTotal += 1000;
        }
        if(citaAsignada.getVacuna().getTipo()=="No obligatoria"){
            precioTotal += 3000;
        }
        if (citaAsignada.getPaciente().getTipoEps()=="Contributivo"){
            precioTotal += 2000;
        }
        if (citaAsignada.getPaciente().getTipoEps()=="Subsidiado"){
            precioTotal += 500;
        }
        if (citaAsignada.getPaciente().getTipoEps()=="Particular"){
            precioTotal += 10000;
        }

        precioTotal=precioTotal*(1+IVA);
        return precioTotal;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }


}
