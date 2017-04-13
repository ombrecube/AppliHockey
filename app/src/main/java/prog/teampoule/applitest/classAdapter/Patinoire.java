package prog.teampoule.applitest.classAdapter;

/**
 * Created by Julien on 16/03/2017.
 */

public class Patinoire {
    private int id_patinoire;
    private String nom;
    private String adresse;
    private String cp;
    private String telephone;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId_patinoire() {
        return id_patinoire;
    }

    public void setId_patinoire(int id_patinoire) {
        this.id_patinoire = id_patinoire;
    }
}
