package prog.teampoule.applitest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.R;

/**
 * Created by Julien on 23/02/2017.
 */

public class activity_Equipes extends Menu {

    private Button b = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_equipes);
        View inflated = stub.inflate();

        Bundle extras = getIntent().getExtras();
        ArrayList<String> listJoueurs;
        listJoueurs = extras.getStringArrayList("JOUEURS");

        listJoueurs = melanger(listJoueurs);

        final int taille = listJoueurs.size();
        int nbJoueurs1 = 0;
        int nbJoueurs2 = 0;
        int j = 0;
        while(true)
        {
            if(j>=taille)
                break;
            else {
                nbJoueurs1 ++;
            }
            if(j+1>=taille)
                break;
            else
                nbJoueurs2++;

            j = j +2;
        }

        String[] equipe1 = new String[nbJoueurs1];
        String[] equipe2 = new String[nbJoueurs2];

        int i = 0;
        int index=0;
        while(true)
        {
            if(i>=taille)
                break;
            else {
                equipe1[index] = listJoueurs.get(i);
            }
            if(i+1>=taille)
                break;
            else
                equipe2[index] = listJoueurs.get(i+1);

            i = i +2;
            index++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, equipe1);
        ListView list = (ListView)findViewById(R.id.lstEquipe1);
        list.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, equipe2);
        ListView list2 = (ListView)findViewById(R.id.lstEquipe2);
        list2.setAdapter(adapter2);

    }

    public ArrayList melanger(ArrayList listDepart){

        ArrayList nouvelle = new ArrayList(listDepart);
        Collections.shuffle(nouvelle);
        return nouvelle;
    }

}
