package view.viewclasses;

import javax.swing.*;
import java.awt.*;

public class TutorialJPanel extends JPanel {

    private static TutorialJPanel tutorialJPanelInstance = null;

    private final JButton mainMenuButton;

    private final String[] tutorialList = {"You can generate a cellular automata with an infinite number of cell... almost infinite",
            "You can click on a cell to change its state",
            "the reset button allows you to clear the simulation content",
            "the restart button allows you to reload the initial state of your simulation",
            "you can load some preset, the most famous one",
            "",
            "now play and have fun!"
    };

    private TutorialJPanel() {
        super();
        this.setLayout(new GridLayout(0,1));

        createTutorialLabel();

        this.mainMenuButton = new JButton("Back");
        this.mainMenuButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(MainMenuJPanel.getInstance());
        });

        this.add(mainMenuButton);
    }

    private void createTutorialLabel() {
        for (int i = 0; i < tutorialList.length; i++) {
            JLabel tutorial = new JLabel();
            tutorial.setText(tutorialList[i]);
            this.add(tutorial);
        }
    }

    public static TutorialJPanel getInstance() {
        if (tutorialJPanelInstance == null)
            tutorialJPanelInstance = new TutorialJPanel();
        return tutorialJPanelInstance;
    }
}
