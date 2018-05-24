package simpleGA;

public class Algoritmo {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    /* Public methods */
    
    // Evolve a poblacion
    public static Poblacion evolvePopulation(Poblacion pop) {
        Poblacion newPoblacion = new Poblacion(pop.size(), false);

        // Keep our best individual
        if (elitism) {
            newPoblacion.saveIndividuo(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individuo indiv1 = tournamentSelection(pop);
            Individuo indiv2 = tournamentSelection(pop);
            Individuo newIndiv = crossover(indiv1, indiv2);
            newPoblacion.saveIndividuo(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPoblacion.size(); i++) {
            mutate(newPoblacion.getIndividual(i));
        }

        return newPoblacion;
    }

    // Crossover individuals
    private static Individuo crossover(Individuo indiv1, Individuo indiv2) {
        Individuo newSol = new Individuo();
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individuo indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }

    // Select individuals for crossover
    private static Individuo tournamentSelection(Poblacion pop) {
        // Create a tournament population
        Poblacion tournament = new Poblacion(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividuo(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individuo fittest = tournament.getFittest();
        return fittest;
    }
}