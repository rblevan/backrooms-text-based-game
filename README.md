# 🚪 The Backrooms: Text Adventure

> **Un jeu d'aventure textuel avec interface graphique, basé sur l'univers des Backrooms.**

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Swing](https://img.shields.io/badge/GUI-Java_Swing-blue)
![Maven](https://img.shields.io/badge/Build-Maven-red)
![JUnit](https://img.shields.io/badge/Tests-JUnit_5-green)

## 🎓 Contexte du projet
Ce projet a été réalisé dans le cadre de ma **3ème année de Licence Informatique (L3)** à l'Université de Poitiers.
L'objectif était de développer une application complète en **Java**, en mettant en œuvre les concepts avancés de la **Programmation Orientée Objet (POO)** : héritage, polymorphisme, et gestion d'événements.

Ce projet a été réalisé en groupe de 3 avec mes camarades suivants :
 - Stéphane CASTAN (@stephcstn) [https://github.com/stephcstn]
 - Basile DUFRENE (@Basile) [https://github.com/Basile-Dufrene]

---

## 🕹️ Le Jeu

Plongé dans l'univers angoissant des "Backrooms", le joueur doit trouver la sortie en naviguant à travers des pièces labyrinthiques.
Le jeu se présente sous la forme d'une **Fiction Interactive** : le joueur saisit des commandes textuelles pour interagir avec l'environnement, combattre des entités et gérer son inventaire.

### Fonctionnalités Principales
* **Exploration :** Navigation entre différentes "Rooms" via des commandes directionnelles.
* **Système de Combat :** Affrontement contre des entités hostiles (monstres) avec des armes.
* **Inventaire (Backpack) :** Gestion de poids, ramassage d'objets (clés, armes, nourriture) et consommation de potions.
* **Parser de Commandes :** Analyse syntaxique des entrées utilisateur pour déclencher les actions.
* **Interface Graphique (GUI) :** Utilisation de **Java Swing** pour une expérience plus immersive qu'une simple console.

---

## 🏗️ Architecture Technique

Le projet respecte une architecture modulaire et une hiérarchie de classes stricte :

### 1. Modèle (Logique Métier)
* **Héritage & Polymorphisme :**
    * La classe abstraite `Item` est mère de `Food` et `Weapon`.
    * Gestion centralisée des entités via la classe `Entity`.
* **Gestion des lieux :** La classe `Locations` et `Exits` gèrent le graphe de navigation du labyrinthe.
* **Le Joueur :** La classe `Hero` gère les points de vie (HP), l'inventaire (`Backpack`) et les états du joueur.

### 2. Vue & Contrôleur
* **`GameWindow.java` :** Interface utilisateur basée sur `JFrame`. Elle gère l'affichage du texte narratif et la saisie des commandes.
* **`Main.java` :** Point d'entrée de l'application, initialise la boucle de jeu.
* **`Commands.java` :** Interprète les saisies (ex: "GO NORD", "EAT POMME", "HIT MONSTRE").

### 3. Qualité Code
* **Tests Unitaires :** Une suite de tests **JUnit** couvre les fonctionnalités critiques (Inventaire, Déplacements, Logique des items) pour assurer la robustesse du code.

---

## 📸 Aperçu (Gameplay)

| Interface de Jeu |
|:---:|
| <img src="./screenshots/gameplay.png" width="600"> |

*(L'interface permet de lire la description des lieux et de taper ses commandes en bas de fenêtre)*

---

## 🛠️ Installation et Lancement

### Prérequis
* Java JDK 17 ou supérieur
* Maven

### Cloner et Lancer
Le projet est géré par Maven. Placez-vous dans le dossier du jeu pour le lancer.

```bash
# Entrer dans le dossier du code source
cd backrooms_game

# Compiler et lancer les tests
mvn clean install

# Lancer le jeu (via Maven)
mvn exec:java -Dexec.mainClass="fr.univpoitiers.backrooms.Main"
