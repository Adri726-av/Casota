package ArquitecturaTXT.repository;

import java.util.List;

import ArquitecturaTXT.exceptions.RepositorioException;
import ArquitecturaTXT.models.Persona;

public interface IRepositorioPersonas {
	List<Persona> findAll() throws RepositorioException;
    Persona findById(String nombre) throws RepositorioException;
    boolean existsById(String nombre) throws RepositorioException;
    int count() throws RepositorioException;
    void save(Persona persona) throws RepositorioException;
    boolean deleteById(String nombre) throws RepositorioException;
    List<Persona> getPersonas();
}
