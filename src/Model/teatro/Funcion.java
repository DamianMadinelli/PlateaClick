package Model.teatro;

import java.time.LocalTime;
import java.util.Date;

import Model.AdministradorAsientos;
import Model.entrada.*;
import Model.teatro.actores.*;
import Model.Sala;

public class Funcion {

    private static int contadorId = 0; // Contador estático para los IDs
    private final int id;
    private final String nombre;
    private final AdministradorAsientos asientos;
    private boolean estaDisponible = true;  // Si esta lleno que cambie de estado.
    private final Sala sala;
    private Date fecha;
    private LocalTime horario;
    private GrupoDeActores actores;
    private double duracion;
	private double costoPorMin;

	public Funcion(String nombre, double duracion, Date fecha, LocalTime horario) {
		this.sala = Sala.getInstance();
		this.actores = new GrupoDeActores();
		this.duracion = duracion;
		this.id = ++contadorId; // Asigna el valor incrementado al ID de la instancia
		this.nombre = nombre;
		this.fecha = fecha;
		this.horario = horario;
		// TODO Actualizar el constructor
		costoPorMin = 1;

		// Inicializar un Administrador de Asientos/Entradas. En realidad depende de los
		// valores de la clase "Sala"
		asientos = new AdministradorAsientos();

        // clase inicializador aparte
        asientos.setAsientosASeccion(Platea.ubicacion(), sala.getCantAsientosPlatea());
        asientos.setAsientosASeccion(PalcoAlto.ubicacion(), sala.getCantAsientosPalcoAlto());
        asientos.setAsientosASeccion(PalcoBajo.ubicacion(), sala.getCantAsientosPalcoBajo());
        asientos.setAsientosASeccion(Paraiso.ubicacion(), sala.getCantAsientosParaiso());
        asientos.setAsientosASeccion(Tertulia.ubicacion(), sala.getCantAsientosTertulia());
        asientos.setAsientosASeccion(Cazuela.ubicacion(), sala.getCantAsientosCazuela());
    }

	public static int getContadorId() {
		return contadorId;
	}

	public AdministradorAsientos getAsientos() {
		return asientos;
	}

	public boolean isEstaDisponible() {
		return estaDisponible;
	}

    public Date getFecha() {
        return fecha;
    }

	public LocalTime getHorario() {
		return horario;
	}

	public GrupoDeActores getActores() {
		return actores;
	}

	public void agregarActor(String nombreActor) {
		actores.addActor(nombreActor);
	}

	public void eliminarActor(int idActor) {
		actores.eliminarActor(idActor);
	}

	public double getDuracion() {
		return this.duracion;
	}

	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return this.id;
	}

	public String listarUbicacionesDisponibles() {
		return asientos.listarAsientosDisponiblesPorSeccion();
	}

	public boolean estaDisponible() {
		return this.estaDisponible;
	}

	public AdministradorAsientos getAdministradorAsientos() {
		return asientos;
	}

	public void listarActores() {
		actores.listarActores();
	}

	public void setCostoPorMin(double costo) {
		costoPorMin = costo;
	}

	public double obtenerCostoFuncion() {
		return duracion * costoPorMin;
	}

}
