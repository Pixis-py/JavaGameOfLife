package fr.maeru.Visiteur;

import fr.maeru.Commande.*;
import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;

import java.util.Random;

public class VisiteurChaos extends Visiteur {

    private static final Random random = new Random();

    public VisiteurChaos(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiterCelluleVivante(Cellule cellule) {
        int nbVoisins = cellule.nombreVoisinesVivantes(jeu);
        if (random.nextDouble() < 0.07) {
            jeu.ajouterCommande(new CommandeMeurt(cellule));
            return;
        }
        if (nbVoisins < 2 || nbVoisins > 4) {
            jeu.ajouterCommande(new CommandeMeurt(cellule));
        }
    }

    @Override
    public void visiterCelluleMorte(Cellule cellule) {
        int nbVoisins = cellule.nombreVoisinesVivantes(jeu);
        if (random.nextDouble() < 0.07) {
            jeu.ajouterCommande(new CommandeVit(cellule));
            return;
        }
        if (nbVoisins == 3 || random.nextInt(10) == cellule.hashCode() % 10) {
            jeu.ajouterCommande(new CommandeVit(cellule));
        }
    }
    
}