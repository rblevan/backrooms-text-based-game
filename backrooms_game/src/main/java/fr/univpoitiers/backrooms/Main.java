package fr.univpoitiers.backrooms;

import fr.univpoitiers.backrooms.classes.*;
import fr.univpoitiers.backrooms.enumeration.Direction;

import javax.swing.*;
import java.util.Random;


public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Début du bloc lambda SwingUtilities

            // --- 1. Instanciation des Niveaux (Locations) ---

            // Création des objets de destination spéciaux
            Locations realWorld = new Locations("Real World", "Vous avez réussi à revenir à la réalité. Mais pour combien de temps ?");

            // Level -1 : Grey Corridor (Unsafe)
            String descM1 = "You have entered a long, straight hallway. The air is immediately glacial, chilling you to the bone. This place is known as 'The Negative Hub'.";
            Locations levelMinus1 = new Locations("Level -1 : Grey Corridor", descM1);

            // Level 0 : Lobby (Safe - Point de départ)
            String desc0 = "You wake up in an endless maze of yellow-tinted rooms, dimly lit by flickering fluorescent lights that hum with an unbearable buzz. The air smells faintly of mold and electricity.";
            Locations level0 = new Locations("Level 0 : Lobby", desc0);

            // Level 1 : Living Area (Safe)
            String desc1 = "The maddening hum of the yellow wallpaper has faded. You are in a vast warehouse. The air is cold and smells of damp concrete and... something strangely sweet: Almonds. This is Level 1, the 'Habitable Zone'.";
            Locations level1 = new Locations("Level 1 : Living Area", desc1);

            // Level 2 : Pipe Dreams (Unsafe)
            String desc2 = "You have emerged into a suffocating, industrial labyrinth. The air here is thick, heavy, and incredibly hot. Infinite miles of rusting pipes line the walls and ceilings, hissing with steam and dripping unknown fluids.";
            Locations level2 = new Locations("Level 2 : Pipe Dreams", desc2);

            // Level 3 : Electrical Station (Unsafe)
            String desc3 = "The silence is replaced by a deafening, industrial roar. You are standing in a series of narrow, claustrophobic corridors made of dark brick. Thick electrical cables snake along the walls, pulsating with dangerous energy.";
            Locations level3 = new Locations("Level 3 : Electrical Station", desc3);

            // Level 4 : Abandoned Office
            String desc4 = "The noise stops instantly. You are standing on a soft, grey carpet in an infinite, empty office building. You can find Almond Water in the coolers. You feel a sense of safety here.";
            Locations level4 = new Locations("Level 4 : Abandoned Office", desc4);

            // Level 5 : Terror Hotel
            String desc5 = "The office carpet gives way to elegant red rugs and polished mahogany wood. Smooth jazz music plays. You are in a grand, early 20th-century hotel. Staying here too long erodes your sanity.";
            Locations level5 = new Locations("Level 5 : Terror Hotel", desc5);

            // Level 6 : Lights Out
            String desc6 = "You can't see anything. This is not just darkness; it is a void. You must feel your way through the blindness.";
            Locations level6 = new Locations("Level 6 : Lights Out", desc6);

            // Level 7 : Thalassophobia
            String desc7 = "You gasp as you hit the freezing water. You are floating in a flooded room. Beneath the surface lies a vast, impossible ocean. You must swim to survive.";
            Locations level7 = new Locations("Level 7 : Thalassophobia", desc7);

            // Level 8 : Cave System
            String desc8 = "You drag yourself out of the water onto rough, jagged stone. You are in a colossal cave system. Glowing moss provides dim, greenish light. This place is a hunting ground. Watch the ceiling.";
            Locations level8 = new Locations("Level 8 : Cave System", desc8);

            // Level 9 : The Suburbs
            String desc9 = "The jagged stone disappears. You are standing on asphalt in a neighborhood of suburban houses under a starless, black sky. It looks like home, but it feels wrong. Do not be fooled by the familiarity.";
            Locations level9 = new Locations("Level 9 : The Suburbs", desc9);


            // --- 2. Connexion des Exits (Retablies et Complétées) ---

            // Level -1 (Grey Corridor)
            levelMinus1.addExit(Direction.WEST, new Exits(levelMinus1, "Return to the endless corridor", false)); // Retour au Level 0
            levelMinus1.addExit(Direction.NORTH, new Exits(level1, "A wooden door leading to Level 1", false));
            levelMinus1.addExit(Direction.SOUTH,  new Exits(level3, "A wooden door leading to Level 3", false));
            levelMinus1.addExit(Direction.EAST, new Exits(level0, "A wooden door leading to Level 0", false));

            // Level 0 (Lobby)
            level0.addExit(Direction.NORTH, new Exits(level1, "Staircase going up to Level 1", false));
            level0.addExit(Direction.SOUTH, new Exits(levelMinus1, "A glitching area leading to Level -1", false)); // CONNEXION RETABLIE

            // Level 1 (Living Area)

            level1.addExit(Direction.SOUTH, new Exits(level0, "Door to Level 0", false));
            level1.addExit(Direction.EAST, new Exits(level2, "Maintenance door to Level 2", false));

            // Level 2 (Pipe Dreams)
            level2.addExit(Direction.WEST, new Exits(level1, "Return to Level 1", false));
            level2.addExit(Direction.NORTH, new Exits(level3, "Fire exit to Level 3", false));
            level2.addExit(Direction.EAST, new Exits(level4, "Old corridor toward Level 4", false));

            // Level 3 (Electrical Station)
            level3.addExit(Direction.NORTH,  new Exits(level4, "Elevator to Level 4", false));
            level3.addExit(Direction.EAST,  new Exits(level5, "Rare wooden door to Level 5", false));

            // Level 4 (Abandoned Office)
            level4.addExit(Direction.NORTH, new Exits(level5, "Stairs up to Level 5", false));
            level4.addExit(Direction.SOUTH, new Exits(level6, "Stairs down to Level 6", false));

            // Level 5 (Terror Hotel)
            level5.addExit(Direction.NORTH,  new Exits(level6, "Boiler room to Level 6", false));

            // Level 6 (Lights Out)
            level6.addExit(Direction.SOUTH, new Exits(level7, "Hole leading to Level 7", false));

            // Level 7 (Thalassophobia)
            level7.addExit(Direction.NORTH,  new Exits(level8, "Cave opening to Level 8", false));

            // Level 8 (Cave System)
            level8.addExit(Direction.SOUTH,  new Exits(level9, "Pitfall to Level 9", false));
            level8.addExit(Direction.WEST, new Exits(level2, "Vent leading back to Level 2", false));

            // Level 9 (The Suburbs)
            level9.addExit(Direction.EAST, new Exits(realWorld, "Golden door to real world", false));

            //initialisation d'items dans le jeu

            level0.addItem(new Food("ALMOND_WATER", 1, "Sweet, refreshing and calming.", 20));
            level0.addItem(new Food("ENERGY_BAR", 1, "A dense bar full of calories.", 10));
            level0.addItem(new Weapon("FLASHLIGHT", 1, "A weak flashlight. Can stun small entities.", 5));

            level1.addItem(new Weapon("CROWBAR", 3, "A solid metal tool for defense.", 15));
            level1.addItem(new Food("LUNCHBOX_SANDWICH", 1, "A stale but edible sandwich.", 12));

            level2.addItem(new Weapon("BROKEN_PIPE", 2, "A heavy shard of rusted pipe.", 12));
            level2.addItem(new Food("EXPIRED_RATIONS", 1, "Tastes awful but restores energy.", 8));

            level3.addItem(new Food("ELECTROLYTE_DRINK", 1, "Helps with recovery.", 15));
            level3.addItem(new Spells("STATIC_PULSE", 1, "A spell generating a burst of static energy."));

            level4.addItem(new Food("ALMOND_WATER_BOTTLE", 1, "Fresh almond water.", 25));
            level4.addItem(new Spells("MEMORY_FLASH", 1, "A spell that temporarily reveals hidden paths."));

            level5.addItem(new Weapon("FIRE_AXE", 4, "A sharp emergency axe.", 25));
            level5.addItem(new Spells("HAUNTING_WHISPER", 1, "A dangerous spell with unpredictable effects."));

            level6.addItem(new Weapon("GLOWSTICK_TORCH", 1, "A glowing stick acting as a weak weapon.", 3));
            level6.addItem(new Spells("VOID_ECHO", 1, "A spell born from absolute darkness."));

            level7.addItem(new Food("HYDRATION_GEL", 1, "A strange gel restoring stamina.", 12));

            level8.addItem(new Weapon("CRYSTAL_SHARD", 1, "A sharp glowing stone from the cave.", 10));

            level9.addItem(new Food("CHILDS_SNACK_PACK", 1, "A small pack of candy.", 8));
            level9.addItem(new Spells("SUBURB_MIRAGE", 1, "A spell creating illusions of safety."));

            realWorld.addItem(new Spells("REALITY_ANCHOR", 1, "A spell stabilizing your presence in the real world."));

            Characters lurker = new Characters(35,"The Lurker",5, "A tall, thin creature that stalks silently from the shadows.");

            Characters redHands = new Characters(50, "The Red Hands", 3, "A burned electrical humanoid with crackling fingers.");

            Characters drownedMaiden = new Characters(60, "The Drowned Maiden", 4, "A drowned woman dripping black water, able to pull victims into unseen depths.");

            Characters crawlingChoir = new Characters(80, "The Crawling Choir", 6, "A mass of twisted bodies on the ceiling, whispering in maddening harmony.");

            Characters facelessCaretaker = new Characters(70, "The Faceless Caretaker", 4, "A silent hotel butler with no face, enforcing unknown rules with violent precision.");

            level1.addCharacter(lurker);
            level3.addCharacter(redHands);
            level7.addCharacter(drownedMaiden);
            level8.addCharacter(crawlingChoir);
            level5.addCharacter(facelessCaretaker);


            // --- 3. Initialisation du Joueur et du Jeu ---

            String playerName = JOptionPane.showInputDialog(
                    null,
                    "Enter your name:",
                    "Backrooms - Character Creation",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Anonymous";
            }

            // Initialisation du joueur : NOM + Position de départ (Level 0)
            Backpack backpack = new Backpack("Blue backpack","A standard backpack", 120);
            Hero player = new Hero(playerName, 10,"Everyman",20, "beautiful man or woman", level0, backpack); // Nécessite un constructeur Hero(String name, Locations startLocation)

            Commands commandProcessor = new Commands(player, level0); // Le processeur de commandes a besoin du joueur
            GameWindow gameWindow = new GameWindow(commandProcessor);

            // --- 4. Affichage Initial ---

            gameWindow.appendText("Welcome " + player.getUsername() + " to the Backrooms.\n");
            gameWindow.appendText("You find yourself in a strange place...\n\n");
            gameWindow.appendText("You check your backpack. The faint rustle of the fabric echoes… but there’s nothing inside.\n\n");

            gameWindow.appendText("");

            // Affiche la description de la salle de départ
            //gameWindow.appendText("--- " + player.getCurrentLocation().getTitle() + " ---\n");
            //gameWindow.appendText(player.getCurrentLocation().getDescription() + "\n");

        });
    }
}