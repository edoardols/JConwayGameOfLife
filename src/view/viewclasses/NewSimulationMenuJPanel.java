package view.viewclasses;

import view.viewcontroller.ViewToController;

import javax.swing.*;
import java.awt.*;

public class NewSimulationMenuJPanel extends JPanel {

    private static NewSimulationMenuJPanel newSimulationMenuJPanelInstance = null;

    private final JButton simulationMenuButton;
    private final JButton blankSimulationButton;
    private final JButton randomSimulationButton;
    private final JButton resumeGameButton;
    private static boolean simulationIsLoaded;

    private NewSimulationMenuJPanel() {
        super();
        this.setLayout(new GridLayout(0,1));

        simulationIsLoaded = false;

        this.simulationMenuButton = new JButton("Back");
        this.simulationMenuButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(MainMenuJPanel.getInstance());
        });

        this.resumeGameButton = new JButton("Resume");
        this.resumeGameButton.addActionListener(e -> {
            resumeGameEvent();
        });

        this.blankSimulationButton = new JButton("Blank");
        this.blankSimulationButton.addActionListener(e -> {
            getInformationSimulation("Blank");

        });
        this.randomSimulationButton = new JButton("Random");
        this.randomSimulationButton.addActionListener(e -> {
            getInformationSimulation("Random");
        });

        this.add(resumeGameButton);
        this.add(blankSimulationButton);
        this.add(randomSimulationButton);
        this.add(simulationMenuButton);
    }

    private static void getInformationSimulation(String string) {
        boolean inputIsGot = false;
        if (string.equals("Blank")) {
            inputIsGot = getInformationBlankSimulationJDialog("Dimension Simulation");
        }

        if (string.equals("Random")) {
            inputIsGot = getInformationRandomSimulationJDialog("Dimension Simulation");
        }

        if (inputIsGot) {
            GUIJFrame.getInstance().setNewCurrentPanel(SimulationJPanel.getInstance());
            simulationIsLoaded = true;
        }
    }

    private static boolean getInformationBlankSimulationJDialog(String title) {
        JTextField rows = new JTextField();
        JTextField columns = new JTextField();

        //TODO DI DEFAULT
        rows.setText("100");
        columns.setText("100");

        Object[] message = {
                "Rows:", rows,
                "Columns:", columns
        };

        int option = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            //TODO VERIFICARE SE UNA STRINGA CONTIENE NUMERI matches("-?\\d+") GESTIONE DELLE ECCEZIONI
            if ( isANumberAndPositive(rows.getText()) && isANumberAndPositive(columns.getText()) ) {
                ViewToController.newBlankSimulation(Integer.valueOf(rows.getText()),Integer.valueOf(columns.getText()));
                return true;
            }else {
                return getInformationBlankSimulationJDialog("Invalid Input! - Please Retry!");
            }
        }else {
            return false;
        }

    }

    private static boolean getInformationRandomSimulationJDialog(String title) {
        JTextField rows = new JTextField();
        JTextField columns = new JTextField();
        JTextField percentage = new JTextField();

        //TODO DI DEFAULT
        rows.setText("100");
        columns.setText("100");
        percentage.setText("40");

        Object[] message = {
                "Rows:", rows,
                "Columns:", columns,
                "Percentage of cell alive:", percentage
        };

        int option = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            //TODO VERIFICARE SE UNA STRINGA CONTIENE NUMERI matches("-?\\d+") GESTIONE DELLE ECCEZIONI
            if ( isANumberAndPositive(rows.getText()) && isANumberAndPositive(columns.getText()) && isAPercentage(percentage.getText()) ) {
                ViewToController.newRandomSimulation(Integer.valueOf(rows.getText()),Integer.valueOf(columns.getText()), Integer.valueOf(percentage.getText()));
                return true;
            }else {
                return getInformationRandomSimulationJDialog("Invalid Input! - Please Retry!");
            }
        }else {
            return false;
        }

    }

    private static boolean isANumberAndPositive(String string) {
        if ( string.matches("-?\\d+") ) { //TODO GESTIONE DELLE ECCEZIONI
            return (Integer.valueOf(string) >= 0 );
        }else {
            return false;
        }
    }

    private static boolean isAPercentage (String string) {
        if (isANumberAndPositive(string)) { //TODO GESTIONE DELLE ECCEZIONI
            return ( (Integer.valueOf(string) - 100) <= 0 );
        }else {
            return false;
        }
    }

    private static void resumeGameEvent() {
        if (simulationIsLoaded) {
            ViewToController.restartSimulation();
            GUIJFrame.getInstance().setNewCurrentPanel(SimulationJPanel.getInstance());
        } else {
            JOptionPane.showMessageDialog(GUIJFrame.getInstance(),
                    "There is no simulation to resume.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public static NewSimulationMenuJPanel getInstance() {
        if (newSimulationMenuJPanelInstance == null)
            newSimulationMenuJPanelInstance = new NewSimulationMenuJPanel();
        return newSimulationMenuJPanelInstance;
    }
}
