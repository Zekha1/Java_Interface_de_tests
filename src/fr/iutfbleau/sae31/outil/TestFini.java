package fr.iutfbleau.sae31.outil;

import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;

/**
 * La classe TestFini représente les résultats d'un test terminé.
 * Elle permet d'ajouter les résultats du test dans la base de données.
 */
public class TestFini {
    private LinkedList<Option> liste;
    private Protocole protocole;

    /**
     * Constructeur de la classe TestFini.
     *
     * @param liste La liste des options sélectionnées pendant le test.
     * @param protocole Le protocole associé au test.
     */
    public TestFini(LinkedList<Option> liste, Protocole protocole) {
        this.liste = liste;
        this.protocole = protocole;
    }

    /**
     * Ajoute les résultats du test dans la base de données.
     */
    public void ajoutBase(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                Connection cnx = DriverManager.getConnection("url", "user", "password");
                try {
                    PreparedStatement ps=cnx.prepareStatement("INSERT INTO Test(reference,succes) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1,this.protocole.getRef());

                    if (this.liste.getLast().getId()==this.protocole.getIdCorrect()) {
                        ps.setBoolean(2,true);
                    } else {
                        ps.setBoolean(2, false);
                    }
                    try{
                        ps.executeUpdate();
                        try {
                            ResultSet rs=ps.getGeneratedKeys();
                            if (rs.next()){
                                int idTest=rs.getInt(1);
                                try {
                                    ps = cnx.prepareStatement("INSERT INTO CliqueEnregistre(position,id_test,id_option) VALUES (?,?,?)");
                                    for (Option option: this.liste){
                                        ps.setInt(1,option.getPosition());
                                        ps.setInt(2,idTest);
                                        ps.setInt(3,option.getId());
                                        ps.executeUpdate();
                                    }
                                } catch (SQLException e){
                                    JOptionPane.showMessageDialog(null,"Erreur de l'ajout des résultats en base de données");
                                    cnx.close();
                                }
                            }
                        } catch (SQLException e){
                            JOptionPane.showMessageDialog(null,"Erreur lors de la récupération du test");
                            cnx.close();
                        }
                    } catch (SQLException e){
                        JOptionPane.showMessageDialog(null,"Erreur lors de l'ajout du test");
                        cnx.close();
                    }
                } catch (SQLException e){
                    JOptionPane.showMessageDialog(null,"Erreur lors de la requête d'ajout en base de données");
                    cnx.close();
                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Erreur de connexion à la base de données");
            }
        } catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Erreur lor du chargement du driver");
        }
    }


}
