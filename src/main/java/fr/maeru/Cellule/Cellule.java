package fr.maeru.Cellule;

import fr.maeru.Visiteur.*;
import fr.maeru.JeuDeLaVie;

public class Cellule {

    private Integer x;
    private Integer y;
    private CelluleEtat etat;

    public Cellule(Integer x, Integer y, CelluleEtat etat){
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    public int nombreVoisinesVivantes(JeuDeLaVie jeu) {
        int count = 0;
        for (int i = this.x - 1; i <= this.x + 1; i++) {
            for (int j = this.y - 1; j <= this.y + 1; j++) {
                if (i >= 0 && i < jeu.getxMax() && j >= 0 && j < jeu.getyMax() && !(i == this.x && j == this.y)) {
                    Cellule voisine = jeu.getGrilleXY(i, j);
                    if (voisine.estVivante()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void vit(){
        etat = etat.vit();
    }

    public void meurt(){
        etat = etat.meurt();
    }

    public boolean estVivante(){
        return etat.estVivante();
    }

    public void accepte(Visiteur v){
        etat.accepte(v, this);
    }


}
