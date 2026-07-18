package exceptions;

public class MetodoDePagoNoEncontradoException extends Exception {
    public MetodoDePagoNoEncontradoException() {
        super("No se encotro el metodo de pago");
    }
}
