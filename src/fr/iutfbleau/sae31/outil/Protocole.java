package fr.iutfbleau.sae31.outil;

import javax.swing.*;
import java.sql.*;

/**
 * La classe Protocole représente un objet associé à un protocole de test.
 * Elle permet de vérifier l'existence du protocole en base de données
 * et de récupérer les informations associées à ce protocole.
 */
public class Protocole {
    private String ref;
    private String description;
    private int idRacine;
    private int idCorrect;

    /**
     * Constructeur de la classe Protocole.
     *
     * @param ref La référence du protocole.
     */
    public Protocole(String ref){
        this.ref=ref;
    }

    /**
     * Vérifie si le protocole donné existe bien en base de données
     * @return un booléen : vrai si le protocole existe, faux sinon
     */
    public boolean exists() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/morinl", "morinl", "chehsatoru");
                try {
                    PreparedStatement pst = cnx.prepareStatement("SELECT reference FROM Protocole WHERE reference=(?)");
                    pst.setString(1,this.ref);
                    try{
                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            cnx.close();
                            return true;
                        }
                        cnx.close();
                        return false;
                    } catch (SQLException e){
                        JOptionPane.showMessageDialog(null,"Erreur lors de l'execution de la requete" + e.getMessage());
                        cnx.close();
                        return false;
                    }
                } catch (SQLException e){
                    JOptionPane.showMessageDialog(null,"Erreur lors de la requête"+e.getMessage());
                    cnx.close();
                    return false;
                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Erreur de connexion à la base de données");
                return false;
            }
        } catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Erreur lor du chargement du driver");
            return false;
        }
    }

    /**
     * Récupère les informations associées au protocole depuis la base de données.
     */
    public void recupProtocole() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/morinl", "morinl", "chehsatoru");
                try {
                    PreparedStatement pst = cnx.prepareStatement("SELECT * FROM Protocole WHERE reference=(?)");
                    pst.setString(1,this.ref);
                    try{
                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            this.description = rs.getString(2);
                            this.idRacine = rs.getInt(3);
                            this.idCorrect = rs.getInt(4);
                            cnx.close();
                        }
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
            JOptionPane.showMessageDialog(null,"Erreur lor du chargement du driver");
        }
    }

    /**
     * Obtient la référence du protocole.
     *
     * @return La référence du protocole.
     */
    public String getRef() {
        return this.ref;
    }

    /**
     * Définit la référence du protocole.
     *
     * @param ref La nouvelle référence du protocole.
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * Obtient la description du protocole.
     *
     * @return La description du protocole.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtient l'identifiant de la racine du protocole.
     *
     * @return L'identifiant de la racine du protocole.
     */
    public int getIdRacine() {
        return idRacine;
    }

    /**
     * Obtient l'identifiant correct associé au protocole.
     *
     * @return L'identifiant correct associé au protocole.
     */
    public int getIdCorrect() {
        return idCorrect;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Protocole.
     *
     * @return La chaîne de caractères représentant l'objet Protocole.
     */
    @Override
    public String toString() {
        return "Référence: " + this.ref + "\nDescrition: " + this.description + "\nidRacine: " + this.idRacine + "\nidCorrect: " + this.idCorrect;
    }
}