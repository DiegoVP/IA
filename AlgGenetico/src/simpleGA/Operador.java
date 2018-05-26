package simpleGA;
import java.util.Arrays;

public class Operador {
	
    public static Poblacion Seleccion(Poblacion unaPoblacion, int m)
    {
    	Poblacion poblaSel = new Poblacion(m, false);
    	
    	//Método Ranking.
    	//Se ordena por Función Aptitud
    	Arrays.sort(unaPoblacion.Individuos);  
    	
    	//Se genera la población seleccionda con los m individuos elegidos.
    	for(int i=0;i<m;i++) 
    	{
    		poblaSel.Individuos[i] = unaPoblacion.Individuos[i];
    	 }
        
        return poblaSel;        
    }
    
    public static Poblacion Cruzamiento(Poblacion unaPoblacion, int m)
    {
   	  	int k = m - 1;
    	int cruzamientos = m/2;
    	
    	Poblacion poblaCruz = new Poblacion(m, false);
    	
    	for(int i=0;i<cruzamientos;i++)
    	{
    		for (int j=0;j<138;j=j+6)
    		{		    	    			
    			poblaCruz.Individuos[i].binario[j]   = unaPoblacion.Individuos[i].binario[j];
    			poblaCruz.Individuos[i].binario[j+1] = unaPoblacion.Individuos[i].binario[j+1];
    			poblaCruz.Individuos[i].binario[j+2] = unaPoblacion.Individuos[k-i].binario[j+2];	    
    			poblaCruz.Individuos[i].binario[j+3] = unaPoblacion.Individuos[k-i].binario[j+3];
    			poblaCruz.Individuos[i].binario[j+4] = unaPoblacion.Individuos[i].binario[j+4];
    			poblaCruz.Individuos[i].binario[j+5] = unaPoblacion.Individuos[i].binario[j+5];
    	    	
    			poblaCruz.Individuos[k-i].binario[j]   = unaPoblacion.Individuos[k-i].binario[j];
    			poblaCruz.Individuos[k-i].binario[j+1] = unaPoblacion.Individuos[k-i].binario[j+1];
    			poblaCruz.Individuos[k-i].binario[j+2] = unaPoblacion.Individuos[i].binario[j+2]; 		    
    			poblaCruz.Individuos[k-i].binario[j+3] = unaPoblacion.Individuos[i].binario[j+3];
    			poblaCruz.Individuos[k-i].binario[j+4] = unaPoblacion.Individuos[k-i].binario[j+4];
    			poblaCruz.Individuos[k-i].binario[j+5] = unaPoblacion.Individuos[k-i].binario[j+5];
    		}    		
    		
    		//Se analiza si se generaron repetidos en los 2 individuos.
    		poblaCruz.Individuos[i].AnalizarRepetidos();	    
    		poblaCruz.Individuos[i].puntaje = poblaCruz.Individuos[i].obtenerFuncionAptitud();  
    		poblaCruz.Individuos[k-i].AnalizarRepetidos();	    
    		poblaCruz.Individuos[k-i].puntaje = poblaCruz.Individuos[k-i].obtenerFuncionAptitud();  
    	}  	    	 	
        Arrays.sort(poblaCruz.Individuos);   
    	return poblaCruz;
    }
    
    public static Poblacion Mutacion(Poblacion unaPoblacion, double prob, int ind, int bit)
    {
    	double random = Math.random();
    	
    	if (random < prob)
    	{
	    	//Se resta 1 para el subíndice.
    		ind--;
	    	bit--;
    		
    		if (unaPoblacion.Individuos[ind].binario[bit] == 0)
	    	{ unaPoblacion.Individuos[ind].binario[bit] = 1;}
	    	else
	    	{ unaPoblacion.Individuos[ind].binario[bit] = 0;}
	    	
	    	//Si se muta, se evalua si se generó un repetido.
	    	unaPoblacion.Individuos[ind].AnalizarRepetidos();	        	
	    	unaPoblacion.Individuos[ind].puntaje = unaPoblacion.Individuos[ind].obtenerFuncionAptitud(); 
    	
	    	System.out.println("Se ha mutado."); 
	    	return unaPoblacion;
        }    
    	System.out.println("No se ha mutado."); 
    	return unaPoblacion;
    }
}
