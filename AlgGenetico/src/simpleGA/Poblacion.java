package simpleGA;

public class Poblacion {

    Individuo[] individuals;

    /*
     * Constructors
     */
    // Create a poblacion
    public Poblacion(int populationSize, boolean initialise) {
        individuals = new Individuo[populationSize];
        // Initialise poblacion
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                Individuo newIndividuo = new Individuo();
                newIndividuo.generateIndividual();
                saveIndividuo(i, newIndividuo);
            }
        }
    }

    /* Getters */
    public Individuo getIndividual(int index) {
        return individuals[index];
    }

    public Individuo getFittest() {
        Individuo fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get poblacion size
    public int size() {
        return individuals.length;
    }

    // Save individual
    public void saveIndividuo(int index, Individuo indiv) {
        individuals[index] = indiv;
    }
}
