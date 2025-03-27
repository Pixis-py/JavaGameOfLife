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

    private String[] patternsDisponibles = {"Choisir un motif", "Planeur", "Canon", "Labyrinthe"};
    private JComboBox<String> patternComboBox;

    public JeuDeLaVieUI(JeuDeLaVie jeu) {
        super("Jeu De La Vie");
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
                case "Labyrinthe":
                    creerPatternLabyrinthe();
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
    
        // Définir le motif du canon de Gosper
        int startX = jeu.getxMax() / 2 - 10;
        int startY = jeu.getyMax() / 2 - 5;
    
        // Configure les cellules vivantes pour former le canon
        jeu.setGrille(startX + 1, startY + 0, new Cellule(startX + 1, startY + 0, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 1, startY + 1, new Cellule(startX + 1, startY + 1, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 2, startY + 1, new Cellule(startX + 2, startY + 1, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 1, startY + 2, new Cellule(startX + 1, startY + 2, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 2, startY + 2, new Cellule(startX + 2, startY + 2, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 3, startY + 2, new Cellule(startX + 3, startY + 2, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 4, startY + 1, new Cellule(startX + 4, startY + 1, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 5, startY + 1, new Cellule(startX + 5, startY + 1, CelluleEtatVivant.getInstance()));
        
        jeu.setGrille(startX + 0, startY + 3, new Cellule(startX + 0, startY + 3, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 1, startY + 3, new Cellule(startX + 1, startY + 3, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 2, startY + 3, new Cellule(startX + 2, startY + 3, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 3, startY + 3, new Cellule(startX + 3, startY + 3, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 4, startY + 3, new Cellule(startX + 4, startY + 3, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 5, startY + 3, new Cellule(startX + 5, startY + 3, CelluleEtatVivant.getInstance()));
        
        jeu.setGrille(startX + 3, startY + 4, new Cellule(startX + 3, startY + 4, CelluleEtatVivant.getInstance()));
        jeu.setGrille(startX + 3, startY + 5, new Cellule(startX + 3, startY + 5, CelluleEtatVivant.getInstance()));
        
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