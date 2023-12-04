package view.viewclasses;

import javax.swing.*;
import java.awt.*;

public class RulesAndStoryJPanel extends JPanel {

    private static RulesAndStoryJPanel rulesAndStoryJPanelInstance = null;

    private final String[] rulesList = {"The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells",
            "each of which is in one of two possible states, live or dead, (or populated and unpopulated, respectively).",
            "Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally",
            "adjacent. At each step in time, the following transitions occur:",
            "",
            "Rule 1: Any live cell with fewer than two live neighbours dies, as if by underpopulation.",
            "Rule 2: Any live cell with two or three live neighbours lives on to the next generation.",
            "Rule 3: Any live cell with more than three live neighbours dies, as if by overpopulation.",
            "Rule 4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.",
            ""
            };

    private final String[] storyList = {"A bit about the story of this theory-game.",
            "",
            "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician",
            "John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its",
            "initial state, requiring no further input. One interacts with the Game of Life by creating an initial",
            "configuration and observing how it evolves."
            };

    private final JButton mainMenuButton;

    private RulesAndStoryJPanel() {
        super();
        this.setLayout(new GridLayout(0,1));

        createRulesLabel();
        createStoryLabel();

        this.mainMenuButton = new JButton("Back");
        this.mainMenuButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(MainMenuJPanel.getInstance());
        });

        this.add(this.mainMenuButton);
    }

    private void createRulesLabel() {
        for (int i = 0; i < rulesList.length; i++) {
            JLabel rule = new JLabel();
            rule.setText(rulesList[i]);
            this.add(rule);
        }
    }

    private void createStoryLabel() {
        for (int i = 0; i < storyList.length; i++) {
            JLabel story = new JLabel();
            story.setText(storyList[i]);
            this.add(story);
        }
    }

    public static RulesAndStoryJPanel getInstance() {
        if (rulesAndStoryJPanelInstance == null)
            rulesAndStoryJPanelInstance = new RulesAndStoryJPanel();
        return rulesAndStoryJPanelInstance;
    }
}
