 package main;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
//import tile.Map;
import tile.TileManager;


import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16*16  tile. default
    final int scale = 3; // 16*3 scale

    public final int tileSize = originalTileSize * scale; // 48*48 tile // public cuz we use it in Player Class
    public final int maxScreenCol = 20; // 4:3 window
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  //48*20 = 960 pixels
    public final int screenHeight = tileSize * maxScreenRow;  //48*12 = 576 pixels  // GAME SCREEN SIZE

    public Side side;

    //WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;
    public final int maxMap = 10;
    public int currentMap = 0;

    //FOR FULLSCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    public boolean holdingGbox = false;

    public boolean holdingRbox = false;

    //FPS
    int FPS = 60;

    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public EventHandler eHandler = new EventHandler(this);
    Sound music = new Sound(); // Created 2 different objects for Sound Effect and Music. If you use 1 object SE or Music stops sometimes.
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    SaveLoad saveLoad = new SaveLoad(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][30]; // display 10 objects same time
    public Entity npc[][] = new Entity[maxMap][10];
//    public Entity monster[][] = new Entity[maxMap][20];
    public Entity projectile[][] = new Entity[maxMap][20]; // cut projectile
    //public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;

    public final int cutsceneState = 11;

    //OTHERS
    public boolean bossBattleOn = false;

    public GamePanel() // constructor
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // JPanel size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // improve game's rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setSidePanel(Side side) {
        this.side = side;

    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();

        gameState = titleState;
        //FOR FULLSCREEN
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB); //blank screen
        g2 = (Graphics2D) tempScreen.getGraphics(); // g2 attached to this tempScreen. g2 will draw on this tempScreen buffered image.

    }

    public void resetGame(boolean restart) {
        stopMusic();
        removeTempEntity();
        bossBattleOn = false;
        player.setDefaultPositions();
        player.restoreStatus();
        aSetter.setNPC();
        player.resetCounter();

        if (restart == true) {
            player.setDefaultValues();
            aSetter.setObject();

        }

    }

    public void startGame(int lvl) {

        side.enableLabels(lvl);
        side.disableLabels(lvl);
        side.tableClear();
        stopMusic();
        player.startLevel(lvl);
        gameState = playState;
        playMusic(0);
        removeTempEntity();
        aSetter.setNPC();
        aSetter.setObject();

        player.runOn = true;

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // run'ı cagirir
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        //long timer = 0;
        //int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            //timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                /*repaint(); COMMENTED FOR FULL SCREEN*/
                drawToTempScreen(); //FOR FULL SCREEN - Draw everything to the buffered image
                drawToScreen();     //FOR FULL SCREEN - Draw the buffered image to the screen
                delta--;
                //drawCount++;
            }

        }
    }

    public void update() {
        if (gameState == playState) {
            //PLAYER
            player.update();
//            player.setAction();

            //NPC
            for (int i = 0; i < npc[1].length; i++) //[1] means second dimension's length!!!
            {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

           
        }

    }

    //FOR FULL SCREEN (FIRST DRAW TO TEMP SCREEN INSTEAD OF JPANEL)
    public void drawToTempScreen() {
        //DEBUG
        long drawStart = 0;
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        } //OTHERS
        else {
            //TILE
            tileM.draw(g2);

          

            //ADD ENTITIES TO THE LIST
            //PLAYER
            entityList.add(player);

            //NPCs
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            //OBJECTS
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);   // result returns : (x=y : 0, x>y : >0, x<y : <0)
                    return result;
                }
            });

            //DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            //EMPTY ENTITY LIST
            entityList.clear();

            //CUTSCENE
            csManager.draw(g2);

            //UI
            ui.draw(g2);

            //DEBUG
            if (keyH.showDebugText == true) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;

                g2.setFont(new Font("Arial", Font.PLAIN, 20));
                g2.setColor(Color.white);
                int x = 10;
                int y = 400;
                int lineHeight = 20;

                g2.drawString("WorldX " + player.worldX, x, y);
                y += lineHeight;
                g2.drawString("WorldY " + player.worldY, x, y);
                y += lineHeight;
                g2.drawString("Col " + (player.worldX + player.solidArea.x) / tileSize, x, y);
                y += lineHeight;
                g2.drawString("Row " + (player.worldY + player.solidArea.y) / tileSize, x, y);
                y += lineHeight;
                g2.drawString("Map " + currentMap, x, y);
                y += lineHeight;
                g2.drawString("Draw time: " + passed, x, y);
                y += lineHeight;
                g2.drawString("God Mode: " + keyH.godModeOn, x, y);

            }
        }
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) // Sound effect, dont need loop
    {
        se.setFile(i);
        se.play();
    }

    public void removeTempEntity() {
        for (int mapNum = 0; mapNum < maxMap; mapNum++) {
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[mapNum][i] != null && obj[mapNum][i].temp == true) {
                    obj[mapNum][i] = null;
                }
            }
        }
    }
}
