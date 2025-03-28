package fr.maeru.Cellule;

import fr.maeru.Visiteur.*;

/**
 * Interface représentant l'état d'une cellule dans le cadre du jeu de la vie.
 * Cette interface définit les méthodes qui permettent de changer l'état de la cellule (vivante ou morte),
 * de vérifier son état actuel et d'interagir avec un visiteur dans le cadre du design pattern Visitor.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public interface CelluleEtat {

    /**
     * Permet à la cellule de devenir vivante.
     * Cette méthode retourne un nouvel état de la cellule, représentant la cellule en vie.
     * 
     * @return Un objet représentant l'état "vivant" de la cellule.
     */
    public CelluleEtat vit();

    /**
     * Permet à la cellule de devenir morte.
     * Cette méthode retourne un nouvel état de la cellule, représentant la cellule morte.
     * 
     * @return Un objet représentant l'état "mort" de la cellule.
     */
    public CelluleEtat meurt();

    /**
     * Vérifie si la cellule est vivante.
     * 
     * @return true si la cellule est vivante, false sinon.
     */
    public Boolean estVivante();

    /**
     * Permet à un visiteur d'interagir avec la cellule en fonction de son état.
     * Cette méthode fait appel au design pattern Visitor pour appliquer une opération spécifique
     * en fonction de l'état de la cellule.
     * 
     * @param v Le visiteur qui va interagir avec la cellule.
     * @param c La cellule qui reçoit l'interaction.
     */
    public void accepte(Visiteur v, Cellule c);
}
