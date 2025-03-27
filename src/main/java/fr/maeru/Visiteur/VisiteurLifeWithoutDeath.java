package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

/**
 * Classe représentant un visiteur avec la règle du jeu "Life Without Death" pour le jeu de la vie.
 * Dans cette règle, les cellules mortes peuvent devenir vivantes si elles ont exactement 3 voisins vivants,
 * mais aucune cellule vivante ne meurt.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class VisiteurLifeWithoutDeath extends Visiteur {

    /**
     * Constructeur de la classe VisiteurLifeWithoutDeath.
     * 
     * @param jeu L'instance du jeu de la vie sur lequel le visiteur opère.
     */
    public VisiteurLifeWithoutDeath(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Méthode qui visite une cellule vivante. Dans cette règle, aucune cellule vivante ne meurt,
     * donc cette méthode ne fait rien.
     * 
     * @param c La cellule vivante à visiter.
     */
    @Override
    public void visiterCelluleVivante(Cellule c) {
    }

    /**
     * Méthode qui visite une cellule morte et applique la règle "Life Without Death" :
     * Si la cellule morte a exactement 3 voisins vivants, elle devient vivante.
     * 
     * @param c La cellule morte à visiter et potentiellement faire renaître.
     */
    @Override
    public void visiterCelluleMorte(Cellule c) {
        int voisinsVivants = c.nombreVoisinesVivantes(jeu);
        if (voisinsVivants == 3) {
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }
}
