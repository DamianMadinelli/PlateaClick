package exceptions;

public class ActorNoEncontradoException extends Exception {
    public ActorNoEncontradoException() {
        super("No se encontro el actor");
    }
}
