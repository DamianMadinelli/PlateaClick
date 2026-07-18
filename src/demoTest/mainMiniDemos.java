package demoTest;

import Model.mediospago.Efectivo;
import Model.compra.Compra;
import Model.entrada.Cazuela;
import Model.entrada.EntradaBase;
import Model.teatro.Funcion;
import Model.teatro.actores.GrupoDeActores;
import Model.entrada.PalcoAlto;
import Model.entrada.PalcoBajo;
import Model.entrada.Paraiso;
import Model.entrada.Platea;
import Model.mediospago.TarjetaCredito2Cuotas;
import Model.mediospago.TarjetaCredito3Cuotas;
import Model.mediospago.TarjetaCredito6Cuotas;
import Model.mediospago.TarjetaDebito;
import api.IEntrada;
import api.IMedioPago;
import Model.teatro.*;

import java.util.Scanner;

import Aplicacion.ControllerTeatro;
import exceptions.AsientoInexistenteException;
import exceptions.FuncionNoEncotradaException;
import exceptions.MetodoDePagoNoEncontradoException;
import exceptions.NoHayAsientosOcupadosException;
import exceptions.TipoEntradaInvalidaException;

public class mainMiniDemos {

	public static void main(String[] args) throws FuncionNoEncotradaException, AsientoInexistenteException, NoHayAsientosOcupadosException {
		demoAdministrador();
		// demoPrototipoVistaFinalCliente();
		// demoMediosDePago();
		// demoMediosDeActores();
		demoEntradaDecorator();
		Administrador administrador = new Administrador("Juan", "Cito");

	}

	private static void demoPrototipoVistaFinalCliente() throws FuncionNoEncotradaException,
			AsientoInexistenteException, TipoEntradaInvalidaException, MetodoDePagoNoEncontradoException {
		ControllerTeatro controllerTeatro = new ControllerTeatro();

		controllerTeatro.cargarSala(1000);
		controllerTeatro.cargarAsientosPlatea(100);
		controllerTeatro.cargarAsientosPalcoAlto(100);
		controllerTeatro.cargarAsientosPalcoBajo(100);
		controllerTeatro.cargarAsientosCazuela(100);
		controllerTeatro.cargarAsientosTertulia(100);
		controllerTeatro.cargarAsientosParaiso(500);

		controllerTeatro.crearFuncion("Juancito el despertar 1", 200, null, null);
		controllerTeatro.crearFuncion("Juancito el despertar 2: Parte 1", 200, null, null);

		Scanner scanner = new Scanner(System.in); // Crear un escáner para leer la entrada del usuario

		System.out.print("Por favor, ingresa tu nombre: "); // Solicitar el nombre
		String nombre = scanner.nextLine();
		Compra compra = controllerTeatro.iniciarCompra(nombre);

		int opcion;

		do {
			System.out.println("\n--- Menú de Opciones ---");
			System.out.println("1. agregar entradas");
			System.out.println("2. finalizar compra");
			System.out.println("3. iniciar nueva Compra");
			System.out.println("0. Salir");
			System.out.print("Selecciona una opción: ");

			opcion = scanner.nextInt(); // Lee la opción ingresada por el usuario
			scanner.nextLine();

			switch (opcion) {
			case 1:
				System.out.println("Has seleccionado agregar entradas.");
				controllerTeatro.listarFunciones();

				System.out.print("Por favor, ingresa el id de la funcion para comprar entradas: ");
				int funcionID = scanner.nextInt();

				Funcion funcion = controllerTeatro.obtenerFuncionPorId(funcionID);
				System.out.println(funcion.listarUbicacionesDisponibles());
				IEntrada miEntrada = controllerTeatro.repetido(compra, funcion);
				System.out.print("Por favor, ingresa el nombre de la entrada que quiere comprar: ");
				scanner.nextLine();
				String entradaID = scanner.nextLine();

				controllerTeatro.AgregarEntrada(entradaID, miEntrada, compra);

				break;
			case 2:
				System.out.print("Por favor, ingresa el medio de pago a utilizar: ");
				String medioPagoID = scanner.nextLine();

				controllerTeatro.seleccionarMedioPago(medioPagoID, compra);

				controllerTeatro.finalizarCompra(compra);

				break;

			case 3:
				if (compra.estaProcesada()) {
					compra = controllerTeatro.iniciarCompra(nombre);
				} else {
					System.out.println("la compra actual sigue activa");
				}
				break;
			case 0:
				System.out.println("Saliendo del programa...");
				break;
			default:
				System.out.println("Opción inválida. Por favor, intenta de nuevo.");
			}
		} while (opcion != 0); // El menú seguirá repitiéndose hasta que se elija la opción 0

		scanner.close();
	}

