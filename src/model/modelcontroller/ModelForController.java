package model.modelcontroller;

import model.modelclasses.OrganismManager;
import model.modelclasses.OrganismPreset;
import model.modelclasses.OrganismTimer;

public class ModelForController {

    public ModelForController() {
    }

    public static boolean isGameStarted() {
        return OrganismManager.isGameStarted();
    }

    public static void changeStateCell(int iIndex, int jIndex) {
        OrganismManager.changeStateCell(iIndex, jIndex);
    }

    public static void newBlankOrganism(int nRows, int nColumns) {
        OrganismManager.newBlankOrganism(nRows, nColumns);
    }

    public static void newRandomOrganism(int nRows, int nColumns, int percentage) {
        OrganismManager.newRandomOrganism(nRows, nColumns, percentage);
    }

    public static void newPresetOrganism(String presetCode, int iIndex, int jIndex) {
        OrganismManager.newPresetOrganism(presetCode, iIndex, jIndex);
    }

    public static void generateNextGeneration() {
        OrganismManager.generateNextGeneration();
    }

    public static void restartOrganism() {
        OrganismManager.restartOrganism();
    }

    public static void resetOrganism() {
        OrganismManager.resetOrganism();
    }

    public static void startSimulation() {
        OrganismTimer.createTimer();
        OrganismTimer.startTimer();
    }

    public static void setDelayUp() {
        OrganismTimer.setDelayUp();
    }

    public static void setDelayDown() {
        OrganismTimer.setDelayDown();
    }

    public static void pauseSimulation() {
        OrganismTimer.pauseTimer();
    }

    public static void unpauseSimulation() {
        OrganismTimer.unpauseTimer();
    }

    public static String[] getPresetList() {
        return OrganismPreset.getPresetList();
    }

}
