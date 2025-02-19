package object;

import entity.Entity;
import java.util.Timer;
import java.util.TimerTask;
import main.GamePanel;

public class OBJ_GreenBox extends Entity {

    GamePanel gp;
    public static final String objName = "GreenBox";

    public OBJ_GreenBox(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        image = setup("/reso/objects/bg", gp.tileSize, gp.tileSize);
        isGreen = true;
        down1 = image;

    }

    @Override
    public boolean use(Entity entity) {

        gp.player.homerun = false;
        gp.player.greenBox--;
         gp.player.boxesQ--;
//        System.out.println(gp.player.boxesQ);

        gp.playSE(23);
       
        cleanup();
        return true;
    }

    private void cleanup() {
        // Dispose of resources
        if (image != null) {
            image.flush(); // Flush the image to release resources
            image = null;

        }

    }

}
