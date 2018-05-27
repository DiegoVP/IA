package simpleGA;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class GA {

    public static void main(String[] args) throws FileNotFoundException, IOException 
    {    
    	int cols = 5;
    	int fils = 64;
    	String[][] jugadores = new String[fils][cols];
    	jugadores = LectorCSV.PasarCSVaMatriz("C:/Users/Leito/git/IA/AlgGenetico/src/simpleGA/archivo.txt",fils,cols);
    	//Pasando la ruta del arch como argumento al ejecutar Ej: java simpleGA.GA C:/Users/Leito/git/IA/AlgGenetico/src/simpleGA/archivo.txt
    	//jugadores = LectorCSV.PasarCSVaMatriz(args[0],fils,cols);

    	//Condiciones Iniciales
    	//Población inicial.
    	int cantPI = 50;
    	
    	//Selección
    	int cantSel = 10; //Elementos m seleccionados para Ranking.
    	
    	//Mutación.
    	double porMut = 0.15;
    	int indMut = 3;
    	int bitMut = 100;
    	
    	//Ciclos
    	int ciclos = 3;
    	
    	System.out.println("1) Creación de Población Inicial: " + cantPI + " individuos");
    	Poblacion poblaIni = new Poblacion(cantPI, true); 
    	
	    //System.out.println("En Bits:");   	    	
    	//Muestra Población por BIT.    	
	    //poblaIni.mostrarPoblacionBin();
  
       //System.out.println();
       //System.out.println("En Decimales:");       	
       //Muestra Poblabcion en forma Decimal.
       //poblaIni.mostrarPoblacionDec();
       
       //System.out.println();
       //System.out.println("Función Aptitud Po:");  
       //poblaIni.mostrarFA();       
       
       //COMIENZA EL CICLO DE PARO 
       System.out.println("2) Comienza la operación."); 
       for(int c=1;c<=ciclos;c++)
	   {
    	   System.out.println("Ciclo: " + c);
	       
	       System.out.println("A) Selección: Ranking."); 
	       Poblacion poblaSel = new Poblacion(cantSel, false); 
	       poblaSel = Operador.Seleccion(poblaIni, cantSel);
	       
	       //System.out.println("Función Aptitud Ps:");  
	       //poblaSel.mostrarFA();
	       
	       System.out.println("B) Cruzamiento: Multipunto");   
	       
	       Poblacion poblaCruz = new Poblacion(cantSel, false);       
	       poblaCruz = Operador.Cruzamiento(poblaSel,cantSel);       
	  	  
		    //System.out.println("En Bits:");   	    	
		   	//Muestra Población por BIT.    	
		    //poblaCruz.mostrarPoblacionBin();
	              
	       //System.out.println();
	       //System.out.println("En Decimales:");       	
	       //Muestra Poblabcion en forma Decimal.
	       // poblaCruz.mostrarPoblacionDec();
	                   
	      //System.out.println();
	      //System.out.println("Función Aptitud de Pc:");  
	      //poblaCruz.mostrarFA();
	       
	      System.out.print("C) Mutación (" + porMut + "%): ");   
	      Poblacion poblaMut = new Poblacion(cantSel, false); 

	      poblaMut = Operador.Mutacion(poblaCruz, porMut, indMut, bitMut);
	      
	      //Se asigna la Población Mutada para el próximo ciclo.
	      poblaIni = poblaMut;      
       }
       
       //Poblacion Final, le asigno el último contenido que le quedo a la Pi
       //No hace falta esta asignacion pero es para que quede claro.
       Poblacion poblaFin = new Poblacion(cantSel, false); 
       poblaFin = poblaIni;
       
       //AC� SE TIENE QUE MOSTRAR EL MEJOR CON LISTADO DE LOS JUGADORES.
       System.out.println();
	   System.out.println("3) Población Final");   
	   System.out.print("Resultados Función Aptitud: "); 
   	   Arrays.sort(poblaFin.Individuos);
   	   poblaIni.mostrarFA();
   	  
   	   System.out.println();
   	   System.out.println();
	   System.out.print("Composici�n del mejor invidiuo");  
	   System.out.println(" (" + poblaFin.Individuos[0].puntaje + "):");	   
	   System.out.print("Cromosoma: ");
   	   poblaFin.Individuos[0].mostrarBin();
   	   System.out.println();	   
	   poblaFin.Individuos[0].mostrarJugadores(jugadores);
    }
}
