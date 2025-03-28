package fr.maeru.Obs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.maeru.JeuDeLaVie;
import fr.maeru.Cellule.Cellule;
import fr.maeru.Cellule.CelluleEtatMort;
import fr.maeru.Cellule.CelluleEtatVivant;
import fr.maeru.Visiteur.*;

/**
 * Classe représentant l'interface graphique du Jeu de la Vie.
 * Elle permet de contrôler le jeu en offrant des fonctionnalités telles que le contrôle de la vitesse,
 * la mise en pause, la génération suivante, le choix des modes de jeu, le chargement de patterns, et le mode jour/nuit.
 * Cette classe étend {@link JFrame} et implémente l'interface {@link Observateur} pour mettre à jour l'interface
 * lors des changements d'état du jeu.
 */
public class JeuDeLaVieUI extends JFrame implements Observateur {

    // ------------------------------------------- ATTRIBUTS -------------------------------------------

    private JeuDeLaVie jeu;
    private JSlider speedSlider;
    private JButton pauseButton;
    private JButton nextGenButton;
    private JPanel controlPanel;
    
    private String[] modesDeJeu = {"Classique", "High Life", "Day and Night", "Diamoeba", "Replicator", "Life Without Death", "Chaos"};
    private JComboBox<String> modeDeJeuComboBox;

    private String[] patternsDisponibles = {"Pattern", "Planeur", "Canon", "Labyrinthe (replicator)", "Explosif (replicator)", "Replicator"};
    private JComboBox<String> patternComboBox;

    private boolean isDayMode = true; 
    private Color backgroundColor = Color.WHITE;

    private GridPanel gridPanel;

    // ------------------------------------------- CONSTRUCTEUR -------------------------------------------

    /**
     * Constructeur de la classe JeuDeLaVieUI.
     * Initialise l'interface graphique du jeu, configure les composants et ajoute les listener d'événements.
     * 
     * @param jeu L'instance du jeu de la vie à afficher dans l'interface graphique.
     */
    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        super("Jeu De La Vie");

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.jeu = jeu;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        setSize(jeu.getxMax() * 10, jeu.getyMax() * 10);

        controlPanel = new JPanel(new FlowLayout());

        patternComboBox = new JComboBox<>(patternsDisponibles);
        patternComboBox.setBackground(Color.BLACK);
        patternComboBox.setForeground(Color.WHITE);
        
        /**
         * Listener d'événement pour le JComboBox des patterns. Selon le pattern sélectionné, 
         * une méthode correspondante est appelée pour initialiser le pattern dans la grille.
         */
        patternComboBox.addActionListener(e -> {
            String selectedPattern = (String) patternComboBox.getSelectedItem();
            switch (selectedPattern) {
                case "Planeur":
                    creerPatternPlaneur();
                    break;
                case "Canon":
                    creerPatternCanon();
                    break;
                case "Labyrinthe (replicator)":
                    creerPatternLabyrinthe();
                    break;
                case "Explosif (replicator)":
                    creerPatternExplosif();
                    break;
                case "Replicator":
                    creerPatternReplicator();
                    break;
                default:
                    break;
            }
        });

