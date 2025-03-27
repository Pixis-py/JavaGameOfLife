package fr.maeru;

import fr.maeru.Cellule.*;
import fr.maeru.Commande.*;
import fr.maeru.Obs.Observable;
import fr.maeru.Obs.Observateur;
import fr.maeru.Visiteur.*;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class JeuDeLaVie implements Observable{

    private Cellule[][] grille;
    private Integer xMax;
    private Integer yMax;
    private List<Observateur> obs = new ArrayList<>();
    private List<Commande> commandes = new ArrayList<>();
    private Visiteur v;
    private int speed = 250;
    private boolean pause;
    private boolean nextGen;

    public JeuDeLaVie(Integer xm, Integer ym){
        this.v = new VisiteurClassique(this);
        this.xMax = xm;
        this.yMax = ym;
        this.grille = new Cellule[xMax][yMax];
        this.pause = false;
        this.nextGen = false;
        initialiseGrille();
    }

    public void initialiseGrille(){

        Integer i, j;

        for(i=0; i<xMax; i++){
            for(j=0; j<yMax; j++){
                if(Math.random() <= 0.1){
                    grille[i][j] = new Cellule(i, j, CelluleEtatVivant.getInstance());
                }
                else{
                    grille[i][j] = new Cellule(i, j, CelluleEtatMort.getInstance());
                }
            }
        }

    } 

    public Integer getxMax() {
        return xMax;
    }

    public Integer getyMax() {
        return yMax;
    }

    public Cellule[][] getGrille() {
        return grille;
    }

    public void setGrille(int x, int y, Cellule c) {
        this.grille[x][y] = c;
    }

    public boolean isNextGen() {
        return nextGen;
    }

    public void setNextGen(boolean nextGen) {
        this.nextGen = nextGen;
    }


    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public Cellule getGrilleXY(Integer x, Integer y){
        return grille[x][y];
    }

    @Override
    public void attacheObservateur(Observateur o) {
        obs.add(o);
    }

    @Override
    public void detacheObservateur(Observateur o) {
        obs.remove(o);
    }

    @Override
    public void notifieObservateur() {
        for(Observateur o : obs){
            o.actualise();
        }
    }

    public void ajouterCommande(Commande c){
        commandes.add(c);
    }

    public void executeCommandes(){
        for(Commande commande: commandes){
            commande.executer();
        }
        commandes.removeAll(commandes);
    }

    public void distribueVisiteur(){
        Integer i, j;
        for(i=0; i<xMax; i++){
            for(j=0; j<yMax; j++){
                this.getGrilleXY(i, j).accepte(v);
            }
        }
    }

    public void calculerGenerationSuivante(){
        if(!pause){
            distribueVisiteur();
            executeCommandes();
            notifieObservateur();
        }
        if(pause && nextGen){
            distribueVisiteur();
            executeCommandes();
            notifieObservateur();
            nextGen = false;
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Visiteur getV() {
        return v;
    }

    public void setV(Visiteur v) {
        this.v = v;
    }

}