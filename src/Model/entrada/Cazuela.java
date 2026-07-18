package Model.entrada;

import api.IEntrada;
import exceptions.AsientoInexistenteException;

public class Cazuela extends EntradaDecorator {

	private double costo = 60;
	private static String ubicacion = "cazuela";

	public Cazuela(IEntrada entrada) throws AsientoInexistenteException {
		super(entrada);
		super.setCosto(costo);
		super.setUbicacion(ubicacion);
		super.ocuparAsiento();
	}

	public static String ubicacion() {
		return ubicacion;
	}
}