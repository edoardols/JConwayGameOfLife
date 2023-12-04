package controller.controllerview;

import controller.controllermodel.ControllerToModel;

public class ControllerForView {

    public ControllerForView() {

    }

    public static void generateOneNextGeneration() {
        ControllerToModel.generateNextGeneration();
    }

    public static void startTimer() {
        ControllerToModel.startTimer();
    }

    public static void setDelayUp() {
        ControllerToModel.setDelayUp();
    }

    public static void setDelayDown() {
        ControllerToModel.setDelayDown();
    }

    public static void pauseSimulation() {
        ControllerToModel.pauseSimulation();
    }

    public static void unpauseSimulation() {
        ControllerToModel.unpauseSimulation();
    }

    public static void restartSimulation() {
        ControllerToModel.restartOrganism();
    }

    public static void newPresetSimulation(String presetCode, int iIndex, int jIndex) {
        ControllerToModel.newPresetSimulation(presetCode, iIndex, jIndex);
    }

    public static void resetSimulation() {
        ControllerToModel.resetOrganism();
    }

    public static void newBlankSimulation(int nRows, int nColumns) {
        ControllerToModel.newBlankOrganism(nRows, nColumns);
    }

    public static void newRandomSimulation(int nRows, int nColumns, int percentage) {
        ControllerToModel.newRandomOrganism(nRows, nColumns, percentage);
    }

    public static void changeStateCell(int iIndex, int jIndex) {
        ControllerToModel.changeStateCell(iIndex, jIndex);
    }

    public static boolean isGameStarted() {
        return ControllerToModel.isGameStarted();
    }

    public static String[] getPresetList() {
        return ControllerToModel.getPresetList();
    }
}
