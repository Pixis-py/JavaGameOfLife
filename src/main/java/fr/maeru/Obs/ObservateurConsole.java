package fr.maeru.Obs;

import fr.maeru.JeuDeLaVie;

public class ObservateurConsole implements Observateur {
    private JeuDeLaVie jeu;
    private int generation;
    

    public ObservateurConsole(JeuDeLaVie jeu) {
        this.jeu = jeu;
        this.generation = 0;
    }

    @Override
    public void actualise() {
        generation++;
        int nombreCellulesVivantes = compterCellulesVivantes();
        System.out.println("Génération : " + generation);
        System.out.println("Cellules vivantes : " + nombreCellulesVivantes);
    }

    private int compterCellulesVivantes() {
        int count = 0;
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                if (jeu.getGrilleXY(x, y).estVivante()) {
                    count++;
                }
            }
        }
        return count;
    }
}