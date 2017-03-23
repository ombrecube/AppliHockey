package prog.teampoule.applitest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import prog.teampoule.applitest.classAdapter.AdapteurJoueur;
import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.R;

/**
 * Created by Julien on 23/02/2017.
 */

public class activity_MatchAleatoire extends Menu {

    private Button b = null;
    private ArrayList<String> listItem=  new ArrayList<String>();
    private ListView listJoueur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_match);
        View inflated = stub.inflate();
        for (int i = 0; i < 4; i++) {
            listItem.add(" ");
        }
        listJoueur = (ListView) findViewById(R.id.lv_joueur);
        LoadListView();
        Button btnAdd = (Button) findViewById(R.id.addJoueur);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ajout d'un joueur", Toast.LENGTH_SHORT).show();
                listItem.add(" ");
                LoadListView();
            }
        });

        final Button button2 = (Button) findViewById(R.id.button7);
        //Intent myIntent;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Générer", Toast.LENGTH_SHORT).show();
                final Intent myIntent = new Intent(getApplicationContext(), activity_Equipes.class);

                ArrayList<String> listJou=  new ArrayList<String>();

                for(int i=1; i < listItem.size()+1; i++)
                {
                    EditText ed = (EditText) findViewById(1000+i);
                    String jou = ed.getText().toString();
                    listJou.add(jou);

                }
                myIntent.putExtra("JOUEURS", listJou);

                startActivity(myIntent);
            }
        });




    }

    private void LoadListView(){
        AdapteurJoueur adpt = new AdapteurJoueur(this,listItem);
        listJoueur.setAdapter(adpt);
    }


}
