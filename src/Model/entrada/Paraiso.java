package Model.entrada;

import api.IEntrada;
import exceptions.AsientoInexistenteException;

public class Paraiso extends EntradaDecorator {

	private double costo = 150;
	private static final String ubicacion = "paraiso";

	public Paraiso(IEntrada entrada) throws AsientoInexistenteException {
		super(entrada);
		super.setCosto(costo);
		super.setUbicacion(ubicacion);
		super.ocuparAsiento();
	}

	public static String ubicacion() {
		return ubicacion;
	}
}