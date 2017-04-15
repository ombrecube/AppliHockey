package prog.teampoule.applitest.classAdapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Julien on 16/03/2017.
 */

public class Patinoire implements Parcelable{
    private int id_patinoire;
    private String nom;
    private String adresse;
    private String cp;
    private String telephone;
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Patinoire(){

    }

    protected Patinoire(Parcel in) {
        id_patinoire = in.readInt();
        nom = in.readString();
        adresse = in.readString();
        cp = in.readString();
        telephone = in.readString();
    }

    public static final Creator<Patinoire> CREATOR = new Creator<Patinoire>() {
        @Override
        public Patinoire createFromParcel(Parcel in) {
            return new Patinoire(in);
        }

        @Override
        public Patinoire[] newArray(int size) {
            return new Patinoire[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_patinoire);
        dest.writeString(nom);
        dest.writeString(adresse);
        dest.writeString(cp);
        dest.writeString(telephone);
    }
}
