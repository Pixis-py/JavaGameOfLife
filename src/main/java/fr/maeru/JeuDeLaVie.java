package fr.maeru;

import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;
import fr.maeru.Obs.Observable;
import fr.maeru.Obs.Observateur;
import fr.maeru.Visiteur.*;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * Représente le jeu de la vie, un automate cellulaire où chaque cellule peut être vivante ou morte.
 * Cette classe gère l'état des cellules, l'évolution des générations, ainsi que la gestion des observateurs
 * et des commandes. Elle permet aussi la distribution de visiteurs pour effectuer des actions sur les cellules.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class JeuDeLaVie implements Observable {

    private Cellule[][] grille;  // Grille représentant l'état des cellules
    private Integer xMax;  // Largeur de la grille
    private Integer yMax;  // Hauteur de la grille
    private List<Observateur> obs = new ArrayList<>();  // Liste des observateurs
    private List<Commande> commandes = new ArrayList<>();  // Liste des commandes à exécuter
    private Visiteur v;  // Visiteur utilisé pour appliquer des actions sur les cellules
    private int speed = 250;  // Vitesse d'exécution du jeu
    private boolean pause;  // Indique si le jeu est en pause
    private boolean nextGen;  // Indique si la génération suivante doit être calculée

    /**
     * Constructeur de la classe {@link JeuDeLaVie}.
     * 
     * @param xm Largeur de la grille.
     * @param ym Hauteur de la grille.
     */
    public JeuDeLaVie(Integer xm, Integer ym) {
        this.v = new VisiteurClassique(this);
        this.xMax = xm;
        this.yMax = ym;
        this.grille = new Cellule[xMax][yMax];
        this.pause = false;
        this.nextGen = false;
        initialiseGrille();
    }

    /**
     * Initialise la grille en remplissant chaque cellule avec un état aléatoire (vivant ou mort).
     * La probabilité qu'une cellule soit vivante est de 10%.
     */
    public void initialiseGrille() {
        Integer i, j;

        for (i = 0; i < xMax; i++) {
            for (j = 0; j < yMax; j++) {
                if (Math.random() <= 0.1) {
                    grille[i][j] = new Cellule(i, j, CelluleEtatVivant.getInstance());
                } else {
                    grille[i][j] = new Cellule(i, j, CelluleEtatMort.getInstance());
                }
            }
        }
    }

    /**
     * Retourne la largeur de la grille.
     * 
     * @return La largeur de la grille.
     */
    public Integer getxMax() {
        return xMax;
    }

    /**
     * Retourne la hauteur de la grille.
     * 
     * @return La hauteur de la grille.
     */
    public Integer getyMax() {
        return yMax;
    }

    /**
     * Retourne la grille des cellules.
     * 
     * @return La grille des cellules.
     */
    public Cellule[][] getGrille() {
        return grille;
    }

    /**
     * Modifie une cellule dans la grille.
     * 
     * @param x La position x de la cellule.
     * @param y La position y de la cellule.
     * @param c La cellule à insérer.
     */
    public void setGrille(int x, int y, Cellule c) {
        this.grille[x][y] = c;
    }

    /**
     * Vérifie si la génération suivante doit être calculée.
     * 
     * @return true si la génération suivante doit être calculée, sinon false.
     */
    public boolean isNextGen() {
        return nextGen;
    }

    /**
     * Définit si la génération suivante doit être calculée.
     * 
     * @param nextGen true si la génération suivante doit être calculée, sinon false.
     */
    public void setNextGen(boolean nextGen) {
        this.nextGen = nextGen;
    }

    /**
     * Vérifie si le jeu est en pause.
     * 
     * @return true si le jeu est en pause, sinon false.
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * Définit l'état de pause du jeu.
     * 
     * @param pause true si le jeu doit être mis en pause, sinon false.
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * Retourne une cellule à une position spécifique dans la grille.
     * 
     * @param x La position x de la cellule.
     * @param y La position y de la cellule.
     * @return La cellule à la position spécifiée.
     */
    public Cellule getGrilleXY(Integer x, Integer y) {
        return grille[x][y];
    }

    /**
     * Attache un observateur à la liste des observateurs.
     * 
     * @param o L'observateur à attacher.
     */
    @Override
    public void attacheObservateur(Observateur o) {
        obs.add(o);
    }

    /**
     * Détache un observateur de la liste des observateurs.
     * 
     * @param o L'observateur à détacher.
     */
    @Override
    public void detacheObservateur(Observateur o) {
        obs.remove(o);
    }

    /**
     * Notifie tous les observateurs attachés pour mettre à jour leur état.
     */
    @Override
    public void notifieObservateur() {
        for (Observateur o : obs) {
            o.actualise();
        }
    }

    /**
     * Ajoute une commande à la liste des commandes à exécuter.
     * 
     * @param c La commande à ajouter.
     */
    public void ajouterCommande(Commande c) {
        commandes.add(c);
    }

    /**
     * Exécute toutes les commandes ajoutées à la liste des commandes.
     */
    public void executeCommandes() {
        for (Commande commande : commandes) {
            commande.executer();
        }
        commandes.removeAll(commandes);
    }

    /**
     * Distribue le visiteur à toutes les cellules de la grille.
     */
    public void distribueVisiteur() {
        Integer i, j;
        for (i = 0; i < xMax; i++) {
            for (j = 0; j < yMax; j++) {
                this.getGrilleXY(i, j).accepte(v);
            }
        }
    }

    /**
     * Calcule la génération suivante des cellules selon les règles du jeu.
     * La génération suivante est calculée si le jeu n'est pas en pause,
     * ou si le jeu est en pause et qu'une nouvelle génération est demandée.
     */
    public void calculerGenerationSuivante() {
        if (!pause) {
            distribueVisiteur();
            executeCommandes();
            notifieObservateur();
        }
        if (pause && nextGen) {
            distribueVisiteur();
            executeCommandes();
            notifieObservateur();
            nextGen = false;
        }
    }

    /**
     * Définit la vitesse d'exécution du jeu.
     * 
     * @param speed La vitesse d'exécution.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Retourne la vitesse d'exécution du jeu.
     * 
     * @return La vitesse d'exécution.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Retourne le visiteur actuel utilisé pour effectuer des actions sur les cellules.
     * 
     * @return Le visiteur actuel.
     */
    public Visiteur getV() {
        return v;
    }

    /**
     * Définit un nouveau visiteur pour effectuer des actions sur les cellules.
     * 
     * @param v Le visiteur à définir.
     */
    public void setV(Visiteur v) {
        this.v = v;
    }
}
