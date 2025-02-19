package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class OBJ_Backdoor extends Entity {

    GamePanel gp;
    public static final String objName = "Door";

    public OBJ_Backdoor(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = objName;
        down1 = setup("/reso/objects/doorsA", gp.tileSize, 235);
        down2 = setup("/reso/objects/doorsB", gp.tileSize, 235);
        collision = true;

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = gp.tileSize;
        solidArea.height = 235;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = " to the Ambatukam Armory.";
         dialogues[1][0] = " The electricty is off.";
    }

    public void interact() {
        
       if(!lightsOn){
           gp.player.index =  gp.player.commands.size() +1;
           startDialogue(this, 1);
       }
    
    }

    
    }

