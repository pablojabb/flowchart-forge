package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class OBJ_Trashbin extends Entity {

    GamePanel gp;
    public static final String objName = "trash";

    public OBJ_Trashbin(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = objName;
        down1 = setup("/reso/objects/trash/trash", 90, 100);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 10;
        solidArea.height = 50;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "Defect Ammunition crate disposed.";
        dialogues[1][0] = "Only defect ammunition crates can be disposed";
    }

    public void interact() {

        if (gp.player.holdingRbox == 1) {

            gp.player.holdingRbox = 0;
            gp.playSE(25);
            startDialogue(this, 0);

            gp.player.direction = "left";

            int goalCol = 24; //+ gp.player.solidArea.x -
            int goalRow = 24;//+ gp.player.solidArea.y

            gp.player.searchPath(goalCol, goalRow);
        }

        if (gp.player.holdingGbox == 1) {

            gp.player.holdingGbox = 0;
            startDialogue(this, 1);
            gp.player.direction = "left";

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    gp.keyH.enterPressed = true;

                }
            }, 500); // Delay of 3 seconds (3000 milliseconds)

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    gp.player.holdingGbox = 1;

                    int goalCol = 24; //+ gp.player.solidArea.x -
                    int goalRow = 24;//+ gp.player.solidArea.y

                    gp.player.searchPath(goalCol, goalRow);

                }
            }, 1000); // Delay of 3 seconds (3000 milliseconds)


        }

    }

}
