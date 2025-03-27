package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

/**
 * Classe représentant un visiteur qui applique les règles classiques du jeu de la vie.
 * Ce visiteur détermine si une cellule doit vivre ou mourir en fonction de son nombre de voisins vivants.
 * Les règles classiques sont appliquées à la cellule en question : une cellule morte avec exactement 3 voisins vivants devient vivante,
 * et une cellule vivante avec moins de 2 voisins ou plus de 3 voisins meurt.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class VisiteurClassique extends Visiteur {

    /**
     * Constructeur de la classe VisiteurClassique.
     * 
     * @param jeu L'instance du jeu de la vie sur lequel le visiteur opère.
     */
    public VisiteurClassique(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Méthode qui visite une cellule morte et applique les règles classiques.
     * Si une cellule morte a exactement 3 voisins vivants, elle revient à la vie.
     * 
     * @param c La cellule morte à visiter et à potentiellement réveiller.
     */
    @Override
    public void visiterCelluleMorte(Cellule c) {
        if(c.nombreVoisinesVivantes(jeu) == 3){
            jeu.ajouterCommande(new CommandeVit(c));
            //System.out.println("Naissance : " + c.nombreVoisinesVivantes(jeu));
        } else {
            if(c.nombreVoisinesVivantes(jeu) > 0){
            //System.out.println("IDLE : " + c.nombreVoisinesVivantes(jeu));
            }
        }
    }

    /**
     * Méthode qui visite une cellule vivante et applique les règles classiques.
     * Si une cellule vivante a moins de 2 voisins ou plus de 3 voisins vivants, elle meurt.
     * 
     * @param c La cellule vivante à visiter et à potentiellement faire mourir.
     */
    @Override
    public void visiterCelluleVivante(Cellule c) {
        if(c.nombreVoisinesVivantes(jeu) > 3 || c.nombreVoisinesVivantes(jeu) < 2){
            jeu.ajouterCommande(new CommandeMeurt(c));
        } else {
            if(c.nombreVoisinesVivantes(jeu) > 0){
            }
        }
    }
}
