package fr.iutfbleau.sae31.synthese;

import java.util.List;
import java.util.Map;

import javax.swing.*;

/**
 * La classe Main est la classe principale du programme. Elle permet de lancer
 * la demande de protocole, récupérer les données associées au test avec la classe
 * DonneesTests, puis afficher la fenêtre du premier camembert avec le bouton
 * de bascule.
 * Les classes associées comprennent le prototype de test (similaire à celui de Lionel),
 * le premier camembert, le deuxième camembert et la bascule.
 * 
 * @author Héloïse BOUSSON
 * @version 1.0
 */
public class Main {

    /**
     * Le point d'entrée principal du programme.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans ce programme).
     */
    public static void main(String[] args) {
        boolean testTrouve = false;

        while (!testTrouve) {
            String ref = JOptionPane.showInputDialog("Entrez le protocole de test");

            if (ref == null) {
                // L'utilisateur a annulé la saisie, le programme se termine
                System.exit(0);
            } else {
                // Récupérer les données du test avec DonneesTests
                DonneesTests donneesTests = new DonneesTests(ref);
                donneesTests.recupDonneesTests();

                // Mauvaise référence
                if (donneesTests.getTestDataList().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Test inconnu. Veuillez entrer une référence valide.");
                } else {
                    // Fenêtre avec le premier camembert et le bouton de bascule
                    Map<Integer, List<CliqueEnregistreData>> clicsByTestId = donneesTests.getClicsByTestId();
                    Map<Integer, OptionData> optionsData = donneesTests.getOptionsData();

                    // Création de l'instance de Camembert avec les données
                    new Camembert(donneesTests, clicsByTestId, optionsData);

                    // Test trouvé, sortir de la boucle
                    testTrouve = true;
                }
            }
        }
    }
}
