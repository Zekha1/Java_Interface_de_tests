package fr.iutfbleau.sae31.outil;

import javax.swing.*;

/**
 * La classe Main est la classe principale du programme.
 * Elle contient la méthode main qui est le point d'entrée du programme.
 */
public class Main {

    /**
     * Point d'entrée du programme.
     * Elle initialise le protocole de test, récupère les informations nécessaires
     * et crée un menu en fonction des options disponibles.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        String ref = JOptionPane.showInputDialog("Entrez le protocole de test");
        Protocole p = new Protocole(ref);

        while (!p.exists()) {
            if (ref==null){
                System.exit(0);
            }
            ref = JOptionPane.showInputDialog("Test inconnu.\nEntrez un protocole de test");
            p.setRef(ref);
        }
        p.recupProtocole();

        MissionAcceptee mission = new MissionAcceptee(p.getDescription());
        if (mission.getReponse()) {
            ListeOption liste = new ListeOption();
            liste.recupOptions(p.getRef());

            ArbreOption arbre = new ArbreOption(p.getIdRacine());
            arbre.creation(liste.getListe());

            Menu menu = new Menu(arbre.getArbre(),p);

        }
    }
}
