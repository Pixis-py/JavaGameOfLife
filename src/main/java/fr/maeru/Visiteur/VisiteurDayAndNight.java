package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

/**
 * Classe représentant un visiteur qui applique les règles du jeu de la vie "Day and Night".
 * Ce visiteur suit des règles différentes pour déterminer si une cellule doit vivre ou mourir.
 * Les règles sont les suivantes :
 * - Une cellule morte devient vivante si elle a 3, 6 ou 8 voisins vivants.
 * - Une cellule vivante meurt si elle a moins de 2 voisins vivants ou plus de 3 voisins vivants.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class VisiteurDayAndNight extends Visiteur {

    /**
     * Constructeur de la classe VisiteurDayAndNight.
     * 
     * @param jeu L'instance du jeu de la vie sur lequel le visiteur opère.
     */
    public VisiteurDayAndNight(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Méthode qui visite une cellule morte et applique les règles "Day and Night".
     * Si une cellule morte a 3, 6 ou 8 voisins vivants, elle devient vivante.
     * 
     * @param c La cellule morte à visiter et à potentiellement réveiller.
     */
    @Override
    public void visiterCelluleMorte(Cellule c) {
        int nbVoisinesVivantes = c.nombreVoisinesVivantes(jeu);
        if (nbVoisinesVivantes == 3 || nbVoisinesVivantes == 6 || nbVoisinesVivantes == 8) {
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }

    /**
     * Méthode qui visite une cellule vivante et applique les règles "Day and Night".
     * Si une cellule vivante a moins de 2 voisins vivants ou plus de 3 voisins vivants, elle meurt.
     * 
     * @param c La cellule vivante à visiter et à potentiellement faire mourir.
     */
    @Override
    public void visiterCelluleVivante(Cellule c) {
        int nbVoisinesVivantes = c.nombreVoisinesVivantes(jeu);
        if (nbVoisinesVivantes < 2 || nbVoisinesVivantes > 3) {
            jeu.ajouterCommande(new CommandeMeurt(c));
        }
    }
}