        speedSlider = new JSlider(100, 1000, 250);
        speedSlider.setMajorTickSpacing(1000);
        speedSlider.setMinorTickSpacing(100);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jeu.setSpeed(speedSlider.getValue());
            }
        });

        JButton toggleButton = new JButton("NIGH / DAY");
        toggleButton.addActionListener(e -> toggleDayNightMode());
        toggleButton.setBackground(Color.BLACK);
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setFocusPainted(false);


        pauseButton = new JButton("PAUSE");
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setFocusPainted(false);

        /**
         * Listener d'événement pour le bouton de pause. Lorsque le bouton est cliqué, il bascule
         * entre l'état "Pause" et "Resume" et met à jour le texte du bouton en conséquence.
         */
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!jeu.isPause()) {
                    jeu.setPause(true);
                    pauseButton.setText("RESUME");
                } else {
                    jeu.setPause(false);
                    pauseButton.setText("PAUSE");
                }
            }
        });

        pauseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pauseButton.setBackground(Color.GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pauseButton.setBackground(Color.BLACK);
            }
        });

        nextGenButton = new JButton("NEXT GEN");
        nextGenButton.setBackground(Color.BLACK);
        nextGenButton.setForeground(Color.WHITE);
        nextGenButton.setFocusPainted(false);

        /**
         * Listener d'événement pour le bouton Next Gen. Lorsque le bouton est cliqué, il demande au jeu
         * d'avancer à la génération suivante.
         */
        nextGenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jeu.setNextGen(true);
            }
        });

        nextGenButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextGenButton.setBackground(Color.GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextGenButton.setBackground(Color.BLACK);
            }
        });

        modeDeJeuComboBox = new JComboBox<>(modesDeJeu);
        modeDeJeuComboBox.setBackground(Color.BLACK);
        modeDeJeuComboBox.setForeground(Color.WHITE);
        
        /**
         * Listener d'événement pour le JComboBox des modes de jeu. Selon le mode sélectionné, 
         * un visiteur correspondant est assigné au jeu pour changer les règles.
         */
        modeDeJeuComboBox.addActionListener(e -> {
            String selectedMode = (String) modeDeJeuComboBox.getSelectedItem();
            switch (selectedMode) {
                case "Classique":
                    jeu.setV(new VisiteurClassique(jeu));
                    break;
                case "High Life":
                    jeu.setV(new VisiteurHighLife(jeu));
                    break;
                case "Day and Night":
                    jeu.setV(new VisiteurDayAndNight(jeu));
                    break;
                case "Diamoeba":
                    jeu.setV(new VisiteurDiamoeba(jeu));
                    break;
                case "Replicator":
                    jeu.setV(new VisiteurReplicator(jeu));
                    break;
                case "Life Without Death":
                    jeu.setV(new VisiteurLifeWithoutDeath(jeu));
                    break;
                case "Chaos":
                    jeu.setV(new VisiteurChaos(jeu));
                    break;
                default:
                    break;
            }
        });

        // Ajout des composants au panel de contrôle
        controlPanel.add(new JLabel("Vitesse (ms):"));
        controlPanel.add(speedSlider);
        controlPanel.add(modeDeJeuComboBox);
        controlPanel.add(pauseButton);
        controlPanel.add(nextGenButton);
        controlPanel.add(patternComboBox);
        controlPanel.add(toggleButton, BorderLayout.SOUTH);

        gridPanel = new GridPanel();
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.SOUTH);
        
        // Redimensionnement de la fenêtre
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualise();
            }
        });

        this.setVisible(true);
    }

    // ------------------------------------------- PATTERNS -------------------------------------------
    
    /**
     * Crée un pattern de planeur (glider) sur la grille du jeu.
     * 
     * Réinitialise d'abord toutes les cellules à l'état mort, puis place les cellules vivantes
     * pour définir le pattern du planeur. Une fois le pattern placé, l'affichage est mis à jour.
     */
    private void creerPatternPlaneur() {

        int startX = jeu.getxMax() / 2;
        int startY = jeu.getyMax() / 2;

        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }

        jeu.setGrille(startX, startY, new Cellule(startX, startY, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 1, startY, new Cellule(startX + 1, startY, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 2, startY, new Cellule(startX + 2, startY, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 2, startY + 1, new Cellule(startX + 2, startY + 1, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 1, startY + 2, new Cellule(startX + 1, startY + 2, CelluleEtatVivant.getInstance()));

        actualise();
    }

    /**
     * Crée un pattern de canon de Gosper sur la grille du jeu.
     * 
     * Réinitialise la grille, puis place les cellules vivantes selon un modèle défini.
     * Une fois le pattern placé, l'affichage est mis à jour.
     */
    private void creerPatternCanon() {

        int startX = 10;
        int startY = 5;

        int[][] pattern = {
            {0, 4}, {0, 5}, {1, 4}, {1, 5},
            {10, 4}, {10, 5}, {10, 6}, {11, 3}, {11, 7}, {12, 2}, {12, 8}, {13, 2}, {13, 8}, {14, 5}, {15, 3}, {15, 7},
            {16, 4}, {16, 5}, {16, 6}, {17, 5},
            {20, 2}, {20, 3}, {20, 4}, {21, 2}, {21, 3}, {21, 4}, {22, 1}, {22, 5}, {24, 0}, {24, 1}, {24, 5}, {24, 6},
            {34, 2}, {34, 3}, {35, 2}, {35, 3}
        };

        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }

        for (int[] cell : pattern) {
            int x = startX + cell[0];
            int y = startY + cell[1];
            jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatVivant.getInstance()));
        }

        actualise();
    }

    /**
     * Crée un pattern explosif sur la grille du jeu.
     * 
     * Après avoir réinitialisé la grille, la méthode place les cellules vivantes de l'explosion au centre de la grille.
     * L'affichage est ensuite mis à jour.
     */
    private void creerPatternExplosif() {

        int startX = jeu.getxMax() / 2;
        int startY = jeu.getyMax() / 2;
        int[][] pattern = {
            {0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}, 
            {2, 0}, {-2, 0}, {0, 2}, {0, -2}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1},
            {3, 0}, {-3, 0}, {0, 3}, {0, -3}
        };

        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }

        for (int[] cell : pattern) {
            int x = startX + cell[0];
            int y = startY + cell[1];
            if (x >= 0 && x < jeu.getxMax() && y >= 0 && y < jeu.getyMax()) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatVivant.getInstance()));
            }
        }

        actualise();
    }

    /**
     * Crée un pattern de replicator sur la grille du jeu.
     * 
     * Après la réinitialisation de la grille, les cellules vivantes du replicator sont placées sur la grille.
     * L'affichage est mis à jour.
     */
    private void creerPatternReplicator() {

        int startX = jeu.getxMax() / 2;
        int startY = jeu.getyMax() / 2;

        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }

        int[][] pattern = {
            {0, 0}, {1, 1}, {2, 1}, {2, 0}, {1, -1}
        };

        for (int[] cell : pattern) {
            int x = startX + cell[0];
            int y = startY + cell[1];
            if (x >= 0 && x < jeu.getxMax() && y >= 0 && y < jeu.getyMax()) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatVivant.getInstance()));
            }
        }

        actualise();
    }

    /**
     * Crée un pattern de labyrinthe simple sur la grille du jeu.
     * 
     * La grille est d'abord réinitialisée, puis les cellules vivantes sont placées pour définir le chemin et les barrières du labyrinthe.
     * L'affichage est ensuite mis à jour.
     */
    private void creerPatternLabyrinthe() {

        int startX = jeu.getxMax() / 2 - 5;
        int startY = jeu.getyMax() / 2 - 5;

        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }

        for (int i = 0; i < 10; i++) {
            jeu.setGrille(startX + 0, startY + i, new Cellule(startX + 0, startY + i, CelluleEtatVivant.getInstance())); // Colonne 1
            jeu.setGrille(startX + 1, startY + i, new Cellule(startX + 1, startY + i, CelluleEtatVivant.getInstance())); // Colonne 2
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 10; i++) {
                jeu.setGrille(startX + i, startY + 4 + j, new Cellule(startX + i, startY + 4 + j, CelluleEtatVivant.getInstance())); // Deux barrières au milieu
            }
        }

        actualise();
    }

    // ------------------------------------------- AFFICHAGE -------------------------------------------

    /**
     * Bascule entre le mode jour et nuit.
     * Change les couleurs de fond et des cellules.
     */
    private void toggleDayNightMode() {

        isDayMode = !isDayMode; 

        if (!isDayMode) {
            backgroundColor = Color.BLACK;
        } else {
            backgroundColor = Color.WHITE;
        }
        gridPanel.repaint();
    }


    /**
     * Actualise l'affichage de la grille en redessinant le panel de la grille.
     * Cette méthode est appelée pour rafraîchir l'affichage lors de chaque mise à jour.
     */
    @Override
    public void actualise() {
        gridPanel.repaint();
    }

    /**
     * Panel de la grille représentant l'état actuel.
     * Ce panel affiche la grille.
     */
    private class GridPanel extends JPanel {
        
        /**
         * Méthode qui affiche la grille sur le panel.
         * Cette méthode est appelée chaque fois que le panel doit être actualisé.
         *
         * @param g L'objet Graphics utilisé pour dessiner sur le panel.
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int cellSize = 6;
            
            int maxGridWidth = (int) (getWidth());
            int maxGridHeight = (int) (getHeight());
            
            int gridWidth = Math.min(maxGridWidth, jeu.getxMax() * cellSize);
            int gridHeight = Math.min(maxGridHeight, jeu.getyMax() * cellSize);
            
            g.setColor(backgroundColor);
            g.fillRect(0, 0, getWidth(), getHeight());
            
            int offsetX = (getWidth() - gridWidth) / 2;
            int offsetY = (getHeight() - gridHeight) / 3;

            for (int x = 0; x < jeu.getxMax(); x++) {
                for (int y = 0; y < jeu.getyMax(); y++) {
                    
                    if (jeu.getGrilleXY(x, y).estVivante()) {
                        g.setColor(Color.GREEN);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    
                    if (x * cellSize < gridWidth && y * cellSize < gridHeight) {
                        g.fillRect(offsetX + x * cellSize, offsetY + y * cellSize, cellSize, cellSize);
                        
                        g.setColor(Color.GRAY);
                        g.drawRect(offsetX + x * cellSize, offsetY + y * cellSize, cellSize, cellSize);
                    }
                }
            }
        }
    }
}
