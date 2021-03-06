package prog.teampoule.applitest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import prog.teampoule.applitest.Utilities.HttpRequestTask_Patinoire;
import prog.teampoule.applitest.Utilities.HttpRequestTask_User;
import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.R;
import prog.teampoule.applitest.classAdapter.Patinoire;

/**
 * Created by Julien on 23/02/2017.
 */

public class activity_Patinoire extends Menu {
    TextView resultat;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_patinoire);
        View inflated = stub.inflate();
        resultat = (TextView) findViewById(R.id.id_chargementPatinoire);
        lv = (ListView) findViewById(R.id.ListViewPatinoire);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Patinoire item = (Patinoire) parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), activity_OnePatinoire.class);
                intent.putExtra("patinoire", item);
                startActivity(intent);
            }
        });
        try{
            getPersonnes("https://patinou-rest-api-ombrecube.c9users.io/patinoire");
        }
        catch(Exception e){
            resultat.setText(e.toString());
        }

    }

    /**
     * Récupère une liste de personnes.
     * @return ArrayList<Personne>: ou autre type de données.
     */
    public void getPersonnes(String myurl) {
        try {

            HttpRequestTask_Patinoire http = new HttpRequestTask_Patinoire();
            http.setURL(myurl);
            http.setResultat(resultat);
            http.setList(lv);
            http.setContext(getApplicationContext());
            http.execute();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Test", e.toString());

        }
    }

}
