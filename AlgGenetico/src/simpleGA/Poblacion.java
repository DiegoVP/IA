package simpleGA;



public class Poblacion {

    Individuo[] Individuos;
    int cantPob;
    
    // Crea la Población.
    public Poblacion(int popSize, boolean inicializar) 
    {
    	cantPob = popSize;
    	Individuos = new Individuo[cantPob];
    	
        for (int i = 0; i < cantPob; i++) 
        {
        	Individuo nuevoIndividuo = new Individuo(inicializar);
            Individuos[i] = nuevoIndividuo;
        }
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
        	System.out.print(individuo.puntaje + ","); 
        }
        System.out.print("."); 
    }
    
     /* Getters */
    public Individuo getIndividual(int index) {
        return Individuos[index];
    }
 
}
