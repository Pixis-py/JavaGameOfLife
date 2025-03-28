package fr.maeru.Visiteur;

import fr.maeru.Commande.*;
import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;

import java.util.Random;

/**
 * Classe représentant un visiteur qui applique des règles de chaos au jeu de la vie.
 * Ce visiteur modifie les cellules en fonction du nombre de voisins vivants et d'un facteur aléatoire.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class VisiteurChaos extends Visiteur {

    // Instance de la classe Random utilisée pour introduire des éléments aléatoires dans la logique. 
    private static final Random random = new Random();

    /**
     * Constructeur de la classe VisiteurChaos.
     * 
     * @param jeu L'instance du jeu de la vie sur lequel le visiteur opère.
     */
    public VisiteurChaos(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Méthode qui visite une cellule vivante et applique les règles de chaos.
     * Si la cellule a moins de 2 ou plus de 4 voisins vivants ou un facteur aléatoire se déclenche,
     * la cellule meurt.
     * 
     * @param cellule La cellule vivante à visiter et à potentiellement modifier.
     */
    @Override
    public void visiterCelluleVivante(Cellule cellule) {
        int nbVoisins = cellule.nombreVoisinesVivantes(jeu);
        if (random.nextDouble() < 0.07) {
            jeu.ajouterCommande(new CommandeMeurt(cellule));
            return;
        }
        if (nbVoisins < 2 || nbVoisins > 4) {
            jeu.ajouterCommande(new CommandeMeurt(cellule));
        }
    }

    /**
     * Méthode qui visite une cellule morte et applique les règles de chaos.
     * Si la cellule a exactement 3 voisins vivant ou un facteur aléatoire se déclenche,
     * la cellule revient à la vie.
     * 
     * @param cellule La cellule morte à visiter et à potentiellement modifier.
     */
    @Override
    public void visiterCelluleMorte(Cellule cellule) {
        int nbVoisins = cellule.nombreVoisinesVivantes(jeu);
        if (random.nextDouble() < 0.07) {
            jeu.ajouterCommande(new CommandeVit(cellule));
            return;
        }
        if (nbVoisins == 3 || random.nextInt(10) == cellule.hashCode() % 10) {
            jeu.ajouterCommande(new CommandeVit(cellule));
        }
    }
}
