package view.viewclasses;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class SettingsJPanel extends JPanel {

    private static SettingsJPanel settingsJPanelInstance = null;

    private JButton mainMenuButton;
    private JComboBox gridColorComboBox;
    private JComboBox cellColorComboBox;

    private JLabel gridColorLabel;
    private JLabel cellColorLabel;

    private HashMap<String, Color> mapColor;

    //IDENTICA IN SIMULATION GRID
    private static final String[] STRING_COLOR_ARRAY = {"Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green",
            "Light Gray", "Magenta", "Orange", "Pink", "Red", "Yellow"};
    private final static Color[] COLOR_ARRAY = {Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green,
            Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.yellow};

    private SettingsJPanel.colorPanelPreview colorPanelPreview;
    private JSplitPane splitPane;

    private SettingsJPanel() {
        super();
        this.setLayout(new GridLayout(0,1));

        mapColor = new HashMap<>();
        createColorMap();

        this.colorPanelPreview = new colorPanelPreview();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, settingsOptionPanel() ,colorPanelPreview);
        colorPanelPreview.setPreferredSize(new Dimension(220,150));

        splitPane.setDividerLocation(150);

        this.add(splitPane);
    }

    private JPanel settingsOptionPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0,1));

        this.mainMenuButton = new JButton("Back");
        this.mainMenuButton.addActionListener(e -> {
            GUIJFrame.getInstance().setNewCurrentPanel(MainMenuJPanel.getInstance());
        });

        this.gridColorLabel = new JLabel("Grid color");

        this.gridColorComboBox = new JComboBox(STRING_COLOR_ARRAY);
        this.gridColorComboBox.addActionListener(e -> {
            String colorString = (String)this.gridColorComboBox.getSelectedItem();
            Color color = mapColor.get(colorString);
            this.colorPanelPreview.setGridColor(color);
            SimulationGridJPanel.getInstance().setGridColor(color);

        });

        this.cellColorLabel = new JLabel("Cell color");

        this.cellColorComboBox = new JComboBox(STRING_COLOR_ARRAY);
        this.cellColorComboBox.addActionListener(e -> {
            String colorString = (String) this.cellColorComboBox.getSelectedItem();
            Color color = mapColor.get(colorString);
            this.colorPanelPreview.setCellColor(color);
            SimulationGridJPanel.getInstance().setCellColor(color);
        });

        panel.add(this.gridColorLabel);
        panel.add(this.gridColorComboBox);
        panel.add(this.cellColorLabel);
        panel.add(this.cellColorComboBox);
        panel.add(this.mainMenuButton);

        return panel;
    }

    private void createColorMap() {
        for (int k = 0; k < COLOR_ARRAY.length; k++) {
            mapColor.put(STRING_COLOR_ARRAY[k] ,COLOR_ARRAY[k]);
        }
    }

    protected static class colorPanelPreview extends JPanel {

        private Color gridColor;
        private Color cellColor;

        private Line2D.Double line;
        private Rectangle2D cell;

        int zoomOffset = 20;
        int nRows = 5;
        int nColumns = 5;
        int borderOffset = 25;

        public colorPanelPreview() {
            super();

            this.line = new Line2D.Double();
            this.cell = new Rectangle2D.Double();

            this.gridColor = Color.gray;
            this.cellColor = Color.yellow;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;

            paintGrid(g2d);
            paintCellOnGrid(g2d);
            revalidate();
        }

        public void setGridColor(Color gridColorPassed) {
            this.gridColor = gridColorPassed;
            repaint();
        }

        public void setCellColor(Color cellColorPassed) {
            this.cellColor = cellColorPassed;
            repaint();
        }

        private void paintGrid(Graphics2D g2d) {
            g2d.setColor(gridColor);

            // Paint the horizontal grid lines
            for (int i = 0; i <= nRows; i++) {
                this.line.setLine(borderOffset, borderOffset + i * this.zoomOffset,
                        borderOffset + nColumns * zoomOffset, borderOffset + i * zoomOffset);
                g2d.draw(this.line);
            }

            // Paint the vertical grid lines
            for (int j = 0; j <= nColumns; j++) {
                this.line.setLine(borderOffset + j * this.zoomOffset, borderOffset,
                        borderOffset + j * this.zoomOffset, borderOffset + nRows * this.zoomOffset);
                g2d.draw(this.line);
            }
        }

        private void paintCellOnGrid(Graphics2D g2d) {

            int[][] glider = new int[3][3];
            int DEAD_STATE = 0;
            int ALIVE_STATE = 1;
            glider[0][0] = DEAD_STATE;
            glider[0][1] = ALIVE_STATE;
            glider[0][2] = DEAD_STATE;
            glider[1][0] = DEAD_STATE;
            glider[1][1] = DEAD_STATE;
            glider[1][2] = ALIVE_STATE;
            glider[2][0] = ALIVE_STATE;
            glider[2][1] = ALIVE_STATE;
            glider[2][2] = ALIVE_STATE;

            //paint state alive and get index
            for (int i = 1; i < nRows-1; i++) {
                for (int j = 1; j < nColumns-1; j++) {
                    if (glider[i-1][j-1] == 1) {
                        //TODO CODIFICA CELLA VIVA
                        //DIPINGI CELLA A I-1 E J-1
                        this.cell.setRect(borderOffset + j * this.zoomOffset, borderOffset + i * this.zoomOffset, zoomOffset, zoomOffset);
                        g2d.setColor(cellColor);
                        g2d.fill(this.cell);
                        g2d.setColor(gridColor);
                        g2d.draw(this.cell);
                    }
                }
            }
        }

    }

    public static SettingsJPanel getInstance() {
        if (settingsJPanelInstance == null)
            settingsJPanelInstance = new SettingsJPanel();
        return settingsJPanelInstance;
    }
}
