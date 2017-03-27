package prog.teampoule.applitest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

        BufferedReader br = null;
        FileReader fr = null;

        if(item.equals("Patins")) {
            try {
                fr = new FileReader("./patins.txt");
                br = new BufferedReader(fr);

                String ligne = new String();

                ligne = br.readLine();

                viewTitreContenu1.setText(ligne);


            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }


        }

        //Toast.makeText(getApplicationContext(), "problème", Toast.LENGTH_LONG).show();
        //Log.d("salut", item.getTitre().toString());

    }
}
