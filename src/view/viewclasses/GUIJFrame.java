package view.viewclasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIJFrame extends JFrame {

    private static GUIJFrame guiJFrameInstance = null;

    private JPanel currentPanel;

    private GUIJFrame() {
        super("JConwayGameOfLife");

        this.currentPanel = MainMenuJPanel.getInstance();

        this.getContentPane().add(this.currentPanel, BorderLayout.CENTER);

        this.pack();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                closeGUI();
            }
        });
    }

    public void setNewCurrentPanel(JPanel newCurrentPanel) {
        this.currentPanel = newCurrentPanel;

        this.getContentPane().removeAll();
        this.getContentPane().add(this.currentPanel);

        this.pack(); //ridimensiona il JFrame
        revalidate();
        repaint();
    }

    public void closeGUI() {
        int result = JOptionPane.showConfirmDialog(GUIJFrame.getInstance(),
                "Do you want to Exit ?", "Exit Confirmation: ",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            GUIJFrame.getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else if (result == JOptionPane.NO_OPTION)
            GUIJFrame.getInstance().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static GUIJFrame getInstance() {
        if (guiJFrameInstance == null)
            guiJFrameInstance = new GUIJFrame();
        return guiJFrameInstance;
    }

}
