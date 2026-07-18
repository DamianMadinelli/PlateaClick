package Model.entrada;

import api.IEntrada;
import exceptions.AsientoInexistenteException;

public class Platea extends EntradaDecorator {

    private double costo = 120;
    private static final String ubicacion = "platea";

    public Platea(IEntrada entrada) throws AsientoInexistenteException {
        super(entrada);
        super.setCosto(costo);
        super.setUbicacion(ubicacion);
        super.ocuparAsiento();
    }

    public static String ubicacion() {
        return ubicacion;
    }

}