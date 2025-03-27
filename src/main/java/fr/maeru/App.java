package fr.maeru;

import fr.maeru.Obs.*;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        JeuDeLaVie jeu = new JeuDeLaVie(100, 100);
        JeuDeLaVieUI jeuUi = new JeuDeLaVieUI(jeu);
        ObservateurConsole consObs = new ObservateurConsole(jeu);
        jeu.attacheObservateur(jeuUi);
        jeu.attacheObservateur(consObs);
        Thread.sleep(5000);
        while(true){
            
            jeu.calculerGenerationSuivante();
            Thread.sleep(jeu.getSpeed());
            jeuUi.actualise();
        }
    }
}
