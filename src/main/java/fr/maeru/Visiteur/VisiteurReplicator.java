package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.Cellule;
import fr.maeru.Commande.CommandeMeurt;
import fr.maeru.Commande.CommandeVit;

/**
 * Classe représentant un visiteur avec la règle du jeu Replicator pour le jeu de la vie.
 * Dans cette règle, les cellules vivantes meurent si elles ont moins de 2 voisins vivants ou plus de 3 voisins vivants,
 * et les cellules mortes deviennent vivantes si elles ont exactement 2 ou 3 voisins vivants.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class VisiteurReplicator extends Visiteur {

    /**
     * Constructeur de la classe VisiteurReplicator.
     * 
     * @param jeu L'instance du jeu de la vie sur lequel le visiteur opère.
     */
    public VisiteurReplicator(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Méthode qui visite une cellule vivante et applique la règle Replicator :
     * Une cellule vivante meurt si elle a moins de 2 voisins vivants ou plus de 3 voisins vivants.
     * 
     * @param c La cellule vivante à visiter.
     */
    @Override
    public void visiterCelluleVivante(Cellule c) {
        int voisinsVivants = c.nombreVoisinesVivantes(jeu);
        if (voisinsVivants < 2 || voisinsVivants > 3) {
            jeu.ajouterCommande(new CommandeMeurt(c));
        }
    }

    /**
     * Méthode qui visite une cellule morte et applique la règle Replicator :
     * Une cellule morte devient vivante si elle a exactement 2 ou 3 voisins vivants.
     * 
     * @param c La cellule morte à visiter et potentiellement faire renaître.
     */
    @Override
    public void visiterCelluleMorte(Cellule c) {
        int voisinsVivants = c.nombreVoisinesVivantes(jeu);
        if (voisinsVivants == 3 || voisinsVivants == 2) {
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }
}
