package object;

import entity.Entity;
import java.util.Timer;
import java.util.TimerTask;
import main.GamePanel;

public class OBJ_Refinary extends Entity {

    GamePanel gp;
    public static final String objName = "ref";

    public OBJ_Refinary(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = objName;
        down1 = setup("/reso/objects/ref/refinary", 150, 90);
//        down2 = setup("/objects/gate2", gp.tileSize, 284);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "Please wait a moment, the crate is undergoing \n "
                + "refinement.";

        dialogues[1][0] = "Only defect ammunition crates can be refine";
        dialogues[2][0] = "The refining machine is off";
    }

    public void interact() {

        if (gp.player.lightsOn) {

            if (gp.player.holdingRbox == 1) {

                gp.player.holdingRbox = 0;
                startDialogue(this, 0);
                gp.player.take=true;

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        gp.obj[gp.currentMap][14].down1 = setup("/reso/objects/bg", gp.tileSize, gp.tileSize);
                        gp.playSE(24);  
                        
                    }
                }, 300); // Delay of 3 seconds (3000 milliseconds)

            } else if (gp.player.holdingGbox == 1) {
                
                startDialogue(this, 1);
            }

        } else {
               startDialogue(this, 2);
        }
    }

}
