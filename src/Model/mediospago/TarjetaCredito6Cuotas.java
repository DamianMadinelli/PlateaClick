package Model.mediospago;

public class TarjetaCredito6Cuotas extends TarjetaCredito {

    private static final double recargo = 0.20;

    public TarjetaCredito6Cuotas() {
        super(recargo);
    }

    public double calcularValor(double costo) {
        return super.calcularValor(costo);
    }

}