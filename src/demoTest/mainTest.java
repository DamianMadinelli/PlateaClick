package demoTest;
import Model.compra.Compra;
import Model.teatro.Funcion;
import api.IEntrada;
import Model.teatro.*;
import java.text.ParseException;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import Aplicacion.ControllerTeatro;
import exceptions.*;

public class mainTest {

    public static void main(String[] args) throws FuncionNoEncotradaException, AsientoInexistenteException, ParseException, ActorNoEncontradoException, NoHayAsientosOcupadosException, TipoEntradaInvalidaException {
        ControllerTeatro controllerTeatro = new ControllerTeatro();

        controllerTeatro.cargarSala(1000);
        controllerTeatro.cargarAsientosPlatea(100);
        controllerTeatro.cargarAsientosPalcoAlto(100);
        controllerTeatro.cargarAsientosPalcoBajo(100);
        controllerTeatro.cargarAsientosCazuela(100);
        controllerTeatro.cargarAsientosTertulia(100);
        controllerTeatro.cargarAsientosParaiso(500);

        controllerTeatro.crearFuncion("Juancito el despertar 1", 200, new Date(1234567890000L), LocalTime.of(14, 30));
        controllerTeatro.crearFuncion("Juancito el despertar 2: Parte 1", 200, new Date(1534567890000L), LocalTime.of(16, 30));

        controllerTeatro.cargarActorAFuncion(1, "Juan el real");
        controllerTeatro.cargarActorAFuncion(2, "Juan terminator");

        Scanner scanner = new Scanner(System.in);
        int opcionPrincipal;

        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("0. Finalizar programa");
            System.out.println("1. Ingresar como Administrador");
            System.out.println("2. Ingresar como Usuario");
            System.out.print("Seleccione una opcion: ");

            opcionPrincipal = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcionPrincipal) {
                case 1:
                    demoPrototipoVistaFinalAdmin(scanner, controllerTeatro);
                    break;
                case 2:
                    demoPrototipoVistaFinalCliente(scanner, controllerTeatro);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, intente de nuevo.");
            }
        } while (opcionPrincipal != 0);

        scanner.close();
    }

    private static void demoPrototipoVistaFinalAdmin(Scanner scanner, ControllerTeatro controllerTeatro) throws FuncionNoEncotradaException, ActorNoEncontradoException, ParseException {

        System.out.print("Por favor, ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Por favor, ingrese su apellido: ");
        String apellido = scanner.nextLine();

        Administrador admin = controllerTeatro.iniciarSesionAdmin(nombre, apellido);

        int opcion;
        do {
            System.out.println("\n--- Menu de Administrador ---");
            System.out.println("0. Volver al menu principal");
            System.out.println("1. Cargar funcion");
            System.out.println("2. Cargar actor a una funcion");
            System.out.println("3. Eliminar actor de una funcion");
            System.out.println("4. Ver detalles de una funcion");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Por favor, ingrese el nombre de la funcion que desea crear: ");
                    String nombreFuncion = scanner.nextLine();

                    System.out.print("Por favor, ingrese la duracion en minutos de la funcion que desea crear: ");
                    int duracion = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingrese la fecha en formato dd-MM-yyyy: ");
                    String fechaString = scanner.nextLine();
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                    Date fecha = formato.parse(fechaString);

                    System.out.println("Ingrese la hora en formato HH:mm: ");
                    String horarioString = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime horario = LocalTime.parse(horarioString, formatter);

                    controllerTeatro.crearFuncion(nombreFuncion, duracion, fecha, horario);
                    System.out.println("Funcion creada exitosamente.");
                    break;

                case 2:
                    controllerTeatro.listarFunciones();
                    System.out.print("Por favor, ingrese el ID de la funcion a la que desea agregar un actor: ");
                    int funcionID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Por favor, ingrese el nombre del actor: ");
                    String nomActor = scanner.nextLine();

                    try {
                        controllerTeatro.cargarActorAFuncion(funcionID, nomActor);
                        System.out.println("Actor agregado a la funcion.");
                    } catch (FuncionNoEncotradaException e) {
                        System.out.println("Funcion no encontrada. Intente con otro ID.");
                    }
                    break;

                case 3:
                    controllerTeatro.listarFunciones();
                    System.out.print("Por favor, ingrese el ID de la funcion de la que desea eliminar un actor: ");
                    int funcionIDEliminar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Actores disponibles en la funcion:");
                    try {
                        controllerTeatro.listarActoresDeFuncion(funcionIDEliminar);
                        System.out.print("Por favor, ingrese el ID del actor a eliminar: ");
                        int idActorEliminar = scanner.nextInt();
                        scanner.nextLine();

                        controllerTeatro.eliminarActorDeFuncion(funcionIDEliminar, idActorEliminar);
                        System.out.println("Actor eliminado de la funcion.");
                    } catch (FuncionNoEncotradaException e) {
                        System.out.println("Funcion no encontrada. Intente con otro ID.");
                    } catch (ActorNoEncontradoException e) {
                        System.out.println("Actor no encontrado en esta funcion.");
                    }
                    break;

                case 4:
                    controllerTeatro.listarFunciones();
                    System.out.print("Ingrese el ID de la funcion para ver los detalles: ");
                    funcionID = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Funcion funcion = controllerTeatro.obtenerFuncionPorId(funcionID);
                        controllerTeatro.mostrarDetallesFuncion(funcion);
                    } catch (FuncionNoEncotradaException e) {
                        System.out.println("Funcion no encontrada. Intente con otro ID.");
                    }
                    break;

                case 0:
                    System.out.println("Regresando al menu principal...");
                    return;  // Salir del submenú de administrador

                default:
                    System.out.println("Opcion invalida. Por favor, intente de nuevo.");
            }
        } while (true);
    }

    private static void demoPrototipoVistaFinalCliente(Scanner scanner, ControllerTeatro controllerTeatro) throws FuncionNoEncotradaException, AsientoInexistenteException, NoHayAsientosOcupadosException, TipoEntradaInvalidaException {
        System.out.print("Por favor, ingrese su nombre: ");
        String nombre = scanner.nextLine();
        Compra compra = controllerTeatro.iniciarCompra(nombre);

        int opcion;
        do {
            System.out.println("\n--- Menu de Usuario ---");
            System.out.println("0. Volver al menu principal");
            System.out.println("1. Agregar entradas");
            System.out.println("2. Finalizar compra");
            System.out.println("3. Iniciar nueva compra");
            System.out.println("4. Ver detalles de una funcion");
            System.out.println("5. Eliminar entrada");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (compra.estaProcesada()) {
                        System.out.println("Compra ya procesada. Inicie una nueva compra.");
                        break;
                    }
                    controllerTeatro.listarFunciones();
                    System.out.print("Por favor, ingrese el ID de la funcion para comprar entradas: ");
                    int funcionID = scanner.nextInt();
                    System.out.println();
                    scanner.nextLine();

                    try {
                        Funcion funcion = controllerTeatro.obtenerFuncionPorId(funcionID);
                        System.out.println();
                        System.out.print("--- Ubicaciones Disponibles ---\n");
                        System.out.println(funcion.listarUbicacionesDisponibles());
                        System.out.print("Por favor, ingrese el nombre de la ubicacion que desea: ");
                        String tipoEntrada = scanner.nextLine();
                        IEntrada miEntrada = controllerTeatro.repetido(compra, funcion);
                        controllerTeatro.AgregarEntrada(tipoEntrada, miEntrada, compra);
                        System.out.println("Entrada agregada exitosamente.");
                    } catch (FuncionNoEncotradaException e) {
                        System.out.println("Funcion no encontrada. Intente con otro ID.");
                    } catch (AsientoInexistenteException e) {
                        System.out.println("Asiento no disponible. Seleccione otra ubicacion.");
                    } catch (TipoEntradaInvalidaException e) {
                        System.out.println("Entrada no valida. Seleccione una de las mostradas en pantalla.");
                    }

                    break;

                case 2:
                    System.out.println();
                    System.out.print("Por favor, ingrese el medio de pago a utilizar exactamente como se muestra: ");
                    System.out.println("\n--- Medios de Pago Disponibles ---");
                    System.out.println("1. Efectivo");
                    System.out.println("2. Tarjeta de Credito - 2 Cuotas");
                    System.out.println("3. Tarjeta de Credito - 3 Cuotas");
                    System.out.println("4. Tarjeta de Credito - 6 Cuotas");
                    System.out.println("5. Tarjeta de Debito");
                    String medioPago = scanner.nextLine();

                    try {
                        controllerTeatro.seleccionarMedioPago(medioPago, compra);
                    } catch (MetodoDePagoNoEncontradoException e) {
                        System.out.println("Metodo de pago no valido. Intente de nuevo.");
                    }

                    System.out.println();
                    System.out.println(" Valor a pagar: " + compra.calcularValor());

                    System.out.println();
                    System.out.println(" Desea pagar? (Si/No) ");
                    String respuesta = scanner.nextLine();

                    if("Si".equalsIgnoreCase(respuesta)) {
                        try {
                            controllerTeatro.finalizarCompra(compra);
                            System.out.println("Compra finalizada exitosamente.");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Compra no finalizada.");
                    }

                    break;

                case 3:
                    if (compra.estaProcesada()) {
                        compra = controllerTeatro.iniciarCompra(nombre);
                        System.out.println("Nueva compra iniciada.");
                    } else {
                        System.out.println("La compra actual sigue activa.");
                    }
                    break;

                case 4:
                    controllerTeatro.listarFunciones();
                    System.out.print("Ingrese el ID de la funcion para ver los detalles: ");
                    funcionID = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Funcion funcion = controllerTeatro.obtenerFuncionPorId(funcionID);
                        System.out.println("\n--- Detalles de la Funcion ---");
                        System.out.println("ID: " + funcion.getId());
                        System.out.println("Nombre: " + funcion.getNombre());
                        System.out.println("Duracion: " + funcion.getDuracion() + " minutos");
                        System.out.println("Fecha: " + funcion.getFecha());
                        System.out.println("Horario: " + funcion.getHorario());
                        System.out.print("--- ");
                        System.out.print("Actores: \n");
                        controllerTeatro.listarActoresDeFuncion(funcion.getId());
                        System.out.print("--- ");
                        System.out.println("Ubicaciones disponibles: ");
                        System.out.println(funcion.listarUbicacionesDisponibles());
                    } catch (FuncionNoEncotradaException e) {
                        System.out.println("Funcion no encontrada. Intente con otro ID.");
                    }
                    break;
                    
                case 5:
                    System.out.println("Eliminar entrada ");
                    controllerTeatro.listarFunciones();
                    System.out.print("Por favor, ingrese el ID de la funcion para eliminar entradas: ");
                    int funcionIDElimi = scanner.nextInt();
                    System.out.println();
                    scanner.nextLine();

                    try {
                        Funcion funcionElim = controllerTeatro.obtenerFuncionPorId(funcionIDElimi);
                        System.out.println();
                        System.out.print("--- Ubicaciones Disponibles ---\n");
                        System.out.println(funcionElim.listarUbicacionesDisponibles());
                        System.out.print("Por favor, ingrese el nombre de la ubicacion que desea eliminar: ");
                        String tipoEntradaElim = scanner.nextLine();
                        IEntrada miEntradaEL = controllerTeatro.repetido(compra, funcionElim);
                        controllerTeatro.eliminarEntrada (tipoEntradaElim, miEntradaEL, compra);
                        System.out.println("entradas actuales");
                        System.out.println(miEntradaEL.getUbicacionLista());
                    } catch (FuncionNoEncotradaException e) {
                        System.out.println("Funcion no encontrada. Intente con otro ID.");
                    } catch (AsientoInexistenteException e) {
                        System.out.println("Asiento no disponible. Seleccione otra ubicacion.");
                    }
                    break;
                case 0:
                    System.out.println("Regresando al menu principal...");
                    return;  // Salir del submenú de usuario

                default:
                    System.out.println("Opcion invalida. Por favor, intente de nuevo.");
            }
        } while (true);
    }
}
