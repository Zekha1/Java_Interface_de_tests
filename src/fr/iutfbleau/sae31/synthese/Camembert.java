package fr.iutfbleau.sae31.synthese;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * La classe Camembert représente une fenêtre graphique affichant un diagramme camembert
 * basé sur les données de tests. Elle implémente l'interface BasculeListener pour permettre
 * la bascule entre deux vues différentes du diagramme.
 *
 * @author Héloïse BOUSSON
 * @version 1.0
 * @see BasculeListener
 */
public class Camembert extends JFrame implements BasculeListener {

    private DonneesTests donneesTests;
    private Map<Integer, List<CliqueEnregistreData>> clicsByTestId;
    private Map<Integer, OptionData> optionsData;
    private boolean estUnMenu = false;
    private JButton bascule;
    private String labelText = "Diagramme des actions choisies";

    /**
     * Constructeur de la classe Camembert.
     *
     * @param donneesTests   Les données de tests.
     * @param clicsByTestId  La carte des clics par ID de test.
     * @param optionsData    La carte des données d'options.
     */
    public Camembert(DonneesTests donneesTests, Map<Integer, List<CliqueEnregistreData>> clicsByTestId,
            Map<Integer, OptionData> optionsData) {
        this.donneesTests = donneesTests;
        this.clicsByTestId = clicsByTestId;
        this.optionsData = optionsData;

        bascule = new JButton("Basculer");
        bascule.addActionListener(new BasculeActionListener(this));

        // Création d'un JPanel pour contenir le camembert et le bouton
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel camembertPanel = new CamembertPanel(donneesTests, clicsByTestId, optionsData, estUnMenu, labelText);
        mainPanel.add(camembertPanel, BorderLayout.CENTER);
        mainPanel.add(bascule, BorderLayout.SOUTH);
        add(mainPanel);

        // Paramètres de la fenêtre
        setTitle("Synthèse des tests");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Méthode appelée lorsqu'une bascule est effectuée. Met à jour la vue du diagramme camembert.
     *
     * @param estUnMenu La nouvelle valeur de la propriété "estUnMenu".
     */
    @Override
    public void onBascule(boolean estUnMenu) {
        this.estUnMenu = estUnMenu;
        createCamembert(estUnMenu);
    }

    /**
     * Crée et met à jour la vue du diagramme camembert en fonction de la propriété "estUnMenu".
     *
     * @param estUnMenu La valeur de la propriété "estUnMenu".
     */
    public void createCamembert(boolean estUnMenu) {
        if (estUnMenu == false) {
            labelText = "Diagramme des actions choisies";
        } else {
            labelText = "Diagramme des sous-menus déployés";
        }

        JPanel panel = new CamembertPanel(donneesTests, clicsByTestId, optionsData, estUnMenu, labelText);
        getContentPane().removeAll();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(bascule, BorderLayout.SOUTH);
        add(mainPanel);

        revalidate();
        repaint();
    }

    /**
     * Retourne la valeur actuelle de la propriété "estUnMenu".
     *
     * @return La valeur actuelle de la propriété "estUnMenu".
     */
    @Override
    public boolean isEstUnMenu() {
        return estUnMenu;
    }
}
