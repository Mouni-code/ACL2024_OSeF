# 🎮 **Jeu de Labyrinthe - Version Finale**  
### *"Partez à l'aventure pour sauver la princesse !"*  

## 🚀 **Description du Projet**  
Ce projet est un jeu de labyrinthe où un héros, en quête de sauver sa princesse, traverse plusieurs niveaux remplis de défis. Le joueur guide le héros pour combattre des monstres, collecter des trésors et gagner des étoiles indispensables pour progresser dans l'aventure. Le jeu est livré sous forme de fichier JAR exécutable avec des mécaniques enrichies.

---

## 🕹️ **Principe du Jeu**  
L'objectif principal est de guider le héros à travers **deux labyrinthes** successifs :  

- **Combattre des monstres** pour libérer le chemin.  
- **Collecter des trésors** qui vous offriront des **étoiles**.  
- **Progression** : Pour accéder au labyrinthe suivant, le héros doit impérativement récolter **deux étoiles**.  
- **Finalité** : Dans le dernier niveau, vous aurez la chance de **sauver la princesse** !  

---

## 📦 **Structure des Versions**  
### Version Actuelle  
- **Interface graphique** interactive.  
- **Mécaniques de gameplay** :  
   - **Déplacements** avec les touches directionnelles *(Haut, Bas, Gauche, Droite)*.  
   - **Attaques** contre les monstres avec la touche **Espace**.  
   - **Collecte des étoiles** avec la touche **G**.  

---

## ⚙️ **Installation et Exécution du Jeu**  

### 📋 **Prérequis**  
- **Java 17** (ou version ultérieure) installé sur votre machine.  
- **Maven** (optionnel, pour recompiler le projet).  

---

### 🛠️ **Compilation du Projet**  
1. **Clonez le projet** depuis le dépôt GitHub :  
   ```bash
   git clone https://github.com/Mouni-code/ACL2024_OSeF.git
   ```

2. **Accédez au dossier du projet** :

 ```bash
cd ACL2024_OSeF
 ```

3. **Compilez le projet avec Maven pour générer le fichier JAR** :

```bash
mvn clean package
```
- Le fichier JAR sera disponible dans le dossier target/ sous le nom :
ACL2024_OSeF-0.0.1-SNAPSHOT.jar.

### ▶️ Exécution du Jeu
Depuis le répertoire du projet ou directement dans le dossier target, exécutez la commande suivante :

```bash
java -jar target/ACL2024_OSeF-0.0.1-SNAPSHOT.jar
```
Le jeu se lance alors dans une fenêtre graphique, prêt à être joué !

### 🎮 Instructions de Jeu
- **Déplacement** : Utilisez les touches UP, DOWN, LEFT, RIGHT pour déplacer le héros.
- **Attaque** : Appuyez sur ESPACE pour attaquer les monstres.
-**Collecte d'étoiles** : Appuyez sur G pour ramasser des trésors et collecter des étoiles.
#### Objectif :
Explorez chaque labyrinthe.
Combattez les monstres et collectez deux étoiles pour accéder au niveau suivant.
Sauvez la princesse dans le dernier labyrinthe !

### 🌟 Améliorations Futures
Voici des fonctionnalités qui pourraient enrichir le jeu :

- **Boss final** : Ajout d'un combat épique contre un boss pour conclure l'aventure.
- **Système d'EXP** : Gagnez des points d'expérience pour améliorer les compétences du héros.
- **Objets et Magie** : Collectez des objets et des sorts pour faciliter les combats.
-**Nouveaux niveaux** : Ajout d'autres labyrinthes plus complexes pour prolonger le défi.

### 🏁 Bon jeu et amusez-vous bien ! 🏰



