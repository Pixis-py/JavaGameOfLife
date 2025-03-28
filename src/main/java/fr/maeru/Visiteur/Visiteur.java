package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;

/**
 * Classe abstraite représentant un visiteur dans le cadre du design pattern Visitor.
 * Cette classe permet de visiter différents types de cellules dans le jeu de la vie.
 * Les sous-classes de Visiteur doivent implémenter les méthodes de visite spécifiques
 * pour les cellules vivantes et mortes.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public abstract class Visiteur {

    // Instance du jeu de la vie dans lequel les cellules seront visitées. 
    protected JeuDeLaVie jeu;

    /**
     * Constructeur de la classe Visiteur.
     * 
     * @param jeu L'instance du jeu de la vie sur lequel le visiteur opère.
     */
    public Visiteur(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    /**
     * Méthode à implémenter pour visiter une cellule vivante.
     * 
     * @param c La cellule vivante à visiter.
     */
    public void visiterCelluleVivante(Cellule c){}

    /**
     * Méthode à implémenter pour visiter une cellule morte.
     * 
     * @param c La cellule morte à visiter.
     */
    public void visiterCelluleMorte(Cellule c){}
}
