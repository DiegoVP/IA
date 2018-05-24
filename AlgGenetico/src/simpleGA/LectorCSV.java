package simpleGA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//import com.opencsv.CSVReader;

/**
 * Clase para leer archivos CSV desde Java.
 * Para la lectura de csv complejos se debe usar la librería OpenCSV.
 * Leer más información en http://www.programandoapasitos.com/2017/04/como-leer-fichero-csv-con-java.html
 * @author Inazio
 *
 */
public class LectorCSV {

	// Métodos
	/**
	 * Lee un CSV que no contiene el mismo caracter que el separador en su texto
	 * y sin comillas que delimiten los campos
	 * @param path Ruta donde está el archivo
	 * @throws IOException 
	 */
	public static String[][] PasarCSVaMatriz(String path, int fils, int cols) throws IOException {
		
		//
		 String[][] matriz = new String[fils][cols];
		 byte fila = 0;
		
		// Abro el .csv en buffer de lectura
		BufferedReader bufferLectura = new BufferedReader(new FileReader(path));
		
		// Leo una línea del archivo
		String linea = bufferLectura.readLine();
		
		while (linea != null) {
			// Separa la línea leída con el separador definido previamente
			String[] campos = linea.split(String.valueOf(';'));

			matriz[fila][0] = campos[0];
			matriz[fila][1] = campos[1];
			matriz[fila][2] = campos[2];	
			matriz[fila][3] = campos[3];		
			matriz[fila][4] = campos[4];		
			
			// Vuelvo a leer del fichero
			linea = bufferLectura.readLine();
			fila++;
		}
		
		// Cierro el buffer de lectura
		if (bufferLectura != null) {
			bufferLectura.close();
		}
		
		return matriz;
	}

}


