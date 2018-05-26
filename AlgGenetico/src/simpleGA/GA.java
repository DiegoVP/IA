package simpleGA;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GA {

    public static void main(String[] args) throws FileNotFoundException, IOException 
    {    
    	int cols = 5;
    	int fils = 66;
    	String[][] jugadores = new String[fils][cols];
    	jugadores = LectorCSV.PasarCSVaMatriz("C:/Users/Leito/git/IA/AlgGenetico/src/simpleGA/archivo.txt",fils,cols);

        System.out.println("----ARCHIVO CSV----");
    	//Muestro los Jugadores del CSV.    	
    	for(String[] jugador : jugadores)
    	{
    		for(String detalle : jugador)
    		{
    		System.out.print(detalle + ";");   		
    		}
    		System.out.println();
    	}
    	
        System.out.println();
    	System.out.println("----POBLACIÓN INICIAL----");
    	Poblacion poblaIni = new Poblacion(10, true); 
    	
	    System.out.println("En Bits:");   	    	
    	//Muestra Población por BIT.    	
	    poblaIni.mostrarPoblacionBin();
  
        System.out.println();
        System.out.println("En Decimales:");       	
       //Muestra Poblabcion en forma Decimal.
        poblaIni.mostrarPoblacionDec();
       
       System.out.println();
       System.out.println("Función Aptitud Po:");  
       poblaIni.mostrarFA();
       
       System.out.println();
       System.out.println("----SELECCION----"); 
       Poblacion poblaSel = new Poblacion(5, false); 
       poblaSel = Operador.SeleccionRanking(poblaIni, 6);
       
       System.out.println("Función Aptitud Ps:");  
       poblaSel.mostrarFA();
       
       System.out.println();
       System.out.println("----CRUZAMIENTO----");   
       
       Poblacion poblaCruz = new Poblacion(6, false);       
       poblaCruz = Operador.Cruzamiento(poblaSel,6);       
  	  
	    System.out.println("En Bits:");   	    	
	   	//Muestra Población por BIT.    	
	    poblaCruz.mostrarPoblacionBin();
              
       System.out.println();
       System.out.println("En Decimales:");       	
      //Muestra Poblabcion en forma Decimal.
       poblaCruz.mostrarPoblacionDec();
                   
      System.out.println();
      System.out.println("Función Aptitud de Pc:");  
      poblaCruz.mostrarFA();
       
      System.out.println();
      System.out.println("----MUTACIÓN----");   
      
      
  	
    	/*
        // Set a candidate solution
        FuncionActitud.setSolution("1111000000000000000000000000000000000000000000000000000000001111");

        // Create an initial poblacion
        Poblacion myPop = new Poblacion(50, true);
        
        // Evolve our poblacion until we reach an optimum solution
        int generationCount = 0;
        while (myPop.getFittest().getFitness() < FuncionActitud.getMaxFitness()) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
            myPop = Algoritmo.evolvePopulation(myPop);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
*/
    }
}