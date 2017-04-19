package prog.teampoule.applitest.Activity;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import prog.teampoule.applitest.BDD.EvenementsBDD;
import prog.teampoule.applitest.BDD.MySQLiteHelper;
import prog.teampoule.applitest.R;

public class activity_Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        final String date = extras.getString("DATE");
        long d = Long.parseLong(date);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormatted = formatter.format(d);

        TextView txtDate = (TextView) findViewById(R.id.txtDate);
        txtDate.setText(dateFormatted);

        final Button btn = (Button) findViewById(R.id.btn_Valid);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Intent myIntent = new Intent(getApplicationContext(), activity_Calendrier.class);
                EditText edEvent = (EditText) findViewById(R.id.edEvent);
                MySQLiteHelper db = new MySQLiteHelper(getApplicationContext());
                EvenementsBDD e = new EvenementsBDD(db);

                e.createEvent(edEvent.getText().toString(),date);

                startActivity(myIntent);
            }
        });


    }

}
