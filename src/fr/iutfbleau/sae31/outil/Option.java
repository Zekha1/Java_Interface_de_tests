package fr.iutfbleau.sae31.outil;

/**
 * La classe Option représente une option dans un arbre d'options.
 * Chaque option est caractérisée par un identifiant, une position, un identifiant parent, une action,
 * une indication de la présence d'enfants, et un indicateur si l'option a été ajoutée.
 *
 * @author Lionel
 * @version 1.0
 */
public class Option {
    private int id;
    private int position;
    private int idParent;
    private String action;
    private boolean hasChild;
    private boolean ajoute;

    /**
     * Constructeur de la classe Option.
     *
     * @param position La position de l'option.
     * @param idParent L'identifiant du parent de l'option.
     * @param action L'action associée à l'option.
     * @param id L'identifiant de l'option.
     * @param hasChild Indique si l'option a des enfants.
     */
    public Option(int position,int idParent,String action,int id,boolean hasChild){
        this.position=position;
        this.idParent=idParent;
        this.action=action;
        this.id=id;
        this.hasChild=hasChild;
        this.ajoute=false;
    }

    /**
     * Obtient l'identifiant de l'option.
     *
     * @return L'identifiant de l'option.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient l'identifiant du parent de l'option.
     *
     * @return L'identifiant du parent de l'option.
     */
    public int getIdParent(){
        return this.idParent;
    }

    /**
     * Obtient la position de l'option.
     *
     * @return La position de l'option.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Indique si l'option a des enfants.
     *
     * @return true si l'option a des enfants, false sinon.
     */
    public boolean getHasChild(){
        return this.hasChild;
    }

    /**
     * Indique si l'option a été ajoutée à l'arbre.
     *
     * @return true si l'option a été ajoutée, false sinon.
     */
    public boolean isAjoute() {
        return ajoute;
    }

    /**
     * Définit si l'option a été ajoutée.
     *
     * @param ajoute true si l'option a été ajoutée, false sinon.
     */
    public void setAjoute(boolean ajoute) {
        this.ajoute = ajoute;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Option.
     *
     * @return La chaîne de caractères représentant l'objet Option.
     */
    @Override
    public String toString() {
        return this.action;
    }
}