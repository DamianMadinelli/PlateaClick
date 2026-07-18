package Model.mediospago;

import api.IMedioPago;

public abstract class TarjetaCredito implements IMedioPago{

    private final double recargo;
    
    public TarjetaCredito(double recargo){
    	this.recargo = recargo;
    }
    
    public double calcularValor(double costo) {
    	return costo * (1 + recargo);
    }

}