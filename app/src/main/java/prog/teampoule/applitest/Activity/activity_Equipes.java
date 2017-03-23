package prog.teampoule.applitest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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


        final String tx = listJoueurs.get(2).toString();
        final Button btn = (Button) findViewById(R.id.btn_test);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), tx, Toast.LENGTH_SHORT).show();

            }
        });




    }


}
