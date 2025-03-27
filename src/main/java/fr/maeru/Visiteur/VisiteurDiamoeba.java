package fr.maeru.Visiteur;

import java.util.Random;
import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

/**
 * Classe représentant un visiteur qui applique un comportement aléatoire pour le jeu de la vie.
 * Ce visiteur utilise des règles basées sur le hasard pour déterminer si une cellule vivante doit mourir
 * ou si une cellule morte doit devenir vivante. Les règles sont les suivantes :
 * - Une cellule vivante meurt avec une probabilité de 50%.
 * - Une cellule morte devient vivante avec une probabilité de 10%.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class VisiteurDiamoeba extends Visiteur {

    private Random random;

    /**
     * Constructeur de la classe VisiteurDiamoeba.
     * Initialise un générateur de nombres aléatoires.
     * 
     * @param jeu L'instance du jeu de la vie sur lequel le visiteur opère.
     */
    public VisiteurDiamoeba(JeuDeLaVie jeu) {
        super(jeu);
        this.random = new Random();
    }

    /**
     * Méthode qui visite une cellule vivante et, selon une probabilité de 50%, la fait mourir.
     * 
     * @param c La cellule vivante à visiter et potentiellement faire mourir.
     */
    @Override
    public void visiterCelluleVivante(Cellule c) {
        if (random.nextBoolean()) {
            jeu.ajouterCommande(new CommandeMeurt(c));
        }
    }

    /**
     * Méthode qui visite une cellule morte et, selon une probabilité de 10%, la fait renaître.
     * 
     * @param c La cellule morte à visiter et potentiellement faire renaître.
     */
    @Override
    public void visiterCelluleMorte(Cellule c) {
        if (random.nextInt(100) < 10) {
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }
}
