package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

public class VisiteurDayAndNight extends Visiteur {

    public VisiteurDayAndNight(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiterCelluleMorte(Cellule c) {
        int nbVoisinesVivantes = c.nombreVoisinesVivantes(jeu);
        if (nbVoisinesVivantes == 3 || nbVoisinesVivantes == 6 || nbVoisinesVivantes == 8) {
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }

    @Override
    public void visiterCelluleVivante(Cellule c) {
        int nbVoisinesVivantes = c.nombreVoisinesVivantes(jeu);
        if (nbVoisinesVivantes < 2 || nbVoisinesVivantes > 3) {
            jeu.ajouterCommande(new CommandeMeurt(c));
        }
    }
}