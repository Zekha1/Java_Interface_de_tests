package fr.iutfbleau.sae31.outil;

import javax.swing.*;
import java.awt.*;

/**
 * La classe Menu représente la fenêtre principale de l'application, affichant un arbre d'options.
 */
public class Menu extends JFrame {
    /**
     * Constructeur de la classe Menu.
     *
     * @param arbre L'arbre d'options à afficher dans la fenêtre.
     * @param protocole Le protocole associé à l'arbre d'options.
     */
    public Menu(JTree arbre,Protocole protocole){
        super();

//      Configuration de la fenêtre
        this.setMinimumSize(new Dimension(250,400));
        this.setSize(250,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//      Ajout d'un listener sur l'arbre
        ArbreListener la= new ArbreListener();
        arbre.addTreeSelectionListener(la);
        this.add(arbre);

//      Ajout du bouton de confirmation
        JPanel pano=new JPanel();
        pano.setBackground(new Color(34, 109, 34));
        JButton bouton = new JButton("Si vous avez terminé,\nappuyez ici");
        ButtonListener bl = new ButtonListener(this,la,protocole);
        bouton.addActionListener(bl);
        pano.add(bouton,BorderLayout.CENTER);
        this.add(pano,BorderLayout.SOUTH);

//      Rend la fenêtre visible
        this.setVisible(true);
    }
}