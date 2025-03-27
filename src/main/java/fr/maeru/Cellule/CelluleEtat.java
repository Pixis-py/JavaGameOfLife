package fr.maeru.Cellule;

import fr.maeru.Visiteur.*;

public interface CelluleEtat {
    public CelluleEtat vit();
    public CelluleEtat meurt();
    public Boolean estVivante();
    public void accepte(Visiteur v, Cellule c);
}
