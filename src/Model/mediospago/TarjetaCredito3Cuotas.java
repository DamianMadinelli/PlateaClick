package Model.mediospago;

public class TarjetaCredito3Cuotas extends TarjetaCredito {

	private static final double recargo = 0.12;
	
    public TarjetaCredito3Cuotas(){
    	super(recargo);
    }
    
    public double calcularValor(double costo) {
    	return super.calcularValor(costo);
    }

}