package AccesoDatos.Boletin3.Ej2.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import AccesoDatos.Boletin3.Ej2.repository.IRepoPais;
import AccesoDatos.Boletin3.Ej2.repository.RepoPais;

public class Main {
	public static void main(String[] args) {
		Logger logger = LogManager.getLogger(Main.class);
		IRepoPais rp = new RepoPais("src/main/resources/paises.csv");
		try {
			logger.debug(rp.findAll());
			rp.escribeContinente("Asia");
		} catch (PaisRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
