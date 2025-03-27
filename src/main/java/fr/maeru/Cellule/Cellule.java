package fr.maeru.Cellule;

import fr.maeru.Visiteur.*;
import fr.maeru.JeuDeLaVie;

/**
 * La classe Cellule représente une cellule dans le jeu de la vie. Chaque cellule a une position
 * (x, y) et un état (vivant ou mort). Cette classe contient des méthodes pour gérer son état et
 * interagir avec ses voisines dans la grille du jeu.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class Cellule {

    /**
     * La position x de la cellule dans la grille.
     */
    private Integer x;

    /**
     * La position y de la cellule dans la grille.
     */
    private Integer y;

    /**
     * L'état actuel de la cellule, qui peut être vivant ou mort.
     */
    private CelluleEtat etat;

    /**
     * Constructeur de la cellule avec les coordonnées (x, y) et un état initial.
     * 
     * @param x La position x de la cellule dans la grille.
     * @param y La position y de la cellule dans la grille.
     * @param etat L'état initial de la cellule (vivant ou mort).
     */
    public Cellule(Integer x, Integer y, CelluleEtat etat) {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    /**
     * Calcule et retourne le nombre de voisines vivantes de la cellule.
     * 
     * @param jeu L'instance du jeu de la vie, utilisée pour accéder à la grille de cellules.
     * @return Le nombre de cellules voisines vivantes.
     */
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

    /**
     * Change l'état de la cellule pour la rendre vivante.
     */
    public void vit() {
        etat = etat.vit();
    }

    /**
     * Change l'état de la cellule pour la rendre morte.
     */
    public void meurt() {
        etat = etat.meurt();
    }

    /**
     * Vérifie si la cellule est vivante.
     * 
     * @return true si la cellule est vivante, false sinon.
     */
    public boolean estVivante() {
        return etat.estVivante();
    }

    /**
     * Permet à un visiteur d'interagir avec la cellule. Cette méthode utilise le design pattern
     * **Visitor** pour déléguer l'interaction avec l'état de la cellule.
     * 
     * @param v Le visiteur qui va interagir avec la cellule.
     */
    public void accepte(Visiteur v) {
        etat.accepte(v, this);
    }
}
