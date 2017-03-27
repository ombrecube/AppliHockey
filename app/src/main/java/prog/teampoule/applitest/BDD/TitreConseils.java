package prog.teampoule.applitest.BDD;

/**
 * Created by Noemie on 27/03/2017.
 */

public class TitreConseils {
    private int id_titre;
    private String nom_titre;

    public void TitreConseils(int id, String titre) {
        id_titre = id;
        nom_titre = titre;
    }

    public int getId_titre() {
        return id_titre;
    }

    public String getNom_titre() {
        return nom_titre;
    }

    public void setId_titre(int id) {
        id_titre = id;
    }

    public void setNom_titre(String nom) {
        nom_titre = nom;
    }

    @Override
    public String toString() {
        return "TitreConseils[" +
                "id_titre=" + id_titre +
                ", nom_titre='" + nom_titre + '\'' +
                ']';
    }

}
