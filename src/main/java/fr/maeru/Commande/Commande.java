package fr.maeru.Commande;

import fr.maeru.Cellule.*;

/**
 * Classe abstraite représentant une commande générique à exécuter sur une cellule.
 * Elle fait partie du modèle de conception Commande, qui permet de définir une interface
 * commune pour toutes les commandes spécifiques (comme CommandeVit et CommandeMeurt).
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public abstract class Commande {

    /**
     * La cellule sur laquelle l'action sera effectuée.
     * Cette propriété est protégée pour permettre l'accès dans les classes dérivées.
     */
    protected Cellule cel;

    /**
     * Méthode abstraite qui sera implémentée par les classes dérivées pour exécuter l'action spécifique.
     * Par exemple, dans CommandeVit, cette méthode fera vivre la cellule, et dans CommandeMeurt,
     * elle fera mourir la cellule.
     */
    public void executer() {}
}
