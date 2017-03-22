package prog.teampoule.applitest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
        activity_Conseils_Item item = intent.getParcelableExtra("Item");


        //Toast.makeText(getApplicationContext(), "problème", Toast.LENGTH_LONG).show();
        //Log.d("salut", item.getTitre().toString());

        TextView viewTitre = (TextView) findViewById(R.id.titreDetails);
        TextView viewTitre1 = (TextView) findViewById(R.id.titreContenu1);
        TextView viewContenu1 = (TextView) findViewById(R.id.textContenu1);
        TextView viewTitre2 = (TextView) findViewById(R.id.titreContenu2);
        TextView viewContenu2 = (TextView) findViewById(R.id.textContenu2);
        TextView viewTitre3 = (TextView) findViewById(R.id.titreContenu3);
        TextView viewContenu3 = (TextView) findViewById(R.id.textContenu3);

        viewTitre.setText(item.getTitre());
        viewTitre1.setText(item.getTitreContenu(0));
        viewContenu1.setText(item.getContenu(0));
        viewTitre2.setText(item.getTitreContenu(1));
        viewContenu2.setText(item.getContenu(1));
        viewTitre3.setText(item.getTitreContenu(2));
        viewContenu3.setText(item.getContenu(2));

    }
}
