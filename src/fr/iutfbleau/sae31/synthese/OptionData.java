package fr.iutfbleau.sae31.synthese;

/**
 * La classe OptionData représente les données associées à une option, comprenant
 * son titre et une indication sur le fait qu'elle soit un menu ou non.
 * 
 * @author Héloïse BOUSSON
 * @version 1.0
 */
public class OptionData {

    private String titre;
    private boolean estUnMenu;

    /**
     * Constructeur de la classe OptionData.
     * 
     * @param titre     Le titre de l'option.
     * @param estUnMenu Indique si l'option est un menu (true) ou non (false).
     */
    public OptionData(String titre, boolean estUnMenu) {
        this.titre = titre;
        this.estUnMenu = estUnMenu;
    }

    /**
     * Obtient le titre de l'option.
     * 
     * @return Le titre de l'option.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Indique si l'option est un menu.
     * 
     * @return true si l'option est un menu, false sinon.
     */
    public boolean isMenu() {
        return estUnMenu;
    }
}
