package model.modelclasses;

public class Achievements {

    private static int countGeneration;
    private static int countDeadGeneration;
    private static int countBornGeneration;
    private static int countBlinkerPlaced;
    private static int countGliderPlaced;
    private static int countDead;
    private static int countBorn;
    private static int countSameCellChangeState;
    private static int countChangeCellState;
    private static int countSurvivor;

    private static int iIndexCell;
    private static int jIndexCell;

    private static int bornAchievementProgression;
    private static int deadAchievementProgression;
    private static int survivorAchievementProgression;
    private static int generationAchievementProgression;
    private static int changeStateAchievementProgression;
    private static int blinkerAchievementProgression;
    private static int gliderAchievementProgression;

    private static boolean isNotifyGliderPlacedEnable;
    private static boolean isNotifyBlinkerPlacedEnable;
    private static boolean isNotifyBornEnable;
    private static boolean isNotifyDeadEnable;
    private static boolean isNotifyGenerationEnable;
    private static boolean isNotifyChangeStateCellEnable;
    private static boolean isNotifySurvivorEnable;

    public Achievements() {
    }

    public static void initializeAchievement() {
        iIndexCell = 0;
        jIndexCell = 0;
        initializeProgress();
        initializeNotify();
        initializeCount();

    }

    private static void initializeCount() {
        countGeneration = 0;
        countBlinkerPlaced = 0;
        countBornGeneration = 0;
        countDeadGeneration = 0;
        countGliderPlaced = 0;
        countBorn = 0;
        countDead = 0;
        countSameCellChangeState = 0;
        countChangeCellState = 0;
    }

    private static void initializeProgress() {
        bornAchievementProgression = 0;
        deadAchievementProgression = 0;
        survivorAchievementProgression = 0;
        generationAchievementProgression = 0;
        changeStateAchievementProgression = 0;
        blinkerAchievementProgression = 0;
        gliderAchievementProgression = 0;
    }

    private static void initializeNotify() {
        isNotifyGliderPlacedEnable = true;
        isNotifyBlinkerPlacedEnable = true;
        isNotifyBornEnable = true;
        isNotifyDeadEnable = true;
        isNotifyGenerationEnable = true;
        isNotifyChangeStateCellEnable = true;
        isNotifySurvivorEnable = true;
    }

    public static void notifyGliderPlaced() {
        if (isNotifyGliderPlacedEnable) {
            countGliderPlaced++;
            updateGliderAchievementState();
        }
    }

    public static void notifyBlinkerPlaced() {
        if (isNotifyBlinkerPlacedEnable) {
            countBlinkerPlaced++;
            updateBlinkerAchievementState();
        }
    }

    public static void notifyBorn(int bornGeneration) {
        if (isNotifyBornEnable) {
            countBornGeneration = bornGeneration;
            countBorn = countBorn + bornGeneration;
            updateBornAchievementState();
        }
    }

    public static void notifyDead(int deadGeneration) {
        if (isNotifyDeadEnable) {
            countDeadGeneration = deadGeneration;
            countDead = countDead + deadGeneration;
            updateDeadAchievementState();
        }
    }

    public static void notifySurvivor(int survivorGeneration) {
        if (isNotifySurvivorEnable) {
            countSurvivor = survivorGeneration;
            updateSurvivorAchievementState();
        }
    }

    public static void notifyGeneration() {
        if (isNotifyGenerationEnable) {
            countGeneration++;
            updateGenerationAchievementState();
        }
    }

    public static void notifyChangeStateCell(int iIndexCellPassed, int jIndexCellPassed) {
        if (isNotifyChangeStateCellEnable) {
            //TODO CLICCATO SULLA STESSA CELLA
            countChangeCellState++;
            if ( (iIndexCell == iIndexCellPassed) && (jIndexCell == jIndexCellPassed) ) {
                countSameCellChangeState++;
            }
            //LO CONSERVA PER LA PROSSIMA VOLTA SE SERVE RICONFRONTARE
            iIndexCell = iIndexCellPassed;
            jIndexCell = jIndexCellPassed;
            updateChangeStateAchievementState();
        }
    }

