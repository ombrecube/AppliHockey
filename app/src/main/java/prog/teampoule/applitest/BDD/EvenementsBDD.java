package prog.teampoule.applitest.BDD;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



public class EvenementsBDD {
    private static final String TABLE_TITRE = "evenements";
    private static final String KEY_ID_EVENEMENT = "id_evenement";
    private static final String KEY_NOM_EVENEMENT = "nom_evenement";
    private static final String KEY_DATE_EVENEMENT = "date_evenement";
    private static final String[] COLUMNS_TITRE = {KEY_ID_EVENEMENT, KEY_NOM_EVENEMENT, KEY_DATE_EVENEMENT};
    private MySQLiteHelper context;
    private SQLiteDatabase bdd;
    public EvenementsBDD(MySQLiteHelper c) {
        context = c;
    }

    public List<Evenements> getAllEvenements() {
        List<Evenements> listEvents = new ArrayList<Evenements>();

        SQLiteDatabase db = context.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_TITRE + ";";

        Cursor cursor = db.rawQuery(query, null);

        Evenements event = null;
        if (cursor.moveToFirst()) {
            do {
                event = new Evenements();
                event.setId_evenement(Integer.parseInt(cursor.getString(0)));
                event.setNom_evenement(cursor.getString(1));
                event.setDate_evenement(cursor.getString(2));

                Log.i("DetailsConseils", event.toString());

                listEvents.add(event);
            } while (cursor.moveToNext());
        }

        Log.d("getAllEvents()", listEvents.toString());

        return listEvents;
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