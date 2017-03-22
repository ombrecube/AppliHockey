package prog.teampoule.applitest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import prog.teampoule.applitest.Menu;
import prog.teampoule.applitest.R;

/**
 * Created by Julien on 23/02/2017.
 */

public class activity_MatchAleatoire extends Menu {

    private Button b = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_match);
        View inflated = stub.inflate();



        final Button button = (Button) findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Plus", Toast.LENGTH_SHORT).show();


                LinearLayout lay = (LinearLayout) findViewById(R.id.id_test);
                LinearLayout postLayout = new LinearLayout(getApplicationContext());
                TextView tx = new TextView(getApplicationContext());
                tx.setText("ZZZZZZZZZZ");
                //lay.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                postLayout.addView(tx, layoutParam);
                setContentView(postLayout);

                //EditText input = (EditText) findViewById(R.id.editText3);
                //String string = input.getText().toString();
                //Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG).show();

                //EditText tx = (EditText) findViewById(R.id.editText3);
                //tx.setVisibility(View.VISIBLE);


            }
        });

        final Button button2 = (Button) findViewById(R.id.button7);
        //Intent myIntent;
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Générer", Toast.LENGTH_SHORT).show();
                final Intent myIntent = new Intent(getApplicationContext(), activity_Equipes.class);
                startActivity(myIntent);
            }
        });




    }


}
