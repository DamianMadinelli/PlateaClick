package Model.entrada;

import api.IEntrada;
import exceptions.AsientoInexistenteException;

public class Tertulia extends EntradaDecorator {

	private double costo = 40;
	private static final String ubicacion = "tertulia";

	public Tertulia(IEntrada entrada) throws AsientoInexistenteException {
		super(entrada);
		super.setCosto(costo);
		super.setUbicacion(ubicacion);
		super.ocuparAsiento();
	}

	public static String ubicacion() {
		return ubicacion;
	}

}