package Model.teatro.actores;

import java.util.*;

public class GrupoDeActores {

    private List<Actor> actores;

    public GrupoDeActores() {
        actores = new ArrayList<Actor>();
    }

    public void addActor(String nombreArtistico) {
        actores.add(new Actor(nombreArtistico));
    }

    public void listarActores() {
        for (Actor actor : actores) {
            System.out.println(actor.getNombreArtistico() + ", id: " + actor.getId());
        }
    }

    public void eliminarActor(int id) {
        for (Actor actor : actores) {
            if (actor.getId() == id) {
                actores.remove(actor);
                return;
            }
        }
    }

}