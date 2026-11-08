package EldenRing.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import EldenRing.exceptions.EldenException;
import EldenRing.models.Encuentro;
import EldenRing.models.SinLuz;

public class RegistrosSinLuz {
	private Set<SinLuz> sinLuz;

	public RegistrosSinLuz() {
		super();
		this.sinLuz = new TreeSet<>();
	}

	public Set<SinLuz> getSinLuz() {
		return sinLuz;
	}

	public void setSinLuz(Set<SinLuz> sinLuz) {
		this.sinLuz = sinLuz;
	}
	
	public void agregarSinLuz(SinLuz s) {
		sinLuz.add(s);
	}
	
	public SinLuz getSinLuz(int id) throws EldenException {
		SinLuz encontrado = null;
		for(SinLuz s: sinLuz) {
			if (s.getId() == id) {
				encontrado = s;
			}
		}
		if (encontrado == null) {
			throw new EldenException("No existe el SinLuz con el id: " + id);
		}
		return encontrado;
	}
	
	public void agregaEncuentro(int id, Encuentro encuentro) throws EldenException {
		SinLuz encontrado = null;
		for(SinLuz s: sinLuz) {
			if (s.getId() == id) {
				encontrado = s;
			}
		}
		if (encontrado == null) {
			throw new EldenException("El SinLuz que has indicado no existe");
		}
		else {
			encontrado.agregarEncuentro(encuentro);
			System.out.println("Has agregado el SinLuz con el id: " + encontrado.getId());
		}
	}
	
	public List<SinLuz> getSinLuzDificultad6 () {
		List<SinLuz> lista = new ArrayList<SinLuz>();
		for (SinLuz s : sinLuz) {
			Set<Encuentro> encuentros = s.getLista();
			for (Encuentro e : encuentros) {
				if (e.getDificulad() > 6) {
					lista.add(s);
				}
			}
		}
		return lista;
	}
}