    private static void updateBornAchievementState() {
        if (countBornGeneration >= 0 && bornAchievementProgression == 0) {
            //prima cella nata
            bornAchievementProgression = 1;
        }
        if (countBornGeneration >= 100 && bornAchievementProgression == 1) {
            //nati 100 in una generazione
            bornAchievementProgression = 100;
        }
        if (countBornGeneration >= 1000 && bornAchievementProgression == 100) {
            //nati 1000 in una generazione
            bornAchievementProgression = 1000;
        }
        if (countBornGeneration >= 10000 && bornAchievementProgression == 1000) {
            //nati 10000 in una generazione
            bornAchievementProgression = 10000;
        }
        if (countBorn >= 100000 && bornAchievementProgression == 10000) {
            //nati 100000 in una simulazione
            //TODO ACHIEVEMENT FINITI
            isNotifyBornEnable = false;
        }
    }

    private static void updateDeadAchievementState() {
        if (countDeadGeneration > 0 && deadAchievementProgression == 0) {
            //prima cella morta
            deadAchievementProgression = 1;
        }
        if (countDeadGeneration >= 100 && deadAchievementProgression == 1) {
            //morti 100 in una generazione
            deadAchievementProgression = 100;
        }
        if (countDeadGeneration >= 1000 && deadAchievementProgression == 100) {
            //morti 1000 in una generazione
            deadAchievementProgression = 1000;
        }
        if (countDeadGeneration >= 10000 && deadAchievementProgression == 1000) {
            //morti 10000 in una generazione
            deadAchievementProgression = 10000;
        }
        if (countDead >= 100000 && deadAchievementProgression == 10000) {
            //morti 100000 in una generazione
            //TODO ACHIEVEMENT FINITI
            isNotifyDeadEnable = false;
        }
    }

    private static void updateSurvivorAchievementState() {
        if (countSurvivor > 0 && survivorAchievementProgression == 0) {
            //primo sopravvissuto
            survivorAchievementProgression = 1;
        }
        if (countSurvivor >= 100 && survivorAchievementProgression == 1) {
            //sopravvissuto 100 in una generazione
            survivorAchievementProgression = 100;
        }
        if (countSurvivor >= 1000 && survivorAchievementProgression == 100) {
            //sopravvissuto 1000 in una generazione
            survivorAchievementProgression = 1000;
        }
        if (countSurvivor >= 10000 && survivorAchievementProgression == 1000) {
            //sopravvissuto 10000 in una generazione
            //TODO ACHIEVEMENT FINITI
            isNotifySurvivorEnable = false;
        }
    }

    private static void updateGenerationAchievementState() {
        if (countGeneration > 0 && generationAchievementProgression == 0) {
            //prima generazione nata
            generationAchievementProgression = 1;
        }
        if (countGeneration >= 10 && generationAchievementProgression == 1) {
            //prima generazione nata
            generationAchievementProgression = 10;
        }
        if (countGeneration >= 100 && generationAchievementProgression == 10) {
            //prima generazione nata
            generationAchievementProgression = 100;
        }
        if (countGeneration >= 1000 && generationAchievementProgression == 100) {
            //prima generazione nata
            generationAchievementProgression = 1000;
        }
        if (countGeneration >= 10000 && generationAchievementProgression == 1000) {
            //prima generazione nata
            //TODO ACHIEVEMENT FINITI
            isNotifyGenerationEnable = false;
        }
    }

    private static void updateChangeStateAchievementState() {
        if (countChangeCellState > 0 && changeStateAchievementProgression == 0) {
            //power of life = cambia lo stato di una cella
            changeStateAchievementProgression = 1;
        }
        if (countChangeCellState >= 50 && changeStateAchievementProgression == 1) {
            //dio = cambia lo stato di 50 celle
            //TODO ACHIEVEMENT FINITI
            changeStateAchievementProgression = -1;
        }
        if (countSameCellChangeState >= 10) {
            //indeciso = cambia lo stato di una cella per 10 volte
            //TODO ACHIEVEMENT FINITI
        }
    }

    private static void updateBlinkerAchievementState() {
        if (countBlinkerPlaced > 0 && blinkerAchievementProgression == 0) {
            //dont blink or you miss = primo blinker piazzato
            blinkerAchievementProgression = 1;
        }
        if (countBlinkerPlaced >= 10 && blinkerAchievementProgression == 1) {
            //party time = piazza 10 blinker in una simulazione
            isNotifyBlinkerPlacedEnable = false;
        }
    }

    private static void updateGliderAchievementState() {
        if (countGliderPlaced > 0 && gliderAchievementProgression == 0) {
            //glider time = piazza primo glider
            gliderAchievementProgression = 1;
        }
        if (countGliderPlaced >= 10 && gliderAchievementProgression == 1) {
            //i glider you = piazza 10 glider in una simulazione
            isNotifyGliderPlacedEnable = false;
        }
    }

}
