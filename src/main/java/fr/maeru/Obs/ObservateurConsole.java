package fr.maeru.Obs;

import fr.maeru.JeuDeLaVie;

/**
 * Implémentation de l'observateur pour l'interface console.
 * Cette classe observe les évolutions du jeu de la vie et affiche dans la console les informations
 * sur la génération actuelle ainsi que le nombre de cellules vivantes.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class ObservateurConsole implements Observateur {
    
    private JeuDeLaVie jeu; // Référence au jeu à observer
    private int generation; // Compteur de génération actuel
    
    /**
     * Constructeur de l'observateur console.
     * 
     * @param jeu L'instance du jeu de la vie à observer.
     */
    public ObservateurConsole(JeuDeLaVie jeu) {
        this.jeu = jeu;
        this.generation = 0; // Initialisation de la génération à 0
    }

    /**
     * Actualise l'affichage de la génération actuelle et du nombre de cellules vivantes.
     * Cette méthode est appelée chaque fois qu'une nouvelle génération est calculée.
     */
    @Override
    public void actualise() {
        generation++; // Incrémentation du compteur de génération
        int nombreCellulesVivantes = compterCellulesVivantes(); // Comptage des cellules vivantes
        System.out.println("Génération : " + generation); // Affichage de la génération
        System.out.println("Cellules vivantes : " + nombreCellulesVivantes); // Affichage du nombre de cellules vivantes
    }

    /**
     * Compte le nombre de cellules vivantes sur la grille du jeu.
     * 
     * @return Le nombre de cellules vivantes dans la grille.
     */
    private int compterCellulesVivantes() {
        int count = 0;
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                // Si la cellule est vivante, on incrémente le compteur
                if (jeu.getGrilleXY(x, y).estVivante()) {
                    count++;
                }
            }
        }
        return count;
    }
}
