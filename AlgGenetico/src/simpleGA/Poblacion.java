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
	    	individuo.mostrarBin();
	    } 
    }
    
    public void mostrarPoblacionDec()
    {
        for(Individuo individuo : Individuos)
        {
 		   individuo.mostrarDec();  	
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
    

 
}
