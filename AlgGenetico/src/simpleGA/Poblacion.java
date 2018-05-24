package simpleGA;

public class Poblacion {

    Individuo[] Individuos;
    int populationSize;
    
    /*
     * Constructors
     */
    // Create a poblacion
    public Poblacion(int popSize, boolean initialise) {
    	populationSize = popSize;
    	Individuos = new Individuo[populationSize];
    	
        // Initialise poblacion    	
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize; i++) {
                Individuo newIndividuo = new Individuo();
                Individuos[i] = newIndividuo;
            }
        }
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
