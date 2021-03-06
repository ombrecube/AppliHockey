package prog.teampoule.applitest.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import prog.teampoule.applitest.BDD.Evenements;
import prog.teampoule.applitest.BDD.EvenementsBDD;
import prog.teampoule.applitest.BDD.MySQLiteHelper;
import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.R;

/**
 * Created by Julien on 20/03/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class activity_Calendrier extends Menu {

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_calendrier);
        View inflated = stub.inflate();

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        MySQLiteHelper db = new MySQLiteHelper(this);
        EvenementsBDD events = new EvenementsBDD(db);

        List<Evenements> listEvents =  events.getAllEvenements();

        String nom_evenements = new String();
        String date_evenements = new String();

        for (int i = 0; i < listEvents.size(); i++) {

            nom_evenements = listEvents.get(i).getNom_evenement();
            date_evenements = listEvents.get(i).getDate_evenement();
            final String date_bdd = date_evenements;

            long date = Long.parseLong(date_evenements);

            final Event event = new Event(Color.RED, date, nom_evenements);
            compactCalendar.addEvent(event);

            compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {
                    Context context = getApplicationContext();

                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
                    String strDate = dateFormat.format(dateClicked);

                    long d = dateClicked.getTime();
                    final String date_clicked = Long.toString(d);

                    TextView lblEvent = (TextView)findViewById(R.id.lblEvent);
                    lblEvent.setVisibility(View.VISIBLE);
                    lblEvent.setText("Evénement du "+strDate+" : ");

                    TextView descEvent = (TextView)findViewById(R.id.descEvent);
                    descEvent.setVisibility(View.VISIBLE);


                    if (date_bdd.equals(date_clicked)) {
                        descEvent.setText(event.getData().toString());
                    } else {
                        descEvent.setText("Aucun événement");
                    }

                    final Button btn = (Button) findViewById(R.id.btn_ajout);
                    btn.setVisibility(View.VISIBLE);
                    btn.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            final Intent myIntent = new Intent(getApplicationContext(), activity_Event.class);

                            myIntent.putExtra("DATE", date_clicked);

                            startActivity(myIntent);
                        }
                    });

                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
                }


            });


        }
    }
}
