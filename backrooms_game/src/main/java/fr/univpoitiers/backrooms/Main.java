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
            Locations r1 = new Locations("- Level 0 -\n\n" + "You wake up in an endless maze of yellow-tinted rooms, dimly lit by flickering fluorescent lights that hum with an unbearable buzz. The wallpaper is old and peeling, the carpet is damp beneath your feet, and the air smells faintly of mold and electricity. Every room looks the same, yet none are truly identical. You walk straight, turn around, retrace your steps - only to find yourself somewhere new. The rules of space don't seem to apply here.\n", null);
            Locations r2 = new Locations("- Level 1 - \n\n" + "The maddening hum of the yellow wallpaper has faded. You are somewhere else.The air is cold and smells of damp concrete and... something strangely sweet. Almonds. You are in a vast warehouse. High above, industrial fluorescent lights cast a harsh, reliable glow. Unlike the flicker of before, the power here is constant. But the floor is wet. Pools of milky water steam gently, releasing a thick, low-hanging fog that clings to the ground. It obscures everything. Abandoned forklifts sit like sleeping beasts in the mist. This is Level 1. They call it the 'Habitable Zone'. The reliable electricity means you are not the first. Others are here, surviving in groups, hidden by the same fog that hides you. You can hear distant echoes, but the mist muffles the sound. Stairwells and dark corridors branch off into the haze, leading to different zones.",null);
            Locations r3 = new Locations("- Level 2 - \n\n" + "C'est le level 2",null);
            Locations r4 = new Locations("- Level 3 - \n\n" + "C'est le level 3",null);
            Locations r5 = new Locations("- Level 4 - \n\n" + "C'est le level 4",null);

            Characters C1 = new Characters(100, "CACA", 20, "je mange les pieds");

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