	private static void demoEntradaDecorator() throws AsientoInexistenteException, NoHayAsientosOcupadosException {
		System.out.println("--- Inicia DEMO DECORATOR --- \n\n");
		Funcion funcion = new Funcion("Mi obra", 75.2, null, null);
		Funcion funcion2 = new Funcion("Mi obra 2", 75.2, null, null);
		Funcion funcion3 = new Funcion("Mi obra 3", 75.2, null, null);
		IEntrada miEntrada = new EntradaBase(funcion);
		IEntrada miEntrada2 = new EntradaBase(funcion);
		IEntrada miEntrada3 = new EntradaBase(funcion2);
		IEntrada miEntrada4 = new EntradaBase(funcion3);
		// Supongamos que compro 2 plateas
		miEntrada = new Platea(miEntrada);
		miEntrada = new Platea(miEntrada);
		// ahora obtengo el costo de estas
		System.out.println(miEntrada.obtenerValor());
		miEntrada = new PalcoAlto(miEntrada);
		miEntrada = new PalcoBajo(miEntrada);
		// segunda entrada pero la misma funcion
		miEntrada2 = new PalcoAlto(miEntrada2);
		miEntrada2 = new PalcoBajo(miEntrada2);
		miEntrada3 = new PalcoAlto(miEntrada3);
		miEntrada3 = new Paraiso(miEntrada3);
		miEntrada4 = new PalcoAlto(miEntrada4);
		miEntrada4 = new Cazuela(miEntrada4);
		System.out.println(miEntrada.obtenerValor());
		System.out.println(funcion.listarUbicacionesDisponibles());
		System.out.println(miEntrada.obtenerFuncion().getNombre());
		System.out.println(miEntrada.getUbicacionLista());
		System.out.println("Simulo la ejecuciï¿½n de una compra");
		System.out.println(funcion.listarUbicacionesDisponibles());
		System.out.println("Antes de eliminar: \n");
		System.out.println(miEntrada.getUbicacionLista());
		miEntrada = miEntrada.eliminarEntrada(Paraiso.ubicacion());
		System.out.println(miEntrada.getUbicacionLista() + "\n\n");
		miEntrada = miEntrada.eliminarEntrada(Platea.ubicacion());
		miEntrada = miEntrada.eliminarEntrada(Platea.ubicacion());
		miEntrada = miEntrada.eliminarEntrada(PalcoAlto.ubicacion());
		miEntrada = miEntrada.eliminarEntrada(PalcoAlto.ubicacion());
		miEntrada = miEntrada.eliminarEntrada(PalcoAlto.ubicacion());
		miEntrada = miEntrada.eliminarEntrada(Paraiso.ubicacion());
		System.out.println(miEntrada.getUbicacionLista() + "\n\n");
		System.out.println(funcion.listarUbicacionesDisponibles());
		Compra miCompra = new Compra();
		miCompra.agregarEntrada(miEntrada2);
//		miCompra.agregarEntrada(miEntrada3);
//		miCompra.agregarEntrada(miEntrada4);
		miCompra.setMedioDePago(new Efectivo());
		System.out.println(miCompra.calcularValor());
		miCompra.setMedioDePago(new TarjetaDebito());
		System.out.println(miCompra.calcularValor());
		miCompra.setMedioDePago(new TarjetaCredito2Cuotas());
		System.out.println(miCompra.calcularValor());
		miCompra.setMedioDePago(new TarjetaCredito3Cuotas());
		System.out.println(miCompra.calcularValor());
		miCompra.setMedioDePago(new TarjetaCredito6Cuotas());
		System.out.println(miCompra.calcularValor());
	}

	public static void demoAdministrador() throws FuncionNoEncotradaException {
		System.out.println("--- Inicia DEMO Administrador --- \n\n");
		ControllerTeatro controllerTeatro = new ControllerTeatro();
		controllerTeatro.crearFuncion("Juancito el despertar 1", 120.5, null, null);
		controllerTeatro.crearFuncion("Juancito el despertar 2: Parte 1", 90.2, null, null);

		controllerTeatro.cargarSala(1000);
		controllerTeatro.cargarAsientosPlatea(100);
		controllerTeatro.cargarAsientosPalcoAlto(100);
		controllerTeatro.cargarAsientosPalcoBajo(100);
		controllerTeatro.cargarAsientosCazuela(100);
		controllerTeatro.cargarAsientosTertulia(100);
		controllerTeatro.cargarAsientosParaiso(500);
	}

	private static void demoMediosDeActores() throws FuncionNoEncotradaException {
		System.out.println("--- Inicia DEMO de Actores --- \n\n");
		ControllerTeatro controllerTeatro = new ControllerTeatro();
		controllerTeatro.cargarActorAFuncion(1, "Meli La Magica");
		controllerTeatro.cargarActorAFuncion(1, "Damian El Magnifico");
		controllerTeatro.cargarActorAFuncion(1, "Gaby La Esplendida");
		controllerTeatro.cargarActorAFuncion(1, "Mati El Loco");
		controllerTeatro.cargarActorAFuncion(1, "Kim El Poderoso");
		controllerTeatro.cargarActorAFuncion(1, "Fran El Escapista");
		controllerTeatro.listarActoresDeFuncion(1);
	}

	public static void demoMediosDePago() {
		System.out.println("--- Inicia DEMO Medios de Pago --- \n\n");
		double precio = 10;
		IMedioPago testMedio1, testMedio2, testMedio3, testMedio4, testMedio5;
		testMedio1 = new Efectivo();
		testMedio2 = new TarjetaDebito();
		testMedio3 = new TarjetaCredito2Cuotas();
		testMedio4 = new TarjetaCredito3Cuotas();
		testMedio5 = new TarjetaCredito6Cuotas();

		System.out.println("Precio base: " + precio);
		System.out.println("Pago con efectivo: " + testMedio1.calcularValor(precio));
		System.out.println("Pago con debito: " + testMedio2.calcularValor(precio));
		System.out.println("Pago con tarjeta 2 cuotas: " + testMedio3.calcularValor(precio));
		System.out.println("Pago con tarjeta 3 cuotas: " + testMedio4.calcularValor(precio));
		System.out.println("Pago con tarjeta 6 cuotas: " + testMedio5.calcularValor(precio));
	}

}