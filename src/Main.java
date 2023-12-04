import view.viewclasses.GUIJFrame;


public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                () -> GUIJFrame.getInstance().setVisible(true));

    }
}
