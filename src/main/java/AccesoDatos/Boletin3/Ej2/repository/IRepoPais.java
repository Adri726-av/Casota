package AccesoDatos.Boletin3.Ej2.repository;

import java.io.FileNotFoundException;
import java.util.List;

import AccesoDatos.Boletin3.Ej2.exceptions.PaisRepositorioException;
import AccesoDatos.Boletin3.Ej2.models.Pais;

public interface IRepoPais {
	public List<Pais> findAll() throws FileNotFoundException;
	public Pais getById(String nombre);
	public boolean existById(String nombre);
	public void save(Pais p) throws PaisRepositorioException, FileNotFoundException;
	public boolean deleteById(String nombre) throws FileNotFoundException, PaisRepositorioException;
}
