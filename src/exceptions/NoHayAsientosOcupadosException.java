package exceptions;

public class NoHayAsientosOcupadosException extends Exception{
    public NoHayAsientosOcupadosException(){
        super("No existen asientos ocupados");
    }
}
