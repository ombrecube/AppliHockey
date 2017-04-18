
package prog.teampoule.applitest.BDD;



public class Evenements {
    private int id_evenement;
    private String nom_evenement;
    private String date_evenement;

    public void Evenements(int id, String evenement, String date) {
        id_evenement = id;
        nom_evenement = evenement;
        date_evenement = date;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public String getDate_evenement() { return date_evenement; }

    public void setId_evenement(int id) {
        id_evenement = id;
    }

    public void setNom_evenement(String nom) {
        nom_evenement = nom;
    }

    public void setDate_evenement(String date) {
        date_evenement = date;
    }

    @Override
    public String toString() {
        return "Evenements[" +
                "id_evenement=" + id_evenement +
                ", nom_evenement='" + nom_evenement +
                ", date_evenement='" + date_evenement + '\'' +
                ']';
    }

}
