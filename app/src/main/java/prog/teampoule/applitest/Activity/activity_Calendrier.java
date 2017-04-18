package prog.teampoule.applitest.Activity;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

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

    public static String getDate(long milliSeconds, String dateFormat)
    {
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

        //Set an event for Teachers' Professional Day 2016 which is 21st of October

        final Event ev1 = new Event(Color.LTGRAY, 1477040400000L, "Teachers' Professional Day");
        final Event ev2 = new Event(Color.LTGRAY, 1491796800000L, "Test event");

        compactCalendar.addEvent(ev1);
        compactCalendar.addEvent(ev2);

        MySQLiteHelper db = new MySQLiteHelper(this);
        EvenementsBDD events = new EvenementsBDD(db);

        List<Evenements> listEvents = new ArrayList<Evenements>();
        listEvents = events.getAllEvenements();

        String nom_evenements = new String();
        String date_evenements = new String();


        nom_evenements = listEvents.get(0).getNom_evenement();
        date_evenements = listEvents.get(0).getDate_evenement();

        //date_evenements = date_evenements + "L";
        long date = Long.parseLong(date_evenements);

        final Event event = new Event(Color.RED, date, nom_evenements);
        compactCalendar.addEvent(event);

        String test_date = getDate(date, "E MM dd hh:mm:ss yyyy");

        Log.d("date", test_date);
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                //Toast.makeText(context, dateClicked.toString(), Toast.LENGTH_SHORT).show();

                if (dateClicked.toString().compareTo("Sun Apr 16 00:00:00 EDT 2017") == 0) {
                    Toast.makeText(context, event.getData().toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Pas d'événement prévu", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }


        });



    }
}
