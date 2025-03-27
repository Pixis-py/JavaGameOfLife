package fr.maeru.Commande;

import fr.maeru.Cellule.*;

/**
 * CommandeVit représente une commande qui déclenche l'action "vit" sur une cellule spécifiée.
 * Cette classe fait partie du design pattern Commande, permettant d'encapsuler l'action à exécuter
 * sur une cellule.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class CommandeVit extends Commande {

    /**
     * La cellule sur laquelle l'action "vit" sera exécutée.
     */
    private Cellule cel;

    /**
     * Constructeur pour créer une instance de CommandeVit avec la cellule spécifiée.
     * 
     * @param c La cellule sur laquelle l'action sera effectuée.
     */
    public CommandeVit(Cellule c) {
        this.cel = c;
    }

    /**
     * Exécute l'action "vit" sur la cellule spécifiée.
     * Cette méthode déclenche l'action de vie de la cellule, permettant ainsi
     * à la cellule de passer à l'état vivant.
     */
    public void executer() {
        cel.vit();
    }
}
