package fr.iutfbleau.sae31.outil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ButtonListener est un écouteur d'événements utilisé pour gérer les actions liées à un bouton.
 */
public class ButtonListener implements ActionListener {
    private JFrame fenetre;
    private ArbreListener al;
    private Protocole protocole;

    /**
     * Constructeur de la classe ButtonListener.
     *
     * @param fenetre La fenêtre à laquelle le bouton est associé.
     * @param al L'écouteur d'événements associé à l'arbre d'options.
     * @param protocole Le protocole associé à l'arbre d'options.
     */
    public ButtonListener(JFrame fenetre, ArbreListener al, Protocole protocole) {
        this.fenetre=fenetre;
        this.al=al;
        this.protocole=protocole;
    }

    /**
     * Méthode appelée lorsqu'un bouton est cliqué.
     *
     * @param e L'événement de clic sur le bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        TestFini tf=new TestFini(al.getOptionLinkedList(),this.protocole);
        tf.ajoutBase();
        this.fenetre.dispose();
    }
}
