package EldenRing2.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import EldenRing.exceptions.EldenException;
import EldenRing2.models.Encuentro;
import EldenRing2.models.SinLuz;

public class repoSinLuz {
	private Map<SinLuz, List<Encuentro>> mapa;

	public repoSinLuz(Map<SinLuz, Encuentro> mapa) {
		super();
		this.mapa = new TreeMap<>();
	}
	
	public void agregarSinLuz(SinLuz s, Encuentro e) {
		mapa.put(s, new ArrayList<>());
	}
	
	public SinLuz getSinLuz(int id) throws EldenException {
		SinLuz sinLuz = null;
		for (SinLuz s : mapa.keySet()) {
			if (s.getId() == id) {
				sinLuz = s;
			}
		}
		if (sinLuz == null) {
			throw new EldenException("No existe el SinLuz con el id: " + id);
		}
		return sinLuz;
	}
	
	public void agregaEncuentro(Encuentro e , int id) throws EldenException {
		SinLuz s = getSinLuz(id);
		List<Encuentro> lista = mapa.get(s);
		
		
	}
}
