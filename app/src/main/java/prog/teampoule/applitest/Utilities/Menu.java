package prog.teampoule.applitest.Utilities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import prog.teampoule.applitest.Activity.activity_Calendrier;
import prog.teampoule.applitest.Activity.activity_Conseils;
import prog.teampoule.applitest.Activity.activity_Default;
import prog.teampoule.applitest.Activity.activity_MatchAleatoire;
import prog.teampoule.applitest.Activity.activity_MiniJeu;
import prog.teampoule.applitest.Activity.activity_Patinoire;
import prog.teampoule.applitest.R;

/**
 * Created by Julien on 23/02/2017.
 */

public class Menu  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent myIntent;
        switch (id){
            case R.id.id_patinoire:
                Toast.makeText(getApplicationContext(),"Patinoire",Toast.LENGTH_SHORT).show();
                myIntent = new Intent(getApplicationContext(), activity_Patinoire.class);
                startActivity(myIntent);
                break;
            case R.id.id_conseil:
                Toast.makeText(getApplicationContext(),"Conseil",Toast.LENGTH_SHORT).show();
                myIntent = new Intent(getApplicationContext(), activity_Conseils.class);
                startActivity(myIntent);
                break;
            case R.id.id_tutoriel:
                Toast.makeText(getApplicationContext(),"Tutoriel",Toast.LENGTH_SHORT).show();
                myIntent = new Intent(getApplicationContext(), activity_Default.class);
                startActivity(myIntent);
                break;
            case R.id.id_amis:
                Toast.makeText(getApplicationContext(),"Amis",Toast.LENGTH_SHORT).show();
                myIntent = new Intent(getApplicationContext(), activity_Default.class);
                startActivity(myIntent);
                break;
            case R.id.id_calendrier:
                Toast.makeText(getApplicationContext(),"Calendrier",Toast.LENGTH_SHORT).show();
                myIntent = new Intent(getApplicationContext(), activity_Calendrier.class);
                startActivity(myIntent);
                break;
            case R.id.id_jeu:
                Toast.makeText(getApplicationContext(),"Jeu",Toast.LENGTH_SHORT).show();
                myIntent = new Intent(getApplicationContext(), activity_MiniJeu.class);
                startActivity(myIntent);
                break;
            case R.id.id_match:
                Toast.makeText(getApplicationContext(),"Match",Toast.LENGTH_SHORT).show();
                myIntent = new Intent(getApplicationContext(), activity_MatchAleatoire.class);
                startActivity(myIntent);
                break;
            default:
                myIntent = new Intent(getApplicationContext(), activity_Default.class);
                startActivity(myIntent);
                break;
        }
        finish();
        return false;
    }
}
