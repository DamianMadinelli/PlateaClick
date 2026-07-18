package Model.teatro;

import Model.compra.Compra;

import java.util.*;

public class Cliente {
    private final List<Compra> compras;

    private final String nombre;

    public Cliente(String nombre) {
        compras = new ArrayList<>();
        this.nombre = nombre;
    }

    // agregamos este metodo ya que consideramos que es quien tiene que crear la compra
    public Compra crearCompra() {
        Compra compra = new Compra();
        this.compras.add(compra);
        return compra;
    }

    public String getNombre() {
        return nombre;
    }


}