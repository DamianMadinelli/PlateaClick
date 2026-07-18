package Model.mediospago;

import api.IMedioPago;

public class Efectivo implements IMedioPago {
	
	private static double descuento = 0.1;

    public double calcularValor(double costo) {
        return costo * (1 - descuento);
    }

}