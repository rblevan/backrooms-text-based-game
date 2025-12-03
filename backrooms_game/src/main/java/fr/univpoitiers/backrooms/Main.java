package fr.univpoitiers.backrooms;

import fr.univpoitiers.backrooms.classes.Characters;
import fr.univpoitiers.backrooms.classes.Commands;
import fr.univpoitiers.backrooms.classes.Locations;

import javax.swing.SwingUtilities;

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

            // Level 0 (Lobby)
            level0.addExit(Direction.NORTH, level1);
            level0.addExit(Direction.DOWN, levelMinus1); // CONNEXION RETABLIE
            level0.addExit(Direction.EAST, realWorld);

            // Level -1 (Grey Corridor)
            levelMinus1.addExit(Direction.UP, level0); // Retour au Level 0
            levelMinus1.addExit(Direction.NORTH, level1);
            levelMinus1.addExit(Direction.SOUTH, level3);

            // Level 1 (Living Area)
            level1.addExit(Direction.SOUTH, level0);
            level1.addExit(Direction.EAST, level2);
            level1.addExit(Direction.UP, realWorld);

            // Level 2 (Pipe Dreams)
            level2.addExit(Direction.WEST, level1);
            level2.addExit(Direction.NORTH, level3);
            level2.addExit(Direction.EAST, level4);
            level2.addExit(Direction.UP, level8); // Entrée par les vents

            // Level 3 (Electrical Station)
            level3.addExit(Direction.SOUTH, level2);
            level3.addExit(Direction.NORTH, level4);
            level3.addExit(Direction.EAST, level5);
            level3.addExit(Direction.WEST, levelMinus1); // Retour vers L-1 (Exits L-1)

            // Level 4 (Abandoned Office)
            level4.addExit(Direction.SOUTH, level3);
            level4.addExit(Direction.UP, level5);
            level4.addExit(Direction.DOWN, level6);
            level4.addExit(Direction.WEST, realWorld);

            // Level 5 (Terror Hotel)
            level5.addExit(Direction.SOUTH, level4); // Retour
            level5.addExit(Direction.NORTH, level6);

            // Level 6 (Lights Out)
            level6.addExit(Direction.SOUTH, level5);
            level6.addExit(Direction.DOWN, level7);

            // Level 7 (Thalassophobia)
            level7.addExit(Direction.UP, level6); // Retour
            level7.addExit(Direction.NORTH, level8);

            // Level 8 (Cave System)
            level8.addExit(Direction.SOUTH, level7);
            level8.addExit(Direction.DOWN, level9);
            level8.addExit(Direction.WEST, level2); // Sortie vers L2 par les vents

            // Level 9 (The Suburbs)
            level9.addExit(Direction.UP, level8); // Retour
            level9.addExit(Direction.EAST, realWorld);


            // --- 3. Initialisation du Joueur et du Jeu ---

            // Initialisation du joueur : NOM + Position de départ (Level 0)
            Hero player = new Hero("Anonyme", level0); // Nécessite un constructeur Hero(String name, Locations startLocation)

            Commands commandProcessor = new Commands(player, level0); // Le processeur de commandes a besoin du joueur
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

            // Level 0 (Lobby)
            level0.addExit(Direction.NORTH, level1);
            level0.addExit(Direction.DOWN, levelMinus1); // CONNEXION RETABLIE
            level0.addExit(Direction.EAST, realWorld);

            // Level -1 (Grey Corridor)
            levelMinus1.addExit(Direction.UP, level0); // Retour au Level 0
            levelMinus1.addExit(Direction.NORTH, level1);
            levelMinus1.addExit(Direction.SOUTH, level3);

            // Level 1 (Living Area)
            level1.addExit(Direction.SOUTH, level0);
            level1.addExit(Direction.EAST, level2);
            level1.addExit(Direction.UP, realWorld);

            // Level 2 (Pipe Dreams)
            level2.addExit(Direction.WEST, level1);
            level2.addExit(Direction.NORTH, level3);
            level2.addExit(Direction.EAST, level4);
            level2.addExit(Direction.UP, level8); // Entrée par les vents

            // Level 3 (Electrical Station)
            level3.addExit(Direction.SOUTH, level2);
            level3.addExit(Direction.NORTH, level4);
            level3.addExit(Direction.EAST, level5);
            level3.addExit(Direction.WEST, levelMinus1); // Retour vers L-1 (Exits L-1)

            // Level 4 (Abandoned Office)
            level4.addExit(Direction.SOUTH, level3);
            level4.addExit(Direction.UP, level5);
            level4.addExit(Direction.DOWN, level6);
            level4.addExit(Direction.WEST, realWorld);

            // Level 5 (Terror Hotel)
            level5.addExit(Direction.SOUTH, level4); // Retour
            level5.addExit(Direction.NORTH, level6);

            // Level 6 (Lights Out)
            level6.addExit(Direction.SOUTH, level5);
            level6.addExit(Direction.DOWN, level7);

            // Level 7 (Thalassophobia)
            level7.addExit(Direction.UP, level6); // Retour
            level7.addExit(Direction.NORTH, level8);

            // Level 8 (Cave System)
            level8.addExit(Direction.SOUTH, level7);
            level8.addExit(Direction.DOWN, level9);
            level8.addExit(Direction.WEST, level2); // Sortie vers L2 par les vents

            // Level 9 (The Suburbs)
            level9.addExit(Direction.UP, level8); // Retour
            level9.addExit(Direction.EAST, realWorld);


            // --- 3. Initialisation du Joueur et du Jeu ---

            // Initialisation du joueur : NOM + Position de départ (Level 0)
            Hero player = new Hero("Anonyme", level0); // Nécessite un constructeur Hero(String name, Locations startLocation)

            Commands commandProcessor = new Commands(); // Le processeur de commandes a besoin du joueur
            GameWindow gameWindow = new GameWindow(commandProcessor);

            // --- Initial Game Text ---
            gameWindow.appendText("Welcome to the Backrooms.\n");
            gameWindow.appendText("You find yourself in a strange place...\n\n");

            // Affiche la description de la salle de départ
            //gameWindow.appendText("--- " + player.getCurrentLocation().getTitle() + " ---\n");
            //gameWindow.appendText(player.getCurrentLocation().getDescription() + "\n");
