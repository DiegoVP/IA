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
    	System.out.println("Generación Población");
    	Poblacion pobla = new Poblacion(10, true); 
    	

	    System.out.println("En Bits:");   	    	
    	//Muestra Población por BIT.    	
        for(Individuo individuo : pobla.Individuos)
        {
        	int k = 0;
        	for(byte bit : individuo.binario)
        	{
        		System.out.print(bit);
        		
     		    k++;
     		    if (k == 6) 
     		    {
     		    	System.out.print("|");
     		    	k = 0; 
     		    }	
        	} 
        	System.out.println();
        }
        

        System.out.println();
        System.out.println("En Decimales:");       	
       //Muestra Poblabcion en forma Decimal.
       for(Individuo individuo : pobla.Individuos)
       {
		   int k = 0;
    	   for(byte decimal : individuo.decimal)
    	   {
			   System.out.print(decimal);		   
			   k++;
			   if (k == 3) 
			   {
				   System.out.print("|");    			   
				   k =0; 
			   }
    	   }
           System.out.println();
       }
       
       System.out.println();
       System.out.println("Función de Aptitud de Cada Población:");  
       for(Individuo individuo : pobla.Individuos)
       {
	   System.out.println("FA: " + individuo.puntaje); 
       }
       
       System.out.println();
       System.out.println("Ordenamiento por Ranking:");  
       pobla.SeleccionRanking();
              
       for(Individuo individuo : pobla.Individuos)
       {
	   System.out.println("FA: " + individuo.puntaje); 
       }
       
       System.out.println("----CRUZAMIENTO----");   
       
       Poblacion poblaCruz = new Poblacion(5, false);       
       poblaCruz = pobla.Cruzamiento();
       
  	  
    System.out.println("En Bits:");   	    	
   	//Muestra Población por BIT.    	
       for(Individuo individuo : poblaCruz.Individuos)
       {
       	int k = 0;
       	for(byte bit : individuo.binario)
       	{
       		System.out.print(bit);
       		
    		    k++;
    		    if (k == 6) 
    		    {
    		    	System.out.print("|");
    		    	k = 0; 
    		    }	
       	} 
       	System.out.println();
       }
       
       System.out.println();
       System.out.println("En Decimales:");       	
      //Muestra Poblabcion en forma Decimal.
      for(Individuo individuo : poblaCruz.Individuos)
      {
		   int k = 0;
   	   for(byte decimal : individuo.decimal)
   	   {
			   System.out.print(decimal);		   
			   k++;
			   if (k == 3) 
			   {
				   System.out.print("|");    			   
				   k =0; 
			   }
   	   }
          System.out.println();
      }
      
      System.out.println();
      System.out.println("Función de Aptitud de Cada Población:");  
      for(Individuo individuo : poblaCruz.Individuos)
      {
	   System.out.println("FA: " + individuo.puntaje); 
      }
       
  	
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