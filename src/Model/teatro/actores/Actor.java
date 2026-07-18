package Model.teatro.actores;

public class Actor {
    private static int contadorId = 0;
    private final String nombreArtistico;
    private final int id;

    public Actor(String nombreArtistico) {
        this.id = ++contadorId;
        this.nombreArtistico = nombreArtistico;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return id + "," + nombreArtistico;
    }

}