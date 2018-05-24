package simpleGA;

public class GA {

    public static void main(String[] args) {

        // Set a candidate solution
        FuncionActitud.setSolution("1111000000000000000000000000000000000000000000000000000000001111");

        // Create an initial poblacion
        Poblacion myPop = new Poblacion(50, true);
        
        // Evolve our poblacion until we reach an optimum solution
        int generationCount = 0;
        while (myPop.getFittest().getFitness() < FuncionActitud.getMaxFitness()) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
            myPop = Algoritmo.evolvePopulation(myPop);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());

    }
}