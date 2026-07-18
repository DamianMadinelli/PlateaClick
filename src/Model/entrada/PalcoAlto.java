package Model.entrada;

import api.IEntrada;
import exceptions.AsientoInexistenteException;

public class PalcoAlto extends EntradaDecorator {

	private double costo = 80;
    private static final String ubicacion = "palco alto";

	public PalcoAlto(IEntrada entrada) throws AsientoInexistenteException {
		super(entrada);
		super.setCosto(costo);
		super.setUbicacion(ubicacion);
		super.ocuparAsiento();
	}

	public static String ubicacion() {
		return ubicacion;
	}

}