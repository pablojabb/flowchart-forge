package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, spacePressed;
    //DEBUG
    public boolean showDebugText = false;
    public boolean godModeOn = false;
    public boolean pressedEnter = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            titleState(code);
        } // PLAY STATE
        else if (gp.gameState == gp.playState) {
            playState(code);
        } // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        } //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
            dialogueState(code);
        } // OPTIONS STATE
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        } // GAMEOVER STATE
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }

    }

    public void titleState(int code) {
        //MAIN MENU
        if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1; // Character class selection screen
//                    gp.gameState = gp.playState;
//                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 1) {
                    //about us
                    gp.gameState = gp.cutsceneState;
                    gp.csManager.sceneNum = gp.csManager.ending;

                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        } else if (gp.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 9;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 9) {
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {

                if (gp.ui.commandNum == 0) {

                    gp.startGame(0);

                }
                //THIEF
                if (gp.ui.commandNum == 1) {
                    gp.player.level = 1;
                    gp.startGame(gp.player.level);
                    System.out.println(gp.player.level);

                }
                //SORCERER
                if (gp.ui.commandNum == 2) {

                    gp.player.level = 2;
                    gp.startGame(gp.player.level);
                    System.out.println(gp.player.level);
                }

                if (gp.ui.commandNum == 3) {

                    gp.player.level = 3;
                    gp.startGame(gp.player.level);
                    System.out.println(gp.player.level);
                }

                if (gp.ui.commandNum == 4) {

                    gp.player.level = 4;
                    gp.startGame(gp.player.level);
                    System.out.println(gp.player.level);
                }

                if (gp.ui.commandNum == 5) {

                    gp.player.level = 5;
                    gp.startGame(gp.player.level);
                    System.out.println(gp.player.level);
                }

                if (gp.ui.commandNum == 6) {

                    gp.player.level = 6;
                    gp.startGame(gp.player.level);
                    System.out.println(gp.player.level);
                }
                //BACK
                if (gp.ui.commandNum == 7) {

                    gp.player.level = 7;
                    gp.startGame(gp.player.level);
                    System.out.println(gp.player.level);
                }
                if (gp.ui.commandNum == 8) {

                    gp.player.level = 8;
                    gp.startGame(gp.player.level);
                    System.out.println(gp.player.level);
                }

                if (gp.ui.commandNum == 9) {
                    gp.ui.titleScreenState = 0;
                }
            }
        }

    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

//        if(code == KeyEvent.VK_I)
//        {
//           gp.player.startDialogue(gp.player, 0);
//        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }

        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        //DEBUG
        if (code == KeyEvent.VK_T) //Debug Menu
        {
            if (showDebugText == false) {
                showDebugText = true;
            } else if (showDebugText == true) {
                showDebugText = false;
            }
        }
        if (code == KeyEvent.VK_R) //Refresh Map without restarting game // Save Map File : in IntellijIDE "Ctrl + F9", in Eclipce "Ctrl + S"
        {
            switch (gp.currentMap) {
                case 0:
                    gp.tileM.loadMap("/maps/worldmap.txt", 0);
                    break;
                case 1:
                    gp.tileM.loadMap("/maps/interior01.txt", 1);
                    break;
            }
        }
        if (code == KeyEvent.VK_G) //Debug Menu
        {
            if (godModeOn == false) {
                godModeOn = true;
            } else if (godModeOn == true) {
                godModeOn = false;
            }

        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
            pressedEnter = true;
        }
    }

    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0:
                maxCommandNum = 5;
                break;
            case 3:
                maxCommandNum = 1;
                break;
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSE(9);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) //music
                {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();  //check for music maybe a song is already being played, but you dont need it for SE, when set a sound checkVolume will be execute.
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) //SE
                {
                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) //music
                {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) //SE
                {
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }
    }

    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }

        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }

        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) //RETRY, reset position, life, mana, monsters, npcs...
            {
                gp.playSE(12);
                gp.startGame(gp.player.level);

            } else if (gp.ui.commandNum == 1) //QUIT, reset everything
            {

                if (gp.player.level == 8) {
                    gp.gameState = gp.cutsceneState;
                    gp.csManager.sceneNum = gp.csManager.ending;
                } else {

                    gp.playSE(29);
                    gp.player.level++;
                    gp.startGame(gp.player.level);

                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;

        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
