package prog.teampoule.applitest;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.id_patinoire:
                Toast.makeText(getApplicationContext(),"Patinoire",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_conseil:
                Toast.makeText(getApplicationContext(),"Conseil",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_tutoriel:
                Toast.makeText(getApplicationContext(),"Tutoriel",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_amis:
                Toast.makeText(getApplicationContext(),"Amis",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_calendrier:
                Toast.makeText(getApplicationContext(),"Calendrier",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_jeu:
                Toast.makeText(getApplicationContext(),"Jeu",Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_match:
                Toast.makeText(getApplicationContext(),"Match",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
