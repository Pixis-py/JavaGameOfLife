package fr.maeru.Obs;

/**
 * Interface représentant un observateur dans le cadre du pattern observateur.
 * Les classes qui implémentent cette interface doivent définir un comportement d'actualisation
 * des informations lorsque l'état du sujet (ici le jeu de la vie) change.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public interface Observateur {
    
    /**
     * Méthode appelée pour actualiser l'affichage des informations de l'observateur.
     * Cette méthode doit être implémentée pour mettre à jour les données lorsque l'état du jeu évolue.
     */
    public void actualise();
}
