package model.modelclasses;

import java.util.Random;

public class OrganismLoader { //GESTIONE DEI VARI SALVATAGGI E PRESET

    private static int ALIVE_STATE = 1; //MUST BE THE SAME AS ALIVE_STATE IN ORGANISM
    private static int DEAD_STATE = 0; //MUST BE THE SAME AS DEAD_STATE IN ORGANISM

    private static int[][] passedOrganismToOrganismManager;

    private static int nRowsWithBorder;
    private static int nColumnsWithBorder;

    public OrganismLoader() {
    }

    private static void setGridBorder (int nRowsUserInput, int nColumnsUserInput) {
        nRowsWithBorder = nRowsUserInput + 2;
        nColumnsWithBorder = nColumnsUserInput + 2;
    }

    //----------LAST PASSED----------//
    public static int[][] getPassedOrganismToOrganismManager() { //MANTENERE LA STESSA CONFIGURAZIONE E RICARICARLA
        return passedOrganismToOrganismManager;
    }

    //----------RANDOM----------//
    public static int[][] getRandomOrganism (int nRowsUserInput, int nColumnsUserInput, int percentageUserInput) {
        setGridBorder (nRowsUserInput, nColumnsUserInput);
        generateRandomOrganism(percentageUserInput);
        return passedOrganismToOrganismManager;
    }

    private static void generateRandomOrganism(int percentage) { //percentage 0 to 100

        passedOrganismToOrganismManager = new int[nRowsWithBorder][nColumnsWithBorder];
        int random;

        //the result of the next two for is that a chosen percentage of cell in the grid = nRows * nColumns is ALIVE
        //exclude border
        for (int i = 1; i < nRowsWithBorder-1; i++) {
            for (int j = 1; j < nColumnsWithBorder-1; j++) {
                random = new Random().nextInt(100); //generate random number from 0 to 99
                if (random < percentage) {
                    passedOrganismToOrganismManager[i][j] = ALIVE_STATE;
                }
                else {
                    passedOrganismToOrganismManager[i][j] = DEAD_STATE;
                }
            }
        }

    }

    //----------BLANK----------//
    public static int[][] getBlankOrganism(int nRowsUserInput, int nColumnsUserInput) {
        setGridBorder (nRowsUserInput, nColumnsUserInput);
        //a blank organism can be seen as a random organism with 0 cell alive (or a percentage of cell alive = 0)
        generateRandomOrganism(0);
        return passedOrganismToOrganismManager;
    }

}
