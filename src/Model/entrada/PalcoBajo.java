package Model.entrada;

import api.IEntrada;
import exceptions.AsientoInexistenteException;

public class PalcoBajo extends EntradaDecorator {

	private double costo = 100;
	private static final String ubicacion = "palco bajo";

	public PalcoBajo(IEntrada entrada) throws AsientoInexistenteException {
		super(entrada);
		super.setCosto(costo);
		super.setUbicacion(ubicacion);
		super.ocuparAsiento();
	}

	public static String ubicacion() {
		return ubicacion;
	}

}