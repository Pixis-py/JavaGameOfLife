package fr.maeru.Commande;

import fr.maeru.Cellule.*;

public class CommandeMeurt extends Commande{
    public CommandeMeurt(Cellule c){
        this.cel = c;
    }
    public void executer(){
        cel.meurt();
    }
}
