package fr.iutfbleau.sae31.synthese;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe CamembertPanel est une extension de JPanel destinée à afficher un diagramme camembert
 * basé sur les données de tests. Elle permet de personnaliser la représentation graphique du camembert
 * en fonction des clics enregistrés et des options disponibles.
 *
 * @author Héloïse BOUSSON
 * @version 1.0
 */
public class CamembertPanel extends JPanel {

    private DonneesTests donneesTests;
    private Map<Integer, List<CliqueEnregistreData>> clicsByTestId;
    private Map<Integer, OptionData> optionsData;
    private boolean estUnMenu;
    private String labelText;

    /**
     * Constructeur de la classe CamembertPanel.
     *
     * @param donneesTests   Les données de tests.
     * @param clicsByTestId  La carte des clics par ID de test.
     * @param optionsData    La carte des données d'options.
     * @param estUnMenu      Indique si le camembert doit représenter les actions de menu ou de sous-menu.
     * @param labelText      Le texte à afficher en tant que label du camembert.
     */
    public CamembertPanel(DonneesTests donneesTests, Map<Integer, List<CliqueEnregistreData>> clicsByTestId,
            Map<Integer, OptionData> optionsData, boolean estUnMenu, String labelText) {
        this.donneesTests = donneesTests;
        this.clicsByTestId = clicsByTestId;
        this.optionsData = optionsData;
        this.estUnMenu = estUnMenu;
        this.labelText = labelText;
    }

    /**
     * Méthode appelée pour dessiner le contenu du composant.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Code pour créer le camembert
        Map<Integer, Integer> actionsParOption = new HashMap<>();
        Map<Integer, Color> couleursOptions = new HashMap<>();

        int totalActions = 0;

        for (List<CliqueEnregistreData> clicsList : clicsByTestId.values()) {
            for (CliqueEnregistreData clic : clicsList) {
                int idOption = clic.getIdOption();
                OptionData optionData = optionsData.get(idOption);

                if (optionData != null
                        && (estUnMenu && optionData.isMenu() || !estUnMenu && !optionData.isMenu())) {
                    totalActions++;
                    actionsParOption.put(idOption, actionsParOption.getOrDefault(idOption, 0) + 1);
                    couleursOptions.putIfAbsent(idOption, new Color((int) (Math.random() * 0x1000000)));
                }
            }
        }

        int startAngle = 0;

        for (Map.Entry<Integer, Integer> entry : actionsParOption.entrySet()) {
            int idOption = entry.getKey();
            int nombreActions = entry.getValue();

            double proportionActions = (double) nombreActions / totalActions;

            double angleActions = proportionActions * 360;

            Color couleurOption;
            OptionData optionData = optionsData.get(idOption);

            if (optionData != null) {
                if (optionData.isMenu()) {
                    if (donneesTests.getIdOptionsCorrectes().contains(idOption)) {
                        couleurOption = new Color(0, (int) ((Math.random() * 156) + 100), 0);
                    } else {
                        couleurOption = new Color((int) (Math.random() * 256), (int) (Math.random() * 50),
                                (int) (Math.random() * 256));
                    }
                } else {
                    if (donneesTests.getIdOptionsCorrectes().contains(idOption)) {
                        couleurOption = Color.GREEN;
                    } else {
                        couleurOption = new Color((int) (Math.random() * 256), (int) (Math.random() * 50),
                                (int) (Math.random() * 256));
                    }
                }

                couleursOptions.put(idOption, couleurOption);

                g.setColor(couleurOption);
                g.fillArc(100, getHeight() / 2 - 100, 200, 200, startAngle, (int) angleActions);

                startAngle += angleActions;
            }
        }

        int legendeX = getWidth() - 200;
        int legendeY = 20;

        for (Map.Entry<Integer, Color> entry : couleursOptions.entrySet()) {
            int idOption = entry.getKey();
            Color couleurOption = entry.getValue();

            String nomOption = optionsData.get(idOption).getTitre();

            g.setColor(couleurOption);
            g.fillRect(legendeX, legendeY, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(nomOption, legendeX + 30, legendeY + 15);
            legendeY += 30;
        }
        g.setColor(Color.BLACK);
        g.drawString(labelText, 10, 20);
    }
}
