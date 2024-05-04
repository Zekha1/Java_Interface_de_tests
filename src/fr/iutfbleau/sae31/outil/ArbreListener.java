package fr.iutfbleau.sae31.outil;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.LinkedList;

/**
 * La classe ArbreListener est un listener utilisé pour détecter les sélections dans un arbre d'options.
 */
public class ArbreListener implements TreeSelectionListener {
    private LinkedList<Option> optionLinkedList;

    /**
     * Constructeur de la classe ArbreListener.
     * Initialise une liste chaînée pour stocker les options sélectionnées.
     */
    public ArbreListener() {
        this.optionLinkedList = new LinkedList<>();
    }

    /**
     * Méthode appelée lorsqu'une sélection change dans l'arbre d'options.
     *
     * @param e L'événement de sélection dans l'arbre.
     */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getNewLeadSelectionPath();

        if (path != null) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            Option optionSelect = (Option) selectedNode.getUserObject();
            this.optionLinkedList.add(optionSelect);
        }
    }

    /**
     * Obtient la liste chaînée des options sélectionnées.
     *
     * @return La liste chaînée des options sélectionnées.
     */
    public LinkedList<Option> getOptionLinkedList(){
        return this.optionLinkedList;
    }
}
