package fr.maeru.Cellule;

import fr.maeru.Visiteur.*;

/**
 * Représente l'état "mort" d'une cellule dans le cadre du jeu de la vie.
 * Cette classe implémente l'interface {@link CelluleEtat} et définit les comportements
 * spécifiques lorsque la cellule est morte.
 * Elle suit le pattern **Singleton** pour garantir qu'il n'existe qu'une seule instance de cet état.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class CelluleEtatMort implements CelluleEtat {

    /**
     * Instance unique de l'état "mort" (Singleton).
     */
    private static CelluleEtatMort inst = null;

    /**
     * Constructeur privé pour empêcher la création d'instances multiples.
     */
    private CelluleEtatMort(){}

    /**
     * Permet à une cellule morte de devenir vivante.
     * Cette méthode retourne l'état "vivant" de la cellule en utilisant le **Singleton** de {@link CelluleEtatVivant}.
     * 
     * @return L'état "vivant" de la cellule.
     */
    @Override
    public CelluleEtat vit() {
        return CelluleEtatVivant.getInstance();
    }

    /**
     * Permet à une cellule morte de rester morte.
     * Cette méthode retourne l'instance actuelle de l'état "mort".
     * 
     * @return L'état "mort" de la cellule (instance actuelle).
     */
    @Override
    public CelluleEtat meurt() {
        return this;
    }

    /**
     * Vérifie si la cellule est vivante.
     * Puisque l'état actuel est "mort", cette méthode retourne toujours false.
     * 
     * @return false, car la cellule est morte.
     */
    @Override
    public Boolean estVivante() {
        return false;
    }

    /**
     * Retourne l'instance unique de l'état "mort" (Singleton).
     * Si l'instance n'a pas encore été créée, elle est instanciée une seule fois.
     * 
     * @return L'instance unique de l'état "mort".
     */
    public static CelluleEtatMort getInstance(){
        if(inst == null){
            inst = new CelluleEtatMort();
        }
        return inst;
    }

    /**
     * Permet à un visiteur d'interagir avec la cellule en état "mort".
     * Cette méthode fait appel au design pattern **Visitor** et invoque la méthode spécifique
     * du visiteur pour l'état "mort" de la cellule.
     * 
     * @param v Le visiteur qui interagira avec la cellule.
     * @param c La cellule morte à visiter.
     */
    public void accepte(Visiteur v, Cellule c){
        v.visiterCelluleMorte(c);
    }
}
