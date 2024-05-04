package fr.iutfbleau.sae31.outil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * La classe ArbreOption permet de créer une arborescence à partir d'une liste d'option, certaines options peuvent être des enfants d'une autre option.
 * Chaque option est représentée par un nœud dans l'arborescence.
 *
 * L'arborescence est construite en spécifiant la racine identifiée par un ID et en utilisant une liste d'options passée en argument.
 * Les options sont organisées de façon hiérarchique en fonction de leurs relations parent-enfant(s).
 *
 */
public class ArbreOption {
    private JTree arbre;
    private int idRacine;

    /**
     * Constructeur de la classe ArbreOption
     * @param idRacine id de la racine de l'arborescence
     */
    public ArbreOption(int idRacine) {
        this.idRacine = idRacine;
    }


    /**
     * Méthode qui crée l'arborescence en utilisant la liste d'option spécifiée. La racine de l'arborescence est définie en fonction de l'identifiant passé lors de la création de l'objet ArbreOption.
     *
     * @param liste La liste des Options à intégrer dans le JTree
     * @throws NoSuchElementException Si la racine spécifiée n'est pas trouvée dans la liste d'options.
     */
    public void creation(List<Option> liste) {
        DefaultMutableTreeNode root = null;
        for (Option option: liste){
            if (option.getId()==this.idRacine){
                root=new DefaultMutableTreeNode(option);
                option.setAjoute(true);
            }
        }
        if (root == null) {
            throw new NoSuchElementException("La racine n'a pas été trouvée");
        }

        for (Option option: liste){
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(option);
            if (!option.isAjoute()){
                if (option.getHasChild()){
                    DefaultMutableTreeNode nodeEnfant = enfant(liste,option,treeNode);
                        treeNode.add(nodeEnfant);
                }

                root.add(treeNode);
                option.setAjoute(true);
            }

        }

        this.arbre = new JTree(root);
        this.arbre.setRootVisible(false);
    }

    /**
     * Récursive : Crée un nœud pour une option spécifiée et ses éventuels enfants.
     * Cette méthode est utilisée pour construire l'arborescence.
     *
     * @param liste la liste d'options à intégrer dans le JTree
     * @param optionParent L'option parent pour laquelle créer le nœud
     * @param treeNode Le nœud parent pour lequel ajouter le nœud de l'option enfant
     * @return le nœud créé pour l'option spécifiée et ses potentiels enfants
     */
    private static DefaultMutableTreeNode enfant(List<Option> liste,Option optionParent,DefaultMutableTreeNode treeNode){
        DefaultMutableTreeNode enfant=null;
        for(Option option : liste){
            if (option.getIdParent()==optionParent.getId()){
                enfant=new DefaultMutableTreeNode(option);
                if (option.getHasChild()){
                    DefaultMutableTreeNode petitEnfant = enfant(liste,option,enfant);
                    enfant.add(petitEnfant);

                }
                treeNode.add(enfant);
                option.setAjoute(true);
            }
        }

        return enfant;
    }


    /**
     * Récupère l'arborescence
     *
     * @return L'arborescence créée
     */
    public JTree getArbre() {
        return arbre;
    }
}