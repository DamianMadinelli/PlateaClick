package Model.mediospago;


public class TarjetaCredito2Cuotas extends TarjetaCredito {
    
	private static final double recargo = 0.06;
	
    public TarjetaCredito2Cuotas(){
    	super(recargo);
    }
    
    public double calcularValor(double costo) {
    	return super.calcularValor(costo);
    }
}