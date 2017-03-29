package prog.teampoule.applitest.BDD;

/**
 * Created by Noemie on 27/03/2017.
 */

public class DetailsConseils {
    private int id_details;
    private int id_titre;
    private String nom_details;
    private String contenu_details;

    public void DetailsConseils(int id_d, int id_t, String nom, String contenu) {
        id_details = id_d;
        id_titre = id_t;
        nom_details = nom;
        contenu_details = contenu;
    }

    public int getId_details() {
        return id_details;
    }

    public int getId_titre() {
        return id_titre;
    }

    public String getNom_details() {
        return nom_details;
    }

    public String getContenu_details() {
        return contenu_details;
    }

    public void setId_details(int id_details) {
        this.id_details = id_details;
    }

    public void setId_titre(int id_titre) {
        this.id_titre = id_titre;
    }

    public void setNom_details(String nom_details) {
        this.nom_details = nom_details;
    }

    public void setContenu_details(String contenu_details) {
        this.contenu_details = contenu_details;
    }
}
