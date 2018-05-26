package simpleGA;



public class Poblacion {

    Individuo[] Individuos;
    int populationSize;
    
    // Crea la Población.
    public Poblacion(int popSize, boolean initialise) 
    {
    	populationSize = popSize;
    	Individuos = new Individuo[populationSize];
    	
        //if (initialise) 
        //{
        	for (int i = 0; i < populationSize; i++) 
        	{
                Individuo newIndividuo = new Individuo(initialise);
                Individuos[i] = newIndividuo;
            }
        //}
    }
    
    public void mostrarPoblacionBin()
    {
	    for(Individuo individuo : Individuos)
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
    }
    
    public void mostrarPoblacionDec()
    {
        for(Individuo individuo : Individuos)
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
    }
    
    public void mostrarFA()
    {
        for(Individuo individuo : Individuos)
        {
        	System.out.println("FA: " + individuo.puntaje); 
        }
    }
    
     /* Getters */
    public Individuo getIndividual(int index) {
        return Individuos[index];
    }    
}
