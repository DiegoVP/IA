package simpleGA;

public class Individuo implements Comparable<Individuo>{

    static int defaultGeneLength = 138;
    int puntaje = 0;
    byte[] binario = new byte[defaultGeneLength];
    byte[] decimal = new byte[69];
    
    public Individuo(boolean initialise){    	
		//Genero valores random para los 23 jugadores.
    	
	    if (initialise)
	    {
	    	for(int i=0; i<138; i++)
			{
		        byte bit = (byte) Math.round(Math.random());      
		        setBit(i,bit);
			}	  
		    
			//Fuerzo a que haya 3 Arqueros, 7 defensores, 8 volantes y 5 delanteros.
			byte a = 0;
			byte b = 0;
			int j = 0;
			
		    for(int i=0; i<23; i++)
			{
		    	setBit(j,a);
		    	setBit(j+1,b);
		    	j = j + 6;
		    	
		        if (j == 18)  {a = 0 ; b = 1;}
		        if (j == 60)  {a = 1 ; b = 0;}
		        if (j == 108) {a = 1 ; b = 1;}                  	
			}     
		      
		    //Si hay repetido, cambia valores para que dejen de repetirse.
		    boolean vf = HayRepetidos();	    
		    if  (vf == true);	    
		    {
		    	CambiarRepetidos();
		    }
		    
		    //Genera 
		    pasarADecimal();	   
		    
		    //Obtiene la Funci�n Aptitud;
		    puntaje = obtenerFuncionAptitud();	
	    }
    }

    public void CambiarRepetidos()
    {
    	 //Se cambian los jugadores que puedan llegar a estar repetidos.
	    //Compara cada jugador con los que est�n abajo en la misma posici�n, si hay alguno 
	    //repetido, borra cambia el primer valor del primer bit de condici�n.
		int k = 0;
		int j = 0;
		byte a = 0;
		byte b = 0;
		
	    for(int i=0; i<22; i++)
		{
		     j = k;	     
		     while (getBit(k) == getBit(j+6) && getBit(k+1)  == getBit(j+7) && j < 126)	    	 
		     {	    	 	 
		    	 if (getBit(k+2) == getBit(j+8)  &&	 getBit(k+3) == getBit(j+9)  &&
					 getBit(k+4) == getBit(j+10) &&	 getBit(k+5) == getBit(j+11))
					 {
					 	a = 1;
					    b = 0;
					    setBit(k+2,a);
					    setBit(j+8,b);
					 }
		    	 j=j+6;
		     }   
		     k = k+6;
		} 
    }
    
    public boolean HayRepetidos()
    {
    	boolean vf = false;	
		int k = 0;
		int j = 0;
		
	    for(int i=0; i<22; i++)
		{
		     j = k;	     
		     while (getBit(k) == getBit(j+6) && getBit(k+1)  == getBit(j+7) && j < 126)	    	 
		     {	    	 	 
		    	 if (getBit(k+2) == getBit(j+8)  &&	 getBit(k+3) == getBit(j+9)  &&
					 getBit(k+4) == getBit(j+10) &&	 getBit(k+5) == getBit(j+11))
					 {
		    		 return true;
					 }
		    	 j=j+6;
		     }   
		     k = k+6;
		}     	    	
    	return vf;
    }
    
    public void pasarADecimal(){
    
    	int k = 0;
    	int j = 0;
    	for(int i=0; i<23; i++)
		{
    		//POSICI�N
    		decimal[j]  = (byte) (2 * getBit(k)   + getBit(k+1));
    		//CONDICI�N F�SICA
    		decimal[j+1]= (byte) (2 * getBit(k+2) + getBit(k+3) + 7);
    		//NIVEL
    		decimal[j+2]= (byte) (2 * getBit(k+4) + getBit(k+5) + 7);
   		
    		j+=3;
    		k+=6;
		}    	
    }
    
    public int obtenerFuncionAptitud(){    
    	int suma = 0;
		int j = 0;    	
    	
    	//AGREGAR TODAS LAS CONDICIONES QUE FALTAN.
    	for(int i=0; i<23; i++)
		{
    		suma+= decimal[j+1] + decimal[j+2];
    		j+= 3;
    		
    		//MESSI es decimaL(k) = 3 y decimal(k+1) = 10 y decimal (k+2) = 10 x ejempLo.
		}
    	
    	if (HayRepetidos() == true)
    	{
    		suma = 0;
    	}
    	   	
    	return suma;  	    
    }
    
    
    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    public byte getBit(int index) {
        return binario[index];
    }

    public void setBit(int index, byte value) 
    {
        binario[index] = value;
    }

    /* Public methods */
    public int size() {
        return binario.length;
    }

    @Override
    public String toString() 
    {
        String geneString = "";
        for (int i = 0; i < size(); i++) {geneString += getBit(i);}
        return geneString;
    }

	@Override
	public int compareTo(Individuo ind) 
	{
        if (puntaje > ind.puntaje) {return -1;}        
        if (puntaje < ind.puntaje) {return 1;}
        return 0;
    }    
}