package main;

import javax.swing.UIManager;
import view.StartScreen;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Σετάρισμα του LookAndFeel του περιβάλλοντος
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }

        StartScreen startScreen = new StartScreen();
        startScreen.setVisible(true);
        startScreen.setLocationRelativeTo(null);
}}
