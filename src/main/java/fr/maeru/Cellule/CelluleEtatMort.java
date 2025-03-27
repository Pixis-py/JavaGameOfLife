package fr.maeru.Cellule;

import fr.maeru.Visiteur.*;

public class CelluleEtatMort implements CelluleEtat {

    private static CelluleEtatMort inst = null;
    private CelluleEtatMort(){}

    @Override
    public CelluleEtat vit() {
        return CelluleEtatVivant.getInstance();
    }

    @Override
    public CelluleEtat meurt() {
        return this;
    }

    @Override
    public Boolean estVivante() {
        return false;
    }

    public static CelluleEtatMort getInstance(){
        if(inst == null){
            inst = new CelluleEtatMort();
        }
        return inst;
    }

    public void accepte(Visiteur v, Cellule c){
        v.visiterCelluleMorte(c);
    }
    
}
