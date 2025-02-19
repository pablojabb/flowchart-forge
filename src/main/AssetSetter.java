package main;

import data.Progress;
import entity.NPC_Brown;
import entity.NPC_OldMan;
import entity.NPC_Orange;
import entity.NPC_White;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 2;
//

        gp.obj[mapNum][i] = new OBJ_Gate(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 23;
        i++;

        gp.obj[mapNum][i] = new OBJ_Gate(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        gp.obj[mapNum][i] = new OBJ_Backdoor(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 34;
        gp.obj[mapNum][i].worldY = gp.tileSize * 24;
        i++;

        gp.obj[mapNum][i] = new OBJ_Van(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 36;
        gp.obj[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        gp.obj[mapNum][i] = new OBJ_Trashbin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 32;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[mapNum][i] = new OBJ_Trashbintile(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.obj[mapNum][i] = new OBJ_Trashbintile(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.obj[mapNum][i] = new OBJ_Trashbintile(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 19;
        gp.obj[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.obj[mapNum][i] = new OBJ_Trashbintile(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 28;
        gp.obj[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.obj[mapNum][i] = new OBJ_Refinary(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 20;
        gp.obj[mapNum][i].worldY = gp.tileSize * 27;
        i++;

        gp.obj[mapNum][i] = new OBJ_Control(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[mapNum][i] = new OBJ_Remote(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 20;
        i++;

        gp.obj[mapNum][i] = new OBJ_Blank(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 23;
        gp.obj[mapNum][i].worldY = gp.tileSize * 28;
        i++;

        gp.obj[mapNum][i] = new OBJ_Remote(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 31;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;

        gp.obj[mapNum][i] = new OBJ_Remote(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 32;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;

        gp.obj[mapNum][i] = new OBJ_Remote(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;

        gp.obj[mapNum][i] = new OBJ_Control(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;

        gp.obj[mapNum][i] = new OBJ_Control(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;

        gp.obj[mapNum][i] = new OBJ_Van(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 36;
        gp.obj[mapNum][i].worldY = gp.tileSize * 17;
        i++;

        gp.obj[mapNum][i] = new OBJ_Control(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 19;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;

        gp.obj[mapNum][i] = new OBJ_Van(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 6;
        gp.obj[mapNum][i].worldY = gp.tileSize * 24;
        i++;

        switch (gp.player.level) {
            case 0:
                //A

                break;

            case 1:

                break;

            case 2:
                gp.obj[mapNum][i] = new OBJ_GreenBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 18) + 12; //876   864
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.greenBox++;

                gp.player.boxesQ = gp.player.greenBox + gp.player.redBox;
                break;

            case 3:

                gp.obj[mapNum][i] = new OBJ_GreenBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 18) + 12; //876   864
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.greenBox++;

                gp.player.boxesQ = gp.player.greenBox + gp.player.redBox;
                break;

            case 4:

                gp.obj[mapNum][i] = new OBJ_GreenBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 18) + 12; //876   864
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.greenBox++;

                gp.obj[mapNum][i] = new OBJ_GreenBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 17) + 12; //876   864
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.greenBox++;

                gp.player.boxesQ = gp.player.greenBox + gp.player.redBox;
                break;

            case 5:

                gp.obj[mapNum][i] = new OBJ_RedBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 18) + 12; //876   864
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.redBox++;

                gp.player.boxesQ = gp.player.greenBox + gp.player.redBox;
                break;

            case 6:

                gp.obj[mapNum][i] = new OBJ_RedBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 17) + 12;
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.redBox++;

                gp.player.boxesQ = gp.player.greenBox + gp.player.redBox;
                break;

            case 7:

                gp.obj[mapNum][i] = new OBJ_RedBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 17) + 12;
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.redBox++;

                gp.obj[mapNum][i] = new OBJ_GreenBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 18) + 12; //876   864
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.greenBox++;

                gp.player.boxesQ = gp.player.greenBox + gp.player.redBox;
                break;

            case 8:

                gp.obj[mapNum][i] = new OBJ_RedBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 17) + 12;
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.redBox++;

                gp.obj[mapNum][i] = new OBJ_RedBox(gp);
                gp.obj[mapNum][i].worldX = (gp.tileSize * 18) + 12; //876   864
                gp.obj[mapNum][i].worldY = gp.tileSize * 21;
                i++;
                gp.player.redBox++;

                gp.player.boxesQ = gp.player.greenBox + gp.player.redBox;
                break;

        }

    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        //MAP = 0
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 21;
        gp.npc[mapNum][i].worldY = gp.tileSize * 24;
        i++;

        gp.npc[mapNum][i] = new NPC_Orange(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 25;
        gp.npc[mapNum][i].worldY = gp.tileSize * 32;
        i++;

        gp.npc[mapNum][i] = new NPC_Orange(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 15;
        gp.npc[mapNum][i].worldY = gp.tileSize * 32;
        i++;

        gp.npc[mapNum][i] = new NPC_Orange(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 34;
        gp.npc[mapNum][i].worldY = gp.tileSize * 32;
        i++;

        gp.npc[mapNum][i] = new NPC_White(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 22;
        gp.npc[mapNum][i].worldY = gp.tileSize * 14;
        i++;

        gp.npc[mapNum][i] = new NPC_White(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 27;
        gp.npc[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        gp.npc[mapNum][i] = new NPC_White(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 31;
        gp.npc[mapNum][i].worldY = gp.tileSize * 13;
        i++;

        gp.npc[mapNum][i] = new NPC_Brown(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 36;
        gp.npc[mapNum][i].worldY = gp.tileSize * 21;
        i++;

//        gp.npc[mapNum][i] = new NPC_player(gp);
//        gp.npc[mapNum][i].worldX = gp.tileSize * 23;
//        gp.npc[mapNum][i].worldY = gp.tileSize * 21;
//        i++;
    }

}
