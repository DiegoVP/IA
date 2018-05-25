package simpleGA;

import java.util.Arrays;
import java.util.Collections;

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
    
    public void SeleccionRanking()
    {
        Arrays.sort(Individuos);
    }
    
    public Poblacion Cruzamiento()
    {
   	  	int cantRank = 10;
   	  	int k = cantRank - 1;
    	int cruzamientos = cantRank/2;
    	
    	Poblacion poblaCruz = new Poblacion(cantRank, false);
    	
    	for(int i=0;i<cruzamientos;i++)
    	{
    		for (int j=0;j<138;j=j+6)
    		{		    	    			
    			poblaCruz.Individuos[i].binario[j]   = Individuos[i].binario[j];
    			poblaCruz.Individuos[i].binario[j+1] = Individuos[i].binario[j+1];
    			poblaCruz.Individuos[i].binario[j+2] = Individuos[k-i].binario[j+2];	    
    			poblaCruz.Individuos[i].binario[j+3] = Individuos[k-i].binario[j+3];
    			poblaCruz.Individuos[i].binario[j+4] = Individuos[i].binario[j+4];
    			poblaCruz.Individuos[i].binario[j+5] = Individuos[i].binario[j+5];
    	    	
    			poblaCruz.Individuos[k-i].binario[j]   = Individuos[k-i].binario[j];
    			poblaCruz.Individuos[k-i].binario[j+1] = Individuos[k-i].binario[j+1];
    			poblaCruz.Individuos[k-i].binario[j+2] = Individuos[i].binario[j+2]; 		    
    			poblaCruz.Individuos[k-i].binario[j+3] = Individuos[i].binario[j+3];
    			poblaCruz.Individuos[k-i].binario[j+4] = Individuos[k-i].binario[j+4];
    			poblaCruz.Individuos[k-i].binario[j+5] = Individuos[k-i].binario[j+5];
    		}
    	}
    	 	
        for(Individuo individuo : poblaCruz.Individuos)
        {
    	    boolean vf = individuo.HayRepetidos();	    
    	    if  (vf == true);	    
    	    {
    	    	individuo.CambiarRepetidos();
    	    }   	
        	
        	individuo.pasarADecimal();
        	individuo.puntaje = individuo.obtenerFuncionAptitud();        	
        }
        
        Arrays.sort(poblaCruz.Individuos);    	
    	
    	return poblaCruz;
    }

    /* Getters */
    public Individuo getIndividual(int index) {
        return Individuos[index];
    }

    public Individuo getFittest() {
        Individuo fittest = Individuos[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < populationSize; i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }  
    
}
