package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_White extends Entity {

      public NPC_White(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        dialogueSet = -1; //For first dialogueSet(= 0)

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/reso/npc/wh/wu1", gp.tileSize, gp.tileSize);
        up2 = setup("/reso/npc/wh/wu2", gp.tileSize, gp.tileSize);
        up3 = setup("/reso/npc/wh/wu3", gp.tileSize, gp.tileSize);
        down1 = setup("/reso/npc/wh/wd1", gp.tileSize, gp.tileSize);
        down2 = setup("/reso/npc/wh/wd2", gp.tileSize, gp.tileSize);
        down3 = setup("/reso/npc/wh/wd3", gp.tileSize, gp.tileSize);
        left1 = setup("/reso/npc/wh/wl1", gp.tileSize, gp.tileSize);
        left2 = setup("/reso/npc/wh/wl2", gp.tileSize, gp.tileSize);
        left3 = setup("/reso/npc/wh/wl3", gp.tileSize, gp.tileSize);
        right1 = setup("/reso/npc/wh/wr1", gp.tileSize, gp.tileSize);
        right2 = setup("/reso/npc/wh/wr2", gp.tileSize, gp.tileSize);
        right3 = setup("/reso/npc/wh/wr3", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0][0] = "hello motherfucker ";
        dialogues[0][1] = "So you've come to this island to find the treasure?";
        dialogues[0][2] = "I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.";
        dialogues[0][4] = "Well, good luck on you.";
        dialogues[0][3] = "You can talk with me again when you're stuck.";

        dialogues[1][0] = "If you become tired, rest at the water.";
        dialogues[1][1] = "However, the monsters reappear if you rest.\nI don't know why but that's how it works.";
        dialogues[1][2] = "In any case, don't push yourself too hard.";

        dialogues[2][0] = "I wonder how to open that door...";
    }

    public void setAction() {
        if (onPath == true) {
//            int goalCol = 12;
//            int goalRow = 9;

//            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
//            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
//            searchPath(goalCol, goalRow);

        } else {
            actionLockCounter++;

            if (actionLockCounter == 50) {
                Random random = new Random();
                int i = random.nextInt(30) + 1;  // pick up  a number from 1 to 100
                if (i <= 8) {
                    direction = "right";
                }
                if (i > 8 && i <= 15) {
                    direction = "left";
                }
                if (i > 15 && i <= 23) {
                    direction = "up";
                }
                if (i > 23&& i <= 30) {
                    direction = "down";
                }
                actionLockCounter = 0; // reset
            }
        }
    }

    public void speak() {
        //ENTITY CLASS SPEAK()
        //Do this character specific stuff

        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            //dialogueSet = 0;
            dialogueSet--; //displays last set
        }

        /*if(gp.player.life < gp.player.maxLife/3)
        {
            dialogueSet = 1;
        }*/
        //follow me, come with me  or something like that
        //onPath = true;
    }
}
