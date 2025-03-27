package fr.maeru.Obs;

/**
 * Interface représentant un sujet observable dans le cadre du pattern observateur.
 * Les objets qui implémentent cette interface peuvent ajouter, retirer et notifier des observateurs
 * lorsqu'un changement d'état se produit.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public interface Observable {
    
    /**
     * Attache un observateur à l'objet observable.
     * L'observateur sera notifié lorsque l'état de l'objet observé changera.
     * 
     * @param o L'observateur à attacher.
     */
    public void attacheObservateur(Observateur o);
    
    /**
     * Détache un observateur de l'objet observable.
     * L'observateur ne sera plus notifié lors de changements d'état.
     * 
     * @param o L'observateur à détacher.
     */
    public void detacheObservateur(Observateur o);
    
    /**
     * Notifie tous les observateurs attachés de l'objet observable que l'état a changé.
     * Cette méthode est appelée lorsque l'objet observé subit un changement qui doit
     * être communiqué aux observateurs.
     */
    public void notifieObservateur();
}
