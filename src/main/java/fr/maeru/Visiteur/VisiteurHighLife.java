package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

/**
 * Classe représentant un visiteur avec la règle du jeu "HighLife" pour le jeu de la vie.
 * La règle "HighLife" modifie le comportement des cellules comme suit :
 * - Une cellule morte devient vivante si elle a exactement 3 ou 6 voisins vivants.
 * - Une cellule vivante meurt si elle a moins de 2 ou plus de 3 voisins vivants.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class VisiteurHighLife extends Visiteur {
    
    /**
     * Constructeur de la classe VisiteurHighLife.
     * 
     * @param jeu L'instance du jeu de la vie sur lequel le visiteur opère.
     */
    public VisiteurHighLife(JeuDeLaVie jeu) {
        super(jeu);
    }
    
    /**
     * Méthode qui visite une cellule morte et applique la règle HighLife :
     * Si la cellule a exactement 3 ou 6 voisins vivants, elle devient vivante.
     * 
     * @param c La cellule morte à visiter et potentiellement faire renaître.
     */
    @Override
    public void visiterCelluleMorte(Cellule c) {
        if(c.nombreVoisinesVivantes(jeu) == 3 || c.nombreVoisinesVivantes(jeu) == 6){
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }

    /**
     * Méthode qui visite une cellule vivante et applique la règle HighLife :
     * Si la cellule a moins de 2 ou plus de 3 voisins vivants, elle meurt.
     * 
     * @param c La cellule vivante à visiter et potentiellement faire mourir.
     */
    @Override
    public void visiterCelluleVivante(Cellule c) {
        if(c.nombreVoisinesVivantes(jeu) > 3 || c.nombreVoisinesVivantes(jeu) < 2){
            jeu.ajouterCommande(new CommandeMeurt(c));
        }
    } 
}
