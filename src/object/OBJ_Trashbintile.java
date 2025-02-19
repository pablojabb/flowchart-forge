package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class OBJ_Trashbintile extends Entity {

    GamePanel gp;
    public static final String objName = "Door";

    public OBJ_Trashbintile(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = objName;
        down1 = setup("/reso/objects/trash/trashtile", 90, 100);
      
        collision = true;

        solidArea.x = 40;
        solidArea.y = 40;
        solidArea.width = 90;
        solidArea.height = 70;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "Ammunition crate disposed.";
    }

    public void interact() {
        
          
             startDialogue(this, 0);
        }

    
    }

