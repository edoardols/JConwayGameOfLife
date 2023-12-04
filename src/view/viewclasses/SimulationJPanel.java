package view.viewclasses;

import view.viewcontroller.ViewToController;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SimulationJPanel extends JPanel implements KeyListener {

    private static SimulationJPanel simulationJPanelInstance = null;

    private JPanel upperPanel;
    private JPanel bottomPanel;
    private JButton simulationMenuButton;
    private JButton zoomOutButton;
    private JButton zoomInButton;
    private JButton restartSimulationButton;
    private JButton resetSimulationButton;
    private JButton startSimulationButton;
    private JButton nextStepSimulationButton;
    private JButton speedUpSimulationButton;
    private JButton speedDownSimulationButton;

    private JScrollPane scrollPaneSimulationPanel;

    private final int LEFT_ARROW = 37;
    private final int UP_ARROW = 38;
    private final int RIGHT_ARROW = 39;
    private final int DOWN_ARROW = 40;

    private final int X_UNIT_MOVE = 20;
    private final int Y_UNIT_MOVE = 20;

    private JButton presetButton;

    private SimulationJPanel() {
        super();
        this.setLayout(new BorderLayout());

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow(true);

        this.simulationMenuButton = new JButton("Menu");
        this.simulationMenuButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(NewSimulationMenuJPanel.getInstance());
            ViewToController.pauseSimulation();
        });

        this.zoomInButton = new JButton("Zoom +");
        this.zoomInButton.addActionListener(e -> {
            SimulationGridJPanel.getInstance().zoomIn();
        });

        this.zoomOutButton = new JButton("Zoom -");
        this.zoomOutButton.addActionListener(e -> {
            SimulationGridJPanel.getInstance().zoomOut();
        });

        this.presetButton = new JButton("Preset");
        this.presetButton.addActionListener(e -> {
            newPreset();
        });

        this.restartSimulationButton = new JButton("Restart");
        this.restartSimulationButton.addActionListener(e -> {
            ViewToController.restartSimulation();
            pauseSimulation();
        });

        this.resetSimulationButton = new JButton("Reset");
        this.resetSimulationButton.addActionListener(e -> {
            ViewToController.resetSimulation();
            pauseSimulation();
        });

        this.startSimulationButton = new JButton("Start");
        this.startSimulationButton.addActionListener(e -> {
            ViewToController.startTimer();
            changePlayLabel();
        });

        this.nextStepSimulationButton = new JButton("Next");
        this.nextStepSimulationButton.addActionListener(e -> {
            ViewToController.generateOneNextGeneration();
            pauseSimulation();
        });

        this.speedUpSimulationButton = new JButton("Speed +");
        this.speedUpSimulationButton.addActionListener(e -> {
            ViewToController.setDelayDown();
            unpauseSimulation();
        });

        this.speedDownSimulationButton = new JButton("Speed -");
        this.speedDownSimulationButton.addActionListener(e -> {
            ViewToController.setDelayUp();
            unpauseSimulation();
        });

        this.scrollPaneSimulationPanel = new JScrollPane(SimulationGridJPanel.getInstance());
        this.scrollPaneSimulationPanel.setPreferredSize(new Dimension(500,500));

        this.upperPanel = new JPanel();
        this.bottomPanel = new JPanel();

        this.upperPanel.add(this.simulationMenuButton);
        this.upperPanel.add(this.zoomInButton);
        this.upperPanel.add(this.zoomOutButton);

        this.add(this.upperPanel, BorderLayout.PAGE_START);

        scrollPaneSimulationPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneSimulationPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPaneSimulationPanel, BorderLayout.CENTER);

        this.bottomPanel.add(this.presetButton);
        this.bottomPanel.add(this.restartSimulationButton);
        this.bottomPanel.add(this.resetSimulationButton);
        this.bottomPanel.add(this.startSimulationButton);
        this.bottomPanel.add(this.nextStepSimulationButton);
        this.bottomPanel.add(this.speedUpSimulationButton);
        this.bottomPanel.add(this.speedDownSimulationButton);

        this.add(this.bottomPanel, BorderLayout.PAGE_END);
    }

    private void newPreset() {
        //TODO
        String[] preset = ViewToController.getPresetList();
        String presetCode = (String) JOptionPane.showInputDialog(
                GUIJFrame.getInstance(),
                "Choose a preset:",
                "Preset Input",
                JOptionPane.PLAIN_MESSAGE,
                null,
                preset,
                0);
        if (presetCode != null) {
            SimulationGridJPanel.getInstance().setNextPresetToLoad(presetCode);

        }
    }

    private void pauseSimulation() {
        this.startSimulationButton.setText("Resume");
        ViewToController.pauseSimulation();
    }

    private void unpauseSimulation() {
        this.startSimulationButton.setText("Pause");
        ViewToController.unpauseSimulation();
    }

    private void changePlayLabel() {
        if (this.startSimulationButton.getText().equals("Pause")) {
            pauseSimulation();
        }
        else {
            unpauseSimulation();
        }
    }

    public static SimulationJPanel getInstance() {
        if (simulationJPanelInstance == null)
            simulationJPanelInstance = new SimulationJPanel();
        return simulationJPanelInstance;
    }

    //TODO TEST
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //LEFT_ARROW = 37;
        //UP_ARROW = 38;
        //RIGHT_ARROW = 39;
        //DOWN_ARROW = 40;

        if (e.getKeyCode() == LEFT_ARROW) {
            this.scrollPaneSimulationPanel.getHorizontalScrollBar().setValue(this.scrollPaneSimulationPanel.getHorizontalScrollBar().getValue() - X_UNIT_MOVE);
        }
        if (e.getKeyCode() == UP_ARROW) {
            this.scrollPaneSimulationPanel.getVerticalScrollBar().setValue(this.scrollPaneSimulationPanel.getVerticalScrollBar().getValue() - Y_UNIT_MOVE);
        }
        if (e.getKeyCode() == RIGHT_ARROW) {
            this.scrollPaneSimulationPanel.getHorizontalScrollBar().setValue(this.scrollPaneSimulationPanel.getHorizontalScrollBar().getValue() + X_UNIT_MOVE);
        }
        if (e.getKeyCode() == DOWN_ARROW) {
            this.scrollPaneSimulationPanel.getVerticalScrollBar().setValue(this.scrollPaneSimulationPanel.getVerticalScrollBar().getValue() + Y_UNIT_MOVE);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
