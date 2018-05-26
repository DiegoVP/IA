package simpleGA;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class GA {

    public static void main(String[] args) throws FileNotFoundException, IOException 
    {    
    	int cols = 5;
    	int fils = 66;
    	String[][] jugadores = new String[fils][cols];
    	jugadores = LectorCSV.PasarCSVaMatriz("C:/Users/Leito/git/IA/AlgGenetico/src/simpleGA/archivo.txt",fils,cols);

        System.out.println("----ARCHIVO CSV----");
    	//Muestro los Jugadores del CSV.    	
    	/*
        for(String[] jugador : jugadores)
    	{
    		for(String detalle : jugador)
    		{
    		System.out.print(detalle + ";");   		
    		}
    		System.out.println();
    	}
    	*/
    	
    	//Condiciones Iniciales
    	//Poblaci�n inicial.
    	int cantPI = 50;
    	
    	//Selecci�n
    	int cantSel = 10; //Elementos m seleccionados para Ranking.
    	
    	//Mutaci�n.
    	double porMut = 0.08;
    	int indMut = 3;
    	int bitMut = 100;
    	
    	//Ciclos
    	int ciclos = 3;
    	
        System.out.println();
    	System.out.println("----POBLACI�N INICIAL----");
    	Poblacion poblaIni = new Poblacion(cantPI, true); 
    	
	    System.out.println("En Bits:");   	    	
    	//Muestra Poblaci�n por BIT.    	
	    //poblaIni.mostrarPoblacionBin();
  
        System.out.println();
        System.out.println("En Decimales:");       	
       //Muestra Poblabcion en forma Decimal.
       //poblaIni.mostrarPoblacionDec();
       
       System.out.println();
       System.out.println("Funci�n Aptitud Po:");  
       poblaIni.mostrarFA();
       
       
       //COMIENZA EL CICLO DE PARO 
       for(int c=1;c<ciclos;c++)
	   {
    	   System.out.println();
    	   System.out.println("CICLO: " + c);
	       
	       System.out.println();
	       System.out.println("----SELECCION----"); 
	       Poblacion poblaSel = new Poblacion(cantSel, false); 
	       poblaSel = Operador.Seleccion(poblaIni, cantSel);
	       
	       System.out.println("Funci�n Aptitud Ps:");  
	       poblaSel.mostrarFA();
	       
	       System.out.println();
	       System.out.println("----CRUZAMIENTO----");   
	       
	       Poblacion poblaCruz = new Poblacion(cantSel, false);       
	       poblaCruz = Operador.Cruzamiento(poblaSel,cantSel);       
	  	  
		    System.out.println("En Bits:");   	    	
		   	//Muestra Poblaci�n por BIT.    	
		    //poblaCruz.mostrarPoblacionBin();
	              
	       System.out.println();
	       System.out.println("En Decimales:");       	
	      //Muestra Poblabcion en forma Decimal.
	      // poblaCruz.mostrarPoblacionDec();
	                   
	      System.out.println();
	      System.out.println("Funci�n Aptitud de Pc:");  
	      poblaCruz.mostrarFA();
	       
	      System.out.println();
	      System.out.println("----MUTACI�N----");   
	      Poblacion poblaMut = new Poblacion(cantSel, false); 

	      poblaMut = Operador.Mutacion(poblaCruz, porMut, indMut, bitMut);
	      
	      //Se asigna la Poblaci�n Mutada para el pr�ximo ciclo.
	      poblaIni = poblaMut;      
       }
       
       //Poblacion Final, le asigno el �ltimo contenido que le quedo a la Pi
       //No hace falta esta asignacion pero es para que quede claro.
       Poblacion poblaFin = new Poblacion(cantSel, false); 
       poblaFin = poblaIni;
       
       //AC� SE TIENE QUE MOSTRAR EL MEJOR CON LISTADO DE LOS JUGADORES.
       System.out.println();
	   System.out.println("---- POBLACION FINAL----");   
   	   Arrays.sort(poblaFin.Individuos);
   	   poblaIni.mostrarFA();
   	  
	   System.out.println("---- EL MEJOR INDIVIDUO ENCONTRADO TIENE UNA FA DE----");  
	   System.out.println(poblaFin.Individuos[0].puntaje);	   
    }
}