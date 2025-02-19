package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;

    public int boxesQ;
    public int redBox;
    public int greenBox;

    public int holdingRbox = 0;
    public int holdingGbox = 0;
    public int index = 0;
    public int level = 0;
    public boolean runOn = false;
    public boolean nextLvl = false;
    public boolean homerun = true;
    public boolean take = false;
    public boolean upWalk = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp); // calling constructor of super class(from entity class)
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 38;
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        // when u create Player object, initialize with default values
        getHoldGImage();
        getHoldRImage();

        setDefaultValues();
    }

    public void setDefaultValues() {
        //Default Starting Positions
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 24;
        gp.currentMap = 0;

        //Blue Gem Start Position, mapNum = 3;
//         worldX = gp.tileSize *25;
//        worldY = gp.tileSize * 9;
//        gp.currentMap = 3;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        //PLAYER STATUS
        maxLife = 10;
        life = maxLife;
        maxMana = 8;
        mana = maxMana;
        ammo = 10;
        strength = 1;           // The more strenght he has, the more damage he gives.
        dexterity = 1;          // The more dexterity he has, the less damage he receives.
        exp = 0;
        nextLevelExp = 4;
        coin = 40;
        invincible = false;

        currentLight = null;

        getImage();

        setItems();
        //setDialogue();
    }

    public void setDefaultPositions() {

    }

    public void startLevel(int lvl) {

        holdingGbox = 0;
        holdingRbox = 0;
        redBox = 0;
        greenBox = 0;
        commands = new ArrayList<>(Arrays.asList());
        solution = new ArrayList<>(Arrays.asList());
        index = 0;

        switch (lvl) {

            case 0 -> {
                setDefaultValues();
                worldX = gp.tileSize * 10;
                worldY = gp.tileSize * 26;
                gp.currentMap = 0;

                commands = new ArrayList<>(Arrays.asList(23, 24));
                solution = new ArrayList<>(Arrays.asList());
                runOn = true;
                setDialogue();
                if (runOn == true) {

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            direction = "left";

                            startDialogue(gp.player, lvl);
                            runOn = false;

                        }
                    }, 6000); // Delay of 3 seconds (3000 milliseconds)

                }

                break;
            }

            case 1 -> {
                //

                setDefaultValues();
                worldX = gp.tileSize * 25;
                worldY = gp.tileSize * 24;
                gp.currentMap = 0;

                solution = new ArrayList<>(Arrays.asList(1, 2, 3, 22, 1, 0, 1, 2, 3, 22, 1, 0, 0));
                runOn = true;
                setDialogue();

                if (runOn == true) {

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            startDialogue(gp.player, lvl);
                            runOn = false;

                        }
                    }, 300); // Delay of 3 seconds (3000 milliseconds)

                }
                break;
            }

            case 2 -> {

                setDefaultValues();
                worldX = gp.tileSize * 25;
                worldY = gp.tileSize * 24;
                gp.currentMap = 0;

                solution = new ArrayList<>(Arrays.asList(15, 16, 15, 0));
                runOn = true;
                setDialogue();

                if (runOn == true) {

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            startDialogue(gp.player, lvl);
                            runOn = false;

                        }
                    }, 300); // Delay of 3 seconds (3000 milliseconds)

                }
                break;
            }

            case 3 -> {

                setDefaultValues();
                worldX = gp.tileSize * 25;
                worldY = gp.tileSize * 24;
                gp.currentMap = 0;

                solution = new ArrayList<>(Arrays.asList(1, 2, 3, 22, 1, 0, 15, 16, 15, 0, 5, 11, 12, 14, 12, 11, 5, 0, 1, 2, 3, 22, 1, 0, 0));
                runOn = true;
                setDialogue();

                if (runOn == true) {

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            startDialogue(gp.player, lvl);
                            runOn = false;

                        }
                    }, 300); // Delay of 3 seconds (3000 milliseconds)

                }
                break;
            }

            case 4 -> {

                setDefaultValues();
                worldX = gp.tileSize * 25;
                worldY = gp.tileSize * 24;
                gp.currentMap = 0;

                solution = new ArrayList<>(Arrays.asList(1, 2, 3, 22, 1, 0, 25, 15, 16, 15, 0, 5, 11, 12, 14, 12, 11, 5, 0, 26, 1, 2, 3, 22, 1, 0, 0));
                runOn = true;
                setDialogue();

                if (runOn == true) {

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            startDialogue(gp.player, lvl);
                            runOn = false;

                        }
                    }, 300); // Delay of 3 seconds (3000 milliseconds)

                }
                break;
            }

            case 5 -> {
                setDefaultValues();
                worldX = gp.tileSize * 25;
                worldY = gp.tileSize * 24;
                gp.currentMap = 0;

                solution = new ArrayList<>(Arrays.asList(1, 2, 3, 22, 1, 0, 15, 16, 15, 0, 1, 4, 1, 0, 1, 2, 3, 22, 1, 0, 0));
                runOn = true;
                setDialogue();

                if (runOn == true) {

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            startDialogue(gp.player, lvl);
                            runOn = false;

                        }
                    }, 300); // Delay of 3 seconds (3000 milliseconds)

                }
                break;
            }

            case 6 -> {
                setDefaultValues();
                worldX = gp.tileSize * 25;
                worldY = gp.tileSize * 24;
                gp.currentMap = 0;

                solution = new ArrayList<>(Arrays.asList(1, 2, 3, 22, 1, 0, 15, 16, 15, 0, 5, 6, 7, 8, 9, 10, 0, 5, 11, 12, 14, 12, 11, 5, 0, 1, 2, 3, 22, 1, 0, 0));
                runOn = true;
                setDialogue();

                if (runOn == true) {

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            startDialogue(gp.player, lvl);
                            runOn = false;

                        }
                    }, 300); // Delay of 3 seconds (3000 milliseconds)

                }
                break;
            }

            case 7 -> {
                setDefaultValues();
                worldX = gp.tileSize * 25;

                worldY = gp.tileSize * 24;
                gp.currentMap = 0;

                solution = new ArrayList<>(Arrays.asList(1, 2, 3, 22, 1, 0, 15, 16, 15, 0, 5, 11, 12, 14, 12, 11, 5, 0, 15, 16, 15, 0, 1, 4, 1, 0, 1, 2, 3, 22, 1, 0, 0));
                runOn = true;
                setDialogue();

                if (runOn == true) {

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            startDialogue(gp.player, lvl);
                            runOn = false;

                        }
                    }, 300); // Delay of 3 seconds (3000 milliseconds)

                }

            }

            case 8 -> {
                setDefaultValues();
                worldX = gp.tileSize * 25;
                worldY = gp.tileSize * 24;
                gp.currentMap = 0;

                solution = new ArrayList<>(Arrays.asList(1, 2, 3, 22, 1, 0, 25, 15, 16, 15, 0, 5, 6, 7, 8, 9, 10, 0, 5, 11, 12, 14, 12, 11, 5, 0, 26, 1, 2, 3, 22, 1, 0, 0));

                runOn = true;
                setDialogue();

                if (runOn == true) {

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            startDialogue(gp.player, lvl);
                            runOn = false;

                        }
                    }, 300); // Delay of 3 seconds (3000 milliseconds)

                }
                break;

            }

        }

    }

    public void setDialogue() {
        dialogues[0][0] = "INTRODUCTION: Welcome to the Armory.";
        dialogues[0][1] = "Robot Assistant: Hello, Private Ryan.\nWelcome to Earth's International Armory (EIA).";
        dialogues[0][2] = "Robot Assistant: I am V2, your new robot assistant.\nStarting today, I'll be helping you manage the armory.";

        hintDialogues[0][0] = "LVL 0: INTRODUCTION\n\n"
                + "V2: You are the new soldier\nwho will manage\nArmory A6.\nAlways listen to V2.";

        learnDialogues[0][0] = "LVL 0: INTRODUCTION\n\n"
                + "You are about to start a new journey\ndelving into flowcharts.\n";

        dialogues[1][0] = "LVL 1: THE START THEN THE END";
        dialogues[1][1] = "V2: You have unlocked a new command.";
        dialogues[1][2] = "V2: The START symbol turns on the electricity,\nwhile the END symbol turns off the electricity.";

        hintDialogues[1][0] = "LVL 1: THE START THEN THE END\n\n"
                + "V2: Turn on the electricity \nusing the START symbol and \nturn it off using the END \nsymbol.";

        learnDialogues[1][0] = "LVL 1: THE START THEN THE END\n\n"
                + "The START and END symbols are important symbols\nin all flowcharts because they signify the beginning\nand the end of a flowchart.";

        dialogues[2][0] = "LVL 2: THE HEAVY INPUT";
        dialogues[2][1] = "V2: You have unlocked a new command.";
        dialogues[2][2] = "V2: The INPUT symbol picks up the ammunition box\n available in the room.";

        hintDialogues[2][0] = "LVL 2: THE HEAVY INPUT\n\n"
                + "V2: Use the INPUT symbol to\npick up an ammunition box\nin the room.";

        learnDialogues[2][0] = "LVL 2: THE HEAVY INPUT\n\n"
                + "The INPUT symbol is important in flowcharts \nbecause it shows where data is being inputted.";

        dialogues[3][0] = "LVL 3: PASS THE GATEWAY OF OUTPUT";
        dialogues[3][1] = "V2: You have unlocked a new command.";
        dialogues[3][2] = "V2: The OUTPUT symbol puts the good ammunition boxes\n you're carrying into the delivery van.";

        hintDialogues[3][0] = "LVL 3: PASS THE GATEWAY OF\n OUTPUT\n"
                + "V2: Use the INPUT first, then\n the OUTPUT symbol to place\n the boxes in the delivery van.\nTurn on the electricity to\n open the backdoor.";

        learnDialogues[3][0] = "LVL 3: PASS THE GATEWAY OF OUTPUT\n\n"
                + "The OUTPUT symbol is important in flowcharts\nbecause it shows where data is being outputted.";

        dialogues[4][0] = "LVL 4: DECISIONS BACK TO BACK";
        dialogues[4][1] = "V2: You have unlocked a new command.";
        dialogues[4][2] = "V2: The DECISION symbol is used to perform a repeated\n task without repeating the symbols.";

        hintDialogues[4][0] = "LVL 4: DECISIONS BACK TO BACK\n\n"
                + "V2: Use the DECISION symbol\n to repeat the INPUT and\n OUTPUT process with fewer\n symbols.";

        learnDialogues[4][0] = "LVL 4: DECISIONS BACK TO BACK\n\n"
                + "The DECISION symbol is crucial in flowcharts\nbecause it allows the representation of branching paths\nbased on different conditions or choices.";

        dialogues[5][0] = "LVL 5: PROCESS IT";
        dialogues[5][1] = "V2: You have unlocked a new command.";
        dialogues[5][2] = "V2: The RECYCLE PROCESS symbol discards bad quality\n ammunition boxes that you're carrying into the\n recycle bin.";

        hintDialogues[5][0] = "LVL 5: PROCESS IT\n\n"
                + "V2: Use the RECYCLE PROCESS\n symbol to remove the red\n boxes in the room.";

        learnDialogues[5][0] = "LVL 5: PROCESS IT\n\n"
                + "The PROCESS symbol is essential in flowcharts\nbecause it denotes the actions or steps\nthat must be carried out.";

        dialogues[6][0] = "LVL 6: PROCESS IT II";
        dialogues[6][1] = "V2: You have unlocked a new command.";
        dialogues[6][2] = "V2: The REFINE PROCESS symbol converts the bad quality\n ammunition boxes into good quality ammunition boxes.";

        hintDialogues[6][0] = "LVL 6: PROCESS IT II\n"
                + "V2: Use the REFINE PROCESS\n symbol to convert the red\n boxes in the room. Turn\n on the electricity to open\n the backdoor.";

        learnDialogues[6][0] = "LVL 6: PROCESS IT II\n\n"
                + "The PROCESS symbol is essential in flowcharts\nbecause it denotes the actions or steps\nthat must be carried out.";

        dialogues[7][0] = "LVL 7: YOU'VE GROWN";
        dialogues[7][1] = "V2: You have to use the SYMBOLs you've learned\nto solve the tasks.";

        hintDialogues[7][0] = "LVL 7: YOU'VE GROWN\n\n"
                + "V2: -trats-tupni-tuptuo-tupni-\n-elcycer-dne";

        learnDialogues[7][0] = "LVL 7: YOU'VE GROWN\n\n"
                + "You've done an excellent job solving the\n challenge. Keep up the great work!";

        dialogues[8][0] = "LVL 8: YOU'RE READY";
        dialogues[8][1] = "V2: You have to use the SYMBOLs you've learned\nto solve the tasks.";

        hintDialogues[8][0] = "LVL 8: YOU'RE READY\n\n"
                + "V2: -trats-decisionto-tupni-\n-enifer-tuptuo-decisionfrom-\n-dne";

        learnDialogues[8][0] = "LVL 8: YOU'RE READY\n\n"
                + "Congratulations on finishing the game and mastering\n flowcharts! You're ready to take on any challenge.";
    }

    public void restoreStatus() {
        life = maxLife;
        mana = maxMana;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
    }

    public void setItems() {
        inventory.clear(); //cuz if game restarts inventory must be cleared first
        inventory.add(currentWeapon);
        inventory.add(currentShield);

    }

    public void getImage() {
        up1 = setup("/res/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/boy_up_2", gp.tileSize, gp.tileSize);
        up3 = setup("/res/player/boy_up_3", gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/boy_down_2", gp.tileSize, gp.tileSize);
        down3 = setup("/res/player/boy_down_3", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/boy_left_2", gp.tileSize, gp.tileSize);
        left3 = setup("/res/player/boy_left_3", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/boy_right_2", gp.tileSize, gp.tileSize);
        right3 = setup("/res/player/boy_right_3", gp.tileSize, gp.tileSize);
    }

    public void getHoldGImage() {

        holdgdown1 = setup("/reso/player/g/hgd1", gp.tileSize, gp.tileSize);
        holdgdown2 = setup("/reso/player/g/hgd2", gp.tileSize, gp.tileSize);
        holdgdown3 = setup("/reso/player/g/hgd3", gp.tileSize, gp.tileSize);
        holdgup1 = setup("/reso/player/g/hgu1", gp.tileSize, gp.tileSize);
        holdgup2 = setup("/reso/player/g/hgu2", gp.tileSize, gp.tileSize);
        holdgup3 = setup("/reso/player/g/hgu3", gp.tileSize, gp.tileSize);
        holdgleft1 = setup("/reso/player/g/hgl1", gp.tileSize, gp.tileSize);
        holdgleft2 = setup("/reso/player/g/hgl2", gp.tileSize, gp.tileSize);
        holdgleft3 = setup("/reso/player/g/hgl3", gp.tileSize, gp.tileSize);
        holdgright1 = setup("/reso/player/g/hgr1", gp.tileSize, gp.tileSize);
        holdgright2 = setup("/reso/player/g/hgr2", gp.tileSize, gp.tileSize);
        holdgright3 = setup("/reso/player/g/hgr3", gp.tileSize, gp.tileSize);

    }

    public void getHoldRImage() {

        holdrdown1 = setup("/reso/player/r/hrd1", gp.tileSize, gp.tileSize);
        holdrdown2 = setup("/reso/player/r/hrd2", gp.tileSize, gp.tileSize);
        holdrdown3 = setup("/reso/player/r/hrd3", gp.tileSize, gp.tileSize);
        holdrup1 = setup("/reso/player/r/hru1", gp.tileSize, gp.tileSize);
        holdrup2 = setup("/reso/player/r/hru2", gp.tileSize, gp.tileSize);
        holdrup3 = setup("/reso/player/r/hru3", gp.tileSize, gp.tileSize);
        holdrleft1 = setup("/reso/player/r/hrl1", gp.tileSize, gp.tileSize);
        holdrleft2 = setup("/reso/player/r/hrl2", gp.tileSize, gp.tileSize);
        holdrleft3 = setup("/reso/player/r/hrl3", gp.tileSize, gp.tileSize);
        holdrright1 = setup("/reso/player/r/hrr1", gp.tileSize, gp.tileSize);
        holdrright2 = setup("/reso/player/r/hrr2", gp.tileSize, gp.tileSize);
        holdrright3 = setup("/reso/player/r/hrr3", gp.tileSize, gp.tileSize);
    }

    public void setAction(int num) {
        standby = false;
//
        int jumpto = 0;
        int jumpfrom = 0;

        int goalCol = 0;
        int goalRow = 0;
        int checkCol = worldX / gp.tileSize;
        int checkRow = worldY / gp.tileSize;

//
        playing = true;
//
        if (playing) {

            switch (num) {
                case 0:
                    //A
                    goalCol = 25; //+ gp.player.solidArea.x -
                    goalRow = 24; //+ gp.player.solidArea.y
                    break;

                case 1:
                    //B
                    goalCol = 25;
                    goalRow = 21;

                    break;

                case 2:
                    //C
                    goalCol = 22;
                    goalRow = 21;
                    break;

                case 3:
                    //H
                    goalCol = 21;
                    goalRow = 20;
                    break;

                case 4:
                    //D
                    goalCol = 31;
                    goalRow = 20;
                    break;

                case 5:
                    //E
                    goalCol = 25;
                    goalRow = 25;
                    break;

                case 11:
                    //F
                    goalCol = 31;
                    goalRow = 25;
                    break;

                case 6:
                    //G
                    goalCol = 21;
                    goalRow = 25;
                    break;

                case 7:
                    //I
                    goalCol = 21;
                    goalRow = 26;
                    break;

                case 8:
                    //J
                    goalCol = 23;
                    goalRow = 26;
                    break;

                case 9:
                    //K
                    goalCol = 23;
                    goalRow = 27;
                    break;

                case 10:
                    //L
                    goalCol = 25;
                    goalRow = 27;
                    break;

                case 12:
                    //M
                    goalCol = 31;
                    goalRow = 26;
                    break;

                case 13:
                    //N
                    goalCol = 32;
                    goalRow = 26;
                    break;

                case 14:
                    //O
                    goalCol = 35;
                    goalRow = 26;
                    break;

                case 15:
                    //P
                    goalCol = 25;
                    goalRow = 20;

                    break;

                case 16:
                    //Q
                    if (homerun) {

                        goalCol = getCol(boxesQ);
                        goalRow = getRow(boxesQ);
                        break;
                    }

                case 17:
                    //R
                    goalCol = 18;
                    goalRow = 23;
                    break;

                case 18:
                    //S
                    goalCol = 18;
                    goalRow = 24;
                    break;

                case 19:
                    //T
                    goalCol = 16;
                    goalRow = 22;
                    break;

                case 20:
                    //U
                    goalCol = 16;
                    goalRow = 23;
                    break;

                case 21:
                    //V
                    goalCol = 16;
                    goalRow = 24;
                    break;

                case 22:
                    //Z
                    goalCol = 25;
                    goalRow = 20;
                    break;

                case 23:
                    //AS
                    goalCol = 25;
                    goalRow = 26;
                    break;

                case 24:
                    //AB
                    goalCol = 25;
                    goalRow = 25;
                    break;

                case 25:
                    //Loop 1
                    jumpto = index++;

                    break;

                case 26:
                    //Loop 1
                    jumpfrom = index;

                    if (jumpfrom > jumpto && boxesQ != 0) {
                        index = 0;
                        removeElementsBeforeN(commands, 25);
                    } else if (boxesQ <= 0) {
                        index = jumpfrom + 1;
                    }

                    break;

                // Cases 3 through 10 follow similar patterns...
                default:
                    // Handle default case if necessary
                    break;
            }

            searchPath(goalCol, goalRow);

            if (checkCol == goalCol && checkRow == goalRow) {

                index++;

                if (checkCol == 21 && checkRow == 20) {

                    gp.obj[gp.currentMap][12].interact();

                } else if (checkCol == 31 && checkRow == 20) {
                    gp.obj[gp.currentMap][6].interact();

                } else if (checkCol == 21 && checkRow == 26) {

                    gp.obj[gp.currentMap][11].interact();
                } else if (checkCol == 23 && checkRow == 28) {
                    gp.obj[gp.currentMap][14].interact();
                } else if (checkCol == 35 && checkRow == 26) {
                    gp.obj[gp.currentMap][5].interact();

                } else if (checkCol == 25 && checkRow == 20 || checkCol == 25 && checkRow == 22) {

                    homerun = true;
                } else if (checkCol == 23 && checkRow == 27) {
                    gp.obj[gp.currentMap][14].interact();

                }

            }
//            

        }

    }

    public static void removeElementsBeforeN(ArrayList<Integer> list, int n) {
        // Find the index of the element n
        int index = list.indexOf(n);

        // If n is found in the list and it's not the first element
        if (index > 0) {
            // Remove all elements before the index of n
            list.subList(0, index).clear();
        }
    }

    public int getCol(int boxes) {
        int n = 0;
        switch (boxes) {

            case 2:
                n = 16;
                break;
            case 1:
                n = 15;
                break;
            default:
                // Handle cases where boxesQ is not 1-6 if necessary
                break;

        }

        return n;
    }

    public int getRow(int boxes) {
        int n = 0;

        switch (boxes) {

            case 2:
                n = 20;
                break;
            case 1:
                n = 20;
                break;
            default:
                // Handle cases where boxesQ is not 1-6 if necessary
                break;
        }

        return n;
    }

    public void reRun(ArrayList<Integer> indices) {
        if (indices != null && !indices.isEmpty()) {

//            solution.clear();
            greenBox = 0;
            redBox = 0;
            gp.stopMusic();
            gp.playMusic(0);
            gp.removeTempEntity();
            gp.aSetter.setNPC();
            gp.aSetter.setObject();
            worldX = gp.tileSize * 25;
            worldY = gp.tileSize * 24;
            commands = indices;
            index = 0;
            holdingRbox = 0;
            holdingGbox = 0;

            gp.gameState = gp.playState;

        }
    }

    @Override
    public void update() { // Runs 60 times every seconds.

        if (commands.size() > index && commands != null) {
            setAction(commands.get(index));
//            System.out.println(index+"  ind"+ solution.size()+"  size"  +"nextlvl?"+nextLvl);
//            System.out.println(solution.toString());
//            System.out.println(commands.toString());
        } else if (index >= solution.size() && nextLvl == true) {

            gp.gameState = gp.gameOverState;

        } else if (commands.contains(26) && nextLvl == true) {

            gp.gameState = gp.gameOverState;

        } else if (level == 0 && gp.keyH.pressedEnter) {

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    gp.gameState = gp.gameOverState;
                    gp.keyH.pressedEnter = false;

                }
            }, 700); // Delay of 3 seconds (3000 milliseconds)

        } else {
            standby = true;
            image = down1;

        }
        if (!standby) {

//        if (keyH.upPressed == true || keyH.downPressed == true
//                || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
//            if (keyH.upPressed == true) {
//                direction = "up";
//            } else if (keyH.downPressed == true) {                                                                 // You can go up and down while you pressing left or right.
//                direction = "down";                                           // But if you going up or down you cannot press left or right
//            } // The reason is here the if statements order.
//            else if (keyH.leftPressed == true) // For example when "keyH.upPressed == true", the else if blocks are not working. And you cannot go anyway when you press up.
//            {
//                direction = "left";
//            } else if (keyH.rightPressed == true) {
//                direction = "right";
//            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);   // npc array. checks any of npc collision
            interactNPC(npcIndex);

            //CHECK INTERACTIVE COLLISION
           

            //CHECK EVENT
            gp.eHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) //Without this, player moves when you press ENTER
            {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;
                }
            } else {
                switch (direction) {
                    case "up":
                        direction = "down";
                        break;

                    case "down":
                        direction = "up";
                        break;

                    case "left":
                        direction = "right";
                        break;

                    case "right":
                        direction = "left";
                        break;
                }
            }

            if (keyH.enterPressed == true && attackCanceled == false) {
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;
            guarding = false;
            guardCounter = 0;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) //spriteNum changes every 12 frames
                {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;                  // spriteCounter reset
            }
        } else // This is for: If you release the key when you walking, change sprite num to 1 and use player's not-walking sprite.
        {
            standCounter++;
            if (standCounter == 20) // After you release the key player stands 20 frames last position then spriteNum will be 1(default)
            {
                spriteNum = 1;
                standCounter = 0;                        // standCounter reset
            }
            guarding = false;
            guardCounter = 0;

        }

        if (keyH.godModeOn == false) {
            if (life <= 0) {
                gp.gameState = gp.gameOverState;
                gp.ui.commandNum = - 1; //for if you die while pressing enter
                gp.stopMusic();
                gp.playSE(12);
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            // PICKUP ONLY ITEMS

//            System.out.println(gp.obj[gp.currentMap][i].name + "  " + i);
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
                gp.obj[gp.currentMap][i].use(this);

//
                if (gp.obj[gp.currentMap][i].isGreen == true && gp.player.holdingRbox == 0) {
//                  

                    holdingGbox = 1;

                    gp.obj[gp.currentMap][i] = null;

                } else if (gp.obj[gp.currentMap][i].isGreen == false && gp.player.holdingGbox == 0) {
//                  

                    holdingRbox = 1;

                    gp.obj[gp.currentMap][i] = null;

                }

            } //OBSTACLE
            else if (gp.obj[gp.currentMap][i].type == type_obstacle) {

                attackCanceled = true;
                gp.obj[gp.currentMap][i].interact();

            } // INVENTORY ITEMS

        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.enterPressed == true) {
                attackCanceled = true;
                gp.npc[gp.currentMap][i].speak();
            }

            gp.npc[gp.currentMap][i].move(direction);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":

                if (holdingGbox == 0 || holdingRbox == 0) //Attacking sprites
                {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up3;
                    }
                }
                if (holdingGbox == 1) //Attacking sprites
                {

                    if (spriteNum == 1) {
                        image = holdgup1;
                    }
                    if (spriteNum == 2) {
                        image = holdgup2;
                    }
                    if (spriteNum == 3) {
                        image = holdgup3;
                    }
                } else if (holdingRbox == 1) {

                    if (spriteNum == 1) {
                        image = holdrup1;
                    }
                    if (spriteNum == 2) {
                        image = holdrup2;
                    }
                    if (spriteNum == 3) {
                        image = holdrup3;
                    }

                }

                break;

            case "down":
                if (holdingGbox == 0 || holdingRbox == 0) //Attacking sprites
                {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 3) {
                        image = down3;
                    }
                }
                if (holdingGbox == 1) //Attacking sprites
                {
                    if (spriteNum == 1) {
                        image = holdgdown1;
                    }
                    if (spriteNum == 2) {
                        image = holdgdown2;
                    }
                    if (spriteNum == 3) {
                        image = holdgdown3;
                    }
                } else if (holdingRbox == 1) {

                    if (spriteNum == 1) {
                        image = holdrdown1;
                    }
                    if (spriteNum == 2) {
                        image = holdrdown2;
                    }
                    if (spriteNum == 3) {
                        image = holdrdown3;
                    }

                }

                break;

            case "left":
                if (holdingGbox == 0 || holdingRbox == 0) //Attacking sprites
                {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                }
                if (holdingGbox == 1) //Attacking sprites
                {
                    if (spriteNum == 1) {
                        image = holdgleft1;
                    }
                    if (spriteNum == 2) {
                        image = holdgleft2;
                    }
                    if (spriteNum == 3) {
                        image = holdgleft3;
                    }
                } else if (holdingRbox == 1) {
                    if (spriteNum == 1) {
                        image = holdrleft1;
                    }
                    if (spriteNum == 2) {
                        image = holdrleft2;
                    }
                    if (spriteNum == 3) {
                        image = holdrleft3;
                    }

                }

                break;

            case "right":
                if (holdingGbox == 0 || holdingRbox == 0) //Attacking sprites
                {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                }
                if (holdingGbox == 1) //Attacking sprites
                {
                    if (spriteNum == 1) {
                        image = holdgright1;
                    }
                    if (spriteNum == 2) {
                        image = holdgright2;
                    }
                    if (spriteNum == 3) {
                        image = holdgright3;
                    }
                } else if (holdingRbox == 1) {

                    if (spriteNum == 1) {
                        image = holdrright1;
                    }
                    if (spriteNum == 2) {
                        image = holdrright2;
                    }
                    if (spriteNum == 3) {
                        image = holdrright3;
                    }

                }
                break;
        }

        if (drawing == true) //for boss cutscene making player invisible to move camera.(Cuz camera movement based on player). Only draw the PlayerDummy
        {
            g2.drawImage(image, tempScreenX, tempScreenY, null);
        }

        //Reset graphics opacity / alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

//        //DEBUG
//        /*g2.setFont(new Font("Arial",Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:" + invincibleCounter, 10,400);
//
//        g2.setColor(Color.RED);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height); //PLAYER COLLISION CHECKER.RED RECTANGLE.
//
//        tempScreenX = screenX + solidArea.x;
//        tempScreenY = screenY + solidArea.y;
//        switch(direction) {
//            case "up": tempScreenY = screenY - attackArea.height; break;
//            case "down": tempScreenY = screenY + gp.tileSize; break;
//            case "left": tempScreenX = screenX - attackArea.width; break;
//            case "right": tempScreenX = screenX + gp.tileSize; break;
//        }
//        g2.setColor(Color.red);
//        g2.setStroke(new BasicStroke(1));
//        g2.drawRect(tempScreenX, tempScreenY, attackArea.width, attackArea.height);*/
    }
}
