package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

public class VisiteurHighLife extends Visiteur{
    
    public VisiteurHighLife(JeuDeLaVie jeu) {
        super(jeu);
    }
    
    @Override
    public void visiterCelluleMorte(Cellule c) {
        if(c.nombreVoisinesVivantes(jeu) == 3 || c.nombreVoisinesVivantes(jeu) == 6){
            jeu.ajouterCommande(new CommandeVit(c));
        }
    }

    @Override
    public void visiterCelluleVivante(Cellule c) {
        if(c.nombreVoisinesVivantes(jeu) > 3 || c.nombreVoisinesVivantes(jeu) < 2){
            jeu.ajouterCommande(new CommandeMeurt(c));
        }
    } 

}