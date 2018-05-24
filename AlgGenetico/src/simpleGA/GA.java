package simpleGA;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GA {

    public static void main(String[] args) throws FileNotFoundException, IOException {
    	
    	int cols = 5;
    	int fils = 66;
    	String[][] jugadores = new String[fils][cols];
    	jugadores = LectorCSV.PasarCSVaMatriz("C:/Users/Leito/git/IA/AlgGenetico/src/simpleGA/archivo.txt",fils,cols);

/*
    	for(int i=0; i<66; i++)
    	{
    	 for(int j=0; j<5; j++)
    	 {
    	 System.out.print(jugadores[i][j]+";");
    	 }
    	 System.out.println("");
    	}    	
*/
    	
    	Poblacion pobla = new Poblacion(5, true);    
    	
    	 		  
      /* for (int j=0;j<5;j++) 
       {
           int k = 0;
    	   for(int i=0; i<138; i++)
    	   {
    		   System.out.print(pobla.Individuos[j].getBit(i));

    		   k++;
    		   if (k == 6) {
    			   System.out.println(" ");
    		   k =0; }
    	   }
       	System.out.println(" ");
       }*/
       
       for (int j=0;j<5;j++) 
       {
           int k = 0;
    	   for(int i=0; i<69; i++)
    	   {
    		   System.out.print(pobla.Individuos[j].decimal[i]+" ");

    		   k++;
    		   if (k == 3) {
    			   System.out.println(" ");    			   
    		   k =0; }
    	   }
	   System.out.println("FA: " + pobla.Individuos[j].funcionAptitud());   
       System.out.println(" ");
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