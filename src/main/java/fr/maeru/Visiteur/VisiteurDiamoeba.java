package fr.maeru.Visiteur;

import java.util.Random;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

public class VisiteurDiamoeba extends Visiteur {

    private Random random;

    public VisiteurDiamoeba(JeuDeLaVie jeu) {
        super(jeu);
        this.random = new Random();
    }

    @Override
    public void visiterCelluleVivante(Cellule c) {
        if (random.nextBoolean()) {
            jeu.ajouterCommande(new CommandeMeurt(c));
        }
    }

    @Override
    public void visiterCelluleMorte(Cellule c) {
        if (random.nextInt(100) < 10) {
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }
}