package ArquitecturaTXT.controller;

import java.util.ArrayList;
import java.util.List;

import ArquitecturaTXT.models.Persona;
import ArquitecturaTXT.servicio.IServicioPersonas;
import ArquitecturaTXT.servicio.ServicioPersonas;

public class Main {
	private static final String RUTA_DATOS = "datos/personas.txt";

    public static void main(String[] args) {
    	IServicioPersonas servicio = new ServicioPersonas(RUTA_DATOS);

        // 1. Dar de alta
        System.out.println("--- PRUEBA DE ALTAS ---");
        
        List<Double> notasPepe = new ArrayList<>();
        notasPepe.add(1.0);
        notasPepe.add(3.5);
        notasPepe.add(7.7);
        servicio.alta("Pepe", notasPepe);

        List<Double> notasMarcos = new ArrayList<>();
        notasMarcos.add(2.0);
        notasMarcos.add(5.0);
        servicio.alta("Marcos", notasMarcos);

        List<Double> notasCandela = new ArrayList<>();
        notasCandela.add(10.0);
        notasCandela.add(8.1);
        servicio.alta("Candela", notasCandela);

        // 2. Listar todas las personas
        System.out.println("\n--- LISTADO DE PERSONAS ---");
        List<Persona> personas = servicio.listar();
        for (int i = 0; i < personas.size(); i++) {
            Persona p = personas.get(i);
            System.out.println(p + " | Media: " + servicio.media(p));
        }

        // 3. Buscar una persona
        System.out.println("\n--- BÚSQUEDA ---");
        Persona pEncontrada = servicio.buscar("Pepe");
        if (pEncontrada != null) {
            System.out.println("Encontrado: " + pEncontrada);
        }

        // 4. Modificar notas
        System.out.println("\n--- MODIFICACIÓN ---");
        List<Double> nuevasNotasMarcos = new ArrayList<>();
        nuevasNotasMarcos.add(9.0);
        nuevasNotasMarcos.add(9.5);
        servicio.modificarNotas("Marcos", nuevasNotasMarcos);

        // 5. Baja 
        System.out.println("\n--- BAJA ---");
        servicio.baja("Candela");

        // Listar de nuevo para comprobar cambios
        System.out.println("\n--- LISTADO FINAL ---");
        List<Persona> listaFinal = servicio.listar();
        for (int i = 0; i < listaFinal.size(); i++) {
            System.out.println(listaFinal.get(i));
        }
    }
}
