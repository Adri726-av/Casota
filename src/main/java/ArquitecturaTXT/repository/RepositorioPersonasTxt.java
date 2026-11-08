package ArquitecturaTXT.repository;

import ArquitecturaTXT.exceptions.RepositorioException;
import ArquitecturaTXT.models.Persona;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RepositorioPersonasTxt implements IRepositorioPersonas {
    private static final String SEPARADOR = ",";
    private File fichero;
    private List<Persona> personas;

    public RepositorioPersonasTxt(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta no puede estar vacía");
        }
        this.fichero = new File(ruta);
        this.personas = null; // Se cargará perezosamente (lazy loading)
    }

    private void cargarSiHaceFalta() throws RepositorioException {
        if (this.personas == null) {
            this.personas = leerFichero();
        }
    }

    private int buscarPosicion(List<Persona> lista, String nombre) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    private void comprobarEsFichero() throws RepositorioException {
        if (fichero.exists() && !fichero.isFile()) {
            throw new RepositorioException("La ruta existe pero no es un fichero");
        }
    }

    private Persona fromLinea2Persona(String linea) {
        String[] campos = linea.split(SEPARADOR);
        String nombre = campos[0].trim();
        List<Double> notas = new ArrayList<>();
        for (int i = 1; i < campos.length; i++) {
            notas.add(Double.parseDouble(campos[i].trim()));
        }
        return new Persona(nombre, notas);
    }

    private String fromPersona2Linea(Persona p) {
        StringBuilder sb = new StringBuilder(p.getNombre());
        for (Double nota : p.getNotas()) {
            sb.append(SEPARADOR).append(nota);
        }
        return sb.toString();
    }

    private List<Persona> leerFichero() throws RepositorioException {
        List<Persona> lista = new ArrayList<>();
        if (fichero.exists()) {
            comprobarEsFichero();
            if (!fichero.canRead()) {
                throw new RepositorioException("Sin permiso de lectura");
            }
            Scanner in = null;
            try {
                in = new Scanner(fichero, StandardCharsets.UTF_8.name());
                int numLinea = 0;
                while (in.hasNextLine()) {
                    numLinea++;
                    String linea = in.nextLine();
                    if (!linea.trim().isEmpty()) {
                        try {
                            lista.add(fromLinea2Persona(linea));
                        } catch (IllegalArgumentException e) {
                            throw new RepositorioException("Línea " + numLinea + " no válida", e);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RepositorioException("Error al leer el fichero", e);
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        }
        return lista;
    }

    private void escribeFichero(List<Persona> lista) throws RepositorioException {
        File parentDir = fichero.getParentFile();
        if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
            throw new RepositorioException("No se puede crear la carpeta");
        }
        if (fichero.exists()) {
            comprobarEsFichero();
            if (!fichero.canWrite()) {
                throw new RepositorioException("Sin permiso de escritura");
            }
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(fichero, StandardCharsets.UTF_8));
            for (Persona p : lista) {
                out.println(fromPersona2Linea(p));
            }
            if (out.checkError()) {
                throw new RepositorioException("Fallo durante la escritura");
            }
        } catch (IOException e) {
            throw new RepositorioException("Error al escribir el fichero", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public List<Persona> findAll() throws RepositorioException {
        cargarSiHaceFalta();
        return new ArrayList<>(personas);
    }

    @Override
    public Persona findById(String nombre) throws RepositorioException {
        cargarSiHaceFalta();
        int pos = buscarPosicion(personas, nombre);
        if (pos != -1) {
            return personas.get(pos);
        }
        return null;
    }

    @Override
    public boolean existsById(String nombre) throws RepositorioException {
        cargarSiHaceFalta();
        return buscarPosicion(personas, nombre) != -1;
    }

    @Override
    public int count() throws RepositorioException {
        cargarSiHaceFalta();
        return personas.size();
    }

    @Override
    public void save(Persona persona) throws RepositorioException {
        cargarSiHaceFalta();
        List<Persona> copia = new ArrayList<>(personas);
        int pos = buscarPosicion(copia, persona.getNombre());
        if (pos == -1) {
            copia.add(persona); // Alta
        } else {
            copia.set(pos, persona); // Modificación
        }
        escribeFichero(copia); // 1º Se escribe en disco
        this.personas = copia; // 2º Si va bien, se actualiza memoria
    }

    @Override
    public boolean deleteById(String nombre) throws RepositorioException {
        cargarSiHaceFalta();
        List<Persona> copia = new ArrayList<>(personas);
        int pos = buscarPosicion(copia, nombre);
        boolean borrado = false;
        if (pos != -1) {
            copia.remove(pos);
            escribeFichero(copia); // 1º Se escribe en disco
            this.personas = copia; // 2º Si va bien, se actualiza memoria
            borrado = true;
        }
        return borrado;
    }

    @Override
    public List<Persona> getPersonas() {
        return personas;
    }
}