# 🏁 **Review Sprint Final (16/12/2024)**  

## Objectif du Sprint :  
Finaliser le jeu en intégrant les dernières fonctionnalités nécessaires à une expérience complète et cohérente, avec la gestion des cases spéciales, l'interaction entre le héros et les monstres, ainsi que des tests pour garantir la qualité.


## ✅ **I. Gestion des Cases Spéciales (Gabriel)**  
Après réflexion, nous avons décidé d'abandonner l'idée des cases spéciales.

## ✅ **1. Ajout des Diagrammes UML :**  
   - **État :** Le diagramme de classes a été mis à jour et amélioré pour refléter les modifications récentes du code. Il est désormais complet et parfaitement adapté à la structure actuelle du projet.
## ✅ **2.Faire le Release de la version1.0**
- **Version1.0** pour la version avec les premières améliorations.  


## ✅ **II. Gestion des Monstres (Fatima & Sabrine)**  
Les mécaniques liées aux monstres ont été intégrées et ajustées pour interagir correctement avec les éléments du jeu.  

### Tâches :
## ✅ **1. Empêcher le Héros de Sortir de la Carte**  
   - **Description :** Le héros ne peut plus sortir des limites du labyrinthe.  
   - **État :** La gestion des mouvements du héros a été mise à jour pour éviter qu'il dépasse les frontières du niveau. Désormais, les déplacements sont limités aux cases à l'intérieur du labyrinthe.

---

## ✅ **2. Dessiner les Vies et les Barres de Santé**  
   - **Description :** Affichage de la barre de santé du héros et du nombre de vies, ainsi que des barres de santé des monstres.  
   - **État :**  
     - La barre de santé et le nombre de vies du héros s'affichent en haut de l'écran.  
     - Les barres de santé des monstres sont affichées au-dessus de chaque monstre, reflétant leurs points de vie en temps réel.

---

## ✅ **3. Ajouter une Mécanique d'Attaque**  
   - **Description :** Le héros peut attaquer les monstres en appuyant sur la touche Espace. Les monstres peuvent attaquer le héros lorsqu'ils s'en approchent.  
   - **État :**  
     - La fonctionnalité d'attaque du héros a été implémentée avec la touche Espace.  
     - Les monstres réagissent en s'approchant du héros et l'attaquent lorsqu'il est trop proche.

---

## ✅ **4. Faire Tourner les Fantômes autour des Trésors**  
   - **Description :** Les fantômes suivent un chemin circulaire autour des trésors.  
   - **État :**  
     - Cinq points ont été placés autour de chaque trésor, et les fantômes suivent un chemin circulaire autour de ces points, créant ainsi un mouvement dynamique autour des trésors.

---

## ✅ **5. Gérer l'Attaque des Fantômes**  
   - **Description :** Les fantômes attaquent le héros lorsqu'il s'approche trop près d'eux.  
   - **État :**  
     - Les fantômes ont été configurés pour attaquer le héros lorsqu'il se trouve à une distance critique d'eux. Cette interaction rend le jeu plus dynamique et ajoute un défi supplémentaire.

---

## ✅ **6. Afficher le Message "You Died"**  
   - **Description :** Lorsque le héros meurt, il disparaît et le message "You Died" apparaît à l'écran.  
   - **État :**  
     - Le message "You Died" s'affiche correctement lorsque le héros perd toutes ses vies. Le héros disparaît de l'écran, et la partie est suspendue jusqu'à ce que le joueur choisisse de recommencer.

---

## ✅ **III. Intégration et Amélioration du Code (Mouna)**  
L'intégration des différentes fonctionnalités a été réalisée avec succès. Le code a été nettoyé, les erreurs corrigées et des tests ajoutés pour valider les comportements critiques.  

### Tâches :
1. **Fusion des Codes Existants :**  (~24h)
   - **État :** Les différentes parties du code ont été fusionnées en un projet cohérent sans conflits majeurs. Toutes les fonctionnalités sont désormais intégrées dans un environnement de jeu unifié.

2. **Nettoyage du Code :**  
   - **État :** Le code a été nettoyé en supprimant les parties inutiles ou redondantes. Des commentaires ont été ajoutés pour améliorer la lisibilité et la maintenance du code.

3. **Correction des Erreurs :**  
   - **État :** Tous les bugs identifiés lors des tests ont été corrigés. Les tests ont validé que le jeu fonctionne sans erreurs majeures.  
     - **Classe Character devenue Classe Element :** Afin de mieux gérer toutes les entités du jeu, nous avons opté pour une classe plus générale, "Element", en remplacement de la classe "Character". Cette refonte a permis d'améliorer la gestion des mouvements du héros et des monstres. (~ 1 semaine)

4. **Ajout de Tests :**  
   - **État :** Des tests unitaires ont été ajoutés pour valider les fonctions critiques, et des tests d'intégration ont permis de vérifier que toutes les interactions entre les modules sont cohérentes.

5. **Ajout des Versions (Releases) :**  
   - **État :** Les trois versions du jeu ont été ajoutées :  
     - **Version0.0** pour la version initiale.  
     - **Version finale** avec toutes les fonctionnalités implémentées et testées.

6. **Ajout des Conditions de Victoire :**  (~4 heures)
   - **État :** Le héros doit obtenir deux étoiles pour passer au niveau suivant, ajoutant ainsi un nouveau critère de progression.



