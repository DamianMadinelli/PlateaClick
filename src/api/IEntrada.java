package api;
import Model.teatro.Funcion;
import exceptions.AsientoInexistenteException;
import exceptions.NoHayAsientosOcupadosException;

public interface IEntrada {

	public double obtenerValor();

	public String getUbicacionLista();

	public Funcion obtenerFuncion();

	public String obtenerNombreFuncion();

	public String getUbicacion();

	public IEntrada eliminarEntrada(String ubicacion) throws AsientoInexistenteException, NoHayAsientosOcupadosException;

	public IEntrada getEntrada();

	public int contarEntradas ();

}