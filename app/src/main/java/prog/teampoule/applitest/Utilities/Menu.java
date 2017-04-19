package prog.teampoule.applitest.Utilities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import prog.teampoule.applitest.Activity.activity_Allmessages;
import prog.teampoule.applitest.Activity.activity_Calendrier;
import prog.teampoule.applitest.Activity.activity_Conseils;
import prog.teampoule.applitest.Activity.activity_Default;
import prog.teampoule.applitest.Activity.activity_MatchAleatoire;
import prog.teampoule.applitest.Activity.activity_MiniJeu;
import prog.teampoule.applitest.Activity.activity_Patinoire;
import prog.teampoule.applitest.Activity.activity_Friend;
import prog.teampoule.applitest.Activity.activity_login;
import prog.teampoule.applitest.Activity.activity_Tutoriels;
import prog.teampoule.applitest.R;

/**
 * Created by Julien on 23/02/2017.
 */

public class Menu  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public boolean is_Connected = false;
    public NavigationView navigationView;

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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        CreateIs_connected();
        if(is_Connected){
            Log.d("Connected","Reussie");
            navigationView.getMenu().findItem(R.id.Menu_itemAmis).setVisible(true);
        }else{
            navigationView.getMenu().findItem(R.id.Menu_itemAmis).setVisible(false);
        }
    }

    protected void CreateIs_connected() {
        SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
        if(!prefs.getBoolean("is_Connected",false)){
            SharedPreferences.Editor editor = getSharedPreferences("MYPREF", MODE_PRIVATE).edit();
            editor.putBoolean("is_Connected", false);
            editor.commit();
            is_Connected = false;
        }else{
            is_Connected = true;
        }
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
                myIntent = new Intent(getApplicationContext(), activity_Patinoire.class);
                startActivity(myIntent);
                break;
            case R.id.id_conseil:
                myIntent = new Intent(getApplicationContext(), activity_Conseils.class);
                startActivity(myIntent);
                break;
            case R.id.id_tutoriel:
                myIntent = new Intent(getApplicationContext(), activity_Tutoriels.class);
                startActivity(myIntent);
                break;
            case R.id.id_amis:
                myIntent = new Intent(getApplicationContext(), activity_Friend.class);
                startActivity(myIntent);
                break;
            case R.id.id_calendrier:
                myIntent = new Intent(getApplicationContext(), activity_Calendrier.class);
                startActivity(myIntent);
                break;
            case R.id.id_jeu:
                myIntent = new Intent(getApplicationContext(), activity_MiniJeu.class);
                startActivity(myIntent);
                break;
            case R.id.id_match:
                myIntent = new Intent(getApplicationContext(), activity_MatchAleatoire.class);
                startActivity(myIntent);
                break;
            case R.id.id_profil:
                myIntent = new Intent(getApplicationContext(), activity_login.class);
                startActivity(myIntent);
                break;
            case R.id.id_message:
                myIntent = new Intent(getApplicationContext(), activity_Allmessages.class);
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
