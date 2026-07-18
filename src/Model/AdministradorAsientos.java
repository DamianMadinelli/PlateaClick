package Model;

import exceptions.AsientoInexistenteException;
import exceptions.NoHayAsientosOcupadosException;

import java.util.HashMap;
import java.util.Map;

public class AdministradorAsientos {

    private Map<String, Integer> asientosTotales;
    private Map<String, Integer> asientosDisponibles;

    public AdministradorAsientos() {
        asientosTotales = new HashMap<>();
        asientosDisponibles = new HashMap<>();
    }

    public void setAsientosASeccion(String nombreSeccion, Integer cantidadDeAsientos) {
        asientosTotales.put(nombreSeccion, cantidadDeAsientos);
        asientosDisponibles.put(nombreSeccion, cantidadDeAsientos);
    }

    public String listarAsientosDisponiblesPorSeccion() {
        StringBuilder resultado = new StringBuilder();
        for (Map.Entry<String, Integer> entrada : asientosDisponibles.entrySet()) {
            String nombreSeccion = entrada.getKey();
            Integer cantidadDisponibles = entrada.getValue();

            resultado.append("Seccion: ").append(nombreSeccion)
                    .append(", Asientos Disponibles: ").append(cantidadDisponibles)
                    .append("\n");
        }

        return resultado.toString();
    }

    public void ocuparAsiento(String tipoAsiento) throws AsientoInexistenteException {
        if (asientosDisponibles.containsKey(tipoAsiento)) {
            int disponibles = asientosDisponibles.get(tipoAsiento);

            if (disponibles > 0) {
                asientosDisponibles.put(tipoAsiento, disponibles - 1);
                System.out.println("Asiento ocupado: " + tipoAsiento);
            } else {
                System.out.println("No hay asientos disponibles para: " + tipoAsiento);
            }
        } else {
            throw new AsientoInexistenteException();
        }
    }

    public void desocuparAsiento(String tipoAsiento) throws AsientoInexistenteException, NoHayAsientosOcupadosException {
        if (asientosDisponibles.containsKey(tipoAsiento)) {
            int ocupados = asientosTotales.get(tipoAsiento) - asientosDisponibles.get(tipoAsiento);

            if (ocupados > 0) {
                asientosDisponibles.put(tipoAsiento, asientosDisponibles.get(tipoAsiento) + 1);
                System.out.println("Asiento desocupado: " + tipoAsiento);
            } else {
                throw new NoHayAsientosOcupadosException();
            }
        } else {
            throw new AsientoInexistenteException();
        }
    }
}
