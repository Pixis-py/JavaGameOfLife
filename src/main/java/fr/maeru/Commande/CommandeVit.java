package fr.maeru.Commande;

import fr.maeru.Cellule.*;

public class CommandeVit extends Commande{
    public CommandeVit(Cellule c){
        this.cel = c;
    }
    public void executer(){
        cel.vit();
    }
}
