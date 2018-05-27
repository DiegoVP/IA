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
    	//jugadores = LectorCSV.PasarCSVaMatriz("C:/Users/Leito/git/IA/AlgGenetico/src/simpleGA/archivo.txt",fils,cols);
    	//Pasando la ruta del arch como argumento al ejecutar Ej: java simpleGA.GA C:/Users/Leito/git/IA/AlgGenetico/src/simpleGA/archivo.txt
    	jugadores = LectorCSV.PasarCSVaMatriz(args[0],fils,cols);

    	//Condiciones Iniciales
    	//PoblaciÃ³n inicial.
    	int cantPI = 50;
    	
    	//SelecciÃ³n
    	int cantSel = 10; //Elementos m seleccionados para Ranking.
    	
    	//MutaciÃ³n.
    	double porMut = 0.15;
    	int indMut = 3;
    	int bitMut = 100;
    	
    	//Ciclos
    	int ciclos = 3;
    	
    	System.out.println("1) Creación de Población Inicial: " + cantPI + " individuos");
	System.out.println("   Cantidad de ciclos: " + ciclos);
    	Poblacion poblaIni = new Poblacion(cantPI, true); 
    	
	    //System.out.println("En Bits:");   	    	
    	//Muestra PoblaciÃ³n por BIT.    	
	    //poblaIni.mostrarPoblacionBin();
  
       //System.out.println();
       //System.out.println("En Decimales:");       	
       //Muestra Poblabcion en forma Decimal.
       //poblaIni.mostrarPoblacionDec();
       
       //System.out.println();
       //System.out.println("FunciÃ³n Aptitud Po:");  
       //poblaIni.mostrarFA();       
       
       //COMIENZA EL CICLO DE PARO 
       System.out.println("2) Comienza la operación."); 
       for(int c=1;c<=ciclos;c++)
	   {
    	   System.out.println("Ciclo: " + c);
	       
	       System.out.println("A) Selección: Ranking."); 
	       Poblacion poblaSel = new Poblacion(cantSel, false); 
	       poblaSel = Operador.Seleccion(poblaIni, cantSel);
	       
	       //System.out.println("FunciÃ³n Aptitud Ps:");  
	       //poblaSel.mostrarFA();
	       
	       System.out.println("B) Cruzamiento: Multipunto");   
	       
	       Poblacion poblaCruz = new Poblacion(cantSel, false);       
	       poblaCruz = Operador.Cruzamiento(poblaSel,cantSel);       
	  	  
		    //System.out.println("En Bits:");   	    	
		   	//Muestra PoblaciÃ³n por BIT.    	
		    //poblaCruz.mostrarPoblacionBin();
	              
	       //System.out.println();
	       //System.out.println("En Decimales:");       	
	       //Muestra Poblabcion en forma Decimal.
	       // poblaCruz.mostrarPoblacionDec();
	                   
	      //System.out.println();
	      //System.out.println("FunciÃ³n Aptitud de Pc:");  
	      //poblaCruz.mostrarFA();
	       
	      System.out.print("C) Mutación (" + porMut + "%): ");   
	      Poblacion poblaMut = new Poblacion(cantSel, false); 

	      poblaMut = Operador.Mutacion(poblaCruz, porMut, indMut, bitMut);
	      
	      //Se asigna la PoblaciÃ³n Mutada para el prÃ³ximo ciclo.
	      poblaIni = poblaMut;      
       }
       
       //Poblacion Final, le asigno el Ãºltimo contenido que le quedo a la Pi
       //No hace falta esta asignacion pero es para que quede claro.
       Poblacion poblaFin = new Poblacion(cantSel, false); 
       poblaFin = poblaIni;
       
       //ACÁ SE TIENE QUE MOSTRAR EL MEJOR CON LISTADO DE LOS JUGADORES.
       System.out.println();
	   System.out.println("3) Población Final");   
	   System.out.print("Resultados Función Aptitud: "); 
   	   Arrays.sort(poblaFin.Individuos);
   	   poblaIni.mostrarFA();
   	  
   	   System.out.println();
   	   System.out.println();
	   System.out.print("Composición del mejor individuo");  
	   System.out.println(" (" + poblaFin.Individuos[0].puntaje + "):");	   
	   System.out.print("Cromosoma: ");
   	   poblaFin.Individuos[0].mostrarBin();
   	   System.out.println();	   
	   poblaFin.Individuos[0].mostrarJugadores(jugadores);
    }
}
