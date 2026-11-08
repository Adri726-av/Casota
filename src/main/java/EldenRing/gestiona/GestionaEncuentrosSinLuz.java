package EldenRing.gestiona;

import java.time.LocalDate;
import java.util.List;

import EldenRing.exceptions.EldenException;
import EldenRing.models.Encuentro;
import EldenRing.models.SinLuz;
import EldenRing.repositories.RegistrosSinLuz;

public class GestionaEncuentrosSinLuz {
	public static void main(String[] args) throws EldenException{
		RegistrosSinLuz r = new RegistrosSinLuz();
		SinLuz ardyn = new SinLuz("Ardyn");
		SinLuz selene = new SinLuz("Selene");
		SinLuz kael = new SinLuz("Kael");

		Encuentro e1 = new Encuentro( "Asalto al Bastión Carmesí", LocalDate.of(2025, 3, 10), 8);   
		e1.agregarEnemigos("Caballero Carmesí");
		e1.agregarEnemigos("Tirano de Ceniza");
		Encuentro e2 = new Encuentro("Emboscada en el Bosque Umbrío", LocalDate.of(2025, 3, 14), 5);
		e2.agregarEnemigos("Lobo Siniestro");
		e2.agregarEnemigos("Bandido espectral");
		Encuentro e3 = new Encuentro("Duelo en la Cripta Helada", LocalDate.of(2025, 3, 18), 7);
		e3.agregarEnemigos("Espectro del Hielo");
		e3.agregarEnemigos("Mago congelado");
		Encuentro e4 = new Encuentro( "Resistencia en la Torre Abandonada", LocalDate.of(2025, 3, 20), 6);
		e4.agregarEnemigos("Arquero maldito");
		e4.agregarEnemigos("Guardián de piedra");
		Encuentro e5 = new Encuentro( "Invasión en la Villa Marchita", LocalDate.of(2025, 3, 23), 9);
		e5.agregarEnemigos("Gigante marchito");
		e5.agregarEnemigos("Portador del Plomo");
		Encuentro e6 = new Encuentro( "Caza en el Lago Sombrío", LocalDate.of(2025, 3, 25), 4);
		e6.agregarEnemigos("Serpiente negra");
		e6.agregarEnemigos("Sombra anfibia");
		Encuentro e7 = new Encuentro( "Asalto final al Nexo del Caos", LocalDate.of(2025, 3, 30), 10);
		e7.agregarEnemigos("Señor del Caos");
		e7.agregarEnemigos("Centinela oscuro");
		e7.agregarEnemigos("Eco ardiente");
		
		r.agregarSinLuz(ardyn);
		r.agregarSinLuz(selene);
		r.agregarSinLuz(kael);
		
		r.agregaEncuentro(1, e1);
		r.agregaEncuentro(2, e2);
		r.agregaEncuentro(3, e3);
		
		System.out.println(ardyn.toString());
		System.out.println(selene.toString());
		System.out.println(kael.toString());
		try {
			r.agregaEncuentro(5, e7);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(ardyn.toString());
		r.agregaEncuentro(1, e1);
		System.out.println(ardyn.toString());
		
		// No se añade otro mas porque es un HashSet y no se pueden tener repetidos con el mismo nombre
		
		List<SinLuz> lista = r.getSinLuzDificultad6();
		System.out.println(lista);
	}
}
