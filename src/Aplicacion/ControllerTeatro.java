package Aplicacion;

import java.time.LocalTime;
import java.util.Date;

import Model.Sala;
import Model.compra.Compra;
import Model.entrada.*;
import Model.teatro.Administrador;
import Model.teatro.Cliente;
import Model.teatro.Funcion;
import Model.teatro.Teatro;
import api.IEntrada;
import api.IMedioPago;
import Model.mediospago.*;
import exceptions.*;

public class ControllerTeatro {
    private Teatro teatro;
    private Sala sala;
    private final EntradaService entradaService;
    private final MedioPagoService medioPagoService;


    public ControllerTeatro() {
        this.teatro = Teatro.getInstance();
        this.sala = Sala.getInstance();
        this.entradaService = new EntradaService();
        this.medioPagoService = new MedioPagoService();
    }

    public void crearFuncion(String nombre, double duracion, Date fecha, LocalTime horario) {
        Funcion funcion = new Funcion(nombre, duracion, fecha, horario);
        teatro.agregarFuncion(funcion);
    }

    public void eliminarActorDeFuncion(int idFuncion, int idActor) throws FuncionNoEncotradaException, ActorNoEncontradoException {
        teatro.eliminarActorDeFuncion(idFuncion, idActor);
    }

    public void cargarActorAFuncion(int idFuncion, String nombre) throws FuncionNoEncotradaException {
        teatro.cargarActorAFuncion(idFuncion, nombre);
    }

    public void listarActoresDeFuncion(int idFuncion) throws FuncionNoEncotradaException {
        teatro.listarActoresDeFuncion(idFuncion);
    }

    public void cargarSala(int cantTotalAsientos) {
        sala.setCantAsientosTotales(cantTotalAsientos);
    }

    public void cargarAsientosPlatea(int cant) {
        sala.setCantAsientosPlatea(cant);
    }

    public void cargarAsientosPalcoAlto(int cant) {
        sala.setCantAsientosPalcoAlto(cant);
    }

    public void cargarAsientosPalcoBajo(int cant) {
        sala.setCantAsientosPalcoBajo(cant);
    }

    public void cargarAsientosCazuela(int cant) {
        sala.setCantAsientosCazuela(cant);
    }

    public void cargarAsientosTertulia(int cant) {
        sala.setCantAsientosTertulia(cant);
    }

    public void cargarAsientosParaiso(int cant) {
        sala.setCantAsientosParaiso(cant);
    }

    public Funcion obtenerFuncionPorId(int id) throws FuncionNoEncotradaException {
        return teatro.obtenerFuncionPorId(id);
    }

    public void listarFunciones() {
        this.teatro.listarFunciones();
    }

    public void mostrarDetallesFuncion(Funcion funcion) throws FuncionNoEncotradaException {
        System.out.println("\n--- Detalles de la Funcion ---");
        System.out.println("ID: " + funcion.getId());
        System.out.println("Nombre: " + funcion.getNombre());
        System.out.println("Duración: " + funcion.getDuracion() + " minutos");
        System.out.println("Fecha: " + funcion.getFecha());
        System.out.println("Horario: " + funcion.getHorario());
        System.out.print("--- ");
        System.out.print("Actores: \n");
        this.teatro.listarActoresDeFuncion(funcion.getId());
        System.out.print("--- ");
        System.out.println("Ubicaciones disponibles: ");
        System.out.println(funcion.listarUbicacionesDisponibles());
    }

    public Compra iniciarCompra(String nombreCliente) {
        Cliente cliente = new Cliente(nombreCliente);
        return cliente.crearCompra();
    }

    public void AgregarEntrada(String tipoEntrada, IEntrada entradaBase, Compra compra) throws AsientoInexistenteException, TipoEntradaInvalidaException {
        entradaService.agregarEntrada(tipoEntrada, entradaBase, compra);
    }
    
    public void eliminarEntrada (String tipoEntrada, IEntrada entradaBase, Compra compra) throws AsientoInexistenteException, NoHayAsientosOcupadosException {
    	entradaService.eliminarEntrada(tipoEntrada, entradaBase, compra);
    }

    public void seleccionarMedioPago(String tipoMedioPago, Compra compra) throws MetodoDePagoNoEncontradoException {
        IMedioPago medioPago = medioPagoService.obtenerMedioPago(tipoMedioPago);
        compra.setMedioDePago(medioPago);
    }

    public void finalizarCompra(Compra compra) throws AsientoInexistenteException, MetodoDePagoNoEncontradoException {
        compra.ejecutarCompra();
    }

    public IEntrada repetido(Compra compra, Funcion funcion) {
        if (compra.tengoEntrada(funcion) != null) {
            return compra.tengoEntrada(funcion);
        }
        return new EntradaBase(funcion);
    }

    public Administrador iniciarSesionAdmin(String nombre, String apellido) {
        return new Administrador(nombre, apellido);
    }

}