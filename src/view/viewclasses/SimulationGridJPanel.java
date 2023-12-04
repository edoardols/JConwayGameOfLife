package view.viewclasses;

import view.viewcontroller.ViewToController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class SimulationGridJPanel extends JPanel implements MouseListener {

    private static SimulationGridJPanel simulationGridJPanelInstance = null;

    //TEST
    private Line2D.Double line;
    private Rectangle2D cell;

    //parametri passati
    private int nRows;
    private int nColumns;
    private final int[] FIXED_ZOOM = {5, 10, 20, 30, 40, 50}; //express in milliseconds => 1000 milliseconds = 1 sec
    private int DEFAULT_ZOOM_INDEX_ARRAY = 2;

    private final int borderOffset = 0;
    private int zoomOffset = 10;
    private int[][] matrixCopy;

    private boolean isPresetRequest;

    private Color gridColor;
    private Color cellColor;

    private String nextPresetToLoad;

    private Dimension drawArea = null;

    private SimulationGridJPanel() {
        super();

        drawArea = new Dimension(0,0);

        isPresetRequest = false;
        nextPresetToLoad = "";

        this.gridColor = Color.gray;
        this.cellColor = Color.yellow;

        this.line = new Line2D.Double();
        this.cell = new Rectangle2D.Double();


        zoomOffset = FIXED_ZOOM[DEFAULT_ZOOM_INDEX_ARRAY];

        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        paintGrid(g2d);
        paintCellOnGrid(g2d);

        setDrawArea();
        this.setPreferredSize(drawArea);

        revalidate();
    }

    private void setDrawArea() {
        this.drawArea.setSize(borderOffset + zoomOffset* nColumns, borderOffset + zoomOffset* nRows);
    }

    //TODO

    public void setGridColor(Color color) {
        gridColor = color;
    }

    public void setCellColor(Color color) {
        cellColor = color;
    }

    //TODO
    public void matrixCopy(int[][] matrix) {
        matrixCopy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrixCopy[i][j] = matrix[i][j];
            }
        }
    }

    public void paintOrganism(int[][] organismMatrixToPaint) {
        setGridDimension(organismMatrixToPaint.length, organismMatrixToPaint[0].length);
        //TODO
        matrixCopy(organismMatrixToPaint);

        repaint();
    }
    private void setGridDimension(int nRows, int nColumns) {
        this.nRows = nRows -2; //TODO ELIMINA IL BORDO
        this.nColumns = nColumns -2;
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
        //paint state alive and get index
        for (int i = 1; i < matrixCopy.length-1; i++) {
            for (int j = 1; j < matrixCopy[0].length-1; j++) {
                if (matrixCopy[i][j] == 1) {
                    //TODO CODIFICA CELLA VIVA
                    //DIPINGI CELLA A I-1 E J-1
                    this.cell.setRect(borderOffset + (j-1) * this.zoomOffset, borderOffset + (i-1) * this.zoomOffset, zoomOffset, zoomOffset);
                    g2d.setColor(cellColor);
                    g2d.fill(this.cell);
                    g2d.setColor(gridColor);
                    g2d.draw(this.cell);
                }
            }
        }
    }

    public void zoomIn() {
        if (DEFAULT_ZOOM_INDEX_ARRAY < (FIXED_ZOOM.length-1) ) {
            DEFAULT_ZOOM_INDEX_ARRAY++;
            zoomOffset = FIXED_ZOOM[DEFAULT_ZOOM_INDEX_ARRAY];
            repaint();
        }
    }

    public void zoomOut() {
        if (DEFAULT_ZOOM_INDEX_ARRAY > 0 ) {
            DEFAULT_ZOOM_INDEX_ARRAY--;
            zoomOffset = FIXED_ZOOM[DEFAULT_ZOOM_INDEX_ARRAY];
            repaint();
        }
    }

    public boolean isMouseInGrid(MouseEvent e) {
        boolean xIsInGrid = ( (e.getX() >= borderOffset) && (e.getX() <= (nColumns *zoomOffset + borderOffset)) );
        boolean yIsInGrid = ( (e.getY() >= borderOffset) && (e.getY() <= (nRows *zoomOffset + borderOffset)) );
        return ( xIsInGrid && yIsInGrid);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO

        if ( isMouseInGrid(e) && !ViewToController.isGameStarted() ) {
            int iIndexCell = (e.getY() - borderOffset)/zoomOffset;
            int jIndexCell = (e.getX() - borderOffset)/zoomOffset;
            if (!isPresetRequest) {
                ViewToController.changeStateCell(iIndexCell, jIndexCell);
            }
            else {
                ViewToController.newPresetSimulation(nextPresetToLoad, iIndexCell, jIndexCell);
                isPresetRequest = false;
            }
        }
    }

    public void setNextPresetToLoad(String presetCode) {
        this.nextPresetToLoad = presetCode;
        isPresetRequest = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //NOT IMPLEMENTED
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //NOT IMPLEMENTED
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //NOT IMPLEMENTED
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //NOT IMPLEMENTED
    }

    public static SimulationGridJPanel getInstance() {
        if (simulationGridJPanelInstance == null)
            simulationGridJPanelInstance = new SimulationGridJPanel();
        return simulationGridJPanelInstance;
    }

}