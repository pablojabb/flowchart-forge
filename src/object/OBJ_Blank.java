package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Blank extends Entity {

    GamePanel gp;
    public static final String objName = "blank";

    public OBJ_Blank(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = null;
        isGreen = true;
        down1 = image;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void interact() {

        if (gp.player.take) {

            gp.player.holdingGbox = 1;
            gp.playSE(23);
            down1 = null;
        }

//        gp.obj[gp.currentMap][13].down1 = setup("/objects/ON", gp.tileSize, gp.tileSize);
    }

}
