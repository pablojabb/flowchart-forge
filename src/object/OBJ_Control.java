package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Control extends Entity {

    GamePanel gp;
    public static final String objName = "Control";
    private boolean isFirstInteract = true;

    public OBJ_Control(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = objName;
        down1 = setup("/reso/objects/controlon", 180, 40);

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
        dialogues[0][0] = "You opened the rooms electricity.";
         dialogues[1][0] = "You closed the rooms electricity.";
    }

    public void interact() {
        startDialogue(this, 0);

       

        // Check the toggle flag to determine the action
        if (isFirstInteract) {
            down1 = setup("/reso/objects/controloff", 180, 40);
            gp.obj[gp.currentMap][13].down1 = setup("/reso/objects/ON", gp.tileSize, gp.tileSize);
            gp.obj[gp.currentMap][4].down1 = setup("/reso/objects/doorsB", gp.tileSize, 235);
            gp.obj[gp.currentMap][4].solidArea.height = 0;
            isFirstInteract = false; // Toggle the flag for the next interaction
            
            gp.player.lightsOn=true;
            
             gp.playSE(26);
               startDialogue(this, 0);
            
        } else {
            down1 = setup("/reso/objects/controlon", 180, 40);
            gp.obj[gp.currentMap][13].down1 = setup("/reso/objects/OFF", gp.tileSize, gp.tileSize);
            gp.obj[gp.currentMap][4].down1 = setup("/reso/objects/doorsA", gp.tileSize, 235);
            gp.obj[gp.currentMap][4].solidArea.height = 220;
            isFirstInteract = true; // Toggle the flag for the next interaction
            
             gp.player.lightsOn=false;
            
             gp.playSE(26);
               startDialogue(this, 1);
        }
    }
}
