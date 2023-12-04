package view.viewclasses;


import javax.swing.*;
import java.awt.*;

public class AchievementsJPanel extends JPanel{

    private static AchievementsJPanel achievementsJPanelInstance = null;

    private final JButton mainMenuButton;

    private final String[] achievementList = { //1° Achievement, 2° Tips
            "The first cell has born", "1 cell has",
            "The first 100", "100 cell were born",
            "1K", "1000 cell were born",
            "Like bacteria", "10000 cell were born",
            "THEY ARE EVERYWHERE", "100000 cell were born",
            "First blood", "1 cell died",
            "A not very quiet neighborhood", "100 cell died",
            "Bronx", "1000 cell died",
            "-10K", "10000 cell died",
            "Like the dinosaurs", "100000 cell died",
            "The first cell has survived", "1 cell survived",
            "They keep going", "100 cell survived",
            "Garibaldi vibes", "1000 cell survived",
            "10^4", "10000 cell survived",
            "Your organism has passed 1 generation!", "the organism lasted for 1 generation",
            "Your organism has passed 10 generation!", "the organism lasted for 10 generation",
            "Your organism has lasted a century!", "the organism lasted for 100 generation",
            "Your organism has lasted for a millennium!", "the organism lasted for 1000 generation",
            "They are older than us!", "the organism lasted for 10000 generation",
            "The power of life in your click!", "change the state of 1 cell",
            "Now is it too much!", "change the state of 50 cell",
            "You are undecided!", "change the state of the same cell for 10 times",
            "If you blink you miss!", "place 1 blinkr",
            "It's party time!", "place 10 blinker",
            "Look it is like a bird, it flies away", "place 1 glider",
            "I Glider You!", "place 10 glider",
    };

    private AchievementsJPanel() {
        super();
        this.setLayout(new GridLayout(0,1));

        getAchievementPanel();

        this.mainMenuButton = new JButton("Back");
        this.mainMenuButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(MainMenuJPanel.getInstance());
        });

        this.add(mainMenuButton);
    }

    private void getAchievementPanel() {
        for (int i = 0; i < achievementList.length; i = i +2) {
            JLabel label = new JLabel();

            label.setText(achievementList[i]);
            label.setToolTipText(achievementList[i+1]);

            this.add(label);
        }
    }

    public static AchievementsJPanel getInstance() {
        if (achievementsJPanelInstance == null)
            achievementsJPanelInstance = new AchievementsJPanel();
        return achievementsJPanelInstance;
    }

}