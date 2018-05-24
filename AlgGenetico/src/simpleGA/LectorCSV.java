package simpleGA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//import com.opencsv.CSVReader;

/**
 * Clase para leer archivos CSV desde Java.
 * Para la lectura de csv complejos se debe usar la librer�a OpenCSV.
 * Leer m�s informaci�n en http://www.programandoapasitos.com/2017/04/como-leer-fichero-csv-con-java.html
 * @author Inazio
 *
 */
public class LectorCSV {

	// M�todos
	/**
	 * Lee un CSV que no contiene el mismo caracter que el separador en su texto
	 * y sin comillas que delimiten los campos
	 * @param path Ruta donde est� el archivo
	 * @throws IOException 
	 */
	public void leerCSVSimple(String path) throws IOException {
		
		// Abro el .csv en buffer de lectura
		BufferedReader bufferLectura = new BufferedReader(new FileReader(path));
		
		// Leo una l�nea del archivo
		String linea = bufferLectura.readLine();
		
		while (linea != null) {
			// Separa la l�nea le�da con el separador definido previamente
			String[] campos = linea.split(String.valueOf(';'));
			System.out.println(Arrays.toString(campos));
			
			// Vuelvo a leer del fichero
			linea = bufferLectura.readLine();
		}
		
		// Cierro el buffer de lectura
		if (bufferLectura != null) {
			bufferLectura.close();
		}
	}

}


