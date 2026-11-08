package EldenRing.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SinLuz implements Comparable<SinLuz>{
	private int id;
	private static int contador = 1;
	private String nombre;
	private Set<Encuentro> encuentros;
	
	public SinLuz(String nombre) {
		this.id = contador;
		contador++;
		this.nombre = nombre;
		this.encuentros = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		SinLuz.contador = contador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Encuentro> getLista() {
		return encuentros;
	}

	public void setLista(Set<Encuentro> lista) {
		this.encuentros = lista;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(id));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinLuz other = (SinLuz) obj;
		return id == other.id;
	}
	
	public void agregarEncuentro(Encuentro e) {
		encuentros.add(e);
	}

	@Override
	public int compareTo(SinLuz s) {
		return this.nombre.compareTo(s.nombre);
	}

	@Override
	public String toString() {
		return "SinLuz [id=" + id + ", nombre=" + nombre + " " + encuentros + "]";
	}
}
