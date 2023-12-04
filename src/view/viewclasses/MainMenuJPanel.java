package view.viewclasses;

import javax.swing.*;
import java.awt.*;

public class MainMenuJPanel extends JPanel {

    private static MainMenuJPanel mainMenuJPanelInstance = null;


    private JButton simulationButton;
    private JButton rulesAndStoryButton;
    private JButton achievementsButton;
    private JButton settingsButton;
    private JButton tutorialButton;


    private MainMenuJPanel() {
        super();
        this.setLayout(new GridLayout(0,1));

        this.rulesAndStoryButton = new JButton("RulesAndStory");
        this.rulesAndStoryButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(RulesAndStoryJPanel.getInstance());
        });

        this.simulationButton = new JButton("Simulation");
        this.simulationButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(NewSimulationMenuJPanel.getInstance());
        });

        this.achievementsButton = new JButton("Achievements");
        this.achievementsButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(AchievementsJPanel.getInstance());
        });

        this.tutorialButton = new JButton("Tutorial");
        this.tutorialButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(TutorialJPanel.getInstance());
        });

        this.settingsButton = new JButton("Settings");
        this.settingsButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(SettingsJPanel.getInstance());
        });

        this.add(rulesAndStoryButton);
        this.add(simulationButton);
        this.add(achievementsButton);
        this.add(tutorialButton);
        this.add(settingsButton);
    }

    public static MainMenuJPanel getInstance() {
        if (mainMenuJPanelInstance == null)
            mainMenuJPanelInstance = new MainMenuJPanel();
        return mainMenuJPanelInstance;
    }
}
