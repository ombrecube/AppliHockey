package prog.teampoule.applitest.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import prog.teampoule.applitest.InputStreamOperations;
import prog.teampoule.applitest.Menu;
import prog.teampoule.applitest.Patinoire;
import prog.teampoule.applitest.R;

/**
 * Created by Julien on 23/02/2017.
 */

public class activity_Patinoire extends Menu {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_patinoire);
        View inflated = stub.inflate();
        TextView test = (TextView) findViewById(R.id.TestPatinoire);
        ArrayList<Patinoire> patinoires = getPersonnes("https://patinou-rest-api-ombrecube.c9users.io/patinoire");
        test.setText(patinoires.toString());
    }

/**
 * Récupère une liste de personnes.
 * @return ArrayList<Personne>: ou autre type de données.
 */
public static ArrayList<Patinoire> getPersonnes(String myurl) {

    ArrayList<Patinoire> patinoires = new ArrayList<Patinoire>();
    Log.d("JSON","TEst");
    try {
        URL url = new URL(myurl);
        Log.d("JSON","0");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        Log.d("JSON","1");
        InputStream inputStream = connection.getInputStream();
        String result = InputStreamOperations.InputStreamToString(inputStream);
        Log.d("JSON","2");

        // On récupère le JSON complet
        JSONObject jsonObject = new JSONObject(result);
        Log.d("JSON",jsonObject.toString());
        // On récupère le tableau d'objets qui nous concernent
        JSONArray array = new JSONArray(jsonObject);
        // Pour tous les objets on récupère les infos
        for (int i = 0; i < array.length(); i++) {
            // On récupère un objet JSON du tableau
            JSONObject obj = new JSONObject(array.getString(i));
            // On fait le lien Personne - Objet JSON
            Patinoire patinoire = new Patinoire();
            patinoire.setName(obj.getString("name"));
            patinoire.setText(obj.getString("text"));
            // On ajoute la personne à la liste
            patinoires.add(patinoire);

        }

    } catch (Exception e) {
        e.printStackTrace();
        Log.d("Test",e.toString());
    }
    // On retourne la liste des personnes
    return patinoires;
}
}
