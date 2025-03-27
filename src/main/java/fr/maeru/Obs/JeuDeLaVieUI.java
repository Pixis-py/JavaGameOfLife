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

public class JeuDeLaVieUI extends JFrame implements Observateur {

    private JeuDeLaVie jeu;
    private JSlider speedSlider;
    private JButton pauseButton;
    private JButton nextGenButton;
    private JPanel controlPanel;
    
    private String[] modesDeJeu = {"Classique", "High Life", "Day and Night", "Diamoeba", "Replicator", "Life Without Death", "Chaos"};
    private JComboBox<String> modeDeJeuComboBox;
    private GridPanel gridPanel;

    private String[] patternsDisponibles = {"Pattern", "Planeur", "Canon", "Labyrinthe (replicator)", "Explosif (replicator)", "Replicator"};
    private JComboBox<String> patternComboBox;

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

        // Initialiser le JComboBox pour les motifs
        patternComboBox = new JComboBox<>(patternsDisponibles);
        patternComboBox.setBackground(Color.BLACK);
        patternComboBox.setForeground(Color.WHITE);
        
        
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

        pauseButton = new JButton("PAUSE");
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setFocusPainted(false);

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

        controlPanel.add(new JLabel("Vitesse (ms):"));
        controlPanel.add(speedSlider);
        controlPanel.add(modeDeJeuComboBox);
        controlPanel.add(pauseButton);
        controlPanel.add(nextGenButton);
        controlPanel.add(patternComboBox); // Ajout du JComboBox

        gridPanel = new GridPanel();
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.SOUTH);
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualise();
            }
        });

        this.setVisible(true);
    }

    @Override
    public void actualise() {
        gridPanel.repaint();
    }

    private void creerPatternPlaneur() {
        // Réinitialiser la grille
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }

        // Définir le motif de planeur
        int startX = jeu.getxMax() / 2;
        int startY = jeu.getyMax() / 2;

        jeu.setGrille(startX, startY, new Cellule(startX, startY, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 1, startY, new Cellule(startX + 1, startY, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 2, startY, new Cellule(startX + 2, startY, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 2, startY + 1, new Cellule(startX + 2, startY + 1, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 1, startY + 2, new Cellule(startX + 1, startY + 2, CelluleEtatVivant.getInstance()));

        actualise();
    }

    private void creerPatternCanon() {
        // Réinitialiser la grille
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }
    
        // Coordonnées de départ du canon
        int startX = 10; // Ajuste selon la taille de ta grille
        int startY = 5;  // Ajuste selon la taille de ta grille
    
        // Définition des cellules vivantes du canon de Gosper
        int[][] pattern = {
            {0, 4}, {0, 5}, {1, 4}, {1, 5},
            {10, 4}, {10, 5}, {10, 6}, {11, 3}, {11, 7}, {12, 2}, {12, 8}, {13, 2}, {13, 8}, {14, 5}, {15, 3}, {15, 7},
            {16, 4}, {16, 5}, {16, 6}, {17, 5},
            {20, 2}, {20, 3}, {20, 4}, {21, 2}, {21, 3}, {21, 4}, {22, 1}, {22, 5}, {24, 0}, {24, 1}, {24, 5}, {24, 6},
            {34, 2}, {34, 3}, {35, 2}, {35, 3}
        };
    
        // Placer les cellules vivantes sur la grille
        for (int[] cell : pattern) {
            int x = startX + cell[0];
            int y = startY + cell[1];
            jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatVivant.getInstance()));
        }
    
        // Notifier l'affichage
        actualise();
    }
    
    private void creerPatternExplosif() {
        // Réinitialiser la grille
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }
    
        // Position de départ de l'explosion
        int startX = jeu.getxMax() / 2;
        int startY = jeu.getyMax() / 2;
    
        // Définir les cellules vivantes pour l'explosion
        int[][] pattern = {
            {0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1},  // Centre + voisins directs
            {2, 0}, {-2, 0}, {0, 2}, {0, -2}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}, // Explosion
            {3, 0}, {-3, 0}, {0, 3}, {0, -3}
        };
    
        // Placer les cellules vivantes sur la grille
        for (int[] cell : pattern) {
            int x = startX + cell[0];
            int y = startY + cell[1];
            if (x >= 0 && x < jeu.getxMax() && y >= 0 && y < jeu.getyMax()) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatVivant.getInstance()));
            }
        }
    
        // Notifier l'affichage
        actualise();
    }
    
    private void creerPatternReplicator() {
        // Réinitialiser la grille
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }
    
        // Position de départ du replicator
        int startX = jeu.getxMax() / 2;
        int startY = jeu.getyMax() / 2;
    
        // Définir le pattern de Replicator
        int[][] pattern = {
            {0, 0}, {1, 1}, {2, 1}, {2, 0}, {1, -1}
        };
    
        // Placer les cellules vivantes sur la grille
        for (int[] cell : pattern) {
            int x = startX + cell[0];
            int y = startY + cell[1];
            if (x >= 0 && x < jeu.getxMax() && y >= 0 && y < jeu.getyMax()) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatVivant.getInstance()));
            }
        }
    
        // Notifier l'affichage
        actualise();
    }

    private void creerPatternLabyrinthe() {
        // Réinitialiser la grille
        for (int x = 0; x < jeu.getxMax(); x++) {
            for (int y = 0; y < jeu.getyMax(); y++) {
                jeu.setGrille(x, y, new Cellule(x, y, CelluleEtatMort.getInstance()));
            }
        }
    
        // Définir un motif de labyrinthe simple
        int startX = jeu.getxMax() / 2 - 5;
        int startY = jeu.getyMax() / 2 - 5;
    
        // Exemple d'un chemin simple dans le labyrinthe
        for (int i = 0; i < 10; i++) {
            jeu.setGrille(startX + 0, startY + i, new Cellule(startX + 0, startY + i, CelluleEtatVivant.getInstance())); // Colonne 1
            jeu.setGrille(startX + 1, startY + i, new Cellule(startX + 1, startY + i, CelluleEtatVivant.getInstance())); // Colonne 2
        }
    
        // Barrières du labyrinthe
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 10; i++) {
                jeu.setGrille(startX + i, startY + 4 + j, new Cellule(startX + i, startY + 4 + j, CelluleEtatVivant.getInstance())); // Deux barrières au milieu
            }
        }
    
        // Notifier l'affichage
        actualise();
    }

    private class GridPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int cellSize = 5;
            int maxGridWidth = (int) (getWidth() * 0.7);
            int maxGridHeight = (int) (getHeight() * 0.7);
            
            int gridWidth = Math.min(maxGridWidth, jeu.getxMax() * cellSize);
            int gridHeight = Math.min(maxGridHeight, jeu.getyMax() * cellSize);
            
            g.setColor(Color.WHITE);
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