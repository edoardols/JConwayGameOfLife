package controller.controllermodel;

import model.modelcontroller.ModelForController;

public class ControllerToModel {

    public ControllerToModel() {
    }

    public static void newBlankOrganism(int nRows, int nColumns) {
        ModelForController.newBlankOrganism(nRows, nColumns);
    }

    public static void newRandomOrganism(int nRows, int nColumns, int percentage) {
        ModelForController.newRandomOrganism(nRows, nColumns, percentage);
    }

    public static void newPresetSimulation(String presetCode, int iIndex, int jIndex) {
        ModelForController.newPresetOrganism(presetCode, iIndex, jIndex);
    }

    public static void generateNextGeneration() {
        ModelForController.generateNextGeneration();
    }

    public static void restartOrganism() {
        ModelForController.restartOrganism();
    }

    public static void resetOrganism() {
        ModelForController.resetOrganism();
    }

    public static void startTimer() {
        ModelForController.startSimulation();
    }

    public static void setDelayUp() {
        ModelForController.setDelayUp();
    }

    public static void setDelayDown() {
        ModelForController.setDelayDown();
    }

    public static void pauseSimulation() {
        ModelForController.pauseSimulation();
    }

    public static void unpauseSimulation() {
        ModelForController.unpauseSimulation();
    }

    public static void changeStateCell(int iIndex, int jIndex) {
        ModelForController.changeStateCell(iIndex, jIndex);
    }

    public static boolean isGameStarted() {
        return ModelForController.isGameStarted();
    }

    public static String[] getPresetList() {
        return ModelForController.getPresetList();
    }

}
