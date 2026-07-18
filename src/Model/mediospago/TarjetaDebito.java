package Model.mediospago;

import api.IMedioPago;

public class TarjetaDebito implements IMedioPago {

    public double calcularValor(double costo) {
        return costo;
    }

}