package AccesoDatos.Boletin3.Ej2.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import AccesoDatos.Boletin3.Ej2.exceptions.PaisRepositorioException;
import AccesoDatos.Boletin3.Ej2.models.Pais;

public class RepoPais implements IRepoPais{
	private File fichero;
	private List<Pais> paises;
	
	public RepoPais(String ruta) {
		if (ruta == null || ruta.isBlank()) {
			throw new IllegalArgumentException("La ruta no puede estar vacia");
		}
		this.fichero = new File(ruta);
		this.paises = new ArrayList<Pais>();
	}
	
	public void cargarSiHaceFalta() throws FileNotFoundException {
		if (this.paises == null) {
			this.paises = leerCSV();
		}
	}
	
	public List<Pais> leerCSV() throws  FileNotFoundException {
		Scanner in = null;
		List<Pais> paises = new ArrayList<>();
		try {
			FileReader fichero = new FileReader(this.fichero);
			in = new Scanner(fichero);
			while (in.hasNextLine()) { 
				String linea = in.nextLine();
				try {
					Pais p = linea2model(linea);
					paises.add(p);
				} catch (PaisRepositorioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return paises;
	}
	
	public Pais linea2model(String linea) throws PaisRepositorioException {
		String[] campos = linea.split(";");
		
		String nombre = null;
		String continente = null;
		Integer poblacion = null;
		String idioma = null;
		String moneda = null;
		
		Pais p = null;
		
		for (int i = 0; i < campos.length; i++) {
			if (i == 0) {
				nombre = campos[i];
			}
			else if (i == 1) {
				continente = campos[i];
			}
			else if (i == 2) {
				poblacion = Integer.parseInt(campos[i]);
			}
			else if (i == 3) {
				idioma = campos[i];
			}
			else if (i == 4) {
				moneda = campos[i];
			}
		}
		
		if (nombre == null || nombre == "" || continente == null || continente == "") {
			throw new PaisRepositorioException("Datos no validos");
		}
		
		else {
			p = new Pais(nombre, continente, poblacion, idioma, moneda);
		}
		return p;
	}

	@Override
	public List<Pais> findAll() throws FileNotFoundException {
		if (this.paises == null) {
			this.paises = leerCSV();
		}
		return this.paises;
	}

	@Override
	public Pais getById(String nombre) {
		Pais pais = null;
		if (this.paises != null || this.paises.size() == 0) {
			for (Pais p: this.paises) {
				if (p.getNombre().equals(nombre)) {
					pais = p;
				}
			}
		}
		return pais;
	}

	@Override
	public boolean existById(String nombre) {
		boolean existe = false;
		Pais pais = getById(nombre);
		if (pais != null) {
			existe = true;
		}
		return existe;
	}
	
	public void escribeCSV(List<Pais> paises) throws PaisRepositorioException {
		try {
			FileWriter fw = new FileWriter(this.fichero);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("Nombre, Continente, Poblacion, Idioma, Moneda");
			for (Pais p: paises) {
				pw.println(p.getNombre() + "," + p.getContinente() + "," + p.getPoblacion() + "," + p.getIdioma() + "," + p.getMoneda());
			}
		}catch (IOException e) {
			throw new PaisRepositorioException("No se ha podido escribir en el fichero");
		}
	}
	
	public int buscarPosicion(List<Pais> paises, String nombre) {
		int pos = -1;
		for (int i = 0; i < paises.size(); i++) {
			if (paises.get(i).getNombre().equals(nombre)) {
				pos = i;
			}
		}
		return pos;
	}
	
	@Override
	public void save(Pais p) throws PaisRepositorioException, FileNotFoundException {
		cargarSiHaceFalta();
		List<Pais> copia = this.paises;
		int pos = buscarPosicion(copia, p.getNombre());
		if (pos == -1) {
			copia.add(p);
		}
		else {
			copia.set(pos, p);
		}
		escribeCSV(copia);
		this.paises = copia;
	}

	@Override
	public boolean deleteById(String nombre) throws FileNotFoundException, PaisRepositorioException {
		cargarSiHaceFalta();
		List<Pais> copia = this.paises;
		int pos = buscarPosicion(copia, nombre);
		boolean borrado = false;
		if (pos == -1) {
			copia.remove(pos);
			escribeCSV(copia);
			this.paises = copia;
			borrado = true;
		}
		return borrado;
	}
}
