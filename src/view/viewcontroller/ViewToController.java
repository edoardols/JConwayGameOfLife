package view.viewcontroller;

import controller.controllerview.ControllerForView;

public class ViewToController {

    public ViewToController() {
    }
    public static boolean isGameStarted() {
        return ControllerForView.isGameStarted();
    }

    public static void changeStateCell(int iIndex, int jIndex) {
        ControllerForView.changeStateCell(iIndex, jIndex);
    }

    public static void generateOneNextGeneration() {
        ControllerForView.generateOneNextGeneration();
    }

    public static void resetSimulation() {
        ControllerForView.resetSimulation();
    }

    public static void startTimer() {
        ControllerForView.startTimer();
    }

    public static void setDelayUp() {
        ControllerForView.setDelayUp();
    }

    public static void setDelayDown() {
        ControllerForView.setDelayDown();
    }

    public static void pauseSimulation() {
        ControllerForView.pauseSimulation();
    }

    public static void unpauseSimulation() {
        ControllerForView.unpauseSimulation();
    }

    public static void restartSimulation() {
        ControllerForView.restartSimulation();
    }

    public static void newPresetSimulation(String presetCode, int iIndex, int jIndex) {
        ControllerForView.newPresetSimulation(presetCode, iIndex, jIndex);
    }

    public static String[] getPresetList() {
        return ControllerForView.getPresetList();
    }

    public static void newBlankSimulation(int nRows, int nColumns) {
        ControllerForView.newBlankSimulation(nRows, nColumns);
    }

    public static void newRandomSimulation(int nRows, int nColumns, int percentage) {
        ControllerForView.newRandomSimulation(nRows, nColumns, percentage);
    }

}
