package Model;

public class Sala {

    public Sala() {
        this.cantAsientosTotales = 0;
    }

    // Atributos para la cantidad total de asientos y por sección
    private int cantAsientosTotales;
    private static Sala instancia = null;
    private int cantAsientosPlatea;
    private int cantAsientosPalcoAlto;
    private int cantAsientosPalcoBajo;
    private int cantAsientosCazuela;
    private int cantAsientosTertulia;
    private int cantAsientosParaiso;

    // Metodo estático para obtener la única instancia de Sala (Singleton)
    public static Sala getInstance() {
        if (instancia == null) {
            instancia = new Sala();
        }
        return instancia;
    }

    public void setCantAsientosTotales(int cantidad) {
        this.cantAsientosTotales = cantidad;
    }

    public int getCantAsientosPlatea() {
        return cantAsientosPlatea;
    }

    public int getCantAsientosPalcoAlto() {
        return cantAsientosPalcoAlto;
    }

    public int getCantAsientosPalcoBajo() {
        return cantAsientosPalcoBajo;
    }

    public int getCantAsientosCazuela() {
        return cantAsientosCazuela;
    }

    public int getCantAsientosTertulia() {
        return cantAsientosTertulia;
    }

    public int getCantAsientosParaiso() {
        return cantAsientosParaiso;
    }

    // Metodo para calcular el total de asientos asignados
    private int calcularAsientosAsignados() {
        return cantAsientosPlatea + cantAsientosPalcoAlto + cantAsientosPalcoBajo +
                cantAsientosCazuela + cantAsientosTertulia + cantAsientosParaiso;
    }

    // Metodo para validar y asignar asientos en una sección
    private boolean validarAsignacion(int asientos) {
        return calcularAsientosAsignados() + asientos <= cantAsientosTotales;
    }

    public void setCantAsientosPlatea(int asientos) {
        if (validarAsignacion(asientos)) {
            this.cantAsientosPlatea = asientos;
            return;
        }
        System.out.println("Error: No se pueden asignar más asientos de los disponibles.");
    }

    public void setCantAsientosPalcoAlto(int asientos) {
        if (validarAsignacion(asientos)) {
            this.cantAsientosPalcoAlto = asientos;
            return;
        }
        System.out.println("Error: No se pueden asignar más asientos de los disponibles.");
    }

    public void setCantAsientosPalcoBajo(int asientos) {
        if (validarAsignacion(asientos)) {
            this.cantAsientosPalcoBajo = asientos;
            return;
        }
        System.out.println("Error: No se pueden asignar más asientos de los disponibles.");
    }

    public void setCantAsientosCazuela(int asientos) {
        if (validarAsignacion(asientos)) {
            this.cantAsientosCazuela = asientos;
            return;
        }
        System.out.println("Error: No se pueden asignar más asientos de los disponibles.");
    }

    public void setCantAsientosTertulia(int asientos) {
        if (validarAsignacion(asientos)) {
            this.cantAsientosTertulia = asientos;
            return;
        }
        System.out.println("Error: No se pueden asignar más asientos de los disponibles.");
    }

    public void setCantAsientosParaiso(int asientos) {
        if (validarAsignacion(asientos)) {
            this.cantAsientosParaiso = asientos;
            return;
        }
        System.out.println("Error: No se pueden asignar más asientos de los disponibles.");
    }
}
