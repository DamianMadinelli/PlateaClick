package exceptions;

public class AsientoInexistenteException extends Exception{
    public AsientoInexistenteException(){
        super("No existe ese asiento");
    }
}
