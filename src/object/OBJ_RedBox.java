package object;

import entity.Entity;
import java.util.Timer;
import java.util.TimerTask;
import main.GamePanel;

public class OBJ_RedBox extends Entity {

    GamePanel gp;
    public static final String objName = "RedBox";

    public OBJ_RedBox(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        image = setup("/reso/objects/br", gp.tileSize, gp.tileSize);
        isGreen = false;
        down1 = image;

    }
    


    public boolean use(Entity entity) {

         gp.player.homerun = false;
         gp.player.redBox--;
         gp.player.boxesQ--;
        gp.playSE(23);
       
        
        
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
////                gp.player.redBox--;
//
//            }
//        }, 1000); 

        return true;
    }
}
