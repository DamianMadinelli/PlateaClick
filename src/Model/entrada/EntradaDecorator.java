package Model.entrada;

import Model.teatro.Funcion;
import api.IEntrada;
import exceptions.AsientoInexistenteException;
import exceptions.NoHayAsientosOcupadosException;

public abstract class EntradaDecorator implements IEntrada {

    private double costo;
    private IEntrada entrada;
    private String ubicacion;

    public EntradaDecorator(IEntrada entrada) throws AsientoInexistenteException {
        this.entrada = entrada;
    }

    // Metodo Mamushca para obtener la suma del costo de todos los asientos
    @Override
    public double obtenerValor() {
        return entrada.obtenerValor() + costo;
    }

    // Metodo Mamushca para obtener la funcion que se encuentra en la entrada
    // base/hoja
    @Override
    public Funcion obtenerFuncion() {
        return entrada.obtenerFuncion();
    }

    @Override
    public String obtenerNombreFuncion() {
        return entrada.obtenerNombreFuncion();
    }

    public String getUbicacionLista() {
        return ubicacion + ";" + entrada.getUbicacionLista();
    }

    protected void setCosto(double costo) {
        this.costo = costo;
    }

    protected void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    protected void ocuparAsiento() throws AsientoInexistenteException {
        Funcion miFuncion = this.obtenerFuncion();
        miFuncion.getAdministradorAsientos().ocuparAsiento(ubicacion);
    }

    public IEntrada getEntrada() {
        return entrada;
    }

    public IEntrada eliminarEntrada(String ubicacion) throws AsientoInexistenteException, NoHayAsientosOcupadosException {
        if (getUbicacion().equals(ubicacion)) {
            obtenerFuncion().getAdministradorAsientos().desocuparAsiento(ubicacion);
            return entrada;
        } else if (entrada.getUbicacion().equals(ubicacion)) {
            obtenerFuncion().getAdministradorAsientos().desocuparAsiento(entrada.getUbicacion());
            entrada = entrada.getEntrada();
            return this;
        }
        entrada.eliminarEntrada(ubicacion);
        return this;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int contarEntradas() {
        return 1 + entrada.contarEntradas();
    }

}