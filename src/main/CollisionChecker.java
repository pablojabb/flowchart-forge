package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

   public void checkTile(Entity entity) {
    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

    int entityLeftCol = Math.max(0, Math.min(entityLeftWorldX / gp.tileSize, gp.maxWorldCol - 1));
    int entityRightCol = Math.max(0, Math.min(entityRightWorldX / gp.tileSize, gp.maxWorldCol - 1));
    int entityTopRow = Math.max(0, Math.min(entityTopWorldY / gp.tileSize, gp.maxWorldRow - 1));
    int entityBottomRow = Math.max(0, Math.min(entityBottomWorldY / gp.tileSize, gp.maxWorldRow - 1));

    int tileNum1, tileNum2;

    // Use a temporal direction when it's being knockbacked
    String direction = entity.direction;
    if (entity.knockBack == true) {
        direction = entity.knockBackDirection;
    }

    switch (direction) {
        case "up":
            entityTopRow = Math.max(0, Math.min(entityTopRow, gp.maxWorldRow )); // Adjusting for direction
            tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
            if (entityTopRow >= 0 && entityLeftCol >= 0 && entityRightCol < gp.maxWorldCol &&
                    tileNum1 >= 0 && tileNum1 < gp.tileM.mapTileNum[gp.currentMap].length &&
                    tileNum2 >= 0 && tileNum2 < gp.tileM.mapTileNum[gp.currentMap].length &&
                    (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)) {
                entity.collisionOn = true;
            }
            break;
        case "down":
            entityBottomRow = Math.max(0, Math.min(entityBottomRow , gp.maxWorldRow )); // Adjusting for direction
            tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
            if (entityBottomRow < gp.maxWorldRow && entityLeftCol >= 0 && entityRightCol < gp.maxWorldCol &&
                    tileNum1 >= 0 && tileNum1 < gp.tileM.mapTileNum[gp.currentMap].length &&
                    tileNum2 >= 0 && tileNum2 < gp.tileM.mapTileNum[gp.currentMap].length &&
                    (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)) {
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftCol = Math.max(0, Math.min(entityLeftCol , gp.maxWorldCol )); // Adjusting for direction
            tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
            if (entityLeftCol >= 0 && entityTopRow >= 0 && entityBottomRow < gp.maxWorldRow &&
                    tileNum1 >= 0 && tileNum1 < gp.tileM.mapTileNum[gp.currentMap][0].length &&
                    tileNum2 >= 0 && tileNum2 < gp.tileM.mapTileNum[gp.currentMap][0].length &&
                    (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)) {
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightCol = Math.max(0, Math.min(entityRightCol , gp.maxWorldCol )); // Adjusting for direction
            tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
            if (entityRightCol < gp.maxWorldCol && entityTopRow >= 0 && entityBottomRow < gp.maxWorldRow &&
                    tileNum1 >= 0 && tileNum1 < gp.tileM.mapTileNum[gp.currentMap][0].length &&
                    tileNum2 >= 0 && tileNum2 < gp.tileM.mapTileNum[gp.currentMap][0].length &&
                    (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)) {
                entity.collisionOn = true;
            }
            break;
    }
}


    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        //Use a temporal direction when it's being knockbacked
        String direction = entity.direction;
        if (entity.knockBack == true) {
            direction = entity.knockBackDirection;
        }

        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null) {
                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get the object's solid area position
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;       //entity's solid area and obj's solid area is different.
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch (direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) //Checking if Entity rectangle and Object rectangle intersects.
                {
                    if (gp.obj[gp.currentMap][i].collision == true) //Collision (Player can't enter through a door.)
                    {
                        entity.collisionOn = true;
                    }
                    if (player == true) // Checking this because no one can receive items except the player.
                    {
                        index = i;   // Non-player characters cannot pickup objects.
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX; // Reset
                entity.solidArea.y = entity.solidAreaDefaultY;

                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;     // Reset
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    //NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;   // no collision returns 999;
        //Use a temporal direction when it's being knockbacked
        String direction = entity.direction;
        if (entity.knockBack == true) {
            direction = entity.knockBackDirection;
        }

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get the object's solid area position
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;       //entity's solid area and obj's solid area is different.
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch (direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                    if (target[gp.currentMap][i] != entity) // avoid entity includes itself as a collision target
                    {
                        entity.collisionOn = true;
                        index = i;   // Non-player characters cannot pickup objects.
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX; //Reset
                entity.solidArea.y = entity.solidAreaDefaultY;

                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;     //Reset
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;
        // get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // get the object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;       //entity's solid area and obj's solid area is different.
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX; ////Reset
        entity.solidArea.y = entity.solidAreaDefaultY;

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;     ////Reset
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
