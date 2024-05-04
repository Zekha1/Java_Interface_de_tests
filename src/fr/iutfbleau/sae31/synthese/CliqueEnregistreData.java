package fr.iutfbleau.sae31.synthese;

/**
 * La classe CliqueEnregistreData représente les données d'un clic enregistré,
 * comprenant l'identifiant du clic, la position du clic, et l'identifiant de l'option associée.
 *
 * @author Héloïse BOUSSON
 * @version 1.0
 */
public class CliqueEnregistreData {
    private int idClic;
    private int position;
    private int idOption;

    /**
     * Obtient l'identifiant du clic.
     *
     * @return L'identifiant du clic.
     */
    public int getIdClic() {
        return idClic;
    }

    /**
     * Définit l'identifiant du clic.
     *
     * @param idClic Le nouvel identifiant du clic.
     */
    public void setIdClic(int idClic) {
        this.idClic = idClic;
    }

    /**
     * Obtient la position du clic.
     *
     * @return La position du clic.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Définit la position du clic.
     *
     * @param position La nouvelle position du clic.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Obtient l'identifiant de l'option associée au clic.
     *
     * @return L'identifiant de l'option associée au clic.
     */
    public int getIdOption() {
        return idOption;
    }

    /**
     * Définit l'identifiant de l'option associée au clic.
     *
     * @param idOption Le nouvel identifiant de l'option associée au clic.
     */
    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }
}
