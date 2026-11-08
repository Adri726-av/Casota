package Ej3;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Gestiona {
	
	private static final Logger logger = LogManager.getLogger(Gestiona.class);
	
	public static void main(String[] args) {
		String padre = System.getProperty("user.home");
		
		File directorio = new File(padre, "miDirectorio");
		
		boolean creado = directorio.mkdir();
		
		if (creado) {
			logger.debug("Directorio creado: " + directorio.getPath());
		}
		
		String ruta = directorio.getPath();
		
		File fichero1 = new File(ruta, "lectura.txt");
		try {
			fichero1.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (fichero1.exists()) {
			logger.debug("Fichero creado: lectura.txt");
		}
		
		File fichero2 = new File(ruta, "normal.txt");
		try {
			fichero2.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (fichero2.exists()) {
			logger.debug("Fichero creado: normal.txt");
		}
		
		boolean soloLectura = fichero1.setReadOnly();
		
		if (soloLectura) {
			logger.debug("lectura.txt modificado a solo lectura");
		}
		
		logger.debug(fichero1.getName() + " -> lectura: " + fichero1.canRead() + " | escritura: " + fichero1.canWrite() + " | ejecución: " + fichero1.canExecute());
		
		logger.debug(fichero2.getName() + " -> lectura: " + fichero2.canRead() + " | escritura: " + fichero2.canWrite() + " | ejecución: " + fichero2.canExecute());
		
		File fichero3 = new File(ruta, "renombrado.txt");
		
		fichero2.renameTo(fichero3);
		
		logger.debug("normal.txt renombrado a renombrado.txt");
		
		boolean borrar = fichero1.delete();
		
		if (borrar) {
			logger.debug("No se ha podido borrar el archivo lectura.txt");
		}
		
		fichero1.setWritable(true);
		
		logger.debug("Permiso de escritura restaurado en lectura.txt");
		
		boolean borrar2 = fichero1.delete();
		
		if (!(fichero1.exists())) {
			logger.debug("El fichero " + fichero1.getName() + " se ha podido borrar correctamente");
		}
		
		logger.debug("Contenido final de mi directorio");
		
		for (File f : directorio.listFiles()) {
			logger.debug(f.getName());
		}
	}
}
