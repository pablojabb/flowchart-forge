package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Remote extends Entity {

    GamePanel gp;
    public static final String objName = "Remote";
    public OBJ_Remote(GamePanel gp)
    {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
        image = setup("/reso/objects/OFF",gp.tileSize,gp.tileSize);
      
        down1 = image;
        
         collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 22;
        solidArea.height = 22;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
      
    }

}
    
