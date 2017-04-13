package prog.teampoule.applitest.classAdapter;

import java.util.Date;

/**
 * Created by Julien on 13/04/2017.
 */

public class Conversation {

    private String loginEmetteur;
    private String loginReceveur;
    private String message;
    private String date;
    private int id_user;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLoginEmetteur() {
        return loginEmetteur;
    }

    public void setLoginEmetteur(String loginEmetteur) {
        this.loginEmetteur = loginEmetteur;
    }

    public String getLoginReceveur() {
        return loginReceveur;
    }

    public void setLoginReceveur(String loginReceveur) {
        this.loginReceveur = loginReceveur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Conversation(){

    }

    public Conversation(String loginEmetteur, String loginReceveur, String message, String date) {
        this.loginEmetteur = loginEmetteur;
        this.loginReceveur = loginReceveur;
        this.message = message;
        this.date = date;
    }
}
