# Etat d’avancement : 24/11


•    Adaptation du mouvement du personnage avec les inputs du clavier: (6h code - 3 jours pour la création du personnage 2d pixelisé)(Mouna)
    - Création d'une version graphique avec un fond d'écran vert, et un vrai personnage en 2d.
    - Gestion du mouvement du personnage en 2d UP, DOWN, LEFT, RIGHT
    - Demande à un proche pour la création des autres éléments manquants en 2d :
        - Le personnage Hero est créé jusqu'à maintant.
    - Les releases n'ont toujours pas été fait à cause d'un soucis en relation avec le pom.xml

•    Amélioration du personnage Hero (GAB)
    - Création d'une version 1.0

•    Création de la classe Monster :
        -Implémentation d'une classe représentant un monstre dans un jeu, héritant de la classe Character
        -Ajout des fonctionnalités principales :
            -Attribut isIntelligent pour différencier les comportements.
            -Constructeur initialisant les propriétés du monstre (coordonnées, vie, dégâts, vitesse, intelligence).

        Méthodes principales :
            move :
                -Déplacement aléatoire si le monstre n’est pas intelligent.
                -Déplacement basé sur une direction spécifique si le monstre est intelligent.
                -Poursuite du héros avec "poursuivreHero"