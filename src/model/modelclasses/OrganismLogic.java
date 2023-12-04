package model.modelclasses;

public class OrganismLogic {

    private int numberOfRows; // matrix.length
    private int numberOfColumns; // matrix[].length

    private int[][] currentGenerationMatrix2D;
    private int[][] nextGenerationMatrix2D;

    private int DEAD_STATE = 0; //the cell is dead
    private int ALIVE_STATE = 1; //the cell is alive
    private boolean isGameStarted;

    public OrganismLogic(int nRowsFromInput, int nColumnsFromInput) {
        this.isGameStarted = false;
        this.numberOfRows = nRowsFromInput;
        this.numberOfColumns = nColumnsFromInput;

        //NB i=0 e i=numberOfRows-1 => border
        //NB j=0 e j=numberOfColumns-1 => border

        currentGenerationMatrix2D = new int[numberOfRows][numberOfColumns]; //with border
        nextGenerationMatrix2D = new int[numberOfRows][numberOfColumns]; //with border

        initializeNewMatrix(currentGenerationMatrix2D);
        initializeNewMatrix(nextGenerationMatrix2D);

        //TODO AD OGNI NUOVA CREAZIONE CHE UN RESET
        Achievements.initializeAchievement();
    }

    private void initializeNewMatrix(int[][] matrixPassed) {
        //adding border as dead cell
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                matrixPassed[i][j] = DEAD_STATE;
            }
        }
    }

    public void resetOrganism() {
        initializeNewMatrix(currentGenerationMatrix2D);
        initializeNewMatrix(nextGenerationMatrix2D);
        isGameStarted = false;
        Achievements.initializeAchievement();
    }

    public void changeStateCell(int iIndex, int jIndex) {
        //TODO +1 POICHè è GESTICO CON DUE CELLE IN PIù QUINDI 00 IN VIEW è 11 IN LOGIC
        if ( this.currentGenerationMatrix2D[iIndex+1][jIndex+1] == ALIVE_STATE ) {
            this.currentGenerationMatrix2D[iIndex+1][jIndex+1] = DEAD_STATE;
        }else {
            this.currentGenerationMatrix2D[iIndex+1][jIndex+1] = ALIVE_STATE;
        }
        Achievements.notifyChangeStateCell(iIndex,jIndex);
    }

    public void setPreset(int iIndex, int jIndex, int[][] presetMatrix) {
        int presetRows = presetMatrix.length;
        int presetColumns = presetMatrix[0].length;

        int offsetRows = (presetRows - 1)/2;
        int offsetColumns = (presetColumns - 1)/2;

        int iIndexPreset = 0;
        int jIndexPreset = 0;

        for (int i = -offsetRows; i <= offsetRows; i++) {
            jIndexPreset = 0;
            for (int j = -offsetColumns; j <= offsetColumns; j++) {
                //+1 TRASLATION BETWEEN VIEW AND MODEL
                currentGenerationMatrix2D[iIndex + i +1][jIndex + j +1] = presetMatrix[iIndexPreset][jIndexPreset];
                jIndexPreset++;
            }
            iIndexPreset++;
        }

    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void loadOnCurrentGenerationMatrix(int[][] organismToLoad) {
        //I BORDI NON SONO SOVRASCRITTI E QUINDI RIMANGONO A ZERO SI EVITANO PROBLEMI DI CONFIGURAZIONE
        for (int i = 1; i < numberOfRows-1; i++) {
            for (int j = 1; j < numberOfColumns-1; j++) {
                currentGenerationMatrix2D[i][j] = organismToLoad[i][j];
            }
        }
    }

    public int[][] getCurrentGenerationMatrix2D() {
        return currentGenerationMatrix2D;
    }

    public void generateNextGeneration() {
        //TODO PIU EFFICENTE DI FARE UNA CHIAMATA AD ONGNI ITERAZIONE
        int countBornForAchievement = 0;
        int countDeadForAchievement = 0;
        int countSurvivorForAchievement = 0;

        isGameStarted = true;
        int numberOfNeighbours = 0;
        for (int i = 1; i < numberOfRows-1; i++) {
            for (int j = 1; j < numberOfColumns-1; j++) {

                numberOfNeighbours = calculateNeighbours(i,j);

                //Rules of The Game Of Life, from https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

                //Rule 1: Any live cell with fewer than two live neighbours dies, as if by underpopulation.
                if ( (currentGenerationMatrix2D[i][j] == ALIVE_STATE) && (numberOfNeighbours < 2) ){
                    nextGenerationMatrix2D[i][j] = DEAD_STATE;
                    countDeadForAchievement++;
                }

                //Rule 2: Any live cell with two or three live neighbours lives on to the next generation.
                if ( (currentGenerationMatrix2D[i][j] == ALIVE_STATE) && ((numberOfNeighbours == 2) || (numberOfNeighbours == 3)) ) {
                    nextGenerationMatrix2D[i][j] = ALIVE_STATE;
                    countSurvivorForAchievement++;
                }

                //Rule 3:Any live cell with more than three live neighbours dies, as if by overpopulation.
                if ( (currentGenerationMatrix2D[i][j] == ALIVE_STATE) && (numberOfNeighbours > 3) ) {
                    nextGenerationMatrix2D[i][j] = DEAD_STATE;
                    countDeadForAchievement++;
                }

                //Rule 4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                if ( (currentGenerationMatrix2D[i][j] == DEAD_STATE) && (numberOfNeighbours == 3) ) {
                    nextGenerationMatrix2D[i][j] = ALIVE_STATE;
                    countBornForAchievement++;
                }
            }
        }
        exchangeOfMatrix();

        //TODO NOTIFICA DEGLI ACHIEVEMENT

        Achievements.notifyBorn(countBornForAchievement);
        Achievements.notifyDead(countDeadForAchievement);
        Achievements.notifySurvivor(countSurvivorForAchievement);
        Achievements.notifyGeneration();
    }

    private int calculateNeighbours(int iIndexCell, int jIndexCell) {
        int aliveNeighbours = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                //if the cell is ALIVE count as 1
                //if the cell is DEAD count as 0
                aliveNeighbours = aliveNeighbours + currentGenerationMatrix2D[iIndexCell + i][jIndexCell + j];
            }
        }
        if (currentGenerationMatrix2D[iIndexCell][jIndexCell] == ALIVE_STATE) {
            // -1 because in the aliveNeighbours there is the cell itself that is ALIVE
            aliveNeighbours = aliveNeighbours - 1;
        }
        return aliveNeighbours;
    }

    private void exchangeOfMatrix() {
        for (int i = 1; i < numberOfRows-1; i++) { // save time from 1 to numberOfRows-1
            for (int j = 1; j < numberOfColumns-1; j++) {
                currentGenerationMatrix2D[i][j] = nextGenerationMatrix2D[i][j];
            }
        }
    }
}
