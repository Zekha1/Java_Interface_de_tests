package fr.iutfbleau.sae31.synthese;

/**
 * L'interface BasculeListener définit les méthodes nécessaires pour écouter les événements de bascule.
 * Elle est conçue pour être utilisée en conjonction avec BasculeActionListener afin de permettre la bascule
 * de la valeur de la propriété "estUnMenu".
 *
 * @author Héloïse BOUSSON
 * @version 1.0
 * @see BasculeActionListener
 */
public interface BasculeListener {

    /**
     * Méthode appelée lorsqu'une bascule est effectuée.
     *
     * @param estUnMenu La nouvelle valeur de la propriété "estUnMenu".
     */
    void onBascule(boolean estUnMenu);

    /**
     * Méthode permettant de récupérer la valeur actuelle de la propriété "estUnMenu".
     *
     * @return La valeur actuelle de la propriété "estUnMenu".
     */
    boolean isEstUnMenu();
}
