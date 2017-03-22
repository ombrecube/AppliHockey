package prog.teampoule.applitest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Noemie on 22/03/2017.
 */

public class activity_Conseils_Item implements Parcelable {
    private String titre;
    private String titreContenu[];
    private String contenu[];

    public activity_Conseils_Item (String str, String tab2[], String tab3[]) {
        titre = str;
        titreContenu = tab2;
        contenu = tab3;
    }

    public String getTitre() {
        return titre;
    }

    public String[] getTabTitreContenu() {
        return titreContenu;
    }

    public String[] getTabContenu() {
        return contenu;
    }

    public String getTitreContenu(int i) {
        return titreContenu[i];
    }

    public String getContenu(int i) {
        return contenu[i];
    }

    public void setTitre(String nouveauTitre) {
        titre = nouveauTitre;
    }

    public void setTitreContenu(String nouveauTitre, int i) {
        titreContenu[i] = nouveauTitre;
    }

    public void setContenu(String nouveauContenu, int i) {
        contenu[i] = nouveauContenu;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(titre);
        out.writeStringArray(titreContenu);
        out.writeStringArray(contenu);
    }

    public static final Parcelable.Creator<activity_Conseils_Item> CREATOR
            = new Parcelable.Creator<activity_Conseils_Item>() {
        public activity_Conseils_Item createFromParcel(Parcel in) {
            return new activity_Conseils_Item(in);
        }

        public activity_Conseils_Item[] newArray(int size) {
            return new activity_Conseils_Item[size];
        }
    };

    private activity_Conseils_Item(Parcel in) {
        titre = in.readString();
        titreContenu = in.readStringArray();
        contenu = in.readStringArray();
    }
}
