package entity;

import main.GamePanel;

import java.awt.*;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;

        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;

        dialogueSet = -1; //For first dialogueSet(= 0)

        getImage();
        setDialogue();
//        setAction();
    }

    public void getImage() {

        up1 = setup("/reso/npc/robo/ru1", gp.tileSize, gp.tileSize);
        up2 = setup("/reso/npc/robo/ru2", gp.tileSize, gp.tileSize);
        up3 = setup("/reso/npc/robo/ru1", gp.tileSize, gp.tileSize);
        down1 = setup("/reso/npc/robo/rd1", gp.tileSize, gp.tileSize);
        down2 = setup("/reso/npc/robo/rd2", gp.tileSize, gp.tileSize);
        down3 = setup("/reso/npc/robo/rd1", gp.tileSize, gp.tileSize);
        left1 = setup("/reso/npc/robo/rl1", gp.tileSize, gp.tileSize);
        left2 = setup("/reso/npc/robo/rl2", gp.tileSize, gp.tileSize);
        left3 = setup("/reso/npc/robo/rl1", gp.tileSize, gp.tileSize);
        right1 = setup("/reso/npc/robo/rr1", gp.tileSize, gp.tileSize);
        right2 = setup("/reso/npc/robo/rr2", gp.tileSize, gp.tileSize);
        right3 = setup("/reso/npc/robo/rr1", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0][0] = "hello motherfucker zzzzzzzzzzzz ";
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
//   
        int goalCol = (gp.player.worldX - gp.tileSize) / gp.tileSize; //+ gp.player.solidArea.x -
        int goalRow = (gp.player.worldY  - gp.tileSize) / gp.tileSize;//+ gp.player.solidArea.y
        searchPath(goalCol, goalRow);

//        actionLockCounter++;
//
//        if (actionLockCounter == 50) {
//            switch (direction) {
//                case "up":
//                    direction = "right";
//                    break;
//                case "right":
//                    direction = "down";
//                    break;
//                case "down":
//                    direction = "left";
//                    break;
//                case "left":
//                    direction = "up";
//                    break;
//            }
//            actionLockCounter = 0; // reset
//        }

//      
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
        onPath = true;
    }
}
