package fr.iutfbleau.sae31.synthese;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe BasculeActionListener est une implémentation de l'interface ActionListener
 * qui réagit aux événements d'action, tels que les clics de bouton.
 * Elle est conçue pour être utilisée en conjonction avec BasculeListener afin de basculer
 * la valeur d'une propriété "estUnMenu" lorsqu'un événement d'action se produit.
 *
 * @author Héloïse BOUSSON
 * @version 1.0
 * @see BasculeListener
 */
public class BasculeActionListener implements ActionListener {

    /**
     * L'instance de BasculeListener associée à cet écouteur.
     */
    private BasculeListener basculeListener;

    /**
     * Constructeur de la classe BasculeActionListener.
     *
     * @param basculeListener L'instance de BasculeListener à associer à cet écouteur.
     */
    public BasculeActionListener(BasculeListener basculeListener) {
        this.basculeListener = basculeListener;
    }

    /**
     * Méthode appelée lorsqu'un événement d'action se produit.
     * Bascule la valeur de la propriété "estUnMenu" de l'objet BasculeListener associé.
     *
     * @param e L'événement d'action généré.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Bascule de la valeur estUnMenu
        basculeListener.onBascule(!basculeListener.isEstUnMenu());
    }
}
