package main;

import entity.Entity;
import object.*;

public class EntityGenerator {

    GamePanel gp;

    public EntityGenerator(GamePanel gp)
    {
        this.gp = gp;
    }

    public Entity getObject(String itemName)
    {
        Entity obj = null;

        switch (itemName)
        {
          
            case OBJ_Door.objName: obj = new OBJ_Door(gp);break;
            case OBJ_Door_Iron.objName: obj = new OBJ_Door_Iron(gp);break;
//            case OBJ_Gate.objName: obj = new OBJ_Gate(gp);break;
           

        }
        return obj;
    }

}
