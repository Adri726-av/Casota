package ArquitecturaTXT.models;

import java.util.List;

public class Persona {
	private static final Double NOTA_MIN = 0.0;
    private static final Double NOTA_MAX = 10.0;
    
    private String nombre;
    private List<Double> notas;

    public Persona(String nombre, List<Double> notas) {
        setNombre(nombre);
        setNotas(notas);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        if (notas != null) {
            for (Double nota : notas) {
                if (nota == null || nota < NOTA_MIN || nota > NOTA_MAX) {
                    throw new IllegalArgumentException("Nota no válida: " + nota);
                }
            }
        }
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Persona{nombre='" + nombre + "', notas=" + notas + "}";
    }
}
