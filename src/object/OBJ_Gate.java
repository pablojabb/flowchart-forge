package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class OBJ_Gate extends Entity {

    GamePanel gp;
    public static final String objName = "Door";

    public OBJ_Gate(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = objName;
        down1 = setup("/reso/objects/gate", gp.tileSize, 284);
        down2 = setup("/reso/objects/gate2", gp.tileSize, 284);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 284;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        price = 35;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "Welcome to the Earth's International Armory.";
    }

    public void interact() {

        gp.playSE(13);
        down1 = setup("/reso/objects/gate2", gp.tileSize, 284);
        solidArea.height = 0;
        // Using Timer for delay

        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                down1 = setup("/reso/objects/gate", gp.tileSize, 284);
                solidArea.height = 284;
                gp.playSE(13);

            }
        }, 7000); // Delay of 3 seconds (3000 milliseconds)
        
    }

}
