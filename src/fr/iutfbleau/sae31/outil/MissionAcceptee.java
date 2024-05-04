package fr.iutfbleau.sae31.outil;

import javax.swing.*;

/**
 * La classe MissionAcceptee étend JOptionPane et est utilisée pour afficher une boîte de dialogue
 * demandant à l'utilisateur s'il veut continuer dans ce protocole. Elle représente une personnalisation de la
 * boîte de dialogue de confirmation de Java Swing.
 */
public class MissionAcceptee extends JOptionPane {
    private int reponse;

    /**
     * Constructeur de la classe MissionAcceptee.
     *
     * @param description La description du protocole à afficher dans la boîte de dialogue.
     */
    public MissionAcceptee(String description) {
        String message = "Votre mission, si vous l'acceptez est la suivante:\n"+description;
        this.reponse=showConfirmDialog(null,message);
    }

    /**
     * Obtient la réponse de l'utilisateur.
     *
     * @return true si l'utilisateur accepte (la réponse est YES_OPTION), sinon false.
     */
    public boolean getReponse(){
        return this.reponse == YES_OPTION;
    }
}
