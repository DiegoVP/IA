package simpleGA;
//
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class AlgoritmoGenetico {

    public static void main(String[] args) throws FileNotFoundException, IOException 
    {    
    	//Condiciones Iniciales
    	//Población inicial.
    	int cantPI = 50;
    	
    	//Seleccionn
    	int cantSel = 10; //Elementos m seleccionados para Ranking.
    	
    	//Mutacion.
    	double porMut = 0.15;
    	int indMut = 3;
    	int bitMut = 100;
    	
    	//Ciclos
    	int ciclos = 3;
    	   	
    	//Obtener datos del CSV.
    	int cols = 5;
    	int fils = 64;
    	String[][] jugadores = new String[fils][cols];
    	jugadores = LectorCSV.PasarCSVaMatriz(args[0],fils,cols);
    	//"C:\Users\Leito\git\IA\AlgGenetico\src\simpleGA\Jugadores.txt";
    	
    	System.out.println("1) Creación de Población Inicial: " + cantPI + " individuos");
    	Poblacion poblaIni = new Poblacion(cantPI, true); 

       //Comienza el ciclo de paro.
       System.out.println("2) Comienza la operación. Cantidad de ciclos: " + ciclos + "."); 
       for(int c=1;c<=ciclos;c++)
	   {
    	   System.out.println("Ciclo: " + c);
	       
	       System.out.println("A) Selección: Ranking."); 
	       Poblacion poblaSel = new Poblacion(cantSel, false); 
	       poblaSel = Operador.Seleccion(poblaIni, cantSel);
	              
	       System.out.println("B) Cruzamiento: Multipunto");   
	       
	       Poblacion poblaCruz = new Poblacion(cantSel, false);       
	       poblaCruz = Operador.Cruzamiento(poblaSel,cantSel);       
	  	  	       
	      System.out.print("C) Mutación (" + porMut + "%): ");   
	      Poblacion poblaMut = new Poblacion(cantSel, false); 

	      poblaMut = Operador.Mutacion(poblaCruz, porMut, indMut, bitMut);
	      
	      //Se asigna la Poblacion Mutada para el proximo ciclo.
	      poblaIni = poblaMut;      
       }
       
       //Poblacion Final, le asigno el mismo contenido que le quedo a la ultima Pi
       Poblacion poblaFin = new Poblacion(cantSel, false); 
       poblaFin = poblaIni;
       
       System.out.println();
	   System.out.println("3) Población Final");   
	   System.out.print("Resultados Función Aptitud: "); 
   	   Arrays.sort(poblaFin.Individuos);
   	   poblaIni.mostrarFA();
   	  
   	   System.out.println();
   	   System.out.println();
	   System.out.print("Composición del mejor individuo");  
	   System.out.println(" (" + poblaFin.Individuos[0].puntaje + "):");	   
	   System.out.print("Valores del Cromosoma: ");
   	   poblaFin.Individuos[0].mostrarBin();
   	   System.out.println();	   
	   poblaFin.Individuos[0].mostrarJugadores(jugadores);
    }
}
