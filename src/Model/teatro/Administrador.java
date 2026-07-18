package Model.teatro;

public class Administrador {
    private static int contador = 0;
    private final int id;
    private String nombre;
    private String apellido;

    public Administrador(String nombre, String apellido) {
        contador++;
        this.id = contador;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}