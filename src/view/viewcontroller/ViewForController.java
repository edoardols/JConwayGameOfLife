package view.viewcontroller;

import view.viewclasses.SimulationGridJPanel;

public class ViewForController {

    public ViewForController() {

    }

    public static void paintSimulation(int[][] organism) {
        SimulationGridJPanel.getInstance().paintOrganism(organism);
    }

}
