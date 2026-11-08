package ArquitecturaTXT.servicio;

import java.util.ArrayList;
import java.util.List;

import ArquitecturaTXT.exceptions.RepositorioException;
import ArquitecturaTXT.models.Persona;
import ArquitecturaTXT.repository.IRepositorioPersonas;
import ArquitecturaTXT.repository.RepositorioPersonasTxt;

public class ServicioPersonas implements IServicioPersonas{
	private IRepositorioPersonas repositorio;

    public ServicioPersonas(String ruta) {
        this.repositorio = new RepositorioPersonasTxt(ruta);
    }

    private void datoNoValido(String mensaje) {
        System.err.println("Dato no válido: " + mensaje);
    }

    private void errorPersistencia(RepositorioException e) {
        System.err.println("Error de persistencia: " + e.getMessage());
    }

    @Override
    public List<Persona> listar() {
        try {
            return repositorio.findAll();
        } catch (RepositorioException e) {
            errorPersistencia(e);
            return new ArrayList<>();
        }
    }

    @Override
    public Persona buscar(String nombre) {
        try {
            Persona resultado = repositorio.findById(nombre);
            if (resultado == null) {
                datoNoValido("No existe: " + nombre);
            }
            return resultado;
        } catch (RepositorioException e) {
            errorPersistencia(e);
            return null;
        }
    }

    @Override
    public boolean alta(String nombre, List<Double> notas) {
        boolean hecho = false;
        try {
            if (repositorio.existsById(nombre)) {
                datoNoValido("Ya existe: " + nombre);
            } else {
                repositorio.save(new Persona(nombre, notas));
                hecho = true;
            }
        } catch (IllegalArgumentException e) {
            datoNoValido(e.getMessage());
        } catch (RepositorioException e) {
            errorPersistencia(e);
        }
        return hecho;
    }

    @Override
    public boolean modificarNotas(String nombre, List<Double> notas) {
        boolean hecho = false;
        try {
            if (!repositorio.existsById(nombre)) {
                datoNoValido("No existe: " + nombre);
            } else {
                repositorio.save(new Persona(nombre, notas));
                hecho = true;
            }
        } catch (IllegalArgumentException e) {
            datoNoValido(e.getMessage());
        } catch (RepositorioException e) {
            errorPersistencia(e);
        }
        return hecho;
    }

    @Override
    public boolean baja(String nombre) {
        boolean hecho = false;
        try {
            hecho = repositorio.deleteById(nombre);
            if (!hecho) {
                datoNoValido("No existe: " + nombre);
            }
        } catch (RepositorioException e) {
            errorPersistencia(e);
        }
        return hecho;
    }

    @Override
    public double media(Persona p) {
        if (p == null || p.getNotas() == null || p.getNotas().isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (double nota : p.getNotas()) {
            suma += nota;
        }
        return suma / p.getNotas().size();
    }
}
