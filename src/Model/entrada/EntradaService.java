package Model.entrada;

import Model.compra.Compra;
import api.IEntrada;
import exceptions.AsientoInexistenteException;
import exceptions.NoHayAsientosOcupadosException;
import exceptions.TipoEntradaInvalidaException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EntradaService {

    // Registro de decoradores utilizando un Map
    private static final Map<String, Function<IEntrada, IEntrada>> DECORADORES = new HashMap<>();

    static {
        // Agregamos los decoradores utilizando nombres en minúsculas para una búsqueda consistente
        DECORADORES.put("palco alto", t -> {
			try {
				return new PalcoAlto(t);
			} catch (AsientoInexistenteException e) {
				e.printStackTrace();
			}
			return t;
		});
        DECORADORES.put("palco bajo", t -> {
			try {
				return new PalcoBajo(t);
			} catch (AsientoInexistenteException e) {
				e.printStackTrace();
			}
			return t;
		});
        DECORADORES.put("cazuela", t -> {
			try {
				return new Cazuela(t);
			} catch (AsientoInexistenteException e) {
				e.printStackTrace();
			}
			return t;
		});
        DECORADORES.put("tertulia", t -> {
			try {
				return new Tertulia(t);
			} catch (AsientoInexistenteException e) {
				e.printStackTrace();
			}
			return t;
		});
        DECORADORES.put("platea", t -> {
			try {
				return new Platea(t);
			} catch (AsientoInexistenteException e) {
				e.printStackTrace();
			}
			return t;
		});
        DECORADORES.put("paraiso", t -> {
			try {
				return new Paraiso(t);
			} catch (AsientoInexistenteException e) {
				e.printStackTrace();
			}
			return t;
		});
    }

    public void agregarEntrada(String tipoEntrada, IEntrada entradaBase, Compra compra) throws AsientoInexistenteException, TipoEntradaInvalidaException {
        String tipoEntradaNormalizado = tipoEntrada.toLowerCase();
        Function<IEntrada, IEntrada> decorador = DECORADORES.get(tipoEntradaNormalizado);

        if (decorador == null) {
            throw new TipoEntradaInvalidaException();
        }

        IEntrada entradaDecorada = decorador.apply(entradaBase);
        compra.agregarEntrada(entradaDecorada);
    }

	public void eliminarEntrada(String tipoEntrada, IEntrada entradaBase, Compra compra) throws AsientoInexistenteException, NoHayAsientosOcupadosException {
        String tipoEntradaNormalizado = tipoEntrada.toLowerCase();
        entradaBase = entradaBase.eliminarEntrada(tipoEntradaNormalizado);
        compra.repetida(entradaBase);
        if(entradaBase.contarEntradas() == 0) {
        	compra.borrarEntradas(entradaBase);
        }
	}
}