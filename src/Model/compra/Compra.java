package Model.compra;

import java.util.*;

import Model.teatro.Funcion;
import api.IEntrada;
import api.IMedioPago;
import exceptions.AsientoInexistenteException;
import exceptions.MetodoDePagoNoEncontradoException;

public class Compra {

	public Compra() {
		this.compraProcesada = false;
		this.entradas = new ArrayList<IEntrada>();
	}

	private IMedioPago medioDePago;
	private List<IEntrada> entradas;
	private boolean compraProcesada;

	public double calcularValor() {
		ArrayList<Double> sumasParciales = new ArrayList<Double>();
		int funcionNumero = 0;
		double valorFinal = 0;
		// Cada entrada es por función
		for (IEntrada entrada : entradas) {
			double val = 0;
			val = (entrada.obtenerValor()
					+ (entrada.obtenerFuncion().obtenerCostoFuncion() * entrada.contarEntradas()));
			sumasParciales.add(funcionNumero, val);
			funcionNumero++;
		}
		for (Double sum : sumasParciales) {
			valorFinal += sum;
		}
		return this.medioDePago.calcularValor(valorFinal);
	}

	public void setMedioDePago(IMedioPago medioPago) {
		this.medioDePago = medioPago;
	}

	public void agregarEntrada(IEntrada entrada) {
		this.repetida(entrada);
	}

	public boolean estaProcesada() {
		return this.compraProcesada;
	}

	public void ejecutarCompra() throws AsientoInexistenteException, MetodoDePagoNoEncontradoException {
		System.out.println("imprimiendo ticket");
		System.out.println("valor total " + this.calcularValor());
		for (IEntrada entrada : entradas) {
			System.out.println(entrada.obtenerFuncion().getNombre());
			System.out.println(entrada.getUbicacionLista());
		}
		this.compraProcesada = true;
	}

	public void repetida(IEntrada entrada) {
		for (IEntrada e : entradas) {
			if (e.obtenerFuncion().equals(entrada.obtenerFuncion())) {
				entradas.remove(e);
				break;
			}
		}
		this.entradas.add(entrada);
	}

	public IEntrada tengoEntrada(Funcion funcion) {
		for (IEntrada e : entradas) {
			if (e.obtenerFuncion().equals(funcion)) {
				return e;
			}
		}
		return null;
	}
	
	public void borrarEntradas (IEntrada entrada) {
		for (IEntrada e : entradas) {
			if (e.obtenerFuncion().equals(entrada.obtenerFuncion())) {
				entradas.remove(e);
				return;
			}
		}
	}
}