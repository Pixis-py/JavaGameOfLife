package fr.maeru.Commande;

import fr.maeru.Cellule.*;

/**
 * Classe abstraite représentant une commande générique à exécuter sur une cellule.
 * Elle fait partie du design pattern Commande, qui permet de définir une interface
 * commune pour toutes les commandes spécifiques (comme CommandeVit et CommandeMeurt).
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public abstract class Commande {

    /**
     * La cellule sur laquelle l'action sera effectuée.
     */
    protected Cellule cel;

    /**
     * Méthode abstraite qui est implémentée par les classes spécialisées pour exécuter l'action spécifique.
     */
    public void executer() {}
}
