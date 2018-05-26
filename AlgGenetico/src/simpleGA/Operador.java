package simpleGA;
import java.util.Arrays;

public class Operador {
	
    public static Poblacion SeleccionRanking(Poblacion unaPoblacion, int cant)
    {
    	Poblacion poblaSel = new Poblacion(cant, false);
    	
    	Arrays.sort(unaPoblacion.Individuos);  
    	
    	for(int i=0;i<cant;i++) 
    	{
    		poblaSel.Individuos[i] = unaPoblacion.Individuos[i];
    	 }
        
        return poblaSel;
        
    }
    
    public static Poblacion Cruzamiento(Poblacion unaPoblacion, int cant)
    {
   	  	int k = cant - 1;
    	int cruzamientos = cant/2;
    	
    	Poblacion poblaCruz = new Poblacion(cant, false);
    	
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
}
