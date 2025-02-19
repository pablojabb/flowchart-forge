package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Van extends Entity {

    GamePanel gp;
    public static final String objName = "van";

    public OBJ_Van(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = setup("/reso/objects/van/van", 280, 120);
        down1 = image;
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
        dialogues[0][0] = "Ammunition crate loaded to the van";
        dialogues[1][0] = "Defect Ammunition crate cant be loaded to the van";

    }

    public void interact() {

//            gp.playSE(3);
        if (gp.player.holdingGbox == 1) {
            down1 = setup("/reso/objects/van/vano", 280, 120);
            gp.player.holdingGbox = 0;
              gp.playSE(25);
            startDialogue(this, 0);
        } else if (gp.player.holdingRbox == 1) {

            startDialogue(this, 1);
        }

    }
}
