package fr.maeru.Cellule;

import fr.maeru.Visiteur.*;

public class CelluleEtatVivant implements CelluleEtat {

    private static CelluleEtatVivant inst = null;
    private CelluleEtatVivant(){}

    @Override
    public CelluleEtat vit() {
        return this;
    }

    @Override
    public CelluleEtat meurt() {
        return CelluleEtatMort.getInstance();
    }

    @Override
    public Boolean estVivante() {
        return true;
    }

    public static CelluleEtatVivant getInstance(){
        if(inst == null){
            inst = new CelluleEtatVivant();
        }
        return inst;
    }
    
    public void accepte(Visiteur v, Cellule c){
        v.visiterCelluleVivante(c);
    }

}
