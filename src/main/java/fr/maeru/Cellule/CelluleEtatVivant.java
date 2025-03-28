package fr.maeru.Cellule;

import fr.maeru.Visiteur.*;

/**
 * Représente l'état "vivant" d'une cellule dans le cadre du jeu de la vie.
 * Cette classe implémente l'interface {@link CelluleEtat} et définit les comportements
 * spécifiques lorsque la cellule est vivante.
 * Elle suit le pattern Singleton pour garantir qu'il n'existe qu'une seule instance de cet état.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class CelluleEtatVivant implements CelluleEtat {

    /**
     * Instance unique de l'état "vivant".
     */
    private static CelluleEtatVivant inst = null;

    /**
     * Constructeur privé pour empêcher la création d'instances multiples.
     */
    private CelluleEtatVivant(){}

    /**
     * Permet à une cellule vivante de rester vivante.
     * Cette méthode retourne l'instance actuelle de l'état "vivant".
     * 
     * @return L'état "vivant" de la cellule (instance actuelle).
     */
    @Override
    public CelluleEtat vit() {
        return this;
    }

    /**
     * Permet à une cellule vivante de devenir morte.
     * Cette méthode retourne l'état "mort" de la cellule en utilisant le Singleton de {@link CelluleEtatMort}.
     * 
     * @return L'état "mort" de la cellule.
     */
    @Override
    public CelluleEtat meurt() {
        return CelluleEtatMort.getInstance();
    }

    /**
     * Vérifie si la cellule est vivante.
     * Puisque l'état actuel est "vivant", cette méthode retourne toujours true.
     * 
     * @return true, car la cellule est vivante.
     */
    @Override
    public Boolean estVivante() {
        return true;
    }

    /**
     * Retourne l'instance unique de l'état "vivant".
     * Si l'instance n'a pas encore été créée, elle est instanciée une seule fois.
     * 
     * @return L'instance unique de l'état "vivant".
     */
    public static CelluleEtatVivant getInstance(){
        if(inst == null){
            inst = new CelluleEtatVivant();
        }
        return inst;
    }

    /**
     * Permet à un visiteur d'interagir avec la cellule en état "vivant".
     * Cette méthode fait appel au design pattern Visitor et invoque la méthode spécifique
     * du visiteur pour l'état "vivant" de la cellule.
     * 
     * @param v Le visiteur qui interagira avec la cellule.
     * @param c La cellule vivante à visiter.
     */
    public void accepte(Visiteur v, Cellule c){
        v.visiterCelluleVivante(c);
    }
}
