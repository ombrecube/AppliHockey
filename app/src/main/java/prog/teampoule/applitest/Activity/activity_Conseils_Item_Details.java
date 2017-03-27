package prog.teampoule.applitest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import prog.teampoule.applitest.BDD.ConseilsBDD;
import prog.teampoule.applitest.BDD.DetailsConseils;
import prog.teampoule.applitest.BDD.MySQLiteHelper;
import prog.teampoule.applitest.R;

/**
 * Created by Noemie on 22/03/2017.
 */

public class activity_Conseils_Item_Details extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseils_details);

        Intent intent = getIntent();
        String item = intent.getExtras().getString("Item");

        TextView viewTitre = (TextView) findViewById(R.id.titreDetails);
        viewTitre.setText(item);

        TextView viewTitreContenu1 = (TextView) findViewById(R.id.titreContenu1);
        TextView viewTitreContenu2 = (TextView) findViewById(R.id.titreContenu2);
        TextView viewTitreContenu3 = (TextView) findViewById(R.id.titreContenu3);
        TextView viewContenu1 = (TextView) findViewById(R.id.textContenu1);
        TextView viewContenu2 = (TextView) findViewById(R.id.textContenu2);
        TextView viewContenu3 = (TextView) findViewById(R.id.textContenu3);

        MySQLiteHelper db = new MySQLiteHelper(getApplicationContext());
        ConseilsBDD conseils = new ConseilsBDD(db);
        List<DetailsConseils> listDetails = new ArrayList<DetailsConseils>();
        listDetails = conseils.getAllDetailsConseils(1);

        String nom_details = new String();
        String contenu_details = new String();

        nom_details = listDetails.get(0).getNom_details();
        contenu_details = listDetails.get(0).getContenu_details();
        viewTitreContenu1.setText(nom_details);
        viewContenu1.setText(contenu_details);

        nom_details = listDetails.get(1).getNom_details();
        contenu_details = listDetails.get(1).getContenu_details();
        viewTitreContenu2.setText(nom_details);
        viewContenu2.setText(contenu_details);

        //Toast.makeText(getApplicationContext(), "problème", Toast.LENGTH_LONG).show();
        //Log.d("salut", item.getTitre().toString());

    }
}
