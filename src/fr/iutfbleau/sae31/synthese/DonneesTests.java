package fr.iutfbleau.sae31.synthese;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * La classe DonneesTests représente les données associées à un ensemble de tests, y compris
 * les données des tests, les clics enregistrés, les options correctes, et les données d'options.
 * Elle récupère les données à partir d'une base de données MariaDB.
 *
 * @author Héloïse BOUSSON
 * @version 1.0
 */
public class DonneesTests {

    private String ref;
    private List<Integer> idOptionsCorrectes;
    private List<TestData> testDataList;
    private Map<Integer, List<CliqueEnregistreData>> clicsByTestId;
    private Map<Integer, OptionData> optionsData;

    /**
     * Constructeur de la classe DonneesTests.
     *
     * @param ref La référence associée aux tests.
     */
    public DonneesTests(String ref) {
        this.ref = ref;
        this.idOptionsCorrectes = new ArrayList<>();
        this.testDataList = new ArrayList<>();
        this.clicsByTestId = new HashMap<>();
        this.optionsData = new HashMap<>();
    }

    /**
     * Récupère les données des tests à partir de la base de données MariaDB.
     */
    public void recupDonneesTests() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/morinl", "morinl",
                        "chehsatoru");
                try {
                    // Récupération des données de la table Test et de la table CliqueEnregistre
                    // pour chaque test
                    String sql = "SELECT t.*, c.* FROM Test t LEFT JOIN CliqueEnregistre c ON t.id_test = c.id_test WHERE t.reference = ?";
                    try (PreparedStatement pst = cnx.prepareStatement(sql)) {
                        pst.setString(1, this.ref);

                        try (ResultSet rs = pst.executeQuery()) {
                            int currentTestId = -1;
                            TestData currentTestData = null;
                            List<CliqueEnregistreData> clicsList = new ArrayList<>();

                            while (rs.next()) {
                                int testId = rs.getInt("id_test");

                                if (testId != currentTestId) {
                                    // Nouveau test, sauvegarde des données précédentes
                                    if (currentTestData != null) {
                                        this.testDataList.add(currentTestData);
                                        this.clicsByTestId.put(currentTestId, clicsList);
                                    }

                                    // Nouvelle initialisation
                                    currentTestId = testId;
                                    currentTestData = new TestData();
                                    currentTestData.setIdTest(testId);
                                    currentTestData.setSucces(rs.getBoolean("succes"));
                                    clicsList = new ArrayList<>();
                                }

                                // Récupération des données de la table CliqueEnregistre
                                CliqueEnregistreData cliqueEnregistreData = new CliqueEnregistreData();
                                cliqueEnregistreData.setIdClic(rs.getInt("id_clic"));
                                cliqueEnregistreData.setPosition(rs.getInt("position"));
                                cliqueEnregistreData.setIdOption(rs.getInt("id_option"));
                                clicsList.add(cliqueEnregistreData);
                            }

                            // Ajout des dernières données
                            if (currentTestData != null) {
                                this.testDataList.add(currentTestData);
                                this.clicsByTestId.put(currentTestId, clicsList);
                            }
                            // Récupération de l'id de l'option correcte
                            sql = "SELECT id_option_correcte FROM Protocole WHERE reference = ?";
                            try (PreparedStatement pstm = cnx.prepareStatement(sql)) {
                                pstm.setString(1, this.ref);
                                try (ResultSet rls = pstm.executeQuery()) {
                                    if (rls.next()) {
                                        int idOptionCorrecte = rls.getInt("id_option_correcte");
                                        // Ajout de l'id de l'option correcte et des id des menus associés récursivement
                                        this.idOptionsCorrectes.add(idOptionCorrecte);
                                        retrieveMenusForOption(idOptionCorrecte, cnx);
                                    }
                                }
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null,
                                        "Erreur lors de l'exécution de la requête : " + e.getMessage());
                                cnx.close();
                            }
                            // Récupération des noms des options
                            sql = "SELECT id_option, titre, est_un_menu FROM Options WHERE reference = ?";
                            try (PreparedStatement pstm = cnx.prepareStatement(sql)) {
                                pstm.setString(1, this.ref);
                                try (ResultSet rls = pstm.executeQuery()) {
                                    while (rls.next()) {
                                        int idOption = rls.getInt("id_option");
                                        String titreOption = rls.getString("titre");
                                        boolean estUnMenu = rls.getBoolean("est_un_menu");

                                        optionsData.put(idOption, new OptionData(titreOption, estUnMenu));
                                    }
                                } catch (SQLException e) {
                                    JOptionPane.showMessageDialog(null,
                                            "Erreur lors de l'exécution de la requête : " + e.getMessage());
                                    cnx.close();
                                }
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null,
                                        "Erreur lors de l'exécution de la requête : " + e.getMessage());
                                cnx.close();
                            }
                            cnx.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null,
                                    "Erreur lors de l'exécution de la requête : " + e.getMessage());
                            cnx.close();
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Erreur lors du prepared statement : " + e.getMessage());
                        cnx.close();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la requête : " + e.getMessage());
                    cnx.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement du driver");
        }
    }    

    /**
     * Récupère récursivement les identifiants des menus associés à une option correcte.
     *
     * @param idOption L'identifiant de l'option correcte.
     * @param cnx      La connexion à la base de données.
     */
    private void retrieveMenusForOption(int idOption, Connection cnx) {
        // Boucle pour récupérer les menus associés
        while (true) {
            // Récupération de l'id du menu associé à l'option
            String sqlMenu = "SELECT id_parent FROM ContientOption WHERE id_option = ?";
            try (PreparedStatement pstMenu = cnx.prepareStatement(sqlMenu)) {
                pstMenu.setInt(1, idOption);
                try (ResultSet rsMenu = pstMenu.executeQuery()) {
                    if (rsMenu.next()) {
                        idOption = rsMenu.getInt("id_parent");
        
                        if (idOption != 0) {
                            // Ajout de l'id du menu associé
                            this.idOptionsCorrectes.add(idOption);
                        } else {
                            // Sortir de la boucle si l'option n'a pas de parent (null)
                            break;
                        }
                    } else {
                        // Sortir de la boucle si aucun résultat n'est trouvé
                        break;
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la requête : " + e.getMessage());
                    cnx.close();
                    break;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors du prepared statement : " + e.getMessage());
            }
        }        
    }
    
    

    /**
     * Obtient la référence associée aux tests.
     *
     * @return La référence associée aux tests.
     */
    public String getRef() {
        return this.ref;
    }

    /**
     * Obtient la liste des données des tests.
     *
     * @return La liste des données des tests.
     */
    public List<TestData> getTestDataList() {
        return this.testDataList;
    }

    /**
     * Obtient la liste des identifiants des options correctes.
     *
     * @return La liste des identifiants des options correctes.
     */
    public List<Integer> getIdOptionsCorrectes() {
        return this.idOptionsCorrectes;
    }

    /**
     * Obtient la carte des clics enregistrés par ID de test.
     *
     * @return La carte des clics enregistrés par ID de test.
     */
    public Map<Integer, List<CliqueEnregistreData>> getClicsByTestId() {
        return this.clicsByTestId;
    }

    /**
     * Obtient la carte des données d'options par ID d'option.
     *
     * @return La carte des données d'options par ID d'option.
     */
    public Map<Integer, OptionData> getOptionsData() {
        return this.optionsData;
    }
}
