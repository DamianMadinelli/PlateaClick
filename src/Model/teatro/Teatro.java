package Model.teatro;

import java.util.*;

import Model.teatro.actores.Actor;
import exceptions.*;

public class Teatro {
    private static Teatro instancia; // Variable estática para almacenar la instancia única de Teatro
    private String nombre;
    private List<Funcion> funciones;

    // Constructor privado para evitar instancias múltiples
    private Teatro() {
        funciones = new ArrayList<>();
    }

    // Metodo estático para obtener la única instancia de Teatro
    public static Teatro getInstance() {
        if (instancia == null) {
            instancia = new Teatro();
        }
        return instancia;
    }

    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

    public void listarFunciones() {
        for (Funcion funcion : funciones) {
            if (funcion.estaDisponible()) {
                System.out.println(funcion.getNombre() + ", tiene asientos disponibles." + " id=" + funcion.getId());
            }
        }
    }

    public String listarFuncionesAsientos(int idFuncion) {
        return "";
    }

    public Funcion obtenerFuncionPorId(int id) throws FuncionNoEncotradaException {
        for (Funcion funcion : funciones) {
            if (funcion.getId() == id) {
                return funcion;
            }
        }
        throw new FuncionNoEncotradaException();
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public void cargarActorAFuncion(int idFuncion, String nombreActor) throws FuncionNoEncotradaException {
        Funcion funcion = obtenerFuncionPorId(idFuncion);
        funcion.agregarActor(nombreActor);
    }

    public void listarActoresDeFuncion(int idFuncion) throws FuncionNoEncotradaException {
        Funcion funcion = obtenerFuncionPorId(idFuncion);
        funcion.listarActores();
    }

    public void eliminarActorDeFuncion(int idFuncion, int idActor) throws FuncionNoEncotradaException {
        Funcion funcion = obtenerFuncionPorId(idFuncion);
        funcion.eliminarActor(idActor);
    }
}
