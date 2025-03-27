package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;

public class VisiteurClassique extends Visiteur{

    public VisiteurClassique(JeuDeLaVie jeu) {
        super(jeu);
    }
    
    @Override
    public void visiterCelluleMorte(Cellule c) {
        if(c.nombreVoisinesVivantes(jeu) == 3){
            jeu.ajouterCommande(new CommandeVit(c));
            System.out.println("Naissance : " + c.nombreVoisinesVivantes(jeu));
        } else{
            if(c.nombreVoisinesVivantes(jeu) > 0){
            System.out.println("IDLE : " + c.nombreVoisinesVivantes(jeu));
            }
        }
    }

    @Override
    public void visiterCelluleVivante(Cellule c) {
        if(c.nombreVoisinesVivantes(jeu) > 3 || c.nombreVoisinesVivantes(jeu) < 2){
            jeu.ajouterCommande(new CommandeMeurt(c));
        }else{
            if(c.nombreVoisinesVivantes(jeu) > 0){
            }
        }
    } 
    
}
