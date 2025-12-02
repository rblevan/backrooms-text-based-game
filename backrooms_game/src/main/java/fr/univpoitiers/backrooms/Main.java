package fr.univpoitiers.backrooms;

import fr.univpoitiers.backrooms.classes.Characters;
import fr.univpoitiers.backrooms.classes.Commands;
import fr.univpoitiers.backrooms.classes.Locations;

import javax.swing.SwingUtilities;

import static java.lang.Thread.onSpinWait;
import static java.lang.Thread.sleep;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            // --- Game Data Initialization ---


            Commands commandProcessor = new Commands();

            GameWindow gameWindow = new GameWindow(commandProcessor);

            // --- Initial Game Text ---
            gameWindow.appendText("Welcome to the Backrooms.\n");
            gameWindow.appendText("You find yourself in a strange place...\n\n");
            // gameWindow.appendText(r1.getDescription() + "\n");
            // gameWindow.appendText(r2.getDescription() + "\n");
            
        });

    }
}
