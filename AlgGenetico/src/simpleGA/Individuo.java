package simpleGA;

public class Individuo implements Comparable<Individuo>{

    int puntaje = 0;
    byte[] binario = new byte[138];
    byte[] decimal = new byte[69];
    
    public byte getBit(int index) 
    {
        return binario[index];
    }

    public void setBit(int index, byte value) 
    {
        binario[index] = value;
    }

	@Override
	public int compareTo(Individuo ind) 
	{
        if (puntaje > ind.puntaje) {return -1;}        
        if (puntaje < ind.puntaje) {return 1;}
        return 0;
    }    
    
    public Individuo(boolean inicializar){    	
		//Genero valores random para los 23 jugadores.
    	
	    if (inicializar)
	    {
	    	for(int i=0; i<138; i++)
			{
		        byte bit = (byte) Math.round(Math.random());      
		        setBit(i,bit);
			}	  
		    
			//Fuerzo a que haya 3 Arqueros, 7 defensores, 8 volantes y 5 delanteros.
			byte a = 0;
			byte b = 0;
			int  j = 0;
			
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
		    AnalizarRepetidos();
	    
		    //Obtiene la Función Aptitud;
		    puntaje = obtenerFuncionAptitud();
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
    
    public void CambiarRepetidos()
    {
    	//Se cambian los jugadores que puedan llegar a estar repetidos.
	    //Compara cada jugador con los que están abajo en la misma posición, si hay alguno 
	    //repetido, borra cambia el primer valor del primer bit de condición.
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
    
    public void AnalizarRepetidos()
    {
	    boolean vf = HayRepetidos();
	    if  (vf == true)
	    {
	    	CambiarRepetidos();
	    }
    }
    
      public void pasarADecimal()
    {    
    	int k = 0;
    	int j = 0;
    	for(int i=0; i<23; i++)
		{
    		//POSICIÓN
    		decimal[j]  = (byte) (2 * getBit(k)   + getBit(k+1));
    		//CONDICIÓN FÍSICA
    		decimal[j+1]= (byte) (2 * getBit(k+2) + getBit(k+3) + 7);
    		//NIVEL
    		decimal[j+2]= (byte) (2 * getBit(k+4) + getBit(k+5) + 7);
    		j+=3;
    		k+=6;
		}    	
    }
    
    public int obtenerFuncionAptitud()
    {   
    	int suma = 0;
		int j = 0;    	
		
		//Se pasa a Decimal para analizar mejor los valores.
    	pasarADecimal();
    	
    	//AGREGAR TODAS LAS CONDICIONES QUE FALTAN.
    	for(int i=0; i<23; i++)
		{
    		suma+= decimal[j+1] + decimal[j+2];
    		j+= 3;
    		
    		//MESSI es decimal(k) = 3 y decimal(k+1) = 10 y decimal (k+2) = 10 x ejempLo.
		}
    	
    	//Si hay algun repetido se devuelve 0.
    	if (HayRepetidos() == true)	{suma = 0;}
    	   	
    	return suma;  	    
    }

	public void mostrarJugadores(String[][] jugadores) 
	{
	    for(int i=0; i<138; i+=6)
		{
	    	boolean encontrado = false;
	    	int k = 0;
	    	
    		String pos = String.valueOf(binario[i]);
    		pos = pos.concat(String.valueOf(binario[i+1]));	    	
    		String fis = String.valueOf(binario[i+2]);
    		fis = fis.concat(String.valueOf(binario[i+3]));	    	    		
    		String niv = String.valueOf(binario[i+4]);
    		niv =niv.concat(String.valueOf(binario[i+5]));
    		
    		if (i==0)  {System.out.println("Arqueros:");}
    		if (i==18) {System.out.println(); System.out.println("Defensores:");}
    		if (i==60) {System.out.println(); System.out.println("Volantes:");}
    		if (i==108){System.out.println();System.out.println("Delanteros:");}
    		  		
	    	while (encontrado == false)
	    	{	    		
	    		if (pos.equals(jugadores[k][2]) &&
	    			fis.equals(jugadores[k][3]) &&
	    			niv.equals(jugadores[k][4]))
	    		{
	            	System.out.println(" " + jugadores[k][1]); 
	            	encontrado = true;
	    		}
	    		else
	    		{
	    			k++;
	    		}
	    	}		    
		}		
	}
}
