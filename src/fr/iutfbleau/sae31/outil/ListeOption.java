package fr.iutfbleau.sae31.outil;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe ListeOption représente une liste d'options associées à un protocole de test.
 */
public class ListeOption {
    private List<Option> liste;

    /**
     * Constructeur de la classe ListeOption.
     * Initialise la liste d'options en tant qu'ArrayList.
     */
    public ListeOption(){
        this.liste = new ArrayList<>();
    }

    /**
     * Récupère les options associées à un protocole depuis la base de données.
     *
     * @param ref La référence du protocole pour lequel récupérer les options.
     */
    public void recupOptions(String ref){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/morinl", "morinl", "chehsatoru");
                try {
                    PreparedStatement pst = cnx.prepareStatement("SELECT o.titre,c.id_parent,c.position,o.id_option,o.est_un_menu " +
                            "FROM Protocole p, Options o, ContientOption c " +
                            "WHERE p.reference=(?) AND p.reference=o.reference AND o.id_option=c.id_option; ");
                    pst.setString(1,ref);
                    try{
                        ResultSet rs = pst.executeQuery();
                        while (rs.next()) {
                            Option o = new Option(rs.getInt(3),rs.getInt(2),rs.getString(1),rs.getInt(4),rs.getBoolean(5));
                            this.liste.add(o);
                        }
                        cnx.close();
                    } catch (SQLException e){
                        JOptionPane.showMessageDialog(null,"Erreur lors de l'execution de la requete" + e.getMessage());
                        cnx.close();
                    }
                } catch (SQLException e){
                    JOptionPane.showMessageDialog(null,"Erreur lors de la requête"+e.getMessage());
                    cnx.close();
                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Erreur de connexion à la base de données");
            }
        } catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Erreur lors du chargement du driver");
        }
    }

    /**
     * Obtient la liste d'options.
     *
     * @return La liste d'options.
     */
    public List<Option> getListe() {
        return this.liste;
    }
}
