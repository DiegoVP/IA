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
		int mya = 0;
		int ponzio = 0;
		int perez = 0;
		int krane = 0;
		
		//Se pasa a Decimal para analizar mejor los valores.
    	pasarADecimal();
    	
    	//AGREGAR TODAS LAS CONDICIONES QUE FALTAN.
    	for(int i=0; i<23; i++)
		{
    		suma+= 4*decimal[j+1] + 6*decimal[j+2];
    		
    		//Condiciones para sumar y restar
    		//Si está Messi suma 40 puntos
    		 if  (decimal[j] == 3 && decimal[j+1] == 10 && decimal [j+2] == 10) {suma+= 40; mya+=1;}
    		//Si está Aguero
    		 if  (decimal[j] == 3 && decimal[j+1] == 10 && decimal [j+2] == 9) {mya+=1;}
    		//Si está Messi y Aguero juntos suma 10 puntos
    		 if  (mya == 2) {suma+=10;}
    		 
    		//Si está Ponzio
    		 if  (decimal[j] == 2 && decimal[j+1] == 9 && decimal [j+2] == 8) {ponzio+=1;}
    		//Si está Kraneviter
    		 if  (decimal[j] == 2 && decimal[j+1] == 10 && decimal [j+2] == 7) {krane+=1;}
    		//Si está Pablo Perez
    		 if  (decimal[j] == 2 && decimal[j+1] == 7 && decimal [j+2] == 7) {perez+=1;}
    		//Si está Ponzio y Kraneviter juntos suma 7 puntos
    		 if  (ponzio == 1 && krane == 1) {suma+=7;}
    		//Si está Ponzio y Perez juntos resta 7 puntos
    		 if  (ponzio == 1 && perez == 1) {suma-=7;}
    		 
    		 //Si hay un arquero con nivel mayor o igual a 9 suma 15 puntos
    		 if  (decimal[j] == 0 && decimal [j+2] > 8) {suma+=15;}
    		 
    		 //Si hay un defensor con condición física mayor a 8 suma 10 puntos
    		 if  (decimal[j] == 1 && decimal[j+1] > 8) {suma+=10;}
    		 
    		 //Si hay un volante con nivel mayor a 6 y condición física mayor a 6 suma 10 puntos
    		 if  (decimal[j] == 2 && decimal[j+1] > 6 && decimal [j+2] > 6) {suma+=10;}
    		 
    		 //Si hay un delantero con nivel mayor a 8 suma 15 puntos.
    		 if  (decimal[j] == 3 && decimal [j+2] > 8) {suma+=15;}
    		 
    		 //Si hay un arquero con nivel menor a 8 resta 10 puntos
    		 if  (decimal[j] == 0 && decimal [j+2] < 8) {suma-=10;}
    		 
    		 //Si hay un arquero con condición física menor a 8 resta 5 puntos
    		 if  (decimal[j] == 0 && decimal [j+1] < 8) {suma-=5;}
    		 
    		 //Si hay un defensor con nivel menor a 8 resta 5 puntos
    		 if  (decimal[j] == 1 && decimal[j+2] < 8) {suma-=5;}
    		 
    		 //Si hay un volante con condición física menor a 8 resta 5 puntos
    		 if  (decimal[j] == 2 && decimal[j+1] < 8) {suma-=5;}
    		 
    		 //Si hay un delantero con nivel menor a 8 resta 10 puntos
    		 if  (decimal[j] == 3 && decimal [j+2] < 8) {suma+=10;}
    		 
    		j+= 3;
    		    		
    		//MESSI:  decimal(j) = 3 && decimal(j+1) = 10 && decimal (j+2) = 10 
    		//AGUERO: decimal(j) = 3 && decimal(j+1) = 10 && decimal (j+2) = 9
    		//PONZIO: decimal(j) = 2 && decimal(j+1) = 9  && decimal (j+2) = 8
    		//KRANE:  decimal(j) = 2 && decimal(j+1) = 10  && decimal (j+2) = 7
    		//PEREZ:  decimal(j) = 2 && decimal(j+1) = 7  && decimal (j+2) = 7
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

	public void mostrarDec() 
	{
	     int k = 0;
	 	 for(byte decimal : decimal)
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

	public void mostrarBin() 
	{
    	int k = 0;
    	for(byte bit : binario)
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
