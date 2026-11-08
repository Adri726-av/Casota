package EldenRing.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Encuentro {
	
	private String nombre;
	private LocalDate fechaEncuentro;
	private int dificulad;
	private List<String> enemigos;
	
	public Encuentro(String nombre, LocalDate fechaEncuentro, int dificulad) {
		super();
		this.nombre = nombre;
		this.fechaEncuentro = fechaEncuentro;
		this.dificulad = dificulad;
		this.enemigos = new ArrayList<>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaEncuentro() {
		return fechaEncuentro;
	}
	public void setFechaEncuentro(LocalDate fechaEncuentro) {
		this.fechaEncuentro = fechaEncuentro;
	}
	public int getDificulad() {
		return dificulad;
	}
	public void setDificulad(int dificulad) {
		this.dificulad = dificulad;
	}
	public List<String> getEnemigos() {
		return enemigos;
	}
	public void setEnemigos(List<String> enemigos) {
		this.enemigos = enemigos;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Encuentro other = (Encuentro) obj;
		return Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Encuentro [nombre=" + nombre + ", fechaEncuentro=" + fechaEncuentro + ", dificulad=" + dificulad
				+ ", enemigos=" + enemigos + "]";
	}
	public void agregarEnemigos(String nombre) {
		enemigos.add(nombre);
	}
}
