package main;

import java.io.*;
import java.net.URL;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            // Saving configuration to the user's home directory
            String userHome = System.getProperty("user.home");
            File configFile = new File(userHome, "config.txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(configFile));

            // Full Screen
            if (gp.fullScreenOn) {
                bw.write("On");
            } else {
                bw.write("Off");
            }
            bw.newLine();

            // Music Volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            // SE Volume
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig() {
        BufferedReader br = null;
        try {
            // Attempt to load the configuration from the user's home directory first
            String userHome = System.getProperty("user.home");
            File configFile = new File(userHome, "config.txt");

            if (configFile.exists()) {
                br = new BufferedReader(new FileReader(configFile));
            } else {
                // Fallback to loading from the classpath resource
                URL resource = getClass().getResource("/reso/config.txt");
                if (resource == null) {
                    throw new FileNotFoundException("Configuration file not found in the classpath.");
                }
                br = new BufferedReader(new InputStreamReader(resource.openStream()));
            }

            String s = br.readLine();

            // Full Screen
            if (s.equals("On")) {
                gp.fullScreenOn = true;
            } else if (s.equals("Off")) {
                gp.fullScreenOn = false;
            }

            // Music Volume
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            // SE Volume
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
