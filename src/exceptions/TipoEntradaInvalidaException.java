package exceptions;

public class TipoEntradaInvalidaException extends Exception{
    public TipoEntradaInvalidaException(){
        super("No se encontro la entrada indicada");
    }
}
