package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.Cellule;
import fr.maeru.Commande.CommandeMeurt;
import fr.maeru.Commande.CommandeVit;

public class VisiteurReplicator extends Visiteur {

    public VisiteurReplicator(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiterCelluleVivante(Cellule c){
        int voisinsVivants = c.nombreVoisinesVivantes(jeu);
        if (voisinsVivants < 2 || voisinsVivants > 3) {
            jeu.ajouterCommande(new CommandeMeurt(c));
        }
    }

    @Override
    public void visiterCelluleMorte(Cellule c) {
        int voisinsVivants = c.nombreVoisinesVivantes(jeu);
        if (voisinsVivants == 3 || voisinsVivants == 2) {
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }
}