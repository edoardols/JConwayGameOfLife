package model.modelclasses;

public class OrganismPreset {

    private static final int ALIVE_STATE = 1;
    private static final int DEAD_STATE = 0;

    private static int[][] blinker = null;
    private static final String BLINKER_CODE = "Blinker";

    private static int[][] glider = null;
    private static final String GLIDER_CODE = "Glider";

    //ORDER MUST BE RESPECTED
    private static final String[] presetName = {"Blinker", "Glider"} ;

    public OrganismPreset() {

    }

    public static String[] getPresetList() {
        return presetName;
    }

    public static int[][] getPreset(String presetCode) {
        switch (presetCode) {
            case BLINKER_CODE:
                blinker = new int[3][1];
                blinker[0][0] = ALIVE_STATE;
                blinker[1][0] = ALIVE_STATE;
                blinker[2][0] = ALIVE_STATE;
                Achievements.notifyBlinkerPlaced();
                return blinker;

            case GLIDER_CODE:
                glider = new int[3][3];
                glider[0][0] = DEAD_STATE;
                glider[0][1] = ALIVE_STATE;
                glider[0][2] = DEAD_STATE;
                glider[1][0] = DEAD_STATE;
                glider[1][1] = DEAD_STATE;
                glider[1][2] = ALIVE_STATE;
                glider[2][0] = ALIVE_STATE;
                glider[2][1] = ALIVE_STATE;
                glider[2][2] = ALIVE_STATE;
                Achievements.notifyGliderPlaced();
                return glider;

            default:
                return null;
        }
    }
}
