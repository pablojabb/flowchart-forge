package main;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // Set the FlatLaf theme before creating any Swing components
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Set some UIManager properties
        UIManager.put("Button.arc", 20);
        UIManager.put("TextComponent.arc", 20);

        // Use EventQueue.invokeLater to ensure the GUI creation runs on the Event Dispatch Thread (EDT)
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Flowchart Forge");

        GamePanel gamePanel = new GamePanel();
        Side side = new Side();

        side.setGamePanel(gamePanel);
        gamePanel.setSidePanel(side);

        gamePanel.config.loadConfig();

        // Create a container to hold both panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(gamePanel, BorderLayout.CENTER); // Add game panel to center
        mainPanel.add(side, BorderLayout.EAST); // Add side panel to the right

        window.add(mainPanel);
        window.pack();
        window.setLocationRelativeTo(null);

        gamePanel.setupGame();
        gamePanel.startGameThread();

        // Make the window visible
        window.setVisible(true);
    }
}
