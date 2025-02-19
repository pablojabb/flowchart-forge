package main;

import object.OBJ_Door;
import object.OBJ_Door_Iron;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;

    //Scene Number
    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int ending = 2;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
        endCredit = "John Moises M. Asaldo\n"
                + "Project Coordinator"
                + "\n\n"
                + "John Carlo Cohitmingao\n"
                + "Associate Project Coordinator"
                + "\n\n"
                + "JM Ladrera\n"
                + "Researcher"
                + "\n\n"
                + "Vencint Lucambo\n"
                + "Researcher"
                + "\n\n"
                + "Zyrus Pontillas\n"
                + "Researcher"
                + "\n\n"
                + "Kristle Faith Betuin\n"
                + "Researcher"
                + "\n\n"
                + "Chad Ryan Granali\n"
                + "Researcher"
                + "\n\n\n"
                + "Anthony Jumaya\n"
                + "Game Developer"
                + "\n\n"
                + "Credits to ryisnow in YT \n"
                + "2D Game Referrence"
                 + "\n\n"
                + "Game Assets : Across the web \n"
                + "\n\n"
                + "Music : Miracle Fruit by: RudeManners \n"
                + "\n\n\n"
                + "We hope you learn something\n"
                + "Thank you for playing!";
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (sceneNum) {
            case ending:
                scene_ending();
                break;
        }

    }

    

    public void scene_ending() {
        if (scenePhase == 0) {
            gp.stopMusic();

            scenePhase++;
        }
        if (scenePhase == 1) {
            //Display dialogues
            scenePhase++;
        }
        if (scenePhase == 2) {
            //Play the fanfare
            gp.playSE(29);
            scenePhase++;
        }
        if (scenePhase == 3) {
            //Wait until the sound effect ends
            if (counterReached(100) == true) // 5 sec delay
            {
                scenePhase++;
            }
        }
        if (scenePhase == 4) {
            //The screen gets darker
            /*alpha += 0.005f;  // after 200 frames alpha becomes 1
            if(alpha > 1f)
            {
                alpha = 1f;
            }*/
            alpha = graduallyAlpha(alpha, 0.005f);

            drawBlackBackground(alpha);

            if (alpha == 1f) {
                alpha = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 5) {
            drawBlackBackground(1f);

            //Show message gradually
            /* alpha += 0.005f;  // after 200 frames alpha becomes 1
            if(alpha > 1f)
            {
                alpha = 1f;
            }*/
            alpha = graduallyAlpha(alpha, 0.005f);
            String text = "This 2D game is created to help you easily understand\n"
                    + "flowcharts through challenges in a game.\n"
                    + "It is not perfect, but we hope you learned a lot.\n";

            drawString(alpha, 38f, 200, text, 70);

            if (counterReached(350) == true && alpha == 1f) {
                gp.playMusic(0);
                alpha = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 6) {
            drawBlackBackground(1f);

            alpha = graduallyAlpha(alpha, 0.01f);

            drawString(alpha, 140f, gp.screenHeight / 2, "Flowchart Forge", 40);

            if (counterReached(200) == true && alpha == 1f) {
                scenePhase++;
                alpha = 0;
            }
        }
        if (scenePhase == 7) {
            //First Credits
            drawBlackBackground(1f);

            alpha = graduallyAlpha(alpha, 0.01f);

            y = gp.screenHeight / 2;

            drawString(alpha, 38f, y, endCredit, 40);

            if (counterReached(200) == true && alpha == 1f) {
                scenePhase++;
                alpha = 0;
            }
        }
        if (scenePhase == 8) {
            drawBlackBackground(1f);

            //Scrolling the credit
            y--;
            drawString(1f, 38f, y, endCredit, 40);
            if (counterReached(1350) == true) //22sec
            {
                //Reset
                sceneNum = NA;
                scenePhase = 0;

                //Transition to game again
                gp.gameState = gp.titleState;
                gp.stopMusic();

            }
        }
    }

    public boolean counterReached(int target) {
        boolean counterReached = false;
        counter++;
        if (counter > target) {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }

    public void drawBlackBackground(float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line : text.split("\n")) {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public float graduallyAlpha(float alpha, float grade) {
        alpha += grade;  // after 200 frames alpha becomes 1
        if (alpha > 1f) {
            alpha = 1f;
        }
        return alpha;
    }
}
