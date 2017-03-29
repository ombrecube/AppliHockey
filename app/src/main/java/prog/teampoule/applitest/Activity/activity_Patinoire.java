package prog.teampoule.applitest.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import java.util.ArrayList;

import prog.teampoule.applitest.Utilities.HttpRequestTaskManager;
import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.classAdapter.Patinoire;
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

        try {

            HttpRequestTaskManager http = new HttpRequestTaskManager();
            http.setURL(myurl);
            http.execute();
            ArrayList<Patinoire> patinoires;
            patinoires = http.getList();
            // On récupère le JSON complet
            return patinoires;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Test", e.toString());

        }
        return null;
    }
}
