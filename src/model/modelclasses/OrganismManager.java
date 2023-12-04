package model.modelclasses;

import model.modelcontroller.ModelToController;

public class OrganismManager {

    private static OrganismLogic organismLogic;

    public OrganismManager() {
    }

    public static boolean isGameStarted() {
        return organismLogic.isGameStarted();
    }

    public static void changeStateCell(int iIndex, int jIndex) {
        organismLogic.changeStateCell(iIndex, jIndex);
        printOrganism();
    }

    public static void printOrganism() {
        ModelToController.paintSimulation(organismLogic.getCurrentGenerationMatrix2D());
    }

    public static void generateNextGeneration() {
        organismLogic.generateNextGeneration();
        printOrganism();
    }


    private static void loadOrganism(int[][] organismToLoad) {
        organismLogic = new OrganismLogic(organismToLoad.length, organismToLoad[0].length);
        organismLogic.loadOnCurrentGenerationMatrix(organismToLoad);
        printOrganism();
    }

    public static void newRandomOrganism(int nRowsUserInput, int nColumnsUserInput, int percentage) {
        loadOrganism( OrganismLoader.getRandomOrganism(nRowsUserInput,nColumnsUserInput, percentage) );
    }

    public static void newBlankOrganism(int nRowsUserInput, int nColumnsUserInput) {
        loadOrganism( OrganismLoader.getBlankOrganism( nRowsUserInput,nColumnsUserInput ) );
    }

    public static void newPresetOrganism(String presetCode, int iIndex, int jIndex) {
        organismLogic.setPreset( iIndex, jIndex, OrganismPreset.getPreset(presetCode) );
        printOrganism();

    }

    public static void restartOrganism() {
        loadOrganism( OrganismLoader.getPassedOrganismToOrganismManager() );
    }

    public static void resetOrganism() {
        organismLogic.resetOrganism();
        printOrganism();
    }

}
