/* Autores: Diego Andres Gracia Granados, Daniel Giraldo Vanegas, Elian David Velandia Riveros, Juan Camilo Gutierrez Martinez
y Santiago Arboleda Acevedo */
package gestorAplicacion.administracion;

public enum CategoriaHabitacion
{
    CAMILLA(50000),
    INDIVIDUAL(150000),
    DOBLE(320000),
    OBSERVACION(500000),
    UCI(1300000),
    UCC(1500000);
    private final int valor;

    private CategoriaHabitacion(int valor)
    {
        this.valor=valor;
    }

    public int getValor()
    {
        return valor;
    }
}
