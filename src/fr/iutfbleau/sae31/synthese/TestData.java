package fr.iutfbleau.sae31.synthese;

/**
 * La classe TestData représente les données associées à un test, comprenant
 * son identifiant et une indication sur le succès ou l'échec du test.
 * 
 * @author Héloïse BOUSSON
 * @version 1.0
 */
public class TestData {
    private int idTest;
    private boolean succes;

    /**
     * Obtient l'identifiant du test.
     * 
     * @return L'identifiant du test.
     */
    public int getIdTest() {
        return idTest;
    }

    /**
     * Définit l'identifiant du test.
     * 
     * @param idTest Le nouvel identifiant du test.
     */
    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    /**
     * Indique si le test a été réussi.
     * 
     * @return true si le test a réussi, false sinon.
     */
    public boolean isSucces() {
        return succes;
    }

    /**
     * Définit le succès ou l'échec du test.
     * 
     * @param succes true si le test a réussi, false sinon.
     */
    public void setSucces(boolean succes) {
        this.succes = succes;
    }
}
