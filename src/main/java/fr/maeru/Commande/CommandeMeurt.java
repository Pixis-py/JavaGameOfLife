package fr.maeru.Commande;

import fr.maeru.Cellule.*;

/**
 * CommandeMeurt représente une commande qui déclenche l'action "meurt" sur une cellule spécifiée.
 * Cette classe fait partie du design pattern Commande, permettant d'encapsuler l'action de mort à exécuter
 * sur une cellule.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class CommandeMeurt extends Commande {

    /**
     * La cellule sur laquelle l'action "meurt" sera exécutée.
     */
    private Cellule cel;

    /**
     * Constructeur pour créer une instance de CommandeMeurt avec la cellule spécifiée.
     * 
     * @param c La cellule sur laquelle l'action sera effectuée.
     */
    public CommandeMeurt(Cellule c) {
        this.cel = c;
    }

    /**
     * Exécute l'action "meurt" sur la cellule spécifiée.
     */
    public void executer() {
        cel.meurt();
    }
}
