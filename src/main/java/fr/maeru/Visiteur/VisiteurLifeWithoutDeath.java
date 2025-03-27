package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

public class VisiteurLifeWithoutDeath extends Visiteur {

    public VisiteurLifeWithoutDeath(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiterCelluleVivante(Cellule c) {
    }

    @Override
    public void visiterCelluleMorte(Cellule c) {
        int voisinsVivants = c.nombreVoisinesVivantes(jeu);
        if (voisinsVivants == 3) {
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }
}