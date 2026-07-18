package Model.entrada;

import Model.teatro.Funcion;
import api.IEntrada;

public class EntradaBase implements IEntrada {
	
	private final Funcion miFuncion;

    public EntradaBase(Funcion funcion) {
    	miFuncion = funcion;
    }
    
    @Override
	public double obtenerValor() {
        return 0;
	}

	@Override
	public Funcion obtenerFuncion() {
		return miFuncion;
	}

	@Override
	public String obtenerNombreFuncion() {
		return obtenerFuncion().getNombre();
	}

	@Override
	public String getUbicacionLista() {
		return "entrada basica";
	}

	public IEntrada eliminarEntrada (String ubicacion) {
		return this;
	}
	
	public String getUbicacion() {
		return "entrada basica";
	}
	
	public IEntrada getEntrada() {
		return this;
	}

	@Override
	public int contarEntradas() {
		return 0;
	}
	
}