package prog.teampoule.applitest.BDD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telecom.Call;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noemie on 27/03/2017.
 */

public class ConseilsBDD {
    private static final String TABLE_TITRE = "titreconseils";
    private static final String TABLE_DETAILS = "detailsconseils";
    private static final String KEY_ID_TITRE = "id_titre";
    private static final String KEY_NOM_TITRE = "nom_titre";
    private static final String KEY_ID_DETAILS = "id_details";
    private static final String KEY_NOM_DETAILS = "nom_details";
    private static final String KEY_CONTENU_DETAILS = "contenu_details";
    private static final String[] COLUMNS_TITRE = {KEY_ID_TITRE, KEY_NOM_TITRE};
    private static final String[] COLUMNS_DETAILS = {KEY_ID_DETAILS, KEY_ID_TITRE, KEY_NOM_DETAILS, KEY_CONTENU_DETAILS};
    private MySQLiteHelper context;
    private SQLiteDatabase bdd;
    public ConseilsBDD(MySQLiteHelper c) {
        context = c;
    }


    public TitreConseils getTitreConseils(int id) {
        SQLiteDatabase db = context.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_TITRE, // a. table
                        COLUMNS_TITRE, // b. column names
                        " id_titre = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        TitreConseils titreConseils = new TitreConseils();

        titreConseils.setId_titre(Integer.parseInt(cursor.getString(0)));
        titreConseils.setNom_titre(cursor.getString(1));

        Log.d("getTitreConseils(" + id + ")", titreConseils.toString());

        return titreConseils;
    }

    public List<DetailsConseils> getAllDetailsConseils(int id) {
        List<DetailsConseils> listDetails = new ArrayList<DetailsConseils>();

        String query = "SELECT * FROM " + TABLE_DETAILS
                + " WHERE id_titre = " + id + ";";

        SQLiteDatabase db = context.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        DetailsConseils detailsConseils = null;
        if (cursor.moveToFirst()) {
            do {
                detailsConseils = new DetailsConseils();
                detailsConseils.setId_details(Integer.parseInt(cursor.getString(0)));
                detailsConseils.setId_titre(Integer.parseInt(cursor.getString(1)));
                detailsConseils.setNom_details(cursor.getString(2));
                detailsConseils.setContenu_details(cursor.getString(3));

                Log.i("DetailsConseils", detailsConseils.toString());

                listDetails.add(detailsConseils);
            } while (cursor.moveToNext());
        }

        Log.d("getAllDetails()", listDetails.toString());

        return listDetails;
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = context.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }
}