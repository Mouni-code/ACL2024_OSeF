# Backlog Sprint Final (25/11/2024)

## Objectif du sprint :
Finaliser le jeu en intégrant les dernières fonctionnalités nécessaires à une expérience complète et cohérente, avec une gestion des cases spéciales, une interaction héros-monstre, et des tests pour garantir la qualité.

---

## I. Gestion des cases spéciales (Gabriel) ~1sem
Les cases spéciales introduisent des mécaniques uniques dans le jeu pour enrichir l'expérience des joueurs.

### Types de cases :
1. **Case de soin :**
   - **Effet :** Le héros regagne des points de vie.
   - **Tâches :**
     - Détection : Identifier lorsque le héros atteint la case.
     - Implémenter l'effet : Augmenter les points de vie du héros en fonction d’un ratio défini.
     - Test : Vérifier la cohérence entre les points gagnés et le maximum autorisé.

2. **Case dégât (trap) :**
   - **Effet :** Inflige des dégâts au héros.
   - **Tâches :**
     - Détection : Identifier lorsque le héros marche sur la case.
     - Implémenter l'effet : Réduire les points de vie du héros.
     - Test : Simuler différents scénarios pour valider les calculs.

3. **Case téléportation (niveau à niveau) :**
   - **Effet :** Transfère le héros vers une nouvelle zone/niveau.
   - **Tâches :**
     - Détection et activation.
     - Génération de la nouvelle zone : Charger ou générer un niveau suivant les paramètres prédéfinis.
     - Test : Vérifier les transitions et la position initiale du héros.

4. **Case marchand/trésor :**
   - **Effet :** Offre des objets, des améliorations ou de l'équipement.
   - **Tâches :**
     - Interaction : Interface pour échanger des ressources (or, étoiles) contre des objets.
     - Génération : Déterminer les objets disponibles selon le niveau actuel.
     - Test : Assurer la cohérence des échanges et des inventaires.

5. **Étoiles pour le boss final :**
   - **Mécanique :** Collecter un nombre défini d'étoiles pour déverrouiller l'accès au boss.
   - **Tâches :**
     - Suivi : Ajouter un compteur d'étoiles.
     - Condition d'accès : Vérifier que le joueur possède suffisamment d'étoiles pour déclencher le combat final.
     - Test : Simuler l'accès au boss avec et sans étoiles suffisantes.

---

## II. Gestion des monstres ( Fatima & Sabrine) ~1sem
Adapter la mécanique des monstres pour qu'ils interagissent avec le héros et les éléments du jeu.

### Tâches :
1. **Adaptation des monstres au code :**
   - Intégration des modèles existants dans l’architecture actuelle.
   - Synchronisation des mouvements avec les mécanismes du jeu.

2. **Gestion des collisions :**
   - Détecter les contacts entre le héros et les monstres.
   - Appliquer les effets correspondants (dégâts infligés, réaction du héros).

3. **Barres de vie :**
   - Héros : Mettre à jour la barre en fonction des dégâts reçus ou de la régénération.
   - Monstres : Ajouter des barres visibles pour certains ennemis clés (par exemple, le boss).

---

## III. Intégration et amélioration du code ( Mouna)

### Tâches :
1. **Fusion des deux codes actuels :** ~2 jours
   - Assembler les fonctionnalités existantes dans un projet cohérent.
   - Résolution des conflits entre les modules.

2. **Nettoyage du code :**
   - Supprimer les parties inutilisées ou redondantes.
   - Améliorer la lisibilité (commentaires, reformulations).

3. **Correction des erreurs :**
   - Identifier et résoudre les bugs en effectuant des tests ciblés.

4. **Ajout de tests :**
   - Tests unitaires pour valider les comportements des fonctions critiques.
   - Tests d'intégration pour vérifier les interactions entre modules.

2&3&4 ~ 3jours

5. **Menu principal :** 2h
   - Interface pour commencer une partie, charger un niveau ou quitter.
   - Design simple et intuitif avec navigation fluide.
 6. **Ajout des diagrammes UML :** ~ 1h
    - Amélioration du Diagramme de classes existant afin de l'adapter avec le code actuel.
7. **Ajouter les trois releases du jeu :** ~30min
    - L'ajout de la version V.0 de la version.
    - L'ajout de la version V.1.
    - L'ajout de la version finale.
---
