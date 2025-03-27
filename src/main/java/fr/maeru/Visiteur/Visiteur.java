package fr.maeru.Visiteur;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.*;

public abstract class Visiteur {

    protected JeuDeLaVie jeu;

    public Visiteur(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    public void visiterCelluleVivante(Cellule c){}
    public void visiterCelluleMorte(Cellule c){}

}
