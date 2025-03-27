package fr.maeru;

import fr.maeru.Obs.*;

/**
 * Classe principale de l'application qui lance le jeu de la vie.
 * Elle initialise une instance du jeu, l'interface utilisateur et les observateurs, 
 * puis démarre une boucle qui calcule les générations suivantes du jeu et met à jour l'interface.
 * 
 * Cette classe permet de simuler le jeu de la vie avec une interface console et graphique, 
 * en affichant les évolutions de la grille au fil des générations.
 * 
 * @author Maelig Pesantez
 * @version 1.0
 */
public class App 
{
    /**
     * Méthode principale qui lance l'exécution du jeu de la vie.
     * Elle crée les instances nécessaires du jeu, de l'interface utilisateur et des observateurs,
     * puis démarre une boucle infinie pour calculer les générations suivantes.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application).
     * @throws InterruptedException Si le thread est interrompu pendant le sommeil.
     */
    public static void main( String[] args ) throws InterruptedException {
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
